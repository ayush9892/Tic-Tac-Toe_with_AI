package tictactoe;
import static tictactoe.Main.sc;

public class InputChecking {
    StringBuilder input = new StringBuilder("         ");
//    public void cellsInputCheck(DisplayGame displayGame) {
//        System.out.print("Enter the cells: ");
//        boolean correctInput = false;
//
//        while (!correctInput) {
//            input = sc.next();
//
//            if((input.length() == 9) && input.matches("[XO_]+?")) {
//                displayGame.display(input);
//                correctInput = true;
//            } else {
//                System.out.println("Your input is wrong, please enter again!");
//            }
//        }
//    }

    public boolean coordinatesCheck(int row, int col) {
        if((row > 0 && row < 4) && (col > 0 && col < 4)) {
            return occurCheck(stringCord(row, col));
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
    }

    private boolean occurCheck(int pos) {
        if(input.charAt(pos) == ' ') {
            return true;
        }
        System.out.println("This cell is occupied! Choose another one!");
        return false;
    }

    public int stringCord(int row, int col) {
        row--;
        if(row == 2) {
            return row*2+col+1;
        } else if(row == 1) {
            return row*2+col;
        } else {
            return row*2+col-1;
        }
    }
}


