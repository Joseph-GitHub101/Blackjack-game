import java.util.ArrayList;

public class Player {

	private String name;
	private double bet;
	private int[] sum = { 0, 0 };
	private ArrayList<Card> playersCards;

	/**
	 * @param name
	 * @param bet
	 * @param sum
	 * @param playersCards
	 */
	public Player(String name, double bet, int[] sum, ArrayList<Card> playersCards) {
		super();
		this.name = name;
		this.bet = bet;
		this.sum = sum;
		this.playersCards = playersCards;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the bet
	 */
	public double getBet() {
		return bet;
	}

	/**
	 * @param bet the bet to set
	 */
	public void setBet(double bet) {
		this.bet = bet;
	}

	/**
	 * @return the sum
	 */
	public int getSum1() {
		return sum[0];
	}

	public int getSum2() {
		return sum[1];
	}

	/**
	 * @param i the sum to set
	 */
	public void setSum1(int i) {
		this.sum[0] = i;
	}

	public void setSum2(int i) {
		this.sum[1] = i;
	}

	/**
	 * @return the playersCards
	 */
	public ArrayList<Card> getPlayersCards() {
		return playersCards;
	}

	/**
	 * @param playersCards the playersCards to set
	 */
	public void setPlayersCards(Card card) {

		this.playersCards.add(card);
	}

}
