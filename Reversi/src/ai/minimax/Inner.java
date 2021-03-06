package ai.minimax;

import java.util.ArrayList;

import util.ArgumentCheckUtil;
import core.Board;
import core.NextMove;
import core.Cell;

/**
 * ゲーム木の内部ノードを表すクラスです.<br>
 * このクラスでは盤面の評価は行いません.
 * 自分にとって最も有利になる手を最善手として保存し、
 * その手を選んだ場合の盤面の評価値をこのノードの評価値とします.
 * @author tanabe
 *
 */
public class Inner extends GameTree {

    /**
     * 最善手です.
     */
    private NextMove nextMove;

    /**
     * 内部ノードを生成します.
     * @param level 何手先を読むかを表す値
     * @param board 評価対象の盤面
     * @param cell 次に置く石
     * @throws IllegalArgumentException 第一引数が負の場合、または第三引数が黒石でも白石でもない場合に発生
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     */
    public Inner(int level, Board board, Cell cell) {

        super(level, board);

        // 引数チェック
        ArgumentCheckUtil.checkNotNothing(cell);

        // 置ける場所のリスト
        ArrayList<int[]> moves = new ArrayList<int[]>();
        ArrayList<GameTree> nodes = new ArrayList<GameTree>();

        for(int y = 0; y < board.getHeight(); y++) {
            for(int x = 0; x < board.getWidth(); x++) {

                // 一手先の盤面
                Board nextBoard = new Board(board);

                if(nextBoard.putStone(x, y, cell)) {

                    nodes.add(makeNextNode(level, cell, nextBoard));
                    moves.add(new int[]{x, y});

                }

            }
        }

        if(nodes.size() <= 0) {
            // 置ける場所がなかった
            Leaf leaf = new Leaf(level, board, cell);

            this.setValue(leaf.getValue());
            this.nextMove = null;

        } else {

            int bestIndex = 0;

            for(int index = 1; index < moves.size(); index++) {

                if(cell == Cell.BLACK) {
                    if(nodes.get(bestIndex).getValue() < nodes.get(index).getValue()) {
                        bestIndex = index;
                    }
                } else if(cell == Cell.WHITE) {
                    if(nodes.get(bestIndex).getValue() > nodes.get(index).getValue()) {
                        bestIndex = index;
                    }
                }
            }

            this.setValue(nodes.get(bestIndex).getValue());
            int[] bestMove = moves.get(bestIndex);
            this.nextMove = new NextMove(bestMove[0], bestMove[1], cell);

        }
    }

    /**
     * 次のレベルのノードを生成します.
     * @param currentLevel 現在のレベル
     * @param currentCell 現在の石
     * @param nextBoard 次のレベルで評価すべき盤面
     * @return 次のレベルのノード
     */
    private GameTree makeNextNode(int currentLevel, Cell currentCell, Board nextBoard) {
        // 次に置く石
        Cell nextStone;

        if(currentCell == Cell.BLACK) {
            nextStone = Cell.WHITE;
        } else {
            nextStone = Cell.BLACK;
        }

        // 一手先のレベル
        int nextLevel = currentLevel - 1;

        if(nextLevel <= 0) {
            return new Leaf(nextLevel, nextBoard, nextStone);
        } else {
            return new Inner(nextLevel, nextBoard, nextStone);
        }
    }

    /**
     * 最善手を取得します.
     * @return 最善手
     */
    public NextMove getNextMove() {
        return this.nextMove;
    }
}
