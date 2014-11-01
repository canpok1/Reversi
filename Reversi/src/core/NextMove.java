package core;

/**
 * リバーシの手を表すクラスです.<br>
 * プレイヤーが何色の石をどこに置くかを表します.
 * @author tanabe
 *
 */
public class NextMove {

    /**
     * 石を置くマス目のX座標です.
     */
    private final int x;

    /**
     * 石を置くマス目のY座標です.
     */
    private final int y;

    /**
     * 置く石の色です.どの値が何色を表すかは{@link Board}を参照してください.
     */
    private final int stone;


    /**
     * プレイヤーが指す手を作成します.
     * @param x 石を置くマス目のX座標
     * @param y 石を置くマス目のY座標
     * @param stone 石の色
     */
    public NextMove(int x, int y, int stone) {

        this.x = x;
        this.y = y;
        this.stone = stone;

    }


    /**
     * 石を置くマス目のX座標を取得します.
     * @return X座標
     */
    public int getX() {
        return this.x;
    }

    /**
     * 石を置くマス目のY座標を取得します.
     * @return Y座標
     */
    public int getY() {
        return this.y;
    }

    /**
     * 石の色を取得します.
     * @return 石の色
     */
    public int getStone() {
        return this.stone;
    }


    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
