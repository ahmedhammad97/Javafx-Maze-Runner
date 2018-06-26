
package mazerunner.model;

import java.net.URL;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class Player extends GameCharacter implements java.io.Serializable{
    private static Player p = new Player(0,0);
    private boolean isBlue=true;
    private boolean left=true;
    private Image blueLeftImg;
    private Image blueRightImg;
    private Image greenLeftImg;
    private Image greenRightImg;


    private Player(int x, int y) {
        super(x, y);
        URL u = Player.class.getResource("/Images/L.png");
        blueLeftImg = new Image(u.toString());
        u = Player.class.getResource("/Images/R.png");
        blueRightImg = new Image(u.toString());
        u = Player.class.getResource("/Images/A.png");
        greenLeftImg = new Image(u.toString());
        u = Player.class.getResource("/Images/D.png");
        greenRightImg = new Image(u.toString());
    }

    
    public static Player getPlayer(){
        return p;
    }
    
    public void setType(boolean x){
        isBlue = x;
    }

    

    @Override
    public boolean isDestroyable() {
        return false;
    }
    
    public boolean isLeft(){
        return left;
    }
    
    public void rotate(){
        if(this.isLeft() && this.isBlue){super.setImage(blueRightImg);}
        else if(this.isBlue){super.setImage(blueLeftImg);}
        else if(this.left){super.setImage(greenRightImg);}
        else{super.setImage(greenLeftImg);}
        this.left = !left;
    }
    
    public void setSuperImg(){
        if(p.isBlue && p.left){super.setImage(blueLeftImg);}
        else if(p.isBlue){super.setImage(blueRightImg);}
        else if(p.left){super.setImage(greenLeftImg);}
        else{super.setImage(greenRightImg);}
    }
    
    public char getLetter(){
        if(p.isBlue && p.left){return 'L';}
        else if(p.isBlue){return 'R';}
        else if(p.left){return 'A';}
        else{return 'D';}
    }
    
    public GameCharacter setAttr(int x, int y, boolean blue, boolean left){
        super.setPositionX(x);
        super.setPositionY(y);
        this.isBlue=blue;
        this.left=left;
        setSuperImg();
        return getPlayer();
    }
    
    
}
