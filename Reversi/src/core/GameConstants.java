package core;

/**
 * ゲームに関する定数を管理するクラスです.
 * @author tanabe
 *
 */
public final class GameConstants {

    /**
     * コンストラクタ.
     * 定数クラスのため使用不可.
     */
    private GameConstants() { }
    
    /**
     * ゲームのルールに関する定数を管理するクラスです.
     * @author tanabe
     *
     */
    public static final class RuleConstants {
        
        /**
         * コンストラクタ.
         * 定数クラスのため使用不可.
         */
        private RuleConstants() { }
        
        /**
         * ゲームの盤面の横幅です.
         */
        public static final int BOARD_WIDTH = 8;
        
        /**
         * ゲームの盤面の縦幅です.
         */
        public static final int BOARD_HEIGHT = 8;
        
        /**
         * ゲームを行うのに必要なプレイヤーの人数です.
         */
        public static final int PLAYER_COUNT = 2;
    }
}
