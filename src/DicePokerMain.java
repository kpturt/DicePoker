import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DicePokerMain {
    public static void main(String[] args) {
        Game g = new Game();
        g.openGame();
    }//psvm
}//Main

class Game{
    Random rnd = new Random();
    Scanner scan = new Scanner(System.in);
    public double balance;
    double pool = 0;
    double chosenBet = 0;
    int[] diceP1 = new int[5];
    int[] diceP2 = new int[5];
    int firstHighestP1;
    int firstHightestP2;
    int secondHighestP1;
    int secondHightestP2;
    {
        balance = 500;
    }

    public void openGame(){
        System.out.println("Welcome to dice poker!");
        System.out.println("Your balance is: "+balance);
        System.out.println("!start to begin the game.");
        //System.out.println("!balance show your balance.");
        System.out.println("------------------------------------");
        play();
    }//openGame

    public void play(){

        String command = scan.nextLine();

        if(command.equals("!start")){
            System.out.println("Beginning game!");
            startGame();
        }
        else{
            System.out.println("Incorrect input.");
            play();
        }
    }//play

    public void startGame(){
        startBet();
    }//startGame

    public void startBet(){
        System.out.println("Select staring bet: ");
        System.out.println("1: "+0.1*balance);
        System.out.println("2: "+0.25*balance);
        System.out.println("3: "+0.5*balance);
        System.out.println("4: "+balance);

        String command = scan.nextLine();
        if(command.equals("1")){
            chosenBet = 0.1*balance;
            pool += chosenBet;
            balance -= chosenBet;
            System.out.println("Chosen bet: "+chosenBet);
        }
        else if(command.equals("2")){
            chosenBet = 0.25*balance;
            pool += chosenBet;
            balance -= chosenBet;
            System.out.println("Chosen bet: "+chosenBet);
        }
        else if(command.equals("3")){
            chosenBet = 0.5*balance;
            pool += chosenBet;
            balance -= chosenBet;
            System.out.println("Chosen bet: "+chosenBet);
        }
        else if(command.equals("4")){
            chosenBet = balance;
            pool += chosenBet;
            balance -= chosenBet;
            System.out.println("Chosen bet: "+chosenBet);
        }
        else{
            System.out.println("Incorrect input.");
            startGame();
        }
        roll();
    }

    public void roll(){
        System.out.println("Type !roll to roll.");
        String command = scan.nextLine();

        if(command.equals("!roll")){
            System.out.println("Rolling the dice...");
            for(int i = 0; i<5; i++){
                diceP1[i] = rnd.nextInt(6)+1;
            }
            System.out.println("You rolled: "+Arrays.toString(diceP1));
            checkRoll();
        }
        else{
            System.out.println("Incorrect output.");
            roll();
        }
    }//roll

    public void checkRoll(){

        int[] count = new int[6];

        //counting how many of the same dice you have
        for(int i = 0; i < 5; i++){
            if(diceP1[i] == 1){
                count[0]++;
            }
            if(diceP1[i] == 2){
                count[1]++;
            }
            if(diceP1[i] == 3){
                count[2]++;
            }
            if(diceP1[i] == 4){
                count[3]++;
            }
            if(diceP1[i] == 5){
                count[4]++;
            }
        }//for

        System.out.println("How many of each dice you have: "+Arrays.toString(count));
        System.out.println("You have: ");
        String result = "";
        int firstHighest = Arrays.stream(diceP1).max().getAsInt();

        //checking the combinations and what kind of hand you have
        for(int i = 0; i<count.length; i++) {
            if (count[i] == 5) {
                result = "Five of a kind";
                firstHighest = Arrays.stream(diceP1).max().getAsInt();
                break;
            } else if (count[i] == 4) {
                result = "Four of a kind";
                firstHighest = i+1;
                break;
            } else if (count[i] == 3) {
                result = "Three of a kind";
                firstHighest = i+1;
                for (int j = i+1; j<6; j++) {
                    if (count[j] == 2) {
                        result = "Full house";
                        break;
                    }
                }
                break;
            } else if (count[i] == 2) {
                result = "One Pair ";
                for (int j = i+1; j<6; j++) {
                    if (count[j] == 2) {
                        result = "Two Pairs ";
                        break;
                    }
                }
                break;
            } else {
                result = "Highest dice";
            }
        }
        System.out.print(result);
        System.out.print(", highest dice is: "+firstHighest);
    }//checkRoll
}//Game

