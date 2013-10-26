package ai;

import java.util.ArrayList;

import core.Board;
import core.NextMove;

/**
 * 置ける場所にランダムに置いていくプレイヤーです。
 * @author tanabe
 *
 */
public class RandomSelect implements GamePlayer {

	/**
	 * 石を置くまでの待ち時間(ms)です。
	 */
	private final int delayTime;

	/**
	 * プレイヤーを生成します。
	 * @param delayTime 石を置くまでの待ち時間(ms)。
	 */
	public RandomSelect(int delayTime) {

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
	public RandomSelect() {
		this(500);
	}

	/**
	 * {@inheritDoc}
	 * @param stone {@inheritDoc}
	 * @param board {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws NullPointerException 引数が<code>null</code>の場合に発生
	 * @throws IllegalArgumentException 対応する石がない場合に発生
	 */
	@Override
	public NextMove getNextMove(int stone, Board board) {

		// 引数チェック
		if((stone != Board.BLACK_STONE)
				&& (stone != Board.WHITE_STONE)) {
			throw new IllegalArgumentException("対応する石がありません。");
		}
		if(board == null) {
			throw new NullPointerException("ボードをnullにすることはできません。");
		}

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

		// 置ける場所からランダムに一か所選択する
		int select = (int)Math.floor(Math.random() * points.size());
		int[] point = points.get(select);

		NextMove move = new NextMove(point[0], point[1], stone);

		try {
			Thread.sleep(this.delayTime);
		} catch (InterruptedException e) {
		}

		return move;
	}

}
