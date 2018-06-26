
package mazerunner.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mazerunner.model.*;




public class FXMLDocumentController implements Initializable {
    
     @FXML
    private Button loadGameBtn;

    @FXML
    private Pane winPane;

    @FXML
    private Button newGameBtn;

    @FXML
    private ColorPicker color;

    @FXML
    private Button howToPlayBtn;

    @FXML
    private Button continueBtn;

    @FXML
    private Button quitBtn;

    @FXML
    private Label bulletLabel;

    @FXML
    private Label healthLabel;

    @FXML
    private Pane pausePane;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button settingsBackBtn;

    @FXML
    private ImageView maleChar;

    @FXML
    private Pane canvasPane;

    @FXML
    private ImageView startRunning;

    @FXML
    private ImageView femaleChar;

    @FXML
    private Pane losePane;

    @FXML
    private Canvas canvas;

    @FXML
    private Button winExitBtn;

    @FXML
    private Button pauseBtn;

    @FXML
    private Pane howToPlayPane;

    @FXML
    private ComboBox<String> difficulty;

    @FXML
    private GridPane gamePane;

    @FXML
    private Button howBackBtn;

    @FXML
    private Button loseExitBtn;

    @FXML
    private Pane startPane;

    @FXML
    private ImageView startFlying;

    @FXML
    private Pane settingsPane;

    @FXML
    private Button settingsBtn;

    @FXML
    private Label timeLabel;

    @FXML
    private Button saveBtn;
    
    @FXML 
    private Label gameSavedLabel;

    
    
    /**Class variables**/
    private Game g = Game.getGame();
    private boolean blueHero=true;
    private String difficult = "Easy";
    private Color c = Color.web("#E9E9E9");
    private Clock t;
    private ArrayList<GameCharacter> arr;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException  {
        
        if(event.getSource()==howToPlayBtn){
            startPane.setVisible(false);
            howToPlayPane.setVisible(true);
        }
        if(event.getSource()==settingsBtn){
            startPane.setVisible(false);
            settingsPane.setVisible(true);
            femaleChar.setBlendMode(BlendMode.OVERLAY);
            maleChar.setBlendMode(null);
        }
        if(event.getSource()==howBackBtn){
            howToPlayPane.setVisible(false);
            startPane.setVisible(true);
        }
        if(event.getSource()==settingsBackBtn){
            settingsPane.setVisible(false);
            startPane.setVisible(true);
            if(!difficulty.getSelectionModel().isEmpty()){
            difficult = difficulty.getSelectionModel().getSelectedItem();
            }
            
        }
        if(event.getSource()==newGameBtn){
            startPane.setVisible(false);
            gamePane.setVisible(true);
            t = new Clock();
            t.setObserver(new LabelObserver(timeLabel));
            g.setBulletsObserver(new LabelObserver(bulletLabel));
            g.setHealthObserver(new LabelObserver(healthLabel));
            g.setScoreObserver(new LabelObserver(scoreLabel));
            g.setHealth(100);
            g.setBullets(6);
            g.setScore(0);
            t.start(1000, 1000);
            g.setPlayer(blueHero);
            g.setSize(difficult);
            arr = Game.getGame().generateMap(Game.getGame().getN());
            updateCanvas(arr);
            
            
        }
        
        if(event.getSource()==pauseBtn){
            pausePane.setVisible(true);
            new Pausing().doAction(t);
            
        }
        
        if(event.getSource()==continueBtn){
            pausePane.setVisible(false);
            new Playing().doAction(t);
            gameSavedLabel.setVisible(false);
        }
        
        if(event.getSource()==quitBtn){
            pausePane.setVisible(false);
            gamePane.setVisible(false);
            startPane.setVisible(true);
            new Stop().doAction(t);
            gameSavedLabel.setVisible(false);
        }
        
        if(event.getSource()==winExitBtn){
            winPane.setVisible(false);
            gamePane.setVisible(false);
            startPane.setVisible(true);
        }
        
        if(event.getSource()==loseExitBtn){
            losePane.setVisible(false);
            gamePane.setVisible(false);
            startPane.setVisible(true);
        }
        
        if(event.getSource()==saveBtn){
           Memento m = new Memento(arr);
           m.save(t.getTime(),g.getHealth(),g.getBullets(),g.getScore(), g.getN());
           gameSavedLabel.setVisible(true);
        }
        
        if(event.getSource()==loadGameBtn){
            t = new Clock();
            t.setObserver(new LabelObserver(timeLabel));
            g.setBulletsObserver(new LabelObserver(bulletLabel));
            g.setHealthObserver(new LabelObserver(healthLabel));
            g.setScoreObserver(new LabelObserver(scoreLabel));
            GameLoader x = new GameLoader(g);
            t.setTime(x.getTime());
            arr = x.load();
            startPane.setVisible(false);
            gamePane.setVisible(true);
            t.start(1000, 1000);
            updateCanvas(arr);
        }
        
    }
    
