
package mazerunner.model;

import java.net.URL;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class StealerMonster extends Monster implements java.io.Serializable{

    public StealerMonster(int x, int y) {
        super(x, y);
        URL u = StealerMonster.class.getResource("/Images/m.png");
        super.setImage(new Image(u.toString()));
    }

   

    @Override
    public boolean isDestroyable() {
        return true;
    }
    
}
