Reversi
=======

## 概要

リバーシで遊べるプログラムです。

## ビルド方法

JDK1.6以降がインストールされた環境でGradleを使うことを想定しています。
Gradle単体でビルドする方法とEclipseにインポートしてビルドする方法が選べますので、お好きな方法でビルドして下さい。

### Gradleでビルドする場合

build.gradleと同じ階層で下記コマンドを実行してください。

```
gradlew
```

下記処理が行われます。

* ソースコードのコンパイル
* Javadoc生成
* findbugsのチェック
* checkstyleのチェック
* JUnitでのテスト
* jmockit-coverageによるカバレッジ測定
* jarの生成
* 起動batなどが同梱されたzip生成

### Eclipseでビルドする場合

下記コマンドでEclipseのプロジェクトが生成されるので、インポートしてビルドして下さい。

```
gradlew eclipse
```

### コード

doxygenにより生成したドキュメントはgh-pagesブランチにて管理しています。
下記URLで参照可能です。

http://canpok1.github.io/Reversi/

