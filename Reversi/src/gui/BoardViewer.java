package gui;

import processing.core.PApplet;
import core.Board;

/**
 * リバーシの盤面を表示するクラスです。
 * 
 * @author tanabe
 * 
 */
public class BoardViewer {

    /**
     * マス目一つ当たりの表示サイズ(横幅)です。
     */
    public static final int CELL_SIZE_X = 50;

    /**
     * マス目一つ当たりの表示サイズ(縦幅)です。
     */
    public static final int CELL_SIZE_Y = 50;

    /**
     * 各マス目の縁の幅です。
     */
    public static final int CELL_EDGE_WIDTH = 1;

    /**
     * 石の縁の幅です。
     */
    private static final int STONE_EDGE_WIDTH = 1;

    /**
     * 横に並ぶマス目の数です。
     */
    private final int boardWidth;

    /**
     * 縦に並ぶマス目の数です。
     */
    private final int boardHeight;

    /**
     * 石の配置情報です。
     */
    private final int[][] stones;

    /**
     * 盤面を描画するときの基準点のX座標です。
     */
    private int x;

    /**
     * 盤面を描画するときの基準点のY座標です。
     */
    private int y;

    /**
     * カーソルを合わせるマス目を示す横方向のインデックスです。 描画のための座標ではありません。
     */
    private int cursorX;

    /**
     * カーソルを合わせるマス目を示す縦方向のインデックスです。 描画のための座標ではありません。
     */
    private int cursorY;

    /**
     * カーソルの表示状態です。
     */
    private boolean cursorVisible;

