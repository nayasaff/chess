package Board;

public class Player {

    private boolean isWhite;

    public Player(boolean isWhite){
        this.isWhite = isWhite;
    }

    public boolean isWhite(){
        return isWhite;
    }

    public void setWhite(boolean isWhite){
        this.isWhite = isWhite;
    }

}
