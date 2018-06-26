
package mazerunner.model;

import java.net.URL;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class WeakBomb extends Bomb implements java.io.Serializable{

    public WeakBomb(int x, int y) {
        super(x, y);
        URL u = WeakBomb.class.getResource("/Images/b.png");
        super.setImage(new Image(u.toString()));
    }

    

    @Override
    public boolean isDestroyable() {
        return true;
    }
    
}
