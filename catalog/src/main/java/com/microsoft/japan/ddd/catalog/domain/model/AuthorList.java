package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * AuthorList クラスは、書籍の著者のリストを表す値オブジェクトです。
 * このクラスは、複数の著者を保持し、必要に応じて著者の検証やフォーマットの処理を行うことができます。
 * 
 * ひとつの物語を共同で執筆したフィクション作品としては、122人という記録があるため、150人を最大値として設定します。
 * 
 * @param authors 書籍の著者のリスト
 * 
 * @author Kenta Kosugi
 */
public record AuthorList(Author... authors) {

    public static final int MAX_AUTHORS = 150; // 著者の最大数を定義

    /**
     * AuthorList クラスのコンストラクタです。
     * 
     * @param authors 書籍の著者のリスト
     */
    public AuthorList {
        // 著者のリストが null または空であってはならないことを検証します。
        if (Objects.isNull(authors) || authors.length == 0) {
            throw new IllegalArgumentException("著者のリストは空であってはなりません。");
        }

        // 著者の数が最大数を超えていないことを検証します。
        if (authors.length > MAX_AUTHORS) {
            throw new IllegalArgumentException("著者の数は最大 " + MAX_AUTHORS + " 人までです。");
        }
    }

    /**
     * 著者の数を取得します。
     * 
     * @return 著者の数
     */
    public int numberOfAuthors() {
        return this.authors.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        AuthorList that = (AuthorList) o;
        return new HashSet<>(Arrays.asList(this.authors))
                .equals(new HashSet<>(Arrays.asList(that.authors)));
    }

    @Override
    public int hashCode() {
        // 順序に依存しないよう Set にしてからハッシュを計算
        return new HashSet<>(Arrays.asList(this.authors)).hashCode();
    }

}