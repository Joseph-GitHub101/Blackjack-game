import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Random;

public class Blackjack {
	// I might be able to organize the main a little better by creating more methods
	// and calling them in the main instead of using somewhat repeated code.
	// Also consider doing a while loop for entire main method to allow many rounds
	// of blackjack games.
	public static void main(String[] args) {

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

		ArrayList<Card> cards = new ArrayList<>();
		String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		String[] suit = { "Clubs", "Diamonds", "Spades", "Hearts" };

		cardList(cards, rank, suit, numDecks);

		ArrayList<Player> playerList = new ArrayList<>();
		setPlayers(playerList, scan, numPlayers);

		Card firstDeal;
		boolean displayDealerHand = false;
		for (int i = 0; i < 2; i++) {

			for (int j = 0; j < playerList.size(); j++) {
				firstDeal = pickCard(cards, numDecks);
				// the line below is useful to see what picks everyone gets at first, so I'll
				// just leave it be.
				// System.out.println(playerList.get(j).getName() + " you picked a " +
				// firstDeal.getRank() + " of " + firstDeal.getSuit() + "\n");
				playerList.get(j).setPlayersCards(firstDeal);
				int value = cardValue(firstDeal);

				setValue(playerList, value, j);
			}
		}

		displayCardHands(playerList, displayDealerHand);

		for (int i = 1; i < playerList.size(); i++) {
			if (((playerList.get(i).getSum1() == 21) || (playerList.get(i).getSum2() == 21))
					&& ((playerList.get(0).getSum1() != 21) && (playerList.get(0).getSum2() != 21))) {
				System.out.println("Congrats " + playerList.get(i).getName() + "! You got blackjack. You now have: $"
						+ df.format(playerList.get(i).getBet() * 2.5));
				playerList.get(i).setName(null);
			} else if (((playerList.get(0).getSum1() == 21) || (playerList.get(0).getSum2() == 21))
					&& ((playerList.get(i).getSum1() != 21) && (playerList.get(i).getSum2() != 21))) {
				System.out.println("Sorry, " + playerList.get(i).getName() + ". You have lost because "
						+ playerList.get(0).getName() + " got blackjack. Your bet of $"
						+ df.format(playerList.get(i).getBet()) + " is lost to the casino.");
				playerList.get(i).setName(null);
			} else if (((playerList.get(i).getSum1() == 21) || (playerList.get(i).getSum2() == 21))
					&& ((playerList.get(0).getSum1() == 21) || (playerList.get(0).getSum2() == 21))) {
				System.out.println(playerList.get(i).getName() + " and " + playerList.get(0).getName()
						+ "got blackjack! This is a push, no transactions will be made.");
				playerList.get(i).setName(null);
			}
		}
		if (allNamesNull(playerList)) {
			System.exit(0);
		}

		int i = 1;
		for (Iterator<Player> itr = playerList.subList(1, playerList.size()).iterator(); itr.hasNext(); i++) {
			itr.next();
			boolean pick = true;
			while ((playerList.get(i).getName() != null) && (pick)) {

				scan = new Scanner(System.in);
				System.out.println(
						playerList.get(i).getName() + ", would you like to hit or stay? Type 'h' to hit, 's' to stay.");
				String hitOrStay = scan.nextLine();
				while (!(hitOrStay.equals("h") || hitOrStay.equals("s"))) {

					System.out.println("Wrong input. Type 'h' to hit, 's' to stay.");
					hitOrStay = scan.nextLine();
				}
				if (hitOrStay.equals("h")) {
					Card cardPick = pickCard(cards, numDecks);
					System.out.println(playerList.get(i).getName() + " just picked: " + cardPick.getRank() + " of "
							+ cardPick.getSuit());

					playerList.get(i).setPlayersCards(cardPick);
					int value = cardValue(cardPick);

					setValue(playerList, value, i);

				} else {
					pick = false;
				}

				displayCardHands(playerList, displayDealerHand);

				if ((playerList.get(i).getSum1() > 21) && (playerList.get(i).getSum2() > 21)) {
					System.out.println("Sorry, " + playerList.get(i).getName() + ". You have busted. Your bet of $"
							+ df.format(playerList.get(i).getBet()) + " is lost to the casino.");
					playerList.get(i).setName(null);

					// itr.remove(); --- this was working only 75% of the time. It would sometimes
					// remove a player, sometimes not remove a player.
				}

			}

		}

		if (allNamesNull(playerList)) {
			System.out.println("Game over!");
			System.exit(0);
		}

		System.out.println("\nDealer's turn:\n");
		displayDealerHand = true;
		displayCardHands(playerList, displayDealerHand);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}

		while (((playerList.get(0).getSum1() < 17) && (playerList.get(0).getSum2() < 17))
				|| ((playerList.get(0).getSum1() < 17) && (playerList.get(0).getSum2() > 21))) {

			firstDeal = pickCard(cards, numDecks);
			System.out.println(playerList.get(0).getName() + " just picked: " + firstDeal.getRank() + " of "
					+ firstDeal.getSuit());
			playerList.get(0).setPlayersCards(firstDeal);
			int value = cardValue(firstDeal);

			setValue(playerList, value, 0);

			displayCardHands(playerList, displayDealerHand);

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}

