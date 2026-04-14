package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Objects;

/**
 * PublisherName クラスは、出版社の名前を表す値オブジェクトです。
 * このクラスは、出版社の名前を保持し、必要に応じて出版社の名前に関する検証やフォーマットの処理を行うことができます。
 * 
 * @param value 出版社の名前
 * 
 * @author Kenta Kosugi
 */
public record PublisherName(String value) {

    public static final int MAX_LENGTH = 255;

    /**
     * PublisherName クラスのコンストラクタです。
     * 
     * @param value 出版社の名前
     */
    public PublisherName(String value) {
        // PublisherName が null または空白であってはならないことを検証します。
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("PublisherName には null または空白を指定できません。");
        }

        // PublisherName の長さが最大長を超えていないことを検証します。
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("PublisherName は最大 " + MAX_LENGTH + " 文字まで指定できます。");
        }
        
        this.value = value;
    }

}
