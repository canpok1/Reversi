package ai;

import util.ArgumentCheckUtil;
import input.NextMoveSelector;
import core.Board;
import core.NextMove;
import core.Cell;

/**
 * 次の手を入力して決定するリバーシプレイヤーです.
 * @author tanabe
 *
 */
public class Human implements GamePlayer {

    /**
     * 入力元です.
     */
    private final NextMoveSelector selector;


    /**
     * リバーシプレイヤーを生成します.
     * @param inputter 入力元
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     */
    public Human(NextMoveSelector inputter) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNull(inputter);

        this.selector = inputter;

    }



    @Override
    public NextMove think(Cell cell, Board board) {

        ////////////////////////////////////
        // 置く場所があるかをチェック
        ////////////////////////////////////

        // 置き場所チェック用の盤面
        Board checkBoard = new Board(board);
        // パスをするかの判定用
        boolean passFlag = true;

        // 置く場所があるかをチェック
        for(int y = 0; y < board.getHeight(); y++) {
            for(int x = 0; x < board.getWidth(); x++) {

                if(checkBoard.putStone(x, y, cell)) {
                    passFlag = false;
                }

            }
        }

        if(passFlag) {
            // パスする
            if(cell == Cell.WHITE) {
                this.selector.dispImportantMessage("You cannot put the white stone.");
            } else {
                this.selector.dispImportantMessage("You cannot put the black stone.");
            }
            return null;
        }

        NextMove move = putStone(cell, board);

        return move;

    }


    /**
     * 置く場所を考えます.
     * @param cell 置きたい石
     * @param board 現在の盤面
     * @return 置く場所
     */
    private NextMove putStone(Cell cell, Board board) {
        NextMove move = null;

        boolean isCompleted = false;

        String message = "";

        if(cell == Cell.BLACK) {
            message = "Please input the place which put the black stone.";
        } else if(cell == Cell.WHITE) {
            message = "Please input the place which put the white stone.";
        }

        while(!isCompleted) {

            move = this.selector.select(cell, message);

            try {
                if(board.putStone(move.getX(), move.getY(), cell)) {
                    isCompleted = true;
                    continue;
                }
                if(cell == Cell.BLACK) {
                    message = "Cannot put the stone."
                            + " Please input the place which put the black stone.";
                    continue;
                }
                if(cell == Cell.WHITE) {
                    message = "Cannot put the stone."
                            + " Please input the place which put the white stone.";
                    continue;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                if((move.getX() < 0) || (move.getY() < 0)) {
                    // ゲーム中断
                    isCompleted = true;
                    continue;
                }
                if(cell == Cell.BLACK) {
                    message = "A cell does not exist."
                            + " Please input the place which put the black stone.";
                    continue;
                }
                if(cell == Cell.WHITE) {
                    message = "A cell does not exist."
                            + " Please input the place which put the white stone.";
                    continue;
                }
                // ゲーム中断
                isCompleted = true;
            }

        }
        return move;
    }


}
