// CHECKSTYLE:OFF

package core;

import mockit.Mocked;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import output.GameViewer;
import ai.GamePlayer;

/**
 * {@link GameManager}の単体テストです.
 * @author tanabe
 *
 */
@RunWith(Enclosed.class)
public class GameManagerTest {

    public static class インスタンス化テスト {
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がnullだと例外発生(
                @Mocked GamePlayer mockPlayer,
                @Mocked Board mockBoard,
                @Mocked GameViewer mockViewer) {
            new GameManager(null, mockPlayer, mockBoard, mockViewer);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第二引数がnullだと例外発生(
                @Mocked GamePlayer mockPlayer,
                @Mocked Board mockBoard,
                @Mocked GameViewer mockViewer) {
            new GameManager(mockPlayer, null, mockBoard, mockViewer);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第三引数がnullだと例外発生(
                @Mocked GamePlayer mockPlayer1,
                @Mocked GamePlayer mockPlayer2,
                @Mocked GameViewer mockViewer) {
            new GameManager(mockPlayer1, mockPlayer2, null, mockViewer);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第四引数がnullだと例外発生(
                @Mocked GamePlayer mockPlayer1,
                @Mocked GamePlayer mockPlayer2,
                @Mocked Board mockBoard) {
            new GameManager(mockPlayer1, mockPlayer2, mockBoard, null);
        }
        
    }
    
    public static class ゲーム終了テスト {
        
        @Test
        public void isFinishがインスタンス化直後にTrueを返すか(
                @Mocked GamePlayer mockPlayer1,
                @Mocked GamePlayer mockPlayer2,
                @Mocked Board mockBoard,
                @Mocked GameViewer mockViewer) {
            GameManager manager
                = new GameManager(
                    mockPlayer1,
                    mockPlayer2,
                    mockBoard,
                    mockViewer);
            assertThat(manager.isFinish(), is(true));
        }
    }
}

//CHECKSTYLE:ON