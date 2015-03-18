package gui;

import processing.core.PApplet;
import util.ArgumentCheckUtil;

/**
 * ボタンです.
 * @author tanabe
 *
 */
public class Button {

    /**
     * ボタンの左上のX座標です.
     */
    private int x;

    /**
     * ボタンの左上のY座標です.
     */
    private int y;

    /**
     * ボタンの横幅です.
     */
    private final int width;

    /**
     * ボタンの縦幅です.
     */
    private final int height;

    /**
     * ボタンに表示する文字列です.
     */
    private String text;

    /**
     * 押されたことを示すフラグです.
     */
    private boolean isPressed;

    /**
     * ボタンを生成します.
     * @param width ボタンの横幅
     * @param height ボタンの縦幅
     * @throws IllegalArgumentException 引数が0以下の値の場合に発生
     */
    public Button(int width, int height) {

        // 引数チェック
        ArgumentCheckUtil.checkNotZeroAndNegativeValue(width);
        ArgumentCheckUtil.checkNotZeroAndNegativeValue(height);

        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.text = "";
        this.isPressed = false;

    }


    /**
     * ボタンを描画します.
     * @param parent 描画先のアプレット
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     */
    public void draw(PApplet parent) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNull(parent);

        parent.rectMode(PApplet.CORNER);

        if(this.isContain(parent.mouseX, parent.mouseY)) {
            parent.fill(150, 150, 150); // CHECKSTYLE IGNORE THIS LINE
        } else {
            parent.fill(255, 255, 255); // CHECKSTYLE IGNORE THIS LINE

        }
        parent.rect(this.x, this.y, this.width, this.height);

        parent.fill(0, 0, 0);
        parent.textSize(20);    // CHECKSTYLE IGNORE THIS LINE
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.text(this.text, this.x, this.y, this.width, this.height);

    }


    /**
     * ボタンの左上座標を変更します.
     * @param x ボタンの左上のX座標
     * @param y ボタンの左上のY座標
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * ボタンに表示する文字列を設定します.
     * @param text 文字列
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     */
    public void setText(String text) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNull(text);

        this.text = text;

    }


    /**
     * 指定した座標がボタン上か判定します.
     * @param x X座標
     * @param y Y座標
     * @return ボタン上であれば<code>true</code>、そうでなければ<code>false</code>
     */
    public boolean isContain(int x, int y) {
        return ((x >= this.x) && (x <= this.x + this.width - 1)
                && (y >= this.y) && (y <= this.y + this.height - 1));
    }


    /**
     * ボタンのON/OFF状態を設定します.
     * @param f 押した場合は<code>true</code>、押していない場合は<code>false</code>
     */
    public void setPressed(boolean f) {
        this.isPressed = f;
    }


    /**
     * ボタンのON/OFF状態を取得します.
     * @return 押されている場合は<code>true</code>、押されていない場合は<code>false</code>
     */
    public boolean getPressed() {
        return this.isPressed;
    }
}
