package com.microsoft.japan.ddd.domain.model.isbn;

import org.junit.jupiter.api.Test;

import com.microsoft.japan.ddd.catalog.domain.model.isbn.PrefixSymbol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;

public class PrefixSymbolTest {

    @Test
    @DisplayName("PrefixSymbol コードが null の場合に例外がスローされることを検証するテスト")
    void testConstructorNegative() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new PrefixSymbol(null));
        assertEquals("GS1 コードは null であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("PrefixSymbol コードが有効な場合に正しくインスタンスが生成されることを検証するテスト")
    void testConstructor() {
        var prefixSymbol1 = new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978);
        assertEquals("ISBN978", prefixSymbol1.getValue());

        var prefixSymbol2 = new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN979);
        assertEquals("ISBN979", prefixSymbol2.getValue());
    }

    @Test
    @DisplayName("PrefixSymbol コードの数値部分が正しく取得できることを検証するテスト")
    void testGetNumValue() {
        var prefixSymbol = new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978);
        assertEquals("978", prefixSymbol.getNumValue());

        prefixSymbol = new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN979);
        assertEquals("979", prefixSymbol.getNumValue());
    }   

    @Test
    @DisplayName("PrefixSymbol コードの文字列部分が正しく取得できることを検証するテスト")
    void testGetValue() {
        var prefixSymbol = new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN979);
        assertEquals("ISBN979", prefixSymbol.getValue());
    }

    @Test
    void testLengthWithPrefix() {
        var gs1 = new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978);
        assertEquals(7, gs1.lengthWithPrefix());
    }

    @Test
    void testOfNegative() {
        var exception = assertThrows(IllegalArgumentException.class, () -> PrefixSymbol.of(null));
        assertEquals("PrefixSymbol コードは空であってはなりません。", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> PrefixSymbol.of(""));
        assertEquals("PrefixSymbol コードは空であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("PrefixSymbol コードが 'ISBN978' or 'ISBN979' でない場合に例外がスローされることを検証するテスト")
    void testOfNegative2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> PrefixSymbol.of("123"));
        assertEquals(
                "PrefixSymbol コードは ISBN を含む必要があります。",
                exception.getMessage());
    }

    @Test
    @DisplayName("PrefixSymbol コードが 'ISBN978' or 'ISBN979' でない場合に例外がスローされることを検証するテスト")
    void testOfNegative3() {
        var exception = assertThrows(IllegalArgumentException.class, () -> PrefixSymbol.of("ISBN123"));
        assertEquals(
                "PrefixSymbol コードは ISBN978 または ISBN979 でなければなりません。",
                exception.getMessage());
    }

    @Test
    @DisplayName("PrefixSymbol コードが 'ISBN978' or 'ISBN979' である場合に正しくインスタンスが生成されることを検証するテスト")
    void testOfPositive() {
        var prefixSymbol = PrefixSymbol.of("ISBN978");
        assertEquals("ISBN978", prefixSymbol.getValue());

        prefixSymbol = PrefixSymbol.of("ISBN979");
        assertEquals("ISBN979", prefixSymbol.getValue());
    }

}
