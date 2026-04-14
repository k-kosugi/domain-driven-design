DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS publisher;

CREATE TABLE publisher (
    id    VARCHAR(36)  NOT NULL,
    name  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book (
    isbn13        VARCHAR(21)  NOT NULL,
    title         VARCHAR(255) NOT NULL,
    contents      TEXT,
    publisher_id  VARCHAR(36),
    embedding     VECTOR(1536),
    PRIMARY KEY (isbn13),
    FOREIGN KEY (publisher_id) REFERENCES publisher(id)
);

CREATE TABLE author (
    id          VARCHAR(36)  NOT NULL,
    isbn13      VARCHAR(21)  NOT NULL,
    first_name  VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name   VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (isbn13) REFERENCES book(isbn13)
);