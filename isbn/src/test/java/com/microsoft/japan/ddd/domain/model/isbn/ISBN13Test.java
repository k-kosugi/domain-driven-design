package com.microsoft.japan.ddd.domain.model.isbn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.japan.ddd.catalog.domain.model.isbn.CheckDigit;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.PrefixSymbol;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.Publication;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.Registrant;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.RegistrationGroup;

public class ISBN13Test {

    @Test
    @DisplayName("コンストラクタの引数の各要素が null")
    void testConstructorNegative001() {
        // GS1 が null の場合のテスト
        var exception = assertThrows(IllegalArgumentException.class, () -> new ISBN13(
                null,
                new RegistrationGroup("4"),
                new Registrant("7741"),
                new Publication("8411"),
                new CheckDigit(0)));

        assertEquals("PrefixSymbol は null であってはなりません。", exception.getMessage());

        // RegistrationGroup が null の場合のテスト
        exception = assertThrows(IllegalArgumentException.class, () -> new ISBN13(
                new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978),
                null,
                new Registrant("7741"),
                new Publication("8411"),
                new CheckDigit(0)));

        assertEquals("RegistrationGroup は null であってはなりません。", exception.getMessage());

        // Registrant が null の場合のテスト
        exception = assertThrows(IllegalArgumentException.class, () -> new ISBN13(
                new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978),
                new RegistrationGroup("4"),
                null,
                new Publication("8411"),
                new CheckDigit(0)));

        assertEquals("Registrant は null であってはなりません。", exception.getMessage());

        // Publication が null の場合のテスト
        exception = assertThrows(IllegalArgumentException.class, () -> new ISBN13(
                new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978),
                new RegistrationGroup("4"),
                new Registrant("7741"),
                null,
                new CheckDigit(0)));

        assertEquals("Publication は null であってはなりません。", exception.getMessage());

        // CheckDigit が null の場合のテスト
        exception = assertThrows(IllegalArgumentException.class, () -> new ISBN13(
                new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978),
                new RegistrationGroup("4"),
                new Registrant("7741"),
                new Publication("8411"),
                null));

        assertEquals("CheckDigit は null であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("ISBN13 の長さが不正")
    void testConstructorNegative002() {
        // 異常系のテスト
        var exception = assertThrows(IllegalArgumentException.class, () -> new ISBN13(
                new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978),
                new RegistrationGroup("4"),
                new Registrant("1234567"),
                new Publication("12345"),
                new CheckDigit(0)));

        assertEquals("ISBN13 の長さが不正です。", exception.getMessage());
    }

    @Test
    @DisplayName("チェックデジットが不正")
    void testConstructorNegative003() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new ISBN13(
                new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978),
                new RegistrationGroup("4"),
                new Registrant("7741"),
                new Publication("8411"),
                new CheckDigit(0)));

        assertEquals("ISBN13 のチェックデジットが不正です。", exception.getMessage());
    }

    @Test
    @DisplayName("正常な ISBN 13 の場合")
    void testConstructorPositive() {
        var isbn13 = new ISBN13(
                new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978),
                new RegistrationGroup("4"),
                new Registrant("7741"),
                new Publication("8411"),
                new CheckDigit(1));

        assertEquals("ISBN978-4-7741-8411-1", isbn13.toString());
    }

    @Test
    @DisplayName("正常な ISBN 13 の場合(ISBN979)")
    void testConstructorPositive2() {
        var isbn13 = new ISBN13(
                new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN979),
                new RegistrationGroup("4"),
                new Registrant("7741"),
                new Publication("8411"),
                new CheckDigit(0));

        assertEquals("ISBN979-4-7741-8411-0", isbn13.toString());
    }

    @Test
    void testCheckDigit() {
        var checkDigit = ISBN13.checkDigit(
                new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978),
                new RegistrationGroup("4"),
                new Registrant("7741"),
                new Publication("8411"));

        assertEquals(1, checkDigit.value());
    }

    @Test
    void testCheckDigit2() {

    }

    @Test
    @DisplayName("ISBN13 の要素数が不正な場合")
    void testOfNegative1() {
        // 要素数が不正な場合1(ハイフン)
        var exception = assertThrows(IllegalArgumentException.class, () -> ISBN13.of("978-0-0-47741-8411-1"));
        assertEquals("ISBN13 の要素数が不正です。", exception.getMessage());

        // 要素数が不正な場合2(スペース)
        exception = assertThrows(IllegalArgumentException.class, () -> ISBN13.of("978 0 0 47741 8411 1"));
        assertEquals("ISBN13 の要素数が不正です。", exception.getMessage());
    }

    @Test
    @DisplayName("ISBN13 の長さが不正な場合")
    void testOfNegative2() {
        // 長さが13以上の場合
        var exception = assertThrows(IllegalArgumentException.class, () -> ISBN13.of("ISBN978-4-7741-841123-1"));
        assertEquals("ISBN13 の長さが不正です。", exception.getMessage());

        // 長さが13に満たない場合
        exception = assertThrows(IllegalArgumentException.class, () -> ISBN13.of("ISBN978-4-7741-841-1"));
        assertEquals("ISBN13 の長さが不正です。", exception.getMessage());
    }

    @Test
    @DisplayName("正常な ISBN13 の場合")
    void testOfPositive() {
        // ハイフンの場合
        var isbn13 = ISBN13.of("ISBN978-4-7741-8411-1");
        assertEquals("ISBN978-4-7741-8411-1", isbn13.toString());

        // スペースの場合
        isbn13 = ISBN13.of("ISBN978 4 7741 8411 1");
        assertEquals("ISBN978-4-7741-8411-1", isbn13.toString());
    }

    @Test
    @DisplayName("同一のオブジェクトではないが、内容が同じ ISBN13 同士の比較")
    void testOfEqual1() {
        var isbn13_1 = ISBN13.of("ISBN978-4-7741-8411-1");
        var isbn13_2 = ISBN13.of("ISBN978-4-7741-8411-1");

        assertEquals(isbn13_1, isbn13_2);
    }

    @Test
    @DisplayName("同一のオブジェクト同士の比較")
    void testOfEqual2() {
        var isbn13 = ISBN13.of("ISBN978-4-7741-8411-1");

        assertEquals(isbn13, isbn13);
    }

    @Test
    @DisplayName("引数が null の場合 false を返す")
    void testOfEqual3() {
        var isbn13 = ISBN13.of("ISBN978-4-7741-8411-1");

        assertFalse(isbn13.equals(null));
    }

    @Test
    @DisplayName("ISBN13 の中身が全く異なる場合")
    void testOfEqual4() {

        var isbn13_1 = ISBN13.of("ISBN978-4-7741-8411-1");
        var isbn13_2 = ISBN13.of("ISBN978 4 7741 8410 4");

        assertFalse(isbn13_1.equals(isbn13_2));
    }

    @Test
    @DisplayName("全く違うオブジェクト同士の比較")
    void testOfEqual5() {
        var isbn13_1 = ISBN13.of("ISBN978-4-7741-8411-1");
        var isbn13_2 = new Object();

        assertFalse(isbn13_1.equals(isbn13_2));
    }

}
