# .github/instructions/testing.instructions.md
---
applyTo: "**/test/**/*.java"
---
* テストフレームワークは JUnit 5 を使用すること
* アサーションは AssertJ を使用すること
* テストメソッド名は　@DisplayName アノテーションを使用して日本語で記述すること (例: `ISBN13が正しく生成される`)
* 値オブジェクトのテストでは等価性・不変性・バリデーションを網羅すること
* テストは Arrange-Act-Assert パターンに従うこと
* モックには Mockito を使用すること