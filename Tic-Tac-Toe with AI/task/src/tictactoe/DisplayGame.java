package tictactoe;

public class DisplayGame {
    public void display(String input) {
        for(int i=0; i<9; i++) {
            System.out.print("-");
        }
        System.out.println();

        int brk = 1;

        for(int i=0; i< input.length(); i++) {
            if(brk == 1) {
                System.out.print("| ");
            }
            System.out.print(input.charAt(i) + " ");

            if(brk == 3) {
                System.out.println("|");
                brk = 0;
            }
            brk++;
        }
        for(int i=0; i<9; i++) {
            System.out.print("-");
        }
        System.out.println();

    }
}
