
INSERT INTO publisher (id, name) VALUES
('8144', 'オライリー・ジャパン');
INSERT INTO publisher (id, name) VALUES ('8731', 'オライリー・ジャパン（旧）');

INSERT INTO book (isbn13, title, contents, publisher_id) VALUES
('ISBN978-4-8144-0073-7', 'ドメイン駆動設計をはじめよう', 'ドメイン駆動設計はエリック・エヴァンスにより提唱されたソフトウェア設計の手法です。対象とする事業活動（ドメイン）とその課題の観点から、より良いソフトウェアを構築するために関係者が協力する方法を提供します。本書は4部構成になっており、第Ⅰ部「設計の基本方針」では、ソフトウェアの設計方針を大きな視点から決めるための考え方とやり方を取り上げます。第Ⅱ部「実装方法の選択」ではソースコードに焦点を合わせ、業務ロジックをどう実装するかの選択肢を学びます。第Ⅲ部「ドメイン駆動設計の実践」では、ソフトウェア開発の現場にドメイン駆動設計を実践的に取り入れるための方法を紹介します。第Ⅳ部「他の方法論や設計技法との関係」では、ドメイン駆動設計とそれ以外の方法論や設計技法との関係を検討します。最新の技術トレンドを取り入れながら、ドメイン駆動設計の基本概念と実践方法をわかりやすく解説します。', '8144');

INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-1', 'ISBN978-4-8144-0073-7', 'Vlad', NULL, 'Khononov');

INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-2', 'ISBN978-4-8144-0073-7', '了', NULL, '増田');

INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-3', 'ISBN978-4-8144-0073-7', '琢磨', NULL, '綿引');

INSERT INTO book (isbn13, title, contents, publisher_id) VALUES
('ISBN978-4-8144-0006-5', 'ソフトウェアアーキテクチャ・ハードパーツ', 'ソフトウェアアーキテクチャの難しい判断に焦点を当て、分散アーキテクチャにおけるトレードオフ分析の手法を解説します。マイクロサービスやサービスメッシュなどの現代的なアーキテクチャパターンを取り上げ、実際の現場で直面する困難な設計上の決断をどのように行うかを具体的な事例を通じて学びます。', '8144');

INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-10', 'ISBN978-4-8144-0006-5', 'Neal', NULL, 'Ford');
INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-11', 'ISBN978-4-8144-0006-5', 'Mark', NULL, 'Richards');
INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-12', 'ISBN978-4-8144-0006-5', 'Pramod', NULL, 'Sadalage');
INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-13', 'ISBN978-4-8144-0006-5', 'Zhamak', NULL, 'Dehghani');

-- リーダブルコード
INSERT INTO book (isbn13, title, contents, publisher_id) VALUES
('ISBN978-4-87311-565-8', 'リーダブルコード', 'より良いコードを書くための実践的なテクニックを紹介します。変数名、関数名、コメントの書き方から、コードの構造化、テストの書き方まで、コードの可読性を高めるための具体的な方法を解説します。すべての開発者が読むべき必携の一冊です。', '8144');

INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-20', 'ISBN978-4-87311-565-8', 'Dustin', NULL, 'Boswell');
INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-21', 'ISBN978-4-87311-565-8', 'Trevor', NULL, 'Foucher');

-- データ指向アプリケーションデザイン
INSERT INTO book (isbn13, title, contents, publisher_id) VALUES
('ISBN978-4-87311-870-3', 'データ指向アプリケーションデザイン', 'データシステムの設計と構築における根本的な課題を解説します。信頼性、スケーラビリティ、メンテナビリティを実現するためのアーキテクチャパターンを、分散システム、データモデル、クエリ言語、ストレージエンジン、レプリケーション、パーティショニングなどの観点から体系的に説明します。', '8144');

INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-30', 'ISBN978-4-87311-870-3', 'Martin', NULL, 'Kleppmann');

-- Googleのソフトウェアエンジニアリング
INSERT INTO book (isbn13, title, contents, publisher_id) VALUES
('ISBN978-4-87311-965-6', 'Googleのソフトウェアエンジニアリング', 'Googleのソフトウェアエンジニアリングの実践と文化を解説します。コードレビュー、テスト、ドキュメント、依存関係管理、大規模システムの設計と運用など、長期的に持続可能なソフトウェアを構築するためのベストプラクティスを紹介します。', '8144');

INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-40', 'ISBN978-4-87311-965-6', 'Titus', NULL, 'Winters');
INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-41', 'ISBN978-4-87311-965-6', 'Tom', NULL, 'Manshreck');
INSERT INTO author (id, isbn13, first_name, middle_name, last_name) VALUES
('author-42', 'ISBN978-4-87311-965-6', 'Hyrum', NULL, 'Wright');