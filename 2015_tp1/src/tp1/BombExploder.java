package tp1;

import java.util.Scanner;

public class BombExploder {
    public static void main(String[] args) {
 
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
            System.out.printf("Solving this board required %d iterations.", count);
        } 

        sc.close();
    }
}