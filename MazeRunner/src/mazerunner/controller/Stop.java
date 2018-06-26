
package mazerunner.controller;


public class Stop implements state{

    @Override
    public void doAction(Clock t) {
        t.stop();
    }
    
}
