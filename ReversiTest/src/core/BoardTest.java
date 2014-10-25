// CHECKSTYLE:OFF

package core;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * {@link Board}の単体テストです。
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
                    Assert.assertEquals(Board.NOTHING, instance.getStone(x, y));
                }
            }
        }
        
        /**
         * {@link Board#Board(Board)}で、石の配置が等しい別のインスタンスが
         * 生成されることをテストします。
         */
        @Test
        public void コピーコンストラクタで生成したときの石の配置() {    
            Board instance = new Board();
            instance.initStone(0, 0, Board.WHITE_STONE);
            instance.initStone(1, 1, Board.WHITE_STONE);
            instance.initStone(2, 2, Board.WHITE_STONE);
            instance.initStone(3, 3, Board.WHITE_STONE);
    
            instance.initStone(1, 0, Board.BLACK_STONE);
            instance.initStone(2, 0, Board.BLACK_STONE);
            instance.initStone(3, 0, Board.BLACK_STONE);
            instance.initStone(4, 0, Board.BLACK_STONE);
    
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
         * テスト用のインスタンス。
         */
        private Board board;

        /**
         * インスタンスを新たに作成します。
         */
        @Before
        public void setup() {
            board = new Board();
        }

        /**
         * {@link Board#initStone(int, int, int)}で正しく白石を
         * 配置することができることをテストします。
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
        public void initStoneで盤面外を指定すると例外発生() {
    
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
         * {@link Board#getStoneCount(int)}で石がないマス目の数を
         * 正しく取得できることをテストします。
         */
        @Test
        public void getStoneCountで石がないマス目の数を取得できるか() {
    
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
        public void getStoneCountで白石の数を取得できるか() {
    
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
        public void getStoneCountで黒石の数を取得できるか() {
    
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
        
        @Test(expected=ArrayIndexOutOfBoundsException.class)
        public void initStoneでX座標としてマイナス1を渡すと例外発生() {
            this.board.initStone(-1, 0, Board.BLACK_STONE);
        }
        
        @Test(expected=ArrayIndexOutOfBoundsException.class)
        public void initStoneでY座標としてマイナス1を渡すと例外発生() {
            this.board.initStone(0, -1, Board.BLACK_STONE);
        }
        
        @Test(expected=IllegalArgumentException.class)
        public void initStoneで石として3を渡すと例外発生() {
            this.board.initStone(0, 0, 3);
        }
        
        @Test(expected=IllegalArgumentException.class)
        public void putStoneで石として3を渡すと例外発生() {
            this.board.putStone(0, 0, 3);
        }
        
        @Test(expected=IllegalArgumentException.class)
        public void canPutで石として3を渡すと例外発生() {
            this.board.canPut(0, 0, 3);
        }
    }
    
    public static class 石の反転テスト {
        /**
         * テスト用のインスタンス。
         */
        private Board board;

        /**
         * インスタンスを新たに作成します。
         */
        @Before
        public void setup() {
            board = new Board();
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
        public void pusStoneでの白石配置で上方向をひっくり返せるか() {
    
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
        public void putStoneでの白石配置で右上方向をひっくり返せるか() {
    
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
        public void putStoneでの白石配置で右方向の石をひっくり返せるか() {
    
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
        public void putStoneでの白石配置で右下方向の石をひっくり返せるか() {
    
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
        public void putStoneでの白石配置で下方向の石をひっくり返せるか() {
    
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
        public void putStoneでの白石配置で左下方向の石をひっくり返せるか() {
    
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
        public void putStoneでの白石配置で左方向の石をひっくり返せるか() {
    
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
        public void putStoneでの白石配置で左上方向の石をひっくり返せるか() {
    
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
        public void putStoneでの白石配置で間に空白があってどこもひっくり返せないと戻り値False() {
    
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
        public void putStoneでの白石配置で白石がなくてどこもひっくり返せないとき戻り値False() {
    
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
        public void putStoneでの黒石配置で上方向をひっくり返せるか() {
    
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
        public void putStoneでの黒石配置で右上方向をひっくり返せるか() {
    
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
        public void putStoneでの黒石配置で右方向をひっくり返せるか() {
    
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
        public void putStoneでの黒石配置で右下方向をひっくり返せるか() {
    
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
        public void putStoneでの黒石配置で下方向をひっくり返せるか() {
    
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
        public void putStoneでの黒石配置で左下方向をひっくり返せるか() {
    
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
        public void putStoneでの黒石配置で左方向をひっくり返せるか() {
    
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
        public void putStoneでの黒石配置で左上方向をひっくり返せるか() {
    
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
        public void putStoneでの黒石配置で間に空白があってどこもひっくり返せないとき戻り値False() {
    
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
        public void putStoneでの黒石配置で黒石がなくてどこもひっくり返せないとき戻り値False() {
    
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
        
        @Test
        public void putStoneで石が置いてある場所を指定するとFalse() {
            this.board.initStone(0, 0, Board.BLACK_STONE);
            Assert.assertFalse(this.board.putStone(0, 0, Board.BLACK_STONE));
        }
    }
    
    public static class インスタンスの比較テスト {
        
        /**
         * テスト用のインスタンス。
         */
        private Board board;

        /**
         * インスタンスを新たに作成します。
         */
        @Before
        public void setup() {
            board = new Board();
        }

        /**
         * {@link Board#equals(Object)}に石の配置が等しいインスタンスを与えた場合、
         * 戻り値が<code>true</code>となることをテストします。
         */
        @Test
        public void equalsで石の配置が同じだとTrue() {
    
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
        public void equalsで石のは位置が異なるとFalse() {
    
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
}

//CHECKSTYLE:ON
