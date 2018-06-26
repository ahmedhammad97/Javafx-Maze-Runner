
package mazerunner.controller;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import mazerunner.model.GameCharacter;
import mazerunner.model.Player;


public class Memento {
    private ArrayList mem;
    
    public Memento(ArrayList<GameCharacter> x){
        this.mem = x;
    }
    
    public void save(int time, int health, int bullets, int score, int n) throws IOException {
        String temp = String.valueOf(time) + " " + String.valueOf(health) + " " + String.valueOf(bullets) + " " + String.valueOf(score) + " " + n + " "
                + Player.getPlayer().getIndexX() + " " + Player.getPlayer().getIndexY();
        FileWriter f = new FileWriter("./info.txt");
        f.write(temp);
        f.close();
        
        ArrayList<Character> arr = new ReverseCellFactory().generate(mem);
        writeCharArr(arr);
    }
    
    private void writeCharArr(ArrayList<Character> x) throws IOException{
        String s = x.toString();
        FileWriter f = new FileWriter("./plan.txt");
        f.write(s);
        f.close();
    }
        
}
