package Pieces;

import Board.Game;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, color == Color.BLACK ? -30 : 30 );
    }

    public String toString(){
        return "Knight";
    }


    @Override
    public ArrayList<Integer[]> getAvailableMoves(int currentX, int currentY, Game board) {
        ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
        int [][] directions = {{-2,-1}, {-2,1}, {-1,-2}, {-1, 2}, {1,2}, {1,-2}, {2,1}, {2,-1}};
        for(int[] dir : directions){
            int x = currentX + dir[0];
            int y = currentY + dir[1];
            if(board.isValidMove(y,x, this.getColor()))
                moves.add(new Integer[]{y,x});
        }
        return moves;
    }
}
