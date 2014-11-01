package ai;

import core.Board;
import core.NextMove;

/**
 * ゲームを行うプレイヤーを表すインターフェースです.
 * @author tanabe
 *
 */
public interface GamePlayer {

    /**
     * 次の手を取得します.
     * パスの場合は<code>null</code>を返します.
     * 座標が負の場合はゲームを中断します.
     * @param stone 置く石
     * @param board リバーシの盤面
     * @return 次の手
     */
    NextMove think(int stone, Board board);

}
