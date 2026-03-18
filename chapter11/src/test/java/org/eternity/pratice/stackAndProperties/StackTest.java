package org.eternity.pratice.stackAndProperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 과제 1-1: Stack을 합성으로 리팩터링
 *
 * 기존 java.util.Stack은 Vector를 상속받아 LIFO가 아닌 방식으로도
 * 요소에 접근할 수 있는 캡슐화 위반 문제가 있었다.
 *
 * 목표:
 * - Stack이 내부에 java.util.LinkedList를 인스턴스 변수로 가진다 (합성)
 * - push, pop, peek, isEmpty만 퍼블릭 인터페이스로 노출한다
 * - Vector/List의 get(index), add(index, element) 같은 메서드는 외부에 노출되지 않는다
 */
class StackTest {

    private Stack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    @DisplayName("새로 생성된 스택은 비어있다")
    void newStackIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("push한 요소를 pop하면 LIFO 순서로 반환된다")
    void pushAndPopFollowsLifo() {
        stack.push("첫번째");
        stack.push("두번째");
        stack.push("세번째");

        assertEquals("세번째", stack.pop());
        assertEquals("두번째", stack.pop());
        assertEquals("첫번째", stack.pop());
    }

    @Test
    @DisplayName("pop 후 스택이 비면 isEmpty가 true를 반환한다")
    void isEmptyAfterPoppingAll() {
        stack.push("요소");
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("peek은 최상위 요소를 반환하되 제거하지 않는다")
    void peekReturnsTopWithoutRemoving() {
        stack.push("첫번째");
        stack.push("두번째");

        assertEquals("두번째", stack.peek());
        assertEquals("두번째", stack.peek()); // 두 번 호출해도 같은 값
        assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("빈 스택에서 pop하면 예외가 발생한다")
    void popOnEmptyStackThrowsException() {
        assertThrows(RuntimeException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("빈 스택에서 peek하면 예외가 발생한다")
    void peekOnEmptyStackThrowsException() {
        assertThrows(RuntimeException.class, () -> stack.peek());
    }
}
