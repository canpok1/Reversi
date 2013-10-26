package core;

import junit.framework.Assert;


import org.junit.Before;
import org.junit.Test;


/**
 * {@link Board}の単体テストです。
 * @author tanabe
 *
 */
public class BoardTest {

	/**
	 * テスト用のインスタンス
	 */
	private Board board;

	/**
	 * インスタンスを新たに作成します。
	 */
	@Before
	public void before() {
		board = new Board();
	}


	/**
	 * {@link Board#Board()}で生成したインスタンスには
	 * すべてのマス目に石がないことをテストします。
	 */
	@Test
	public void testConstractor() {

		for(int y = 0; y < this.board.getHeight(); y++) {
			for(int x = 0; x < this.board.getWidth(); x++) {

				Assert.assertEquals(Board.NOTHING, this.board.getStone(x, y));

			}
		}
	}


	/**
	 * {@link Board#initStone(int, int, int)}で正しく白石を
	 * 配置することができることをテストします。
	 */
	@Test
	public void testInitStone1() {

		// 石を置く座標(左)
		int left = 0;
		// 石を置く座標(右)
		int right = this.board.getWidth() - 1;
		// 石を置く座標(上)
		int top = 0;
		// 石を置く座標(下)
		int under = this.board.getHeight() - 1;

		// 置く石
		int stone = Board.WHITE_STONE;

		// 左上に配置
		this.board.initStone(left, top, stone);
		Assert.assertEquals(stone, this.board.getStone(left, top));

		// 右上に配置
		this.board.initStone(right, top, stone);
		Assert.assertEquals(stone, this.board.getStone(right, top));

		// 左下に配置
		this.board.initStone(left, under, stone);
		Assert.assertEquals(stone, this.board.getStone(left, under));

		// 右下に配置
		this.board.initStone(right, under, stone);
		Assert.assertEquals(stone, this.board.getStone(right, under));

	}


	/**
	 * {@link Board#initStone(int, int, int)}で正しく黒石を
	 * 配置することができることをテストします。
	 */
	@Test
	public void testInitStone2() {

		// 石を置く座標(左)
		int left = 0;
		// 石を置く座標(右)
		int right = this.board.getWidth() - 1;
		// 石を置く座標(上)
		int top = 0;
		// 石を置く座標(下)
		int under = this.board.getHeight() - 1;

		// 置く石
		int stone = Board.BLACK_STONE;

		// 左上に配置
		this.board.initStone(left, top, stone);
		Assert.assertEquals(stone, this.board.getStone(left, top));

		// 右上に配置
		this.board.initStone(right, top, stone);
		Assert.assertEquals(stone, this.board.getStone(right, top));

		// 左下に配置
		this.board.initStone(left, under, stone);
		Assert.assertEquals(stone, this.board.getStone(left, under));

		// 右下に配置
		this.board.initStone(right, under, stone);
		Assert.assertEquals(stone, this.board.getStone(right, under));

	}


	/**
	 * {@link Board#initStone(int, int, int)}で
	 * 石がないことを設定できることをテストします。
	 */
	@Test
	public void testInitStone3() {

		// 石を置く座標(左)
		int left = 0;
		// 石を置く座標(右)
		int right = this.board.getWidth() - 1;
		// 石を置く座標(上)
		int top = 0;
		// 石を置く座標(下)
		int under = this.board.getHeight() - 1;

		// 置く石
		int stone = Board.NOTHING;

		// 左上に配置
		this.board.initStone(left, top, stone);
		Assert.assertEquals(stone, this.board.getStone(left, top));

		// 右上に配置
		this.board.initStone(right, top, stone);
		Assert.assertEquals(stone, this.board.getStone(right, top));

		// 左下に配置
		this.board.initStone(left, under, stone);
		Assert.assertEquals(stone, this.board.getStone(left, under));

		// 右下に配置
		this.board.initStone(right, under, stone);
		Assert.assertEquals(stone, this.board.getStone(right, under));

	}


	/**
	 * {@link Board#initStone(int, int, int)}で盤面外の座標を設定した場合、
	 * {@link IndexOutOfBoundsException}が発生することをテストします。
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInitStone4() {

		// 設定するマス目のX座標
		int x = this.board.getWidth() + 1;
		// 設定するマス目のY座標
		int y = this.board.getHeight() + 1;
		// 設定値
		int stone = Board.NOTHING;

		// ここで例外発生
		this.board.initStone(x, y, stone);

	}


	/**
	 * {@link Board#equals(Object)}に石の配置が等しいインスタンスを与えた場合、
	 * 戻り値が<code>true</code>となることをテストします。
	 */
	@Test
	public void testEquals1() {

		// 比較用のインスタンス
		Board b = new Board();

		// 石を配置
		this.board.initStone(0, 0, Board.WHITE_STONE);
		b.initStone(0, 0, Board.WHITE_STONE);

		this.board.initStone(1, 0, Board.BLACK_STONE);
		b.initStone(1, 0, Board.BLACK_STONE);

		this.board.initStone(6, 6, Board.WHITE_STONE);
		b.initStone(6, 6, Board.WHITE_STONE);

		this.board.initStone(4, 2, Board.BLACK_STONE);
		b.initStone(4, 2, Board.BLACK_STONE);

		// 比較
		Assert.assertTrue(this.board.equals(b));

	}


