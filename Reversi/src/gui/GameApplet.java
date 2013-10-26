package gui;

import ai.GamePlayer;

import ai.PlayerFactory;
import output.GameViewer;
import input.NextMoveInputter;
import core.Board;
import core.GameManager;
import core.NextMove;
import processing.core.PApplet;

/**
 * ゲームを描画するクラスです。
 * @author tanabe
 *
 */
public class GameApplet extends PApplet
	implements NextMoveInputter, GameViewer {

	/**
	 * シリアルバージョンです。
	 */
	private static final long serialVersionUID = 6930504402437251055L;

	/**
	 * 「ゲームモード選択」を表す定数です。
	 */
	private static final int MODE_SELECT = 0;

	/**
	 * 「ゲーム中」を表す定数です。
	 */
	private static final int GAME_PLAYING = 1;

	/**
	 * 「ゲーム結果」を表す定数です。
	 */
	private static final int GAME_RESULT = 2;

	/**
	 * 描画領域の横幅です。
	 */
	public static final int WIDTH = 600;

	/**
	 * 描画領域の縦幅です。
	 */
	public static final int HEIGHT = 500;

	/**
	 * 「PASS」や「NEW GAME」など、決定に使用するボタンの番号です。
	 */
	private static final int OK_BUTTON = 0;

	/**
	 * 「GIVE UP」など、キャンセルに使用するボタンの番号です。
	 */
	private static final int CANCEL_BUTTON = 1;

	/**
	 * 現在の状態を表す変数です。
	 */
	private int state;

	/**
	 * リバーシの盤面ビューワーです。
	 */
	private BoardViewer board;

	/**
	 * ボタンです。
	 */
	private Button[] buttons;

	/**
	 * ゲームメッセージです。
	 */
	private MessageBox message;

	/**
	 * 次に置く石です。
	 */
	private int nextStone;

	/**
	 * 次の手です。
	 */
	private NextMove nextMove;

	/**
	 * ゲームを管理するマネージャーです。
	 */
	private GameManager manager;

	/**
	 * プレイヤーを生成するファクトリーです。
	 */
	private PlayerFactory factory;


	@Override
	public void setup() {

		this.buttons = new Button[7];
		for(int no = 0; no < this.buttons.length; no++) {
			this.buttons[no] = new Button(170, 50);
			this.buttons[no].setPressed(false);
		}

		this.board = new BoardViewer(8, 8);
		this.board.setPosition(10, 10);

		this.message = new MessageBox(580, 70);

		this.size(GameApplet.WIDTH, GameApplet.HEIGHT);
		this.smooth();

		this.changeState(GameApplet.MODE_SELECT);

		this.nextStone = -1;
		this.nextMove = null;

		this.manager = null;
		this.factory = new PlayerFactory(this);

	}


	@Override
	public void draw() {

		this.background(255, 255, 255);

		switch(this.state) {

		case GameApplet.MODE_SELECT :

			for(Button button : this.buttons) {
				button.draw(this);
			}

			this.message.draw(this);

			break;

		case GameApplet.GAME_PLAYING :

			this.buttons[GameApplet.OK_BUTTON].draw(this);
			this.buttons[GameApplet.CANCEL_BUTTON].draw(this);
			this.board.draw(this);
			this.message.draw(this);

			if(this.manager.isFinish()) {
				this.changeState(GameApplet.GAME_RESULT);
			}

			break;

		case GameApplet.GAME_RESULT :

			this.manager.viewGameResult();

			this.buttons[GameApplet.OK_BUTTON].draw(this);
			this.board.draw(this);
			this.message.draw(this);

			break;

		}

	}


	@Override
	public void mousePressed() {

		switch(this.state) {

		case GameApplet.MODE_SELECT :

			int no1 = -1;
			int no2 = -1;
			int delayTime = 0;

			// TODO プレイヤーを充実させる

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

			if(no2 >= 0) {

				Board b = new Board();
				GamePlayer p1 = this.factory.create(no1, delayTime);
				GamePlayer p2 = this.factory.create(no2, delayTime);
				this.manager = new GameManager(p1, p2, b, this);

				this.manager.gameStart();

				this.changeState(GameApplet.GAME_PLAYING);

			}

			break;

		case GameApplet.GAME_PLAYING :

			int cellX = this.board.getCellX(this.mouseX);
			int cellY = this.board.getCellY(this.mouseY);

			if((cellX >= 0) && (cellY >= 0)) {

				// マウスカーソルが盤面上
				if((this.nextStone == Board.BLACK_STONE) || (this.nextStone == Board.WHITE_STONE)) {
					this.nextMove = new NextMove(cellX, cellY, this.nextStone);
				}

			} else if(this.buttons[GameApplet.CANCEL_BUTTON].isContain(this.mouseX, this.mouseY)) {

				// ゲーム終了を選択
				if(!this.buttons[GameApplet.CANCEL_BUTTON].getPressed()) {
					this.nextMove = new NextMove(-1, -1, this.manager.getCurrentStone());
					this.buttons[GameApplet.CANCEL_BUTTON].setPressed(true);
				}

			} else if(this.buttons[GameApplet.OK_BUTTON].isContain(this.mouseX, this.mouseY)) {

				// パス
				this.buttons[GameApplet.OK_BUTTON].setPressed(true);

			}

			break;

		case GameApplet.GAME_RESULT :

			if(this.buttons[0].isContain(this.mouseX, this.mouseY)) {
				// 「NEW GAME」を選択
				this.changeState(GameApplet.MODE_SELECT);
			}

			break;
		}

	}


	@Override
	public void mouseMoved() {

		if(this.state == GameApplet.GAME_PLAYING) {
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
	 * 盤面を表示します。
	 * @param board 表示する盤面
	 * @throws NullPointerException 引数が<code>null</code>の場合に発生
	 * @throws IllegalArgumentException ビューワーとサイズが異なる盤面が与えられたときに発生
	 */
	@Override
	public void view(Board board) {

		// 引数チェック
		if(board == null) {
			throw new NullPointerException("盤面をnullにすることはできません。");
		}
		if((this.board.getBoardWidth() != board.getWidth())
				|| (this.board.getBoardHeight() != board.getHeight())) {
			throw new IllegalArgumentException("盤面サイズがビューワーと異なります。");
		}

		for(int y = 0; y < this.board.getBoardHeight(); y++) {
			for(int x = 0; x < this.board.getBoardWidth(); x++) {

				this.board.setStone(x, y, board.getStone(x, y));

			}
		}

	}


	/**
	 * メッセージを表示します。
	 * @throws NullPointerException 引数が<code>null</code>の場合に発生
	 */
	@Override
	public void view(String message) {

		// 引数チェック
		if(message == null) {
			throw new NullPointerException("メッセージをnullにすることはできません。");
		}

		this.message.setMessage(message);

	}


	@Override
	public NextMove getNextMove(int stone, String message) {

		this.view(message);

		return this.getNextMove(stone);
	}


	@Override
	public NextMove getNextMove(int stone) {

		this.nextStone = stone;

		this.buttons[GameApplet.CANCEL_BUTTON].setText("GIVE UP");

		while(this.nextMove == null) {
		}

		this.buttons[GameApplet.CANCEL_BUTTON].setText("");

		NextMove result = this.nextMove;

		this.nextStone = -1;
		this.nextMove = null;

		return result;

	}


	/**
	 * ゲームの状態を変更し、ボタンを初期化します。
	 * @param state 新しい状態
	 * @throws IllegalArgumentException 定義されていない状態を指定した場合に発生
	 */
	private void changeState(int state) {

		this.buttonSetup(state);
		this.state = state;

	}


	/**
	 * ゲームの状態に合わせてボタンを初期化します。
	 * @param state ゲームの状態
	 * @throws IllegalArgumentException 定義されていない状態を指定した場合に発生
	 */
	private void buttonSetup(int state) {

		switch(state) {

		case GameApplet.MODE_SELECT :

			this.buttons[0].setPosition(100, 100);

			for(int no = 1; no < this.buttons.length; no++) {
				this.buttons[no].setPosition(100 + 210 * ((no + 1) % 2), 120 + 70 * ((no + 1) / 2));
				this.buttons[no].setText("not used");
				this.buttons[no].setPressed(false);
			}

			this.buttons[0].setText("P1 VS P2");
			this.buttons[1].setText("P1 VS CP(LV1)");
			this.buttons[2].setText("CP(LV1) VS P1");
			this.buttons[3].setText("P1 VS CP(LV2)");
			this.buttons[4].setText("CP(LV2) VS P1");
			this.buttons[5].setText("P1 VS CP(LV3)");
			this.buttons[6].setText("CP(LV3) VS P1");

			this.message.setPosition(10, 420);
			this.message.setMessage("Please choose a player.");

			break;

		case GameApplet.GAME_PLAYING :

			this.message.setPosition(10, 420);

			// パスするとき以外はボタンの文字なし
			this.buttons[GameApplet.OK_BUTTON].setText("");
			this.buttons[GameApplet.OK_BUTTON].setPosition(420, 10);
			this.buttons[GameApplet.OK_BUTTON].setPressed(false);

			// 自分の番以外はボタンの文字なし
			this.buttons[GameApplet.CANCEL_BUTTON].setText(" ");
			this.buttons[GameApplet.CANCEL_BUTTON].setPosition(420, 360);
			this.buttons[GameApplet.CANCEL_BUTTON].setPressed(false);

			break;

		case GameApplet.GAME_RESULT :

			this.buttons[OK_BUTTON].setText("NEW GAME");
			this.buttons[OK_BUTTON].setPosition(420, 10);
			this.buttons[OK_BUTTON].setPressed(false);

			break;

		default :
			throw new IllegalArgumentException("定義されていない状態です。");

		}

	}


	@Override
	public void dispImportantMessage(String message) {

		this.view(message);

		while(!this.buttons[GameApplet.OK_BUTTON].getPressed()) {
			this.buttons[GameApplet.OK_BUTTON].setText("PASS");
		}

		this.buttons[GameApplet.OK_BUTTON].setPressed(false);
		this.buttons[GameApplet.OK_BUTTON].setText("");

	}


}
