package com.microsoft.japan.ddd.catalog.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContentsTest {

    @Test
    @DisplayName("Contents クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証するテスト")
    void testConstructorNegative1() {
        // Contents クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証します。
        var exception = assertThrows(IllegalArgumentException.class, () -> new Contents(null));
        assertEquals("Contents には null を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("Contents クラスのコンストラクタに空文字を渡すと IllegalArgumentException がスローされることを検証するテスト")
    void testConstructorNegative2() {
        // Contents クラスのコンストラクタに空文字を渡すと IllegalArgumentException がスローされることを検証します。
        var exception = assertThrows(IllegalArgumentException.class, () -> new Contents(" "));
        assertEquals("Contents には null を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("Contents クラスのコンストラクタに最大長を超える文字列を渡すと IllegalArgumentException がスローされることを検証するテスト")
    void testConstructorNegative3() {
        // Contents クラスのコンストラクタに最大長を超える文字列を渡すと IllegalArgumentException がスローされることを検証します。
        var exception = assertThrows(IllegalArgumentException.class, () -> new Contents("a".repeat(Contents.MAX_LENGTH + 1)));
        assertEquals("Contents の長さは " + Contents.MAX_LENGTH + " 文字以内である必要があります。", exception.getMessage());
    }

    @Test
    @DisplayName("Contents クラスのコンストラクタに有効な文字列を渡すと正しくオブジェクトが生成されることを検証するテスト")
    void testConstructorPositive() {
        // Contents クラスのコンストラクタに有効な文字列を渡すと正しくオブジェクトが生成されることを検証します。
        String validContents = "これは有効なコンテンツです。";
        Contents contents = new Contents(validContents);
        assertEquals(validContents, contents.value());
    }

    @Test
    @DisplayName("Contents クラスのコンストラクタに最大長の文字列を渡すと正しくオブジェクトが生成されることを検証するテスト")
    void testConstructorPositive2() {
        // Contents クラスのコンストラクタに最大長の文字列を渡すと正しくオブジェクトが生成されることを検証します。
        String validContents = "a".repeat(Contents.MAX_LENGTH);
        Contents contents = new Contents(validContents);
        assertEquals(validContents, contents.value());
    }

}
