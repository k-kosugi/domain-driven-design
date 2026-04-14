package com.microsoft.japan.ddd.catalog.domain.model.isbn;

/**
 * CheckDigitクラスは、ISBNコードのチェックディジットを表す値オブジェクトです。
 * このクラスは、チェックディジットの値を保持し、必要に応じてチェックディジットの検証やフォーマットの処理を行うことができます。
 * 
 * @param value チェックディジットの値
 * 
 * @author Kenta Kosugi
 */
public record CheckDigit(int value) {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 9;

    /**
     * CheckDigit クラスのコンストラクタです。
     * 
     * @param value チェックディジットの値
     */
    public CheckDigit {
        // CheckDigit が指定された範囲内でなければならないことを検証します。
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException("CheckDigit は " + MIN_VALUE + " から " + MAX_VALUE + " の範囲でなければなりません。");
        }
    }

    /**
     * CheckDigit の値を取得します。
     * 
     * @return CheckDigit の値
     */
    public int length() {
        return String.valueOf(this.value).length();
    }
}
