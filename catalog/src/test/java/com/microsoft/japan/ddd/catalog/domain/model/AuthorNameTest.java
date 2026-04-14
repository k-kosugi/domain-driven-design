package com.microsoft.japan.ddd.catalog.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthorNameTest {

    @Test
    @DisplayName("AuthorName クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証します。")
    void testConstructorNegative1() {
        // AuthorName クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証します。
        var exception1 = assertThrows(IllegalArgumentException.class, () -> new AuthorName(null));
        assertEquals("AuthorName には null または空白を指定できません。", exception1.getMessage());
    }

    @Test
    @DisplayName("AuthorName クラスのコンストラクタに空白を渡すと IllegalArgumentException がスローされることを検証します。")
    void testConstructorNegative2() {
        // AuthorName クラスのコンストラクタに空白を渡すと IllegalArgumentException がスローされることを検証します。
        var exception2 = assertThrows(IllegalArgumentException.class, () -> new AuthorName("   "));
        assertEquals("AuthorName には null または空白を指定できません。", exception2.getMessage());
    }

    @Test
    @DisplayName("AuthorName クラスのコンストラクタに最大長を超える文字列を渡すと IllegalArgumentException がスローされることを検証します。")
    void testConstructorNegative3() {
        // AuthorName クラスのコンストラクタに最大長を超える文字列を渡すと IllegalArgumentException がスローされることを検証します。
        var longString = "a".repeat(AuthorName.MAX_LENGTH + 1);
        var exception3 = assertThrows(IllegalArgumentException.class, () -> new AuthorName(longString));
        assertEquals("AuthorName は最大 " + AuthorName.MAX_LENGTH + " 文字まで指定できます。", exception3.getMessage());
    }

    @Test
    @DisplayName("AuthorName クラスのコンストラクタに有効な値を渡すと正しくオブジェクトが生成されることを検証します。")
    void testConstructorPositive() {
        // 境界値分析の観点から、AuthorName クラスのコンストラクタに最大長の文字列を渡すと正しくオブジェクトが生成されることを検証します。
        var longString = "a".repeat(AuthorName.MAX_LENGTH);
        var authorName = new AuthorName(longString);
        assertEquals(longString, authorName.value());
    }

    @Test
    @DisplayName("AuthorName クラスの value メソッドが正しい値を返すことを検証します。")
    void testValue() {
        var authorName = new AuthorName("Kenta");
        assertEquals("Kenta", authorName.value());  
    }
}
