package gui;

/**
 * GUI要素に関する定数を管理するクラスです.
 * @author tanabe
 *
 */
public final class GuiConstants {

    /**
     * コンストラクタ.
     * 定数クラスのため使用不可.
     */
    private GuiConstants() { }
    
    /**　入力の確認間隔(ms). */
    public static final int INPUT_INTERVAL_MS = 100;
    
    /**
     * 並びに関する定数を管理するクラスです.
     * @author tanabe
     *
     */
    public static final class LayoutConstraints {
        
        /**
         * コンストラクタ.
         * 定数クラスのため使用不可.
         */
        private LayoutConstraints() { }
        
        /** ゲームの描画領域の横幅です. */
        public static final int GAME_AREA_WIDTH = 600;
        /** ゲームの描画領域の縦幅です. */
        public static final int GAME_AREA_HEIGHT = 500;
        /** マス目一つ当たりの表示サイズ(横幅)です. */
        public static final int CELL_SIZE_X = 50;
        /** マス目一つ当たりの表示サイズ(縦幅)です. */
        public static final int CELL_SIZE_Y = 50;
        /** 各マス目の縁の幅です. */
        public static final int CELL_EDGE_WIDTH = 1;
        /** 石の縁の幅です. */
        public static final int STONE_EDGE_WIDTH = 1;
        /** 背景色(赤). */
        public static final int BACKGROUND_COLOR_R = 255;
        /** 背景色(緑). */
        public static final int BACKGROUND_COLOR_G = 255;
        /** 背景色(青). */
        public static final int BACKGROUND_COLOR_B = 255;
        
    }
    
}
