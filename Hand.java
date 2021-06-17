import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;

public class Hand {

	public static void setValue(ArrayList<Player> players, int value, int idx) {
		if (value != 0) {
			players.get(idx).setSum1(players.get(idx).getSum1() + value);
			players.get(idx).setSum2(players.get(idx).getSum2() + value);
		}

		if (value == 0 && (players.get(idx).getSum1() == players.get(idx).getSum2())) {

			players.get(idx).setSum1(players.get(idx).getSum1() + 1);
			players.get(idx).setSum2(players.get(idx).getSum2() + 11);
		} else if (value == 0 && (players.get(idx).getSum1() != players.get(idx).getSum2())) {
			players.get(idx).setSum1(players.get(idx).getSum1() + 1);
			players.get(idx).setSum2(players.get(idx).getSum2() + 1);
		}
	}

	public static void displayCardHands(ArrayList<Player> players, boolean displayDealerHand) {
		if (displayDealerHand == true) {
			System.out.println(players.get(0).getName() + "'s hand:\n");
			for (Card card : players.get(0).getPlayersCards()) {
				System.out.println(card.toString());
			}
		} else {

			System.out.println("\n" + players.get(0).getName() + "'s known card: "
					+ players.get(0).getPlayersCards().get(0).toString() + "\n");

		}

		System.out.println("\nPlayers' hands:\n\n");

		for (int i = 1; i < players.size(); i++) {
			if (players.get(i).getName() == null)
				continue;

			System.out.println(players.get(i).getName() + "'s cards:\n");
			for (Card card : players.get(i).getPlayersCards()) {
				System.out.println(card.toString());
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void pickFirstCards(ArrayList<Player> players, ArrayList<Card> cards, Scanner scan) {

		Card firstCards;

		for (int i = 0; i < 2; i++) {

			for (int j = 0; j < players.size(); j++) {
				firstCards = cardDeck.pickCard(cards, scan);
				// the line below is useful to see what picks everyone gets at first, so I'll
				// just leave it be.
				// System.out.println(playerList.get(j).getName() + " you picked a " +
				// firstCards.toString() + "\n");
				players.get(j).setPlayersCards(firstCards);
				int value = Card.cardValue(firstCards);

				Hand.setValue(players, value, j);
			}
		}
	}

	public static void pickPlayerHands(ArrayList<Player> players, ArrayList<Player> originalPlayers,
			ArrayList<Card> cards, Scanner scan, boolean displayDealerHand, DecimalFormat df) {

		int i = 1;
		for (Iterator<Player> itr = players.subList(1, players.size()).iterator(); itr.hasNext(); i++) {
			itr.next();
			boolean pick = true;
			while ((players.get(i).getName() != null) && (pick)) {

				scan = new Scanner(System.in);
				System.out.println(
						players.get(i).getName() + ", would you like to hit or stay? Type 'h' to hit, 's' to stay.");
				String hitOrStay = scan.nextLine();
				while (!(hitOrStay.equals("h") || hitOrStay.equals("s"))) {

					System.out.println("Wrong input. Type 'h' to hit, 's' to stay.");
					hitOrStay = scan.nextLine();
				}
				if (hitOrStay.equals("h")) {
					Card cardPick = cardDeck.pickCard(cards, scan);
					System.out.println(players.get(i).getName() + " just picked: " + cardPick.toString());

					players.get(i).setPlayersCards(cardPick);
					int value = Card.cardValue(cardPick);

					setValue(players, value, i);

				} else {
					pick = false;
				}

				displayCardHands(players, displayDealerHand);

				check1(players, originalPlayers, df, i);

			}

		}

	}

	public static void check1(ArrayList<Player> players, ArrayList<Player> originalPlayers, DecimalFormat df, int i) {
		if ((players.get(i).getSum1() > 21) && (players.get(i).getSum2() > 21)) {
			System.out.println("Sorry, " + players.get(i).getName() + ". You have busted. Your bet of $"
					+ df.format(players.get(i).getBet()) + " is lost to the casino.");
			originalPlayers.get(i).setCash(-players.get(i).getBet());
			originalPlayers.get(0).setCash(players.get(i).getBet());
			players.get(i).setName(null);

		}
	}

	public static void check2(ArrayList<Player> players, ArrayList<Player> originalPlayers, DecimalFormat df) {
		for (int i = 1; i < players.size(); i++) {
			if (((players.get(i).getSum1() == 21) || (players.get(i).getSum2() == 21))
					&& ((players.get(0).getSum1() != 21) && (players.get(0).getSum2() != 21))) {
				System.out.println("Congrats " + players.get(i).getName() + "! You got blackjack. You have won $"
						+ df.format(players.get(i).getBet() * 1.5));
				originalPlayers.get(i).setCash(players.get(i).getBet() * 1.5);
				originalPlayers.get(0).setCash(-players.get(i).getBet() * 1.5);
				players.get(i).setName(null);
			} else if (((players.get(0).getSum1() == 21) || (players.get(0).getSum2() == 21))
					&& ((players.get(i).getSum1() != 21) && (players.get(i).getSum2() != 21))) {
				System.out.println("Sorry, " + players.get(i).getName() + ". You have lost because "
						+ players.get(0).getName() + " got blackjack. Your bet of $"
						+ df.format(players.get(i).getBet()) + " is lost to the casino.");
				originalPlayers.get(i).setCash(-players.get(i).getBet());
				originalPlayers.get(0).setCash(players.get(i).getBet());
				players.get(i).setName(null);
			} else if (((players.get(i).getSum1() == 21) || (players.get(i).getSum2() == 21))
					&& ((players.get(0).getSum1() == 21) || (players.get(0).getSum2() == 21))) {
				System.out.println(players.get(i).getName() + " and " + players.get(0).getName()
						+ "got blackjack! This is a push, no transactions will be made.");
				players.get(i).setName(null);
			}
		}

	}

	public static void check3(ArrayList<Player> players, ArrayList<Player> originalPlayers, DecimalFormat df) {
		if ((players.get(0).getSum1() > 21) && (players.get(0).getSum2() > 21)) {
			System.out.println("Sorry, " + players.get(0).getName() + ". You have busted. The casino lost.");

			for (int i = 1; i < players.size(); i++) {
				if (players.get(i).getName() == null) {
					continue;
				}
				System.out.println("Congrats " + players.get(i).getName() + "! You have won $"
						+ df.format(players.get(i).getBet()));
				originalPlayers.get(i).setCash(players.get(i).getBet());
				originalPlayers.get(0).setCash(-players.get(i).getBet());
			}

		}

		else {
			int sum;
			int dealerSum;

			if (players.get(0).getSum2() > 21) {
				dealerSum = players.get(0).getSum1();
			} else {
				dealerSum = players.get(0).getSum2();
			}

			for (int j = 1; j < players.size(); j++) {
				if (players.get(j).getName() == null)
					continue;

				if (players.get(j).getSum2() > 21) {
					sum = players.get(j).getSum1();
				} else {
					sum = players.get(j).getSum2();
				}
				if (dealerSum > sum) {
					System.out.println("Sorry, " + players.get(j).getName() + ". You have lost. Your bet of $"
							+ df.format(players.get(j).getBet()) + " is lost to the casino.");
					originalPlayers.get(j).setCash(-players.get(j).getBet());
					originalPlayers.get(0).setCash(players.get(j).getBet());

				} else if (dealerSum < sum) {
					System.out.println("Congrats " + players.get(j).getName()
							+ "! You have won against the dealer and get: $" + df.format(players.get(j).getBet()));
					originalPlayers.get(j).setCash(players.get(j).getBet());
					originalPlayers.get(0).setCash(-players.get(j).getBet());
				} else {
					System.out.println(
							players.get(j).getName() + " you have tied with the dealer. You get to keep your bet of $"
									+ df.format(players.get(j).getBet()));
				}
			}
		}
	}

	public static void getTotalCash(HashMap<Integer, String> names, ArrayList<Player> originalPlayers,
			DecimalFormat df) {
		int i = 0;
		for (Player player : originalPlayers) {
			System.out.println(names.get(i) + "'s net gain/loss thus far: $" + df.format(player.getCash()));
			i++;
		}
	}

}
