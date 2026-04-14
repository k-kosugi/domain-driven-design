package com.microsoft.japan.ddd.domain.model.isbn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.japan.ddd.catalog.domain.model.isbn.RegistrationGroup;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationGroupTest {

    @Test
    @DisplayName("コンストラクタに null を渡すと例外が発生すること")
    void testConstructorNegative1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegistrationGroup(null);
        });

        assertEquals("RegistrationGroup は空であってはなりません。", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegistrationGroup("");
        });

        assertEquals("RegistrationGroup は空であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("コンストラクタに 5 文字以上の文字列を渡すと例外が発生すること")
    void testConstructorNegative2() {
        // 境界値
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegistrationGroup("123456");
        });
        
        assertEquals("RegistrationGroup には " + RegistrationGroup.MAX_LENGTH + " 文字以上を設定できません", exception.getMessage());
    }

    @Test
    @DisplayName("コンストラクタに数字以外の文字列を渡すと例外が発生すること")
    void testConstructorNegative3() {
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegistrationGroup("abc");
        });
        
        assertEquals("RegistrationGroup は数字でなければなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("コンストラクタに有効な文字列を渡すと RegistrationGroup オブジェクトが作成されること")
    void testConstructorPositive() {
        // 境界値
        var registrationGroup = new RegistrationGroup("12345");
        assertEquals("12345", registrationGroup.value());
    }

    @Test
    void testEquals() {

    }

    @Test
    void testHashCode() {

    }

    @Test
    void testLength() {

    }

    @Test
    void testToCharArray() {

    }

    @Test
    void testToString() {

    }

    @Test
    void testValue() {

    }
}
