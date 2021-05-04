package be.app;

import java.util.Scanner;

/**
 *Assuming two humans playing on 3x3 board and
 *always X goes first
 */
public class TicTacToeGame {

    private static String[][] board = new String[3][3];

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        String response = game.startGame();
        System.out.println(response);
    }

    /**
     * This is the starting point
     * @return final response
     */
    public  String startGame(){
        populateNumbersInBoard();
        Scanner in = new Scanner(System.in);
        int counter = 0;
        String player = "X"; //starting player

        while (counter < 9) {
            System.out.println("Please reserve the position - Player :"+player);
            String input = in.next();
            if (input.length() != 1 || !Character.isDigit(input.charAt(0)) || Integer.valueOf(input) < 1) {
                System.out.println("Please enter only digit between 1 to 9");
                continue;
            }
            if (!reservePosition(Integer.valueOf(input),player )) {
                System.out.println("Given position already reserved! try again!");
                continue;
            }
            if (counter > 3) {
                if (checkWinner(player)) {
                    return "Winner is :" + player;
                }
            }
            player = player == "X" ? "O" : "X";
            counter++;
            if(counter > 8){
                return "Game was drawn";
            }
        }
        return null;
    }


    /**
     * It will check the winner after 5th move onwards
     * @param player
     * @return true/false
     */
    public boolean checkWinner(String player) {
        // Check all rows
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].equals(player)
                    && board[i][1].equals(player)
                    && board[i][2].equals(player)) {
                return true;
            }
        }

        // Check all columns
        for (int i = 0; i < board.length; i++) {
            if (board[0][i].equals(player)
                    && board[1][i].equals(player)
                    && board[2][i].equals(player)) {
                return true;
            }
        }

        //check diagonal
        if ((board[0][0].equals(player)
                && board[1][1].equals(player)
                && board[2][2].equals(player))
                || (board[2][0].equals(player)
                && board[1][1].equals(player)
                && board[0][2].equals(player))) {
            return true;
        }
        return false;
    }


    /**
     * It will first check whether the given position available or not
     * will return True on successful reserve else false
     * @param position
     * @param c
     * @return true/false
     */
    public boolean reservePosition(int position, String c) {
        int i, j = 0;
        if (position < 4) {
            i = 0;
            j = position - 1;
        } else if (position < 7) {
            i = 1;
            j = position - 4;
        } else {
            i = 2;
            j = position - 7;
        }
        if(Character.isDigit(board[i][j].charAt(0))){
            board[i][j] = c;
            displayBoard();
            return true;
        }
        return false;
    }


    /**
     * Method to populate the numbers rather than a null objects in board
     */
    public void populateNumbersInBoard(){
        int counter = 0;
        for(int i=0; i < board.length ; i++) {
            for (int j = 0; j < board.length ; j++) {
                board[i][j] = String.valueOf(++counter);
            }
        }
        displayBoard();
    }


    /**
     * Method to display current board status
     */
    public void displayBoard() {
        System.out.println(String.format("|---|---|---|\n"
                        + "| %s | %s | %s |\n"
                        + "|-----------|\n"
                        + "| %s | %s | %s |\n"
                        + "|-----------|\n"
                        + "| %s | %s | %s |\n"
                        + "|---|---|---|\n"
                , board[0][0], board[0][1], board[0][2],
                board[1][0], board[1][1], board[1][2],
                board[2][0], board[2][1], board[2][2]));
    }
}
