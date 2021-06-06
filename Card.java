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

}
