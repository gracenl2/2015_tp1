// IFT2015 TP1
// Auteurs: Jeongeun Lee (20215747)
//          Grace Lee (20174147)
// Date:    Le 28 octobre 2022

package tp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BombExploder {
	
    public static void main(String[] args) throws FileNotFoundException {
    	
    	File myObj = new File(args[0]);

        Scanner sc = new Scanner(myObj);

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
            System.out.printf("Solving this board required %d iterations.", count);     // print the number of iterations that is needed to execute the BombExploder
        } 

        sc.close();
    }
}