import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Blackjack {
	public static ArrayList<Player> originalPlayers;

	public static void main(String[] args) {
		int round = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the number of blackjack players NOT including the dealer: ");
		int numPlayers = scan.nextInt();
		while (numPlayers < 1 || numPlayers > 6) {
			System.out.println("Not a valid number of players. Enter a number 1-6: ");
			numPlayers = scan.nextInt();
		}

		System.out.println("How many decks of cards would you like to use? Enter a number 1-8: ");
		int numDecks = scan.nextInt();

		while (numDecks < 1 || numDecks > 8) {
			System.out.println("Not a valid number of decks. Enter a number 1-8: ");
			numDecks = scan.nextInt();
		}
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Card> cards = new ArrayList<Card>();
		cardDeck.cardList(cards, numDecks);
		HashMap<Integer, String> names = new HashMap<Integer, String>();
		originalPlayers = new ArrayList<Player>();

		while (true) {
			players = new ArrayList<Player>();
			round++;
			if (round > 1) {
				scan = new Scanner(System.in);
				System.out.println("Round over!\n");
				Hand.getTotalCash(names, originalPlayers, df);
				System.out.println("\nWould you like to play another round of Blackjack?");
				String playing = scan.nextLine();
				if (playing.toUpperCase().startsWith("N")) {
					break;
				}
				// System.out.println("originalPlayers is : " + originalPlayers);

			}

			if (round > 1) {
				SetPlayers.createPlayers(players, scan, numPlayers, round, names, originalPlayers);
				for (Player player : players) {
					player.clearCards();
				}
			} else {
				SetPlayers.createPlayers(players, scan, numPlayers, round, names, originalPlayers);
			}

			// System.out.println("originalPlayers is : " + originalPlayers);

			Hand.pickFirstCards(players, cards, scan);
			boolean displayDealerHand = false;
			Hand.displayCardHands(players, displayDealerHand);

			Hand.check2(players, originalPlayers, df);

			if (AllNamesNull.allNamesNull(players)) {
				continue;
			}

			Hand.pickPlayerHands(players, originalPlayers, cards, scan, displayDealerHand, df);

			if (AllNamesNull.allNamesNull(players)) {
				continue;
			}

			// System.out.println("originalPlayers is : " + originalPlayers);

			Dealer.dealersPlay(players, cards, numDecks, displayDealerHand, scan);

			Hand.check3(players, originalPlayers, df);
			if (AllNamesNull.allNamesNull(players)) {
				continue;
			}

		}
		System.out.println("Game Over!\n");
		scan.close();
		System.exit(0);

	}

}
