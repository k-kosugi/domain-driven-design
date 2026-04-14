package com.microsoft.japan.ddd.catalog.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthorIdTest {

    @Test
    @DisplayName("AuthorId クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative01() {
        // AuthorId クラスのコンストラクタをテストします。
        var exception = assertThrows(IllegalArgumentException.class, () -> new AuthorId(null));
        assertEquals("AuthorId には null または空白を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("AuthorId クラスのコンストラクタに空白を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative02() {
        // AuthorId クラスのコンストラクタをテストします。
        var exception = assertThrows(IllegalArgumentException.class, () -> new AuthorId("   "));
        assertEquals("AuthorId には null または空白を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("AuthorId クラスのコンストラクタに最大長を超える文字列を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative03() {
        // AuthorId クラスのコンストラクタをテストします。
        var exception = assertThrows(IllegalArgumentException.class, () -> new AuthorId("a".repeat(AuthorId.MAX_LENGTH + 1)));
        assertEquals("AuthorId は最大 " + AuthorId.MAX_LENGTH + " 文字まで指定できます。", exception.getMessage());
    }

    @Test
    @DisplayName("AuthorId クラスのコンストラクタに有効な文字列を渡すと AuthorId オブジェクトが作成されること")
    void testConstructorPositive() {
        // AuthorId クラスのコンストラクタをテストします。
        var authorId = new AuthorId("12345");
        assertEquals("12345", authorId.value());
    }

    @Test
    @DisplayName("AuthorId クラスのコンストラクタに最大長の文字列を渡すと AuthorId オブジェクトが作成されること")
    void testConstructorPositive2() {
        // 境界値のテスト
        var authorId = new AuthorId("a".repeat(AuthorId.MAX_LENGTH));
        assertEquals("a".repeat(AuthorId.MAX_LENGTH), authorId.value());
    }

}
