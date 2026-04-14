package com.microsoft.japan.ddd.catalog.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PublisherIdTest {

    @Test
    @DisplayName("PublisherId のコンストラクタに null を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative1() {
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new PublisherId(null));
        assertEquals("PublisherId には null または空白を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("PublisherId のコンストラクタに空白を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative2() {
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new PublisherId("   "));
        assertEquals("PublisherId には null または空白を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("PublisherId のコンストラクタに最大長を超える文字列を渡すと IllegalArgumentException がスローされること")  
    void testConstructorNegative3() {
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new PublisherId("a".repeat(PublisherId.MAX_LENGTH + 1)));
        assertEquals("PublisherId は最大 " + PublisherId.MAX_LENGTH + " 文字まで指定できます。", exception.getMessage());
    }

    @Test
    @DisplayName("PublisherId のコンストラクタに有効な文字列を渡すと PublisherId オブジェクトが正常に作成されること")
    void testConstructorPositive() {
        String validId = "valid-publisher-id";
        PublisherId publisherId = new PublisherId(validId);
        assertEquals(validId, publisherId.value());
    }

    @Test
    @DisplayName("PublisherId のコンストラクタに最大長の文字列を渡すと PublisherId オブジェクトが正常に作成されること") 
    void testConstructorPositive2() {
        String validId = "a".repeat(PublisherId.MAX_LENGTH);
        PublisherId publisherId = new PublisherId(validId);
        assertEquals(validId, publisherId.value());
    }

}
