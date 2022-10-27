//IFT2015 TP1
//Auteures: Jeongeun Lee (20215747)
//          Grace Lee (20174147)

//
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

    protected static int getNumber() 
    {
        int min = 0;
		int max = 2;

		Random random = new Random();

		int value = random.nextInt((max - min) + 1) + min;

        return value;
    }

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
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    protected void checkAround(int posRow, int posCol) 
    {

        if (posRow > 0) {
            if (this.board.get(posRow - 1).get(posCol) == 1) {
                this.inactiveBomb.add((posRow - 1) * this.colNum + posCol);
            }
        }

        if (posRow < this.rowNum - 1) {
            if (this.board.get(posRow + 1).get(posCol) == 1) {
                this.inactiveBomb.add((posRow + 1) * this.colNum + posCol);
            }
        }

        if (posCol > 0) {
            if (this.board.get(posRow).get(posCol - 1) == 1) {
                this.inactiveBomb.add(posRow * this.colNum + (posCol - 1));
            }
        }

        if (posCol < this.colNum - 1) {
            if (this.board.get(posRow).get(posCol + 1) == 1) {
                this.inactiveBomb.add(posRow * this.colNum + (posCol + 1));
            }
        }
    }
}
