package com.microsoft.japan.ddd.catalog.adapter.out.persistence;

import java.util.Arrays;
import java.util.Objects;

import com.microsoft.japan.ddd.catalog.adapter.out.persistence.postgresql.entity.AuthorEntity;
import com.microsoft.japan.ddd.catalog.adapter.out.persistence.postgresql.entity.BookEntity;
import com.microsoft.japan.ddd.catalog.adapter.out.persistence.postgresql.entity.PublisherEntity;
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
 * BookMapper クラスは、Book ドメインモデルと BookEntity の相互変換を行うクラスです。
 * このクラスは、Book ドメインモデルと BookEntity の間のマッピングロジックを実装します。
 * 
 * @author Kenta Kosugi
 */
public class BookMapper {

    /**
     * Book ドメインモデルを BookEntity に変換するメソッドです。
     * 
     * @param book Book ドメインモデル
     * @return BookEntity Book ドメインモデルに対応する BookEntity
     */
    public static BookEntity toEntity(Book book) {
        // null チェック
        if (Objects.isNull(book)) {
            return null;
        }

        // Book ドメインモデルを BookEntity に変換して返す
        return new BookEntity(
                book.isbn13().toString(),
                book.title().value(),
                book.contents().value(),
                Arrays.stream(book.authorList().authors())
                        .map(BookMapper::toEntity)
                        .toList(),
                BookMapper.toEntity(book.publisher()));
    }

    /**
     * BookEntity を Book ドメインモデルに変換するメソッドです。
     * 
     * @param bookEntity BookEntity
     * @return Book BookEntity に対応する Book ドメインモデル
     */
    public static AuthorEntity toEntity(Author author) {
        // null チェック
        if (Objects.isNull(author)) {
            return null;
        }

        // Author ドメインモデルを AuthorEntity に変換して返す
        return new AuthorEntity(
                author.authorId().value(),
                author.firstName().value(),
                author.middleName() != null ? author.middleName().value() : null,
                author.lastName().value());
    }

    /**
     * Publisher ドメインモデルを PublisherEntity に変換するメソッドです。
     * 
     * @param publisher Publisher ドメインモデル
     * @return PublisherEntity Publisher ドメインモデルに対応する PublisherEntity
     */
    public static PublisherEntity toEntity(Publisher publisher) {
        // null チェック
        if (Objects.isNull(publisher)) {
            return null;
        }

        // Publisher ドメインモデルを PublisherEntity に変換して返す
        return new PublisherEntity(
                publisher.publisherId().value(),
                publisher.name().value());
    }

    /**
     * BookEntity を Book ドメインモデルに変換するメソッドです。
     * 
     * @param bookEntity BookEntity
     * @return Book BookEntity に対応する Book ドメインモデル
     */
    public static Book toDomain(BookEntity bookEntity) {
        // null チェック
        if (Objects.isNull(bookEntity)) {
            return null;
        }

        // BookEntity を Book ドメインモデルに変換して返す
        return new Book(
                ISBN13.of(bookEntity.getIsbn13()),
                new Title(bookEntity.getTitle()),
                new Contents(bookEntity.getContents()),
                new AuthorList(bookEntity.getAuthors().stream()
                        .map(BookMapper::toDomain)
                        .toArray(Author[]::new)),
                BookMapper.toDomain(bookEntity.getPublisher()));
    }

    /**
     * AuthorEntity を Author ドメインモデルに変換するメソッドです。
     * 
     * @param authorEntity AuthorEntity
     * @return Author AuthorEntity に対応する Author ドメインモデル
     */
    public static Author toDomain(AuthorEntity authorEntity) {
        // null チェック
        if (Objects.isNull(authorEntity)) {
            return null;
        }

        // AuthorEntity を Author ドメインモデルに変換して返す
        return new Author(
                new AuthorId(authorEntity.getId()),
                new AuthorName(authorEntity.getFirstName()),
                authorEntity.getMiddleName() == null ? null: new AuthorName(authorEntity.getMiddleName()),
                new AuthorName(authorEntity.getLastName()));
    }

    /**
     * PublisherEntity を Publisher ドメインモデルに変換するメソッドです。
     * 
     * @param publisherEntity PublisherEntity
     * @return Publisher PublisherEntity に対応する Publisher ドメインモデル
     */
    public static Publisher toDomain(PublisherEntity publisherEntity) {
        // null チェック
        if (Objects.isNull(publisherEntity)) {
            return null;
        }

        // PublisherEntity を Publisher ドメインモデルに変換して返す
        return new Publisher(
                new PublisherId(publisherEntity.getId()),
                new PublisherName(publisherEntity.getName()));
    }

}
