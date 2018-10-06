package randomDice;
//Test driven development-Write test cases before writing actual code
import java.util.List;

public interface Dice {
	int getSides(); //how many sides we have
	int rollDie(); //roll die
	List<Integer> getRollHistory(); //returns history in array
	int getSpecificRoll(int i);
	int getNumOfRolls(); //return how many time the dice was rolled
}
