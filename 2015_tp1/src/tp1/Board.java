// IFT2015 TP1
// Auteurs: Jeongeun Lee (20215747)
//          Grace Lee (20174147)
// Date:    Le 28 octobre 2022

package tp1;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    protected ArrayList<ArrayList<Integer>> board;
    protected ArrayList<Integer> inactiveBomb;
    protected int rowNum = 0;
    protected int colNum = 0;


    public Board(int rowNum, int colNum) 
    {
        this.rowNum = rowNum;
        this.colNum = colNum;

        this.board = this.makeBoard();

        this.inactiveBomb = new ArrayList<Integer>();
    }

    // create the random number between 0, 1 and 2 in the board
    protected static int getNumber() 
    {
        int min = 0;
		int max = 2;

		Random random = new Random();

		int value = random.nextInt((max - min) + 1) + min;

        return value;
    }

    // create the board of the n x m size that we assign (rowNum x colNum)
    protected ArrayList<ArrayList<Integer>> makeBoard() 
    {
        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < this.rowNum; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            
            for (int j = 0; j < this.colNum; j++) {
                row.add(getNumber());
            }
            board.add(row);
        }

        return board;
    }

    // print out the board with a space between each number 
    public void printBoard() 
    {
        for (int i = 0; i < this.rowNum; i++) {
            ArrayList<Integer> row = board.get(i);

            for (int j = 0; j < this.colNum; j++) {
                System.out.print(row.get(j) + " ");
            }

            System.out.println("");
        }
        System.out.println("");
    }

    // 
    public int activateBomb() 
    {
        this.inactiveBomb.clear();

        for (int i = 0; i < this.rowNum; i++) {
            
            for (int j = 0; j < this.colNum; j++) {
                if (this.board.get(i).get(j) == 2) {
                    this.checkAround(i, j);
                }
            }
        }
        if (!this.inactiveBomb.isEmpty()) {
            for (Integer pos: this.inactiveBomb) {
                int r = (int) Math.floor(pos/this.colNum);
                int c = (int) pos % this.colNum;
                this.board.get(r).set(c, 2);
            }
        }

        this.printBoard();

        return isThereBomb();
    }
   
    protected int isThereBomb()
    {
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < this.colNum; j++) {
                if (this.board.get(i).get(j) == 1) {
                    if (inactiveBomb.isEmpty()) {
                        return -1;      // return -1 if the inactiveBomb array is empty but if there are still inactive bombs in the board
                    } else {
                        return 1;       // return 1 if there are inactive bombs in the array
                    }
                }
            }
        }
        return 0;       // return 0 if there is no more inactive bomb in the array
    }

    // check around the active bombs and if there is an inactive bomb, add its position to the inactiveBomb arraylist
    protected void checkAround(int posRow, int posCol) 
    {

        if (posRow > 0) {
            if (this.board.get(posRow - 1).get(posCol) == 1) {
                this.inactiveBomb.add((posRow - 1) * this.colNum + posCol);     // check left
            }
        }

        if (posRow < this.rowNum - 1) {
            if (this.board.get(posRow + 1).get(posCol) == 1) {
                this.inactiveBomb.add((posRow + 1) * this.colNum + posCol);     // check right
            }
        }

        if (posCol > 0) {
            if (this.board.get(posRow).get(posCol - 1) == 1) {
                this.inactiveBomb.add(posRow * this.colNum + (posCol - 1));     // check bottom
            }
        }

        if (posCol < this.colNum - 1) {
            if (this.board.get(posRow).get(posCol + 1) == 1) {
                this.inactiveBomb.add(posRow * this.colNum + (posCol + 1));     // check top
            }
        }
    }
}
