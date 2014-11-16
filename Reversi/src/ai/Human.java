package ai;

import input.NextMoveSelector;
import core.Board;
import core.NextMove;
import core.Stone;

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
        if(inputter == null) {
            throw new NullPointerException("引数をnullにはできません.");
        }

        this.selector = inputter;

    }



    @Override
    public NextMove think(Stone stone, Board board) {

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

                if(checkBoard.putStone(x, y, stone)) {
                    passFlag = false;
                }

            }
        }

        if(passFlag) {
            // パスする
            if(stone == Stone.WHITE) {
                this.selector.dispImportantMessage("You cannot put the white stone.");
            } else {
                this.selector.dispImportantMessage("You cannot put the black stone.");
            }
            return null;
        }


        ////////////////////////////////////
        // 石を置く
        ////////////////////////////////////

        NextMove move = null;

        boolean isCompleted = false;

        String message = "";

        if(stone == Stone.BLACK) {
            message = "Please input the place which put the black stone.";
        } else if(stone == Stone.WHITE) {
            message = "Please input the place which put the white stone.";
        }

        while(!isCompleted) {

            move = this.selector.select(stone, message);

            try {
                if(board.putStone(move.getX(), move.getY(), stone)) {
                    isCompleted = true;
                } else {
                    if(stone == Stone.BLACK) {
                        message = "Cannot put the stone."
                                + " Please input the place which put the black stone.";
                    } else if(stone == Stone.WHITE) {
                        message = "Cannot put the stone."
                                + " Please input the place which put the white stone.";
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                if((move.getX() < 0) || (move.getY() < 0)) {
                    // ゲーム中断
                    isCompleted = true;
                } else {
                    if(stone == Stone.BLACK) {
                        message = "A cell does not exist."
                                + " Please input the place which put the black stone.";
                    } else if(stone == Stone.WHITE) {
                        message = "A cell does not exist."
                                + " Please input the place which put the white stone.";
                    } else {
                        // ゲーム中断
                        isCompleted = true;
                    }
                }
            }

        }

        return move;

    }


}
