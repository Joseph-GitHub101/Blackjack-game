import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
//might be a good idea to add a 1 second time out between certain program outputs
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class Blackjack {
	//I need to organize the main method better by creating more methods and calling them in the main instead of using repeated code.
	//Also consider doing a while loop for entire main method to allow many rounds of blackjack games.
	public static void main(String[] args) {
		
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
		String[] suit = { "clubs", "diamonds", "spades", "hearts" };
		cardList(cards, rank, suit, numDecks);

		ArrayList<Player> playerList = new ArrayList<>();
		
		String playerName;
		int bet;
		
		for (int i = 1; i <= numPlayers + 1; i++) {
			switch (i) {
			case 1:
				scan = new Scanner(System.in);
				System.out.println("What name would the dealer like to use?");
				playerName = scan.nextLine();
				ArrayList<Card> dealerCards = new ArrayList<>();
				int[] cardSum1 = {0,0};
				Player dealer = new Player(playerName, 0, cardSum1, dealerCards);
				playerList.add(dealer);
				break;
				
			case 2:
				scan = new Scanner(System.in);
				System.out.println("What name would the first player like to use?");
				playerName = scan.nextLine();
				System.out.println(playerName + " what bet would you like to place?");
				bet = scan.nextInt();
				while(bet<=0) {
					System.out.println("Invalid bet, you fool! Bets are in positive dollar values only. Try again: ");
					bet = scan.nextInt();
				}
				ArrayList<Card> player1Cards = new ArrayList<>();
				int[] cardSum2 = {0,0};
				Player player1 = new Player(playerName, bet, cardSum2, player1Cards);
				playerList.add(player1);
				break;
			case 3:
				scan = new Scanner(System.in);
				System.out.println("What name would the second player like to use?");
				playerName = scan.nextLine();
				System.out.println(playerName + " what bet would you like to place?");
				bet = scan.nextInt();
				while(bet<=0) {
					System.out.println("Invalid bet, you fool! Bets are in positive dollar values only. Try again: ");
					bet = scan.nextInt();
				}
				ArrayList<Card> player2Cards = new ArrayList<>();
				int[] cardSum3 = {0,0};
				Player player2 = new Player(playerName, bet, cardSum3, player2Cards);
				playerList.add(player2);
				break;
			case 4:
				scan = new Scanner(System.in);
				System.out.println("What name would the third player like to use?");
				playerName = scan.nextLine();
				System.out.println(playerName + " what bet would you like to place?");
				bet = scan.nextInt();
				while(bet<=0) {
					System.out.println("Invalid bet, you fool! Bets are in positive dollar values only. Try again: ");
					bet = scan.nextInt();
				}
				ArrayList<Card> player3Cards = new ArrayList<>();
				int[] cardSum4 = {0,0};
				Player player3 = new Player(playerName, bet, cardSum4, player3Cards);
				playerList.add(player3);
				break;
			case 5:
				scan = new Scanner(System.in);
				System.out.println("What name would the fourth player like to use?");
				playerName = scan.nextLine();
				System.out.println(playerName + " what bet would you like to place?");
				bet = scan.nextInt();
				while(bet<=0) {
					System.out.println("Invalid bet, you fool! Bets are in positive dollar values only. Try again: ");
					bet = scan.nextInt();
				}
				ArrayList<Card> player4Cards = new ArrayList<>();
				int[] cardSum5 = {0,0};
				Player player4 = new Player(playerName, bet, cardSum5, player4Cards);
				playerList.add(player4);
				break;
			case 6:
				scan = new Scanner(System.in);
				System.out.println("What name would the fifth player like to use?");
				playerName = scan.nextLine();
				System.out.println(playerName + " what bet would you like to place?");
				bet = scan.nextInt();
				while(bet<=0) {
					System.out.println("Invalid bet, you fool! Bets are in positive dollar values only. Try again: ");
					bet = scan.nextInt();
				}
				ArrayList<Card> player5Cards = new ArrayList<>();
				int[] cardSum6 = {0,0};
				Player player5 = new Player(playerName, bet, cardSum6, player5Cards);
				playerList.add(player5);
				break;
			case 7:
				scan = new Scanner(System.in);
				System.out.println("What name would the sixth player like to use?");
				playerName = scan.nextLine();
				System.out.println(playerName + " what bet would you like to place?");
				bet = scan.nextInt();
				while(bet<=0) {
					System.out.println("Invalid bet, you fool! Bets are in positive dollar values only. Try again: ");
					bet = scan.nextInt();
				}
				ArrayList<Card> player6Cards = new ArrayList<>();
				int[] cardSum7 = {0,0};
				Player player6 = new Player(playerName, bet, cardSum7, player6Cards);
				playerList.add(player6);
				break;
			}
		}
		Card firstDeal;
		boolean displayDealerHand = false;
		for (int i = 0; i < 2; i++) {
			
			for (Player player : playerList) {
				firstDeal = SelectCard.pickCard(cards, numDecks);
				//System.out.println(player.getName() + " you picked a " + firstDeal + "\n");
				player.setPlayersCards(firstDeal);
				int value = cardValue(firstDeal);
				
				if (value != 0) {
					player.setSum1(player.getSum1() + value);
					player.setSum2(player.getSum2() + value);
				}
				
				if (value==0 && (player.getSum1() == player.getSum2())) {
				 
					player.setSum1(player.getSum1() + 1);
					player.setSum2(player.getSum2() + 11);
				}
				else if (value==0 && (player.getSum1() != player.getSum2())){
					player.setSum1(player.getSum1() + 1);
					player.setSum2(player.getSum2() + 1);
				}
		}
		}
		
		displayCardHands(playerList, displayDealerHand);
		
		for(int i=1; i<playerList.size(); i++) {
			if(((playerList.get(i).getSum1()==21) || (playerList.get(i).getSum2()==21)) && ((playerList.get(0).getSum1() !=21) && (playerList.get(0).getSum2() !=21))) {
				System.out.println("Congrats " + playerList.get(i).getName() + "!" + " You now have: $" + playerList.get(i).getBet()*2.5);
				playerList.remove(playerList.get(i));
			}
			else if(((playerList.get(0).getSum1()==21) || (playerList.get(0).getSum2()==21)) && ((playerList.get(i).getSum1() !=21) && (playerList.get(i).getSum2() !=21))) {
				System.out.println("Sorry, " + playerList.get(i).getName() + ". You have lost. Your bet of $" + playerList.get(i).getBet() + " is lost to the casino.");
				playerList.remove(playerList.get(i));
			}
		}
			if(playerList.size() ==1) {
				System.exit(0);
			}
		
			//I don't know why, but for some reason with certain program runs, the iterator fails to remove a player that has busted 
			//(throws an IllegalStateException). It might be a problem with removing at the end of the playerList 
			//(even though this actually works about half the time anyway).
			//Maybe it is an Eclipse glitch? It works almost all the time, however.
			int i=1;
			for (Iterator<Player> itr = playerList.subList(1, playerList.size()).iterator(); itr.hasNext(); i++) {
				itr.next();
				boolean pick = true;
				while (pick && (playerList.size() > i)) {
					scan = new Scanner(System.in);
					System.out.println(playerList.get(i).getName() + ", would you like to hit or stay? Type 'h' to hit, 's' to stay.");
					String hitOrStay = scan.nextLine();
					while (!(hitOrStay.equals("h") || hitOrStay.equals("s"))) {
						
						System.out.println("Wrong input. Type 'h' to hit, 's' to stay.");
						hitOrStay = scan.nextLine();
					}
					if (hitOrStay.equals("h")) {
						Card cardPick = SelectCard.pickCard(cards, numDecks);
						System.out.println(playerList.get(i).getName() + " just picked: " + cardPick.getRank() + " of " + cardPick.getSuit());
						
						playerList.get(i).setPlayersCards(cardPick);
						int value = cardValue(cardPick);
						
						if (value != 0) {
							playerList.get(i).setSum1(playerList.get(i).getSum1() + value);
							playerList.get(i).setSum2(playerList.get(i).getSum2() + value);
						}
						
						if (value==0 && (playerList.get(i).getSum1() == playerList.get(i).getSum2())) {
						 
							playerList.get(i).setSum1(playerList.get(i).getSum1() + 1);
							playerList.get(i).setSum2(playerList.get(i).getSum2() + 11);
						}
						else if (value==0 && (playerList.get(i).getSum1() != playerList.get(i).getSum2())){
							playerList.get(i).setSum1(playerList.get(i).getSum1() + 1);
							playerList.get(i).setSum2(playerList.get(i).getSum2() + 1);
						}
						
					} else {
						pick = false;
					}
					
					displayCardHands(playerList, displayDealerHand);
					
					if ((playerList.get(i).getSum1()>21) && (playerList.get(i).getSum2()>21)) {
						System.out.println("Sorry, " + playerList.get(i).getName() + ". You have busted. Your bet of $" + playerList.get(i).getBet()+" is lost to the casino.");
						itr.remove();
					}
				}
				
			}
			
			if (playerList.size() ==1) {
				System.exit(0);
			}
			
			System.out.println("\nDealer's turn:\n");
			displayDealerHand = true;
			displayCardHands(playerList, displayDealerHand);
			
			//int dealerSum1 = playerList.get(0).getSum1();
			//int dealerSum2 = playerList.get(0).getSum2();
			
			while((playerList.get(0).getSum1() < 17) && (playerList.get(0).getSum2() < 17)) {
			
			firstDeal = SelectCard.pickCard(cards, numDecks);
			System.out.println(playerList.get(0).getName() + " just picked: " + firstDeal.getRank() + " of " + firstDeal.getSuit());
			playerList.get(0).setPlayersCards(firstDeal);
			int value = cardValue(firstDeal);
			if (value != 0) {
				playerList.get(0).setSum1(playerList.get(0).getSum1() + value);
				playerList.get(0).setSum2(playerList.get(0).getSum2() + value);
			}
			
			if (value==0 && (playerList.get(0).getSum1() == playerList.get(0).getSum2())) {
			 
				playerList.get(0).setSum1(playerList.get(0).getSum1() + 1);
				playerList.get(0).setSum2(playerList.get(0).getSum2() + 11);
			}
			else if (value==0 && (playerList.get(0).getSum1() != playerList.get(0).getSum2())){
				playerList.get(0).setSum1(playerList.get(0).getSum1() + 1);
				playerList.get(0).setSum2(playerList.get(0).getSum2() + 1);
			}
			displayCardHands(playerList, displayDealerHand);
			}
				
			if((playerList.get(0).getSum1() > 21) && (playerList.get(0).getSum2() > 21)) {
				System.out.println("Sorry, " + playerList.get(0).getName() + ". You have busted. The casino lost.");
				for (Player player : playerList.subList(1, playerList.size())) {
					
					System.out.println("Congrats " + player.getName() + "! You now have: $" + player.getBet()*2);
				}
				System.exit(0);
			}
			
			else {
				int sum;
				int dealerSum;
				
				if(playerList.get(0).getSum2()>21) {
					dealerSum = playerList.get(0).getSum1();
				}
				else {
					dealerSum = playerList.get(0).getSum2();
				}
				
				for (Player player : playerList.subList(1, playerList.size())) {
					if(player.getSum2()>21) {
						sum = player.getSum1();
					}
					else {
						sum = player.getSum2();
					}
					if (dealerSum > sum) {
						System.out.println("Sorry, " + player.getName() + ". You have lost. Your bet of $" + player.getBet() + " is lost to the casino.");
						player.setBet(0);
						//playerList.remove(player);
					}
					else if (dealerSum < sum){
						System.out.println("Congrats " + player.getName() + "! You have won against the dealer and now have: $" + player.getBet()*2);
					}
					else {
						System.out.println(player.getName() + " you have tied with the dealer. You get to keep your cash.");
					}
				}
			}
			
		scan.close();
		System.exit(0);
	}


	public static void cardList(List<Card> cards, String[] rank, String[] suit, int numDecks) {

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
	

	public static void displayCardHands(ArrayList<Player> players, boolean displayDealerHand) {
		if (displayDealerHand == true) {
			System.out.println(players.get(0).getName() + "'s hand:\n");
			for (Card card : players.get(0).getPlayersCards()) {
				System.out.println(card.getRank() + " of " + card.getSuit());
			}
		} else {
			
			System.out.println("\n" + players.get(0).getName() + "'s known card: "
			+ players.get(0).getPlayersCards().get(0).getRank() + " of " + players.get(0).getPlayersCards().get(0).getSuit() + "\n");
			 
		}

		System.out.println();
		System.out.println("Players' hands:\n\n");
		
		//Player player : players.subList(1, players.size())
		for (int i=1; i< players.size(); i++) {
			
			System.out.println(players.get(i).getName() + "'s cards:\n");
			for (Card card : players.get(i).getPlayersCards()) {
				System.out.println(card.getRank() + " of " + card.getSuit());
			}
		System.out.println();
		}
		System.out.println();
	}

}
