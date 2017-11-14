package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Vee on 23/03/2016.
 */
//the monster class extends Entity, its a simple class which allows you to set a drop and randomly generate a random
    //monster from the list in a txt doc
public class Monster extends Entity {

    private Random rand = new Random();

    private int maxMonsters = 5;
    private Item drop = new Item();;

    public Item getDrop() {
        return drop;
    }

    public void setDrop() {
        this.drop = drop.findItem();
    }
    //I adapted some code i found online to help me with the code snippit bellow
    //http://stackoverflow.com/questions/17851478/reading-a-particular-line-from-a-text-file-in-java
    public void findMonster() throws IOException {
        String fileName = "C:\\Users\\Vee\\IdeaProjects\\RPG\\src\\Main\\monsters.txt";
        int counter = 0;
        int random = rand.nextInt(maxMonsters) + 1;
        String line;
        FileReader fileReader;
            try {
                fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while((line = bufferedReader.readLine()) != null){
                    counter++;
                    if(counter == random){
                        String[] lineSplit = line.split("\\s+");
                        super.setName(lineSplit[0]);
                        super.setEnergy(Integer.parseInt(lineSplit[1]));
                        super.setStrength(Integer.parseInt(lineSplit[2]));
                        this.setDrop();
                        System.out.println("oh no a " + this.getName() + " has spawned");
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }

}
