package output;

import util.ArgumentCheckUtil;
import core.Board;

/**
 * ゲームの情報をコンソールに出力するクラスです.
 * 
 * @author tanabe
 *
 */
public class ConsoleViewer implements GameViewer {

    /**
     * {@inheritDoc} 出力先はコンソールです.
     * @param board {@inheritDoc}
     * @throws IllegalArgumentException 引数が<code>null</code>の場合に発生
     */
    @Override
    public void view(Board board) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNull(board);

        // 行番号を表示
        System.out.print(" ");
        for(int x = 0; x < board.getWidth(); x++) {
            System.out.print(x);
        }
        System.out.println("");

        for(int y = 0; y < board.getHeight(); y++) {

            // 列番号を表示
            System.out.print(y);

            // 石を表示
            for(int x = 0; x < board.getWidth(); x++) {

                switch(board.getStone(x, y)) {
                    case WHITE:
                        System.out.print("o");
                        break;
    
                    case BLACK:
                        System.out.print("x");
                        break;
    
                    case NOTHING:
                        System.out.print("-");
                        break;
    
                    default:
                        throw new UnsupportedOperationException();
                }

            }

            // 改行
            System.out.println("");

        }

        System.out.println("<<<'o' is white, 'x' is black.>>>");
    }

    /**
     * {@inheritDoc}
     * @param message {@inheritDoc}
     * @throws IllegalArgumentException 引数が<code>null</code>の場合に発生
     */
    @Override
    public void view(String message) {

        // 引数チェック
        ArgumentCheckUtil.checkNotNull(message);

        System.out.println(message);

    }

}
