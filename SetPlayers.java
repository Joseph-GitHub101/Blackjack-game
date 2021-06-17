import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SetPlayers {

	public static void createPlayers(ArrayList<Player> players, Scanner scan, int numPlayers, int round,
			HashMap<Integer, String> names, ArrayList<Player> originalPlayers) {
		String dealerName;
		String playerName;
		double bet;

		scan = new Scanner(System.in);
		if (round == 1) {
			System.out.println("What name would the dealer like to use?");
			dealerName = scan.nextLine();
			names.put(0, dealerName);
		}

		ArrayList<Card> dealerCards = new ArrayList<Card>();
		int[] dealerSums = { 0, 0 };

		if (round == 1) {
			Player dealer = new Player(names.get(0), 0, dealerSums, dealerCards, 0);
			players.add(dealer);

		} else {
			Player dealer = new Player(names.get(0), 0, dealerSums, dealerCards, originalPlayers.get(0).getCash());
			players.add(dealer);
		}

		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "first");
		map.put(2, "second");
		map.put(3, "third");
		map.put(4, "fourth");
		map.put(5, "fifth");
		map.put(6, "sixth");

		for (int i = 1; i <= numPlayers; i++) {
			scan = new Scanner(System.in);
			if (round == 1) {
				System.out.println("What name would the " + map.get(i) + " player like to use?");
				playerName = scan.nextLine();
				names.put(i, playerName);
			}
			System.out.println(names.get(i) + " what bet would you like to place?");
			bet = scan.nextDouble();
			while (bet <= 0) {
				System.out.println("Invalid bet, you fool! Bets are in positive dollar values only. Try again: ");
				bet = scan.nextDouble();
			}
			ArrayList<Card> playerCards = new ArrayList<Card>();
			int[] playerSums = { 0, 0 };
			if (round == 1) {
				Player player = new Player(names.get(i), bet, playerSums, playerCards, 0);
				players.add(player);
			} else {
				Player player = new Player(names.get(i), bet, playerSums, playerCards,
						((ArrayList<Player>) originalPlayers).get(i).getCash());
				players.add(player);
			}
		}
		if (round == 1) {
			for (int i = 0; i < players.size(); i++) {
				originalPlayers.add(players.get(i));
			}
		}
	}

}
