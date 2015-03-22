package gui;

import static gui.GuiConstants.INPUT_INTERVAL_MS;
import static gui.GuiConstants.LayoutConstraints.GAME_AREA_HEIGHT;
import static gui.GuiConstants.LayoutConstraints.GAME_AREA_WIDTH;
import static gui.GuiConstants.LayoutConstraints.BACKGROUND_COLOR_R;
import static gui.GuiConstants.LayoutConstraints.BACKGROUND_COLOR_G;
import static gui.GuiConstants.LayoutConstraints.BACKGROUND_COLOR_B;
import static core.GameConstants.RuleConstants.BOARD_HEIGHT;
import static core.GameConstants.RuleConstants.BOARD_WIDTH;
import input.NextMoveSelector;
import output.GameViewer;
import processing.core.PApplet;
import util.ArgumentCheckUtil;
import ai.GamePlayer;
import ai.PlayerFactory;
import core.Board;
import core.GameManager;
import core.NextMove;
import core.Cell;

/**
 * ゲームを描画するクラスです.
 * @author tanabe
 *
 */
public class GameApplet extends PApplet
    implements NextMoveSelector, GameViewer {

    /**
     * シリアルバージョンです.
     */
    private static final long serialVersionUID = 6930504402437251055L;

    /**
     * ゲームの場面の種類を表す列挙です.
     * @author tanabe
     *
     */
    private enum SCENE {
        /** ゲームモード選択. */
        MODE_SELECT,
        /** ゲーム中. */
        GAME_PLAYING,
        /** ゲーム結果. */
        GAME_RESULT,
    }

    /**
     * 描画領域の横幅です.
     */
    public static final int WIDTH = GAME_AREA_WIDTH;

    /**
     * 描画領域の縦幅です.
     */
    public static final int HEIGHT = GAME_AREA_HEIGHT;
    
    /**
     * 「PASS」や「NEW GAME」など、決定に使用するボタンの番号です.
     */
    private static final int OK_BUTTON = 0;

    /**
     * 「GIVE UP」など、キャンセルに使用するボタンの番号です.
     */
    private static final int CANCEL_BUTTON = 1;

    /**
     * 現在の状態を表す変数です.
     */
    private SCENE scene;

    /**
     * リバーシの盤面ビューワーです.
     */
    private BoardViewer board;

    /**
     * ボタンです.
     */
    private Button[] buttons;

    /**
     * ゲームメッセージです.
     */
    private MessageBox message;

    /**
     * 次に置く石です.
     */
    private Cell nextStone;

    /**
     * 次の手です.
     */
    private NextMove nextMove;

    /**
     * ゲームを管理するマネージャーです.
     */
    private GameManager manager;

    /**
     * プレイヤーを生成するファクトリーです.
     */
    private PlayerFactory factory;


    @Override
    public void setup() {

        this.buttons = new Button[7];    // CHECKSTYLE IGNORE THIS LINE
        for(int no = 0; no < this.buttons.length; no++) {
            this.buttons[no] = new Button(170, 50);    // CHECKSTYLE IGNORE THIS LINE
            this.buttons[no].setPressed(false);
        }

        this.board = new BoardViewer(BOARD_WIDTH, BOARD_HEIGHT);
        this.board.setPosition(10, 10);         // CHECKSTYLE IGNORE THIS LINE
        this.message = new MessageBox(580, 70); // CHECKSTYLE IGNORE THIS LINE

        this.size(GAME_AREA_WIDTH, GAME_AREA_HEIGHT);
        this.smooth();

        this.changeScene(SCENE.MODE_SELECT);

        this.nextStone = Cell.NOTHING;
        this.nextMove = null;

        this.manager = null;
        this.factory = new PlayerFactory(this);

    }


    @Override
    public void draw() {

        this.background(BACKGROUND_COLOR_R,
                        BACKGROUND_COLOR_G,
                        BACKGROUND_COLOR_B);
        
        switch(this.scene) {

            case MODE_SELECT :
    
                for(Button button : this.buttons) {
                    button.draw(this);
                }
    
                this.message.draw(this);
    
                break;
    
            case GAME_PLAYING :
    
                this.buttons[GameApplet.OK_BUTTON].draw(this);
                this.buttons[GameApplet.CANCEL_BUTTON].draw(this);
                this.board.draw(this);
                this.message.draw(this);
    
                if(this.manager.isFinish()) {
                    this.changeScene(SCENE.GAME_RESULT);
                }
    
                break;
    
            case GAME_RESULT :
    
                this.manager.viewGameResult();
    
                this.buttons[GameApplet.OK_BUTTON].draw(this);
                this.board.draw(this);
                this.message.draw(this);
    
                break;
    
            default :
                throw new UnsupportedOperationException();
        }

    }


    @Override
    public void mousePressed() {

        switch(this.scene) {

            case MODE_SELECT :
                this.selectMode();
                break;
    
            case GAME_PLAYING :
                this.playGame();
                break;
    
            case GAME_RESULT :
    
                if(this.buttons[0].isContain(this.mouseX, this.mouseY)) {
                    // 「NEW GAME」を選択
                    this.changeScene(SCENE.MODE_SELECT);
                }
    
                break;
    
            default :
                throw new UnsupportedOperationException();
        }

    }
    
    /**
     * ゲーム選択処理.
     */
    private void selectMode() {
        int no1 = -1;
        int no2 = -1;
        int delayTime = 0;

        // TODO プレイヤーを充実させる

        // CHECKSTYLE:OFF
        if(this.buttons[0].isContain(this.mouseX, this.mouseY)) {
            // P1 VS P2
            no1 = PlayerFactory.HUMAN;
            no2 = PlayerFactory.HUMAN;
        } else if(this.buttons[1].isContain(this.mouseX, this.mouseY)) {
            // P1 VS CP(LV1)
            no1 = PlayerFactory.HUMAN;
            no2 = PlayerFactory.RANDOM;
            delayTime = 500;
        } else if(this.buttons[2].isContain(this.mouseX, this.mouseY)) {
            // CP(LV1) VS P1
            no1 = PlayerFactory.RANDOM;
            no2 = PlayerFactory.HUMAN;
            delayTime = 500;
        } else if(this.buttons[3].isContain(this.mouseX, this.mouseY)) {
            // P1 VS CP(LV2)
            no1 = PlayerFactory.HUMAN;
            no2 = PlayerFactory.MAXIMUM;
            delayTime = 500;
        } else if(this.buttons[4].isContain(this.mouseX, this.mouseY)) {
            // CP(LV2) VS P1
            no1 = PlayerFactory.MAXIMUM;
            no2 = PlayerFactory.HUMAN;
            delayTime = 500;
        } else if(this.buttons[5].isContain(this.mouseX, this.mouseY)) {
            // P1 VS CP(LV3)
            no1 = PlayerFactory.HUMAN;
            no2 = PlayerFactory.TABLE;
            delayTime = 400;
        } else if(this.buttons[6].isContain(this.mouseX, this.mouseY)) {
            // CP(LV3) VS P1
            no1 = PlayerFactory.TABLE;
            no2 = PlayerFactory.HUMAN;
            delayTime = 500;
        }
        // CHECKSTYLE:ON

        if(no2 >= 0) {

            Board b = new Board();
            GamePlayer p1 = this.factory.create(no1, delayTime);
            GamePlayer p2 = this.factory.create(no2, delayTime);
            this.manager = new GameManager(p1, p2, b, this);

            this.manager.gameStart();

            this.changeScene(SCENE.GAME_PLAYING);

        }
    }
    
    /**
     * ゲーム中の処理.
     */
    private void playGame() {
        int cellX = this.board.getCellX(this.mouseX);
        int cellY = this.board.getCellY(this.mouseY);

        if((cellX >= 0) && (cellY >= 0)) {

            // マウスカーソルが盤面上
            if((this.nextStone == Cell.BLACK)
                    || (this.nextStone == Cell.WHITE)) {
                this.nextMove = new NextMove(cellX, cellY, this.nextStone);
            }

        } else if(this.buttons[GameApplet.CANCEL_BUTTON]
                    .isContain(this.mouseX, this.mouseY)) {

            // ゲーム終了を選択
            if(!this.buttons[GameApplet.CANCEL_BUTTON].getPressed()) {
                this.nextMove = new NextMove(-1, -1, this.manager.getCurrentStone());
                this.buttons[GameApplet.CANCEL_BUTTON].setPressed(true);
            }

        } else if(this.buttons[GameApplet.OK_BUTTON].isContain(this.mouseX, this.mouseY)) {

            // パス
            this.buttons[GameApplet.OK_BUTTON].setPressed(true);

        }
    }


    @Override
    public void mouseMoved() {

        if(this.scene == SCENE.GAME_PLAYING) {
            int cellX = this.board.getCellX(this.mouseX);
            int cellY = this.board.getCellY(this.mouseY);

            if((cellX < 0) || (cellY < 0)) {
                this.board.setCursorVisible(false);
            } else {
                this.board.setCursorVisible(true);
                this.board.setCursorPosition(cellX, cellY);
            }
        }

    }


    /**
     * 盤面を表示します.
     * @param board 表示する盤面
     * @throws IllegalArgumentException
     *      引数がnull、もしくはビューワーとサイズが異なる盤面が与えられたときに発生
     */
    @Override
    public void view(Board board) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNull(board);
        if((this.board.getBoardWidth() != board.getWidth())
                || (this.board.getBoardHeight() != board.getHeight())) {
            throw new IllegalArgumentException("盤面サイズがビューワーと異なります.");
        }

        for(int y = 0; y < this.board.getBoardHeight(); y++) {
            for(int x = 0; x < this.board.getBoardWidth(); x++) {

                this.board.setStone(x, y, board.getStone(x, y));

            }
        }

    }


    /**
     * メッセージを表示します.
     * @param message メッセージ
     * @throws IllegalArgumentException 引数が<code>null</code>の場合に発生
     */
    @Override
    public void view(String message) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNull(message);

        this.message.setMessage(message);

    }


    /**
     * 次の手を選びます.
     * @param cell 置く石
     * @param message 表示するメッセージ
     * @return 次の手
     */
    @Override
    public NextMove select(Cell cell, String message) {

        this.view(message);

        return this.select(cell);
    }


    /**
     * 次の手を選びます.
     * @param cell 置く石
     * @return 次の手
     */
    @Override
    public NextMove select(Cell cell) {

        this.nextStone = cell;

        this.buttons[GameApplet.CANCEL_BUTTON].setText("GIVE UP");

        while(true) {
            if(this.nextMove != null) {
                break;
            }
            
            try {
                Thread.sleep(INPUT_INTERVAL_MS);
            } catch(Exception e) {  // CHECKSTYLE IGNORE THIS LINE
                // 待機するだけなので例外は気にしない.
            }
        }

        this.buttons[GameApplet.CANCEL_BUTTON].setText("");

        NextMove result = this.nextMove;

        this.nextStone = Cell.NOTHING;
        this.nextMove = null;

        return result;

    }


    /**
     * ゲームの場面を変更し、ボタンを初期化します.
     * @param scene 新しい場面
     */
    private void changeScene(SCENE scene) {

        this.buttonSetup(scene);
        this.scene = scene;

    }


    /**
     * ゲームの状態に合わせてボタンを初期化します.
     * @param scene ゲームの状態
     * @throws IllegalArgumentException 定義されていない状態を指定した場合に発生
     */
    private void buttonSetup(SCENE scene) {

        switch(scene) {

            case MODE_SELECT :

                this.buttons[0].setPosition(100, 100);  // CHECKSTYLE IGNORE THIS LINE

                for(int no = 1; no < this.buttons.length; no++) {
                    // CHECKSTYLE:OFF
                    this.buttons[no].setPosition(100 + 210 * ((no + 1) % 2),
                                                 120 + 70 * ((no + 1) / 2));
                    // CHECKSTYLE:ON
                    this.buttons[no].setText("not used");
                    this.buttons[no].setPressed(false);
                }

                // CHECKSTYLE:OFF
                this.buttons[0].setText("P1 VS P2");
                this.buttons[1].setText("P1 VS CP(LV1)");
                this.buttons[2].setText("CP(LV1) VS P1");
                this.buttons[3].setText("P1 VS CP(LV2)");
                this.buttons[4].setText("CP(LV2) VS P1");
                this.buttons[5].setText("P1 VS CP(LV3)");
                this.buttons[6].setText("CP(LV3) VS P1");
                this.message.setPosition(10, 420);
                this.message.setMessage("Please choose a player.");
                // CHECKSTYLE:ON
                
                break;
    
            case GAME_PLAYING :
    
                this.message.setPosition(10, 420);    // CHECKSTYLE IGNORE THIS LINE
    
                // パスするとき以外はボタンの文字なし
                this.buttons[GameApplet.OK_BUTTON].setText("");
                this.buttons[GameApplet.OK_BUTTON]
                        .setPosition(420, 10);    // CHECKSTYLE IGNORE THIS LINE
                this.buttons[GameApplet.OK_BUTTON].setPressed(false);
    
                // 自分の番以外はボタンの文字なし
                this.buttons[GameApplet.CANCEL_BUTTON].setText(" ");
                this.buttons[GameApplet.CANCEL_BUTTON]
                        .setPosition(420, 360);    // CHECKSTYLE IGNORE THIS LINE
                this.buttons[GameApplet.CANCEL_BUTTON].setPressed(false);
    
                break;
    
            case GAME_RESULT :
    
                this.buttons[OK_BUTTON].setText("NEW GAME");
                this.buttons[OK_BUTTON]
                        .setPosition(420, 10);    // CHECKSTYLE IGNORE THIS LINE
                this.buttons[OK_BUTTON].setPressed(false);
    
                break;
    
            default :
                throw new IllegalArgumentException("定義されていない状態です.");

        }

    }


    @Override
    public void dispImportantMessage(String message) {

        this.view(message);

        this.buttons[GameApplet.OK_BUTTON].setText("PASS");
        while(!this.buttons[GameApplet.OK_BUTTON].getPressed()) {
            try {
                Thread.sleep(INPUT_INTERVAL_MS);
            } catch(Exception e) {  // CHECKSTYLE IGNORE THIS LINE
                // 待機するだけなので例外は気にしない.
            }
        }

        this.buttons[GameApplet.OK_BUTTON].setPressed(false);
        this.buttons[GameApplet.OK_BUTTON].setText("");

    }


}
