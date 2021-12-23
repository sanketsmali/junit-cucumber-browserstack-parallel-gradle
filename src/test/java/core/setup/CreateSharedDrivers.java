package core.setup;


import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static core.utilities.Tools.logger;
import static java.lang.System.getProperty;

import java.net.MalformedURLException;
import java.net.URL;

public class CreateSharedDrivers {

	/** initialize this class to create a driver if driver is not null 
	 * @throws MalformedURLException */
	public CreateSharedDrivers() throws MalformedURLException {
		if (Hooks.getDriver() == null) {
			createDriver();
		}
	}

	/** quits all storedDrivers with a shutdown hook */
	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> Hooks.storedDrivers.forEach(driver -> {
			logger().info(String.format("Stored Driver Count: [%s]", String.valueOf(Hooks.storedDrivers.size())));

			logger().info(String.format("Driver [%s] will be quit", driver));

			try {
				driver.quit();
			} catch (Exception e) {
				driver = null;
			}
		})));
	}

	
	private void createDriver() throws MalformedURLException {

		String url = "https://@hub-cloud.browserstack.com/wd/hub";
		URL url1 = new URL(url);
		try {
			Hooks.addDriver(new RemoteWebDriver(url1, Hooks.capabilities));
			Hooks.getDriver().manage().deleteAllCookies();
			Hooks.getDriver().manage().window().maximize();
			
		} catch (ElementNotInteractableException e) {
			// Ignore Exception
		}

	}
}
