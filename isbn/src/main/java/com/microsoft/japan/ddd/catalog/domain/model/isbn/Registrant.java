package com.microsoft.japan.ddd.catalog.domain.model.isbn;

import java.util.Objects;

/**
 * Registrantクラスは、出版者を表す値オブジェクトです。
 * このクラスは、出版者の値を保持し、必要に応じて出版者の検証やフォーマットの処理を行うことができます。
 * 
 * @param value 出版者コードの値
 * 
 * @author Kenta Kosugi
 */
public record Registrant(String value) {

    public static final int MAX_LENGTH = 7;
    
    /**
     * Registrant クラスのコンストラクタです。
     * 
     * @param value 出版者コードの値
     */
    public Registrant {
        // Registrant が空であってはならないことを検証します。
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Registrant は空であってはなりません。");
        }

        // Registrant が指定された長さでなければならないことを検証します。
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Registrant には " + MAX_LENGTH + " 文字以上を設定できません。");
        }

        // Registrant が数字でなければならないことを検証します。
        try {
            var valueAsInteger = Integer.parseInt(value);

            // Registrant が負の数であってはならないことを検証します。
            if(valueAsInteger < 0) {
                throw new IllegalArgumentException("Registrant は負の数であってはなりません。");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Registrant は数字でなければなりません。");
        }
    }

    /**
     * Registrant コードの値を取得します。
     * 
     * @return Registrant コードの値
     */
    public char[] toCharArray() {
        return this.value.toCharArray();
    }

    /**
     * Registrant コードの長さを取得します。
     * 
     * @return Registrant コードの長さ
     */
    public int length() {
        return this.value.length();
    }
    
}
