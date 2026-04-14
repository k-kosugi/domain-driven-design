package com.microsoft.japan.ddd.catalog.domain.model.isbn;

import java.util.Objects;

/**
 * RegistrationGroupクラスは、登録グループコードを表す値オブジェクトです。
 * このクラスは、登録グループコードの値を保持し、必要に応じて登録グループコードの検証やフォーマットの処理を行うことができます。
 * 
 * @param value 登録グループコードの値
 * 
 * @author Kenta Kosugi
 */
public record RegistrationGroup(String value) {

    /**
     * RegistrationGroup コードの最大長
     */
    public static final int MAX_LENGTH = 5;

    /**
     * RegistrationGroup クラスのコンストラクタです。
     * 
     * @param value 登録グループコードの値
     */
    public RegistrationGroup {
        // RegistrationGroup が空であってはならないことを検証します。
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("RegistrationGroup は空であってはなりません。");
        }

        // RegistrationGroup が指定された長さでなければならないことを検証します。
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("RegistrationGroup には " + MAX_LENGTH + " 文字以上を設定できません");
        }

        // RegistrationGroup が数字でなければならないことを検証します。
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("RegistrationGroup は数字でなければなりません。");
        }
    }

    /**
     * RegistrationGroup コードの値を取得します。
     * 
     * @return RegistrationGroup コードの値
     */
    public char[] toCharArray() {
        return this.value.toCharArray();
    }

    /**
     * RegistrationGroup コードの長さを取得します。
     * 
     * @return RegistrationGroup コードの長さ
     */
    public int length() {
        return this.value.length();
    }
    
}
