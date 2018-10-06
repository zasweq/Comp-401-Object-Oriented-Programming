package a2;
import java.util.Scanner;
//left is interface, right is class name
public class HandEvaluator {
	public static void main(String args []) {
		boolean continuee = true;
		Scanner scanner = new Scanner(System.in);
		while(continuee) { //this is the case for zero
		int players = scanner.nextInt();
		if(players==0) {
			continuee = false;
			//break;
		}
		Card [] hand = new Card [5]; //array that will get passed to pokerhand constructor
		for(int i=0; i<5; i++) { //fills hand up with values
			int rank = scanner.nextInt();
			String type = scanner.next(); //correct syntax?
			//System.out.println(type); //test
			Card card = new CardImpl(rank, Suit(type)); //create a helper function for the second part of this
			//place card in array of final hand for the USER
			hand[i] = card;
		}
		PokerHand pokerhand = new PokerHandImpl(hand);
		int count = 0; //counts how many times you win
		//tests
		//System.out.println(players);
		//System.out.println(pokerhand.getHandRank());
		//System.out.println(pokerhand.getHandTypeValue());
		//System.out.println(hand[0].getSuit());
		//System.out.println(hand[1].getSuit());
		//System.out.println(hand[2].getSuit());
		//System.out.println(hand[3].getSuit());
		//System.out.println(hand[4].getSuit());

		for(int i = 0; i<10000; i++) { //for loop for 10000 cases
			int win = 0; //int to test if you win, for loop with number of players, compare each of the hands dealt to the hand you have, if you lose to one of them you don't get a++ to this, will compare to how many players
			Deck deck = new DeckImpl(); //10000 for loop above this so deck gets remade every time
			//for loop that removes players hand from deck
			for(int k = 0; k<5; k++) {
				deck.findAndRemove(hand[k]);
			}
			
			for (int j = 0; j<players; j++) {
				PokerHand opphand = deck.dealHand();
				pokerhand.getHandRank(); //rank both hands
				pokerhand.getHandTypeValue();
				int one=pokerhand.compareTo(opphand); //who wins?
				if(one==1) { //if your hand wins
					win++;
				}
			}
			if(win==players) {
				count++;
			}
		}
		System.out.println((int) Math.rint(count/100.00));
		//System.out.println(continuee);
		}
	}







	//helper function, pass a string, return an object you can use for second part of card construction
	private static Card.Suit Suit(String s){
		if(s.equals("S")) { //used equals and not .equals which was wrong
			return Card.Suit.SPADES;
		}
		else if(s.equals("H")) {
			return Card.Suit.HEARTS;
		}
		else if(s.equals("D")) {
			return Card.Suit.DIAMONDS;
		}
		else { //if(s=="C") test for this condition
			return Card.Suit.CLUBS;
		}
	}

}