		if ((playerList.get(0).getSum1() > 21) && (playerList.get(0).getSum2() > 21)) {
			System.out.println("Sorry, " + playerList.get(0).getName() + ". You have busted. The casino lost.");
			for (Player player : playerList.subList(1, playerList.size())) {
				if (player.getName() == null)
					continue;
				System.out
						.println("Congrats " + player.getName() + "! You now have: $" + df.format(player.getBet() * 2));
			}
			System.exit(0);
		}

		else {
			int sum;
			int dealerSum;

			if (playerList.get(0).getSum2() > 21) {
				dealerSum = playerList.get(0).getSum1();
			} else {
				dealerSum = playerList.get(0).getSum2();
			}

			for (Player player : playerList.subList(1, playerList.size())) {
				if (player.getName() == null)
					continue;

				if (player.getSum2() > 21) {
					sum = player.getSum1();
				} else {
					sum = player.getSum2();
				}
				if (dealerSum > sum) {
					System.out.println("Sorry, " + player.getName() + ". You have lost. Your bet of $"
							+ df.format(player.getBet()) + " is lost to the casino.");
					player.setBet(0);
				} else if (dealerSum < sum) {
					System.out.println("Congrats " + player.getName()
							+ "! You have won against the dealer and now have: $" + df.format(player.getBet() * 2));
				} else {
					System.out
							.println(player.getName() + " you have tied with the dealer. You get to keep your bet of $"
									+ df.format(player.getBet()));
				}
			}
		}

		scan.close();
		System.exit(0);
	}

	public static void cardList(ArrayList<Card> cards, String[] rank, String[] suit, int numDecks) {

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

	public static void setPlayers(ArrayList<Player> playerList, Scanner scan, int numPlayers) {
		String playerName;
		double bet;

		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "first");
		map.put(2, "second");
		map.put(3, "third");
		map.put(4, "fourth");
		map.put(5, "fifth");
		map.put(6, "sixth");

		scan = new Scanner(System.in);
		System.out.println("What name would the dealer like to use?");
		playerName = scan.nextLine();
		ArrayList<Card> dealerCards = new ArrayList<>();
		int[] cardSum1 = { 0, 0 };
		Player dealer = new Player(playerName, 0, cardSum1, dealerCards);
		playerList.add(dealer);

		for (int i = 1; i <= numPlayers; i++) {
			scan = new Scanner(System.in);
			System.out.println("What name would the " + map.get(i) + " player like to use?");
			playerName = scan.nextLine();
			System.out.println(playerName + " what bet would you like to place?");
			bet = scan.nextDouble();
			while (bet <= 0) {
				System.out.println("Invalid bet, you fool! Bets are in positive dollar values only. Try again: ");
				bet = scan.nextDouble();
			}
			ArrayList<Card> playerCards = new ArrayList<>();
			int[] cardSum2 = { 0, 0 };
			Player player = new Player(playerName, bet, cardSum2, playerCards);
			playerList.add(player);
		}

	}

	public static Card pickCard(ArrayList<Card> cards, int numDecks) {
		Random rand = new Random();
		int magicNumber = rand.nextInt(52 * numDecks);
		Card randCard = cards.get(magicNumber);
		while (randCard == null) {
			magicNumber = rand.nextInt(52 * numDecks);
			randCard = cards.get(magicNumber);
		}
		cards.set(magicNumber, null);
		return randCard;
	}

	public static int cardValue(Card cardPick) {
		String cardVal = cardPick.getRank();
		if (cardVal.equals("2")) {
			return 2;
		} else if (cardVal.equals("3")) {
			return 3;
		} else if (cardVal.equals("4")) {
			return 4;
		} else if (cardVal.equals("5")) {
			return 5;
		} else if (cardVal.equals("6")) {
			return 6;
		} else if (cardVal.equals("7")) {
			return 7;
		} else if (cardVal.equals("8")) {
			return 8;
		} else if (cardVal.equals("9")) {
			return 9;
		} else if (cardVal.equals("10") || cardVal.equals("Jack") || cardVal.equals("Queen")
				|| cardVal.equals("King")) {
			return 10;
		}

		else {
			return 0;
		}
	}

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
				System.out.println(card.getRank() + " of " + card.getSuit());
			}
		} else {

			System.out.println("\n" + players.get(0).getName() + "'s known card: "
					+ players.get(0).getPlayersCards().get(0).getRank() + " of "
					+ players.get(0).getPlayersCards().get(0).getSuit() + "\n");

		}

		System.out.println();
		System.out.println("Players' hands:\n\n");

		for (int i = 1; i < players.size(); i++) {
			if (players.get(i).getName() == null)
				continue;

			System.out.println(players.get(i).getName() + "'s cards:\n");
			for (Card card : players.get(i).getPlayersCards()) {
				System.out.println(card.getRank() + " of " + card.getSuit());
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean allNamesNull(ArrayList<Player> players) {
		int count = 0;
		for (int i = 1; i < players.size(); i++) {
			if (players.get(i).getName() != null) {
				count += 1;
			}
		}
		return count == 0;
	}

}
