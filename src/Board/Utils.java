package Board;

import java.util.ArrayList;

public class Utils {


    public static Integer[] miniMax(Game currentBoard) {
        Integer[] initialMove = null;
        Integer[] bestMove = null;
        int minimum_value = Integer.MAX_VALUE;
        int initialAlpha = Integer.MIN_VALUE;
        int initialBeta = Integer.MAX_VALUE;
        Game copyBoard = currentBoard.copy();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (copyBoard.getBoard()[i][j].getPiece() != null && !copyBoard.getBoard()[i][j].getAvailableMove(copyBoard).isEmpty()) {
                    ArrayList<Integer[]> allAvailableMoves = copyBoard.getBoard()[i][j].getAvailableMove(copyBoard);
                    for (Integer[] move : allAvailableMoves) {
                        copyBoard.movePiece(copyBoard.getBoard(), new int[]{i, j}, new int[]{move[0], move[1]});
                        int value = minimaxHelper(5, initialAlpha, initialBeta ,false ,copyBoard);
                        if (value < minimum_value) {
                            initialMove = new Integer[]{i, j};
                            minimum_value = value;
                            bestMove = move;
                        }
                    }
                }
            }
        }
        return bestMove;
    }

    public static int minimaxHelper(int depth, int alpha, int beta, boolean isMaximizingPlayer, Game copyBoard){
        if(depth == 0 || copyBoard.isGameOver()){
            return copyBoard.evaluateBoard();
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
                            int eval = minimaxHelper(depth - 1, alpha, beta, false, copyBoard);
                            maximum_value = Math.max(maximum_value, eval);
                            alpha = Math.max(alpha, eval);
                            if (beta <= alpha)
                                break; // Beta cut-off

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
                            int eval = minimaxHelper(depth - 1, alpha, beta, true, copyBoard);
                            minimum_value = Math.min(minimum_value, eval);
                            beta = Math.min(beta, eval);
                            if (beta <= alpha)
                                break; // Alpha cut-off
                        }
                    }
                }
            }
            return minimum_value;
        }

    }

}
