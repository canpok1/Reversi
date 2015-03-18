package ai;

import java.util.ArrayList;

import util.ArgumentCheckUtil;
import core.Board;
import core.NextMove;
import core.Cell;

/**
 * 置ける場所にランダムに置いていくプレイヤーです.
 * @author tanabe
 *
 */
public class RandomSelect implements GamePlayer {

    /**
     * 石を置くまでの待ち時間(ms)です.
     */
    private final int delayTime;
    
    /**
     * 待ち時間のデフォルト値.
     */
    private static final int DEFAULT_DELAY_TIME = 500;

    /**
     * プレイヤーを生成します.
     * @param delayTime 石を置くまでの待ち時間(ms).
     */
    public RandomSelect(int delayTime) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNegativeValue(delayTime);

        this.delayTime = delayTime;

    }

    /**
     * プレイヤーを生成します.
     * 石を置くまでの待ち時間は500msです.
     */
    public RandomSelect() {
        this(DEFAULT_DELAY_TIME);
    }

    /**
     * {@inheritDoc}
     * @param cell {@inheritDoc}
     * @param board {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException 引数が<code>null</code>の場合に発生
     * @throws IllegalArgumentException 対応する石がない場合に発生
     */
    @Override
    public NextMove think(Cell cell, Board board) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNothing(cell);
        ArgumentCheckUtil.checkNotNull(board);

        // 置ける場所
        ArrayList<int[]> points = new ArrayList<int[]>();

        Board copyBoard = new Board(board);

        for(int y = 0; y < board.getHeight(); y++) {
            for(int x = 0; x < board.getWidth(); x++) {

                if(copyBoard.putStone(x, y, cell)) {
                    points.add(new int[]{x, y});
                    copyBoard = new Board(board);
                }

            }
        }

        if(points.size() == 0) {
            // 置ける場所がない場合はパス
            return null;
        }

        // 置ける場所からランダムに一か所選択する
        int select = (int)Math.floor(Math.random() * points.size());
        int[] point = points.get(select);

        NextMove move = new NextMove(point[0], point[1], cell);

        try {
            Thread.sleep(this.delayTime);
        } catch (InterruptedException e) {  // CHECKSTYLE IGNORE THIS LINE
        }

        return move;
    }

}
