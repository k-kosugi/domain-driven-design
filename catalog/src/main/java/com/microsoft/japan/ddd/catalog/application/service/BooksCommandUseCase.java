package com.microsoft.japan.ddd.catalog.application.service;

import org.springframework.stereotype.Service;

import com.microsoft.japan.ddd.catalog.application.dto.BookDTO;
import com.microsoft.japan.ddd.catalog.application.mapper.BookMapper;
import com.microsoft.japan.ddd.catalog.application.port.in.SaveBookUseCase;
import com.microsoft.japan.ddd.catalog.application.port.out.BookRepository;
import com.microsoft.japan.ddd.catalog.application.port.out.EmbeddingPort;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;

import jakarta.validation.constraints.NotNull;

/**
 * BooksCommandUseCase クラスは、Book ドメインモデルを保存するためのロジックを提供します。
 * 
 * @author Kenta Kosugi
 */
@Service
public class BooksCommandUseCase implements SaveBookUseCase {

    private final BookRepository bookRepository;
    private final EmbeddingPort embeddingPort;

    /**
     * BooksCommandUseCase クラスのコンストラクタです。
     * 
     * @param bookRepository BookRepository のインスタンス
     * @param embeddingPort EmbeddingPort のインスタンス
     */
    public BooksCommandUseCase(BookRepository bookRepository, EmbeddingPort embeddingPort) {
        this.bookRepository = bookRepository;
        this.embeddingPort = embeddingPort;
    }

    /**
     * Book ドメインモデルを保存するメソッドです。
     * 
     * @param bookDTO BookDTO 
     */
    @Override
    public void saveBook(@NotNull BookDTO bookDTO) {
        // ISBN13 の重複チェック
        var isbn13 = ISBN13.of(bookDTO.isbn13());

        // ISBN13 が既に存在する場合は例外をスロー
        var book = this.bookRepository.findByIsbn13(isbn13).orElse(null);
        if (book != null) {
            throw new IllegalArgumentException("ISBN13 が重複しています。");
        }

        // BookDTO を Book ドメインモデルに変換する
        book = BookMapper.toDomain(bookDTO);

        // ベクトルデータの取得
        var embedding = this.embeddingPort.getEmbedding(bookDTO.contents());

        // 保存
        this.bookRepository.save(book, embedding);
    }

}
