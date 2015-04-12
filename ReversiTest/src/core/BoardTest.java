// CHECKSTYLE:OFF

package core;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * {@link Board}の単体テストです.
 * @author tanabe
 *
 */
@RunWith(Enclosed.class)
public class BoardTest {

    public static class インスタンス化テスト {
        @Test
        public void デフォルトコンストラクタで生成したときの石の配置() {
            Board instance = new Board();
            
            for(int y = 0; y < instance.getHeight(); y++) {
                for(int x = 0; x < instance.getWidth(); x++) {
                    Assert.assertEquals(Cell.NOTHING, instance.getStone(x, y));
                }
            }
        }
        
        /**
         * {@link Board#Board(Board)}で、石の配置が等しい別のインスタンスが
         * 生成されることをテストします.
         */
        @Test
        public void コピーコンストラクタで生成したときの石の配置() {    
            Board instance = new Board();
            instance.initStone(0, 0, Cell.WHITE);
            instance.initStone(1, 1, Cell.WHITE);
            instance.initStone(2, 2, Cell.WHITE);
            instance.initStone(3, 3, Cell.WHITE);
    
            instance.initStone(1, 0, Cell.BLACK);
            instance.initStone(2, 0, Cell.BLACK);
            instance.initStone(3, 0, Cell.BLACK);
            instance.initStone(4, 0, Cell.BLACK);
    
            // コピー
            Board copy = new Board(instance);
    
            // 石の配置が等しいかをチェック
            Assert.assertTrue(instance.equals(copy));
    
            // 異なるインスタンスかをチェック
            Assert.assertFalse(instance == copy);
    
        }
        
        @Test(expected=IllegalArgumentException.class)
        public void コピーコンストラクタにnullを渡したら例外発生() {
            new Board(null);
        }
    }
    
    public static class 石の配置テスト {
        
        /**
         * テスト用のインスタンス.
         */
        private Board board;

        /**
         * インスタンスを新たに作成します.
         */
        @Before
        public void setup() {
            board = new Board();
        }

        /**
         * {@link Board#initStone(int, int, int)}で正しく白石を
         * 配置することができることをテストします.
         */
        @Test
        public void initStoneで指定した場所に白石を配置できるか() {
    
            // 石を置く座標(左)
            int left = 0;
            // 石を置く座標(右)
            int right = this.board.getWidth() - 1;
            // 石を置く座標(上)
            int top = 0;
            // 石を置く座標(下)
            int under = this.board.getHeight() - 1;
    
            // 置く石
            Cell cell = Cell.WHITE;
    
            // 左上に配置
            this.board.initStone(left, top, cell);
            Assert.assertEquals(cell, this.board.getStone(left, top));
    
            // 右上に配置
            this.board.initStone(right, top, cell);
            Assert.assertEquals(cell, this.board.getStone(right, top));
    
            // 左下に配置
            this.board.initStone(left, under, cell);
            Assert.assertEquals(cell, this.board.getStone(left, under));
    
            // 右下に配置
            this.board.initStone(right, under, cell);
            Assert.assertEquals(cell, this.board.getStone(right, under));
    
        }


        /**
         * {@link Board#initStone(int, int, int)}で正しく黒石を
         * 配置することができることをテストします.
         */
        @Test
        public void initStoneで指定した場所に黒石を配置できるか() {
    
            // 石を置く座標(左)
            int left = 0;
            // 石を置く座標(右)
            int right = this.board.getWidth() - 1;
            // 石を置く座標(上)
            int top = 0;
            // 石を置く座標(下)
            int under = this.board.getHeight() - 1;
    
            // 置く石
            Cell cell = Cell.BLACK;
    
            // 左上に配置
            this.board.initStone(left, top, cell);
            Assert.assertEquals(cell, this.board.getStone(left, top));
    
            // 右上に配置
            this.board.initStone(right, top, cell);
            Assert.assertEquals(cell, this.board.getStone(right, top));
    
            // 左下に配置
            this.board.initStone(left, under, cell);
            Assert.assertEquals(cell, this.board.getStone(left, under));
    
            // 右下に配置
            this.board.initStone(right, under, cell);
            Assert.assertEquals(cell, this.board.getStone(right, under));
    
        }
    
    
        /**
         * {@link Board#initStone(int, int, int)}で
         * 石がないことを設定できることをテストします.
         */
        @Test
        public void initStoneで指定した場所を石なしにできるか() {
    
            // 石を置く座標(左)
            int left = 0;
            // 石を置く座標(右)
            int right = this.board.getWidth() - 1;
            // 石を置く座標(上)
            int top = 0;
            // 石を置く座標(下)
            int under = this.board.getHeight() - 1;
    
            // 置く石
            Cell cell = Cell.NOTHING;
    
            // 左上に配置
            this.board.initStone(left, top, cell);
            Assert.assertEquals(cell, this.board.getStone(left, top));
    
            // 右上に配置
            this.board.initStone(right, top, cell);
            Assert.assertEquals(cell, this.board.getStone(right, top));
    
            // 左下に配置
            this.board.initStone(left, under, cell);
            Assert.assertEquals(cell, this.board.getStone(left, under));
    
            // 右下に配置
            this.board.initStone(right, under, cell);
            Assert.assertEquals(cell, this.board.getStone(right, under));
    
        }
    
    
        /**
         * {@link Board#initStone(int, int, int)}で盤面外の座標を設定した場合、
         * {@link IndexOutOfBoundsException}が発生することをテストします.
         */
        @Test(expected = IndexOutOfBoundsException.class)
        public void initStoneで盤面外を指定すると例外発生() {
    
            // 設定するマス目のX座標
            int x = this.board.getWidth() + 1;
            // 設定するマス目のY座標
            int y = this.board.getHeight() + 1;
            // 設定値
            Cell cell = Cell.NOTHING;
    
            // ここで例外発生
            this.board.initStone(x, y, cell);
    
        }
    
