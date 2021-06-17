public class Card {

	// the card's rank: 2-10, Jack Queen King
	private String rank;
	// card's suit: clubs, diamonds, spades, hearts
	private String suit;

	/**
	 * @param rank
	 * @param suit
	 */
	public Card(String rank, String suit) {
		super();
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @return the suit
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * @param suit the suit to set
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}

	@Override
	public String toString() {
		return this.getRank() + " of " + this.getSuit();
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

}
