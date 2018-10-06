package randomDice;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//Polymorphism
public class Recitation2 implements Dice { //it will use these methods
	//should always declare variable in constructor
	private int sides;
	//private int[] rolls;
	//changed to protected so weightedDice can access it
	protected List<Integer> rolls = new ArrayList<Integer>(); //a list can't store primitive types, Integer isn't a primitive type
	//fixed size vs. add or remove
	//same type vs.
	//support type vs. supporting mostly any type
	//Arrays vs. Lists

	public Recitation2(int sides) {
		this.sides = sides; //since sides is also the parameter name
	}
	
	
	@Override
	public int getSides() {
		return sides;
	}

	@Override
	public int rollDie() {
		Random r = new Random();
		int roll = r.nextInt(6)+1;
		rolls.add(roll);
		//rolls.sort(c);
		return roll;
	}

	@Override
	public List<Integer> getRollHistory() {
		return rolls;
	}

	@Override
	public int getSpecificRoll(int i) {
		return rolls.get(i);
	}

	@Override
	public int getNumOfRolls() {
		return rolls.size();
	}
	
}
