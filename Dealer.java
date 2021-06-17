import java.util.ArrayList;
import java.util.Scanner;

public class Dealer {

	public static void dealersPlay(ArrayList<Player> players, ArrayList<Card> cards, int numDecks,
			boolean displayDealerHand, Scanner scan) {
		System.out.println("\nDealer's turn:\n");
		displayDealerHand = true;
		Hand.displayCardHands(players, displayDealerHand);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}

		while (((players.get(0).getSum1() < 17) && (players.get(0).getSum2() < 17))
				|| ((players.get(0).getSum1() < 17) && (players.get(0).getSum2() > 21))) {

			Card firstDeal = cardDeck.pickCard(cards, scan);
			System.out.println(players.get(0).getName() + " just picked: " + firstDeal.toString());
			players.get(0).setPlayersCards(firstDeal);
			int value = Card.cardValue(firstDeal);

			Hand.setValue(players, value, 0);

			Hand.displayCardHands(players, displayDealerHand);

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
	}
}
