package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import Board.Utils;

import Board.Game;
import Pieces.*;
public class View extends JFrame {

    private Game game;
    private JPanel[][] panels;
    private JPanel initialPanel;
    private int[] initialMove;
    private final Color darkBlock = new Color(254, 206,158);
    private final Color lightBlock = new Color(208,137, 71);
    private final Color darkBlockMove = new Color(170,162,58);
    private final Color lightBlockMove = new Color(205,210,106);

    private boolean isPlayerTurn;


    public String printArray(ArrayList<Integer[]> array){
        String result = "";
        for(int i =0; i < array.size(); i++) {
            for (int j = 0; j < array.get(i).length; j++)
                result += array.get(i)[j] + " ";
            result += "\n";
        }
        return result;
    }
    public View(){
        game = new Game();
        panels = new JPanel[8][8];
        isPlayerTurn = true;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8,8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int finalI = i;
                final int finalJ = j;
                panels[i][j] = new JPanel();

                panels[i][j].setBackground((i + j) % 2 == 0 ? darkBlock : lightBlock);
                Piece currentPiece = game.getBoard()[i][j].getPiece();

                if(currentPiece != null){
                    String color = currentPiece.getColor() == Color.BLACK ? "black" : "white";
                    String piece = currentPiece.toString().toLowerCase();
                    URL image = getClass().getResource("/Assets/" +color + " " + piece + ".png");
                    JLabel imgLabel = new JLabel(new ImageIcon(image));

                    panels[i][j].add(imgLabel);
                }

                if(isPlayerTurn) {
                    panels[i][j].addMouseListener(new MouseListener() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Piece currentPiece = game.getBoard()[finalI][finalJ].getPiece();
                            if (currentPiece != null && currentPiece.getColor() == Color.WHITE) {
                                initialPanel = panels[finalI][finalJ];
                                initialMove = new int[]{finalI, finalJ};
                                getPieceMove(currentPiece, finalI, finalJ);
                            } else if (panels[finalI][finalJ].getBackground() == lightBlockMove || panels[finalI][finalJ].getBackground() == darkBlockMove) {
                                movePiece(finalI, finalJ);
                                isPlayerTurn = false;
                                handleComputerTurn();
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });
                }

                add(panels[i][j]);
            }
        }



        setSize(600,600);
        setVisible(true);
    }

    public void getPieceMove(Piece currentPiece, int finalI, int finalJ){
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if ((k + l) % 2 == 0) {
                    panels[k][l].setBackground(darkBlock);
                } else {
                    panels[k][l].setBackground(lightBlock);
                }
            }
        }

        ArrayList<Integer []> moves = currentPiece.getAvailableMoves(finalJ,finalI, game);
        panels[finalI][finalJ].setBackground((finalI + finalJ) %2 == 0 ? darkBlockMove: lightBlockMove);
        for(Integer[] move : moves){

            if(panels[move[0]][move[1]].getBackground() == lightBlock)
                panels[move[0]][move[1]].setBackground(lightBlockMove);
            else if (panels[move[0]][move[1]].getBackground() ==  darkBlock)
                panels[move[0]][move[1]].setBackground(darkBlockMove);
        }
    }

    public void movePiece(int finalI, int finalJ){
        if(panels[finalI][finalJ] != initialPanel){
            Component jPanelComponent = initialPanel.getComponents()[0];
            initialPanel.removeAll();
            initialPanel.revalidate();
            initialPanel.repaint();
            panels[finalI][finalJ].add(jPanelComponent);
            panels[finalI][finalJ].revalidate();
            panels[finalI][finalJ].repaint();
            game.movePiece(initialMove, new int[]{finalI, finalJ});
            System.out.println(game.toString());
        }

        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if ((k + l) % 2 == 0) {
                    panels[k][l].setBackground(darkBlock);
                } else {
                    panels[k][l].setBackground(lightBlock);
                }
            }
        }

    }

    public void moveComputerPiece(Integer[][] move){
        int initialMoveI = move[0][0];
        int initialMoveJ  = move[0][1];

        int finalMoveI = move[1][0];
        int finalMoveJ = move[1][1];

        Component jPanelComponent = panels[initialMoveI][initialMoveJ].getComponents()[0];
        panels[initialMoveI][initialMoveJ].removeAll();
        panels[initialMoveI][initialMoveJ].revalidate();
        panels[initialMoveI][initialMoveJ].repaint();

        panels[finalMoveI][finalMoveJ].add(jPanelComponent);
        panels[finalMoveI][finalMoveJ].revalidate();
        panels[finalMoveI][finalMoveJ].repaint();

    }

    public void handleComputerTurn(){
        Integer[][] move = Utils.miniMax(game);
        moveComputerPiece(move);
        game.movePiece(new int[]{move[0][0], move[0][1]}, new int[]{move[1][0], move[1][1]});

        isPlayerTurn = true;
    }

}
