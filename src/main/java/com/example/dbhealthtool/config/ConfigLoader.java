package com.example.dbhealthtool.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties props = new Properties();

    static {
        try {
            InputStream is = ConfigLoader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (is == null) {
                throw new RuntimeException("config.properties not found in classpath!");
            }

            props.load(is);

        } catch (Exception e) {
            System.out.println("Error loading config file!");
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}