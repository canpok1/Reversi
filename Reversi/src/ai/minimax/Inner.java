package ai.minimax;

import java.util.ArrayList;

import core.Board;
import core.NextMove;

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
     * @param stone 次に置く石
     * @throws IllegalArgumentException 第一引数が負の場合、または第三引数が黒石でも白石でもない場合に発生
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     */
    public Inner(int level, Board board, int stone) {

        super(level, board);

        // 引数チェック
        if((stone != Board.BLACK_STONE) && (stone != Board.WHITE_STONE)) {
            throw new IllegalArgumentException("黒石でも白石でもない値を指定することはできません.");
        }

        // 置ける場所のリスト
        ArrayList<int[]> moves = new ArrayList<int[]>();
        ArrayList<GameTree> nodes = new ArrayList<GameTree>();

        for(int y = 0; y < board.getHeight(); y++) {
            for(int x = 0; x < board.getWidth(); x++) {

                // 一手先の盤面
                Board nextBoard = new Board(board);

                if(nextBoard.putStone(x, y, stone)) {

                    // 次に置く石
                    int nextStone;

                    if(stone == Board.BLACK_STONE) {
                        nextStone = Board.WHITE_STONE;
                    } else {
                        nextStone = Board.BLACK_STONE;
                    }

                    // 一手先のレベル
                    int nextLevel = level - 1;

                    if(nextLevel <= 0) {
                        nodes.add(new Leaf(nextLevel, nextBoard, nextStone));
                    } else {
                        nodes.add(new Inner(nextLevel, nextBoard, nextStone));
                    }

                    moves.add(new int[]{x, y});

                }

            }
        }

        if(nodes.size() <= 0) {
            // 置ける場所がなかった
            Leaf leaf = new Leaf(level, board, stone);

            this.setValue(leaf.getValue());
            this.nextMove = null;

        } else {

            int bestIndex = 0;

            for(int index = 1; index < moves.size(); index++) {

                if(stone == Board.BLACK_STONE) {
                    if(nodes.get(bestIndex).getValue() < nodes.get(index).getValue()) {
                        bestIndex = index;
                    }
                } else if(stone == Board.WHITE_STONE) {
                    if(nodes.get(bestIndex).getValue() > nodes.get(index).getValue()) {
                        bestIndex = index;
                    }
                }
            }

            this.setValue(nodes.get(bestIndex).getValue());
            int[] bestMove = moves.get(bestIndex);
            this.nextMove = new NextMove(bestMove[0], bestMove[1], stone);

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
