package org.eternity.rectangle;

public class Any {

    // 코드중복, 변경 취약
    void scaleUp(Rectangle rectangle, int multiple) {
        rectangle.setLeft(rectangle.getLeft() * multiple);
        rectangle.setRight(rectangle.getRight() * multiple);
        rectangle.setTop(rectangle.getTop() * multiple);
        rectangle.setBottom(rectangle.getBottom() * multiple);
    }

    void scaleUpByAuto(Rectangle rectangle, int multiple) {
        rectangle.enlarge(multiple);
    }

}
