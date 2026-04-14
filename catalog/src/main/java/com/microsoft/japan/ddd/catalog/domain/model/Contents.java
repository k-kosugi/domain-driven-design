package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Objects;

/**
 * Contents クラスは、書籍の内容を表す値オブジェクトです。
 * このクラスは、書籍の内容の値を保持し、必要に応じて内容の検証やフォーマットの処理を行うことができます。
 * 
 * @param String value 書籍の内容
 * 
 * @author Kenta Kosugi
 */
public record Contents(String value) {

    public static final int MAX_LENGTH = 100000;

    /**
     * Contents クラスのコンストラクタです。
     * 
     * @param value 書籍の内容
     */
    public Contents {
        // Contents が null であってはならないことを検証します。
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Contents には null を指定できません。");
        }

        // Contents の長さが最大値を超えていないことを検証します。
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Contents の長さは " + MAX_LENGTH + " 文字以内である必要があります。");
        }
    }

}
