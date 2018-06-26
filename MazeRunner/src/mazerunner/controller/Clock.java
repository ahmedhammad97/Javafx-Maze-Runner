
package mazerunner.controller;

import mazerunner.controller.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;


public class Clock {
    private List observers = new ArrayList<Observer>();
    boolean paused=false;
    private int seconds = 0;
    private Timer t = new Timer();
   
    private TimerTask tt = new TimerTask() {        
        @Override
        public void run() {  
            if(!paused){
            seconds++;
            notifyObserver();
            }
        }
    };
    
    
    public Clock(){
      
    }
    
    
    public void notifyObserver(){
        
        Platform.runLater(new Runnable() {
            public void run() {
                for(Object x : observers){
                    Observer  y = (Observer) x;
                    y.update(seconds);
                } 
            }
        });        
    }
    
    public void start(int x, int y){
        t.scheduleAtFixedRate(tt, x, y);
    }
    
    public void stop(){
        t.cancel();
    }
    
    public void setObserver(Observer o){
        observers.add(o);
    }
    
    public void pause(){
        paused=true;
        
    }
    public void cont(){
        paused=false;
    }
    
    public int getTime(){
        return seconds;
    }
    
    public void setTime(int sec){
        this.seconds = sec;
        notifyObserver();
    }
}
