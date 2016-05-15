/**
 * Created by Brandon on 5/1/2016.
 */
package ConnectFourPackage;
import java.util.Scanner;

public class ConnectFour {
    public static final int BOARD_WIDTH = 7;
    public static final int BOARD_HEIGHT = 6;

    public static int playerNum;
    public static char mark;

    public static void printBoard(Square[][] printMe){
        for (int i = 0; i < BOARD_WIDTH; i++) {               // print first line of board
            if (i == BOARD_WIDTH - 1)
                System.out.print("__" + (i + 1) + "__\n");
            else
                System.out.print("__" + (i + 1) + "_");
        }

        for (int i = 0; i < BOARD_HEIGHT; i++)              // print body of board
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (j == (BOARD_WIDTH - 1))
                    System.out.print("| " + printMe[i][j].getMark() + " |\n");
                else
                System.out.print("| " + printMe[i][j].getMark() + " ");
            }
        for (int i = 0; i < BOARD_WIDTH; i++) {               // print last line of board
            if (i == BOARD_WIDTH - 1)
                System.out.print("_____\n");
            else
                System.out.print("____");
        }
    }

    public static void findTurn(int count) {
        if ((count % 2) == 1) {
            playerNum = 2;
            mark = '+';
        } else {
            playerNum = 1;
            mark = 'o';
        }
    }

    public static void setBoard(int column, Square[][] board) {
        for (int i = (BOARD_HEIGHT - 1); i >= 0; i--) {
            if (board[i][column].isEmpty()) {
                board[i][column].setMark(mark);
                break;
            }
        }
    }

    public static void setSquares(Square[][] setMe){
        for (int i = 0; i < BOARD_HEIGHT; i++)
            for (int j = 0; j < BOARD_WIDTH; j++)
                setMe[i][j] = new Square();
    }

    public static boolean hasWon(Square[][] checkMe){
        int order;
        int k, o;

        for (int i = 0; i < BOARD_HEIGHT; i++)
            for (int j = 0; j < BOARD_WIDTH; j++){
                if (!checkMe[i][j].isEmpty()){
                    // checking vertical win
                    if ((j + 1) < BOARD_WIDTH && checkMe[i][j + 1].getMark() == checkMe[i][j].getMark()){
                        order = 1;
                        k = j + 2;
                        while (k < BOARD_WIDTH && checkMe[i][k].getMark() == checkMe[i][j].getMark()){
                            order++;
                            if (order == 3) {return true;}
                            k++;
                        }
                    }

                    // checking horizontal win
                    if ((i + 1) < BOARD_HEIGHT && checkMe[i + 1][j].getMark() == checkMe[i][j].getMark()){
                        order = 1;
                        o = i + 2;
                        while (o < BOARD_HEIGHT && checkMe[o][j].getMark() == checkMe[i][j].getMark()){
                            order++;
                            if (order == 3) {return true;}
                            o++;
                        }
                    }

                    // checking bot left to top right win
                    if ((i + 1) < BOARD_HEIGHT && (j + 1) < BOARD_WIDTH && checkMe[i + 1][j + 1].getMark() == checkMe[i][j].getMark()){
                        order = 1;
                        o = i + 2;
                        k = j + 2;
                        while (o < BOARD_HEIGHT && k < BOARD_WIDTH && checkMe[o][k].getMark() == checkMe[i][j].getMark()){
                            order++;
                            if (order == 3) {return true;}
                            o++;
                            k++;
                        }
                    }

                    // checking top left to bot right win
                    if ((i - 1) >= 0&& (j + 1) < BOARD_WIDTH && checkMe[i - 1][j + 1].getMark() == checkMe[i][j].getMark()){
                        order = 1;
                        o = i - 2;
                        k = j + 2;
                        while (o >= 0 && k < BOARD_WIDTH && checkMe[o][k].getMark() == checkMe[i][j].getMark()){
                            order++;
                            if (order == 3) {return true;}
                            o--;
                            k++;
                        }
                    }

                }
            }
        return false;
    }

    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);      // input user desired location
        int turnCount = 0;                          // keeps track of whose turn it is
        int column;
        Square[][] board = new Square[BOARD_HEIGHT][BOARD_WIDTH];

        setSquares(board);

        while(true){
            printBoard(board);
            System.out.println("\n\n");

            findTurn(turnCount);

            System.out.println("****PLAYER " + playerNum + ": Which column do you choose?****");
            column = scnr.nextInt();

            while (column < 1 || column > BOARD_WIDTH || !(board[0][column-1].isEmpty())){
                System.out.println("INVALID COLUMN: Choose again");
                column = scnr.nextInt();
            }

            setBoard(column-1, board);

            if (hasWon(board))
                break;

            turnCount++;
        }

        printBoard(board);
        System.out.println("\n\n");
        System.out.println("PLAYER " + playerNum + " HAS WON!!!");
    }

}
