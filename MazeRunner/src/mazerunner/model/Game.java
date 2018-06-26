
package mazerunner.model;

import mazerunner.controller.LabelObserver;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class Game {

    private static Game g = new Game();
    List imgNames = new ArrayList<Character>();
    private Color c;
    private Player p = Player.getPlayer();
    private int bullets=6;
    private int health=100;
    private int score=0;
    private int n = 15;
    private List charArr;
    private int imgSize;
    private LabelObserver scoreObserver;
    private LabelObserver healthObserver;
    private LabelObserver bulletsObserver;
    
    
    private Game(){initializeImageNames();}
    
    public static Game getGame(){
        return g;
    }
    
    private void initializeImageNames(){
        imgNames.add("m");imgNames.add("M");imgNames.add("g");imgNames.add("G");imgNames.add("b");imgNames.add("B");imgNames.add("s");imgNames.add("w");
        imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");
        imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");imgNames.add(" ");
    }
    
    public ArrayList<GameCharacter> generateMap(int n){
        ArrayList y = new ArrayList<Character>();
        for(int i=0;i<n;i++){
            for(int k=0;k<2*n;k++){
                int x = (int) (Math.random()*imgNames.size());
                y.add(imgNames.get(x));
            }
        }
        
        y.remove(0);y.add(0, Player.getPlayer().getLetter());
        y.remove(y.size()-1);y.add('d');
        CellFactory c = new CellFactory(y,n);
        return c.getNewMap();
    }
    
    public int getN(){
        return n;
    }
    
    public int getHealth() {
        return health;
    }

    
    public void setHealth(int health) {
        this.health = health;
        healthObserver.update(health);
        
    }
    
    public void setScore(int score){
        this.score = score;
        scoreObserver.update(score);
    }
    
    public void setBullets(int n){
        this.bullets = n;
        bulletsObserver.update(bullets);
    }
    
    public void incrementScore(int n){
        score+=n;
        scoreObserver.update(score);
    }
    
    public Player getPlayer(){
        return p;
    }
    
    public void addBullet(){this.bullets++;bulletsObserver.update(bullets);}
    public boolean fireBullet(){if(bullets>0){this.bullets--;bulletsObserver.update(bullets);return true;}return false;}
    
    public void setSize(String s){
        switch(s){
            case "Easy" : n=15; break;
            case "Moderate" : n=20; break;
            case "Hard" : n=25; break;
            default : System.out.println("Unknown difficulty");
        }
        g.imgSize = 600/n;
    }
    
    public void setImageSize(){
        this.imgSize = 600/n;
    }
    
    public void setN(int n){
        this.n = n;
    }
    
    public void setPlayer(boolean blue){
        p.setType(blue);
    }
    
    public int getImgSize(){
        return g.imgSize;
    }
    
    public int getBullets(){
        return bullets;
    }
    
    public int getScore(){
        return score;
    }
    
    
    /**
     * @param scoreObserver the scoreObserver to set
     */
    public void setScoreObserver(LabelObserver scoreObserver) {
        this.scoreObserver = scoreObserver;
    }

    /**
     * @param healthObserver the healthObserver to set
     */
    public void setHealthObserver(LabelObserver healthObserver) {
        this.healthObserver = healthObserver;
    }

    /**
     * @param bulletsObserver the bulletsObserver to set
     */
    public void setBulletsObserver(LabelObserver bulletsObserver) {
        this.bulletsObserver = bulletsObserver;
    }
    
    
    
}
