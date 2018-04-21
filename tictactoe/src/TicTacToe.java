import java.util.Scanner;



/**
 TicTacToe is the class where the game is actually ran.

 Class TicTacToe contains the rules of the game and the main method. Main method contains an object of class Game.
 The object calls all the methods from class Game exluding playerCheckWin, cpuCheckWin, playerCheckWin10OrOver and cpuCheckWin10OrOver.
 Last 4 aforementioned methods are called in method move.

 @author Teemu Koskinen
 @version 2017.1209
 @since 1.0
 */
public class TicTacToe {
    public static void main(String [] args){
        boolean yesOrNo = true;
        Game Tic = new Game();
        System.out.println();
        System.out.println("Welcome to the game of TicTacToe.");
        System.out.println();
        System.out.println("Here are the rules of the game:");
        System.out.println();
        System.out.println("The goal of the game is to get");
        System.out.println("row of 3 matching symbols. The");
        System.out.println("symbols are 'O' and 'X'.");
        System.out.println();
        System.out.println("The human player will always be 'O'");
        System.out.println("and the CPU will respectively be 'X'.");
        System.out.println();
        System.out.println("You can only play against a CPU.");
        System.out.println("CPUs moves are randomly generated.");
        System.out.println("You can make rows horizontally,");
        System.out.println("vertically and diagonally.");
        System.out.println();
        System.out.println("If the gameboard is under 10x10");
        System.out.println("The winnning condition is 3 in a row.");
        System.out.println("If the gameboard is 10x10 or over,");
        System.out.println("then the winning condition is 5 in row.");
        System.out.println("Do you want to start a game?");
        System.out.println();
        System.out.println("Yes/No");
        System.out.println();
        while(yesOrNo) {
            Scanner user = new Scanner(System.in);
            String start = user.nextLine();
            if (start.toLowerCase().equals("yes")) {
                yesOrNo = false;
                System.out.println();
                System.out.println("You will now create a playing area. You can decide the size of the board but the board must be square.");
                System.out.println();
                Tic.setRow();
                Tic.setColumn();
                Tic.createTable();
                Tic.formatTable();
                Tic.drawTable();
                System.out.println();
                Tic.move();
            } else if (start.toLowerCase().equals("no")) {
                return;
            } else {
                System.out.println("Please input yes or no.");
            }
        }
    }
}

    

