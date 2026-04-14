package com.microsoft.japan.ddd.catalog.domain.model.isbn;

/**
 * Separatorクラスは、ISBN コードのセパレーターを表す列挙型です。
 * このクラスは、ISBN コードのセパレーターとして使用される文字列を定義し、必要に応じてセパレーターの値を取得することができます。
 * 
 * @author Kenta Kosugi
 */
public enum Separator {
    
    /**
     * ハイフンセパレーター
     */
    HYPHEN("-"), 
    
    /**
     * スペースセパレーター
     */
    SPACE(" ");

    private final String value;

    Separator(final String value) {
        this.value = value;
    }

    /**
     * セパレーターの値を取得します。
     * 
     * @return セパレーターの値
     */
    public String getValue() {
        return this.value;
    }
}
