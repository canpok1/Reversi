package ai;

import input.NextMoveInputter;

import core.Board;
import core.NextMove;

/**
 * 次の手を入力して決定するリバーシプレイヤーです。
 * @author tanabe
 *
 */
public class Human implements GamePlayer {

	/**
	 * 入力元です。
	 */
	private final NextMoveInputter inputter;


	/**
	 * リバーシプレイヤーを生成します。
	 * @param inputter 入力元
	 * @throws NullPointerException 引数が<code>null</code>の場合に発生
	 */
	public Human(NextMoveInputter inputter) {

		// 引数チェック
		if(inputter == null) {
			throw new NullPointerException("引数をnullにはできません。");
		}

		this.inputter = inputter;

	}



	@Override
	public NextMove getNextMove(int stone, Board board) {

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
			if(stone == Board.WHITE_STONE) {
				this.inputter.dispImportantMessage("You cannot put the white stone.");
			} else {
				this.inputter.dispImportantMessage("You cannot put the black stone.");
			}
			return null;
		}


		////////////////////////////////////
		// 石を置く
		////////////////////////////////////

		NextMove move = null;

		boolean isCompleted = false;

		String message = "";

		if(stone == Board.BLACK_STONE) {
			message = "Please input the place which put the black stone.";
		} else if(stone == Board.WHITE_STONE) {
			message = "Please input the place which put the white stone.";
		}

		while(!isCompleted) {

			move = this.inputter.getNextMove(stone, message);

			try {
				if(board.putStone(move.getX(), move.getY(), stone)) {
					isCompleted = true;
				} else {
					if(stone == Board.BLACK_STONE) {
						message = "Cannot put the stone. Please input the place which put the black stone.";
					} else if(stone == Board.WHITE_STONE) {
						message = "Cannot put the stone. Please input the place which put the white stone.";
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				if((move.getX() < 0) || (move.getY() < 0)) {
					// ゲーム中断
					isCompleted = true;
				} else {
					if(stone == Board.BLACK_STONE) {
						message = "A cell does not exist. Please input the place which put the black stone.";
					} else if(stone == Board.WHITE_STONE) {
						message = "A cell does not exist. Please input the place which put the white stone.";
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
