package org.eternity.pratice.stackAndProperties;

import java.util.EmptyStackException;
import java.util.Vector;

// 안정성을 높이기 위한 합성의 용도
public class Stack<T> {

    private Vector<T> elements = new Vector<>();

    public void push(T element) {
        elements.add(element);
    }

    public T pop() {
        if(elements.isEmpty()) {
            throw new EmptyStackException();
        }
         return elements.remove(elements.size() - 1);
    }

    public Boolean isEmpty() {
        return elements.isEmpty();
    }

    public T peek() {
        return elements.get(elements.size() - 1);
    }

}
