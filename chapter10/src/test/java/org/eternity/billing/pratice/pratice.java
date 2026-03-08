package org.eternity.billing.pratice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Properties;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class pratice {

    @Test
    void stackTest() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack); // [1, 2, 3]

        stack.add(0,5);
        System.out.println(stack); // [5, 1, 2, 3]
    }

    @Test
    void propertiesTest() {
        Properties properties = new Properties();
        properties.put("key1", "value1");
        properties.put("key2", "value2");
        System.out.println(properties); // {key1=value1, key2=value2}

        properties.put("key3", 3);
        System.out.println(properties); // {key1=value1, key2=value2, key3=3}

        properties.getProperty("key3");
        System.out.println(properties.getProperty("key3"));
        assertNull(properties.getProperty("key3"));
    }

}
