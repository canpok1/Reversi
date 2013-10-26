package core;

import junit.framework.Assert;

import org.junit.Test;

/**
 * {@link NextMove}のテストクラスです。
 * @author tanabe
 *
 */
public class NextMoveTest {

	/**
	 * コンストラクタの第一引数で与えた正の値を
	 * {@link NextMove#getX()}で取得できることをテストします。
	 */
	@Test
	public void testGetX1() {

		NextMove nextMove = new NextMove(1, 0, 0);

		Assert.assertEquals(1, nextMove.getX());

	}


	/**
	 * コンストラクタの第一引数で与えた負の値を
	 * {@link NextMove#getX()}で取得できることをテストします。
	 */
	@Test
	public void testGetX2() {

		NextMove nextMove = new NextMove(-1, 0, 0);

		Assert.assertEquals(-1, nextMove.getX());

	}


	/**
	 * コンストラクタの第一引数で与えた0を
	 * {@link NextMove#getX()}で取得できることをテストします。
	 */
	@Test
	public void testGetX3() {

		NextMove nextMove = new NextMove(0, 0, 0);

		Assert.assertEquals(0, nextMove.getX());

	}


	/**
	 * コンストラクタの第二引数で与えた正の値を
	 * {@link NextMove#getY()}で取得できることをテストします。
	 */
	@Test
	public void testGetY1() {

		NextMove nextMove = new NextMove(0, 1, 0);

		Assert.assertEquals(1, nextMove.getY());

	}


	/**
	 * コンストラクタの第二引数で与えた負の値を
	 * {@link NextMove#getY()}で取得できることをテストします。
	 */
	@Test
	public void testGetY2() {

		NextMove nextMove = new NextMove(0, -1, 0);

		Assert.assertEquals(-1, nextMove.getY());

	}


	/**
	 * コンストラクタの第二引数で与えた0を
	 * {@link NextMove#getY()}で取得できることをテストします。
	 */
	@Test
	public void testGetY3() {

		NextMove nextMove = new NextMove(0, 0, 0);

		Assert.assertEquals(0, nextMove.getY());

	}


	/**
	 * コンストラクタの第三引数で与えた値を
	 * {@link NextMove#getStone()}で取得できることをテストします。
	 */
	@Test
	public void testGetStone() {

		NextMove nextMove = new NextMove(0, 0, 0);

		Assert.assertEquals(0, nextMove.getY());

	}


}