    /**
     * ボードビューワーを生成します。
     * 
     * @param width
     *            横に並ぶマス目の数
     * @param height
     *            縦に並ぶマス目の数
     * @throws IllegalArgumentException
     *             引数が0以下の値の場合に発生
     */
    public BoardViewer(int width, int height) {

        // 引数チェック
        if (width <= 0) {
            throw new IllegalArgumentException("横に並ぶマス目の数を0以下にすることはできません。");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("縦に並ぶマス目の数を0以下にすることはできません。");
        }

        this.boardWidth = width;
        this.boardHeight = height;

        this.stones = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.stones[y][x] = Board.NOTHING;
            }
        }

        this.x = 0;
        this.y = 0;

        this.cursorX = 0;
        this.cursorY = 0;

        this.cursorVisible = false;

    }

    /**
     * 描画するときの基準点を変更します。
     * 
     * @param x
     *            基準点のX座標
     * @param y
     *            基準点のY座標
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 指定座標のマス目にある石を取得します。
     * 
     * @param cellX
     *            X座標
     * @param cellY
     *            Y座標
     * @return マス目にある石。
     * @throws IllegalArgumentException
     *             指定座標にマスがない場合に発生
     */
    public int getStone(int cellX, int cellY) {

        // 座標が盤面上かをチェック
        if ((cellX < 0) || (cellX >= this.boardWidth) || (cellY < 0)
                || (cellY >= this.boardHeight)) {
            throw new IllegalArgumentException("マス目が存在しません。");
        }

        return this.stones[cellY][cellX];

    }

    /**
     * 指定の描画座標のマス目に石を配置します。
     * 
     * @param cellX
     *            X座標
     * @param cellY
     *            Y座標
     * @param stone
     *            配置する石
     * @throws IllegalArgumentException
     *             対応するマスや石がない場合に発生
     */
    public void setStone(int cellX, int cellY, int stone) {

        // 引数チェック
        if ((stone != Board.NOTHING) && (stone != Board.WHITE_STONE)
                && (stone != Board.BLACK_STONE)) {
            throw new IllegalArgumentException("石の値が不正です。");
        }

        // 座標が盤面上かをチェック
        if ((cellX < 0) || (cellX >= this.boardWidth) || (cellY < 0)
                || (cellY >= this.boardHeight)) {
            throw new IllegalArgumentException("マス目が存在しません。");
        }

        this.stones[cellY][cellX] = stone;

    }

    /**
     * 指定の描画座標のマス目にカーソルを合わせます。
     * 
     * @param cellX
     *            マス目のX座標
     * @param cellY
     *            マス目のY座標
     * @throws IllegalArgumentException
     *             存在しないマス目の座標を与えた時に発生
     */
    public void setCursorPosition(int cellX, int cellY) {

        // 引数チェック
        if ((cellX < 0) || (cellX >= this.boardWidth) || (cellY < 0)
                || (cellY >= this.boardHeight)) {
            throw new IllegalArgumentException("マス目が存在しません。");
        }

        this.cursorX = cellX;
        this.cursorY = cellY;

    }

    /**
     * カーソルの表示状態を設定します。
     * 
     * @param visible
     *            カーソルを表示するなら<code>true</code>、表示しないなら<code>false</code>
     */
    public void setCursorVisible(boolean visible) {

        this.cursorVisible = visible;

    }

    /**
     * 盤面を描画します。
     * 
     * @param parent
     *            描画先のアプレット
     * @throws NullPointerException
     *             引数が<code>null</code>の場合に発生
     */
    public void draw(PApplet parent) {

        // 引数チェック
        if (parent == null) {
            throw new NullPointerException("描画先のアプレットをnullにすることはできません。");
        }

        parent.rectMode(PApplet.CORNER);
        parent.ellipseMode(PApplet.CORNER);

        // マス目を描画
        for (int indexY = 0; indexY < this.boardHeight; indexY++) {
            for (int indexX = 0; indexX < this.boardWidth; indexX++) {

                // 描画の基準点（各マス目の左上）
                int drawX = this.x + indexX * BoardViewer.CELL_SIZE_X;
                int drawY = this.y + indexY * BoardViewer.CELL_SIZE_Y;

                if (this.cursorVisible && (this.cursorX == indexX)
                        && (this.cursorY == indexY)) {
                    parent.fill(255, 0, 0);
                } else {
                    parent.fill(0, 255, 0);
                }

                parent.strokeWeight(BoardViewer.CELL_EDGE_WIDTH);
                parent.rect(drawX, drawY, BoardViewer.CELL_SIZE_X,
                        BoardViewer.CELL_SIZE_Y);

                // 石を描画
                parent.strokeWeight(BoardViewer.STONE_EDGE_WIDTH);
                if (this.stones[indexY][indexX] == Board.BLACK_STONE) {

                    parent.fill(0, 0, 0);
                    parent.ellipse(drawX, drawY, BoardViewer.CELL_SIZE_X,
                            BoardViewer.CELL_SIZE_Y);

                } else if (this.stones[indexY][indexX] == Board.WHITE_STONE) {

                    parent.fill(255, 255, 255);
                    parent.ellipse(drawX, drawY, BoardViewer.CELL_SIZE_X,
                            BoardViewer.CELL_SIZE_Y);

                }

            }
        }

    }

    /**
     * 描画時の横幅を取得します。
     * 
     * @return 描画時の横幅
     */
    public int getDrawWidth() {
        return BoardViewer.CELL_SIZE_X * this.boardWidth;
    }

    /**
     * 描画時の縦幅を取得します。
     * 
     * @return 描画時の縦幅
     */
    public int getDrawHeight() {
        return BoardViewer.CELL_SIZE_Y * this.boardHeight;
    }

    /**
     * 描画座標に対応するマス目座標を取得します。<br>
     * 対応するマス目がない場合は負の値を返します。
     * 
     * @param drawX
     *            描画用のX座標
     * @return マス目のX座標
     */
    public int getCellX(int drawX) {

        // 横方向のチェック
        for (int cellX = 0; cellX < this.boardWidth; cellX++) {

            int left = this.x + cellX * BoardViewer.CELL_SIZE_X;
            int right = left + BoardViewer.CELL_SIZE_X - 1;

            if ((drawX >= left) && (drawX < right)) {
                return cellX;
            }

        }

        return -1;

    }

    /**
     * 描画座標に対応するマス目座標を取得します。<br>
     * 対応するマス目がない場合は負の値を返します。
     * 
     * @param drawY
     *            描画用のY座標
     * @return マス目のY座標
     */
    public int getCellY(int drawY) {

        // 横方向のチェック
        for (int cellY = 0; cellY < this.boardWidth; cellY++) {

            int left = this.x + cellY * BoardViewer.CELL_SIZE_X;
            int right = left + BoardViewer.CELL_SIZE_X - 1;

            if ((drawY >= left) && (drawY < right)) {
                return cellY;
            }

        }

        return -1;

    }

    /**
     * ビューワーの盤面サイズ(横幅)を取得します。
     * 
     * @return 盤面サイズ(横幅)
     */
    public int getBoardWidth() {
        return this.boardWidth;
    }

    /**
     * ビューワーの盤面サイズ(縦幅)を取得します。
     * 
     * @return 盤面サイズ(縦幅)
     */
    public int getBoardHeight() {
        return this.boardHeight;
    }
}
