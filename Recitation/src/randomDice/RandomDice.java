package randomDice;
import java.util.Scanner;
import java.util.Random;

public class RandomDice {
	public static void main(String[] args) { //type this correctly this is why you are getting so many errors
		System.out.print("Hello World");
		Scanner s = new Scanner(System.in); //always use new to create objects
		System.out.println("Please enter how many dice you want to roll: "); //you can split it up over two lines using +
		int diceRolls = 0;
		diceRolls = s.nextInt();
		int[]dice = rollDice(diceRolls);
		getMax(dice);
		getMin(dice);
		getAverage(dice);
	
	}
	
	public static int[] rollDice(int diceRolls) {
		Random r = new Random();
		int [] dice = new int[diceRolls]; //whenever you declare an array in java, they stay the same size
		for(int i = 0; i<dice.length; i++) {
			dice[i] = r.nextInt(6) + 1; //a random number greater than or equal to zero and less than the parameter
			System.out.println(dice[i]);
		}
		return dice;
	
		
	}
	public static void getMax(int[] dice) {
		int max = 0;
		for(int i = 0; i<dice.length; i++) {
			if(dice[i]>max) {
				max = dice[i];
			}
		}
		
		System.out.println("The maximum is " + max);
	}
	public static void getMin(int[] dice) {
		int min = 7;
		for(int i = 0; i<dice.length; i++) {
			if(dice[i]<min) {
				min = dice[i];
			}
		}
		
		System.out.println("The minimum is " + min);
			
	}
	public static double getAverage(int[] dice) {
		double total = 0.0;
		double average;
		for(int i = 0; i<dice.length; i++) {
			total += dice[i];
		}
		average = total/dice.length;
		System.out.println("The average is " + average);
		return average;
	}

}
