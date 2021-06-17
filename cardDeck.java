import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class cardDeck {

	public static void cardList(ArrayList<Card> cards, int numDecks) {

		String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		String[] suit = { "Clubs", "Diamonds", "Spades", "Hearts" };

		for (int i = 0; i < suit.length; i++) {
			for (int j = 0; j < rank.length; j++) {
				Card newCard = new Card(null, null);
				newCard.setRank(rank[j]);
				newCard.setSuit(suit[i]);
				cards.add(newCard);
			}
		}
		if (numDecks > 1) {
			for (int k = 1; k < numDecks; k++) {
				cards.addAll(cards);
			}
		}

		Collections.shuffle(cards);
	}

	public static Card pickCard(ArrayList<Card> cards, Scanner scan) throws IndexOutOfBoundsException {
		if (cards.isEmpty()) {
			scan.close();
			System.out.println("Game Over!\n");
			throw new IndexOutOfBoundsException(
					"Not enough cards to finish the round :(\n All wins/losses this round do not count.");
		}
		return cards.remove(0);
	}
}