        /**
         * {@link Board#getStoneCount(int)}で石がないマス目の数を
         * 正しく取得できることをテストします.
         */
        @Test
        public void getStoneCountで石がないマス目の数を取得できるか() {
    
            // 白石を3個置く
            this.board.initStone(0, 0, Cell.WHITE);
            this.board.initStone(3, 3, Cell.WHITE);
            this.board.initStone(5, 5, Cell.WHITE);
    
            // 黒石を3個置く
            this.board.initStone(1, 1, Cell.BLACK);
            this.board.initStone(3, 4, Cell.BLACK);
            this.board.initStone(4, 3, Cell.BLACK);
    
            // 石がないマス目の数
            int count = this.board.getWidth() * this.board.getHeight() - 3 - 3;
    
            Assert.assertEquals(count, this.board.getStoneCount(Cell.NOTHING));
    
        }
    
    
        /**
         * {@link Board#getStoneCount(int)}で白石の数を
         * 正しく取得できることをテストします.
         */
        @Test
        public void getStoneCountで白石の数を取得できるか() {
    
            // 白石を3個置く
            this.board.initStone(0, 0, Cell.WHITE);
            this.board.initStone(3, 3, Cell.WHITE);
            this.board.initStone(5, 5, Cell.WHITE);
    
            // 黒石を3個置く
            this.board.initStone(1, 1, Cell.BLACK);
            this.board.initStone(3, 4, Cell.BLACK);
            this.board.initStone(4, 3, Cell.BLACK);
    
            // 白石の数
            int count = 3;
    
            Assert.assertEquals(count, this.board.getStoneCount(Cell.WHITE));
    
        }
    
    
        /**
         * {@link Board#getStoneCount(int)}で黒石の数を
         * 正しく取得できることをテストします.
         */
        @Test
        public void getStoneCountで黒石の数を取得できるか() {
    
            // 白石を3個置く
            this.board.initStone(0, 0, Cell.WHITE);
            this.board.initStone(3, 3, Cell.WHITE);
            this.board.initStone(5, 5, Cell.WHITE);
    
            // 黒石を3個置く
            this.board.initStone(1, 1, Cell.BLACK);
            this.board.initStone(3, 4, Cell.BLACK);
            this.board.initStone(4, 3, Cell.BLACK);
    
            // 黒石の数
            int count = 3;
    
            Assert.assertEquals(count, this.board.getStoneCount(Cell.BLACK));
    
        }
        
        @Test(expected=ArrayIndexOutOfBoundsException.class)
        public void initStoneでX座標としてマイナス1を渡すと例外発生() {
            this.board.initStone(-1, 0, Cell.BLACK);
        }
        
        @Test(expected=ArrayIndexOutOfBoundsException.class)
        public void initStoneでY座標としてマイナス1を渡すと例外発生() {
            this.board.initStone(0, -1, Cell.BLACK);
        }
        
    }
    
    public static class 石の反転可否判断テスト {
        
        /**
         * テスト用のインスタンス
         */
        private Board board;
        
        /**
         * インスタンスを新たに作成します.
         */
        @Before
        public void setup() {
            board = new Board();
        }
        
        @Test
        public void canPutが上方向反転可能なときにTrueを返すか() {
            setStoneForUp(this.board, Cell.WHITE, Cell.BLACK);
            Assert.assertTrue(this.board.canPut(0, 7, Cell.WHITE));
        }
        
        @Test
        public void canPutが右上方向反転可能なときにTrueを返すか() {
            setStoneForRightUp(this.board, Cell.WHITE, Cell.BLACK);
            Assert.assertTrue(this.board.canPut(0, 7, Cell.WHITE));
        }
        
