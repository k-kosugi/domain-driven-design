package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Objects;

/**
 * 著者を表すクラス。
 * 著者は、名前（姓、名、ミドルネーム）を持ちます。
 * 
 * @author Kenta Kosugi
 */
public record Author(AuthorId authorId, AuthorName firstName, AuthorName middleName, AuthorName lastName) {

    /**
     * Author クラスのコンストラクタです。
     * 
     * @param authorId   著者の ID
     * @param firstName  著者の名
     * @param middleName 著者のミドルネーム（任意）
     * @param lastName   著者の姓
     */
    public Author {
        // 引数の検証を行います。null であってはならない引数が null であれば、IllegalArgumentException をスローします。
        if (Objects.isNull(authorId)) {
            throw new IllegalArgumentException("AuthorId は null であってはなりません。");
        }

        // firstName と lastName は null であってはなりません。middleName は null であっても構いません。
        if (Objects.isNull(firstName)) {
            throw new IllegalArgumentException("FirstName は null であってはなりません。");
        }

        if (Objects.isNull(lastName)) {
            throw new IllegalArgumentException("LastName は null であってはなりません。");
        }
    }

    /**
     * ミドルネームを持たない著者のためのコンストラクタです。
     * 
     * @param authorId  著者の ID
     * @param firstName 著者の名
     * @param lastName  著者の姓
     */
    public Author(AuthorId authorId, AuthorName firstName, AuthorName lastName) {
        this(authorId, firstName, null, lastName);
    }

    /**
     * 著者のフルネームを返すメソッドです。ミドルネームがある場合は、名、ミドルネーム、姓の順でフルネームを構築します。ミドルネームがない場合は、名と姓の順でフルネームを構築します。
     * 
     * @return 著者のフルネームを表す文字列
     */
    public String getFullName() {
        // ミドルネームが null であれば、名と姓の順でフルネームを構築します。ミドルネームがある場合は、名、ミドルネーム、姓の順でフルネームを構築します。
        if (Objects.isNull(middleName)) {
            return String.format("%s %s", firstName.value(), lastName.value());
        } else {
            return String.format("%s %s %s", firstName.value(), middleName.value(), lastName.value());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }
        
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId);
    }

}
