package input;

import core.NextMove;

/**
 * リバーシの次の手を入力するインターフェースです.
 * @author tanabe
 *
 */
public interface NextMoveSelector {

    /**
     * リバーシの次の手を入力します.<br>
     * 置く場所がある場合は必ず石を置いてください.
     * 置く場所がない場合のみ<code>null</code>を返してください.
     * @param stone 次に置く石
     * @param message 入力時に表示するメッセージ
     * @return 次の手.パスの場合は<code>null</code>
     */
    NextMove select(int stone, String message);

    /**
     * リバーシの次の手を入力します.
     * @param stone 次に置く石
     * @return 次の手
     */
    NextMove select(int stone);


    /**
     * 重要なメッセージを表示します.<br>
     * このメッセージの後にユーザーに確認の入力を求めます.
     * 入力があるまで待機します.
     * @param message メッセージ
     */
    void dispImportantMessage(String message);

}
