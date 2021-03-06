package main;

import input.ConsoleSelector;
import input.NextMoveSelector;
import output.ConsoleViewer;
import output.GameViewer;
import ai.GamePlayer;
import ai.Human;
import ai.RandomSelect;
import core.Board;
import core.GameManager;

/**
 * コンソールで実行するためのメインクラスです.
 * @author tanabe
 *
 */
public final class CuiMain {

    /**
     * コンストラクタ.
     * ユーティリティクラスのため、使用不可.
     */
    private CuiMain() { }
    
    /**
     * プログラムのエントリポイントです.
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {

        Board board = new Board();

        GameViewer viewer = new ConsoleViewer();
        NextMoveSelector inputter = new ConsoleSelector();

        GamePlayer p1 = new Human(inputter);
        GamePlayer p2 = new RandomSelect();

        GameManager manager = new GameManager(p1, p2, board, viewer);

        manager.gameStart();

        while(true) {
            if(manager.isFinish()) {
                break;
            }
        }

        manager.viewGameResult();

        viewer.view("Exit");

    }

}
