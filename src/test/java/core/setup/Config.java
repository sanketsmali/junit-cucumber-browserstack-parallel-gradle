package core.setup;



import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static core.utilities.Tools.logger;
import static java.lang.System.getProperty;

public class Config {
	public static final String WORKSPACE = getProperty("user.dir");
	private static String env = getProperty("env", "https://gfycat.com/");
	Boolean parallel = Boolean.parseBoolean(getProperty("parallel", "false"));
	public static final Boolean IS_REMOTE = Boolean.parseBoolean(getProperty("isRemote", "true"));


	private static JSONObject config;
	public int taskID;

	/**
	 * set platform property to -> Android, iOS, or Web and then sets desired
	 * capabilities based off set platform
	 */
	public Config() {
		logger().traceEntry();
		logger().traceExit();
	}

	public DesiredCapabilities setCapabilitties() throws FileNotFoundException, IOException, ParseException {
		List<Integer> taskIDs = new ArrayList<Integer>();
		JSONParser parser = new JSONParser();
		config = (JSONObject) parser.parse(new FileReader("src/test/resources/jsonData/caps.json"));
		int envs = ((JSONArray) config.get("environments")).size();

		for (int i = 0; i < envs; i++) {
			taskIDs.add(i);
		}

		JSONArray env = (JSONArray) config.get("environments");
		System.out.println("env:" + env);

		DesiredCapabilities capabilities = new DesiredCapabilities();

		Map<String, String> envCapabilities = (Map<String, String>) env.get(taskID);
		Iterator it = envCapabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
		}

		System.out.println("envCapabilities : " + envCapabilities);

		Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
		it = commonCapabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (capabilities.getCapability(pair.getKey().toString()) == null) {
				capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
			}
		}

		String username = System.getenv("BROWSERSTACK_USERNAME");
		if (username == null) {
			username = (String) config.get("user");
		}

		capabilities.setCapability("browserstack.user", username);

		String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
		if (accessKey == null) {
			accessKey = (String) config.get("key");
		}
		capabilities.setCapability("browserstack.key", accessKey);

		System.out.println("caps : " + capabilities);
		return capabilities;

	}

	public static String getEnv() {
		return env;
	}

	static void setEnv(String env) {
		logger().traceEntry();
		logger().traceExit(env);
		Config.env = env;
	}


}
