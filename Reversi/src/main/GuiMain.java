package main;

import gui.GameApplet;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * リバーシをウィンドウでプレイするためのクラスです。
 * @author tanabe
 *
 */
public final class GuiMain {

    /**
     * コンストラクタ。
     * ユーティリティクラスのため使用付加。
     */
    private GuiMain() { }

    /**
     * プログラムのエントリポイントです。
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {

        JFrame frame = new MainFrame();

        frame.pack();
        Dimension size = frame.getSize();
        frame.setMinimumSize(size);

        frame.setLocation(100, 100);    // CHECKSTYLE IGNORE THIS LINE
        frame.setVisible(true);

    }

    /**
     * ゲームのフレーム。
     * @author tanabe
     *
     */
    private static class MainFrame extends JFrame {

        /**
         * シリアルバージョンです。
         */
        private static final long serialVersionUID = -6925755286458664752L;
        
        /**
         * ゲームのフレームを生成します。
         */
        public MainFrame() {
    
            GameApplet a = new GameApplet();
            a.init();
    
            JPanel panel = new JPanel();
            panel.add(a);
            a.setLocation(0, 0);
    
            Dimension size = new Dimension(GameApplet.WIDTH, GameApplet.HEIGHT);
            panel.setPreferredSize(size);
            panel.setMinimumSize(size);
            this.add(panel);
            panel.setLocation(0, 0);
    
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Reversi");
    
        }

    }

}
