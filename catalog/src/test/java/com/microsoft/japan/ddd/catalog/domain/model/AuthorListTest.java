package com.microsoft.japan.ddd.catalog.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public class AuthorListTest {

    @Test
    @DisplayName("AuthorList のコンストラクタに null を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new AuthorList((Author[]) null));
        assertEquals("著者のリストは空であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("AuthorList のコンストラクタに空の配列を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new AuthorList());
        assertEquals("著者のリストは空であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("AuthorList のコンストラクタに最大人数を超える配列を渡すと IllegalArgumentException がスローされること")
    void testConstructorNegative3() {
        Author[] authors = new Author[AuthorList.MAX_AUTHORS + 1];
        for (int i = 0; i < authors.length; i++) {
            authors[i] = new Author(new AuthorId("author-id-" + i), new AuthorName("FirstName" + i),
                    new AuthorName("MiddleName" + i), new AuthorName("LastName" + i));
        }
        var exception = assertThrows(IllegalArgumentException.class, () -> new AuthorList(authors));

        assertEquals("著者の数は最大 " + AuthorList.MAX_AUTHORS + " 人までです。", exception.getMessage());
    }

    @Test
    @DisplayName("AuthorList のコンストラクタに最大人数の配列を渡すと AuthorList オブジェクトが正常に作成されること")
    void testConstructorPositive() {
        Author[] authors = new Author[AuthorList.MAX_AUTHORS];
        for (int i = 0; i < authors.length; i++) {
            authors[i] = new Author(new AuthorId("author-id-" + i), new AuthorName("FirstName" + i),
                    new AuthorName("MiddleName" + i), new AuthorName("LastName" + i));
        }
        AuthorList authorList = new AuthorList(authors);
        assertEquals(AuthorList.MAX_AUTHORS, authorList.numberOfAuthors());
    }

    @Test
    @DisplayName("AuthorList のコンストラクタに有効な配列を渡すと AuthorList オブジェクトが正常に作成されること")
    void testConstructorPositive2() {
        Author[] authors = new Author[3];
        for (int i = 0; i < authors.length; i++) {
            authors[i] = new Author(new AuthorId("author-id-" + i), new AuthorName("FirstName" + i),
                    new AuthorName("MiddleName" + i), new AuthorName("LastName" + i));
        }
        AuthorList authorList = new AuthorList(authors);
        assertEquals(3, authorList.numberOfAuthors());
    }

    @Test
    @DisplayName("同一のオブジェクトを比較すると equals メソッドは true を返すこと")
    void testEquals() {
        Author[] authors = new Author[3];
        for (int i = 0; i < authors.length; i++) {
            authors[i] = new Author(new AuthorId("author-id-" + i), new AuthorName("FirstName" + i),
                    new AuthorName("MiddleName" + i), new AuthorName("LastName" + i));
        }
        AuthorList authorList = new AuthorList(authors);

        assertTrue(authorList.equals(authorList));
    }

    @Test
    @DisplayName("null と比較すると equals メソッドは false を返すこと")
    void testEquals1() {
        Author[] authors = new Author[3];
        for (int i = 0; i < authors.length; i++) {
            authors[i] = new Author(new AuthorId("author-id-" + i), new AuthorName("FirstName" + i),
                    new AuthorName("MiddleName" + i), new AuthorName("LastName" + i));
        }
        AuthorList authorList = new AuthorList(authors);

        assertFalse(authorList.equals(null));
    }

    @Test
    @DisplayName("異なるオブジェクトと比較すると equals メソッドは false を返すこと")
    void testEquals2() {
        Author[] authors = new Author[3];
        for (int i = 0; i < authors.length; i++) {
            authors[i] = new Author(new AuthorId("author-id-" + i), new AuthorName("FirstName" + i),
                    new AuthorName("MiddleName" + i), new AuthorName("LastName" + i));
        }
        AuthorList authorList = new AuthorList(authors);

        assertFalse(authorList.equals(new Object()));
    }

    @Test
    @DisplayName("異なるオブジェクトと比較すると equals メソッドは false を返すこと")
    void testEquals3() {
        Author[] authors1 = new Author[3];
        Author[] authors2 = new Author[3];
        for (int i = 0; i < authors1.length; i++) {
            authors1[i] = new Author(new AuthorId("author-id-" + i), new AuthorName("FirstName" + i),
                    new AuthorName("MiddleName" + i), new AuthorName("LastName" + i));
            authors2[i] = new Author(new AuthorId("author-idx-" + i), new AuthorName("FirstName" + i),
                    new AuthorName("MiddleName" + i), new AuthorName("LastName" + i));
        }
        AuthorList authorList1 = new AuthorList(authors1);
        AuthorList authorList2 = new AuthorList(authors2);

        assertFalse(authorList1.equals(authorList2));
    }

    @Test
    @DisplayName("同一のオブジェクトのハッシュコードは等しいこと")  
    void testHashCode() {
        Author[] authors = new Author[3];
        for (int i = 0; i < authors.length; i++) {
            authors[i] = new Author(new AuthorId("author-id-" + i), new AuthorName("FirstName" + i),
                    new AuthorName("MiddleName" + i), new AuthorName("LastName" + i));
        }
        AuthorList authorList1 = new AuthorList(authors);
        AuthorList authorList2 = new AuthorList(authors);

        assertEquals(authorList1.hashCode(), authorList2.hashCode());
    }

}
