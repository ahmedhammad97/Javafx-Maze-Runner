
package mazerunner.model;

import java.net.URL;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class StoneWall extends Wall implements java.io.Serializable{

    public StoneWall(int x, int y) {
        super(x, y);
        URL u = StoneWall.class.getResource("/Images/s.png");
        super.setImage(new Image(u.toString()));
    }

    

    @Override
    public boolean isDestroyable() {
        return false;
    }
    
}
