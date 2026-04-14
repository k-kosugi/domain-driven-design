package com.microsoft.japan.ddd.catalog.adapter.out.persistence.postgresql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microsoft.japan.ddd.catalog.adapter.out.persistence.postgresql.entity.BookEntity;

/**
 * BookJpaRepository インターフェースは、BookEntity をデータベースに保存し、ISBN13 を使用して BookEntity を取得するためのロジックを提供します。
 * 
 * @author Kenta Kosugi
 */
public interface BookJpaRepository extends JpaRepository<BookEntity, String> {

    /**
     * ISBN13 を使用して BookEntity を取得するメソッドです。
     * 
     * @param isbn13 ISBN13
     * @return BookEntity ISBN13 に対応する BookEntity (見つからない場合は null)
     */
    Optional<BookEntity> findByIsbn13(String isbn13);

    /**
     * クエリテキストに類似する BookEntity のリストを取得するメソッドです。
     * 
     * @param queryVector クエリテキストのベクトル表現
     * @param topK        取得する類似書籍の上位 K 件数
     * @return List<BookEntity> クエリテキストに類似する BookEntity のリスト
     */
    @Query(value = """
            SELECT * FROM book
            ORDER BY embedding <=> CAST(:queryVector AS vector)
            LIMIT :topK
            """, nativeQuery = true)
    List<BookEntity> findSimilar(@Param("queryVector") float[] queryVector,
            @Param("topK") int topK);
}
