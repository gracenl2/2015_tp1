package tp1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BombExploder {
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
 		

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Integer>> board = makeBoard(n, m);
        printBoard(board);
    }

    protected static int getNumber()
    {
        int min = 0;
		int max = 3;

		Random random = new Random();

		int value = random.nextInt(max + min) + min;

        return value;

    }

    protected static ArrayList<ArrayList<Integer>> makeBoard(int n, int m) {

        ArrayList<ArrayList<Integer>> board = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>(m);
            
            for (int j = 0; j < m; j++) {
                row.add(getNumber());
            }
            board.add(row);
        }
        return board;
    }
            
    protected static void printBoard(ArrayList<ArrayList<Integer>> board) {

        for (int i = 0; i < board.size(); i++) {
            ArrayList<Integer> row = board.get(i);

            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j) + " ");
            }

            System.out.println("");
        }
    }
}