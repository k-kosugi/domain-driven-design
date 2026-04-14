# isbn

ISBN-13 の値オブジェクトライブラリ。Domain-Driven Design (DDD) における ISBN-13 のドメインモデルを Java レコードで実装したモジュールです。  
`catalog` など他のモジュールから依存ライブラリとして利用されます。

## 概要

ISBN-13 は `978` または `979` で始まる 13 桁の書籍識別コードです。  
本モジュールはその構造をコンポーネントごとに値オブジェクトとして分解し、生成時にすべてのバリデーションとチェックデジット検証を行います。

```
ISBN13 = PrefixSymbol - RegistrationGroup - Registrant - Publication - CheckDigit
例)       978          -        4          -   7741      -   8411      -     0
```

## 技術スタック

| カテゴリ | 技術 |
|---|---|
| 言語 | Java 25 |
| ビルドツール | Maven |
| 依存ライブラリ | なし（標準ライブラリのみ） |

## モジュール座標

```xml
<dependency>
    <groupId>com.microsoft.japan.ddd</groupId>
    <artifactId>isbn</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## クラス構成

| クラス | 種別 | 説明 |
|---|---|---|
| `ISBN13` | record (値オブジェクト) | ISBN-13 全体。5つのコンポーネントで構成される |
| `PrefixSymbol` | record (値オブジェクト) | GS1 プレフィックス (`978` または `979`) |
| `RegistrationGroup` | record (値オブジェクト) | 登録グループコード（言語・国コード）。最大 5 桁 |
| `Registrant` | record (値オブジェクト) | 出版者コード。最大 7 桁 |
| `Publication` | record (値オブジェクト) | 出版コード。最大 6 桁 |
| `CheckDigit` | record (値オブジェクト) | チェックデジット。0〜9 の整数 |
| `Separator` | enum | セパレーター文字 (`-` または ` `) |

## 使い方

### 文字列から生成 (`ISBN13.of`)

ハイフン区切りとスペース区切りの両方に対応しています。

```java
// ハイフン区切り
ISBN13 isbn = ISBN13.of("978-4-7741-8411-0");

// スペース区切り
ISBN13 isbn = ISBN13.of("978 4 7741 8411 0");
```

### コンポーネントから直接生成

```java
ISBN13 isbn = new ISBN13(
    new PrefixSymbol(PrefixSymbol.PrefixSymbolNumber.ISBN978),
    new RegistrationGroup("4"),
    new Registrant("7741"),
    new Publication("8411"),
    new CheckDigit(0)
);
```

### チェックデジットの計算

```java
CheckDigit cd = ISBN13.checkDigit(prefixSymbol, registrationGroup, registrant, publication);
```

## バリデーション仕様

| コンポーネント | 制約 |
|---|---|
| `PrefixSymbol` | `978` または `979` のみ |
| `RegistrationGroup` | 1〜5 桁の数字、空不可 |
| `Registrant` | 1〜7 桁の数字、負数不可、空不可 |
| `Publication` | 1〜6 桁の数字、負数不可、空不可 |
| `CheckDigit` | 0〜9 の整数 |
| `ISBN13` 全体 | 合計 13 桁、チェックデジット一致 |

生成時に上記を満たさない場合は `IllegalArgumentException` がスローされます。

## ビルド・テスト

```bash
# ビルド & ローカルリポジトリにインストール
mvn clean install

# テストのみ実行
mvn test

# Javadoc 生成
mvn javadoc:jar
```

## テスト

| テストクラス | 対象 |
|---|---|
| `ISBN13Test` | `ISBN13` の生成・バリデーション・`of` ファクトリ |
| `CheckDigitTest` | `CheckDigit` のバリデーション |
| `PrefixSymbolTest` | `PrefixSymbol` のバリデーション・ファクトリ |
| `RegistrationGroupTest` | `RegistrationGroup` のバリデーション |
| `RegistrantTest` | `Registrant` のバリデーション |
| `PublicationTest` | `Publication` のバリデーション |
