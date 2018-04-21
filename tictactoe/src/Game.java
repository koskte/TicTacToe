import java.util.Scanner;

/**
 Class Game contains all the methods required to run the game.

 Game contains methods for setting the size of the gameboard, creating the gameboard,
 formatting the gameboard, drawing the gameboard, registering the moves of the player and the CPU,
 and checking if the player or CPU has won or if the game was a tie.

 @author Teemu Koskinen
 @version 2017.1209
 @since 1.0
 */

 public class Game {
    public static Scanner user = new Scanner(System.in);
    private static int row = 0;
    private static int column = 0;
    public static char[][] table;
    public static char player = 'O';
    public static int roww = 0;
    public static int columnn = 0;
    public static int cpuroww = 0;
    public static int cpucolumnn = 0;
    

    /**
     Prevents user from setting anyhting other then an integer to be the value of row.

     @param user reads the users input
     @return returns the first valid input
     */

    public static int getRowInt (Scanner user) {
        while (!user.hasNextInt()) {
            System.out.println();
            user.next();
            System.out.println();
            System.out.println("Please give the row a numeric value");
        }
        return user.nextInt();
    }

    /**
     Prevents user from setting anyhting other then an integer to be the value of column.

     @param user reads the users input
     @return returns the first valid input
     */

    public static int getColInt (Scanner user) {
        while (!user.hasNextInt()) {
            System.out.println();
            user.next();
            System.out.println();
            System.out.println("Please give the column a numeric value");
        }
        return user.nextInt();
    }        
    
    

    /**
     Sets the variable "row" to what the player sets it to. As long as it is bigger than 2.

     */
    public static void setRow() {
        boolean unacceptable = true;
        while (unacceptable) {
            System.out.println("Give the size of the row.");
            row = getRowInt(user);
            System.out.println();
            if (row<3) {
                System.out.println();
                System.out.println("Row has to be atleast the size of 3. Please input a valid size.");
                System.out.println();
            } else {
                unacceptable = false;
            }
        }
    }

    /**
     Sets the variable "column" to what the User sets it to. As long as it is over 2.

     */
    public static void setColumn() {
        boolean unacceptable2 = true;
        while(unacceptable2) {
            System.out.println("Give the size of the column.");
            column = getColInt(user);
            System.out.println();
            if (column<3 || column != row) {
                System.out.println();
                System.out.println("Column has to be atleast the size of 3 and the board must be square. Please input a valid size.");
                System.out.println();
            } else {
                unacceptable2 = false;
            }
        }
    }

    /**
     Creates a 2D array.

     This method creates a 2D array. The method gets it's values for row and column from
     methods setRow and setColumn.

     */
    public static void createTable() {
        table = new char[row][column];
    }

    /**
     Formats the 2D array.

     This method formats the created 2D array to be full of '_' charecters.
     */
    public static void formatTable () {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++ ) {
                table[i][j] = '_';
            }
        }
    }

    /**
     Prints the 2D array.

     When this method is called it prints the table with whatever values the table has in it to the console.
     */
    public static void drawTable() {
        for (int i = 0; i < row; i++) {
                System.out.println();
            for (int j = 0; j < column; j++) {
                if (j == 0) {
                    System.out.print("|");
                }
                System.out.print(table[i][j] + " | ");
                
                
            }
        }
    }

    /**
     Registeres the players and CPUs moves and keeps the game running until the player or the CPU wins.

     */
    public static void move() {
        boolean playing = true;
        int checkTie = 3;
        while(playing) {
            if (player == 'X') {
                boolean cpuTurn = true;
                while (cpuTurn) {
                    cpuroww = (int) (Math.random() * table.length);
                    cpucolumnn = (int) (Math.random() * table.length);
                    if (table[cpuroww][cpucolumnn] == '_'){
                        table[cpuroww][cpucolumnn] = player;
                        cpuTurn = false;
                    } else {
                    
                        cpuTurn = true;
                    }
                }
                System.out.println();
                drawTable();
                System.out.print("CPU placed it's move to " + (cpuroww+1) + '-' + (cpucolumnn+1));
                System.out.println();
            }
            System.out.println();
            if (player == 'O') {
                boolean playerTurn = true;
                while (playerTurn) {
                    System.out.println("Give a row number from 1 to " + table.length);
                    roww = getRowInt(user)-1;
                    System.out.println();
                    System.out.println("Give a column number from 1 to " + table.length);
                    columnn = getColInt(user)-1;
                    if (roww > table.length-1 || columnn > table.length-1) {
                        System.out.println("Your move is out of bounds. Try another one.");
                        playerTurn = true;
                    } else {
                        if (table[roww][columnn] == '_') {
                            table[roww][columnn] = player;
                            playerTurn = false;
                        } else {
                            System.out.println("That space is taken. Try another one.");
                            playerTurn = true;
                        }
                    }
                    
                }
                System.out.println();
                drawTable();
                System.out.print("Player placed his/her move to " + (roww+1) + '-' + (columnn+1));
                System.out.println();
            }

            int lastRow = roww;
            int lastCol = columnn;
            int cpuLastRow = cpuroww;
            int cpuLastCol = cpucolumnn;
            System.out.println();
            

            if (player == 'O' && row < 10 && column < 10) {
                if (!playerCheckWin(lastRow, lastCol, table, player)) {
                    if (checkTie(table)) {
                        playing = false;
                        System.out.println("The game ended in a tie.");
                    } 
                } else {
                    playing = false;
                }
            }
            
            if (player == 'X' && row < 10 && column < 10) {
                if (cpuCheckWin(cpuLastRow, cpuLastCol, table, player)) {
                    playing = false;
                }
            }

            if (player == 'O' && row >= 10 && column >= 10) {
                if (!playerCheckWin10OrOver(lastRow, lastCol, table, player)) {
                    if (checkTie(table)) {
                        playing = false;
                        System.out.println("The game ended in a tie.");
                    }  
                } else {
                    playing = false;
                }
            }

            if (player == 'X' && row >= 10 && column >= 10) {
                if (cpuCheckWin10OrOver(cpuLastRow, cpuLastCol, table, player)) {
                    playing = false;
                }
            }
            
            
            
            if(player == 'O') { //Switches turns.
                player = 'X';
            } else {
                player = 'O';
            }  
        }
        
        
    }

    /**
     Checks if the game ended in a tie.

     This method goes through all the spaces in the 2D array that is given to it in the parameters
     and checks if there are any '_' charecters left. If there aren't any the method returns true.

     @param table 2D array from which the method checks
     @return result of the checking of '_' charecters
     */
    public static boolean checkTie (char [][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     Checks if the player has won when the size of the board is less than 10.

     This method compares the formerly made moves of the player to the last made move.
     If any of the comparisons is true the game will end and declare the player as the winner.
     
     @param roww value of row in the last made move.
     @param columnn value of the column in the last made move.
     @param table 2D array that has the values of all the moves made.
     @param player has the information of whose turn it is.
     @return result of the checking of winner
     */

    public static boolean playerCheckWin(int roww, int columnn, char [][] table, char player) {
        
        // NOTE THAT THE COMMENTS IN THIS METHOD ARE JUST THE AUTHORS WAY OF KEEPING TRACK OF WHAT HE HAS DONE ALREADY.

        if (columnn >=2 && columnn <= table.length) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn-2]) { //Checks for a horizontal straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length - 3 && columnn >= 0) {
            if (table[roww][columnn] == table[roww][columnn+1] && table[roww][columnn] == table[roww][columnn+2]) { //Checks for a horizontal straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-1 && columnn >= 1) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn+1]) { //Checks for a horizontal straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-1 && columnn >= 1 && roww < table.length-1 && roww > 0) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww+1][columnn-1]) { //Checks for a diagonal straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length && columnn > 1 && roww < table.length-2 && roww >= 0) {
            if (table[roww][columnn] == table[roww+1][columnn-1] && table[roww][columnn] == table[roww+2][columnn-2]) { //Checks for a diagonal straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-2 && roww >= table.length-2) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww-2][columnn+2]) { //Checks for a diagonal straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-2 && roww < table.length-2) {
            if (table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww+2][columnn+2]) { //Checks for a diagonal straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 1 && roww > 1 ) {
            if (table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww-2][columnn-2]) { //Checks for a diagonal straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 0 && columnn < table.length-1 && roww > 0 && roww < table.length-1 ) {
            if (table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww-1][columnn-1]) { //Checks for a diagonal straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww < table.length-2 ) {
            if (table[roww][columnn] == table[roww+1][columnn] && table[roww][columnn] == table[roww+2][columnn]) { //Checks for a vertical straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 1) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww-2][columnn]) { //Checks for a vertical straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 0 && roww < table.length-1) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww+1][columnn]) { //Checks for a vertical straight of 3.
                System.out.println(player + " WINS");
                return true;
            }
        }
        return false;
    }

    /**
     Checks if the player has won when the size of the board is atleast 10.

     This method compares the formerly made moves of the player to the last made move.
     If any of the comparisons is true the game will end and declare the player as the winner.
     
     @param roww value of row in the last made move.
     @param columnn value of the column in the last made move.
     @param table 2D array that has the values of all the moves made.
     @param player has the information of whose turn it is.
     @return result of the checking of winner
     */

    public static boolean playerCheckWin10OrOver(int roww, int columnn, char [][] table, char player) {

        // NOTE THAT THE COMMENTS IN THIS METHOD ARE JUST THE AUTHORS WAY OF KEEPING TRACK OF WHAT HE HAS DONE ALREADY.
        
        if (columnn >=4 && columnn <= table.length) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn-2] && table[roww][columnn] == table[roww][columnn-3] && table[roww][columnn] == table[roww][columnn-4]) { //Checks for a horizontal straight of 5. right - left 5. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn >=3 && columnn < table.length-1) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn-2] && table[roww][columnn] == table[roww][columnn-3] && table[roww][columnn] == table[roww][columnn+1]) { //Checks for a horizontal straight of 5. to right and left 4. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length - 5 && columnn >= 0) {
            if (table[roww][columnn] == table[roww][columnn+1] && table[roww][columnn] == table[roww][columnn+2] && table[roww][columnn] == table[roww][columnn+3] && table[roww][columnn] == table[roww][columnn+4]) { //Checks for a horizontal straight of 5. left - right 1. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length - 4 && columnn >= 1) {
            if (table[roww][columnn] == table[roww][columnn+1] && table[roww][columnn] == table[roww][columnn+2] && table[roww][columnn] == table[roww][columnn+3] && table[roww][columnn] == table[roww][columnn-1]) { //Checks for a horizontal straight of 5. to left and right 2. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-2 && columnn >= 2) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn+1] && table[roww][columnn] == table[roww][columnn-2] && table[roww][columnn] == table[roww][columnn+2]) { //Checks for a horizontal straight of 5. to right and left 3. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-3 && columnn >= 3 && roww < table.length-3 && roww > 2) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww+1][columnn-1] && table[roww][columnn] == table[roww-2][columnn+2] && table[roww][columnn] == table[roww+2][columnn-2]) { //Checks for a diagonal straight of 5. to down left and top right 3. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length && columnn > 3 && roww < table.length-4 && roww >= 0) {
            if (table[roww][columnn] == table[roww+1][columnn-1] && table[roww][columnn] == table[roww+2][columnn-2] && table[roww][columnn] == table[roww+3][columnn-3] && table[roww][columnn] == table[roww+4][columnn-4]) { //Checks for a diagonal straight of 5. to down left 5. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-4 && roww > 3) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww-2][columnn+2] && table[roww][columnn] == table[roww-3][columnn+3] && table[roww][columnn] == table[roww-4][columnn+4]) { //Checks for a diagonal straight of 5. to top right 1. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-3 && columnn > 0 && roww > 2 && roww < table.length-1) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww-2][columnn+2] && table[roww][columnn] == table[roww-3][columnn+3] && table[roww][columnn] == table[roww+1][columnn-1]) { //Checks for a diagonal straight of 5. to down left and top right 2. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-1 && columnn > 2 && roww > 0 && roww < table.length-3) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww+1][columnn-1] && table[roww][columnn] == table[roww+2][columnn-2] && table[roww][columnn] == table[roww+3][columnn-3]) { //Checks for a diagonal straight of 5. to down left and top right 4. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-4 && roww < table.length-4) {
            if (table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww+2][columnn+2] && table[roww][columnn] == table[roww+3][columnn+3] && table[roww][columnn] == table[roww+4][columnn+4]) { //Checks for a diagonal straight of 5. to down right 1. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 1 && columnn < table.length-2 &&  roww > 1 && roww < table.length-2) {
            if (table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww-2][columnn-2] && table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww+2][columnn+2]) { //Checks for a diagonal straight of 5. to top left and down right 3. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 3 && roww > 3) {
            if (table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww-2][columnn-2] && table[roww][columnn] == table[roww-3][columnn-3] && table[roww][columnn] == table[roww-4][columnn-4]) { //Checks for a diagonal straight of 5. to top left 5. done 
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 0 && columnn < table.length-3 && roww > 0 && roww < table.length-3 ) {
            if (table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww+2][columnn+2] && table[roww][columnn] == table[roww+3][columnn+3]) { //Checks for a diagonal straight of 5. to top left and down right 2. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 2 && columnn < table.length-1 && roww > 1 && roww < table.length-1 ) {
            if (table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww-2][columnn-2] && table[roww][columnn] == table[roww-3][columnn-3]) { //Checks for a diagonal straight of 5. to top left and down right 4. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww < table.length-4 ) {
            if (table[roww][columnn] == table[roww+1][columnn] && table[roww][columnn] == table[roww+2][columnn] && table[roww][columnn] == table[roww+3][columnn] && table[roww][columnn] == table[roww+4][columnn]) { //Checks for a vertical straight of 5. 1. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 3) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww-2][columnn] && table[roww][columnn] == table[roww-3][columnn] && table[roww][columnn] == table[roww-4][columnn]) { //Checks for a vertical straight of 5. 5. done  
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 1 && roww < table.length-2) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww-2][columnn] && table[roww][columnn] == table[roww+1][columnn] && table[roww][columnn] == table[roww+2][columnn]) { //Checks for a vertical straight of 3. 3. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 0 && roww < table.length-3) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww+1][columnn] && table[roww][columnn] == table[roww+2][columnn] && table[roww][columnn] == table[roww+3][columnn]) { //Checks for a vertical straight of 3. 2. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 2 && roww < table.length-1) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww-2][columnn] && table[roww][columnn] == table[roww-3][columnn] && table[roww][columnn] == table[roww+1][columnn]) { //Checks for a vertical straight of 3. 4. done
                System.out.println(player + " WINS");
                return true;
            }
        }
        return false;
    }

    

    /**
     Checks if the CPU has won when the size of the board is less than 10.

     This method compares the formerly made moves of the CPU to the last made move.
     If any of the comparisons is true the game will end and declare the CPU as the winner.
     
     @param roww value of row in the last made move.
     @param columnn value of the column in the last made move.
     @param table 2D array that has all the values of all moves made.
     @param player has the information of whose turn it is.
     @return result of the checking of winner
     */

    public static boolean cpuCheckWin(int roww, int columnn, char [][] table, char player) {
        if (columnn >=2 && columnn <= table.length) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn-2]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length - 3 && columnn >= 0) {
            if (table[roww][columnn] == table[roww][columnn+1] && table[roww][columnn] == table[roww][columnn+2]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-1 && columnn >= 1) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn+1]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-1 && columnn >= 1 && roww < table.length-1 && roww > 0) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww+1][columnn-1]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length && columnn > 1 && roww < table.length-2 && roww >= 0) {
            if (table[roww][columnn] == table[roww+1][columnn-1] && table[roww][columnn] == table[roww+2][columnn-2]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-2 && roww > 2) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww-2][columnn+2]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-2 && roww < table.length-2) {
            if (table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww+2][columnn+2]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 1 && roww > 1 ) {
            if (table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww-2][columnn-2]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 0 && columnn < table.length-1 && roww > 0 && roww < table.length-1 ) {
            if (table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww-1][columnn-1]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww < table.length-2 ) {
            if (table[roww][columnn] == table[roww+1][columnn] && table[roww][columnn] == table[roww+2][columnn]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 1) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww-2][columnn]) {
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 0 && roww < table.length-1) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww+1][columnn]) {
                System.out.println(player + " WINS");
                return true;
            }
        }
        return false;
    }

    /**
     Checks if the CPU has won when the size of the board is atleast 10.

     This method compares the formerly made moves of the CPU to the last made move.
     If any of the comparisons is true the game will end and declare the CPU as the winner.
     
     @param roww value of row in the last made move.
     @param columnn value of the column in the last made move.
     @param table 2D array that has all the values of all moves made.
     @param player has the information of whose turn it is.
     @return result of the checking of winner
     */

    public static boolean cpuCheckWin10OrOver(int roww, int columnn, char [][] table, char player) {

        // NOTE THAT THE COMMENTS IN THIS METHOD ARE JUST THE AUTHORS WAY OF KEEPING TRACK OF WHAT HE HAS DONE ALREADY.
        
        if (columnn >=4 && columnn <= table.length) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn-2] && table[roww][columnn] == table[roww][columnn-3] && table[roww][columnn] == table[roww][columnn-4]) { //Checks for a horizontal straight of 5. right - left 5. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn >=3 && columnn < table.length-1) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn-2] && table[roww][columnn] == table[roww][columnn-3] && table[roww][columnn] == table[roww][columnn+1]) { //Checks for a horizontal straight of 5. to right and left 4. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length - 5 && columnn >= 0) {
            if (table[roww][columnn] == table[roww][columnn+1] && table[roww][columnn] == table[roww][columnn+2] && table[roww][columnn] == table[roww][columnn+3] && table[roww][columnn] == table[roww][columnn+4]) { //Checks for a horizontal straight of 5. left - right 1. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length - 4 && columnn >= 1) {
            if (table[roww][columnn] == table[roww][columnn+1] && table[roww][columnn] == table[roww][columnn+2] && table[roww][columnn] == table[roww][columnn+3] && table[roww][columnn] == table[roww][columnn-1]) { //Checks for a horizontal straight of 5. to left and right 2. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-2 && columnn >= 2) {
            if (table[roww][columnn] == table[roww][columnn-1] && table[roww][columnn] == table[roww][columnn+1] && table[roww][columnn] == table[roww][columnn-2] && table[roww][columnn] == table[roww][columnn+2]) { //Checks for a horizontal straight of 5. to right and left 3. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-3 && columnn >= 3 && roww < table.length-3 && roww > 2) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww+1][columnn-1] && table[roww][columnn] == table[roww-2][columnn+2] && table[roww][columnn] == table[roww+2][columnn-2]) { //Checks for a diagonal straight of 5. to down left and top right 3. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn <= table.length && columnn > 3 && roww < table.length-4 && roww >= 0) {
            if (table[roww][columnn] == table[roww+1][columnn-1] && table[roww][columnn] == table[roww+2][columnn-2] && table[roww][columnn] == table[roww+3][columnn-3] && table[roww][columnn] == table[roww+4][columnn-4]) { //Checks for a diagonal straight of 5. to down left 5. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-4 && roww > 3) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww-2][columnn+2] && table[roww][columnn] == table[roww-3][columnn+3] && table[roww][columnn] == table[roww-4][columnn+4]) { //Checks for a diagonal straight of 5. to top right 1. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-3 && columnn > 0 && roww > 2 && roww < table.length-1) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww-2][columnn+2] && table[roww][columnn] == table[roww-3][columnn+3] && table[roww][columnn] == table[roww+1][columnn-1]) { //Checks for a diagonal straight of 5. to down left and top right 2. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-1 && columnn > 2 && roww > 0 && roww < table.length-3) {
            if (table[roww][columnn] == table[roww-1][columnn+1] && table[roww][columnn] == table[roww+1][columnn-1] && table[roww][columnn] == table[roww+2][columnn-2] && table[roww][columnn] == table[roww+3][columnn-3]) { //Checks for a diagonal straight of 5. to down left and top right 4. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn < table.length-4 && roww < table.length-4) {
            if (table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww+2][columnn+2] && table[roww][columnn] == table[roww+3][columnn+3] && table[roww][columnn] == table[roww+4][columnn+4]) { //Checks for a diagonal straight of 5. to down right 1. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 1 && columnn < table.length-2 &&  roww > 1 && roww < table.length-2) {
            if (table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww-2][columnn-2] && table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww+2][columnn+2]) { //Checks for a diagonal straight of 5. to top left and down right 3. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 3 && roww > 3) {
            if (table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww-2][columnn-2] && table[roww][columnn] == table[roww-3][columnn-3] && table[roww][columnn] == table[roww-4][columnn-4]) { //Checks for a diagonal straight of 5. to top left 5. done 
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 0 && columnn < table.length-3 && roww > 0 && roww < table.length-3 ) {
            if (table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww+2][columnn+2] && table[roww][columnn] == table[roww+3][columnn+3]) { //Checks for a diagonal straight of 5. to top left and down right 2. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (columnn > 2 && columnn < table.length-1 && roww > 1 && roww < table.length-1 ) {
            if (table[roww][columnn] == table[roww+1][columnn+1] && table[roww][columnn] == table[roww-1][columnn-1] && table[roww][columnn] == table[roww-2][columnn-2] && table[roww][columnn] == table[roww-3][columnn-3]) { //Checks for a diagonal straight of 5. to top left and down right 4. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww < table.length-4 ) {
            if (table[roww][columnn] == table[roww+1][columnn] && table[roww][columnn] == table[roww+2][columnn] && table[roww][columnn] == table[roww+3][columnn] && table[roww][columnn] == table[roww+4][columnn]) { //Checks for a vertical straight of 5. 1. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 3) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww-2][columnn] && table[roww][columnn] == table[roww-3][columnn] && table[roww][columnn] == table[roww-4][columnn]) { //Checks for a vertical straight of 5. 5. done  
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 1 && roww < table.length-2) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww-2][columnn] && table[roww][columnn] == table[roww+1][columnn] && table[roww][columnn] == table[roww+2][columnn]) { //Checks for a vertical straight of 3. 3. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 0 && roww < table.length-3) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww+1][columnn] && table[roww][columnn] == table[roww+2][columnn] && table[roww][columnn] == table[roww+3][columnn]) { //Checks for a vertical straight of 3. 2. done
                System.out.println(player + " WINS");
                return true;
            }
        }

        if (roww > 2 && roww < table.length-1) {
            if (table[roww][columnn] == table[roww-1][columnn] && table[roww][columnn] == table[roww-2][columnn] && table[roww][columnn] == table[roww-3][columnn] && table[roww][columnn] == table[roww+1][columnn]) { //Checks for a vertical straight of 3. 4. done
                System.out.println(player + " WINS");
                return true;
            }
        }
        return false;
    }
}