package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Objects;

/**
 * Publisher クラスは、出版社を表す値オブジェクトです。
 * このクラスは、出版社の名前を保持し、必要に応じて出版社の属性や振る舞いを定義することができます。
 * 
 * @param name 出版社の名前
 * 
 * @author Kenta Kosugi
 */
public record Publisher(PublisherId publisherId, PublisherName name) {

    /**
     * Publisher クラスのコンストラクタです。
     * 
     * @param publisherId 出版社の ID
     * @param name        出版社の名前
     */
    public Publisher {
        // 出版社の名前が null であってはならないことを検証
        if (Objects.isNull(publisherId)) {
            throw new IllegalArgumentException("PublisherId は null であってはなりません。");
        }

        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("出版社の名前は null であってはなりません。");
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
        Publisher publisher = (Publisher) o;
        return Objects.equals(publisherId, publisher.publisherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherId);
    }

}
