package ai;

import java.util.ArrayList;

import core.Board;
import core.NextMove;

/**
 * 最も多く石をひっくり返せる場所に置く戦略をとるプレイヤーです。
 * @author tanabe
 *
 */
public class MaximumReverse implements GamePlayer {

	/**
	 * 石を置くまでの待ち時間(ms)です。
	 */
	private final int delayTime;

	/**
	 * プレイヤーを生成します。
	 * @param delayTime 石を置くまでの待ち時間(ms)。
	 */
	public MaximumReverse(int delayTime) {

		// 引数チェック
		if(delayTime < 0) {
			throw new IllegalArgumentException("待ち時間は負の値にすることはできません。");
		}

		this.delayTime = delayTime;

	}

	/**
	 * プレイヤーを生成します。
	 * 石を置くまでの待ち時間は500msです。
	 */
	public MaximumReverse() {
		this(500);
	}

	/**
	 * {@inheritDoc}<br>
	 * 石を置ける場所の中で、評価値が最も高くなる場所に石を置きます。
	 * @return {@inheritDoc}
	 * @throws NullPointerException 引数が<code>null</code>の場合に発生
	 * @throws IllegalArgumentException 対応する石がない場合に発生
	 */
	@Override
	public NextMove think(int stone, Board board) {


		// 置ける場所
		ArrayList<int[]> points = new ArrayList<int[]>();

		Board copyBoard = new Board(board);

		for(int y = 0; y < board.getHeight(); y++) {
			for(int x = 0; x < board.getWidth(); x++) {

				if(copyBoard.putStone(x, y, stone)) {
					points.add(new int[]{x, y});
					copyBoard = new Board(board);
				}

			}
		}

		if(points.size() == 0) {
			// 置ける場所がない場合はパス
			return null;
		}


		// 一番評価値が高くなる場所の候補
		ArrayList<int[]> results = new ArrayList<int[]>();

		int[] p = points.get(0);
		results.add(p);

		// 試しに置く
		copyBoard.putStone(p[0], p[1], stone);

		// 評価値の最大値
		int max = this.getEvaluationValue(stone, copyBoard);

		copyBoard = new Board(board);

		for(int index = 1; index < points.size(); index++) {

			int[] point = points.get(index);
			copyBoard.putStone(point[0], point[1], stone);
			int val = this.getEvaluationValue(stone, copyBoard);

			if(val > max) {
				results.clear();
				results.add(new int[]{point[0], point[1]});
			} else if(val == max) {
				results.add(new int[]{point[0], point[1]});
			}

			copyBoard = new Board(board);

		}

		try {
			Thread.sleep(this.delayTime);
		} catch (InterruptedException e) {
		}

		int select = (int)Math.floor(Math.random() * results.size());
		int[] result = results.get(select);
		return new NextMove(result[0], result[1], stone);

	}


	/**
	 * 現在の盤面の評価値を取得します。<br>
	 * 評価値は、盤面上の自分の石の数です。
	 * @param stone 評価する側の石
	 * @param board 盤面
	 * @return 評価値
	 * @throws NullPointerException 引数が<code>null</code>の場合に発生
	 * @throws IllegalArgumentException 対応する石がない場合に発生
	 */
	private int getEvaluationValue(int stone, Board board) {

		// 引数チェック
		if((stone != Board.BLACK_STONE)
				&& (stone != Board.WHITE_STONE)) {
			throw new IllegalArgumentException("対応する石がありません。");
		}
		if(board == null) {
			throw new NullPointerException("ボードをnullにすることはできません。");
		}

		return board.getStoneCount(stone);

	}
}
