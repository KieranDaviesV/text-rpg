package Main;

import java.util.ArrayList;


/**
 * Created by Vee on 22/03/2016.
 */
//Entity class the core elements of the monster and player class
public class Entity {
    private String name;
    private int strength;
    private int energy;

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getEnergy() {
        return energy;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", energy=" + energy +
                '}';
    }
}
