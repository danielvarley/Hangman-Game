package hangman;
import java.util.Scanner;

public class GameState {

	private static Scanner sc = new Scanner(System.in);	

	protected static String chooseDifficulty(){
		System.out.println("Set difficulty: To what extent would you describe yourself as clever?\n"
				+ "'Errrr what does 'extent' mean?' --------------------------------> (Easy) Type '1'\n"
				+ "'I'm fairly average.' -------------------------------------------> (Medium) Type '2'\n"
				+ "'I'm a genius. I will give Daniel my car if I lose this game.' --> (Difficult) Type '3'");
		String difficulty = sc.nextLine();
		return difficulty;
	}
}
