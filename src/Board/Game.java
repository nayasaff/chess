package Board;
import Pieces.*;

import java.awt.Color;
import java.util.ArrayList;

public class Game {
    private final Block[][] board;

    private Block whiteKingBlock;
    private Block blackKingBlock;

    public Game(){
        this.board = new Block[8][8];
        for(int i =0;  i < 8; i++){
            for(int j =0; j < 8; j++){
                if(i == 1){
                    board[i][j] = new Block(i, j, new Pawn(Color.BLACK)) ;
                }
                else if (i == 6) {
                    board[i][j] = new Block(i, j,  new Pawn(Color.WHITE));
                }
                else if(i  == 0){
                    if(j == 0 || j == 7){
                        board[i][j] = new Block( i,j, new Rook(Color.BLACK));
                    }
                    else if(j == 1 || j == 6){
                        board[i][j] = new Block(i, j, new Knight(Color.BLACK));
                    }
                    else if(j == 2 || j == 5){
                        board[i][j] = new Block(i, j, new Bishop(Color.BLACK) );
                    }
                    else if(j == 3){
                        Block blackKing = new Block(i, j , new King(Color.BLACK));
                        board[i][j] = blackKing;
                        this.blackKingBlock = blackKing;
                    }
                    else{
                        board[i][j] = new Block(i, j, new Queen(Color.BLACK));
                    }
                }
                else if(i  == 7){
                    if(j == 0 || j == 7){
                        board[i][j] = new Block( i,j, new Rook(Color.WHITE));
                    }
                    else if(j == 1 || j == 6){
                        board[i][j] = new Block(i,j, new Knight(Color.WHITE));
                    }
                    else if(j == 2 || j == 5){
                        board[i][j] = new Block(i , j,new Bishop(Color.WHITE));
                    }
                    else if(j == 3){
                        Block whiteKing =  new Block(i,j,new King(Color.WHITE));
                        board[i][j] = whiteKing;
                        this.whiteKingBlock = whiteKing;
                    }
                    else{
                        board[i][j] = new Block(i,j,new Queen(Color.WHITE));
                    }
                }
                else{
                    board[i][j] = new Block(i, j, null);
                }
            }
        }

    }


    public Game(Block[][] board, Block whiteKingBlock, Block blackKingBlock){
        this.board = board;
        this.whiteKingBlock = whiteKingBlock;
        this.blackKingBlock = blackKingBlock;
    }

    public String toString(){
        String board_string = "";
        for(int i = 0; i < 8; i++){
            for(int j =0; j < 8; j++){
                board_string += board[i][j].getPiece() + " ";
            }
            board_string += "\n";
        }
        return board_string;
    }

    public Block[][] getBoard(){
        return board;
    }


    public Block getWhiteKing() {
        return whiteKingBlock;
    }

    public Block getBlackKing() {
        return blackKingBlock;
    }

    public boolean isValidMove(int y, int x, Color currentColor){
        if((x < 0 ||  x >7) || (y < 0 || y > 7))
            return false;
        Piece attackPiece = board[y][x].getPiece();
        return this.getBoard()[y][x].getPiece() == null || attackPiece.getColor() != currentColor;
    }

    public Game copy() {
        Block[][] copyBoard = new Block[8][8];
        Block newWhiteKingBlock;
        Block newBlackKing;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Block newBlock = board[i][j].deepCopy();
                copyBoard[i][j] = newBlock;
                if(copyBoard[i][j].getPiece() instanceof King && copyBoard[i][j].getPiece().getColor() == Color.WHITE)
                    newWhiteKingBlock = newBlock;
                else if(copyBoard[i][j].getPiece() instanceof King && copyBoard[i][j].getPiece().getColor() == Color.BLACK)
                    newBlackKing = newBlock;
            }
        }

        return new Game(copyBoard, whiteKingBlock, blackKingBlock);
    }

    public void movePiece(int [] initialMove,int[] finalMove){ //return copy of board
        int initialMoveX = initialMove[1];
        int initialMoveY = initialMove[0];

        int finalMoveX = finalMove[1];
        int finalMoveY = finalMove[0];

        Piece pieceToMove = board[initialMoveY][initialMoveX].getPiece();
        board[initialMoveY][initialMoveX].setPiece(null);
        board[finalMoveY][finalMoveX].setPiece(pieceToMove);

        ArrayList<Integer[]> allMoves = board[finalMoveY][finalMoveX].getAvailableMove(this);
        for(Integer[] move : allMoves){
            if(board[move[1]][move[1]].getPiece() instanceof King checkedKing){
                checkedKing.setCheckmate(true);
            }
        }

    }

    public int evaluateBoard(){
        int whiteScore = 0;
        int blackScore = 0;

        for(int i =0; i < board.length; i++){
            for(int j =0; j < board.length; j++){
                if(board[i][j].getPiece() != null && board[i][j].getPiece().getColor() == Color.BLACK)
                    blackScore += board[i][j].getPiece().getPoint();
                else if(board[i][j].getPiece() != null && board[i][j].getPiece().getColor() == Color.WHITE)
                    whiteScore += board[i][j].getPiece().getPoint();
            }
        }
        return whiteScore + blackScore;
    }

    public void retrievePiece(Piece newPiece, Block block){
        block.setPiece(newPiece);
    }

    public void movePiece(Block[][] copyBoard, int [] initialMove,int[] finalMove){

        int initialMoveX = initialMove[1];
        int initialMoveY = initialMove[0];

        int finalMoveX = finalMove[1];
        int finalMoveY = finalMove[0];

        Piece pieceToMove = copyBoard[initialMoveY][initialMoveX].getPiece();
        copyBoard[initialMoveY][initialMoveX].setPiece(null);
        copyBoard[finalMoveY][finalMoveX].setPiece(pieceToMove);

    }

    public int getValue(int[] finalMove){
        int finalMoveX = finalMove[1];
        int finalMoveY = finalMove[0];

        if(board[finalMoveY][finalMoveX] == null)
            return 0;

        return board[finalMoveY][finalMoveX].getPiece().getPoint();
    }



    public boolean isGameOver(){
        King whiteKing = (King) whiteKingBlock.getPiece();
        King blackKing = (King) blackKingBlock.getPiece();
        if(whiteKing.isCheckmate() && whiteKingBlock.getAvailableMove(this).isEmpty())
            return true;
        return blackKing.isCheckmate() && blackKingBlock.getAvailableMove(this).isEmpty();
    }

    public String printArray(ArrayList<Integer[]> array){
        StringBuilder r = new StringBuilder();
        for(Integer[] a : array){
            for(int s : a)
                r.append(s).append(" ");
            r.append('\n');
        }
        return r.toString();
    }






}
