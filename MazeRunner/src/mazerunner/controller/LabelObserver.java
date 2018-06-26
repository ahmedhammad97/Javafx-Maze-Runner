
package mazerunner.controller;

import javafx.scene.control.Label;


public class LabelObserver implements Observer {
    private Label l;

    public LabelObserver(Label l){
        this.l = l;
    }
    @Override
    public void update(int val) {
        l.setText(String.valueOf(val));
    }
}
