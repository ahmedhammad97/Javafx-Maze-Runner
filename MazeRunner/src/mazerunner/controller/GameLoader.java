
package mazerunner.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import mazerunner.model.CellFactory;
import mazerunner.model.Game;
import mazerunner.model.GameCharacter;
import mazerunner.model.Player;


public class GameLoader {
    private int sec;
    private int n;
    public GameLoader(Game g) throws FileNotFoundException{
        digForInfo(g);
    }
    
    private void digForInfo(Game g) throws FileNotFoundException{
        Scanner bla = new Scanner(new File("./info.txt"));
        String s = bla.nextLine();
        String[] x = s.split("\\s");

        
        sec = Integer.parseInt(x[0]);
        
        g.setHealth(Integer.parseInt(x[1]));
        
        g.setBullets(Integer.parseInt(x[2]));
        
        g.setScore(Integer.parseInt(x[3]));
        
        n = Integer.parseInt(x[4]);
        g.setN(n);
        g.setImageSize();
        //System.out.println("N : " + n);
        
        Player.getPlayer().setIndexX(Integer.parseInt(x[5]));
        
        Player.getPlayer().setIndexY(Integer.parseInt(x[6]));
        
       
    }
    
    public int getTime(){
        return sec;
    }
    
    public ArrayList<GameCharacter> load() throws FileNotFoundException{
        Scanner ss = new Scanner(new File("./plan.txt"));
        String s = ss.nextLine();
        s = s.substring(1,s.length()-1);
        ArrayList arr = new ArrayList<Character>();
        String[] bla = s.split("([,][\\s]){1}");
        for(int i=0;i<bla.length;i++){
            arr.add(bla[i].charAt(0));
        }
        //System.out.println(arr);
        return new CellFactory(arr,n).getNewMap();
    }
        
        
    
}
