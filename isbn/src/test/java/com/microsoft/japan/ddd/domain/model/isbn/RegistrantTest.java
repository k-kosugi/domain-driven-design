package com.microsoft.japan.ddd.domain.model.isbn;

import org.junit.jupiter.api.Test;

import com.microsoft.japan.ddd.catalog.domain.model.isbn.Registrant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;

public class RegistrantTest {

    @Test
    @DisplayName("Registrant のコンストラクタの引数が null または空文字の場合に例外がスローされることを検証するテスト")
    void testConstructorNegative1() {
        // Registrant が null の場合のテスト
        var exception = assertThrows(IllegalArgumentException.class, () -> new Registrant(null));
        assertEquals("Registrant は空であってはなりません。", exception.getMessage());

        // Registrant が空文字の場合のテスト
        exception = assertThrows(IllegalArgumentException.class, () -> new Registrant(""));
        assertEquals("Registrant は空であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("Registrant のコンストラクタの引数が指定された長さを超える場合に例外がスローされることを検証するテスト")
    void testConstructorNegative2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Registrant("12345678"));
        assertEquals("Registrant には " + Registrant.MAX_LENGTH + " 文字以上を設定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("Registrant のコンストラクタの引数が負の数の場合に例外がスローされることを検証するテスト")
    void testConstructorNegative3() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Registrant("-123"));
        assertEquals("Registrant は負の数であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("Registrant のコンストラクタの引数が数字でない場合に例外がスローされることを検証するテスト")
    void testConstructorNegative4() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Registrant("abc"));
        assertEquals("Registrant は数字でなければなりません。", exception.getMessage());
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
