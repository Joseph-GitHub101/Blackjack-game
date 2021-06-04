import java.util.ArrayList;
import java.util.Random;

public class SelectCard{
	
	public static Card pickCard(ArrayList<Card> cards, int numDecks) {
		Random rand = new Random();
		int magicNumber = rand.nextInt(52*numDecks);
		Card randCard = cards.get(magicNumber);
		while(randCard==null) {
			magicNumber = rand.nextInt(52*numDecks);
			randCard = cards.get(magicNumber);
		}
		cards.set(magicNumber, null);
		return randCard;
	}

}
