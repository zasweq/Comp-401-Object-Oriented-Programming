package a1;
import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num_of_students = scanner.nextInt();
		//could make six arrays? should I do that, could make a for loop?
		for(int count=0; count<num_of_students; count++) {
			String first_name = scanner.next();
			char first_initial = first_name.charAt(0);
			String last_name = scanner.next();
			double assignment_grade = scanner.nextDouble();
			double participation_grade = scanner.nextDouble();
			double midterm_grade = scanner.nextDouble();
			double final_grade = scanner.nextDouble();
			double percent_grade;
			String letter_grade;
			percent_grade = ((assignment_grade*.4)+(participation_grade*.15)+(midterm_grade*.2)+(final_grade*.25));			
			if(percent_grade>=94) {
				letter_grade = "A";
			}
			else if(percent_grade>=90) {
				letter_grade = "A-";
			}
			else if(percent_grade>=86) {
				letter_grade = "B+";
			}
			else if(percent_grade>=83) {
				letter_grade = "B";
			}
			else if(percent_grade>=80) {
				letter_grade = "B-";
			}
			else if(percent_grade>=76) {
				letter_grade = "C+";
			}
			else if(percent_grade>=73) {
				letter_grade = "C";
			}
			else if(percent_grade>=70) {
				letter_grade = "C-";
			}
			else if(percent_grade>=65) {
				letter_grade = "D+";
			}
			else if(percent_grade>=60) {
				letter_grade = "D";
			}
			else {
				letter_grade = "F";
			}
			System.out.println(first_initial + ". " + last_name + " " + letter_grade);
		
	}		
	}
}
