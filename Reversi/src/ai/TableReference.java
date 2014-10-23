package ai;

import ai.minimax.MiniMax;

import core.Board;
import core.NextMove;

/**
 * 石を置くことで全体の評価値が最も高くなる場所に石を置く戦略をとるプレイヤーです。<br>
 * 盤面の場所ごとの評価値をテーブルで管理しています。<br>
 * 自分の石が置いてある場合は評価値を加算、相手の石が置いていある場合は評価値を減算し、
 * 全体の評価値を計算します。
 * @author tanabe
 *
 */
public class TableReference implements GamePlayer {

	/**
	 * 石を置くまでの待ち時間(ms)です。
	 */
	private final int delayTime;

	/**
	 * プレイヤーを生成します。
	 * @param delayTime 石を置くまでの待ち時間(ms)。
	 */
	public TableReference(int delayTime) {

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
	public TableReference() {
		this(500);
	}

	/**
	 * {@inheritDoc}<br>
	 * 石を置ける場所の中で、評価値が最も高くなる場所に石を置きます。
	 * @return {@inheritDoc}
	 * @throws NullPointerException 引数が<code>null</code>の場合に発生
	 * @throws IllegalArgumentException 対応する石がない場合、または盤面サイズが対応していない場合に発生
	 */
	@Override
	public NextMove think(int stone, Board board) {

		long start = System.currentTimeMillis();

		NextMove result = MiniMax.getNextMove(4, board, stone);

		long waitTime = this.delayTime - (System.currentTimeMillis() - start);

		if(waitTime < 0) {
			waitTime = 0;
		}

		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
		}

		return result;
	}

}