        @Test
        public void canPutが右方向反転可能なときにTrueを返すか() {
            setStoneForRight(this.board, Cell.WHITE, Cell.BLACK);
            Assert.assertTrue(this.board.canPut(0, 7, Cell.WHITE));
        }
        
        @Test
        public void canPutが右下方向反転可能なときにTrueを返すか() {
            setStoneForRightDown(this.board, Cell.WHITE, Cell.BLACK);
            Assert.assertTrue(this.board.canPut(0, 0, Cell.WHITE));
        }
        
        @Test
        public void canPutが下方向反転可能なときにTrueを返すか() {
            setStoneForDown(this.board, Cell.WHITE, Cell.BLACK);
            Assert.assertTrue(this.board.canPut(0, 0, Cell.WHITE));
        }
        
        @Test
        public void canPutが左下方向反転可能なときにTrueを返すか() {
            setStoneForLeftDown(this.board, Cell.WHITE, Cell.BLACK);
            Assert.assertTrue(this.board.canPut(7, 0, Cell.WHITE));
        }

        @Test
        public void canPutが左方向反転可能なときにTrueを返すか() {
            setStoneForLeft(this.board, Cell.WHITE, Cell.BLACK);
            Assert.assertTrue(this.board.canPut(7, 0, Cell.WHITE));
        }
        
        @Test
        public void canPutが左上方向反転可能なときにTrueを返すか() {
            setStoneForLeftUp(this.board, Cell.WHITE, Cell.BLACK);
            Assert.assertTrue(this.board.canPut(7, 7, Cell.WHITE));
        }
    }
    
    public static class 石の反転テスト {
        /**
         * テスト用のインスタンス.
         */
        private Board board;

        /**
         * インスタンスを新たに作成します.
         */
        @Before
        public void setup() {
            board = new Board();
        }

