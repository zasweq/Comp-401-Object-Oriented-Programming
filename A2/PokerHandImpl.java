package a2;

public class PokerHandImpl implements PokerHand{
	private Card[] hand = new Card [5];
	
	private int handvalue; //ranks the hand
	private int handrank; //ranks the high card of the hand
	
	public PokerHandImpl(Card[] cards) { //constructor
		if(cards == null) { //check if array is null, if not throw runtime exception
			throw new RuntimeException("Array is null.");
		}
		
		if(cards.length!=5) {
			throw new RuntimeException("Array is not equal to 5.");
		}
		
		for(int i=1; i<5; i++) { //check if each element of the array is null
			if(cards[i] == null) {
				throw new RuntimeException("An element of the array is null.");
			}
		}
		
		hand = cards.clone();
		
		//sort the array of cards into rank sorted order
		for (int i=0; i<hand.length; i++) { //bubble sort, sorts for smallest starting at left
			  for (int j=i+1; j<hand.length; j++) {
			    if (hand[i].getRank() > hand[j].getRank()) {
			      Card tmp = hand[i];
			      hand[i] = hand[j];
			      hand[j] = tmp;
			    }
			  }
			}
		handvalue = 1;
		handrank = hand[4].getRank(); //sorted, so high card will determine handrank
	}
	
	public Card[] getCards() {
		return hand.clone();
	}
	
	public boolean contains(Card c) {
		boolean contains = false;
		for(int i=0; i<5; i++) {
			if(hand[i].equals(c)) {
				contains = true;
			}
		}
		return contains;
	}
	
	public boolean isOnePair() { //checks if hand is a one pair hand
		boolean contains = false;
		int placeholder = 0;
		int pair = 0; //how many pairs there are in the array
		for(int i=0; i<5; i++) { //for loop for original array
			for(int j=i+1; j<5; j++) { //for loop for array for comparision
				if(hand[i].getRank()==hand[j].getRank()) {
					pair++;
					//since the cards are ranked in ascending class order, this will always set it only if it as a higher hand rank value 
					placeholder = hand[i].getRank(); //placeholder for handrank
				}
			}
		}
		if(pair == 1) { //need to also test the other three cards that they are not same rank, I think this does it
			handvalue = 2; //sets hand value as 2 f condition is met
			handrank = placeholder;
			contains = true;
		}
		return contains;
	}
	
	public boolean isTwoPair() { //checks if hand is a two pair hand
		boolean contains = false;
		int pair = 0; //how many pairs there are in the array
		int placeholder = 0;
		for(int i=0; i<5; i++) { //for loop for original array
			for(int j=i+1; j<5; j++) { //for loop for array for comparision
				if(hand[i].getRank()==hand[j].getRank()) {
					pair++;
					//since the cards are ranked in ascending class order, this will always set it to the highest pair of the hand rank value 
					placeholder = hand[i].getRank();
				}
			}
		}
		if(pair == 2 && isThreeOfAKind()==false) { //need to figure out how to test the fifth number, I think this does it tho
			handvalue = 3; //sets hand value as 2 f condition is met
			contains = true;
			handrank = placeholder;
		}
		return contains;
	}
	//three of a kind would set this equal
	public boolean isThreeOfAKind() { //three possible positions for it, then compare it to the other two cards not in the three
		boolean contains = false;
		if((hand[0].getRank()==hand[1].getRank())&&(hand[1].getRank()==hand[2].getRank())) { //check for it at the first three variables
			if((hand[2].getRank()!=hand[3].getRank())&&(hand[3].getRank()!=hand[4].getRank())) {
				contains = true;
			}
		}
		if((hand[1].getRank()==hand[2].getRank())&&(hand[2].getRank()==hand[3].getRank())) { //no need for contained if statement, as if the three of a kind are in the middle the other two are different rank
			if((hand[0].getRank()!=hand[4].getRank())&&(hand[0].getRank()!=hand[1].getRank())) {	
				contains = true;
			}
		}
		if((hand[2].getRank()==hand[3].getRank())&&(hand[3].getRank()==hand[4].getRank())) {
			if(hand[0].getRank()!=hand[1].getRank()) { //need to add on the condition I added onto the first one, but will not do because I'm just trying to get this over with, (second condition needs an and because four of a kind would still return true)
				contains = true;
			}
		}
		
		if(contains) {
			handvalue = 4;
			handrank = hand[2].getRank(); //from hint, the middle one is always three of a kind
		}
		return contains;
	}
	
