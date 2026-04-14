package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Objects;

import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;

/**
 * Bookクラスは、書籍を表すエンティティです。
 * このクラスは、書籍の ISBN コードを保持し、必要に応じて書籍の属性や振る舞いを定義することができます。
 * 
 * @param isbn13     書籍の ISBN コード
 * @param title      書籍のタイトル
 * @param authorList 書籍の著者リスト
 * @param publisher  書籍の出版社
 * 
 * @author Kenta Kosugi
 */
public record Book(ISBN13 isbn13, Title title, Contents contents, AuthorList authorList, Publisher publisher) {

    /**
     * Book クラスのコンストラクタです。
     * 
     * @param isbn13 ISBN13 オブジェクト
     */
    public Book {
        // ISBN13 が null であってはならないことを検証します。
        if (Objects.isNull(isbn13)) {
            throw new IllegalArgumentException("ISBN13 には null を指定できません。");
        }

        // Title が null であってはならないことを検証します。
        if (Objects.isNull(title)) {
            throw new IllegalArgumentException("Title には null を指定できません。");
        }

        // Contents が null であってはならないことを検証します。
        if(Objects.isNull(contents)) {
            throw new IllegalArgumentException("Contents には null を指定できません。");
        }

        // AuthorList が null であってはならないことを検証します。
        if (Objects.isNull(authorList)) {
            throw new IllegalArgumentException("AuthorList には null を指定できません。");
        }

        // Publisher が null であってはならないことを検証します。
        if (Objects.isNull(publisher)) {
            throw new IllegalArgumentException("Publisher には null を指定できません。");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;
        return Objects.equals(isbn13, book.isbn13);
    }

    @Override
    public int hashCode() { 
        return Objects.hash(isbn13);
    }

}
