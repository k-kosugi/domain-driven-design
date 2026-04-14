package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Objects;

/**
 * AuthorName クラスは、名前を表す値オブジェクトです。
 * このクラスは、名前の値を保持し、必要に応じて名前の属性や振る舞いを定義することができます。
 * 
 * @param value 名前の値
 * 
 * @author Kenta Kosugi
 */
public record AuthorName(String value) {

    public static final int MAX_LENGTH = 255;

    /**
     * AuthorName クラスのコンストラクタです。
     * 
     * @param value 名前の値
     */
    public AuthorName(String value) {
        // AuthorName が null または空白であってはならないことを検証します。
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("AuthorName には null または空白を指定できません。");
        }

        // AuthorName の長さが最大長を超えていないことを検証します。
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("AuthorName は最大 " + MAX_LENGTH + " 文字まで指定できます。");
        }

        this.value = value;
    }

}