package com.microsoft.japan.ddd.catalog.domain.model;

import java.util.Objects;

/**
 * Title クラスは、書籍のタイトルを表す値オブジェクトです。
 * このクラスは、書籍のタイトルの値を保持し、必要に応じてタイトルの検証やフォーマットの処理を行うことができます。
 * 
 * @param value 書籍のタイトル
 * 
 * @author Kenta Kosugi
 */
public record Title(String value) {

    public static final int MAX_LENGTH = 255;

    /**
     * Title クラスのコンストラクタです。
     * 
     * @param value 書籍のタイトル
     */
    public Title {
        // タイトルが null であってはならないことを検証
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("タイトルは null もしくは空白であってはなりません。");
        }

        // タイトルが MAX_LENGTH 文字以内でなければならないことを検証
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("タイトルは " + MAX_LENGTH + " 文字以内でなければなりません。");
        }
    }
}
