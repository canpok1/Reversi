package ai.minimax;

import core.Board;

/**
 * ゲーム木の葉を表すクラスです。
 * 盤面の評価はこのクラスでのみ行います。
 * @author tanabe
 *
 */
public class Leaf extends GameTree {

    /**
     * 対応する盤面の横幅です。
     */
    private static final int BOARD_WIDTH = 8;

    /**
     * 対応する盤面の縦幅です。
     */
    private static final int BOARD_HEIGHT = 8;

    /**
     * 序盤を表す値です。
     */
    private static final int OP_GAME = 0;

    /**
     * 中盤を表す値です。
     */
    private static final int MIDDLE_GAME = 1;

    /**
     * 終盤１を表す値です。
     * 早い段階でどちらかのプレイヤーが隅を二か所とった場合です。
     */
    private static final int END_GAME_1 = 2;

    /**
     * 終盤２を表す値です。
     */
    private static final int END_GAME_2 = 3;


    /**
     * 黒から見た序盤用の評価値テーブルです。
     */
    private static final int[][] OP_GAME_TABLE = {
        {30, -12, 0, -1, -1, 0, -12, 30},
        {-12, -15, -3, -3, -3, -3, -15, -12},
        {0, -3, 0, -1, -1, 0, -3, 0},
        {-1, -3, -1, -1, -1, -1, -3, -1},
        {-1, -3, -1, -1, -1, -1, -3, -1},
        {0, -3, 0, -1, -1, 0, -3, 0},
        {-12, -15, -3, -3, -3, -3, -15, -12},
        {30, -12, 0, -1, -1, 0, -12, 30}
    };

    /**
     * 黒から見た中盤用の評価値テーブルです。
     */
    private static final int[][] MIDDLE_GAME_TABLE = {
        {30, -12, 0, -1, -1, 0, -12, 30},
        {-12, -15, -3, -3, -3, -3, -15, -12},
        {0, -3, 0, -1, -1, 0, -3, 0},
        {-1, -3, -1, -1, -1, -1, -3, -1},
        {-1, -3, -1, -1, -1, -1, -3, -1},
        {0, -3, 0, -1, -1, 0, -3, 0},
        {-12, -15, -3, -3, -3, -3, -15, -12},
        {30, -12, 0, -1, -1, 0, -12, 30}
    };


    /**
     * 黒から見た終盤用の評価値テーブルです。
     */
    private static final int[][] END_GAME_TABLE = {
        {30, -12, 0, -1, -1, 0, -12, 30},
        {-12, -15, -3, -3, -3, -3, -15, -12},
        {0, -3, 0, -1, -1, 0, -3, 0},
        {-1, -3, -1, -1, -1, -1, -3, -1},
        {-1, -3, -1, -1, -1, -1, -3, -1},
        {0, -3, 0, -1, -1, 0, -3, 0},
        {-12, -15, -3, -3, -3, -3, -15, -12},
        {30, -12, 0, -1, -1, 0, -12, 30}
    };


    /**
     * ゲーム木の葉を生成します。<br>
     * このクラスでは盤面の評価を行います。
     * @param level 先読み予定の何手前の盤面かを表す値
     * @param board 評価対象の盤面
     * @param stone 盤面を評価する側の石
     * @throws IllegalArgumentException 第一引数が負の値、盤面サイズが対応していない場合、または第三引数が黒石と白石以外の場合に発生
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     */
    public Leaf(int level, Board board, int stone) {
        super(level, board);

        // 対応するサイズの盤面かをチェック
        if((board.getWidth() != BOARD_WIDTH)
                || (board.getHeight() != BOARD_HEIGHT)) {
            throw new IllegalArgumentException("盤面サイズが対応していません。");
        }

        this.evalValue(stone);

    }


    /**
     * 盤面の評価値を計算します。<br>
     * 盤面上の各場所の評価値は、ゲームの進行状況に応じたテーブルから取得します。<br>
     * 自分の石が置いてあれば加算、相手の石が置いてあれば減算し、全体の評価値を計算します。
     * @param stone どちらの側で評価値を計算するかを表す値
     * @throws IllegalArgumentException 黒石と白石以外の石が与えらえた場合に発生
     */
    public void evalValue(int stone) {

        // 引数チェック
        if((stone != Board.BLACK_STONE)
                && (stone != Board.WHITE_STONE)) {
            throw new IllegalArgumentException("石は黒石か白石でなければなりません。");
        }


        // 評価値
        int result = 0;

        switch(this.stateJudgment()) {

            case OP_GAME :
                result = this.reference(OP_GAME);
    
                if(stone == Board.BLACK_STONE) {
                    result += this.getPuttablePlaceCount(stone);
                } else {
                    result -= this.getPuttablePlaceCount(stone);
                }
    
                break;
    
            case MIDDLE_GAME :
                result = this.reference(MIDDLE_GAME);
    
                if(stone == Board.BLACK_STONE) {
                    result += this.getPuttablePlaceCount(stone);
                } else {
                    result -= this.getPuttablePlaceCount(stone);
                }
    
                break;
    
            case END_GAME_1 :
                result = this.reference(END_GAME_1);
    
                if(stone == Board.BLACK_STONE) {
                    result += this.getPuttablePlaceCount(stone);
                } else {
                    result -= this.getPuttablePlaceCount(stone);
                }
    
                break;
    
            case END_GAME_2 :
                result = this.reference(END_GAME_2);
    
                result += (
                        this.getBoard().getStoneCount(Board.BLACK_STONE)
                        - this.getBoard().getStoneCount(Board.WHITE_STONE));
    
                if(stone == Board.BLACK_STONE) {
                    result += this.getPuttablePlaceCount(stone);
                } else {
                    result -= this.getPuttablePlaceCount(stone);
                }
    
                break;
            default :
                break;
        }

        this.setValue(result);

    }


