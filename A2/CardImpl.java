package a2;

public class CardImpl implements Card {
	private int _rank;
	private Card.Suit _suit;
	public CardImpl(int rank, Card.Suit suit) {
		if(rank<2||rank>14) {
			throw new RuntimeException("Rank is out of Range");
		}
		_rank = rank;
		_suit = suit;
	}
	
	public int getRank() {
		return _rank;
	}
	
	public Card.Suit getSuit(){
		return _suit;
	}
	
	public String toString() {
		String rankenglish;
		String puttogether;
		if (_rank==2) {
			rankenglish = "Two";
		}
		else if (_rank==3) {
			rankenglish = "Three";
		}
		else if (_rank==4) {
			rankenglish = "Four";
		}
		else if (_rank==5) {
			rankenglish = "Five";
		}
		else if (_rank==6) {
			rankenglish = "Six";
		}
		else if (_rank==7) {
			rankenglish = "Seven";
		}
		else if (_rank==8) {
			rankenglish = "Eight";
		}
		else if (_rank==9) {
			rankenglish = "Nine";
		}
		else if (_rank==10) {
			rankenglish = "Ten";
		}
		else if (_rank==11) {
			rankenglish = "Jack";
		}
		else if (_rank==12) {
			rankenglish = "Queen";
		}
		else if (_rank==13) {
			rankenglish = "King";
		}
		else { //if (_rank==14) condition
			rankenglish = "Ace";
		}
		puttogether = rankenglish + " of " + _suit; //figure this out
		return puttogether;
	}
	
	public boolean equals(Card other) {
		return(_rank == other.getRank() && _suit == other.getSuit());
	}
	//Tester Class
	/*public static void main(String args[]) { 
		CardImpl obj = new CardImpl(5, Card.Suit.SPADES);
		System.out.print(obj.toString());
	}*/
	

	
}