        /**
         * {@link Board#putStone(int, int, int)}で白石を置いた場合、
         * 上方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void pusStoneでの白石配置で上方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.WHITE);
            ans.initStone(0, 1, Cell.WHITE);
            ans.initStone(0, 2, Cell.WHITE);
            ans.initStone(0, 3, Cell.WHITE);
            ans.initStone(0, 4, Cell.WHITE);
            ans.initStone(0, 5, Cell.WHITE);
            ans.initStone(0, 6, Cell.WHITE);
            ans.initStone(0, 7, Cell.WHITE);
    
            // 石を配置する
            setStoneForUp(this.board, Cell.WHITE, Cell.BLACK);
    
            // 石を置く
            boolean result = this.board.putStone(0, 7, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
        /**
         * {@link Board#putStone(int, int, int)}で白石を置いた場合、
         * 右上方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの白石配置で右上方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(7, 0, Cell.WHITE);
            ans.initStone(6, 1, Cell.WHITE);
            ans.initStone(5, 2, Cell.WHITE);
            ans.initStone(4, 3, Cell.WHITE);
            ans.initStone(3, 4, Cell.WHITE);
            ans.initStone(2, 5, Cell.WHITE);
            ans.initStone(1, 6, Cell.WHITE);
            ans.initStone(0, 7, Cell.WHITE);
    
            // 石を配置する
            setStoneForRightUp(board, Cell.WHITE, Cell.BLACK);
    
            // 石を置く
            boolean result = this.board.putStone(0, 7, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で白石を置いた場合、
         * 横方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの白石配置で右方向の石をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 7, Cell.WHITE);
            ans.initStone(1, 7, Cell.WHITE);
            ans.initStone(2, 7, Cell.WHITE);
            ans.initStone(3, 7, Cell.WHITE);
            ans.initStone(4, 7, Cell.WHITE);
            ans.initStone(5, 7, Cell.WHITE);
            ans.initStone(6, 7, Cell.WHITE);
            ans.initStone(7, 7, Cell.WHITE);
    
            // 石を配置する
            setStoneForRight(this.board, Cell.WHITE, Cell.BLACK);
    
            // 石を置く
            boolean result = this.board.putStone(0, 7, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
        /**
         * {@link Board#putStone(int, int, int)}で白石を置いた場合、
         * 右下方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの白石配置で右下方向の石をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.WHITE);
            ans.initStone(1, 1, Cell.WHITE);
            ans.initStone(2, 2, Cell.WHITE);
            ans.initStone(3, 3, Cell.WHITE);
            ans.initStone(4, 4, Cell.WHITE);
            ans.initStone(5, 5, Cell.WHITE);
            ans.initStone(6, 6, Cell.WHITE);
            ans.initStone(7, 7, Cell.WHITE);
    
            // 石を配置する
            setStoneForRightDown(this.board, Cell.WHITE, Cell.BLACK);
    
            // 石を置く
            boolean result = this.board.putStone(0, 0, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
        /**
         * {@link Board#putStone(int, int, int)}で白石を置いた場合、
         * 下方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの白石配置で下方向の石をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.WHITE);
            ans.initStone(0, 1, Cell.WHITE);
            ans.initStone(0, 2, Cell.WHITE);
            ans.initStone(0, 3, Cell.WHITE);
            ans.initStone(0, 4, Cell.WHITE);
            ans.initStone(0, 5, Cell.WHITE);
            ans.initStone(0, 6, Cell.WHITE);
            ans.initStone(0, 7, Cell.WHITE);
    
            // 石を配置する
            setStoneForDown(this.board, Cell.WHITE, Cell.BLACK);
    
            // 石を置く
            boolean result = this.board.putStone(0, 0, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で白石を置いた場合、
         * 左下方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの白石配置で左下方向の石をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 7, Cell.WHITE);
            ans.initStone(1, 6, Cell.WHITE);
            ans.initStone(2, 5, Cell.WHITE);
            ans.initStone(3, 4, Cell.WHITE);
            ans.initStone(4, 3, Cell.WHITE);
            ans.initStone(5, 2, Cell.WHITE);
            ans.initStone(6, 1, Cell.WHITE);
            ans.initStone(7, 0, Cell.WHITE);
    
            // 石を配置する
            setStoneForLeftDown(this.board, Cell.WHITE, Cell.BLACK);
    
            // 石を置く
            boolean result = this.board.putStone(7, 0, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で白石を置いた場合、
         * 左方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの白石配置で左方向の石をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.WHITE);
            ans.initStone(1, 0, Cell.WHITE);
            ans.initStone(2, 0, Cell.WHITE);
            ans.initStone(3, 0, Cell.WHITE);
            ans.initStone(4, 0, Cell.WHITE);
            ans.initStone(5, 0, Cell.WHITE);
            ans.initStone(6, 0, Cell.WHITE);
            ans.initStone(7, 0, Cell.WHITE);
    
            // 石を配置する
            setStoneForLeft(this.board, Cell.WHITE, Cell.BLACK);
    
            // 石を置く
            boolean result = this.board.putStone(7, 0, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で白石を置いた場合、
         * 左上方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの白石配置で左上方向の石をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.WHITE);
            ans.initStone(1, 1, Cell.WHITE);
            ans.initStone(2, 2, Cell.WHITE);
            ans.initStone(3, 3, Cell.WHITE);
            ans.initStone(4, 4, Cell.WHITE);
            ans.initStone(5, 5, Cell.WHITE);
            ans.initStone(6, 6, Cell.WHITE);
            ans.initStone(7, 7, Cell.WHITE);
    
            // 石を配置する
            setStoneForLeftUp(this.board, Cell.WHITE, Cell.BLACK);
    
            // 石を置く
            boolean result = this.board.putStone(7, 7, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で白石を置こうとする場合、
         * 盤面は変化せず、戻り値が<code>false</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの白石配置で間に空白があってどこもひっくり返せないと戻り値False() {
    
            // 石を配置する
            this.board.initStone(0, 0, Cell.WHITE);
            this.board.initStone(3, 0, Cell.WHITE);
            this.board.initStone(6, 0, Cell.WHITE);
            this.board.initStone(0, 3, Cell.WHITE);
            this.board.initStone(7, 3, Cell.WHITE);
            this.board.initStone(0, 6, Cell.WHITE);
            this.board.initStone(3, 7, Cell.WHITE);
            this.board.initStone(7, 7, Cell.WHITE);
    
            this.board.initStone(2, 2, Cell.BLACK);
            this.board.initStone(3, 2, Cell.BLACK);
            this.board.initStone(4, 2, Cell.BLACK);
            this.board.initStone(2, 3, Cell.BLACK);
            this.board.initStone(4, 3, Cell.BLACK);
            this.board.initStone(5, 3, Cell.BLACK);
            this.board.initStone(2, 4, Cell.BLACK);
            this.board.initStone(3, 4, Cell.BLACK);
            this.board.initStone(4, 4, Cell.BLACK);
            this.board.initStone(3, 5, Cell.BLACK);
            this.board.initStone(5, 5, Cell.BLACK);
    
            // 石の位置が等しい異なるインスタンスを生成
            Board ans = new Board(this.board);
    
            // 石を置く
            boolean result = this.board.putStone(3, 3, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertFalse(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
        /**
         * {@link Board#putStone(int, int, int)}で白石を置こうとする場合、
         * 盤面は変化せず、戻り値が<code>false</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの白石配置で白石がなくてどこもひっくり返せないとき戻り値False() {
    
            // 石を配置する
            this.board.initStone(0, 0, Cell.BLACK);
            this.board.initStone(3, 0, Cell.BLACK);
            this.board.initStone(6, 0, Cell.BLACK);
    
            this.board.initStone(1, 1, Cell.BLACK);
            this.board.initStone(3, 1, Cell.BLACK);
            this.board.initStone(5, 1, Cell.BLACK);
    
            this.board.initStone(2, 2, Cell.BLACK);
            this.board.initStone(3, 2, Cell.BLACK);
            this.board.initStone(4, 2, Cell.BLACK);
    
            this.board.initStone(0, 3, Cell.BLACK);
            this.board.initStone(1, 3, Cell.BLACK);
            this.board.initStone(2, 3, Cell.BLACK);
            this.board.initStone(4, 3, Cell.BLACK);
            this.board.initStone(5, 3, Cell.BLACK);
            this.board.initStone(6, 3, Cell.BLACK);
            this.board.initStone(7, 3, Cell.BLACK);
    
            this.board.initStone(2, 4, Cell.BLACK);
            this.board.initStone(3, 4, Cell.BLACK);
            this.board.initStone(4, 4, Cell.BLACK);
    
            this.board.initStone(1, 5, Cell.BLACK);
            this.board.initStone(3, 5, Cell.BLACK);
            this.board.initStone(5, 5, Cell.BLACK);
    
            this.board.initStone(0, 6, Cell.BLACK);
            this.board.initStone(3, 6, Cell.BLACK);
            this.board.initStone(6, 6, Cell.BLACK);
    
            this.board.initStone(3, 7, Cell.BLACK);
            this.board.initStone(7, 7, Cell.BLACK);
    
            // 石の位置が等しい異なるインスタンスを生成
            Board ans = new Board(this.board);
    
            // 石を置く
            boolean result = this.board.putStone(3, 3, Cell.WHITE);
    
            // 戻り値チェック
            Assert.assertFalse(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
         * 上方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で上方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.BLACK);
            ans.initStone(0, 1, Cell.BLACK);
            ans.initStone(0, 2, Cell.BLACK);
            ans.initStone(0, 3, Cell.BLACK);
            ans.initStone(0, 4, Cell.BLACK);
            ans.initStone(0, 5, Cell.BLACK);
            ans.initStone(0, 6, Cell.BLACK);
            ans.initStone(0, 7, Cell.BLACK);
    
            // 石を配置する
            setStoneForUp(this.board, Cell.BLACK, Cell.WHITE);
    
            // 石を置く
            boolean result = this.board.putStone(0, 7, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
         * 右上方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で右上方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 7, Cell.BLACK);
            ans.initStone(1, 6, Cell.BLACK);
            ans.initStone(2, 5, Cell.BLACK);
            ans.initStone(3, 4, Cell.BLACK);
            ans.initStone(4, 3, Cell.BLACK);
            ans.initStone(5, 2, Cell.BLACK);
            ans.initStone(6, 1, Cell.BLACK);
            ans.initStone(7, 0, Cell.BLACK);
    
            // 石を配置する
            setStoneForRightUp(this.board, Cell.BLACK, Cell.WHITE);
    
            // 石を置く
            boolean result = this.board.putStone(0, 7, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
         * 横方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で右方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 7, Cell.BLACK);
            ans.initStone(1, 7, Cell.BLACK);
            ans.initStone(2, 7, Cell.BLACK);
            ans.initStone(3, 7, Cell.BLACK);
            ans.initStone(4, 7, Cell.BLACK);
            ans.initStone(5, 7, Cell.BLACK);
            ans.initStone(6, 7, Cell.BLACK);
            ans.initStone(7, 7, Cell.BLACK);
    
            // 石を配置する
            setStoneForRight(this.board, Cell.BLACK, Cell.WHITE);
    
            // 石を置く
            boolean result = this.board.putStone(0, 7, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
         * 右下方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で右下方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.BLACK);
            ans.initStone(1, 1, Cell.BLACK);
            ans.initStone(2, 2, Cell.BLACK);
            ans.initStone(3, 3, Cell.BLACK);
            ans.initStone(4, 4, Cell.BLACK);
            ans.initStone(5, 5, Cell.BLACK);
            ans.initStone(6, 6, Cell.BLACK);
            ans.initStone(7, 7, Cell.BLACK);
    
            // 石を配置する
            setStoneForRightDown(this.board, Cell.BLACK, Cell.WHITE);
    
            // 石を置く
            boolean result = this.board.putStone(0, 0, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
         * 下方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で下方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.BLACK);
            ans.initStone(0, 1, Cell.BLACK);
            ans.initStone(0, 2, Cell.BLACK);
            ans.initStone(0, 3, Cell.BLACK);
            ans.initStone(0, 4, Cell.BLACK);
            ans.initStone(0, 5, Cell.BLACK);
            ans.initStone(0, 6, Cell.BLACK);
            ans.initStone(0, 7, Cell.BLACK);
    
            // 石を配置する
            setStoneForDown(this.board, Cell.BLACK, Cell.WHITE);
    
            // 石を置く
            boolean result = this.board.putStone(0, 0, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
         * 左下方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で左下方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 7, Cell.BLACK);
            ans.initStone(1, 6, Cell.BLACK);
            ans.initStone(2, 5, Cell.BLACK);
            ans.initStone(3, 4, Cell.BLACK);
            ans.initStone(4, 3, Cell.BLACK);
            ans.initStone(5, 2, Cell.BLACK);
            ans.initStone(6, 1, Cell.BLACK);
            ans.initStone(7, 0, Cell.BLACK);
    
            // 石を配置する
            setStoneForLeftDown(this.board, Cell.BLACK, Cell.WHITE);
    
            // 石を置く
            boolean result = this.board.putStone(7, 0, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
         * 左方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で左方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.BLACK);
            ans.initStone(1, 0, Cell.BLACK);
            ans.initStone(2, 0, Cell.BLACK);
            ans.initStone(3, 0, Cell.BLACK);
            ans.initStone(4, 0, Cell.BLACK);
            ans.initStone(5, 0, Cell.BLACK);
            ans.initStone(6, 0, Cell.BLACK);
            ans.initStone(7, 0, Cell.BLACK);
    
            // 石を配置する
            setStoneForLeft(this.board, Cell.BLACK, Cell.WHITE);
    
            // 石を置く
            boolean result = this.board.putStone(7, 0, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置いた場合、
         * 左上方向の石をひっくり返すことができ、戻り値が
         * <code>true</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で左上方向をひっくり返せるか() {
    
            // 石の配置が正しいか判定するための盤面を生成
            Board ans = new Board();
            ans.initStone(0, 0, Cell.BLACK);
            ans.initStone(1, 1, Cell.BLACK);
            ans.initStone(2, 2, Cell.BLACK);
            ans.initStone(3, 3, Cell.BLACK);
            ans.initStone(4, 4, Cell.BLACK);
            ans.initStone(5, 5, Cell.BLACK);
            ans.initStone(6, 6, Cell.BLACK);
            ans.initStone(7, 7, Cell.BLACK);
    
            // 石を配置する
            setStoneForLeftUp(this.board, Cell.BLACK, Cell.WHITE);
    
            // 石を置く
            boolean result = this.board.putStone(7, 7, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertTrue(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置こうとする場合、
         * 盤面は変化せず、戻り値が<code>false</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で間に空白があってどこもひっくり返せないとき戻り値False() {
    
            // 石を配置する
            this.board.initStone(0, 0, Cell.BLACK);
            this.board.initStone(3, 0, Cell.BLACK);
            this.board.initStone(6, 0, Cell.BLACK);
            this.board.initStone(0, 3, Cell.BLACK);
            this.board.initStone(7, 3, Cell.BLACK);
            this.board.initStone(0, 6, Cell.BLACK);
            this.board.initStone(0, 7, Cell.BLACK);
            this.board.initStone(3, 7, Cell.BLACK);
    
            this.board.initStone(2, 2, Cell.WHITE);
            this.board.initStone(3, 2, Cell.WHITE);
            this.board.initStone(4, 2, Cell.WHITE);
            this.board.initStone(2, 3, Cell.WHITE);
            this.board.initStone(4, 3, Cell.WHITE);
            this.board.initStone(5, 3, Cell.WHITE);
            this.board.initStone(2, 4, Cell.WHITE);
            this.board.initStone(3, 4, Cell.WHITE);
            this.board.initStone(4, 4, Cell.WHITE);
            this.board.initStone(3, 5, Cell.WHITE);
            this.board.initStone(5, 5, Cell.WHITE);
    
            // 石の配置が等しいインスタンスを生成
            Board ans = new Board(this.board);
    
            // 石を置く
            boolean result = this.board.putStone(3, 3, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertFalse(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
    
    
        /**
         * {@link Board#putStone(int, int, int)}で黒石を置こうとする場合、
         * 盤面は変化せず、戻り値が<code>false</code>となることをテストします.<br>
         * 石の配置は次の通りです.<br>
         * -が石がないマス、wが白石、bが黒石、xが石を配置する場所を表します.<br>
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
        public void putStoneでの黒石配置で黒石がなくてどこもひっくり返せないとき戻り値False() {
    
            // 石を配置する
            this.board.initStone(0, 0, Cell.WHITE);
            this.board.initStone(3, 0, Cell.WHITE);
            this.board.initStone(6, 0, Cell.WHITE);
    
            this.board.initStone(1, 1, Cell.WHITE);
            this.board.initStone(3, 1, Cell.WHITE);
            this.board.initStone(5, 1, Cell.WHITE);
    
            this.board.initStone(2, 2, Cell.WHITE);
            this.board.initStone(3, 2, Cell.WHITE);
            this.board.initStone(4, 2, Cell.WHITE);
    
            this.board.initStone(0, 3, Cell.WHITE);
            this.board.initStone(1, 3, Cell.WHITE);
            this.board.initStone(2, 3, Cell.WHITE);
            this.board.initStone(4, 3, Cell.WHITE);
            this.board.initStone(5, 3, Cell.WHITE);
            this.board.initStone(6, 3, Cell.WHITE);
            this.board.initStone(7, 3, Cell.WHITE);
    
            this.board.initStone(2, 4, Cell.WHITE);
            this.board.initStone(3, 4, Cell.WHITE);
            this.board.initStone(4, 4, Cell.WHITE);
    
            this.board.initStone(1, 5, Cell.WHITE);
            this.board.initStone(3, 5, Cell.WHITE);
            this.board.initStone(5, 5, Cell.WHITE);
    
            this.board.initStone(0, 6, Cell.WHITE);
            this.board.initStone(3, 6, Cell.WHITE);
            this.board.initStone(6, 6, Cell.WHITE);
    
            this.board.initStone(3, 7, Cell.WHITE);
            this.board.initStone(7, 7, Cell.WHITE);
    
            // 石の位置が等しい異なるインスタンスを生成
            Board ans = new Board(this.board);
    
            // 石を置く
            boolean result = this.board.putStone(3, 3, Cell.BLACK);
    
            // 戻り値チェック
            Assert.assertFalse(result);
    
            // 石の配置をチェック
            Assert.assertTrue(this.board.equals(ans));
    
        }
        
        @Test
        public void putStoneで石が置いてある場所を指定するとFalse() {
            this.board.initStone(0, 0, Cell.BLACK);
            Assert.assertFalse(this.board.putStone(0, 0, Cell.BLACK));
        }
    }
    
    public static class インスタンスの比較テスト {
        
        /**
         * テスト用のインスタンス.
         */
        private Board board;

