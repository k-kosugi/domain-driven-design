package com.microsoft.japan.ddd.catalog.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthorTest {
    
    @Test
    @DisplayName("Author クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証するテスト")
    void testConstructorNegative1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Author(null, new AuthorName("Kenta"), new AuthorName("Kosugi")));
        assertEquals("AuthorId は null であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("Author クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証するテスト")
    void testConstructorNegative2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Author(new AuthorId("author-1"), null, new AuthorName("Kosugi")));
        assertEquals("FirstName は null であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("Author クラスのコンストラクタに null を渡すと IllegalArgumentException がスローされることを検証するテスト")
    void testConstructorNegative3() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Author(new AuthorId("author-1"), new AuthorName("Kenta"), null));
        assertEquals("LastName は null であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("Author クラスのコンストラクタに有効な値を渡すと正しくオブジェクトが生成されることを検証するテスト")
    void testConstructorPositive1() {
        // middleName を指定して Author オブジェクトを生成します。
        var author1 = new Author(new AuthorId("author-1"), new AuthorName("Kenta"), new AuthorName("Kosugi"));

        // middleName を null にしても正しくオブジェクトが生成されることを検証します。
        var author2 = new Author(new AuthorId("author-1"), new AuthorName("Kenta"), null, new AuthorName("Kosugi"));

        assertEquals(new AuthorId("author-1"), author1.authorId());
        assertEquals(new AuthorName("Kenta"), author1.firstName());
        assertEquals(new AuthorName("Kosugi"), author1.lastName());

        assertTrue(author1.equals(author2));
    }

    @Test
    @DisplayName("Author クラスの equals メソッドに同じオブジェクトを渡すと true を返すことを検証するテスト")
    void testEquals1() {
        var author = new Author(new AuthorId("author-1"), new AuthorName("Kenta"), new AuthorName("Kosugi"));

        assertEquals(author, author);
    }

    @Test
    @DisplayName("Author クラスの equals メソッドに null を渡すと false を返すことを検証するテスト")
    void testEquals2() {
        var author = new Author(new AuthorId("author-1"), new AuthorName("Kenta"), new AuthorName("Kosugi"));

        assertFalse(author.equals(null));
    }

    @Test
    @DisplayName("Author クラスの equals メソッドに異なるクラスのオブジェクトを渡すと false を返すことを検証するテスト")
    void testEquals3() {
        var author = new Author(new AuthorId("author-1"), new AuthorName("Kenta"), new AuthorName("Kosugi"));
    
        assertFalse(author.equals(new Object()));
    }

}
