package Main;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Vee on 22/03/2016.
 */

//This is the main game class, it runs all the essentials for the game to work.
public class Game {

    private static String lineBreak = "=======================";

    private static boolean running;
    private static Scanner scanner = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        breakPoint();
        System.out.println("Please pick yourself a name");
        System.out.println("");
        System.out.print("your Move ->");
        String input = scanner.nextLine();
        Player hero = new Player(input);
        System.out.println("your stats are;");
        System.out.println(hero.toString());
        running = true;
        Room room = new Room();
        System.out.println(room.getRoomName());
        System.out.println(room.getRoomDesc());
        while(running) {
            commands(hero, room);
        }
    }
    //commands for the game, using a simple switch statement checking for what the user entered to do the next command
    private static void commands(Player hero, Room room){
        if(hero.getEnergy() == 0){
            gameOver(hero);
        }else{
            room.getExits();
            System.out.println("");
            System.out.print("your Move ->");
            String input = scanner.nextLine();
            System.out.println("");
            switch(input.toLowerCase()) {
                case "help":
                    help();
                    break;
                case "north":
                    moveRoom(room, input.toLowerCase(), hero);
                    break;
                case "east":
                    moveRoom(room, input.toLowerCase(), hero);
                    break;
                case "south":
                    moveRoom(room, input.toLowerCase(), hero);
                    break;
                case "west":
                    moveRoom(room, input.toLowerCase(), hero);
                    break;
                case "battle":
                    if(room.getRoomName().equals("battle room")){
                        battle(hero);
                        try {
                            room.setRoom();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("you cannot fight nothing");
                    }
                    break;
                case "use item":
                    System.out.println("Type in the item you want to use");
                    System.out.println("");
                    System.out.print("your Move ->");
                    String input1 = scanner.nextLine();
                    hero.useItem(input1);
                    break;
                case "inventory":
                    hero.checkInv();
                    break;
                case "stats":
                    System.out.println(hero.toString());
                    break;
                case "exits":
                    room.getExits();
                    break;
                default:
                    breakPoint();
                    System.out.println("invalid command");
                    System.out.println("type help if you need help with commands");
            }
        }
    }
    //moveRoom class allows the next room to be randomly generated.
    private static void moveRoom(Room room, String input, Player hero){
        if(input.equals("north") && room.isNorthExit()){
            System.out.println("You are exiting via the north");
            hero.setEnergy(hero.getEnergy() - 1);
            try {
                room.setRoom();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(input.equals("east") && room.isEastExit()){
            System.out.println("You are exiting via the east");
            hero.setEnergy(hero.getEnergy() - 1);
            try {
                room.setRoom();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(input.equals("south") && room.isSouthExit()){
            System.out.println("You are exiting via the south");
            hero.setEnergy(hero.getEnergy() - 1);
            try {
                room.setRoom();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(input.equals("west") && room.isWestExit()){
            System.out.println("You are exiting via the west");
            hero.setEnergy(hero.getEnergy() - 1);
            try {
                room.setRoom();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("You cannot exit that way");
        }
        breakPoint();
        System.out.println(room.getRoomName());
        System.out.println(room.getRoomDesc());
    }
    //basic help doc built into the game
    private static void help() {
        breakPoint();
        System.out.println("Welcome to the help screen here are the commands to use within the game:");
        System.out.println("North,East,South,West theses are the movement commands");
        System.out.println("When in a battle room you can type battle and a randomly generated monster will come fight you");
        System.out.println("Use item will give you the option to choose a item and use it");
        System.out.println("Inventory will display whats in your bag");
        System.out.println("Stats will display the players stats");
        System.out.println("Exits will display the exits available");
    }
    //break point
    static void breakPoint(){
        System.out.println(lineBreak);
    }
    //basic fighting class will update the monster to a new randomly generated one
    private static void battle(Player hero) {
        breakPoint();
        Monster monster = new Monster();
        try {
            monster.findMonster();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean fighting = true;

        int hAttack = hero.getStrength();
        int mAttack = monster.getStrength();
        int fallenDmg = rand.nextInt(15) + 1;

        //basic fighting could be updated with defence eventually?
        while (fighting) {
            breakPoint();
            if (monster.getEnergy() <= 0) {
                System.out.println("You have defeated the " + monster.getName());
                hero.addInv(monster.getDrop());
                fighting = false;
            } else if (hero.getEnergy() <= 0) {
                gameOver(hero);
                fighting = false;
            } else {
                System.out.println("Choose what to do");
                System.out.println("Attack or Run");
                System.out.println("");
                System.out.print("your Move ->");
                String input = scanner.nextLine();
                if (input.toLowerCase().equals("attack")) {
                    hero.setEnergy(hero.getEnergy() - mAttack);
                    System.out.println("The " + monster.getName() + " hit you for " + mAttack);
                    monster.setEnergy(monster.getEnergy() - hAttack);
                    System.out.println("You hit " + monster.getName() + " for a " + hAttack);
                } else if (input.toLowerCase().equals("run")) {
                    System.out.println("You're running away like a girl");
                    System.out.println("You fell over hurting yourself for " + fallenDmg);
                    hero.setEnergy(hero.getEnergy() - fallenDmg);
                    fighting = false;
                } else {
                    System.out.println("not sure what that command is type attack or run");
                }
            }
        }
    }
    //game over ends the main loop of the game
    private static void gameOver(Player hero){
        breakPoint();
        breakPoint();
        breakPoint();
        System.out.println("The game is truly over now");
        System.out.println(hero.getName() + " you achieved so much didn't you oh great one");
        running = false;
    }

}