        /**
         * インスタンスを新たに作成します.
         */
        @Before
        public void setup() {
            board = new Board();
        }

        /**
         * {@link Board#equals(Object)}に石の配置が等しいインスタンスを与えた場合、
         * 戻り値が<code>true</code>となることをテストします.
         */
        @Test
        public void equalsで石の配置が同じだとTrue() {
    
            // 比較用のインスタンス
            Board b = new Board();
    
            // 石を配置
            this.board.initStone(0, 0, Cell.WHITE);
            b.initStone(0, 0, Cell.WHITE);
    
            this.board.initStone(1, 0, Cell.BLACK);
            b.initStone(1, 0, Cell.BLACK);
    
            this.board.initStone(6, 6, Cell.WHITE);
            b.initStone(6, 6, Cell.WHITE);
    
            this.board.initStone(4, 2, Cell.BLACK);
            b.initStone(4, 2, Cell.BLACK);
    
            // 比較
            Assert.assertTrue(this.board.equals(b));
    
        }
    
    
        /**
         * {@link Board#equals(Object)}に石の配置が異なるインスタンスを与えた場合、
         * 戻り値が<code>false</code>となることをテストします.
         */
        @Test
        public void equalsで石のは位置が異なるとFalse() {
    
            // 比較用のインスタンス
            Board b = new Board();
    
            // 石を配置
            this.board.initStone(0, 0, Cell.WHITE);
            b.initStone(0, 0, Cell.WHITE);
    
            this.board.initStone(1, 0, Cell.BLACK);
            b.initStone(1, 0, Cell.BLACK);
    
            this.board.initStone(6, 6, Cell.WHITE);
            b.initStone(6, 6, Cell.WHITE);
    
            this.board.initStone(4, 2, Cell.BLACK);
    
            // 比較
            Assert.assertFalse(this.board.equals(b));
    
        }
        
