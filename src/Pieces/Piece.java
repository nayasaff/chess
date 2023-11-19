package Pieces;
import Board.Game;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Piece{
    private Color color;
    private int point;
    public Piece(Color color, int point ){
        this.color = color;
        this.point = point;
    }

    public Color getColor(){
        return this.color;
    }

    public int getPoint(){
        return point;
    }
    public abstract ArrayList<Integer[]> getAvailableMoves(int currentX, int currentY, Game board);



}