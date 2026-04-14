package com.microsoft.japan.ddd.catalog.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PublisherTest {

    @Test
    @DisplayName("publisherId が null の場合 IllegalArgumentException がスローされること")
    void testConstructorNegative1() {
        var exception = assertThrows(IllegalArgumentException.class, () -> 
            new Publisher(null, new PublisherName("出版社の名前"))
        );

        assertEquals("PublisherId は null であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("publisherName が null の場合 IllegalArgumentException がスローされること")
    void testConstructorNegative2() {
        var exception = assertThrows(IllegalArgumentException.class, () -> 
            new Publisher(new PublisherId("publisher-id1"), null)
        );

        assertEquals("出版社の名前は null であってはなりません。", exception.getMessage());
    }

    @Test
    @DisplayName("Publisher クラスのコンストラクタが正常に動作すること")
    void testConstructorPositive() {
        var publisherId = new PublisherId("publisher-id1");
        var publisherName = new PublisherName("出版社の名前");
        var publisher = new Publisher(publisherId, publisherName);

        assertEquals(publisherId, publisher.publisherId());
        assertEquals(publisherName, publisher.name());
    }

    @Test
    @DisplayName("Publisher クラスの equals メソッドが正常に動作すること")
    void testEquals1() {
        var publisherId = new PublisherId("publisher-id1");
        var publisherName = new PublisherName("出版社の名前");
        var publisher = new Publisher(publisherId, publisherName);

        assertTrue(publisher.equals(publisher));
    }

    @Test
    @DisplayName("Publisher クラスの equals メソッドが null と比較した場合に false を返すこと")
    void testEquals2() {
        var publisherId = new PublisherId("publisher-id1");
        var publisherName = new PublisherName("出版社の名前");
        var publisher = new Publisher(publisherId, publisherName);

        assertFalse(publisher.equals(null));
    }

    @Test
    @DisplayName("Publisher クラスの equals メソッドが異なる型のオブジェクトと比較した場合に false を返すこと")
    void testEquals3() {
        var publisherId = new PublisherId("publisher-id1");
        var publisherName = new PublisherName("出版社の名前");
        var publisher = new Publisher(publisherId, publisherName);

        assertFalse(publisher.equals(new Object()));
    }

    @Test
    @DisplayName("Publisher クラスの equals メソッドが異なる publisherId を持つ Publisher オブジェクトと比較した場合に false を返すこと")
    void testEquals4() {
        var publisherId1 = new PublisherId("publisher-id1");
        var publisherName1 = new PublisherName("出版社の名前");
        var publisher1 = new Publisher(publisherId1, publisherName1);

        var publisherId2 = new PublisherId("publisher-id2");
        var publisherName2 = new PublisherName("出版社の名前");
        var publisher2 = new Publisher(publisherId2, publisherName2);

        assertFalse(publisher1.equals(publisher2));
    }

    @Test
    @DisplayName("Publisher クラスの hashCode メソッドが正常に動作すること")
    void testHashCode() {
        var publisherId = new PublisherId("publisher-id1");
        var publisherName = new PublisherName("出版社の名前");
        var publisher = new Publisher(publisherId, publisherName);

        assertEquals(publisher.hashCode(), publisher.hashCode());
    }
}
