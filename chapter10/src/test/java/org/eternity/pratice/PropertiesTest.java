package org.eternity.pratice;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesTest {

    @Test
    void PropertiesTask() {
        Properties properties = new Properties();
        properties.setProperty("chanhan", "Java");
        properties.setProperty("Javajigi", "JavaScript");

        properties.put("minsu", 77);

        assertEquals(null, properties.getProperty("minsu"));
    }
}
