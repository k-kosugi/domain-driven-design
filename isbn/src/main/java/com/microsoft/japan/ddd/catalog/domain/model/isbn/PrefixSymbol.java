package com.microsoft.japan.ddd.catalog.domain.model.isbn;

import java.util.Objects;

/**
 * PrefixSymbol クラスは、PrefixSymbol コードを表す値オブジェクトです。
 * このクラスは、PrefixSymbol コードの値を保持し、必要に応じて PrefixSymbol コードの検証やフォーマットの処理を行うことができます。
 * 
 * @param value PrefixSymbolコードの値
 * 
 * @author Kenta Kosugi
 */
public record PrefixSymbol(PrefixSymbolNumber value) {

    public static final String PREFIX = "ISBN";

    /**
     * PrefixSymbol クラスのコンストラクタです。
     * 
     * @param value PrefixSymbol コードの値
     */
    public PrefixSymbol {
        // PrefixSymbol コードが null であってはならないことを検証します。
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("GS1 コードは null であってはなりません。");
        }
    }

    /**
     * PrefixSymbol コードを生成するファクトリメソッドです。
     * 
     * @param value PrefixSymbol コードの数値部分の値
     * @return PrefixSymbol コードのインスタンス
     */
    public static final PrefixSymbol of(String value) {
        // PrefixSymbol コードが空であってはならないことを検証します。
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("PrefixSymbol コードは空であってはなりません。");
        }

        // PrefixSymbol コードが PREFIX を含む必要があることを検証します。
        var splitedValue = value.split(PREFIX);
        if (splitedValue.length != 2) {
            throw new IllegalArgumentException("PrefixSymbol コードは " + PREFIX + " を含む必要があります。");
        }

        // PrefixSymbol コードが "ISBN978" または "ISBN979" でなければならないことを検証します。
        if (!splitedValue[1].equals(PrefixSymbolNumber.ISBN978.getValue()) &&
                !splitedValue[1].equals(PrefixSymbolNumber.ISBN979.getValue())) {
            throw new IllegalArgumentException(
                    "PrefixSymbol コードは " + PrefixSymbolNumber.ISBN978.toString() + " または "
                            + PrefixSymbolNumber.ISBN979.toString() + " でなければなりません。");
        }

        return new PrefixSymbol(PrefixSymbolNumber.valueOf(PREFIX + splitedValue[1]));
    }

    /**
     * PrefixSymbol コードの値を取得します。
     * 
     * @return PrefixSymbol コードの値
     */
    public final String getValue() {
        return this.value.toString();
    }

    /**
     * PrefixSymbol コードの数値部分の値を取得します。
     * 
     * @return PrefixSymbol コードの数値部分の値
     */
    public final String getNumValue() {
        return this.value.getValue();
    }

    /**
     * PrefixSymbol コードの長さを取得します。
     * 
     * @return PrefixSymbol コードの長さ
     */
    public final int length() {
        return this.value.getValue().length();
    }

    /**
     * PREFIX を含んだ PrefixSymbol コードの長さを取得します。
     * 
     * @return PREFIX を含んだ PrefixSymbol コードの長さ
     */
    public final int lengthWithPrefix() {
        return PREFIX.length() + this.value.getValue().length();
    }

    /**
     * PrefixSymbolNumber コード。
     * この列挙型は、ISBN コードの PrefixSymbol コードとして使用される "978" と "979" を定義しています。
     * 
     * @author Kenta Kosugi
     */
    public enum PrefixSymbolNumber {
        /**
         * ISBN978 コード
         */
        ISBN978("978"),

        /**
         * ISBN979 コード
         */
        ISBN979("979");

        private final String value;

        /**
         * PrefixSymbolNumber のコンストラクタです。
         * 
         * @param value PrefixSymbolNumber コードの値
         */
        PrefixSymbolNumber(final String value) {
            this.value = value;
        }

        /**
         * PrefixSymbolNumber コードの値を取得します。
         * 
         * @return PrefixSymbolNumber コードの値
         */
        public String getValue() {
            return this.value;
        }
    }
}
