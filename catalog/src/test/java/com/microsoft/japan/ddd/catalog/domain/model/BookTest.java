package com.microsoft.japan.ddd.catalog.domain.model;

import org.junit.jupiter.api.Test;

import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public class BookTest {

    @Test
    @DisplayName("Book のコンストラクタの引数の isbn13 に null を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative1() {
        // Book クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証します。
        var exception = assertThrows(IllegalArgumentException.class, () -> new Book(null, null, null, null, null));
        assertEquals("ISBN13 には null を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("Book のコンストラクタの引数の title に null を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative2() {
        // Book クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証します。
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Book(ISBN13.of("ISBN978-4-7741-9713-5"), null, null, null, null));
        assertEquals("Title には null を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("Book のコンストラクタの引数の contents に null を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative3() {
        // Book クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証します。
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), null, null, null));
        assertEquals("Contents には null を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("Book のコンストラクタの引数の authorList に null を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative4() {
        // Book クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証します。
        var exception = assertThrows(IllegalArgumentException.class, () -> new Book(ISBN13.of("ISBN978-4-7741-9713-5"),
                new Title("ドメイン駆動設計"), new Contents("これは内容です。"), null, null));
        assertEquals("AuthorList には null を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("Book のコンストラクタの引数の publisher に null を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative5() {
        // Book クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証します。
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), new Contents("これは内容です。"),
                        new AuthorList(new Author(new AuthorId("author-0001"),
                                new AuthorName("Kenta"), new AuthorName("Kosugi"))),
                        null));
        assertEquals("Publisher には null を指定できません。", exception.getMessage());
    }

    @Test
    @DisplayName("Book のコンストラクタに有効な引数を渡すと、Book オブジェクトが正常に作成されること")
    void testConstructorPositive() {
        // Book クラスのコンストラクタに有効な引数を渡すと、Book オブジェクトが正常に作成されることを検証します。
        var book = new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), new Contents("これは内容です。"),
                new AuthorList(new Author(new AuthorId("author-0001"),
                        new AuthorName("Kenta"), new AuthorName("Kosugi"))),
                new Publisher(new PublisherId("publisher-0001"), new PublisherName("技術評論社")));

        assertEquals(ISBN13.of("ISBN978-4-7741-9713-5"), book.isbn13());
        assertEquals(new Title("ドメイン駆動設計"), book.title());
        assertEquals(new Contents("これは内容です。"), book.contents());
        assertEquals(new AuthorList(new Author(new AuthorId("author-0001"),
                new AuthorName("Kenta"), new AuthorName("Kosugi"))), book.authorList());
        assertEquals(new Publisher(new PublisherId("publisher-0001"), new PublisherName("技術評論社")),
                book.publisher());
    }

    @Test
    @DisplayName("Book クラスの equals メソッドに同じオブジェクトを渡すと true を返すこと")
    void testEquals1() {
        var book = new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), new Contents("これは内容です。"),
                new AuthorList(new Author(new AuthorId("author-0001"),
                        new AuthorName("Kenta"), new AuthorName("Kosugi"))),
                new Publisher(new PublisherId("publisher-0001"), new PublisherName("技術評論社")));

        assertTrue(book.equals(book));
    }

    @Test
    @DisplayName("Book クラスの equals メソッドに null を渡すと false を返すこと")
    void testEquals2() {
        var book = new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), new Contents("これは内容です。"),
                new AuthorList(new Author(new AuthorId("author-0001"),
                        new AuthorName("Kenta"), new AuthorName("Kosugi"))),
                new Publisher(new PublisherId("publisher-0001"), new PublisherName("技術評論社")));

        assertFalse(book.equals(null));
    }

    @Test
    @DisplayName("Book クラスの equals メソッドに異なるクラスのオブジェクトを渡すと false を返すこと")
    void testEquals3() {
        var book = new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), new Contents("これは内容です。"),
                new AuthorList(new Author(new AuthorId("author-0001"),
                        new AuthorName("Kenta"), new AuthorName("Kosugi"))),
                new Publisher(new PublisherId("publisher-0001"), new PublisherName("技術評論社")));

        assertFalse(book.equals(new Object()));
    }

    @Test
    @DisplayName("Book クラスの equals メソッドに同じ ISBN13 を持つ異なる Book オブジェクトを渡すと true を返すこと")
    void testEquals4() {
        var book1 = new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), new Contents("これは内容です。"),
                new AuthorList(new Author(new AuthorId("author-0001"),
                        new AuthorName("Kenta"), new AuthorName("Kosugi"))),
                new Publisher(new PublisherId("publisher-0001"), new PublisherName("技術評論社")));

        var book2 = new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), new Contents("これは内容です。"),
                new AuthorList(new Author(new AuthorId("author-0002"),
                        new AuthorName("Kenta"), new AuthorName("Kosugi"))),
                new Publisher(new PublisherId("publisher-0002"), new PublisherName("技術評論社")));

        assertTrue(book1.equals(book2));
    }

    @Test
    @DisplayName("Book クラスの hashCode メソッドに同じ ISBN13 を持つ異なる Book オブジェクトを渡すと同じハッシュコードを返すこと")
    void testHashCode() {
        var book1 = new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), new Contents("これは内容です。"),
                new AuthorList(new Author(new AuthorId("author-0001"),
                        new AuthorName("Kenta"), new AuthorName("Kosugi"))),
                new Publisher(new PublisherId("publisher-0001"), new PublisherName("技術評論社")));

        var book2 = new Book(ISBN13.of("ISBN978-4-7741-9713-5"), new Title("ドメイン駆動設計"), new Contents("これは内容です。"),
                new AuthorList(new Author(new AuthorId("author-0002"),
                        new AuthorName("Kenta"), new AuthorName("Kosugi"))),
                new Publisher(new PublisherId("publisher-0002"), new PublisherName("技術評論社")));

        assertEquals(book1.hashCode(), book2.hashCode());
    }

}
