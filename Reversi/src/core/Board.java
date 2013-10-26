package core;

import java.util.HashSet;
import java.util.Set;

/**
 * リバーシの盤面を表すクラスです。
 * @author tanabe
 *
 */
public class Board {

	/**
	 * 石が置かれていないことを表す値です。
	 */
	public static final int NOTHING = 0;

	/**
	 * 白石を表す値です。
	 */
	public static final int WHITE_STONE = 1;

	/**
	 * 黒石を表す値です。
	 */
	public static final int BLACK_STONE = 2;

	/**
	 * 盤面の横のマス目の数です。
	 */
	private static final int WIDTH = 8;

	/**
	 * 盤面の縦のマス目の数です。
	 */
	private static final int HEIGHT = 8;

	/**
	 * 石を置く場所です。
	 */
	private final int[][] stones;

	/**
	 * 盤面を生成します。
	 */
	public Board() {

		this.stones = new int[Board.HEIGHT][Board.WIDTH];

		for(int y = 0; y < Board.HEIGHT; y++) {
			for(int x = 0; x < Board.WIDTH; x++) {

				this.stones[y][x] = Board.NOTHING;

			}
		}

	}


	/**
	 * コピーコンストラクタです。
	 * @param board コピー元のオブジェクト
	 * @throws NullPointerException 引数が<code>null</code>の場合に発生
	 */
	public Board(Board board) {

		// 引数チェック
		if(board == null) {
			throw new NullPointerException("コピー元をnullにはできません。");
		}

		this.stones = new int[Board.HEIGHT][Board.WIDTH];

		for(int y = 0; y < Board.HEIGHT; y++) {
			for(int x = 0; x < Board.WIDTH; x++) {

				this.stones[y][x] = board.getStone(x, y);

			}
		}

	}


	/**
	 * 指定のマス目に石を配置します。ゲームの初期化で使用して下さい。
	 * @param x マス目のX座標
	 * @param y マス目のY座標
	 * @param stone 配置する石を表す値
	 * @throws IllegalArgumentException 配置する石の色を表す値が不正な場合に発生
	 * @throws ArrayIndexOutOfBoundsException 盤面の範囲外を指定した場合に発生
	 */
	public void initStone(int x, int y, int stone) {

		// 引数チェック
		if((stone != Board.NOTHING)
				&& (stone != Board.WHITE_STONE)
				&& (stone != Board.BLACK_STONE)) {
			throw new IllegalArgumentException("石を表す値が正しくありません。");
		}

		this.stones[y][x] = stone;

	}


	/**
	 * 指定のマス目の石を取得します。
	 * @param x マス目のX座標
	 * @param y マス目のY座標
	 * @return 石を表す値
	 * @throws ArrayIndexOutOfBoundsException 盤面の範囲外を指定した場合に発生
	 */
	public int getStone(int x, int y) {
		return this.stones[y][x];
	}


	/**
	 * 指定した石の数を取得します。
	 * @return 石の数
	 * @throws IllegalArgumentException 石を表す値が不正な場合に発生
	 */
	public int getStoneCount(int stone) {

		// 引数チェック
		if((stone != Board.WHITE_STONE)
				&& (stone != Board.BLACK_STONE)
				&& (stone != Board.NOTHING)) {
			throw new IllegalArgumentException("石を表す値が不正です。");
		}

		int count = 0;

		for(int y = 0; y < Board.HEIGHT; y++) {
			for(int x = 0; x < Board.WIDTH; x++) {

				if(this.stones[y][x] == stone) {
					count++;
				}

			}
		}

		return count;

	}


