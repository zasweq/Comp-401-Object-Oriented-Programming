package randomDice;

import java.util.List;
import java.util.Random;
//should be fairdice lol
public class weightedDice extends Recitation2{ //everything in fair dice will be connected to this class

	private int favorableNumber;
	
	public weightedDice(int sides, int favorableNumber) {
		super(sides);
		this.favorableNumber = favorableNumber;
	}
	
	//favorable number to land 70% of the time
	//Other 30% has to be normal

	@Override
	public int rollDie() {
		Random n = new Random();
		int roll = favorableNumber;
		if(n.nextDouble()<.7) {
			getRollHistory().add(roll);
			return roll;
		} else {
			return super.rollDie(); //return normal dice roll 30% of the time
		}
	}
}
