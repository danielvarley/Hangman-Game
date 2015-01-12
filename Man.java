package hangman;
import java.util.Scanner;

public class Man{

	private static Scanner sc = new Scanner(System.in);	
	protected static int lives;
	protected static String name;		

	protected static void chooseName(){
		System.out.println("Welcome to Hangman! Enter the name of your favourite celebrity:");
		name = sc.nextLine();		
	}

	protected static int setLivesInitial(String d){
		switch (d){
		case "1": lives = 17;
		break;
		case "2": lives = 13;
		break;
		case "3": lives = 10;
		break;
		default : lives = 17;
		System.out.println("Clearly that question was too difficult for you so I've assumed the first option on your behalf.");
		break;
		}
		return lives;
	}

	private static void printLives(){
		if(lives == 1){
			System.out.println("You have "+lives+" life remaining.");
		}
		else System.out.println("You have "+lives+" lives remaining.");
	}

	protected static void loseLife(){
		lives -= 1;
	}

	protected static void checkLives() throws Exception{
		if(lives==0){
			System.out.println("You have run out of lives!\n"
					+ "I was thinking of the word: " +Word.word+ "\nYour lack of skill has caused a slow and painful death for "+Man.name+".");
			System.exit(0);
		}
		else{
			Word.printProgressArray();
			printLives();
			Main.doRound();
		}
	}
}