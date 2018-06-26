
package mazerunner.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public interface Cell {
    public Image getImage();
    public void setImage(Image i);
    public void draw(Canvas canvas);
    public boolean isDestroyable();
}
