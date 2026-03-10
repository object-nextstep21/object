package org.eternity.pratice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.*;

class StackTest {

    @Test
    void stackTast() {
        Stack<String> stack = new Stack<>();
        stack.add("1");
        stack.add("2");
        stack.add("3");
        stack.add(0, "4");

        assertThat(stack.pop()).isNotEqualTo("4");
    }
}