package com.microsoft.japan.ddd.catalog.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;

public class TitleTest {

    @Test
    @DisplayName("Title のコンストラクタに null を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Title(null));
        assert exception.getMessage().equals("タイトルは null もしくは空白であってはなりません。");
    }

    @Test
    @DisplayName("Title のコンストラクタに空白を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Title("   "));
        assert exception.getMessage().equals("タイトルは null もしくは空白であってはなりません。");
    }

    @Test
    @DisplayName("Title のコンストラクタに最大長を超える文字列を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative3() {
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Title("a".repeat(Title.MAX_LENGTH + 1)));
        assert exception.getMessage().equals("タイトルは " + Title.MAX_LENGTH + " 文字以内でなければなりません。");
    }

    @Test
    @DisplayName("Title のコンストラクタに有効な文字列を渡すと Title オブジェクトが正常に作成されること")
    void testConstructorPositive() {
        String validTitle = "有効なタイトル";
        Title title = new Title(validTitle);
        assert title.value().equals(validTitle);
    }

    @Test
    @DisplayName("Title のコンストラクタに最大長の文字列を渡すと Title オブジェクトが正常に作成されること")
    void testConstructorPositive2() {
        String validTitle = "a".repeat(Title.MAX_LENGTH);
        Title title = new Title(validTitle);
        assert title.value().equals(validTitle);
    }

}
