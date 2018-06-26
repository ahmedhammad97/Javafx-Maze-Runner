
package mazerunner.model;

import java.net.URL;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class BulletGift extends Gift implements java.io.Serializable{

    public BulletGift(int x, int y) {
        super(x, y);
        URL u = BulletGift.class.getResource("/Images/G.png");
        super.setImage(new Image(u.toString()));
    }
    
    
    

    @Override
    public boolean isDestroyable() {
        return true;
    }
    
}
