package com.microsoft.japan.ddd.catalog.domain.service;

import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;

/**
 * BookDomainService クラスは、Book ドメインモデルに関連するドメインサービスを実装するクラスです。
 * このクラスは、BookRepository を使用して、Book ドメインモデルに関連するビジネスロジックを実装します。
 * 
 * @author Kenta Kosugi
 */
public class BookDomainService {

    /**
     * ISBN13 が重複しているかどうかを判定するメソッドです。
     * 
     * @param isbn13 ISBN13
     * @return boolean ISBN13 が重複している場合は true、そうでない場合は false
     */
    public boolean isDuplicateISBN13(ISBN13 isbn13) {


        return false;
    }

}