        @Test
        public void 同じインスタンスの比較結果はTrue() {
            Assert.assertTrue(board.equals(board));
        }
        
        @Test
        public void 型が違うインスタンスの比較結果はFalse() {
            Object other = new Object();
            Assert.assertFalse(board.equals(other));
        }
        
    }

    /**
     * 石を配置します.<br>
     * -が石がないマス、mが自分の石、oが相手の石を表します.<br>
     * m-------<br>
     * o-------<br>
     * o-------<br>
     * o-------<br>
     * o-------<br>
     * o-------<br>
     * o-------<br>
     * --------<br>
     * @param board 配置先
     * @param mine 自分の石
     * @param other 相手の石
     */
    public static void setStoneForUp(Board board, Cell mine, Cell other) {
        board.initStone(0, 0, mine);
        board.initStone(0, 1, other);
        board.initStone(0, 2, other);
        board.initStone(0, 3, other);
        board.initStone(0, 4, other);
        board.initStone(0, 5, other);
        board.initStone(0, 6, other);
    }
    
    /**
     * 石を配置します.<br>
     * -が石がないマス、mが自分の石、oが相手の石を表します.<br>
     * -------m<br>
     * ------o-<br>
     * -----o--<br>
     * ----o---<br>
     * ---o----<br>
     * --o-----<br>
     * -o------<br>
     * --------<br>
     */
    public static void setStoneForRightUp(Board board, Cell mine, Cell other) {
        board.initStone(7, 0, mine);
        board.initStone(6, 1, other);
        board.initStone(5, 2, other);
        board.initStone(4, 3, other);
        board.initStone(3, 4, other);
        board.initStone(2, 5, other);
        board.initStone(1, 6, other);
    }
    
