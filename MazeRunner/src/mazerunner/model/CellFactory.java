
package mazerunner.model;

import java.util.ArrayList;


public class CellFactory {
    private ArrayList<Character> arr;
    private int n;
    private int imgSize;
    
    public CellFactory(ArrayList<Character> arr, int n){
        this.arr = arr;
        this.n = n;
        this.imgSize = 600/n;
    }
    
    public ArrayList<GameCharacter> getNewMap(){
        ArrayList m = new ArrayList<GameCharacter>();
        //System.out.println(arr);
        for(int i=0; i<arr.size();i++){
            int xPos = getXPos(i);
            int yPos = getYPos(i);
            m.add(kitchen(String.valueOf(arr.get(i)) ,xPos,yPos, i));
            
        }
        
        return m;
    }
    
    public int getXPos(int i){
        return (i%(2*n))*imgSize;
    }
    
    public int getYPos(int i){
        return (i/(2*n))*imgSize;
    }
    
    private boolean spaceCheck(Character c){
        return c==' ';
    }
    
    private GameCharacter kitchen(String c, int x, int y, int i){
        GameCharacter temp= null;
        switch(c){
            case "w" : temp = new WoodWall(x,y);break;
            case "s" : temp = new StoneWall(x,y);break;
            case "b" : temp = new WeakBomb(x,y);break;
            case "B" : temp = new StrongBomb(x,y);break;
            case "m" : temp = new StealerMonster(x,y);break;
            case "M" : temp = new KillerMonster(x,y);break;
            case "g" : temp = new HealthGift(x,y);break;
            case "G" : temp = new BulletGift(x,y);break;
            case "L" : temp = Player.getPlayer().setAttr(x,y,true,true);break;
            case "R" : temp = Player.getPlayer().setAttr(x,y,true,false);break;
            case "D" : temp = Player.getPlayer().setAttr(x,y,false,false);break;
            case "A" : temp = Player.getPlayer().setAttr(x,y,false,true);break;
            case "d" : temp = EndDoor.getDoor().setPosition(x, y);break;
            case " " : temp = new Space(x,y);break;
            default : System.out.println("Unkown character" + c);
        }
        temp.setIndexX(i%(2*n));
        temp.setIndexY(i/(2*n));
        return temp;
    }
    
}
