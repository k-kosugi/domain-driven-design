package com.microsoft.japan.ddd.catalog.adapter.in.web;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.japan.ddd.catalog.application.dto.BookDTO;
import com.microsoft.japan.ddd.catalog.application.service.BooksCommandUseCase;
import com.microsoft.japan.ddd.catalog.application.service.BooksQueryUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * BookRestAPIController クラスは、書籍に関する REST API エンドポイントを提供します。
 * このクラスは、BooksQueryUseCase と BooksCommandUseCase を使用して、書籍の取得と保存のロジックを実装します。
 * 
 * @author Kenta Kosugi
 */
@RestController
@Validated
public class BookRestAPIController {

    private final BooksQueryUseCase booksQueryUseCase;
    private final BooksCommandUseCase booksCommandUseCase;

    /**
     * BookRestAPIController クラスのコンストラクタです。
     * 
     * @param booksQueryUseCase   BooksQueryUseCase のインスタンス
     * @param booksCommandUseCase BooksCommandUseCase のインスタンス
     */
    public BookRestAPIController(BooksQueryUseCase booksQueryUseCase, BooksCommandUseCase booksCommandUseCase) {
        this.booksQueryUseCase = booksQueryUseCase;
        this.booksCommandUseCase = booksCommandUseCase;
    }

    /**
     * ISBN13 を使用して Book ドメインモデルを取得するエンドポイントです。
     * 
     * @param isbn13 ISBN13
     * @return BookDTO Book ドメインモデルに対応する BookDTO (見つからない場合は null)
     */
    @Operation(summary = "ISBN13 で書籍を取得", description = "指定した ISBN13 に対応する書籍を返します")
    @GetMapping("/books/isbn/{isbn13}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "取得成功", content = @Content(schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "書籍が見つかりません", content = @Content)
    })
    public BookDTO findByIsbn13(@PathVariable @NotEmpty String isbn13) {
        return this.booksQueryUseCase.findByIsbn13(isbn13).orElse(null);
    }

    /**
     * Book ドメインモデルを保存するエンドポイントです。
     * 
     * @param bookDTO BookDTO
     */
    @Operation(summary = "書籍を登録", description = "新しい書籍をカタログに登録します")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "登録成功"),
            @ApiResponse(responseCode = "400", description = "リクエストが不正です", content = @Content)
    })
    @PostMapping("/books")
    public void saveBook(@RequestBody @NotNull BookDTO bookDTO) {
        this.booksCommandUseCase.saveBook(bookDTO);
    }

    /**
     * クエリに類似する本を検索するエンドポイントです。
     * 
     * @param query クエリ
     * @return List<BookDTO> クエリに類似する本のリスト
     */
    @Operation(summary = "類似書籍を検索", description = "クエリテキストに意味的に類似する書籍を返します")
    @ApiResponse(responseCode = "200", description = "検索成功", content = @Content(schema = @Schema(implementation = BookDTO.class)))
    @GetMapping("/books/similar")
    public List<BookDTO> findSimilarQuery(@RequestParam @NotEmpty String query) {
        return this.booksQueryUseCase.findSimilarBooks(query);
    }

}
