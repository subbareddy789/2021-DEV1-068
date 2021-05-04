package be.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class TicTacToeGameTest {

    TicTacToeGame ticTacToeGame;


    @BeforeEach
    void resetObj(){
        ticTacToeGame = new TicTacToeGame();
    }

    @Test
    public void firstPlayerOwnTheGame(){

        String data = "1"+ System.getProperty("line.separator")
                +"5"+ System.getProperty("line.separator")
                +"9"+ System.getProperty("line.separator")
                +"8"+ System.getProperty("line.separator")
                +"7"+ System.getProperty("line.separator")
                +"6"+ System.getProperty("line.separator")
                +"4"+ System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(data.getBytes()));

        String response = ticTacToeGame.startGame();
        assertThat("Winner is :X").isEqualTo(response);
    }
}
