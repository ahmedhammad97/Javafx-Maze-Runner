
package mazerunner.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class GameCharacter implements Cell {

    private int positionX;
    private int positionY;
    private int indexX;
    private int indexY;
    private Image img;

    public GameCharacter(int x, int y){
        this.positionY = y;
        this.positionX = x;
    }
    
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
        this.setIndexX(positionX/Game.getGame().getImgSize());
    }

    public void setPositionY(int posiitonY) {
        this.positionY = posiitonY;
        this.setIndexY(positionY/Game.getGame().getImgSize());
    }
    
    public int getIndexX() {
        return indexX;
    }

    
    public void setIndexX(int indexX) {
        this.indexX = indexX;
    }

    
    public int getIndexY() {
        return indexY;
    }

    
    public void setIndexY(int indexY) {
        this.indexY = indexY;
    }
    
    @Override
    public void draw(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(img, positionX, positionY, Game.getGame().getImgSize(), Game.getGame().getImgSize());
    }
    
    @Override
    public Image getImage(){
        return img;
    }
    
    @Override
    public void setImage(Image i){
        this.img = i;
    }
    
    
}
