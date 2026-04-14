package com.microsoft.japan.ddd.domain.model.isbn;

import org.junit.jupiter.api.Test;

import com.microsoft.japan.ddd.catalog.domain.model.isbn.Publication;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublicationTest {

    @Test
    @DisplayName("コンストラクタの引数が null か空の場合 IllegalArgumentException がスローされる")
    void testConstructorNegative1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Publication(null));
        assertEquals("Publication は空であってはなりません。", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Publication(""));
        assertEquals("Publication は空であってはなりません。", exception.getMessage());

    }

    @Test
    @DisplayName("引数の文字列長が MAX_LENGTH を超える場合 IllegalArgumentException がスローされる")
    void testConstructorNegative2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Publication("123456789012345678901")); // 21文字
        assertEquals("Publication には " + Publication.MAX_LENGTH + " 文字より大きい値を設定できません。", exception.getMessage());

        // 境界値テスト
        exception = assertThrows(IllegalArgumentException.class, () -> new Publication("1234567"));
        assertEquals("Publication には " + Publication.MAX_LENGTH + " 文字より大きい値を設定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("引数が負の場合 IllegalArgumentException がスローされる")
    void testConstructorNegative3() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Publication("-12345"));
        assertEquals("Publication は負の数であってはなりません。", exception.getMessage());

        // 境界値テスト
        exception = assertThrows(IllegalArgumentException.class, () -> new Publication("-1"));
        assertEquals("Publication は負の数であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("引数が数字でない場合 IllegalArgumentException がスローされる")
    void testConstructorNegative4() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Publication("abcde"));
        assertEquals("Publication は数字でなければなりません。", exception.getMessage());

        // 境界値テスト
        exception = assertThrows(IllegalArgumentException.class, () -> new Publication("12345a"));
        assertEquals("Publication は数字でなければなりません。", exception.getMessage());
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