	/**
	 * {@link Board#equals(Object)}に石の配置が異なるインスタンスを与えた場合、
	 * 戻り値が<code>false</code>となることをテストします。
	 */
	@Test
	public void testEquals2() {

		// 比較用のインスタンス
		Board b = new Board();

		// 石を配置
		this.board.initStone(0, 0, Board.WHITE_STONE);
		b.initStone(0, 0, Board.WHITE_STONE);

		this.board.initStone(1, 0, Board.BLACK_STONE);
		b.initStone(1, 0, Board.BLACK_STONE);

		this.board.initStone(6, 6, Board.WHITE_STONE);
		b.initStone(6, 6, Board.WHITE_STONE);

		this.board.initStone(4, 2, Board.BLACK_STONE);

		// 比較
		Assert.assertFalse(this.board.equals(b));

	}


	/**
	 * {@link Board#Board(Board)}で、石の配置が等しい別のインスタンスが
	 * 生成されることをテストします。
	 */
	@Test
	public void testCopy1() {

		this.board.initStone(0, 0, Board.WHITE_STONE);
		this.board.initStone(1, 1, Board.WHITE_STONE);
		this.board.initStone(2, 2, Board.WHITE_STONE);
		this.board.initStone(3, 3, Board.WHITE_STONE);

		this.board.initStone(1, 0, Board.BLACK_STONE);
		this.board.initStone(2, 0, Board.BLACK_STONE);
		this.board.initStone(3, 0, Board.BLACK_STONE);
		this.board.initStone(4, 0, Board.BLACK_STONE);

		// コピー
		Board copy = new Board(this.board);

		// 石の配置が等しいかをチェック
		Assert.assertTrue(this.board.equals(copy));

		// 異なるインスタンスかをチェック
		Assert.assertFalse(this.board == copy);

	}


