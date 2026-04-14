package com.microsoft.japan.ddd.catalog.domain.model.isbn;

import java.util.Objects;

/**
 * Publicationクラスは、出版物を表す値オブジェクトです。
 * このクラスは、Publication の値を保持し、必要に応じて Publication の検証やフォーマットの処理を行うことができます。
 * 
 * @param value 出版物コードの値
 * 
 * @author Kenta Kosugi
 */
public record Publication(String value) {

    public static final int MAX_LENGTH = 6;

    /**
     * Publication クラスのコンストラクタです。
     * 
     * @param value Publication コードの値
     */
    public Publication {
        // Publication が空であってはならないことを検証します。
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Publication は空であってはなりません。");
        }

        // Publication が指定された長さでなければならないことを検証します。
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Publication には " + MAX_LENGTH + " 文字より大きい値を設定できません。");
        }

        // Publication が数字でなければならないことを検証します。
        try {
            var valueAsInteger = Integer.parseInt(value);

            // Publication が負の数であってはならないことを検証します。
            if (valueAsInteger < 0) {
                throw new IllegalArgumentException("Publication は負の数であってはなりません。");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Publication は数字でなければなりません。");
        }
    }

    /**
     * Publication コードをバイト配列に変換します。
     * 
     * @return Publication コードをバイト配列に変換したもの
     */
    public char[] toCharArray() {
        return this.value.toCharArray();
    }

    /**
     * Publication コードの値を取得します。
     * 
     * @return Publication コードの値
     */
    public int length() {
        return this.value.length();
    }

}