    /**
     * 石を配置します.<br>
     * -が石がないマス、mが自分の石、oが相手の石を表します.<br>
     * --------<br>
     * --------<br>
     * --------<br>
     * --------<br>
     * --------<br>
     * --------<br>
     * --------<br>
     * -oooooom<br>
     */
    public static void setStoneForRight(Board board, Cell mine, Cell other) {
        board.initStone(1, 7, other);
        board.initStone(2, 7, other);
        board.initStone(3, 7, other);
        board.initStone(4, 7, other);
        board.initStone(5, 7, other);
        board.initStone(6, 7, other);
        board.initStone(7, 7, mine);
    }

    /**
     * 石を配置します.<br>
     * -が石がないマス、mが自分の石、oが相手の石を表します.<br>
     * --------<br>
     * -o------<br>
     * --o-----<br>
     * ---o----<br>
     * ----o---<br>
     * -----o--<br>
     * ------o-<br>
     * -------m<br>
     */
    public static void setStoneForRightDown(Board board, Cell mine, Cell other) {
        board.initStone(1, 1, other);
        board.initStone(2, 2, other);
        board.initStone(3, 3, other);
        board.initStone(4, 4, other);
        board.initStone(5, 5, other);
        board.initStone(6, 6, other);
        board.initStone(7, 7, mine);
    }
    
