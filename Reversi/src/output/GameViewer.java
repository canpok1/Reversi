package output;

import core.Board;

/**
 * ゲームの情報を表示するためのインターフェースです.
 * @author tanabe
 *
 */
public interface GameViewer {

    /**
     * 盤面情報を表示します.
     * @param board ゲームの盤面
     */
    void view(Board board);

    /**
     * メッセージを表示します.
     * @param message メッセージ
     */
    void view(String message);

}
