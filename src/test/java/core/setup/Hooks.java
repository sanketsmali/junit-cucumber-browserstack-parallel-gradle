package core.setup;

import core.utilities.Tools;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.json.simple.parser.ParseException;
import org.junit.Assume;
import org.junit.AssumptionViolatedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Hooks {

	public static ThreadLocal<Scenario> scenarios = new ThreadLocal<>();
	private boolean setup = false;
	private static boolean reportsCreated = false;

	private Config config;
	private static SoftAssertions softAssert;
	static URL url;
	static DesiredCapabilities capabilities;

	// drivers and storedDrivers needed for parallel runs with multiple threads.
	private static ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<>();
	static List<RemoteWebDriver> storedDrivers = new ArrayList<>();

	/**
	 * logic performed before scenario start
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Before(order = 1)
	public void beforeScenario(Scenario scenario) throws FileNotFoundException, IOException, ParseException {
		Hooks.setScenario(scenario);
		manageResults();

		config = new Config();
		// config.setCapabilities();
		softAssert = new SoftAssertions();

		if (config.parallel)
			System.out.printf("[Thread %2d] Running -> [Scenario: %s]%n", Thread.currentThread().getId(),
					Hooks.getScenario().getName());

		capabilities = config.setCapabilitties();
		new CreateSharedDrivers();
		JavascriptExecutor jse = (JavascriptExecutor) Hooks.getDriver();
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""+Hooks.getScenario().getName()+"\" }}");
		
	}

	/** logic performed after scenario is complete */
	@After(order = 2)
	public void afterScenario() {
		
		
		JavascriptExecutor jse = (JavascriptExecutor)Hooks.getDriver();;
		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+Hooks.getScenario().getStatus()+"\", \"reason\": \"<reason>\"}}");

		try {
			if (!softAssert.errorsCollected().isEmpty())
				softAssert.assertAll();

			if (Hooks.getScenario().isFailed()) {
				if (config.parallel) {
					System.out.printf("[Thread %2d] Running -> [Scenario: %s] - FAILED - (*_*)%n",
							Thread.currentThread().getId(), Hooks.getScenario().getName());
				}

			}

			if (!softAssert.errorsCollected().isEmpty())
				softAssert.assertAll();

		} finally {

			if (config.parallel && !Hooks.getScenario().isFailed()) {
				System.out.printf("[Thread %2d] Running -> [Scenario: %s] - PASSED - (^_^)%n",
						Thread.currentThread().getId(), Hooks.getScenario().getName());
				
				
			}

		}
	}

	/** removes previously created reports and temp files */
	private void manageResults() {
		if (!reportsCreated) {
			try {
				FileUtils.deleteDirectory(new File("./TestResults"));
			} catch (IOException | IllegalArgumentException e) {
				// Ignored files not present
			}

			try {
				FileUtils.forceMkdir(new File("./TestResults/ScreenShots"));
			} catch (IOException | IllegalArgumentException e) {
				System.err.println("Failed to create TestResults file directory");
			}

			reportsCreated = true;

		}
	}

	/** skips a scenario if not valid for current run */
	private void skipScenario(String errorReason) {
		try {
			Assume.assumeTrue(false);
		} catch (AssumptionViolatedException e) {
			throw new AssumptionViolatedException(
					Tools.border("- Scenario: %s%n- Was skipped for: %s", Hooks.getScenario().getName(), errorReason));
		}
	}
	// </editor-fold>

	// <editor-fold desc="@Tag Hooks">
	/** Skip Scenario if Tagged @wip */
	@Before("@wip")
	public void wipSkip() {
	
		skipScenario("being @wip");

	}
	// </editor-fold>-

	// <editor-fold desc="Get And Sets">
	public static SoftAssertions getSoftAssert() {
		return softAssert;
	}

	public static void addDriver(RemoteWebDriver driver) {
		storedDrivers.add(driver);
		drivers.set(driver);
		System.out.printf("[Thread %2d] Created and Added Driver: [%s]%n", Thread.currentThread().getId(),
				Hooks.getDriver());
	}

	public static RemoteWebDriver getDriver() {
		return drivers.get();
	}

	public static void setScenario(Scenario scenario) {
		scenarios.set(scenario);
	}

	public static Scenario getScenario() {
		return scenarios.get();
	}
	// </editor-fold>
}
