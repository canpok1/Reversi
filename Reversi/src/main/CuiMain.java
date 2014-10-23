package main;

import output.ConsoleViewer;
import output.GameViewer;
import input.ConsoleSelector;
import input.NextMoveSelector;
import ai.GamePlayer;
import ai.Human;
import ai.RandomSelect;
import core.Board;
import core.GameManager;

/**
 * コンソールで実行するためのメインクラスです。
 * @author tanabe
 *
 */
public class CuiMain {

	/**
	 * プログラムのエントリポイントです。
	 * @param args
	 */
	public static void main(String[] args) {

		new CuiMain();

	}


	/**
	 * クラスを生成します。
	 */
	public CuiMain() {

		Board board = new Board();

		GameViewer viewer = new ConsoleViewer();
		NextMoveSelector inputter = new ConsoleSelector();

		GamePlayer p1 = new Human(inputter);
		GamePlayer p2 = new RandomSelect();

		GameManager manager = new GameManager(p1, p2, board, viewer);

		manager.gameStart();

		while(!manager.isFinish()) {
		}

		manager.viewGameResult();

		viewer.view("Exit");

	}
}
