package ai.minimax;

import core.Board;
import core.NextMove;
import core.Cell;

/**
 * ミニマックス法を利用して、次に手を取得するクラスです.
 * @author tanabe
 *
 */
public final class MiniMax {

    /**
     * コンストラクタは使用不可.
     */
    private MiniMax() { }
    
    /**
     * 最善手を取得します.
     * @param level 何手先まで先読みをするかを表す値
     * @param board 現在の盤面
     * @param cell 次に置く石
     * @return 最善手
     * @throws IllegalArgumentException 第一引数が負の値、または第三引数が黒石でも白石でもない場合に発生
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     */
    public static NextMove getNextMove(int level, Board board, Cell cell) {

        Inner root = new Inner(level, board, cell);

        return root.getNextMove();

    }

}