	/**
	 * 指定のマス目に石を配置し、石をひっくり返します。<br>
	 * 石をひっくり返すことができるマス目であれば石を配置し、ひっくり返します。
	 * 石をひっくり返すことができないマス目であれば石を配置しません。
	 * @param x マス目のX座標
	 * @param y マス目のY座標
	 * @param stone 配置する石を表す値
	 * @return 石を置くことができれば<code>true</code>、置けなければ<code>false</code>
	 * @throws ArrayIndexOutOfBoundsException 盤面の範囲外を指定した場合に発生
	 * @throws IllegalArgumentException 第三引数が{@link Board#WHITE_STONE}でも{@link Board#BLACK_STONE}でもない場合に発生
	 */
	public boolean putStone(int x, int y, int stone) {

		// 引数チェック
		if((stone != Board.WHITE_STONE)
				&& (stone != Board.BLACK_STONE)) {
			throw new IllegalArgumentException("石を表す値が不正です。");
		}

		// 指定のマス目に石が置かれていないことをチェック
		if(this.getStone(x, y) != Board.NOTHING) {
			return false;
		}


		// ひっくり返す石の座標の集合
		HashSet<int[]> points = new HashSet<int[]>();

		// 上方向のチェック
		points.addAll(this.getReversedStones(x, y, stone, 0));
		// 右上方向のチェック
		points.addAll(this.getReversedStones(x, y, stone, 1));
		// 右方向のチェック
		points.addAll(this.getReversedStones(x, y, stone, 2));
		// 右下方向のチェック
		points.addAll(this.getReversedStones(x, y, stone, 3));
		// 下方向のチェック
		points.addAll(this.getReversedStones(x, y, stone, 4));
		// 左下方向のチェック
		points.addAll(this.getReversedStones(x, y, stone, 5));
		// 左方向のチェック
		points.addAll(this.getReversedStones(x, y, stone, 6));
		// 左上方向のチェック
		points.addAll(this.getReversedStones(x, y, stone, 7));


		if(points.size() != 0) {

			this.initStone(x, y, stone);

			for(Object obj : points.toArray()) {

				if(obj instanceof int[]) {

					int[] point = (int[])obj;
					this.initStone(point[0], point[1], stone);

				}

			}

			return true;

		} else {
			return false;
		}

	}


	/**
	 * 指定の場所に石を置くことができるかを判定します。<br>
	 * 石をひっくり返すことができない場合は石を置くことができません。
	 * @param x 石を置くマスのX座標
	 * @param y 石を置くマスのY座標
	 * @param stone 置く石
	 * @return 石を置くことができる場合は<code>true</code>、置けない場合は<code>false</code>
	 * @throws ArrayIndexOutOfBoundsException 盤面の範囲外を指定した場合に発生
	 * @throws IllegalArgumentException 第三引数が{@link Board#WHITE_STONE}でも{@link Board#BLACK_STONE}でもない場合に発生
	 */
	public boolean canPut(int x, int y, int stone) {

		// 引数チェック
		if((stone != Board.WHITE_STONE)
				&& (stone != Board.BLACK_STONE)) {
			throw new IllegalArgumentException("石を表す値が不正です。");
		}

		// 上方向のチェック
		if(this.getReversedStones(x, y, stone, 0).size() != 0) {
			return true;
		}
		// 右上方向のチェック
		if(this.getReversedStones(x, y, stone, 1).size() != 0) {
			return true;
		}
		// 右方向のチェック
		if(this.getReversedStones(x, y, stone, 2).size() != 0) {
			return true;
		}
		// 右下方向のチェック
		if(this.getReversedStones(x, y, stone, 3).size() != 0) {
			return true;
		}
		// 下方向のチェック
		if(this.getReversedStones(x, y, stone, 4).size() != 0) {
			return true;
		}
		// 左下方向のチェック
		if(this.getReversedStones(x, y, stone, 5).size() != 0) {
			return true;
		}
		// 左方向のチェック
		if(this.getReversedStones(x, y, stone, 6).size() != 0) {
			return true;
		}
		// 左上方向のチェック
		if(this.getReversedStones(x, y, stone, 7).size() != 0) {
			return true;
		}

		return false;

	}


