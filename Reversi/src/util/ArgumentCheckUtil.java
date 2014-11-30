package util;

import core.Cell;

/**
 * 引数チェック処理をまとめたユーティリティクラスです。
 * @author tanabe
 *
 */
public final class ArgumentCheckUtil {
    
    /**
     * コンストラクタ.
     * ユーティリティクラスなので使用不可.
     */
    private ArgumentCheckUtil() { }
    
    /**
     * 引数がNullでないことを確認します.
     * @param arg 引数
     * @exception IllegalArgumentException 引数がNullの場合
     */
    public static void CheckNotNull(Object arg) {
        if(arg == null) {
            throw new IllegalArgumentException("引数はNull不可です.");
        }
    }
    
    /**
     * 引数の値がNOTHINGでないことを確認します.
     * @param arg 引数
     * @exception IllegalArgumentException 引数の値がNOTHINGの場合
     */
    public static void CheckNotNothing(Cell arg) {
        if(arg == Cell.NOTHING) {
            throw new IllegalArgumentException("引数はNOTHING不可です.");
        }
    }
    
    /**
     * 引数の値が負でないことを確認します.
     * @param arg 引数
     * @exception IllegalArgumentException 引数の値が負の場合
     */
    public static void CheckNotNegativeValue(int arg) {
        if(arg < 0) {
            throw new IllegalArgumentException("引数は負不可です.");
        }
    }
    
    /**
     * 引数の値が0以下でないことを確認します.
     * @param arg 引数
     * @exception IllegalArgumentException 引数の値が0以下の場合
     */
    public static void CheckNotZeroAndNegativeValue(int arg) {
        if(arg <= 0) {
            throw new IllegalArgumentException("引数は0以下は不可です.");
        }
    }
}
