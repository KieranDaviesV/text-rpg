package Main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Vee on 22/03/2016.
 */
//the main player class which the user will control
public class Player extends Entity {

    private Random rand = new Random();
    private int random = rand.nextInt(10) + 1;

    private boolean armSlot = false;
    private boolean handSlot = false;

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Item> inv = new ArrayList<>();

    public ArrayList<Item> getInv() {
        return inv;
    }

    public void setInv(ArrayList<Item> inv) {
        this.inv = inv;
    }

    public Player(String name) {

        super.setName(name);
        super.setEnergy(100);
        super.setStrength(random);
    }
    public void addInv(Item item){
        Game.breakPoint();
        System.out.println("You gained a " + item.getName());
        this.inv.add(item);
    }
    //searchs through the arraylist until it finds a item named the same as the string inputted
    //then will do the checks to see what that item can do
    public void useItem(String item) {
        Game.breakPoint();
        if(inv.size() > 0){
            for (int i = 0; i < inv.size(); i++) {
                if (inv.get(i).getName().equals(item)) {
                    System.out.println("you have used the item");
                    if (inv.get(i).isConsumable()) {
                        int healthUp = inv.get(i).getHealthUp();
                        gainHealth(healthUp);
                        //didn't realise how easy it was to remove something from a arraylist
                        //http://stackoverflow.com/questions/10714233/remove-item-from-arraylist
                        inv.remove(i);
                    } else if (inv.get(i).isWearable()) {
                        wield(inv.get(i));
                    } else {
                        System.out.println("Wait you can't use that here");
                    }
                }
            }
        }else{
            System.out.println("You don't have any items in here!");
        }

    }
    public void checkInv(){
        Game.breakPoint();
        if(inv.size() > 0){
            System.out.println("Your inventory consists of: ");
            for(int i = 0; i < inv.size(); i++){
                System.out.print(inv.get(i).toString() + " , ");
            }
        }else{
            System.out.println("You haven't got anything in your inventory");
        }
    }
    private void gainHealth(int healthUp){
        this.setEnergy(this.getEnergy() + healthUp);
        System.out.println("You gained " + healthUp + " energy to give you a total of " + this.getEnergy());
    }
    public void wield(Item item){
        if(!isArmSlot() || !isHandSlot()){
            if(!isHandSlot()){
                System.out.println("Hand slots free");
            }
            if(!isArmSlot()){
                System.out.println("Arm slots free");
            }
            boolean running = true;
            //constantly runs until you pick a correct choice, otherwise would throw a error if you typed something wrong
            while(running){
                System.out.println("Please choose a slot");
                System.out.println("");
                System.out.print("your Move ->");
                String input = scanner.nextLine();
                if(input.equals("hand")){
                    setHandSlot(true);
                    this.setStrength(this.getStrength() + item.getStrBonus());
                    this.setEnergy(this.getEnergy() + item.getHealthUp());
                    System.out.println("You are now wielding a " + item.getName() + " in the hand slot");
                    running = false;
                }else if(input.equals("arm")){
                    setArmSlot(true);
                    this.setStrength(this.getStrength() + item.getStrBonus());
                    this.setEnergy(this.getEnergy() + item.getHealthUp());
                    System.out.println("You are now wielding a " + item.getName() + " in the arm slot");
                    running = false;
                }else{
                    System.out.println("You commands invalid.");
                }
            }

        }
    }
    public boolean isArmSlot() {
        return armSlot;
    }

    public void setArmSlot(boolean armSlot) {
        this.armSlot = armSlot;
    }

    public boolean isHandSlot() {
        return handSlot;
    }

    public void setHandSlot(boolean handSlot) {
        this.handSlot = handSlot;
    }
}
