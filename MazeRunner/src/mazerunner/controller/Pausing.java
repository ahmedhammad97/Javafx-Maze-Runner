
package mazerunner.controller;


public class Pausing implements state {

    @Override
    public void doAction(Clock t) {
        t.pause();
    }
    
}
