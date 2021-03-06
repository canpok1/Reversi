package ai.minimax;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import core.Board;
import core.Cell;

/**
 * {@link Leaf}の単体テストです.
 * @author tanabe
 *
 */
public class LeafTest {

    /**
     * コンストラクタの第一引数に負の値を与えた場合、
     * {@link IllegalArgumentException}が発生することをテストします.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLeaf1() {

        Board board = new Board();

        // ここで例外発生
        new Leaf(-1, board, Cell.BLACK);

    }


    /**
     * コンストラクタの第二引数に<code>null</code>を与えた場合、
     * {@link IllegalArgumentException}が発生することをテストします.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLeaf2() {

        // ここで例外発生
        new Leaf(1, null, Cell.BLACK);

    }


    /**
     * コンストラクタの第三引数に{@link Board#NOTHING}を与えた場合、
     * {@link IllegalArgumentException}が発生することをテストします.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLeaf3() {

        Board board = new Board();

        // ここで例外発生
        new Leaf(1, board, Cell.NOTHING);

    }


    /**
     * コンストラクタの第一引数に与えた値を{@link Leaf#getLevel()}で
     * 取得できることをテストします.
     */
    @Test
    public void testGetLevel() {

        int level = 2;
        Board board = new Board();

        Leaf leaf = new Leaf(level, board, Cell.BLACK);

        assertThat(leaf.getLevel(), is(level));

    }


    /**
     * {@link Leaf#getBoard()}で取得できるインスタンスが、
     * コンストラクタの第二引数で与えたインスタンスとは異なるインスタンスであることをテストします.
     */
    @Test
    public void testGetBoard1() {

        int level = 2;
        Board board = new Board();

        Leaf leaf = new Leaf(level, board, Cell.BLACK);

        assertThat(leaf.getBoard(), is(not(sameInstance(board))));

    }


    /**
     * {@link Leaf#getBoard()}で取得できるインスタンスが、
     * コンストラクタの第二引数で与えたインスタンスと同じ内容であることをテストします.
     */
    @Test
    public void testGetBoard2() {

        int level = 2;
        Board board = new Board();

        Leaf leaf = new Leaf(level, board, Cell.BLACK);

        assertThat(leaf.getBoard(), is(board));

    }

}
