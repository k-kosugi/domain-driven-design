package com.microsoft.japan.ddd.catalog.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import com.microsoft.japan.ddd.catalog.adapter.out.persistence.postgresql.BookJpaRepository;
import com.microsoft.japan.ddd.catalog.application.port.out.BookRepository;
import com.microsoft.japan.ddd.catalog.domain.model.Book;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;

import jakarta.validation.constraints.NotNull;

/**
 * BookRepositoryImpl クラスは、BookRepository インターフェースの実装クラスです。
 * このクラスは、Book ドメインモデルをデータベースに保存し、ISBN13 を使用して Book ドメインモデルを取得するためのロジックを提供します。
 * 
 * @author Kenta Kosugi
 */
@Repository
@Validated
public class BookRepositoryImpl implements BookRepository {

    private final BookJpaRepository bookJpaRepository;

    /**
     * BookRepositoryImpl クラスのコンストラクタです。
     * 
     * @param bookJpaRepository BookJpaRepository のインスタンス
     * @param bookEntityTranslator BookEntityTranslator のインスタンス
     * @param embeddingGateway EmbeddingGateway のインスタンス
     */
    public BookRepositoryImpl(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    /**
     * ISBN13 を使用して Book ドメインモデルを取得するメソッドです。
     * 
     * @param isbn13 ISBN13
     * @return Book ISBN13 に対応する Book ドメインモデル (見つからない場合は null)
     */
    @Override
    public Optional<Book> findByIsbn13(@NotNull ISBN13 isbn13) {
        // isbn13 を使用して BookEntity をデータベースから取得し、Book ドメインモデルに変換して返す
        return this.bookJpaRepository.findByIsbn13(isbn13.toString())
                .map(BookMapper::toDomain);
    }

    @Override
    public void save(@NotNull Book book, @NotNull float[] embedding) {
        // Book ドメインモデルを BookEntity に変換し、データベースに保存する
        var bookEntity = BookMapper.toEntity(book);

        bookEntity.setEmbedding(embedding);

        // データベースに保存
        this.bookJpaRepository.save(bookEntity);
    }

    @Override
    public List<Book> findSimilar(@NotNull float[] queryText) {
        // クエリテキストに類似する BookEntity のリストをデータベースから取得し、Book ドメインモデルのリストに変換して返す
        return this.bookJpaRepository.findSimilar(queryText, 10).stream()
                .map(BookMapper::toDomain)
                .toList();
    }

}
