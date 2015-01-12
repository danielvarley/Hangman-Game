package hangman;
import java.util.Scanner;

public class Main{

	private static Scanner sc = new Scanner(System.in);
	protected static String guess;

	public static void main(String[] args) throws Exception{

		Man.chooseName();
		String d = GameState.chooseDifficulty();
		Man.setLivesInitial(d);		
		Word.getRandomWord();	
		Word.letterCount();		
		//Word.giveWord();
		//uncomment the above line to print the randomly selected word for testing purposes
		System.out.println("The fate of " + Man.name + " rests in your hands...\n" 
				+ "I'm thinking of a word which has " + (Word.letterCount)+" letters.\n"
				+ "Guess letters to reveal the word, type the whole word if you "
				+ "think you've got it.\nGuessing incorrectly loses a life.\n"
				+ "If you lose all "+Man.lives+" lives then you can wave goodbye"
				+ " to your precious "+Man.name+".\n\nHint: lower case letters only please.");
		Word.makeProgressArray();
		Word.printProgressArray();
		Word.makeTargetCharArray();
		doRound();

	}

	protected static void doRound() throws Exception{					
		if(Word.progressString.equals(Word.word)){
			System.out.println("You've guessed all of the correct letters! " +Man.name+" will be very grateful. Congratulations!");	
			System.exit(0);
		}
		else{
			guess = sc.nextLine();	
			if(guess.length() == 0){
				System.out.println("Whoops! Please enter a lowercase letter.");
				Word.printProgressArray();
				doRound();
			}
			if(guess.equals(Word.word)){			
				System.out.println("That's correct! "+Man.name+" will be very grateful. Congratulations!");
				System.exit(0);
			}
			else{
				if(guess.length() > 1){
					Man.loseLife();
					System.out.println("That's not the correct word; you lose a life.");				
					Man.checkLives();
				}
				else{
					Word.checkChar();					
				}
			}
		}
	}
}
