package main;

import java.awt.Dimension;

import gui.GameApplet;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * リバーシをウィンドウでプレイするためのクラスです。
 * @author tanabe
 *
 */
public class GuiMain extends JFrame {

    /**
     * シリアルバージョンです。
     */
    private static final long serialVersionUID = -6925755286458664752L;


    /**
     * プログラムのエントリポイントです。
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {

        JFrame frame = new GuiMain();

        frame.pack();
        Dimension size = frame.getSize();
        frame.setMinimumSize(size);

        frame.setLocation(100, 100);
        frame.setVisible(true);

    }


    /**
     * ゲームのフレームを生成します。
     */
    public GuiMain() {

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
