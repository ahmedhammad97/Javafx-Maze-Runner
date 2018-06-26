
package mazerunner.model;

import java.net.URL;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class WoodWall extends Wall implements java.io.Serializable {

    public WoodWall(int x, int y) {
        super(x, y);
        URL u = WoodWall.class.getResource("/Images/w.png");
        super.setImage(new Image(u.toString()));
    }

    @Override
    public boolean isDestroyable() {
        return true;
    }
    
}
