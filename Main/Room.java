package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Vee on 28/03/2016.
 */
//room class it allows the movement between rooms and will randomly genereate a room each turn
public class Room {

    private String roomName;
    private String roomDesc;
    private boolean northExit = false;
    private boolean southExit = false;
    private boolean eastExit = false;
    private boolean westExit = false;
    private Random rand = new Random();

    public Room() {
        this.roomName = "Starting room";
        this.roomDesc = "Welcome to the start of your adventure";
        this.setExits(true,true,true,true);
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isNorthExit() {
        return northExit;
    }

    public void setNorthExit(boolean northExit) {
        this.northExit = northExit;
    }

    public boolean isSouthExit() {
        return southExit;
    }

    public void setSouthExit(boolean southExit) {
        this.southExit = southExit;
    }

    public boolean isEastExit() {
        return eastExit;
    }

    public void setEastExit(boolean eastExit) {
        this.eastExit = eastExit;
    }

    public boolean isWestExit() {
        return westExit;
    }

    public void setWestExit(boolean westExit) {
        this.westExit = westExit;
    }

    public String getRoomDesc() {

        return roomDesc;
    }
    //I adapted some code i found online to help me with the code snippit bellow
    //http://stackoverflow.com/questions/17851478/reading-a-particular-line-from-a-text-file-in-java

    public void setRoom() throws IOException {
        String fileName ="C:\\Users\\Vee\\IdeaProjects\\RPG\\src\\Main\\room.txt";
        int counter = 0;
        int start = rand.nextInt(30 / 3) * 3 + 1;
        int middle = start + 1;
        int end = start + 2;

        String line;
        FileReader fileReader;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null){
                counter++;
                if(counter == start){
                    this.setRoomName(line);
                }
                if(counter == middle){
                    this.setRoomDesc(line);
                }
                if(counter == end){
                    String[] lineSplit = line.split("\\s+");
                    boolean n = Boolean.parseBoolean(lineSplit[0]);
                    boolean e = Boolean.parseBoolean(lineSplit[1]);
                    boolean s = Boolean.parseBoolean(lineSplit[2]);
                    boolean w = Boolean.parseBoolean(lineSplit[3]);
                    setExits(n,e,s,w);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }
    public void setExits(boolean n, boolean e, boolean s, boolean w){
        this.northExit = n;
        this.eastExit = e;
        this.southExit = s;
        this.westExit = w;
    }
    public void getExits(){
        if(!isEastExit() && !isNorthExit() && !isSouthExit() && !isWestExit()){
            Game.breakPoint();
            System.out.println("There are no exits; where has the door gone?");
        }else{
            Game.breakPoint();
            String exits = "";
            System.out.println("There are a total of " + amountExits() + " exits");
            if(isWestExit()){
                exits += "west, ";
            }
            if(isEastExit()){
                exits += "east, ";
            }
            if (isNorthExit()){
                exits += "north, ";
            }
            if(isSouthExit()){
                exits += "south, ";
            }
            Game.breakPoint();
            System.out.println("There is exits to the; " + exits);
        }
    }
    private int amountExits(){
        int i = 0;
        if(isWestExit()){
            i++;
        }
        if(isEastExit()){
            i++;
        }
        if (isNorthExit()){
            i++;
        }
        if(isSouthExit()){
            i++;
        }
        return i;
    }
}