	/**
	 * {@link Board#putStone(int, int, int)}で白石を置いた場合、
	 * 上方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * w-------<br>
	 * b-------<br>
	 * b-------<br>
	 * b-------<br>
	 * b-------<br>
	 * b-------<br>
	 * b-------<br>
	 * x-------<br>
	 */
	@Test
	public void testPutStoneWhite1() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.WHITE_STONE);
		ans.initStone(0, 1, Board.WHITE_STONE);
		ans.initStone(0, 2, Board.WHITE_STONE);
		ans.initStone(0, 3, Board.WHITE_STONE);
		ans.initStone(0, 4, Board.WHITE_STONE);
		ans.initStone(0, 5, Board.WHITE_STONE);
		ans.initStone(0, 6, Board.WHITE_STONE);
		ans.initStone(0, 7, Board.WHITE_STONE);

		// 石を配置する
		this.board.initStone(0, 0, Board.WHITE_STONE);
		this.board.initStone(0, 1, Board.BLACK_STONE);
		this.board.initStone(0, 2, Board.BLACK_STONE);
		this.board.initStone(0, 3, Board.BLACK_STONE);
		this.board.initStone(0, 4, Board.BLACK_STONE);
		this.board.initStone(0, 5, Board.BLACK_STONE);
		this.board.initStone(0, 6, Board.BLACK_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 7, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}

	/**
	 * {@link Board#putStone(int, int, int)}で白石を置いた場合、
	 * 右上方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * -------w<br>
	 * ------b-<br>
	 * -----b--<br>
	 * ----b---<br>
	 * ---b----<br>
	 * --b-----<br>
	 * -b------<br>
	 * x-------<br>
	 */
	@Test
	public void testPutStoneWhite2() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(7, 0, Board.WHITE_STONE);
		ans.initStone(6, 1, Board.WHITE_STONE);
		ans.initStone(5, 2, Board.WHITE_STONE);
		ans.initStone(4, 3, Board.WHITE_STONE);
		ans.initStone(3, 4, Board.WHITE_STONE);
		ans.initStone(2, 5, Board.WHITE_STONE);
		ans.initStone(1, 6, Board.WHITE_STONE);
		ans.initStone(0, 7, Board.WHITE_STONE);

		// 石を配置する
		this.board.initStone(7, 0, Board.WHITE_STONE);
		this.board.initStone(6, 1, Board.BLACK_STONE);
		this.board.initStone(5, 2, Board.BLACK_STONE);
		this.board.initStone(4, 3, Board.BLACK_STONE);
		this.board.initStone(3, 4, Board.BLACK_STONE);
		this.board.initStone(2, 5, Board.BLACK_STONE);
		this.board.initStone(1, 6, Board.BLACK_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 7, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で白石を置いた場合、
	 * 横方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * xbbbbbbw<br>
	 */
	@Test
	public void testPutStoneWhite3() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 7, Board.WHITE_STONE);
		ans.initStone(1, 7, Board.WHITE_STONE);
		ans.initStone(2, 7, Board.WHITE_STONE);
		ans.initStone(3, 7, Board.WHITE_STONE);
		ans.initStone(4, 7, Board.WHITE_STONE);
		ans.initStone(5, 7, Board.WHITE_STONE);
		ans.initStone(6, 7, Board.WHITE_STONE);
		ans.initStone(7, 7, Board.WHITE_STONE);

		// 石を配置する
		this.board.initStone(1, 7, Board.BLACK_STONE);
		this.board.initStone(2, 7, Board.BLACK_STONE);
		this.board.initStone(3, 7, Board.BLACK_STONE);
		this.board.initStone(4, 7, Board.BLACK_STONE);
		this.board.initStone(5, 7, Board.BLACK_STONE);
		this.board.initStone(6, 7, Board.BLACK_STONE);
		this.board.initStone(7, 7, Board.WHITE_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 7, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}

	/**
	 * {@link Board#putStone(int, int, int)}で白石を置いた場合、
	 * 右下方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * x-------<br>
	 * -b------<br>
	 * --b-----<br>
	 * ---b----<br>
	 * ----b---<br>
	 * -----b--<br>
	 * ------b-<br>
	 * -------w<br>
	 */
	@Test
	public void testPutStoneWhite4() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.WHITE_STONE);
		ans.initStone(1, 1, Board.WHITE_STONE);
		ans.initStone(2, 2, Board.WHITE_STONE);
		ans.initStone(3, 3, Board.WHITE_STONE);
		ans.initStone(4, 4, Board.WHITE_STONE);
		ans.initStone(5, 5, Board.WHITE_STONE);
		ans.initStone(6, 6, Board.WHITE_STONE);
		ans.initStone(7, 7, Board.WHITE_STONE);

		// 石を配置する
		this.board.initStone(1, 1, Board.BLACK_STONE);
		this.board.initStone(2, 2, Board.BLACK_STONE);
		this.board.initStone(3, 3, Board.BLACK_STONE);
		this.board.initStone(4, 4, Board.BLACK_STONE);
		this.board.initStone(5, 5, Board.BLACK_STONE);
		this.board.initStone(6, 6, Board.BLACK_STONE);
		this.board.initStone(7, 7, Board.WHITE_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 0, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}

	/**
	 * {@link Board#putStone(int, int, int)}で白石を置いた場合、
	 * 下方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * x-------<br>
	 * b-------<br>
	 * b-------<br>
	 * b-------<br>
	 * b-------<br>
	 * b-------<br>
	 * b-------<br>
	 * w-------<br>
	 */
	@Test
	public void testPutStoneWhite5() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.WHITE_STONE);
		ans.initStone(0, 1, Board.WHITE_STONE);
		ans.initStone(0, 2, Board.WHITE_STONE);
		ans.initStone(0, 3, Board.WHITE_STONE);
		ans.initStone(0, 4, Board.WHITE_STONE);
		ans.initStone(0, 5, Board.WHITE_STONE);
		ans.initStone(0, 6, Board.WHITE_STONE);
		ans.initStone(0, 7, Board.WHITE_STONE);

		// 石を配置する
		this.board.initStone(0, 1, Board.BLACK_STONE);
		this.board.initStone(0, 2, Board.BLACK_STONE);
		this.board.initStone(0, 3, Board.BLACK_STONE);
		this.board.initStone(0, 4, Board.BLACK_STONE);
		this.board.initStone(0, 5, Board.BLACK_STONE);
		this.board.initStone(0, 6, Board.BLACK_STONE);
		this.board.initStone(0, 7, Board.WHITE_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 0, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で白石を置いた場合、
	 * 左下方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * -------x<br>
	 * ------b-<br>
	 * -----b--<br>
	 * ----b---<br>
	 * ---b----<br>
	 * --b-----<br>
	 * -b------<br>
	 * w-------<br>
	 */
	@Test
	public void testPutStoneWhite6() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 7, Board.WHITE_STONE);
		ans.initStone(1, 6, Board.WHITE_STONE);
		ans.initStone(2, 5, Board.WHITE_STONE);
		ans.initStone(3, 4, Board.WHITE_STONE);
		ans.initStone(4, 3, Board.WHITE_STONE);
		ans.initStone(5, 2, Board.WHITE_STONE);
		ans.initStone(6, 1, Board.WHITE_STONE);
		ans.initStone(7, 0, Board.WHITE_STONE);

		// 石を配置する
		this.board.initStone(0, 7, Board.WHITE_STONE);
		this.board.initStone(1, 6, Board.BLACK_STONE);
		this.board.initStone(2, 5, Board.BLACK_STONE);
		this.board.initStone(3, 4, Board.BLACK_STONE);
		this.board.initStone(4, 3, Board.BLACK_STONE);
		this.board.initStone(5, 2, Board.BLACK_STONE);
		this.board.initStone(6, 1, Board.BLACK_STONE);

		// 石を置く
		boolean result = this.board.putStone(7, 0, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で白石を置いた場合、
	 * 左方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * wbbbbbbx<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 */
	@Test
	public void testPutStoneWhite7() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.WHITE_STONE);
		ans.initStone(1, 0, Board.WHITE_STONE);
		ans.initStone(2, 0, Board.WHITE_STONE);
		ans.initStone(3, 0, Board.WHITE_STONE);
		ans.initStone(4, 0, Board.WHITE_STONE);
		ans.initStone(5, 0, Board.WHITE_STONE);
		ans.initStone(6, 0, Board.WHITE_STONE);
		ans.initStone(7, 0, Board.WHITE_STONE);

		// 石を配置する
		this.board.initStone(0, 0, Board.WHITE_STONE);
		this.board.initStone(1, 0, Board.BLACK_STONE);
		this.board.initStone(2, 0, Board.BLACK_STONE);
		this.board.initStone(3, 0, Board.BLACK_STONE);
		this.board.initStone(4, 0, Board.BLACK_STONE);
		this.board.initStone(5, 0, Board.BLACK_STONE);
		this.board.initStone(6, 0, Board.BLACK_STONE);

		// 石を置く
		boolean result = this.board.putStone(7, 0, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で白石を置いた場合、
	 * 左上方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * w-------<br>
	 * -b------<br>
	 * --b-----<br>
	 * ---b----<br>
	 * ----b---<br>
	 * -----b--<br>
	 * ------b-<br>
	 * -------x<br>
	 */
	@Test
	public void testPutStoneWhite8() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.WHITE_STONE);
		ans.initStone(1, 1, Board.WHITE_STONE);
		ans.initStone(2, 2, Board.WHITE_STONE);
		ans.initStone(3, 3, Board.WHITE_STONE);
		ans.initStone(4, 4, Board.WHITE_STONE);
		ans.initStone(5, 5, Board.WHITE_STONE);
		ans.initStone(6, 6, Board.WHITE_STONE);
		ans.initStone(7, 7, Board.WHITE_STONE);

		// 石を配置する
		this.board.initStone(0, 0, Board.WHITE_STONE);
		this.board.initStone(1, 1, Board.BLACK_STONE);
		this.board.initStone(2, 2, Board.BLACK_STONE);
		this.board.initStone(3, 3, Board.BLACK_STONE);
		this.board.initStone(4, 4, Board.BLACK_STONE);
		this.board.initStone(5, 5, Board.BLACK_STONE);
		this.board.initStone(6, 6, Board.BLACK_STONE);

		// 石を置く
		boolean result = this.board.putStone(7, 7, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で白石を置こうとする場合、
	 * 盤面は変化せず、戻り値が<code>false</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * w--w--w-<br>
	 * --------<br>
	 * --bbb---<br>
	 * w-bxbb-w<br>
	 * --bbb---<br>
	 * ---b-b--<br>
	 * w-------<br>
	 * ---w---w<br>
	 */
	@Test
	public void testPutStoneWhite9() {

		// 石を配置する
		this.board.initStone(0, 0, Board.WHITE_STONE);
		this.board.initStone(3, 0, Board.WHITE_STONE);
		this.board.initStone(6, 0, Board.WHITE_STONE);
		this.board.initStone(0, 3, Board.WHITE_STONE);
		this.board.initStone(7, 3, Board.WHITE_STONE);
		this.board.initStone(0, 6, Board.WHITE_STONE);
		this.board.initStone(3, 7, Board.WHITE_STONE);
		this.board.initStone(7, 7, Board.WHITE_STONE);

		this.board.initStone(2, 2, Board.BLACK_STONE);
		this.board.initStone(3, 2, Board.BLACK_STONE);
		this.board.initStone(4, 2, Board.BLACK_STONE);
		this.board.initStone(2, 3, Board.BLACK_STONE);
		this.board.initStone(4, 3, Board.BLACK_STONE);
		this.board.initStone(5, 3, Board.BLACK_STONE);
		this.board.initStone(2, 4, Board.BLACK_STONE);
		this.board.initStone(3, 4, Board.BLACK_STONE);
		this.board.initStone(4, 4, Board.BLACK_STONE);
		this.board.initStone(3, 5, Board.BLACK_STONE);
		this.board.initStone(5, 5, Board.BLACK_STONE);

		// 石の位置が等しい異なるインスタンスを生成
		Board ans = new Board(this.board);

		// 石を置く
		boolean result = this.board.putStone(3, 3, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertFalse(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}

	/**
	 * {@link Board#putStone(int, int, int)}で白石を置こうとする場合、
	 * 盤面は変化せず、戻り値が<code>false</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * b--b--b-<br>
	 * -b-b-b--<br>
	 * --bbb---<br>
	 * bbbxbbbb<br>
	 * --bbb---<br>
	 * -b-b-b--<br>
	 * b--b--b-<br>
	 * ---b---b<br>
	 */
	@Test
	public void testPutStoneWhite10() {

		// 石を配置する
		this.board.initStone(0, 0, Board.BLACK_STONE);
		this.board.initStone(3, 0, Board.BLACK_STONE);
		this.board.initStone(6, 0, Board.BLACK_STONE);

		this.board.initStone(1, 1, Board.BLACK_STONE);
		this.board.initStone(3, 1, Board.BLACK_STONE);
		this.board.initStone(5, 1, Board.BLACK_STONE);

		this.board.initStone(2, 2, Board.BLACK_STONE);
		this.board.initStone(3, 2, Board.BLACK_STONE);
		this.board.initStone(4, 2, Board.BLACK_STONE);

		this.board.initStone(0, 3, Board.BLACK_STONE);
		this.board.initStone(1, 3, Board.BLACK_STONE);
		this.board.initStone(2, 3, Board.BLACK_STONE);
		this.board.initStone(4, 3, Board.BLACK_STONE);
		this.board.initStone(5, 3, Board.BLACK_STONE);
		this.board.initStone(6, 3, Board.BLACK_STONE);
		this.board.initStone(7, 3, Board.BLACK_STONE);

		this.board.initStone(2, 4, Board.BLACK_STONE);
		this.board.initStone(3, 4, Board.BLACK_STONE);
		this.board.initStone(4, 4, Board.BLACK_STONE);

		this.board.initStone(1, 5, Board.BLACK_STONE);
		this.board.initStone(3, 5, Board.BLACK_STONE);
		this.board.initStone(5, 5, Board.BLACK_STONE);

		this.board.initStone(0, 6, Board.BLACK_STONE);
		this.board.initStone(3, 6, Board.BLACK_STONE);
		this.board.initStone(6, 6, Board.BLACK_STONE);

		this.board.initStone(3, 7, Board.BLACK_STONE);
		this.board.initStone(7, 7, Board.BLACK_STONE);

		// 石の位置が等しい異なるインスタンスを生成
		Board ans = new Board(this.board);

		// 石を置く
		boolean result = this.board.putStone(3, 3, Board.WHITE_STONE);

		// 戻り値チェック
		Assert.assertFalse(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
	 * 上方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * b-------<br>
	 * w-------<br>
	 * w-------<br>
	 * w-------<br>
	 * w-------<br>
	 * w-------<br>
	 * w-------<br>
	 * x-------<br>
	 */
	@Test
	public void testPutStoneBlack1() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.BLACK_STONE);
		ans.initStone(0, 1, Board.BLACK_STONE);
		ans.initStone(0, 2, Board.BLACK_STONE);
		ans.initStone(0, 3, Board.BLACK_STONE);
		ans.initStone(0, 4, Board.BLACK_STONE);
		ans.initStone(0, 5, Board.BLACK_STONE);
		ans.initStone(0, 6, Board.BLACK_STONE);
		ans.initStone(0, 7, Board.BLACK_STONE);

		// 石を配置する
		this.board.initStone(0, 0, Board.BLACK_STONE);
		this.board.initStone(0, 1, Board.WHITE_STONE);
		this.board.initStone(0, 2, Board.WHITE_STONE);
		this.board.initStone(0, 3, Board.WHITE_STONE);
		this.board.initStone(0, 4, Board.WHITE_STONE);
		this.board.initStone(0, 5, Board.WHITE_STONE);
		this.board.initStone(0, 6, Board.WHITE_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 7, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}

	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
	 * 右上方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * -------b<br>
	 * ------w-<br>
	 * -----w--<br>
	 * ----w---<br>
	 * ---w----<br>
	 * --w-----<br>
	 * -w------<br>
	 * x-------<br>
	 */
	@Test
	public void testPutStoneBlack2() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 7, Board.BLACK_STONE);
		ans.initStone(1, 6, Board.BLACK_STONE);
		ans.initStone(2, 5, Board.BLACK_STONE);
		ans.initStone(3, 4, Board.BLACK_STONE);
		ans.initStone(4, 3, Board.BLACK_STONE);
		ans.initStone(5, 2, Board.BLACK_STONE);
		ans.initStone(6, 1, Board.BLACK_STONE);
		ans.initStone(7, 0, Board.BLACK_STONE);

		// 石を配置する
		this.board.initStone(1, 6, Board.WHITE_STONE);
		this.board.initStone(2, 5, Board.WHITE_STONE);
		this.board.initStone(3, 4, Board.WHITE_STONE);
		this.board.initStone(4, 3, Board.WHITE_STONE);
		this.board.initStone(5, 2, Board.WHITE_STONE);
		this.board.initStone(6, 1, Board.WHITE_STONE);
		this.board.initStone(7, 0, Board.BLACK_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 7, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}

	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
	 * 横方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * xwwwwwwb<br>
	 */
	@Test
	public void testPutStoneBlack3() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 7, Board.BLACK_STONE);
		ans.initStone(1, 7, Board.BLACK_STONE);
		ans.initStone(2, 7, Board.BLACK_STONE);
		ans.initStone(3, 7, Board.BLACK_STONE);
		ans.initStone(4, 7, Board.BLACK_STONE);
		ans.initStone(5, 7, Board.BLACK_STONE);
		ans.initStone(6, 7, Board.BLACK_STONE);
		ans.initStone(7, 7, Board.BLACK_STONE);

		// 石を配置する
		this.board.initStone(1, 7, Board.WHITE_STONE);
		this.board.initStone(2, 7, Board.WHITE_STONE);
		this.board.initStone(3, 7, Board.WHITE_STONE);
		this.board.initStone(4, 7, Board.WHITE_STONE);
		this.board.initStone(5, 7, Board.WHITE_STONE);
		this.board.initStone(6, 7, Board.WHITE_STONE);
		this.board.initStone(7, 7, Board.BLACK_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 7, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}

	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
	 * 右下方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * x-------<br>
	 * -w------<br>
	 * --w-----<br>
	 * ---w----<br>
	 * ----w---<br>
	 * -----w--<br>
	 * ------w-<br>
	 * -------b<br>
	 */
	@Test
	public void testPutStoneBlack4() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.BLACK_STONE);
		ans.initStone(1, 1, Board.BLACK_STONE);
		ans.initStone(2, 2, Board.BLACK_STONE);
		ans.initStone(3, 3, Board.BLACK_STONE);
		ans.initStone(4, 4, Board.BLACK_STONE);
		ans.initStone(5, 5, Board.BLACK_STONE);
		ans.initStone(6, 6, Board.BLACK_STONE);
		ans.initStone(7, 7, Board.BLACK_STONE);

		// 石を配置する
		this.board.initStone(1, 1, Board.WHITE_STONE);
		this.board.initStone(2, 2, Board.WHITE_STONE);
		this.board.initStone(3, 3, Board.WHITE_STONE);
		this.board.initStone(4, 4, Board.WHITE_STONE);
		this.board.initStone(5, 5, Board.WHITE_STONE);
		this.board.initStone(6, 6, Board.WHITE_STONE);
		this.board.initStone(7, 7, Board.BLACK_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 0, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}

	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
	 * 下方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * x-------<br>
	 * w-------<br>
	 * w-------<br>
	 * w-------<br>
	 * w-------<br>
	 * w-------<br>
	 * w-------<br>
	 * b-------<br>
	 */
	@Test
	public void testPutStoneBlack5() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.BLACK_STONE);
		ans.initStone(0, 1, Board.BLACK_STONE);
		ans.initStone(0, 2, Board.BLACK_STONE);
		ans.initStone(0, 3, Board.BLACK_STONE);
		ans.initStone(0, 4, Board.BLACK_STONE);
		ans.initStone(0, 5, Board.BLACK_STONE);
		ans.initStone(0, 6, Board.BLACK_STONE);
		ans.initStone(0, 7, Board.BLACK_STONE);

		// 石を配置する
		this.board.initStone(0, 1, Board.WHITE_STONE);
		this.board.initStone(0, 2, Board.WHITE_STONE);
		this.board.initStone(0, 3, Board.WHITE_STONE);
		this.board.initStone(0, 4, Board.WHITE_STONE);
		this.board.initStone(0, 5, Board.WHITE_STONE);
		this.board.initStone(0, 6, Board.WHITE_STONE);
		this.board.initStone(0, 7, Board.BLACK_STONE);

		// 石を置く
		boolean result = this.board.putStone(0, 0, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
	 * 左下方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * -------x<br>
	 * ------w-<br>
	 * -----w--<br>
	 * ----w---<br>
	 * ---w----<br>
	 * --w-----<br>
	 * -w------<br>
	 * b-------<br>
	 */
	@Test
	public void testPutStoneBlack6() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 7, Board.BLACK_STONE);
		ans.initStone(1, 6, Board.BLACK_STONE);
		ans.initStone(2, 5, Board.BLACK_STONE);
		ans.initStone(3, 4, Board.BLACK_STONE);
		ans.initStone(4, 3, Board.BLACK_STONE);
		ans.initStone(5, 2, Board.BLACK_STONE);
		ans.initStone(6, 1, Board.BLACK_STONE);
		ans.initStone(7, 0, Board.BLACK_STONE);

		// 石を配置する
		this.board.initStone(0, 7, Board.BLACK_STONE);
		this.board.initStone(1, 6, Board.WHITE_STONE);
		this.board.initStone(2, 5, Board.WHITE_STONE);
		this.board.initStone(3, 4, Board.WHITE_STONE);
		this.board.initStone(4, 3, Board.WHITE_STONE);
		this.board.initStone(5, 2, Board.WHITE_STONE);
		this.board.initStone(6, 1, Board.WHITE_STONE);

		// 石を置く
		boolean result = this.board.putStone(7, 0, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
	 * 左方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * bwwwwwwx<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 * --------<br>
	 */
	@Test
	public void testPutStoneBlack7() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.BLACK_STONE);
		ans.initStone(1, 0, Board.BLACK_STONE);
		ans.initStone(2, 0, Board.BLACK_STONE);
		ans.initStone(3, 0, Board.BLACK_STONE);
		ans.initStone(4, 0, Board.BLACK_STONE);
		ans.initStone(5, 0, Board.BLACK_STONE);
		ans.initStone(6, 0, Board.BLACK_STONE);
		ans.initStone(7, 0, Board.BLACK_STONE);

		// 石を配置する
		this.board.initStone(0, 0, Board.BLACK_STONE);
		this.board.initStone(1, 0, Board.WHITE_STONE);
		this.board.initStone(2, 0, Board.WHITE_STONE);
		this.board.initStone(3, 0, Board.WHITE_STONE);
		this.board.initStone(4, 0, Board.WHITE_STONE);
		this.board.initStone(5, 0, Board.WHITE_STONE);
		this.board.initStone(6, 0, Board.WHITE_STONE);

		// 石を置く
		boolean result = this.board.putStone(7, 0, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
	 * 左上方向の石をひっくり返すことができ、戻り値が
	 * <code>true</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * b-------<br>
	 * -w------<br>
	 * --w-----<br>
	 * ---w----<br>
	 * ----w---<br>
	 * -----w--<br>
	 * ------w-<br>
	 * -------x<br>
	 */
	@Test
	public void testPutStoneBlack8() {

		// 石の配置が正しいか判定するための盤面を生成
		Board ans = new Board();
		ans.initStone(0, 0, Board.BLACK_STONE);
		ans.initStone(1, 1, Board.BLACK_STONE);
		ans.initStone(2, 2, Board.BLACK_STONE);
		ans.initStone(3, 3, Board.BLACK_STONE);
		ans.initStone(4, 4, Board.BLACK_STONE);
		ans.initStone(5, 5, Board.BLACK_STONE);
		ans.initStone(6, 6, Board.BLACK_STONE);
		ans.initStone(7, 7, Board.BLACK_STONE);

		// 石を配置する
		this.board.initStone(0, 0, Board.BLACK_STONE);
		this.board.initStone(1, 1, Board.WHITE_STONE);
		this.board.initStone(2, 2, Board.WHITE_STONE);
		this.board.initStone(3, 3, Board.WHITE_STONE);
		this.board.initStone(4, 4, Board.WHITE_STONE);
		this.board.initStone(5, 5, Board.WHITE_STONE);
		this.board.initStone(6, 6, Board.WHITE_STONE);

		// 石を置く
		boolean result = this.board.putStone(7, 7, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertTrue(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置こうとする場合、
	 * 盤面は変化せず、戻り値が<code>false</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * b--b--b-<br>
	 * --------<br>
	 * --www---<br>
	 * b-wxww-b<br>
	 * --www---<br>
	 * ---w-w--<br>
	 * b-------<br>
	 * ---b---b<br>
	 */
	@Test
	public void testPutStoneBlack9() {

		// 石を配置する
		this.board.initStone(0, 0, Board.BLACK_STONE);
		this.board.initStone(3, 0, Board.BLACK_STONE);
		this.board.initStone(6, 0, Board.BLACK_STONE);
		this.board.initStone(0, 3, Board.BLACK_STONE);
		this.board.initStone(7, 3, Board.BLACK_STONE);
		this.board.initStone(0, 6, Board.BLACK_STONE);
		this.board.initStone(0, 7, Board.BLACK_STONE);
		this.board.initStone(3, 7, Board.BLACK_STONE);

		this.board.initStone(2, 2, Board.WHITE_STONE);
		this.board.initStone(3, 2, Board.WHITE_STONE);
		this.board.initStone(4, 2, Board.WHITE_STONE);
		this.board.initStone(2, 3, Board.WHITE_STONE);
		this.board.initStone(4, 3, Board.WHITE_STONE);
		this.board.initStone(5, 3, Board.WHITE_STONE);
		this.board.initStone(2, 4, Board.WHITE_STONE);
		this.board.initStone(3, 4, Board.WHITE_STONE);
		this.board.initStone(4, 4, Board.WHITE_STONE);
		this.board.initStone(3, 5, Board.WHITE_STONE);
		this.board.initStone(5, 5, Board.WHITE_STONE);

		// 石の配置が等しいインスタンスを生成
		Board ans = new Board(this.board);

		// 石を置く
		boolean result = this.board.putStone(3, 3, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertFalse(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#putStone(int, int, int)}で黒石を置こうとする場合、
	 * 盤面は変化せず、戻り値が<code>false</code>となることをテストします。<br>
	 * 石の配置は次の通りです。<br>
	 * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します。<br>
	 * w--w--w-<br>
	 * -w-w-w--<br>
	 * --www---<br>
	 * wwwxwwww<br>
	 * --www---<br>
	 * -w-w-w--<br>
	 * w--w--w-<br>
	 * ---w---w<br>
	 */
	@Test
	public void testPutStoneBlack10() {

		// 石を配置する
		this.board.initStone(0, 0, Board.WHITE_STONE);
		this.board.initStone(3, 0, Board.WHITE_STONE);
		this.board.initStone(6, 0, Board.WHITE_STONE);

		this.board.initStone(1, 1, Board.WHITE_STONE);
		this.board.initStone(3, 1, Board.WHITE_STONE);
		this.board.initStone(5, 1, Board.WHITE_STONE);

		this.board.initStone(2, 2, Board.WHITE_STONE);
		this.board.initStone(3, 2, Board.WHITE_STONE);
		this.board.initStone(4, 2, Board.WHITE_STONE);

		this.board.initStone(0, 3, Board.WHITE_STONE);
		this.board.initStone(1, 3, Board.WHITE_STONE);
		this.board.initStone(2, 3, Board.WHITE_STONE);
		this.board.initStone(4, 3, Board.WHITE_STONE);
		this.board.initStone(5, 3, Board.WHITE_STONE);
		this.board.initStone(6, 3, Board.WHITE_STONE);
		this.board.initStone(7, 3, Board.WHITE_STONE);

		this.board.initStone(2, 4, Board.WHITE_STONE);
		this.board.initStone(3, 4, Board.WHITE_STONE);
		this.board.initStone(4, 4, Board.WHITE_STONE);

		this.board.initStone(1, 5, Board.WHITE_STONE);
		this.board.initStone(3, 5, Board.WHITE_STONE);
		this.board.initStone(5, 5, Board.WHITE_STONE);

		this.board.initStone(0, 6, Board.WHITE_STONE);
		this.board.initStone(3, 6, Board.WHITE_STONE);
		this.board.initStone(6, 6, Board.WHITE_STONE);

		this.board.initStone(3, 7, Board.WHITE_STONE);
		this.board.initStone(7, 7, Board.WHITE_STONE);

		// 石の位置が等しい異なるインスタンスを生成
		Board ans = new Board(this.board);

		// 石を置く
		boolean result = this.board.putStone(3, 3, Board.BLACK_STONE);

		// 戻り値チェック
		Assert.assertFalse(result);

		// 石の配置をチェック
		Assert.assertTrue(this.board.equals(ans));

	}


	/**
	 * {@link Board#getStoneCount(int)}で石がないマス目の数を
	 * 正しく取得できることをテストします。
	 */
	@Test
	public void testGetStoneCount1() {

		// 白石を3個置く
		this.board.initStone(0, 0, Board.WHITE_STONE);
		this.board.initStone(3, 3, Board.WHITE_STONE);
		this.board.initStone(5, 5, Board.WHITE_STONE);

		// 黒石を3個置く
		this.board.initStone(1, 1, Board.BLACK_STONE);
		this.board.initStone(3, 4, Board.BLACK_STONE);
		this.board.initStone(4, 3, Board.BLACK_STONE);

		// 石がないマス目の数
		int count = this.board.getWidth() * this.board.getHeight() - 3 - 3;

		Assert.assertEquals(count, this.board.getStoneCount(Board.NOTHING));

	}


	/**
	 * {@link Board#getStoneCount(int)}で白石の数を
	 * 正しく取得できることをテストします。
	 */
	@Test
	public void testGetStoneCount2() {

		// 白石を3個置く
		this.board.initStone(0, 0, Board.WHITE_STONE);
		this.board.initStone(3, 3, Board.WHITE_STONE);
		this.board.initStone(5, 5, Board.WHITE_STONE);

		// 黒石を3個置く
		this.board.initStone(1, 1, Board.BLACK_STONE);
		this.board.initStone(3, 4, Board.BLACK_STONE);
		this.board.initStone(4, 3, Board.BLACK_STONE);

		// 白石の数
		int count = 3;

		Assert.assertEquals(count, this.board.getStoneCount(Board.WHITE_STONE));

	}


	/**
	 * {@link Board#getStoneCount(int)}で黒石の数を
	 * 正しく取得できることをテストします。
	 */
	@Test
	public void testGetStoneCount3() {

		// 白石を3個置く
		this.board.initStone(0, 0, Board.WHITE_STONE);
		this.board.initStone(3, 3, Board.WHITE_STONE);
		this.board.initStone(5, 5, Board.WHITE_STONE);

		// 黒石を3個置く
		this.board.initStone(1, 1, Board.BLACK_STONE);
		this.board.initStone(3, 4, Board.BLACK_STONE);
		this.board.initStone(4, 3, Board.BLACK_STONE);

		// 黒石の数
		int count = 3;

		Assert.assertEquals(count, this.board.getStoneCount(Board.BLACK_STONE));

	}

}
