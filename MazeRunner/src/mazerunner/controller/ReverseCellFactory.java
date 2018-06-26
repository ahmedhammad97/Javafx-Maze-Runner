
package mazerunner.controller;

import java.util.ArrayList;
import mazerunner.model.Game;
import mazerunner.model.GameCharacter;
import mazerunner.model.Player;


public class ReverseCellFactory {
    public ReverseCellFactory(){
        
    }
    
    public ArrayList<Character> generate(ArrayList<GameCharacter> x){
        ArrayList temp = new ArrayList<Character>();
        for(int i=0;i<x.size();i++){
            temp.add(detectType(x.get(i)));
        }
        return temp;
    }
    
    private char detectType(GameCharacter s){
        switch(s.getClass().getSimpleName()){
            case "BulletGift" : return 'G';
            case "EndDoor" : return 'd';
            case "HealthGift" : return 'g';
            case "KillerMonster" : return 'M';
            case "Player" : return Player.getPlayer().getLetter();
            case "Space" : return ' ';
            case "StealerMonster" : return 'm';
            case "StoneWall" : return 's';
            case "StrongBomb" : return 'B';
            case "WeakBomb" : return 'b';
            case "WoodWall" : return 'w';
            default: return ' ';
        }
    }
}
