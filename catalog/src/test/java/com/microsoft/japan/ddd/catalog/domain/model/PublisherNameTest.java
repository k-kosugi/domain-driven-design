package com.microsoft.japan.ddd.catalog.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PublisherNameTest {

    @Test
    @DisplayName("PublisherName が null の場合 IllegalArgumentException がスローされること")
    void testConstructorNegative1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> 
            new PublisherName(null)
        );

        assertEquals("PublisherName には null または空白を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("PublisherName が空白の場合 IllegalArgumentException がスローされること")
    void testConstructorNegative2() {
        // 境界値分析の観点から、PublisherName が空文字列の場合も IllegalArgumentException がスローされることを検証します。
        var exception = assertThrows(IllegalArgumentException.class, () -> 
            new PublisherName(" ")
        );

        assertEquals("PublisherName には null または空白を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("PublisherName が最大長を超える場合 IllegalArgumentException がスローされること")
    void testConstructorNegative3() {
        // 境界値分析の観点から、PublisherName が最大長を超える場合も IllegalArgumentException がスローされることを検証します。
        var longString = "a".repeat(PublisherName.MAX_LENGTH + 1);
        var exception = assertThrows(IllegalArgumentException.class, () -> 
            new PublisherName(longString)
        );

        assertEquals("PublisherName は最大 " + PublisherName.MAX_LENGTH + " 文字まで指定できます。", exception.getMessage());
    }

    @Test
    @DisplayName("PublisherName クラスのコンストラクタが正常に動作すること")
    void testConstructorPositive() {
        var publisherName = new PublisherName("出版社の名前");
        assertEquals("出版社の名前", publisherName.value());
    }

    @Test
    @DisplayName("PublisherName が最大長の文字列の場合も正常に動作すること")
    void testConstructorBoundary() {
        // 境界値分析の観点から、PublisherName が最大長の文字列の場合も正常に動作することを検証します。
        var longString = "a".repeat(PublisherName.MAX_LENGTH);
        var publisherName = new PublisherName(longString);
        assertEquals(longString, publisherName.value());
    }
}