	/**
	 * 指定座標に石を置いたとき、ひっくり返される石の集合を取得します。
	 * 引数で指定した一方向のみをチェックします。
	 * @param x 石を置くX座標
	 * @param y 石を置くY座標
	 * @param stone 石を表す値
	 * @param direction 方向を表す値。上(0)、右上(1)、右(2)、右下(3)、下(4)、左下(5)、左(6)、左上(7)
	 * @return ひっくり返す石の座標を保持した{@link Set}。
	 * 中身はint[2]で、0番がX座標、1番がY座標です。
	 * @throws IllegalArgumentException 石と方向を表す値が不正な場合に発生
	 */
	private Set<int[]> getReversedStones(int x, int y, int stone, int direction) {

		// チェックしていく方向
		int[][] dir = {
				{0, -1},	// 上
				{1, -1},	// 右上
				{1, 0},	// 右
				{1, 1},	// 右下
				{0, 1},	// 下
				{-1, 1},	// 左下
				{-1, 0},	// 左
				{-1, -1}	// 左上
		};

		// 引数チェック
		if((stone != Board.WHITE_STONE)
				&& (stone != Board.BLACK_STONE)) {
			throw new IllegalArgumentException("石を表す値が不正です。");
		}

		if((direction < 0)
				|| (direction >= dir.length)) {
			throw new IllegalArgumentException("方向を表す値が不正です。");
		}

		// 方向を表す変数です。
		int dirX = dir[direction][0];
		int dirY = dir[direction][1];

		// 石の種類をチェックするマス目の座標です。
		int checkX = x + dirX;
		int checkY = y + dirY;

		// ひっくり返せそうな石が配置されている座標の集合
		HashSet<int[]> stones = new HashSet<int[]>();

		// ひっくり返す石が配置されている座標の集合
		HashSet<int[]> result = new HashSet<int[]>();

		// 指定の方向を走査します。
		while(this.isContain(checkX, checkY)) {

			if(this.getStone(checkX, checkY) == Board.NOTHING) {

				// 石がないマス目なので、何もひっくり返せない
				break;

			} else if(this.getStone(checkX, checkY) == stone) {

				// 同じ石があれば、それまでの見つけた石をひっくり返せる
				result.addAll(stones);
				break;

			}

			// ひっくり返すかもしれないので、候補に追加
			stones.add(new int[]{checkX, checkY});

			checkX += dirX;
			checkY += dirY;

		}

		return result;

	}


	/**
	 * 指定の座標が盤面上かを判定します。
	 * @param x X座標
	 * @param y Y座標
	 * @return 盤面上であれば<code>true</code>、そうでないなら<code>false</code>
	 */
	private boolean isContain(int x, int y) {

		if((x >= 0) && (x <= this.getWidth() - 1)
				&& (y >= 0) && (y <= this.getHeight() - 1)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 内容が等しいかを判定します。
	 * @param obj 比較対象のオブジェクト
	 * @return 盤面サイズと石の配置が同じであれば<code>true</code>、それ以外は<code>false</code>
	 */
	@Override
	public boolean equals(Object obj) {

		// 同じインスタンスなら比較の必要なし
		if(obj == this) {
			return true;
		}


		if(!(obj instanceof Board)) {
			return false;
		}

		Board board = (Board)obj;

		// サイズを比較
		if((this.getWidth() != board.getWidth())
			|| (this.getHeight() != board.getHeight())) {
			return false;
		}

		// 配置してある石を比較
		for(int y = 0; y < this.getHeight(); y++) {
			for(int x = 0; x < this.getWidth(); x++) {

				if(this.getStone(x, y) != board.getStone(x, y)) {
					return false;
				}

			}
		}

		return true;

	}


	/**
	 * ハッシュ値を取得します。<br>
	 * {@link Board#equals(Object)}で比較すると等価になるインスタンス同士の
	 * ハッシュ値は同じとならなければなりません。<br>
	 * 等価とならないインスタンス同士のハッシュ値は異なっていなくても
	 * 構いません。<br>
	 * このクラスでは盤面のマス目の数をハッシュ値として返すことにします。
	 *
	 * @return ハッシュ値
	 */
	@Override
	public int hashCode() {
		return this.getWidth() * this.getHeight();
	}

	/**
	 * 横に並ぶマス目の数を取得します。
	 * @return 横に並ぶマス目の数
	 */
	public int getWidth() {
		return Board.WIDTH;
	}


	/**
	 * 縦に並ぶマス目の数を取得します。
	 * @return 縦に並ぶマス目の数
	 */
	public int getHeight() {
		return Board.HEIGHT;
	}
}
