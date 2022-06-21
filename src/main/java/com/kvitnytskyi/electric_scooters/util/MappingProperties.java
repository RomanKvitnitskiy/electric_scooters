package com.kvitnytskyi.electric_scooters.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MappingProperties {

    private static final Logger log = Logger.getLogger(MappingProperties.class);
    private static final String propFile = "pages.properties";
    private static MappingProperties instance;
    private final Properties properties;

    private MappingProperties() {
        log.info("Initializing mapping properties class");
        properties = new Properties();

        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(propFile);
            properties.load(stream);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public static synchronized MappingProperties getInstance() {
        if (instance == null) {
            instance = new MappingProperties();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
