package Pieces;
import Board.Game;
import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(Color color) {
        super(color, color == Color.BLACK ? -50 : 50);
    }

    public String toString(){
        return "Rook";
    }

    @Override
    public ArrayList<Integer[]> getAvailableMoves(int currentX, int currentY, Game board) {
        ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i =0; i < directions.length; i++) {
            int dx = directions[i][0];
            int dy = directions[i][1];

            int x = currentX + dx;
            int y = currentY + dy;

            while (board.isValidMove(y, x, getColor())) {
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
