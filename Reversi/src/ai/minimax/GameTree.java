package ai.minimax;

import util.ArgumentCheckUtil;
import core.Board;

/**
 * ゲームツリーを表す抽象クラスです.
 * @author tanabe
 *
 */
public abstract class GameTree {

    /**
     * 現在の盤面が何手先読みを行った結果かを表す値です.
     */
    private final int level;

    /**
     * 盤面の評価値です.
     */
    private int value;

    /**
     * 評価対象の盤面です.
     */
    private final Board board;


    /**
     * ゲームツリーを生成します.
     * @param level 何手先読みをしたかを表す値
     * @param board 評価対象の盤面
     * @throws IllegalArgumentException 第一引数が負、もしくは第二引数がnullの値の場合に発生
     */
    public GameTree(int level, Board board) {

        // 引数チェック
        ArgumentCheckUtil.CheckNotNegativeValue(level);
        ArgumentCheckUtil.CheckNotNull(board);

        this.level = level;
        this.board = new Board(board);
        this.value = 0;

    }


    /**
     * 現在の盤面が何手先読みをした結果なのかを取得します.
     * @return 何手先読みをしたかを表す値
     */
    public int getLevel() {
        return this.level;
    }


    /**
     * 先読みの結果、最も良い評価値です.
     * @return 評価値
     */
    public int getValue() {
        return this.value;
    }


    /**
     * 評価値を設定します.
     * @param value 評価値
     */
    protected void setValue(int value) {
        this.value = value;
    }


    /**
     * 評価対象の盤面を取得します.
     * @return 盤面
     */
    public Board getBoard() {
        return new Board(this.board);
    }

}