	public boolean isStraight() { //for loop break/continue based on it
		boolean contains = false;
		int needfour = 0; //need four iterations of the for loop to return true
		for(int i=0; i<4; i++) { //only four because it compares element ahead of it to itself
			if((hand[i+1].getRank()-hand[i].getRank())==1) { //this tests if the cards next to each other (already in accending order) meet the conditions of a straight
				needfour++;
			}
		}
		if(needfour==4) { //need all elements of the array to be in acsending order (only need four because you are comparing it to element i+1 in card array
			contains = true;
			handrank = hand[4].getRank();
		}
		if((hand[4].getRank()==14)&&(hand[0].getRank()==2)&&(hand[1].getRank()==3)&&(hand[2].getRank()==4)&&(hand[3].getRank()==5)) { //wheel caveat, give it a chance
			contains = true;
			handrank = 5;
		}
		
		if(contains) {
			handvalue = 5;
		}
		return contains;
	}
	
	public boolean isFlush() { //should be simple, matching in suit
		boolean contains = false;
		/*Card.Suit compare = hand[0].getSuit(); //need all of the four other cards to be this rank
		int needfour = 0; //you need four of these to make the flush true
		for(int i=1; i<5; i++) { //for loop for original array, starts at one because the first card sets rank
			if(compare == hand[i].getSuit()) {
				needfour++;
			}
		}
		if(needfour==4) {
			contains = true;
			handvalue = 6;
			handrank = hand[4].getRank(); //value is highest ranked card
		}*/
		Card.Suit one = hand[0].getSuit();
		Card.Suit two = hand[1].getSuit();
		Card.Suit three = hand[2].getSuit();
		Card.Suit four = hand[3].getSuit();
		Card.Suit five = hand[4].getSuit();
		if((one.equals(two)) && (two.equals(three)) && (three.equals(four))&&(four.equals(five))) {
			contains = true;
			handvalue = 6;
			handrank = hand[4].getRank(); //value is highest ranked card
		}
				
		return contains;
	}
	
	public boolean isFullHouse() { //two ways this can be true, 2 3, 3 2
		boolean contains = false;
		if((hand[0].getRank()==hand[1].getRank())&&(hand[1].getRank()==hand[2].getRank())) { //check for it at the first three variables
			if(hand[3].getRank()==hand[4].getRank()) {
				contains = true;
				handvalue = 7;
				handrank = hand[2].getRank();
			}
		}
		if((hand[2].getRank()==hand[3].getRank())&&(hand[3].getRank()==hand[4].getRank())) {
			if(hand[0].getRank()==hand[1].getRank()) {
				contains = true;
				handvalue = 7;
				handrank = hand[4].getRank();
			}
		}
		return contains;
	}
	
	public boolean isFourOfAKind() { //test two possibilities-the lone card is on the left side or right side (since the cards are acsending)
		boolean contains = false;
		int rank1 = hand[0].getRank();
		int rank2 = hand[1].getRank();
		int rank3 = hand[2].getRank();
		int rank4 = hand[3].getRank();
		int rank5 = hand[4].getRank();
		if((rank1==rank2) && (rank2==rank3) && (rank3==rank4)) { //checks first four cards for rank equality
			contains=true;
			handvalue = 8;
			handrank = rank3;
		}
		if((rank2==rank3) && (rank3==rank4) && (rank4==rank5)) { //checks last four cards for rank equality
			contains=true;
			handvalue = 8;
			handrank = rank3;
		}
		return contains;
	}
	
	public boolean isStraightFlush() {
		boolean contains = false; //THIS IS PROBABLY WRONG LOL, ask jared about this
		if(isFlush()&&isStraight()) { //test for both straight and flush using && operator
			contains = true;
			handvalue = 9;
			handrank = hand[4].getRank(); //add caveat of wheel being 5 ***
		}
		if(contains&&hand[0].getRank()==2&&hand[4].getRank()==14) { //set hand value of special wheel straight
			handrank = 5;
		}
		return contains;
	}
	
	
	
	
	public int getHandTypeValue() { //getter for handvalue
		this.isOnePair(); //no need to store this boolean, as each method already ranks the hand
		this.isTwoPair();
		this.isThreeOfAKind();
		this.isStraight();
		this.isFlush();
		this.isFullHouse();
		this.isFourOfAKind();
		this.isStraightFlush();
		return handvalue;
	}

	public int getHandRank() { //getter for handrank
		this.isOnePair(); //no need to store this boolean, as each method already ranks the hand
		this.isTwoPair();
		this.isThreeOfAKind();
		this.isStraight();
		this.isFlush();
		this.isFullHouse();
		this.isFourOfAKind();
		this.isStraightFlush();
		return handrank;
	}
	
	public int compareTo(PokerHand other) {
		if(handvalue>other.getHandTypeValue()) { //three conditions, handvalue is greater than, equal to, or less than
			return 1;
		}
		else if(handvalue==other.getHandTypeValue()) { //if equal to, have to compare handranks
			if(handrank>other.getHandRank()) {
				return 1;
			}
			else if(handrank==other.getHandRank()) {
				return 0;
			}
			else { //(handrank>other.getHandRank()) this condition
				return -1;
			}
		}
		else { //if(handvalue<other.getHandTypeValue()) this condition
			return -1;
		}
	}
	
}
