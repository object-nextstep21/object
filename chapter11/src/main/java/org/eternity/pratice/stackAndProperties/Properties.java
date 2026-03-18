package org.eternity.pratice.stackAndProperties;

import java.util.Hashtable;

// 안정성을 높이기 위한 합성의 용도
public class Properties {

    private Hashtable<String, String> properties = new Hashtable<>();

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }
}
