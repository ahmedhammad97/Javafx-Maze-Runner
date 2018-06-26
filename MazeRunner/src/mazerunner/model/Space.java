
package mazerunner.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class Space extends GameCharacter implements java.io.Serializable{

    public Space(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isDestroyable() {
        return false;
    }
    
    @Override
    public void draw(Canvas canvas){
        
    }
    
    @Override
    public Image getImage(){
        return null;
    }
    
    @Override
    public void setImage(Image i){
        
    }
}
