
package mazerunner.model;

import java.net.URL;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class KillerMonster extends Monster implements java.io.Serializable {

    public KillerMonster(int x, int y) {
        super(x, y);
        URL u = EndDoor.class.getResource("/Images/M.png");
        super.setImage(new Image(u.toString()));
    }

    

    @Override
    public boolean isDestroyable() {
        return true;
    }
    
}
