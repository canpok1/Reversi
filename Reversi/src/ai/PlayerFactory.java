package ai;

import input.NextMoveSelector;

/**
 * プレイヤーを生成するクラスです。
 * @author tanabe
 *
 */
public class PlayerFactory {

	/**
	 * 置く場所を入力するプレイヤーを表す番号です。
	 */
	public static final int HUMAN = 0;

	/**
	 * 置く場所をランダムに決定するプレイヤーを表す番号です。
	 */
	public static final int RANDOM = 1;

	/**
	 * テーブルから評価値を取得する戦略をとるプレイヤーを表す番号です。
	 */
	public static final int TABLE = 2;

	/**
	 * 最も石をひっくり返すことができる位置に石を置く戦略をとるプレイヤーを表す番号です。
	 */
	public static final int MAXIMUM = 3;

	/**
	 * 入力に使用するインスタンスです。
	 */
	private final NextMoveSelector input;


	/**
	 * プレイヤーを生成するインスタンスを生成します。
	 * @param input 入力に使用するインスタンス
	 * @throws NullPointerException 引数が<code>null</code>の場合に発生
	 */
	public PlayerFactory(NextMoveSelector input) {

		// 引数チェック
		if(input == null) {
			throw new NullPointerException("入力をnullにすることはできません。");
		}

		this.input = input;

	}


	/**
	 * プレイヤーを生成します。プレイヤーが石を置くまでの待ち時間は0msに設定されます。
	 * @param playerType プレイヤーを表す番号
	 * @return 生成したプレイヤー
	 * @throws IllegalArgumentException 対応するプレイヤーがない番号を指定した場合に発生
	 */
	public GamePlayer create(int playerType) {

		return this.create(playerType, 0);

	}

	/**
	 * プレイヤーを生成します。
	 * @param playerType プレイヤーを表す番号
	 * @param delayTime プレイヤーが石を置くまでの待ち時間(ms)。playerTypeに{@link PlayerFactory#HUMAN}を指定した場合は無視されます。
	 * @return 生成したプレイヤー
	 * @throws IllegalArgumentException 対応するプレイヤーがない番号を指定した場合に発生
	 */
	public GamePlayer create(int playerType, int delayTime) {

		switch(playerType) {

		case HUMAN :
			return new Human(this.input);

		case RANDOM :
			return new RandomSelect(delayTime);

		case TABLE :
			return new TableReference(delayTime);

		case MAXIMUM :
			return new MaximumReverse(delayTime);

		default :
			throw new IllegalArgumentException("対応するプレイヤーがありません。");

		}

	}

}
