package hangman;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Word {
		
	private static BufferedReader reader;
	protected static String word;
	protected static int letterCount;
	private static char[] progressArray;
	protected static String progressString;
	private static char[] lettersUsedArray = new char[26];
	private static char chosenChar;			
	final private static char[] alphabetArray = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private static char[] targetCharArray;

	protected static int getRandomLine(){
		Random random = new Random();
		long randomInt = (long) random.nextInt(263541);
		int randomLine = (int) randomInt;
		return randomLine;

	}

	protected static void getRandomWord() throws Exception{
		//specify the path of the file 'words.txt
		//not yet sure how to avoid the need for this
		FileReader wordsFile = new FileReader("/Users/danielvarley/Desktop/words.txt");
		reader = new BufferedReader(wordsFile); 

		List<String> wordsArray = new ArrayList<String>();
		String line = reader.readLine();
		while (line != null){
			wordsArray.add(line);
			line = reader.readLine();
		}
		int i = getRandomLine();
		if(wordsArray.get(i).length() < 4 || wordsArray.get(i).length() > 9){
			getRandomWord();
		}
		else{
			word = wordsArray.get(i);
		}
	}

	protected static void letterCount(){
		letterCount = word.length();
	}

	protected static void giveWord(){
		System.out.println("[Randomly selected word is: "+word+"]");
	}

	protected static void makeProgressArray(){
		progressArray = new char[letterCount];
		for(int i=0;i<progressArray.length;i++){
			progressArray[i] = '_';
		}
	}

	protected static void printProgressArray(){
		progressString = String.valueOf(progressArray);
		System.out.println(progressString);
	}

	protected static void makeTargetCharArray(){
		targetCharArray = word.toCharArray();
	}

	private static void addToLettersUsedArray(){
		for(int i=0; i<lettersUsedArray.length; i++){
			if(lettersUsedArray[i]==0){
				lettersUsedArray[i] = chosenChar;
				break;
			}			
		}
	}

	protected static void checkChar() throws Exception{		
		chosenChar=Main.guess.charAt(0);
		int foundAtIndex = -1;
		int foundAtIndex2 = -1;
		boolean correctGuess = false;
		for(int i=0; i<alphabetArray.length; i++){
			if(chosenChar == alphabetArray[i]){
				foundAtIndex = i;
				break;						
			}			
		}
		if(foundAtIndex >= 0){
			for(int i2=0; i2<lettersUsedArray.length; i2++){
				if(chosenChar == lettersUsedArray[i2]){
					foundAtIndex2 = i2;
					break;
				}
			}
			if(foundAtIndex2 == -1){
				addToLettersUsedArray();
				for(int i3=0; i3<letterCount;i3++){
					if(chosenChar == targetCharArray[i3]){
						progressArray[i3] = chosenChar;
						correctGuess = true;						
					}
				}
				if(correctGuess){
					System.out.println("You guessed a letter. Well done!");
					printProgressArray();					
					Main.doRound();
				}
				else{
					System.out.println("Nope. The word does not contain that letter. You lose a life.");
					Man.loseLife();					
					Man.checkLives();
					Main.doRound();
				}
			}
			else{
				System.out.println("Looks like you've already guessed that letter, try another!");
				printProgressArray();
				Main.doRound();
			}
		}
		else{
			System.out.println("Woops! Please enter a lowercase letter.");			
			printProgressArray();
			Main.doRound();
		}
	}
}	