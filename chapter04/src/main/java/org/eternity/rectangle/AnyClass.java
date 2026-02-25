package org.eternity.rectangle;

public class AnyClass {

    void anyMethod(Rectangle rectangle, int multiple){
        /*
         * Ractangle의 내부가 변경된다면 Ractangle을 사용하는
         * 해당 클래스의 내부 로직도 변경이 필요하다는 단점
         */
        rectangle.setRight(rectangle.getRight() * multiple);
        rectangle.setBottom(rectangle.getBottom() * multiple);

        /*
         * 증가 시키는 연산은 캡슐화할 경우
         * Ractangle의 내부가 변경되도 해당 클래스는 변경하지 않아도 된다.
         */
        rectangle.enlarge(multiple);
    }
}
