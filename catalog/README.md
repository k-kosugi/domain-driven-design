# Catalog Service

書籍カタログサービス - ドメイン駆動設計(DDD)とヘキサゴナルアーキテクチャによる書籍管理 REST API

## 技術スタック

| 技術 | バージョン |
|------|-----------|
| Java | 25 |
| Spring Boot | 4.0.3 |
| PostgreSQL | 17 (pgvector) |
| Spring AI | 2.0.0-M4 |
| Azure OpenAI | text-embedding-3-large |
| springdoc-openapi | 2.8.6 |
| TestContainers | 1.19.7 |

## アーキテクチャ

ヘキサゴナルアーキテクチャ(Ports & Adapters)を採用しています。

```
com.microsoft.japan.ddd.catalog
├── domain/                          # ドメイン層
│   ├── model/                       #   エンティティ・値オブジェクト
│   │   ├── Book                     #     集約ルート (ISBN13, Title, Contents, AuthorList, Publisher)
│   │   ├── Author                   #     著者エンティティ (AuthorId, AuthorName)
│   │   ├── Publisher                #     出版社エンティティ (PublisherId, PublisherName)
│   │   ├── AuthorList               #     著者コレクション (最大150名)
│   │   ├── Title                    #     書籍タイトル (最大255文字)
│   │   ├── Contents                 #     書籍内容 (最大100,000文字)
│   │   ├── AuthorId / AuthorName    #     著者ID・名前
│   │   └── PublisherId / PublisherName  # 出版社ID・名前
│   └── service/
│       └── BookDomainService        #   ドメインサービス (ISBN重複チェック)
│
├── application/                     # アプリケーション層
│   ├── port/
│   │   ├── in/SaveBookUseCase       #   入力ポート
│   │   └── out/                     #   出力ポート
│   │       ├── BookRepository       #     書籍リポジトリ
│   │       └── EmbeddingPort        #     ベクトル埋め込み
│   ├── service/
│   │   ├── BooksCommandUseCase      #   書籍登録ユースケース
│   │   └── BooksQueryUseCase        #   書籍検索ユースケース
│   ├── dto/                         #   BookDTO, AuthorDTO, PublisherDTO
│   └── mapper/BookMapper            #   DTO <-> ドメインモデル変換
│
└── adapter/                         # アダプター層
    ├── in/web/
    │   ├── BookRestAPIController     #   REST API コントローラー
    │   └── config/OpenAPIConfig      #   OpenAPI 設定
    └── out/
        ├── persistence/             #   永続化アダプター
        │   ├── BookRepositoryImpl   #     リポジトリ実装
        │   ├── BookMapper           #     エンティティ <-> ドメインモデル変換
        │   └── postgresql/          #     JPA エンティティ・リポジトリ
        └── ai/openapi/
            └── EmbeddingPortImpl    #   Azure OpenAI 埋め込みアダプター
```

## API エンドポイント

| メソッド | パス | 説明 |
|---------|------|-----|
| GET | `/books/isbn/{isbn13}` | ISBN13 で書籍を取得 |
| POST | `/books` | 新しい書籍を登録 |
| GET | `/books/similar?query={query}` | ベクトル類似検索で書籍を検索 |

### リクエスト例

```json
{
  "isbn13": "ISBN978-4-8144-0073-7",
  "title": "ドメイン駆動設計をはじめよう",
  "contents": "書籍の内容説明",
  "authors": [
    { "id": "author-1", "firstName": "Vlad", "lastName": "Khononov" },
    { "id": "author-2", "firstName": "了", "lastName": "増田" }
  ],
  "publisher": { "id": "8144", "name": "オライリー・ジャパン" }
}
```

## データベース

PostgreSQL 17 + pgvector 拡張を使用。ベクトル埋め込み(1536次元)による類似検索をサポートしています。

### テーブル構成

- **publisher** - 出版社 (`id`, `name`)
- **book** - 書籍 (`isbn13`, `title`, `contents`, `publisher_id`, `embedding`)
- **author** - 著者 (`id`, `isbn13`, `first_name`, `middle_name`, `last_name`)

## ローカル開発

### 前提条件

- Java 25
- Maven
- Docker (PostgreSQL + pgvector 用)

### データベースの起動

```bash
cd docker
docker build -t catalog-db .
docker run -d -p 5432:5432 catalog-db
```

### アプリケーションの起動

```bash
./mvnw spring-boot:run
```

### テストの実行

```bash
./mvnw test
```

TestContainers を使用して PostgreSQL コンテナを自動起動します。

## Docker でのビルド

```bash
# プロジェクトルートから
mvn clean package -DskipTests
docker build -t catalog-service -f catalog/Dockerfile .
```

## 初期データ

起動時に以下の書籍データが投入されます:

| ISBN | タイトル |
|------|---------|
| 978-4-8144-0073-7 | ドメイン駆動設計をはじめよう |
| 978-4-8144-0006-5 | ソフトウェアアーキテクチャ・ハードパーツ |
| 978-4-87311-565-8 | リーダブルコード |
| 978-4-87311-870-3 | データ指向アプリケーションデザイン |
| 978-4-87311-965-6 | Google のソフトウェアエンジニアリング |
