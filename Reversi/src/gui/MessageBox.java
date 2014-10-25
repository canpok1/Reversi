package gui;

import processing.core.PApplet;

/**
 * メッセージ表示領域です。
 * @author tanabe
 *
 */
public class MessageBox {

    /**
     * メッセージボックス内の余白の横幅です。
     */
    private static final int SPACE_WIDTH = 5;

    /**
     * メッセージボックス内の余白の縦幅です。
     */
    private static final int SPACE_HEIGHT = 5;

    /**
     * メッセージボックスの左上のX座標です。
     */
    private int x;

    /**
     * メッセージボックスの左上のY座標です。
     */
    private int y;

    /**
     * メッセージボックスの横幅です。
     */
    private final int width;

    /**
     * メッセージボックスの縦幅です。
     */
    private final int height;

    /**
     * メッセージボックスに表示する文字列です。
     */
    private String message;


    /**
     * メッセージボックスを生成します。
     * @param width メッセージボックスの横幅
     * @param height メッセージボックスの縦幅
     * @throws IllegalArgumentException 引数が0以下の値の場合に発生
     */
    public MessageBox(int width, int height) {

        // 引数チェック
        if(width <= 0) {
            throw new IllegalArgumentException("横幅を0以下にすることはできません。");
        }
        if(height <= 0) {
            throw new IllegalArgumentException("縦幅を0以下にすることはできません。");
        }

        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.message = "";

    }


    /**
     * メッセージボックスを描画します。
     * @param parent 描画先のアプレット
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     */
    public void draw(PApplet parent) {

        // 引数チェック
        if(parent == null) {
            throw new NullPointerException("描画先のアプレットをnullにはできません。");
        }

        parent.rectMode(PApplet.CORNER);

        parent.fill(255, 255, 255); // CHECKSTYLE IGNORE THIS LINE
        parent.rect(this.x, this.y, this.width, this.height);

        parent.fill(0, 0, 0);
        parent.textSize(20);    // CHECKSTYLE IGNORE THIS LINE
        parent.textAlign(PApplet.LEFT, PApplet.CENTER);
        parent.text(this.message,
                this.x + MessageBox.SPACE_WIDTH,
                this.y + MessageBox.SPACE_HEIGHT,
                this.width - MessageBox.SPACE_WIDTH * 2,
                this.height - MessageBox.SPACE_HEIGHT * 2);

    }


    /**
     * メッセージボックスの左上座標を変更します。
     * @param x メッセージボックスの左上のX座標
     * @param y メッセージボックスの左上のY座標
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * メッセージボックスに表示する文字列を設定します。
     * @param message 文字列
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     */
    public void setMessage(String message) {

        // 引数チェック
        if(message == null) {
            throw new NullPointerException("テキストをnullにはできません。");
        }

        this.message = message;

    }


}
