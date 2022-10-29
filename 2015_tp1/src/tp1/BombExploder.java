// IFT2015 TP1
// Auteurs: Jeongeun Lee (20215747)
//          Grace Lee (20174147)
// Date:    Le 28 octobre 2022

package tp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BombExploder {
	
    public static void main(String[] args) throws FileNotFoundException {
    	
        if(args.length > 0) {
            readBoard(args[0]);
            return;
        }


        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Board bomb = new Board(n, m);

        bomb.printBoard();

        int count = 1;

        int result = 0;
        while((result = bomb.activateBomb()) == 1) {
            count++;
        }

        if (result == 0) {
            System.out.printf("Solving this board required %d iterations.\r\n", count);     // print the number of iterations that is needed to execute the BombExploder
        } 

        sc.close();
    }

    public static void readBoard(String filename) throws FileNotFoundException
    {
        File myObj = new File(filename);
        Scanner sc = new Scanner(myObj);

        while(sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();

            for (int i = 0; i < n; i++) {
                ArrayList<Integer> row = new ArrayList<Integer>();
                
                for (int j = 0; j < m; j++) {
                    int value = sc.nextInt();
                    row.add(value);
                }
                board.add(row);
            }
    

            Board bomb = new Board(n, m);
            bomb.setBoard(board);
            System.out.println(n + " " + m);
            bomb.printBoard();

            int count = 1;
    
            int result = 0;
            while((result = bomb.activateBomb()) == 1) {
                count++;
            }
    
            if (result == 0) {
                System.out.printf("Solving this board required %d iterations.\r\n", count);     // print the number of iterations that is needed to execute the BombExploder
            } 
        }

        sc.close();
    }
}