package core;

import static core.GameConstants.RuleConstants.PLAYER_COUNT;

import java.util.ArrayList;
import java.util.List;

import output.GameViewer;
import util.ArgumentCheckUtil;
import ai.GamePlayer;

/**
 * ゲームを管理するクラスです.<br>
 * 盤面の初期化やゲームの勝敗判定等を行います.
 * @author tanabe
 *
 */
public class GameManager implements Runnable {

    /**
     * ゲームを行うプレイヤーです.
     */
    private final GamePlayer[] players;

    /**
     * プレイヤーが使用する石です.
     */
    private final Cell[] cells;

    /**
     * ゲームの盤面です.
     */
    private final Board board;

    /**
     * ゲーム情報を表示するビューワーです.
     */
    private final GameViewer viewer;

    /**
     * 現在の手番プレイヤーを表す番号です.
     */
    private int currentPlayerNo;

    /**
     * 連続でパスを行った回数です.
     */
    private int passCount;

    /**
     * ゲームの記録です.
     */
    private final List<NextMove> gameRecord;

    /**
     * ゲームループ用のスレッドです.
     */
    private Thread gameThread;

    /**
     * ゲームの終了を示すフラグです.
     */
    private boolean gameoverFlag;

    /**
     * ゲームの中断を示すフラグです.
     */
    private boolean quitFlag;

    /**
     * ゲームを生成します.
     * @param p1 プレイヤー1(先行)
     * @param p2 プレイヤー2(後攻)
     * @param board ゲームの盤面
     * @param viewer ゲーム情報を表示するビューワー
     */
    public GameManager(GamePlayer p1, GamePlayer p2, Board board, GameViewer viewer) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNull(p1);
        ArgumentCheckUtil.checkNotNull(p2);
        ArgumentCheckUtil.checkNotNull(board);
        ArgumentCheckUtil.checkNotNull(viewer);

        this.players = new GamePlayer[PLAYER_COUNT];
        this.players[0] = p1;
        this.players[1] = p2;

        // 先行・後攻の色設定
        this.cells = new Cell[PLAYER_COUNT];
        this.cells[0] = Cell.BLACK;
        this.cells[1] = Cell.WHITE;

        this.gameRecord = new ArrayList<NextMove>();
        this.board = board;
        this.viewer = viewer;

    }


    /**
     * ゲームの初期化を行います.
     */
    private void initialize() {

        // 石を除去
        for(int y = 0; y < this.board.getHeight(); y++) {
            for(int x = 0; x < this.board.getWidth(); x++) {

                this.board.initStone(x, y, Cell.NOTHING);

            }
        }

        // CHECKSTYLE:OFF
        // 初期配置
        this.board.initStone(3, 3, Cell.WHITE);
        this.board.initStone(4, 4, Cell.WHITE);
        this.board.initStone(3, 4, Cell.BLACK);
        this.board.initStone(4, 3, Cell.BLACK);
        // CHECKSTYLE:ON
        
        this.currentPlayerNo = 0;
        this.passCount = 0;
        this.gameoverFlag = false;
        this.quitFlag = false;

    }


    /**
     * ゲームを更新します.
     */
    private void update() {

        this.viewer.view(this.board);

        // 次の手を取得
        Cell cell = this.cells[this.currentPlayerNo];
        GamePlayer player = this.players[this.currentPlayerNo];
        NextMove move = player.think(cell, this.board);

        if(move == null) {

            // パス

            // 置く場所確認用の盤面
            Board checkBoard = new Board(this.board);

            // 置く場所がないかをチェック
            for(int y = 0; y < this.board.getHeight(); y++) {
                for(int x = 0; x < this.board.getWidth(); x++) {

                    if(checkBoard.putStone(x, y, cell)) {
                        // 石を置けるのにパスをした場合はルール違反
                        this.viewer.view("You cannot pass.");

                        // ゲーム中断
                        this.quitFlag = true;
                        return;

                    }

                }
            }

            this.passCount++;

            if(this.passCount >= PLAYER_COUNT) {
                // 全プレイヤーがパスをした
                this.gameoverFlag = true;
            }

        } else if((move.getX() < 0) || (move.getY() < 0)) {

            // ゲーム中断
            this.quitFlag = true;
            return;

        } else {

            // 石を置く
            this.board.putStone(move.getX(), move.getY(), move.getStone());
            this.gameRecord.add(move);
            this.passCount = 0;

            if(this.cells[this.currentPlayerNo] == Cell.WHITE) {
                this.viewer.view("White player put " + move);
            } else {
                this.viewer.view("Black player put " + move);
            }

        }

        if(this.board.getStoneCount(Cell.NOTHING) <= 0) {
            // すべてのマスに石がある
            this.gameoverFlag = true;
        } else {
            // プレイヤー切り替え
            this.currentPlayerNo = (this.currentPlayerNo + 1) % PLAYER_COUNT;
        }

    }


    /**
     * ゲームが終了かをチェックします.
     * @return ゲーム終了ならば<code>true</code>、それ以外は<code>false</code>
     */
    public boolean isFinish() {
        return (this.gameThread == null);
    }


    /**
     * ゲームの結果を表示します.
     */
    public void viewGameResult() {

        int whiteCount = this.board.getStoneCount(Cell.WHITE);
        int blackCount = this.board.getStoneCount(Cell.BLACK);


        if(!this.isFinish()) {
            this.viewer.view("Now playing");
        } else if(this.quitFlag) {
            this.viewer.view("Game is stopped");
        } else if(whiteCount > blackCount) {
            this.viewer.view("White player win !!! (" + blackCount + " and " + whiteCount + ")");
        } else if(whiteCount < blackCount) {
            this.viewer.view("Black player win !!! (" + blackCount + " and " + whiteCount + ")");
        } else {
            this.viewer.view("Draw ... (" + blackCount + " and " + whiteCount + ")");
        }

        this.viewer.view(this.board);

    }


    /**
     * 現在の手番プレイヤーの石を取得します.
     * @return 手番プレイヤーの石
     */
    public Cell getCurrentStone() {
        return this.cells[this.currentPlayerNo];
    }


    /**
     * ゲームを開始します.
     */
    public void gameStart() {

        this.gameThread = new Thread(this);
        this.gameThread.start();

    }


    /**
     * ゲームループの処理です.
     * この処理は独立したスレッドで行われます.
     */
    @Override
    public void run() {

        this.initialize();

        while(!(this.gameoverFlag || this.quitFlag)) {

            this.update();

        }

        this.gameThread = null;

    }

}
