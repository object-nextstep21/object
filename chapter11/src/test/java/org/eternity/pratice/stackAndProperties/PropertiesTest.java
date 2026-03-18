package org.eternity.pratice.stackAndProperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 과제 1-2: Properties를 합성으로 리팩터링
 *
 * 기존 java.util.Properties는 Hashtable<Object, Object>를 상속받아
 * String이 아닌 키/값도 저장할 수 있는 캡슐화 위반 문제가 있었다.
 *
 * 목표:
 * - Properties가 내부에 java.util.Hashtable을 인스턴스 변수로 가진다 (합성)
 * - setProperty(String, String)과 getProperty(String)만 퍼블릭 인터페이스로 노출한다
 * - Hashtable의 put(Object, Object) 같은 메서드는 외부에 노출되지 않는다
 * - 따라서 String 타입만 키/값으로 사용할 수 있다 (타입 안전성 확보)
 */
class PropertiesTest {

    private Properties properties;

    @BeforeEach
    void setUp() {
        properties = new Properties();
    }

    @Test
    @DisplayName("setProperty로 저장한 값을 getProperty로 조회할 수 있다")
    void setAndGetProperty() {
        properties.setProperty("encoding", "UTF-8");

        assertEquals("UTF-8", properties.getProperty("encoding"));
    }

    @Test
    @DisplayName("존재하지 않는 키를 조회하면 null을 반환한다")
    void getPropertyReturnsNullForMissingKey() {
        assertNull(properties.getProperty("없는키"));
    }

    @Test
    @DisplayName("같은 키로 다시 저장하면 값이 덮어쓰기된다")
    void setPropertyOverwritesExistingValue() {
        properties.setProperty("version", "1.0");
        properties.setProperty("version", "2.0");

        assertEquals("2.0", properties.getProperty("version"));
    }

    @Test
    @DisplayName("여러 키-값 쌍을 독립적으로 저장하고 조회할 수 있다")
    void multiplePropertiesAreIndependent() {
        properties.setProperty("db.host", "localhost");
        properties.setProperty("db.port", "3306");
        properties.setProperty("db.name", "mydb");

        assertEquals("localhost", properties.getProperty("db.host"));
        assertEquals("3306", properties.getProperty("db.port"));
        assertEquals("mydb", properties.getProperty("db.name"));
    }
}
