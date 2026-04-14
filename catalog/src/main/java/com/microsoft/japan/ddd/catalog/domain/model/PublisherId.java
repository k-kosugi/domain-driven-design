package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Objects;

/**
 * PublisherId クラスは、出版社の識別子を表す値オブジェクトです。
 * このクラスは、出版社の識別子を保持し、必要に応じて識別子に関する検証やフォーマットの処理を行うことができます。
 * 
 * @param value 出版社の識別子
 * 
 * @author Kenta Kosugi
 */
public record PublisherId(String value) {

    public static final int MAX_LENGTH = 36;

    /**
     * PublisherId のコンストラクタです。
     * 
     * @param value 出版社の識別子
     * @throws IllegalArgumentException value が null または空白の場合、または最大長を超える場合にスローされます。
     */
    public PublisherId(String value) {
        // PublisherId が null または空白であってはならないことを検証します。
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("PublisherId には null または空白を指定できません。");
        }

        // PublisherId の長さが最大長を超えていないことを検証します。
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("PublisherId は最大 " + MAX_LENGTH + " 文字まで指定できます。");
        }

        this.value = value;
    }

}
