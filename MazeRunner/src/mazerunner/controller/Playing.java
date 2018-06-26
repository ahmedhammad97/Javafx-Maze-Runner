
package mazerunner.controller;


public class Playing implements state{

    @Override
    public void doAction(Clock t) {
        t.cont();
    }
    
}
