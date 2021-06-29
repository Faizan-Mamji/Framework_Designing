package org.systems.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class propertyReader {
    public FileInputStream fs;
    public Properties prop=new Properties();

    public propertyReader() {
        try {
            fs = new FileInputStream("./config.properties");
            prop.load(fs);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public String getValue(String propValue) {
        return prop.getProperty(propValue);
    }
}
