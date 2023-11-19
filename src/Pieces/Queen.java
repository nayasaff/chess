package Pieces;

import Board.Game;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(Color color) {
        super(color, color == Color.BLACK ? -90 : 90);
    }

    public String toString(){
        return "Queen";
    }


    @Override
    public ArrayList<Integer[]> getAvailableMoves(int currentX, int currentY, Game board) {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0},{-1,-1}, {-1,1}, {1,-1}, {1,1}};
        ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
        for(int i =0; i < directions.length; i++){
            int x = currentX + directions[i][1];
            int y = currentY + directions[i][0];

            while (board.isValidMove(y, x, this.getColor())) {
                moves.add(new Integer[]{y, x});
                if(board.getBoard()[y][x].getPiece() != null && board.getBoard()[y][x].getPiece().getColor() != this.getColor() ){
                    i++;
                    break;
                }
                x += directions[i][1];
                y += directions[i][0];
            }
        }
        return moves;
    }
}
