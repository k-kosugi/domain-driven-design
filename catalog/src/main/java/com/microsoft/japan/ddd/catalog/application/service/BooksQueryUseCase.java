package com.microsoft.japan.ddd.catalog.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microsoft.japan.ddd.catalog.application.dto.BookDTO;
import com.microsoft.japan.ddd.catalog.application.mapper.BookMapper;
import com.microsoft.japan.ddd.catalog.application.port.out.BookRepository;
import com.microsoft.japan.ddd.catalog.application.port.out.EmbeddingPort;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;

import jakarta.validation.constraints.NotEmpty;

/**
 * BooksQueryUseCase クラスは、Book ドメインモデルを ISBN13 を使用して取得するためのロジックを提供します。
 * 
 * @author Kenta Kosugi
 */
@Service
public class BooksQueryUseCase {

    private final BookRepository bookRepository;
    private final EmbeddingPort embeddingPort;

    /**
     * BooksQueryUseCase クラスのコンストラクタです。
     * 
     * @param bookRepository BookRepository のインスタンス
     * @param embeddingPort EmbeddingPort のインスタンス
     */
    public BooksQueryUseCase(BookRepository bookRepository, EmbeddingPort embeddingPort) {
        this.bookRepository = bookRepository;
        this.embeddingPort = embeddingPort;
    }

    /**
     * ISBN13 を使用して Book ドメインモデルを取得するメソッドです。
     * 
     * @param isbn13 ISBN13
     * @return BookDTO Book ドメインモデルに対応する BookDTO (見つからない場合は Optional.empty())
     */
    public Optional<BookDTO> findByIsbn13(@NotEmpty String isbn13) {
        // ISBN13 を使用して Book ドメインモデルを取得する
        var book = this.bookRepository.findByIsbn13(ISBN13.of(isbn13)).orElse(null);

        // Book ドメインモデルを BookDTO に変換して返す
        return book == null ? Optional.empty() : Optional.of(BookMapper.toDTO(book));
    }

    /**
     * クエリに類似する本を検索するメソッドです。
     * 
     * @param query クエリ
     * @return List<BookDTO> クエリに類似する本のリスト
     */
    public List<BookDTO> findSimilarBooks(@NotEmpty String query) {
        // クエリをベクトル化
        var embeddedQuery = this.embeddingPort.getEmbedding(query);

        // ベクトル化されたクエリを使用して類似する本を検索
        var books = this.bookRepository.findSimilar(embeddedQuery);
        return books.stream()
                .map(BookMapper::toDTO)
                .toList();
    }
    
}