    @FXML
    private void handleKeyPress(KeyEvent event){
        //System.out.println(event.getCode().toString());
        int currX = g.getPlayer().getIndexX();
        int currY = g.getPlayer().getIndexY();
        //System.out.println("Currx " + currX + "  currY " + currY);
        switch(event.getCode().toString()){
            case "RIGHT": moveRight(currX,currY);break;
            case "LEFT": moveLeft(currX,currY);break;
            case "UP": moveUp(currX,currY);break;
            case "DOWN": moveDown(currX,currY);break;
            case "CONTROL": fire(currX,currY);break;
            default : System.out.println("Unrecognized key");
        }
        updateCanvas(arr);
    }
    
    private void moveLeft(int x, int y){
        if(g.getPlayer().isLeft()){g.getPlayer().rotate();return;}
        int nextIndex = x-1;
        if(x/(g.getN()*2) == nextIndex/(g.getN()*2) && nextIndex>=0){
            moving(x,y,nextIndex,y);
        }
    }
    
    private void moveRight(int x, int y){
        if(!g.getPlayer().isLeft()){g.getPlayer().rotate();return;}
        int nextIndex = x+1;
        if(x/(g.getN()*2) == nextIndex/(g.getN()*2)){
            moving(x,y,nextIndex,y);
        }
    }
    
    private void moveUp(int x, int y){
        int nextIndex = y-1;
        if(nextIndex>=0){
            moving(x,y,x,nextIndex);
        }
    }
    
    private void moveDown(int x, int y){
        int nextIndex = y+1;
        if(nextIndex<g.getN()){
            moving(x,y,x,nextIndex);
        }
    }
    
    private void fire(int x, int y){
        int newX, newY;
        if(!g.getPlayer().isLeft()){
            newX = x-1;
            newY = y;
        }
        else{
            newX = x+1;
            newY = y;
        }
        
        if(g.fireBullet() && newX>=0  && newX<g.getN()*2){
            int arrIndex1 = (y*2*g.getN())+x;
            int arrIndex2 = (newY*2*g.getN())+newX;

            if(arr.get(arrIndex2).isDestroyable()){
                g.incrementScore(50*100/t.getTime());
                replaceBySpace(arrIndex2);
            }
        }
        
    }
    
