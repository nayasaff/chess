package Board;
import Pieces.*;

import java.util.ArrayList;

public class Block {
    int x;
    int y;
    Piece piece;


    public Block(int y, int x ,Piece piece){
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y){
        this.y =y;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public ArrayList<Integer[]> getAvailableMove(Game board){
        return this.getPiece().getAvailableMoves(getY(), getX(), board);
    }

    public Block deepCopy(){
        return new Block(this.getY(), this.getX(), this.getPiece());
    }







}
