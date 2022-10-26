package tictactoe;


import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DisplayGame displayGame = new DisplayGame();
        MoveMaking moveMaking = new MoveMaking();

        boolean correctCmd = false;
        while(!correctCmd) {
            System.out.print("Input command: ");
            String inp = sc.nextLine();
            String[] Cmds = inp.split(" ");
            if(Cmds.length == 3) {
                moveMaking.enteringMove(Cmds, displayGame);
            } else if(Cmds.length == 1 && inp.equals("exit")) {
                correctCmd = true;
            } else {
                System.out.println("Bad parameters!");
            }
        }
        sc.close();
    }
}
