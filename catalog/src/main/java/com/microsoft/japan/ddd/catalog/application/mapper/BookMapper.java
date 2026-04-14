package com.microsoft.japan.ddd.catalog.application.mapper;

import java.util.Arrays;
import java.util.Objects;

import com.microsoft.japan.ddd.catalog.application.dto.AuthorDTO;
import com.microsoft.japan.ddd.catalog.application.dto.BookDTO;
import com.microsoft.japan.ddd.catalog.application.dto.PublisherDTO;
import com.microsoft.japan.ddd.catalog.domain.model.Author;
import com.microsoft.japan.ddd.catalog.domain.model.AuthorId;
import com.microsoft.japan.ddd.catalog.domain.model.AuthorList;
import com.microsoft.japan.ddd.catalog.domain.model.AuthorName;
import com.microsoft.japan.ddd.catalog.domain.model.Book;
import com.microsoft.japan.ddd.catalog.domain.model.Contents;
import com.microsoft.japan.ddd.catalog.domain.model.Publisher;
import com.microsoft.japan.ddd.catalog.domain.model.PublisherId;
import com.microsoft.japan.ddd.catalog.domain.model.PublisherName;
import com.microsoft.japan.ddd.catalog.domain.model.Title;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;

/**
 * BookMapper クラスは、Book ドメインモデルと BookDTO の相互変換を行うクラスです。
 * このクラスは、Book ドメインモデルと BookDTO の間のマッピングロジックを実装します。
 * 
 * @author Kenta Kosugi
 */
public class BookMapper {

    /**
     * Book ドメインモデルを BookDTO に変換するメソッドです。
     * 
     * @param book Book ドメインモデル
     * @return BookDTO Book ドメインモデルに対応する BookDTO
     */
    public static BookDTO toDTO(Book book) {
        // null チェック
        if(Objects.isNull(book)) {
            return null;
        }

        return new BookDTO(
                book.isbn13().toString(),
                book.title().value(),
                book.contents().value(),
                Arrays.stream(book.authorList().authors())
                        .map(BookMapper::toDTO)
                        .toList(),
                BookMapper.toDTO(book.publisher()),
                null);
    }

    /**
     * Author ドメインモデルを AuthorDTO に変換するメソッドです。
     * @param author Author ドメインモデル
     * @return AuthorDTO Author ドメインモデルに対応する AuthorDTO
     */
    public static AuthorDTO toDTO(Author author) {
        // null チェック
        if(Objects.isNull(author)) {
            return null;
        }

        return new AuthorDTO(
                author.authorId().value(),
                author.firstName().value(),
                author.middleName() != null ? author.middleName().value() : null,
                author.lastName().value());
    }

    /**
     * Publisher ドメインモデルを PublisherDTO に変換するメソッドです。
     * @param publisher Publisher ドメインモデル
     * @return PublisherDTO Publisher ドメインモデルに対応する PublisherDTO
     */
    public static PublisherDTO toDTO(Publisher publisher) {
        // null チェック
        if(Objects.isNull(publisher)) {
            return null;
        }

        return new PublisherDTO(
                publisher.publisherId().value(),
                publisher.name().value());
    }

    /**
     * BookDTO を Book ドメインモデルに変換するメソッドです。
     * 
     * @param bookDTO BookDTO
     * @return Book bookDTO に対応する Book ドメインモデル
     */
    public static Book toDomain(BookDTO bookDTO) {
        // null チェック
        if(Objects.isNull(bookDTO)) {
            return null;
        }

        return new Book(
                ISBN13.of(bookDTO.isbn13()),
                new Title(bookDTO.title()),
                new Contents(bookDTO.contents()),
                new AuthorList(
                        bookDTO.authors().stream().map(BookMapper::toDomain)
                                .toArray(Author[]::new)),
                BookMapper.toDomain(bookDTO.publisher()));
    }

    /**
     * AuthorDTO を Author ドメインモデルに変換するメソッドです。
     * @param authorDTO AuthorDTO
     * @return Author authorDTO に対応する Author ドメインモデル
     */
    public static Author toDomain(AuthorDTO authorDTO) {
        // null チェック
        if(Objects.isNull(authorDTO)) {
            return null;
        }

        return new Author(
                new AuthorId(authorDTO.id()),
                new AuthorName(authorDTO.firstName()),
                authorDTO.middleName() != null ? new AuthorName(authorDTO.middleName()) : null,
                new AuthorName(authorDTO.lastName()));
    }

    /**
     * PublisherDTO を Publisher ドメインモデルに変換するメソッドです。
     * @param publisherDTO PublisherDTO
     * @return Publisher publisherDTO に対応する Publisher ドメインモデル
     */
    public static Publisher toDomain(PublisherDTO publisherDTO) {
        // null チェック
        if(Objects.isNull(publisherDTO)) {
            return null;
        }

        return new Publisher(
                new PublisherId(publisherDTO.id()),
                new PublisherName(publisherDTO.name()));
    }
}