    /**
     * 現在のゲームの進行状況を判断します。
     * @return ゲームの進行状況
     */
    private int stateJudgment() {

        // 現在の手数
        int moveCount = BOARD_WIDTH * BOARD_HEIGHT
            - this.getBoard().getStoneCount(Board.NOTHING)
            - 3; // CHECKSTYLE IGNORE THIS LINE

        if(moveCount >= 45) {   // CHECKSTYLE IGNORE THIS LINE
            // 45手目以降は終盤2
            return END_GAME_2;
        }

        if((moveCount >= 30) && (this.isEndGame1())) {  // CHECKSTYLE IGNORE THIS LINE
            // 30手以降で、条件を満たしていれば終盤1
            return END_GAME_1;
        }

        if(this.isMiddleGame()) {
            return MIDDLE_GAME;
        }

        return OP_GAME;

    }

    /**
     * 現在が中盤かどうかをチェックします。<br>
     * 石が盤面の一番外側に一つでもあれば中盤と判断します。
     * @return ゲームの中盤であれば<code>true</code>、それ以外は<code>false</code>
     */
    private boolean isMiddleGame() {

        // 1c～1fをチェック
        // 8c～8fをチェック
        for(int x = 2; x <= Leaf.BOARD_WIDTH - 3; x++) {    // CHECKSTYLE IGNORE THIS LINE
            int y1 = 0;
            int y2 = Leaf.BOARD_HEIGHT - 1;

            if(this.getBoard().getStone(x, y1) != Board.NOTHING) {
                return true;
            }

            if(this.getBoard().getStone(x, y2) != Board.NOTHING) {
                return true;
            }

        }


        // 2a～5aをチェック
        // 2h～5hをチェック
        for(int y = 2; y <= Leaf.BOARD_WIDTH - 3; y++) {    // CHECKSTYLE IGNORE THIS LINE
            int x1 = 0;
            int x2 = Leaf.BOARD_WIDTH - 1;

            if(this.getBoard().getStone(x1, y) != Board.NOTHING) {
                return true;
            }

            if(this.getBoard().getStone(x2, y) != Board.NOTHING) {
                return true;
            }

        }

        return false;

    }


    /**
     * 現在が終盤1の条件を満たしているかどうかをチェックします。<br>
     * 2つ以上の隅に同じ石が置かれていることが条件です。
     * @return 条件を満たしいている場合は<code>true</code>、それ以外は<code>false</code>
     */
    private boolean isEndGame1() {

        int white = 0;
        int black = 0;

        // CHECKSTYLE:OFF
        int[] stones = new int[4];
        stones[0] = this.getBoard().getStone(0, 0);
        stones[1] = this.getBoard().getStone(0, BOARD_HEIGHT - 1);
        stones[2] = this.getBoard().getStone(BOARD_WIDTH - 1, 0);
        stones[3] = this.getBoard().getStone(BOARD_WIDTH - 1, BOARD_HEIGHT - 1);
        // CHECKSTYLE:ON

        for(int stone : stones) {
            if(stone == Board.BLACK_STONE) {
                black++;
            } else if(stone == Board.WHITE_STONE) {
                white++;
            }
        }

        return ((black >= 2) || (white >= 2));
    }


    /**
     * 黒から見た評価値をテーブルを参照して導出します。
     * @param state 現在のゲームの進行状況
     * @return 評価値
     */
    private int reference(int state) {

        int[][] table;

        if(state == MIDDLE_GAME) {
            table = MIDDLE_GAME_TABLE;
        } else if(state == END_GAME_1) {
            table = END_GAME_TABLE;
        } else if(state == END_GAME_2) {
            table = END_GAME_TABLE;
        } else {
            table = OP_GAME_TABLE;
        }

        int result = 0;

        // 黒から見た評価値を計算
        for(int y = 0; y < Leaf.BOARD_HEIGHT; y++) {
            for(int x = 0; x < Leaf.BOARD_WIDTH; x++) {

                if(this.getBoard().getStone(x, y) == Board.BLACK_STONE) {
                    result += table[y][x];
                } else if(this.getBoard().getStone(x, y) == Board.WHITE_STONE) {
                    result -= table[y][x];
                }

            }
        }

        return result;

    }


    /**
     * 石を置ける場所の数を取得します。
     * @param stone 置く石
     * @return 石を置ける場所の数
     * @throws IllegalArgumentException 引数が白石でも黒石でもない場合に発生
     */
    private int getPuttablePlaceCount(int stone) {

        int result = 0;

        for(int y = 0; y < BOARD_HEIGHT; y++) {
            for(int x = 0; x < BOARD_WIDTH; x++) {

                if(this.getBoard().canPut(x, y, stone)) {
                    result++;
                }

            }
        }

        return result;

    }
}
