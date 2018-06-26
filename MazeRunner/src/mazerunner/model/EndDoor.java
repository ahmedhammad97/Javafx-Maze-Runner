
package mazerunner.model;

import java.net.URL;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class EndDoor extends GameCharacter implements java.io.Serializable {
    private static EndDoor d = new EndDoor(1150,550);
    
    
    
    private EndDoor(int x, int y) {
        super(x,y);
        URL u = EndDoor.class.getResource("/Images/d.png");
        super.setImage(new Image(u.toString()));
    }


    @Override
    public boolean isDestroyable() {
        return false;
    }
    
    public static EndDoor getDoor(){
        return d;
    }
    
    public EndDoor setPosition(int x, int y){
        super.setPositionX(x);
        super.setPositionY(y);
        return d;
    }
    
}
