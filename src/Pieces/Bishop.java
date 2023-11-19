package Pieces;

import Board.Game;

import java.awt.Color;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color,color == Color.BLACK ? -30 : 30);
    }

    public String toString(){
        return "Bishop";
    }

    public ArrayList<Integer[]> getAvailableMoves(int currentX, int currentY, Game board){
        ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
        int [][] directions = {{-1,-1}, {-1,1}, {1,-1}, {1,1}};
        for(int i =0; i < directions.length; i++){
            int dy = directions[i][0];
            int dx = directions[i][1];
            int x =currentX + dx;
            int y = currentY + dy;
            while(board.isValidMove(y, x, getColor())){
                moves.add(new Integer[]{y,x});
                if(board.getBoard()[y][x].getPiece() != null && board.getBoard()[y][x].getPiece().getColor() != this.getColor() ){
                    i++;
                    break;
                }
                x += dx;
                y += dy;
            }
        }
        return moves;
    }


}