    /**
     * 石を配置します.<br>
     * -が石がないマス、mが自分の石、oが相手の石を表します.<br>
     * --------<br>
     * o-------<br>
     * o-------<br>
     * o-------<br>
     * o-------<br>
     * o-------<br>
     * o-------<br>
     * m-------<br>
     */
    public static void setStoneForDown(Board board, Cell mine, Cell other) {
        board.initStone(0, 1, other);
        board.initStone(0, 2, other);
        board.initStone(0, 3, other);
        board.initStone(0, 4, other);
        board.initStone(0, 5, other);
        board.initStone(0, 6, other);
        board.initStone(0, 7, mine);
    }


    /**
     * 石を配置します.<br>
     * -が石がないマス、mが自分の石、oが相手の石を表します.<br>
     * --------<br>
     * ------o-<br>
     * -----o--<br>
     * ----o---<br>
     * ---o----<br>
     * --o-----<br>
     * -o------<br>
     * m-------<br>
     */
    public static void setStoneForLeftDown(Board board, Cell mine, Cell other) {
        // 石を配置する
        board.initStone(0, 7, mine);
        board.initStone(1, 6, other);
        board.initStone(2, 5, other);
        board.initStone(3, 4, other);
        board.initStone(4, 3, other);
        board.initStone(5, 2, other);
        board.initStone(6, 1, other);
    }


    /**
     * 石を配置します.<br>
     * -が石がないマス、mが自分の石、oが相手の石を表します.<br>
     * moooooo-<br>
     * --------<br>
     * --------<br>
     * --------<br>
     * --------<br>
     * --------<br>
     * --------<br>
     * --------<br>
     */
    public static void setStoneForLeft(Board board, Cell mine, Cell other) {
        board.initStone(0, 0, mine);
        board.initStone(1, 0, other);
        board.initStone(2, 0, other);
        board.initStone(3, 0, other);
        board.initStone(4, 0, other);
        board.initStone(5, 0, other);
        board.initStone(6, 0, other);
    }
    
    /**
     * 石を配置します.<br>
     * -が石がないマス、mが自分の石、oが相手の石を表します.<br>
     * m-------<br>
     * -o------<br>
     * --o-----<br>
     * ---o----<br>
     * ----o---<br>
     * -----o--<br>
     * ------o-<br>
     * --------<br>
     */
    public static void setStoneForLeftUp(Board board, Cell mine, Cell other) {
        board.initStone(0, 0, mine);
        board.initStone(1, 1, other);
        board.initStone(2, 2, other);
        board.initStone(3, 3, other);
        board.initStone(4, 4, other);
        board.initStone(5, 5, other);
        board.initStone(6, 6, other);
    }
}

//CHECKSTYLE:ON
