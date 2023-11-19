package Pieces;
import Board.Game;
import Board.Block;
import java.awt.*;
import java.util.ArrayList;

public class King extends Piece{

    private boolean isCheck;
    private boolean isCheckmate;
    public King(Color color) {
        super(color, color == Color.BLACK ? -900 : 900);
        this.isCheck = false;
    }

    public String toString(){
        return "King";
    }

    public boolean isCheck(){
        return this.isCheck;
    }

    public void setCheck(boolean isCheck){
        this.isCheck = isCheck;
    }

    public boolean isCheckmate() {
        return isCheckmate;
    }

    public void setCheckmate(boolean isCheckmate){
        this.isCheckmate = isCheckmate;
    }


    public boolean checkmateMove(Integer[] move, Game board){
        for(Block[] vertical : board.getBoard()){
            for(Block horizontal : vertical){
                if(horizontal.getPiece() !=null && !(horizontal.getPiece() instanceof King) && horizontal.getPiece().getColor() != this.getColor() && horizontal.getAvailableMove(board).contains(move)){
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public ArrayList<Integer[]> getAvailableMoves(int currentX, int currentY, Game board) {
        ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
        int directions[][] = {{-1,-1}, {-1,1}, {1,-1}, {1,1}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int [] dir : directions){
            int x = currentX+ dir[1];
            int y = currentY + dir[0];
            if(board.isValidMove(y,x, this.getColor()) && checkmateMove(new Integer[]{y,x}, board) )
                moves.add(new Integer[]{y,x});
        }
        return moves;
    }
}
