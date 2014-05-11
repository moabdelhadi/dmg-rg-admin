package com.dmg.admin.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VersionReader {

	private static Logger log = LoggerFactory.getLogger(VersionReader.class);

	private static VersionReader manager = null;
	private static Properties properties = null;

	private static final String PROPERTY_FILE = "version.properties";

	private VersionReader() {
		loadProperties(PROPERTY_FILE);

	}

	public static VersionReader getInstance() {
		if (manager != null) {
			return manager;
		}
		synchronized (VersionReader.class) {
			if (VersionReader.manager == null) {
				VersionReader.manager = new VersionReader();
			}
		}
		return manager;
	}

	private void loadProperties(String fileName) {
		try {
			properties = new Properties();
			InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("../../WEB-INF/classes/" + fileName);
			InputStreamReader reader = new InputStreamReader(resourceAsStream, "UTF8");

			properties.load(reader);

		} catch (FileNotFoundException e) {
			log.error("Error in loading property file", e);

		} catch (IOException e) {
			log.error("Error in loading property file", e);
		}

	}

	public String getVersion() {

		Object object = properties.get("version");
		log.debug("propertie" + object.toString());
		return object.toString();

	}

}
