# catalog-ui

catalog の `BookRestAPIController` に対して `BookDTO` を登録する React + Vite の UI です。

## セットアップ

```bash
cd catalog-ui
npm install
npm run dev
```

Vite の開発サーバーでは `/api` を `http://localhost:8080` にプロキシします。
バックエンドの接続先を変更したい場合は、環境変数 `VITE_CATALOG_API_BASE_URL` を設定してください。

## 画面でできること

- `BookDTO` の ISBN13、タイトル、内容を入力
- `AuthorDTO` を複数行で追加、削除して登録
- `PublisherDTO` を 1 件入力して登録
- 送信前の JSON ペイロードをプレビュー
