package Pieces;
import Board.Game;
import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean isInitialMove;

    public Pawn(Color color) {
        super(color,  color == Color.BLACK ? -10 : 10);
        this.isInitialMove = true;
    }

    public String toString(){
        return "Pawn";
    }

    public boolean isInitialMove(){
        return isInitialMove;
    }

    public void setInitialMove(boolean isInitialMove){
        this.isInitialMove = isInitialMove;
    }


    @Override
    public ArrayList<Integer[]> getAvailableMoves(int currentX, int currentY, Game board) {
        ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
        int[][] blackDirections = {{1,0}, {2,0}, {1, 1}};
        int [][] whiteDirections = {{-1,0}, {-2,0}, {-1,-1}};

        for(int i =0; i < blackDirections.length; i++){
            if(this.getColor() == Color.BLACK){
                int blackY = currentY + blackDirections[i][0];
                int blackX = currentX + blackDirections[i][1];
                if(board.isValidMove(blackY, blackX, getColor()) && i != 2 )
                    moves.add(new Integer[]{blackY, blackX } );
                else if(blackY > -1 && blackY < 8 && blackX > -1 && blackX < 8 &&
                        board.getBoard()[blackY][blackX].getPiece() != null &&
                        board.getBoard()[blackY][blackX].getPiece().getColor() == Color.WHITE  && i == 2 )
                    moves.add(new Integer[]{blackY, blackX } );
            }
            else{
                int whiteY = currentY + whiteDirections[i][0];
                int whiteX = currentX + whiteDirections[i][1];
                if(board.isValidMove(whiteY, whiteX, getColor()) && i != 2 )
                    moves.add(new Integer[]{whiteY, whiteX } );
                else if(whiteY > -1 && whiteY < 8 && whiteX > -1 && whiteX < 8
                        && board.getBoard()[whiteY][whiteX].getPiece() != null &&
                        board.getBoard()[whiteY][whiteX].getPiece().getColor() == Color.BLACK  && i == 2 )
                    moves.add(new Integer[]{whiteY, whiteX } );
            }

        }
        return moves;
    }
}