    private void moving(int x1, int y1, int x2, int y2){
        int arrIndex1 = (y1*2*g.getN())+x1;
        int arrIndex2 = (y2*2*g.getN())+x2;
        
        //System.out.println(arr.get(arrIndex2).getClass().getSimpleName());
        if(arr.get(arrIndex2).getClass().getSimpleName().equals("Space")){
            swapPositions(arrIndex1,arrIndex2);
            swapPlacesinArray(arrIndex1,arrIndex2);
        }
        else if(arr.get(arrIndex2).getClass().getSimpleName().equals("BulletGift")){
            g.addBullet();
            g.incrementScore(10*100/t.getTime());
            replaceBySpace(arrIndex2);
            swapPositions(arrIndex1,arrIndex2);
            swapPlacesinArray(arrIndex1,arrIndex2);
        }
        else if(arr.get(arrIndex2).getClass().getSimpleName().equals("HealthGift")){
            g.setHealth(100);
            g.incrementScore(10*100/t.getTime());
            replaceBySpace(arrIndex2);
            swapPositions(arrIndex1,arrIndex2);
            swapPlacesinArray(arrIndex1,arrIndex2);
        }
        else if(arr.get(arrIndex2).getClass().getSimpleName().equals("WeakBomb")){
            if(g.getHealth()<40){youLose();return;}
            g.setHealth(g.getHealth()-40);
            replaceBySpace(arrIndex2);
            swapPositions(arrIndex1,arrIndex2);
            swapPlacesinArray(arrIndex1,arrIndex2);
        }
        else if(arr.get(arrIndex2).getClass().getSimpleName().equals("StrongBomb")){
            if(g.getHealth()<80){youLose();return;}
            g.setHealth(g.getHealth()-80);
            replaceBySpace(arrIndex2);
            swapPositions(arrIndex1,arrIndex2);
            swapPlacesinArray(arrIndex1,arrIndex2);
        }
        else if(arr.get(arrIndex2).getClass().getSimpleName().equals("StealerMonster")){
            g.fireBullet();
            
        }
        else if(arr.get(arrIndex2).getClass().getSimpleName().equals("KillerMonster")){
            youLose();return;
        }
        else if(arr.get(arrIndex2).getClass().getSimpleName().equals("EndDoor")){
            youWin();return;
        }
        
        
    }
    
    private void replaceBySpace(int x){
        int xPos = arr.get(x).getPositionX();
        int yPos = arr.get(x).getPositionY();
        arr.remove(x);
        arr.add(x,new Space(xPos,yPos));
    }
    
    private void swapPlacesinArray(int one, int two){
        GameCharacter temp1 = arr.get(one);
        GameCharacter temp2 = arr.get(two);
        arr.remove(one);arr.add(one, temp2);
        arr.remove(two);arr.add(two, temp1);
    }
    
    private void swapPositions(int one, int two){
        int x1 = arr.get(one).getPositionX();
        int y1 = arr.get(one).getPositionY();
        int x2 = arr.get(two).getPositionX();
        int y2 = arr.get(two).getPositionY();
        
        arr.get(one).setPositionX(x2);
        arr.get(one).setPositionY(y2);
        arr.get(two).setPositionX(x1);
        arr.get(two).setPositionY(y1);
     
    }
    
    @FXML
    private void maleCharClick(MouseEvent event){
        femaleChar.setBlendMode(BlendMode.OVERLAY);
        maleChar.setBlendMode(null);
        blueHero=true;
    }
    
    @FXML
    private void femaleCharClick(MouseEvent event){
        maleChar.setBlendMode(BlendMode.OVERLAY);
        femaleChar.setBlendMode(null);
        blueHero=false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList dList = FXCollections.observableArrayList();
        dList.add("Easy");dList.add("Moderate");dList.add("Hard");
        difficulty.setItems(dList);
        
        color.setValue(Color.valueOf("#E9E9E9"));
      
    }
    
    public boolean getHero(){
        return blueHero;
    }
    
    public String getDifficulty(){
        return difficult;
    }
    
    public void continueGame(){
        t.cont();
    }
    
    public void updateCanvas(ArrayList<GameCharacter> g){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 1200, 600);
        for(int i=0;i<g.size();i++){
            g.get(i).draw(canvas);
        }
    }
    
    private void youWin(){
        t.stop();
        winPane.setVisible(true);
    }
    
    private void youLose(){
        t.stop();
        losePane.setVisible(true);
    }
}
