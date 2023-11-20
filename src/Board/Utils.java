package Board;

import java.util.ArrayList;

public class Utils {


    public static Integer[][] miniMax(Game currentBoard) {
        Integer[] initialMove = null;
        Integer[] bestMove = null;
        int minimum_value = Integer.MAX_VALUE;

        Game copyBoard = currentBoard.copy();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (copyBoard.getBoard()[i][j].getPiece() != null && !copyBoard.getBoard()[i][j].getAvailableMove(copyBoard).isEmpty()) {
                    ArrayList<Integer[]> allAvailableMoves = copyBoard.getBoard()[i][j].getAvailableMove(copyBoard);
                    for (Integer[] move : allAvailableMoves) {
                        copyBoard.movePiece(copyBoard.getBoard(), new int[]{i, j}, new int[]{move[0], move[1]});
                        int value = minimaxHelper(5,false ,copyBoard);
                        if (value < minimum_value) {
                            initialMove = new Integer[]{i, j};
                            minimum_value = value;
                            bestMove = move;
                        }
                    }
                }
            }
        }
        System.out.println(initialMove);
        return new Integer[][]{initialMove, bestMove};
    }

    public static int minimaxHelper(int depth, boolean isMaximizingPlayer, Game copyBoard){
        if(depth == 0 || copyBoard.isGameOver()){
            int res = copyBoard.evaluateBoard();
            System.out.println(res);
            return res;
        }

        int maximum_value = Integer.MIN_VALUE;
        int minimum_value = Integer.MAX_VALUE;
        if(isMaximizingPlayer){
            for (int i = 0; i < copyBoard.getBoard().length; i++) {
                for (int j = 0; j < copyBoard.getBoard()[i].length; j++) {
                    if (copyBoard.getBoard()[i][j].getPiece() != null && !copyBoard.getBoard()[i][j].getAvailableMove(copyBoard).isEmpty()) {
                        ArrayList<Integer[]> allAvailableMoves = copyBoard.getBoard()[i][j].getAvailableMove(copyBoard);
                        for (Integer[] move : allAvailableMoves) {

                            copyBoard.movePiece(copyBoard.getBoard(), new int[]{i, j}, new int[]{move[0], move[1]});
                            int eval = minimaxHelper(depth - 1, false, copyBoard);
                            maximum_value = Math.max(maximum_value, eval);


                        }
                    }
                }
            }
            return maximum_value;
        }

        else{
            for (int i = 0; i < copyBoard.getBoard().length; i++) {
                for (int j = 0; j < copyBoard.getBoard()[i].length; j++) {
                    if (copyBoard.getBoard()[i][j].getPiece() != null && !copyBoard.getBoard()[i][j].getAvailableMove(copyBoard).isEmpty()) {
                        ArrayList<Integer[]> allAvailableMoves = copyBoard.getBoard()[i][j].getAvailableMove(copyBoard);

                        for (Integer[] move : allAvailableMoves) {
                            copyBoard.movePiece(copyBoard.getBoard(), new int[]{i, j}, new int[]{move[0], move[1]});
                            int eval = minimaxHelper(depth - 1,  true, copyBoard);
                            minimum_value = Math.min(minimum_value, eval);

                        }
                    }
                }
            }
            return minimum_value;
        }

    }

}
