package tictactoe;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

import static tictactoe.Main.sc;

public class MoveMaking extends InputChecking{
    int  row, col;
    char player;
    String opponent;
    int cond, count, Cord, gap = -1;
    private void EasyLevel() {
        Random compInp = new Random();
        boolean CompCorrectMove = false;
        while (!CompCorrectMove) {
            row = compInp.nextInt(1, 4);
            col = compInp.nextInt(1, 4);
            if (input.charAt(stringCord(row, col)) == ' ') {
                CompCorrectMove = true;
            }
        }
        Cord = stringCord(row, col);
    }

    private boolean gapCheck(int i, int j, char player) {
        count++;
        if(input.charAt(stringCord(i, j)) == ' ') {
            gap = stringCord(i, j);
        } else if(input.charAt(stringCord(i, j)) == player) {
            cond++;
        }
        if(cond == 2 && count == 3 && gap != -1) {
            Cord = gap;
            cond = 0;
            gap = -1;
            return true;
        } else if(count == 3){
            cond = 0;
            gap = -1;
        }
        return false;
    }


    private boolean MediumLevel(char player) {
        for(int i=1; i<=3; i++) {
            //Horizontal Check
            count = 0;
            for(int j=1; j<=3; j++) {
                if(gapCheck(i, j, player)) {
                    return true;
                }
            }
            // Vertical Check
            count = 0;
            for(int j=1; j<=3; j++) {
                if(gapCheck(j, i, player)) {
                    return true;
                }
            }
        }
        // Right Diagonal Check
        count = 0;
        for(int i=1; i<=3; i++) {
            if(gapCheck(i, i, player)) {
                return true;
            }
        }
        // Left Diagonal Check
        count = 0;
        int j = 1;
        for(int i=3; i>=1; i--) {
            if(gapCheck(i, j, player)) {
                return true;
            }
            j++;
        }
        return false;
    }

    private int[] minimax(StringBuilder Inputs, boolean maximize, char player) {
        int res = resultChecking(Inputs, player);
        if(res == 0) {
            return new int[]{0,-1};
        } else if(res == 1 && player == 'X') {
            return new int[]{1,-1};
        } else if(res == 1 && player == 'O') {
            return new int[]{-1,-1};
        }

        ArrayList<Integer> blocksNo = emptyBlock(Inputs.toString());
        int[] bestMove = new int[]{-2,-1};
        if(maximize) {
            int maxNo = Integer.MIN_VALUE;
            for (Integer integer : blocksNo) {
                StringBuilder InputsCopy = new StringBuilder(Inputs);
                InputsCopy.setCharAt(integer, 'X');
                int posVal = minimax(InputsCopy, false, 'X')[0];
                if (posVal > maxNo) {
                    maxNo = posVal;
                    bestMove[0] = maxNo;
                    bestMove[1] = integer;
                }
            }
            return bestMove;
        } else {
            int minNo = Integer.MAX_VALUE;
            for (Integer integer : blocksNo) {
                StringBuilder InputsCopy = new StringBuilder(Inputs);
                InputsCopy.setCharAt(integer, 'O');
                int posVal = minimax(InputsCopy, true, 'O')[0];
                if (posVal < minNo) {
                    minNo = posVal;
                    bestMove[0] = minNo;
                    bestMove[1] = integer;
                }
            }
            return bestMove;
        }
    }
    private int hard() {
        if(emptyBlock(input.toString()).size() == 9) {
            return 1;
        } else if(player == 'X') {
            Cord = minimax(input, true, player)[1];
        } else if(player == 'O') {
            Cord = minimax(input, false, player)[1];
        }

        return 0;
    }

    private ArrayList<Integer> emptyBlock(String InputsCopy) {
        ArrayList<Integer> emptyBlocks = new ArrayList<>();
        for(int i=0; i<InputsCopy.length(); i++) {
            if(InputsCopy.charAt(i) == ' ') {
                emptyBlocks.add(i);
            }
        }
        return emptyBlocks;
    }

    public void enteringMove(String[] InputCmds, DisplayGame displayGame) {
        player = 'X';
        char prevPlayer = player;
        displayGame.display(input.toString());
        boolean gameFinish = false;
        int playerChange = 2;

        while(!gameFinish) {
            opponent = InputCmds[playerChange % 2 + 1];
            switch (opponent) {
                case "user" :
                    System.out.print("Enter the coordinates: ");
                    boolean UserCorrectMove = false;
                    while(!UserCorrectMove) {
                        try{
                            row = sc.nextInt();
                            col = sc.nextInt();
                            if(coordinatesCheck(row, col)) {
                                UserCorrectMove = true;
                            }
                        } catch (InputMismatchException numExc) {
                            System.out.println("You should enter numbers!");
                        }
                        sc.nextLine();
                    }
                    Cord = stringCord(row, col);
                    break;
                case "easy" :
                    System.out.println("Making move level \"easy\"");
                    EasyLevel();
                    break;
                case "medium" :
                    System.out.println("Making move level \"medium\"");
                    if(!MediumLevel(player)) {
                        if(!MediumLevel(prevPlayer)) {
                            EasyLevel();
                        }
                    }
                    break;
                case "hard" :
                    System.out.println("Making move level \"hard\"");
                    if(hard() == 1) {
                        EasyLevel();
                    }
            }
            input.setCharAt(Cord, player);
            displayGame.display(input.toString());
            int res = resultChecking(input, player);
            if(res == 1 || res == 0) {
                gameFinish = true;
                input = new StringBuilder("         ");
                if(res == 1) {
                    System.out.println(player + " wins");
                } else  {
                    System.out.println("Draw");
                }
            }
            if(player == 'X') {
                player = 'O';
                prevPlayer = 'X';
            } else {
                player = 'X';
                prevPlayer = 'O';
            }
            playerChange++;
        }
    }


    private int resultChecking(StringBuilder input, char player) {
        int peek_Loc = 4;
        for(int i=1; i<=4; i++) {
            if (input.charAt(peek_Loc - i) == player &&
                    input.charAt(peek_Loc + i) == player &&
                    input.charAt(peek_Loc) == player) {
                return 1;
            }
        }
        peek_Loc = 1;
        for(int i=0; i<2; i++) {
            if(input.charAt(peek_Loc - 1) == player &&
                    input.charAt(peek_Loc + 1) == player &&
                    input.charAt(peek_Loc) == player) {
                return 1;
            } else {
                peek_Loc = 7;
            }
        }
        peek_Loc = 3;
        for(int i=0; i<2; i++) {
            if(input.charAt(peek_Loc - 3) == player &&
                    input.charAt(peek_Loc + 3) == player &&
                    input.charAt(peek_Loc) == player) {
                return 1;
            } else {
                peek_Loc = 5;
            }
        }
        if(emptyBlock(input.toString()).size() == 0) {
            return 0;
        }
        return -1;
    }
}
