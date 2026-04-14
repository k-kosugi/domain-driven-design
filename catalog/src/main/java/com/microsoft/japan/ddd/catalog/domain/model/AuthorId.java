package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Objects;

/**
 * AuthorId クラスは、著者の識別子を表すクラスです。
 * 著者の識別子は、著者を一意に識別するための値であり、通常はデータベースの主キーや UUID などが使用されます。
 * 
 * @author Kenta Kosugi
 */
public record AuthorId(String value) {

    public static final int MAX_LENGTH = 36;

    /**
     * AuthorId クラスのコンストラクタです。
     * 
     * @param value 著者の識別子の値
     */
    public AuthorId {
        // AuthorId が null または空白であってはならないことを検証します。
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("AuthorId には null または空白を指定できません。");
        }

        // AuthorId の長さが最大長を超えていないことを検証します。
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("AuthorId は最大 " + MAX_LENGTH + " 文字まで指定できます。");
        }
    }

}
