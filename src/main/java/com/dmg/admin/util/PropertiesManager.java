package com.dmg.admin.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesManager {

	private static Logger log = LoggerFactory.getLogger(PropertiesManager.class);

	private static PropertiesManager manager = null;
	private static Properties properties = null;

	private static final String PROPERTY_FILE = "config.properties";

	private PropertiesManager() {
		loadProperties(PROPERTY_FILE);

	}

	public static PropertiesManager getInstance() {
		if (manager != null) {
			return manager;
		}
		synchronized (PropertiesManager.class) {
			if (PropertiesManager.manager == null) {
				PropertiesManager.manager = new PropertiesManager();
			}
		}
		return manager;
	}

	private void loadProperties(String fileName) {
		try {
			properties = new Properties();
			InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("../../WEB-INF/config/" + fileName);
			InputStreamReader reader = new InputStreamReader(resourceAsStream, "UTF8");

			properties.load(reader);

		} catch (FileNotFoundException e) {
			log.error("Error in loading property file", e);

		} catch (IOException e) {
			log.error("Error in loading property file", e);
		}

	}

	public String getProperty(String key) {

		Object object = properties.get(key);
		log.debug("propertie" + object.toString());
		return object.toString();

	}

}
