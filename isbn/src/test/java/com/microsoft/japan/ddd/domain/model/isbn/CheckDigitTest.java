package com.microsoft.japan.ddd.domain.model.isbn;

import org.junit.jupiter.api.Test;

import com.microsoft.japan.ddd.catalog.domain.model.isbn.CheckDigit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public class CheckDigitTest {
    @Test
    @DisplayName("CheckDigit の値が 0 から 9 の範囲内でなければならないことを検証するテスト")
    void testCheckDigitRange() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new CheckDigit(-1));
        assertEquals("CheckDigit は 0 から 9 の範囲でなければなりません。", exception.getMessage());

        
        exception = assertThrows(IllegalArgumentException.class, () -> new CheckDigit(11));
        assertEquals("CheckDigit は 0 から 9 の範囲でなければなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("Equals メソッドのテスト")
    void testEquals() {
        var checkDigit1 = new CheckDigit(0);
        var checkDigit2 = new CheckDigit(0);
        var checkDigit3 = new CheckDigit(1);

        assertAll(
                () -> assertEquals(checkDigit1, checkDigit2),
                () -> assertNotEquals(checkDigit1, checkDigit3)
        );
    }

    @Test
    @DisplayName("HashCode メソッドのテスト")
    void testHashCode() {
        var checkDigit1 = new CheckDigit(0);
        var checkDigit2 = new CheckDigit(0);
        var checkDigit3 = new CheckDigit(1);

        assertAll(
                () -> assertEquals(checkDigit1.hashCode(), checkDigit2.hashCode()),
                () -> assertNotEquals(checkDigit1.hashCode(), checkDigit3.hashCode())
        );
    }

    @Test
    @DisplayName("Length メソッドのテスト")
    void testLength() {
        var checkDigit = new CheckDigit(0);
        assertEquals(1, checkDigit.length());
    }

    @Test
    @DisplayName("toString メソッドのテスト")
    void testToString() {
        var checkDigit = new CheckDigit(0);
        assertEquals("CheckDigit[value=0]", checkDigit.toString());
    }

    @Test
    @DisplayName("Value メソッドのテスト")
    void testValue() {
        var checkDigit = new CheckDigit(0);
        assertEquals(0, checkDigit.value());

        checkDigit = new CheckDigit(9);
        assertEquals(9, checkDigit.value());
    }
}
