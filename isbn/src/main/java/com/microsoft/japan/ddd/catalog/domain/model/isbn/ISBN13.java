package com.microsoft.japan.ddd.catalog.domain.model.isbn;

import java.util.Objects;

/**
 * ISBN13 クラスは、書籍の ISBN を表す値オブジェクトです。
 * このクラスは、ISBN13 の値を保持し、必要に応じて ISBN13 の検証やフォーマットの処理を行うことができます。
 * 
 * @param prefixSymbol      PrefixSymbol
 * @param registrationGroup 登録グループコード
 * @param registrant        出版者コード
 * @param publication       出版コード
 * @param checkDigit        チェックデジット
 * 
 * @author Kenta Kosugi
 */
public record ISBN13(PrefixSymbol prefixSymbol, RegistrationGroup registrationGroup, Registrant registrant,
        Publication publication,
        CheckDigit checkDigit) {

    /**
     * ISBN13 のコンテンツ数
     */
    private static final int CONTENT_COUNT = 5;

    /**
     * ISBN13 の長さ
     */
    public static final int LENGTH = 13;

    /**
     * ISBN13 の長さ（セパレーターを含む）
     */
    public static final int LENGTH_WITH_PREFIX_AND_SEPARATOR = 4 + LENGTH + CONTENT_COUNT - 1;

    /**
     * ISBN13 クラスのコンストラクタです。
     * 
     * @param prefixSymbol      GS1コード
     * @param registrationGroup 登録グループコード
     * @param registrant        出版者コード
     * @param publication       出版コード
     * @param checkDigit        チェックデジット
     * 
     * @throws IllegalArgumentException ISBN13 の各コンポーネントが null である場合、または ISBN13
     *                                  の長さが不正な場合、または ISBN13
     *                                  のチェックデジットが不正な場合にスローされます。
     */
    public ISBN13 {
        // ISBN13 の各コンポーネントが null であってはならないことを検証します。
        if (Objects.isNull(prefixSymbol)) {
            throw new IllegalArgumentException("PrefixSymbol は null であってはなりません。");
        }

        // ISBN13 の各コンポーネントが null であってはならないことを検証します。
        if (Objects.isNull(registrationGroup)) {
            throw new IllegalArgumentException("RegistrationGroup は null であってはなりません。");
        }

        // ISBN13 の各コンポーネントが null であってはならないことを検証します。
        if (Objects.isNull(registrant)) {
            throw new IllegalArgumentException("Registrant は null であってはなりません。");
        }

        // ISBN13 の各コンポーネントが null であってはならないことを検証します。
        if (Objects.isNull(publication)) {
            throw new IllegalArgumentException("Publication は null であってはなりません。");
        }

        // ISBN13 の各コンポーネントが null であってはならないことを検証します。
        if (Objects.isNull(checkDigit)) {
            throw new IllegalArgumentException("CheckDigit は null であってはなりません。");
        }

        // 長さが13であることを確認します
        if (prefixSymbol.length() + registrationGroup.length() + registrant.length() + publication.length()
                + checkDigit.length() != LENGTH) {
            throw new IllegalArgumentException("ISBN13 の長さが不正です。");
        }

        // チェックデジットを計算します。
        var checkDigitCalculated = checkDigit(prefixSymbol, registrationGroup, registrant, publication);
        if (!checkDigit.equals(checkDigitCalculated)) {
            throw new IllegalArgumentException("ISBN13 のチェックデジットが不正です。");
        }
    }

    /**
     * ISBN13 の文字列から ISBN13 オブジェクトを生成します。
     * 
     * @param value ISBN13 の文字列
     * @return ISBN13 オブジェクト
     * 
     * @throws IllegalArgumentException ISBN13 の文字列が null である場合、または ISBN13
     *                                  の文字列の要素数が不正な場合、または ISBN13 の文字列の長さが不正な場合、または
     *                                  ISBN13 の文字列のチェックデジットが不正な場合にスローされます。
     */
    public static final ISBN13 of(final String value) {
        // セパレーターで分割
        String[] isbnSplitedByHyphen = value.split(Separator.HYPHEN.getValue());
        String[] isbnSplitedBySpace = value.split(Separator.SPACE.getValue());

        var isHyphenCountvalid = isbnSplitedByHyphen.length == CONTENT_COUNT;
        var isSpaceCountvalid = isbnSplitedBySpace.length == CONTENT_COUNT;

        // セパレーターのどちらも正しい要素数でなければ、例外をスローします。
        if (!(isHyphenCountvalid || isSpaceCountvalid)) {
            throw new IllegalArgumentException("ISBN13 の要素数が不正です。");
        }

        // セパレーターのどちらかが正しい要素数であれば、そのセパレーターで分割された配列を使用します。
        String[] isbnSplited = isHyphenCountvalid ? isbnSplitedByHyphen : isbnSplitedBySpace;

        PrefixSymbol prefixSymbol = PrefixSymbol.of(isbnSplited[0]);
        var registrationGroup = new RegistrationGroup(isbnSplited[1]);
        var registrant = new Registrant(isbnSplited[2]);
        var publication = new Publication(isbnSplited[3]);
        var checkDigit = new CheckDigit(Integer.parseInt(isbnSplited[4]));

        // 長さが13であることを確認します。
        if (prefixSymbol.length() + registrationGroup.length() + registrant.length() + publication.length()
                + checkDigit.length() != LENGTH) {
            throw new IllegalArgumentException("ISBN13 の長さが不正です。");
        }

        // ISBN13 オブジェクトを生成して返します。
        return new ISBN13(prefixSymbol, registrationGroup, registrant, publication, checkDigit);
    }

    /**
     * ISBN13 のチェックデジットを計算して返します。
     * 
     * @param prefixSymbol      PrefixSymbol
     * @param registrationGroup 登録グループコード
     * @param registrant        出版者コード
     * @param publication       出版コード
     * 
     * @return ISBN13 のチェックデジット
     */
    public static CheckDigit checkDigit(PrefixSymbol prefixSymbol, RegistrationGroup registrationGroup,
            Registrant registrant,
            Publication publication) {
        char[] prefixSymbolChars = prefixSymbol.getNumValue().toCharArray();
        char[] registrationGroupChars = registrationGroup.toCharArray();
        char[] registrantChars = registrant.toCharArray();
        char[] publicationChars = publication.toCharArray();

        var tmp = new char[LENGTH - 1];
        System.arraycopy(prefixSymbolChars, 0, tmp, 0, prefixSymbolChars.length);
        System.arraycopy(registrationGroupChars, 0, tmp, prefixSymbolChars.length, registrationGroupChars.length);
        System.arraycopy(registrantChars, 0, tmp, prefixSymbolChars.length + registrationGroupChars.length,
                registrantChars.length);
        System.arraycopy(publicationChars, 0, tmp,
                prefixSymbolChars.length + registrationGroupChars.length + registrantChars.length,
                publicationChars.length);

        // チェックデジットの計算
        int sum = 0;
        for (int i = 0; i < tmp.length; i++) {
            int digit = Character.getNumericValue(tmp[i]);
            if (i % 2 == 0) {
                sum += digit;
            } else {
                sum += digit * 3;
            }
        }

        // チェックデジットは、10 から sum を 10 で割った余りを引いた値の 10 で割った余りです。
        return new CheckDigit((10 - (sum % 10)) % 10);
    }

    /**
     * ISBN13 オブジェクトを文字列に変換します。
     * 
     * @return ISBN13 オブジェクトを文字列に変換したもの
     */
    @Override
    public String toString() {
        return String.format("%s%s%s%s%s%s%s%s%s",
                prefixSymbol.getValue(),
                Separator.HYPHEN.getValue(),
                registrationGroup.value(),
                Separator.HYPHEN.getValue(),
                registrant.value(),
                Separator.HYPHEN.getValue(),
                publication.value(),
                Separator.HYPHEN.getValue(),
                checkDigit.value());
    }

    /**
     * ISBN13 オブジェクトが等しいかどうかを比較します。
     * 
     * @param o 比較するオブジェクト
     * @return ISBN13 オブジェクトが等しい場合は true、そうでない場合は false
     */
     @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ISBN13 isbn13 = (ISBN13) o;
        return this.toString().equals(isbn13.toString());
    }
}