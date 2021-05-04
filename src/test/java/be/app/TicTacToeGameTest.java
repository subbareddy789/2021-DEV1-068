package be.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TicTacToeGameTest {

    TicTacToeGame ticTacToeGame;

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    static void init(){
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void resetObj(){
        ticTacToeGame = new TicTacToeGame();
    }

    @Test
    @DisplayName("First Player own the game")
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

    @Test
    @DisplayName("Second player own the game")
    public void secondPlayerOwnTheGame(){
        String data = "1"+ System.getProperty("line.separator")
                +"5"+ System.getProperty("line.separator")
                +"9"+ System.getProperty("line.separator")
                +"2"+ System.getProperty("line.separator")
                +"6"+ System.getProperty("line.separator")
                +"8"+ System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(data.getBytes()));

        String response = ticTacToeGame.startGame();
        assertThat("Winner is :O").isEqualTo(response);
    }

    @Test
    @DisplayName("Game was drawn")
    public void GameWasDrawn(){
        String data = "1"+ System.getProperty("line.separator")
                +"5"+ System.getProperty("line.separator")
                +"7"+ System.getProperty("line.separator")
                +"8"+ System.getProperty("line.separator")
                +"9"+ System.getProperty("line.separator")
                +"4"+ System.getProperty("line.separator")
                +"6"+ System.getProperty("line.separator")
                +"3"+ System.getProperty("line.separator")
                +"2"+ System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(data.getBytes()));

        String response = ticTacToeGame.startGame();
        assertThat("Game was drawn").isEqualTo(response);
    }

    @Test
    @DisplayName("Programme should display invalid input message")
    public void invalidInput(){
        String data = "as"+ System.getProperty("line.separator") // invalid input
                +"1"+ System.getProperty("line.separator")
                +"5"+ System.getProperty("line.separator")
                +"9"+ System.getProperty("line.separator")
                +"8"+ System.getProperty("line.separator")
                +"7"+ System.getProperty("line.separator")
                +"6"+ System.getProperty("line.separator")
                +"4"+ System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ticTacToeGame.startGame();
        assertTrue(outContent.toString().contains("Please enter only digit between 1 to 9"));
    }

    @Test
    @DisplayName("Programme should display position already filled message")
    public void displayErrorMsgWhenReservingFilledPosition(){
        String data = "1"+ System.getProperty("line.separator") // invalid input
                +"1"+ System.getProperty("line.separator")
                +"5"+ System.getProperty("line.separator")
                +"9"+ System.getProperty("line.separator")
                +"8"+ System.getProperty("line.separator")
                +"7"+ System.getProperty("line.separator")
                +"6"+ System.getProperty("line.separator")
                +"4"+ System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ticTacToeGame.startGame();
        assertTrue(outContent.toString().contains("Given position already reserved! try again!"));
    }
}
