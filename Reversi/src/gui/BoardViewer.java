package gui;

import static gui.GuiConstants.LayoutConstraints.CELL_SIZE_X;
import static gui.GuiConstants.LayoutConstraints.CELL_SIZE_Y;
import static gui.GuiConstants.LayoutConstraints.CELL_EDGE_WIDTH;
import static gui.GuiConstants.LayoutConstraints.STONE_EDGE_WIDTH;
import processing.core.PApplet;
import core.Cell;

/**
 * リバーシの盤面を表示するクラスです.
 * 
 * @author tanabe
 * 
 */
public class BoardViewer {

    /**
     * 横に並ぶマス目の数です.
     */
    private final int boardWidth;

    /**
     * 縦に並ぶマス目の数です.
     */
    private final int boardHeight;

    /**
     * 石の配置情報です.
     */
    private final Cell[][] cells;

    /**
     * 盤面を描画するときの基準点のX座標です.
     */
    private int x;

    /**
     * 盤面を描画するときの基準点のY座標です.
     */
    private int y;

    /**
     * カーソルを合わせるマス目を示す横方向のインデックスです. 描画のための座標ではありません.
     */
    private int cursorX;

    /**
     * カーソルを合わせるマス目を示す縦方向のインデックスです. 描画のための座標ではありません.
     */
    private int cursorY;

    /**
     * カーソルの表示状態です.
     */
    private boolean cursorVisible;

    /**
     * ボードビューワーを生成します.
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
            throw new IllegalArgumentException("横に並ぶマス目の数を0以下にすることはできません.");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("縦に並ぶマス目の数を0以下にすることはできません.");
        }

        this.boardWidth = width;
        this.boardHeight = height;

        this.cells = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.cells[y][x] = Cell.NOTHING;
            }
        }

        this.x = 0;
        this.y = 0;

        this.cursorX = 0;
        this.cursorY = 0;

        this.cursorVisible = false;

    }

    /**
     * 描画するときの基準点を変更します.
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
     * 指定座標のマス目にある石を取得します.
     * 
     * @param cellX
     *            X座標
     * @param cellY
     *            Y座標
     * @return マス目にある石.
     * @throws IllegalArgumentException
     *             指定座標にマスがない場合に発生
     */
    public Cell getStone(int cellX, int cellY) {

        // 座標が盤面上かをチェック
        if ((cellX < 0) || (cellX >= this.boardWidth) || (cellY < 0)
                || (cellY >= this.boardHeight)) {
            throw new IllegalArgumentException("マス目が存在しません.");
        }

        return this.cells[cellY][cellX];

    }

    /**
     * 指定の描画座標のマス目に石を配置します.
     * 
     * @param cellX
     *            X座標
     * @param cellY
     *            Y座標
     * @param cell
     *            配置する石
     * @throws IllegalArgumentException
     *             対応するマスや石がない場合に発生
     */
    public void setStone(int cellX, int cellY, Cell cell) {

        // 座標が盤面上かをチェック
        if ((cellX < 0) || (cellX >= this.boardWidth) || (cellY < 0)
                || (cellY >= this.boardHeight)) {
            throw new IllegalArgumentException("マス目が存在しません.");
        }

        this.cells[cellY][cellX] = cell;

    }

    /**
     * 指定の描画座標のマス目にカーソルを合わせます.
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
            throw new IllegalArgumentException("マス目が存在しません.");
        }

        this.cursorX = cellX;
        this.cursorY = cellY;

    }

    /**
     * カーソルの表示状態を設定します.
     * 
     * @param visible
     *            カーソルを表示するなら<code>true</code>、表示しないなら<code>false</code>
     */
    public void setCursorVisible(boolean visible) {

        this.cursorVisible = visible;

    }

    /**
     * 盤面を描画します.
     * 
     * @param parent
     *            描画先のアプレット
     * @throws NullPointerException
     *             引数が<code>null</code>の場合に発生
     */
    public void draw(PApplet parent) {

        // 引数チェック
        if (parent == null) {
            throw new NullPointerException("描画先のアプレットをnullにすることはできません.");
        }

        parent.rectMode(PApplet.CORNER);
        parent.ellipseMode(PApplet.CORNER);

        // マス目を描画
        for (int indexY = 0; indexY < this.boardHeight; indexY++) {
            for (int indexX = 0; indexX < this.boardWidth; indexX++) {

                // 描画の基準点（各マス目の左上）
                int drawX = this.x + indexX * CELL_SIZE_X;
                int drawY = this.y + indexY * CELL_SIZE_Y;

                if (this.cursorVisible && (this.cursorX == indexX)
                        && (this.cursorY == indexY)) {
                    parent.fill(255, 0, 0); // CHECKSTYLE IGNORE THIS LINE
                } else {
                    parent.fill(0, 255, 0); // CHECKSTYLE IGNORE THIS LINE
                }

                parent.strokeWeight(CELL_EDGE_WIDTH);
                parent.rect(drawX, drawY, CELL_SIZE_X,
                        CELL_SIZE_Y);

                // 石を描画
                parent.strokeWeight(STONE_EDGE_WIDTH);
                if (this.cells[indexY][indexX] == Cell.BLACK) {

                    parent.fill(0, 0, 0);
                    parent.ellipse(drawX, drawY, CELL_SIZE_X, CELL_SIZE_Y);

                } else if (this.cells[indexY][indexX] == Cell.WHITE) {

                    parent.fill(255, 255, 255); // CHECKSTYLE IGNORE THIS LINE
                    parent.ellipse(drawX, drawY, CELL_SIZE_X, CELL_SIZE_Y);

                }

            }
        }

    }

    /**
     * 描画時の横幅を取得します.
     * 
     * @return 描画時の横幅
     */
    public int getDrawWidth() {
        return CELL_SIZE_X * this.boardWidth;
    }

    /**
     * 描画時の縦幅を取得します.
     * 
     * @return 描画時の縦幅
     */
    public int getDrawHeight() {
        return CELL_SIZE_Y * this.boardHeight;
    }

    /**
     * 描画座標に対応するマス目座標を取得します.<br>
     * 対応するマス目がない場合は負の値を返します.
     * 
     * @param drawX
     *            描画用のX座標
     * @return マス目のX座標
     */
    public int getCellX(int drawX) {

        // 横方向のチェック
        for (int cellX = 0; cellX < this.boardWidth; cellX++) {

            int left = this.x + cellX * CELL_SIZE_X;
            int right = left + CELL_SIZE_X - 1;

            if ((drawX >= left) && (drawX < right)) {
                return cellX;
            }

        }

        return -1;

    }

    /**
     * 描画座標に対応するマス目座標を取得します.<br>
     * 対応するマス目がない場合は負の値を返します.
     * 
     * @param drawY
     *            描画用のY座標
     * @return マス目のY座標
     */
    public int getCellY(int drawY) {

        // 横方向のチェック
        for (int cellY = 0; cellY < this.boardWidth; cellY++) {

            int left = this.x + cellY * CELL_SIZE_X;
            int right = left + CELL_SIZE_X - 1;

            if ((drawY >= left) && (drawY < right)) {
                return cellY;
            }

        }

        return -1;

    }

    /**
     * ビューワーの盤面サイズ(横幅)を取得します.
     * 
     * @return 盤面サイズ(横幅)
     */
    public int getBoardWidth() {
        return this.boardWidth;
    }

    /**
     * ビューワーの盤面サイズ(縦幅)を取得します.
     * 
     * @return 盤面サイズ(縦幅)
     */
    public int getBoardHeight() {
        return this.boardHeight;
    }
}
