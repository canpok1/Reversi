// CHECKSTYLE:OFF

package core;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

/**
 * {@link NextMove}のテストクラスです.
 * @author tanabe
 *
 */
public class NextMoveTest {

    /**
     * コンストラクタの第一引数で与えた正の値を
     * {@link NextMove#getX()}で取得できることをテストします.
     */
    @Test
    public void コンストラクタで与えた正のX座標を取得できるか() {

        NextMove nextMove = new NextMove(1, 0, Cell.NOTHING);

        assertThat(nextMove.getX(), is(1));
        
    }


    /**
     * コンストラクタの第一引数で与えた負の値を
     * {@link NextMove#getX()}で取得できることをテストします.
     */
    @Test
    public void コンストラクタで与えた負のX座標を取得できるか() {

        NextMove nextMove = new NextMove(-1, 0, Cell.NOTHING);

        assertThat(nextMove.getX(), is(-1));
        
    }


    /**
     * コンストラクタの第一引数で与えた0を
     * {@link NextMove#getX()}で取得できることをテストします.
     */
    @Test
    public void コンストラクタで与えた0のX座標を取得できるか() {

        NextMove nextMove = new NextMove(0, 0, Cell.NOTHING);

        assertThat(nextMove.getX(), is(0));

    }


    /**
     * コンストラクタの第二引数で与えた正の値を
     * {@link NextMove#getY()}で取得できることをテストします.
     */
    @Test
    public void コンストラクタで与えた正のY座標を取得できるか() {

        NextMove nextMove = new NextMove(0, 1, Cell.NOTHING);

        assertThat(nextMove.getY(), is(1));
        
    }


    /**
     * コンストラクタの第二引数で与えた負の値を
     * {@link NextMove#getY()}で取得できることをテストします.
     */
    @Test
    public void コンストラクタで与えた負のY座標を取得できるか() {

        NextMove nextMove = new NextMove(0, -1, Cell.NOTHING);

        assertThat(nextMove.getY(), is(-1));
        
    }


    /**
     * コンストラクタの第二引数で与えた0を
     * {@link NextMove#getY()}で取得できることをテストします.
     */
    @Test
    public void コンストラクタで与えた0のY座標を取得できるか() {

        NextMove nextMove = new NextMove(0, 0, Cell.NOTHING);

        assertThat(nextMove.getY(), is(0));
        
    }


    /**
     * コンストラクタの第三引数で与えた値を
     * {@link NextMove#getStone()}で取得できることをテストします.
     */
    @Test
    public void コンストラクタで与えた石の種類を取得できるか() {

        NextMove nextMove = new NextMove(0, 0, Cell.NOTHING);

        assertThat(nextMove.getStone(), is(Cell.NOTHING));

    }

    @Test
    public void コンストラクタで与えた座標を表示できること() {
        
        NextMove nextMove = new NextMove(0, 0, Cell.NOTHING);
        
        assertThat(nextMove.toString(), is("(0, 0)"));
        
    }

}
//CHECKSTYLE:ON
