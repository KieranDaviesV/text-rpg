package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Vee on 31/03/2016.
 */
//the item class which is just a basic class which could be built on, is for the basic items which can be found in the game
public class Item {

    private String name;
    private String itemDesc;

    private boolean consumable;
    private boolean wearable;

    private int strBonus;
    private int healthUp;

    private Random rand = new Random();

    //I adapted some code i found online to help me with the code snippit bellow
    //http://stackoverflow.com/questions/17851478/reading-a-particular-line-from-a-text-file-in-java
    public Item findItem(){
        String fileName = "C:\\Users\\Vee\\IdeaProjects\\RPG\\src\\Main\\items.txt";
        int counter =0;
        int start = rand.nextInt(14/2) * 2 + 1;
        int end = start + 1;
        String line;
        FileReader fileReader;

        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                counter++;
                if (counter == start) {
                    String[] lineSplit = line.split("\\s+");
                    this.setName(lineSplit[0]);
                    this.setConsumable(Boolean.parseBoolean(lineSplit[1]));
                    this.setWearable(Boolean.parseBoolean(lineSplit[2]));
                    this.setStrBonus(Integer.parseInt(lineSplit[3]));
                    this.setHealthUp(Integer.parseInt(lineSplit[4]));
                }
                if (counter == end) {
                    this.setItemDesc(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
    public int getStrBonus() {
        return strBonus;
    }

    public void setStrBonus(int strBonus) {
        this.strBonus = strBonus;
    }

    public int getHealthUp() {
        return healthUp;
    }

    public void setHealthUp(int healthUp) {
        this.healthUp = healthUp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    public boolean isWearable() {
        return wearable;
    }

    public void setWearable(boolean wearable) {
        this.wearable = wearable;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", " + itemDesc;

    }
}
