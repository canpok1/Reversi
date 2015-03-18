package input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import util.ArgumentCheckUtil;
import core.NextMove;
import core.Cell;

/**
 * リバーシの次の手をコンソールから入力するクラスです.
 * @author tanabe
 *
 */
public class ConsoleSelector implements NextMoveSelector {

    /**
     * {@inheritDoc}
     * 入出力エラーが発生した場合は負の座標の手を返します.
     */
    @Override
    public NextMove select(Cell cell, String message) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNothing(cell);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in), 1);

        this.dispMessage(message);

        int x = -1;
        int y = -1;

        boolean continueInput = true;


        try {
            // X座標を入力
            while(continueInput) {

                System.out.print("X >> ");
                System.out.flush();
                String str;
                str = reader.readLine();

                try {
                    if(str != null) {
                        x = Integer.valueOf(str);
                        continueInput = false;
                    }
                } catch(NumberFormatException e) {
                    System.out.println("You should input number.");
                }

            }

            continueInput = true;

            // Y座標を入力
            while(continueInput) {

                System.out.print("Y >> ");
                System.out.flush();
                String str;
                str = reader.readLine();

                try {
                    if(str != null) {
                        y = Integer.valueOf(str);
                        continueInput = false;
                    }
                } catch(NumberFormatException e) {
                    System.out.println("You should input number.");
                }

            }
        } catch(IOException e) {
            System.err.println("IO Error!!!");
            return new NextMove(-1, -1, cell);
        }

        return new NextMove(x, y, cell);

    }

    @Override
    public NextMove select(Cell cell) {

        return this.select(cell, "");

    }


    /**
     * 文字列を表示します.
     * 引数が<code>null</code>、または空文字の場合は表示しません.
     * @param str 表示する文字列
     */
    private void dispMessage(String str) {

        if(str == null) {
            return;
        }

        if("".equals(str)) {
            return;
        }

        System.out.println(str);

    }

    @Override
    public void dispImportantMessage(String message) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNull(message);

        this.dispMessage(message);
        System.out.print("Please input something. >> ");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in), 1);

        try {
            reader.readLine();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}
