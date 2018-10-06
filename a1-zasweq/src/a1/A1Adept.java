package a1;
import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num_of_assignments = scanner.nextInt();
		int[] assignment_scores = new int [num_of_assignments];
		for(int count = 0; count<num_of_assignments; count++) {
			int assignment_score = scanner.nextInt();
			assignment_scores[count] = assignment_score;
		}
		double participation_total_points = scanner.nextDouble();
		int num_of_students = scanner.nextInt();
		for(int countt=0; countt<num_of_students; countt++) {
			String first_name = scanner.next();
			char first_initial = first_name.charAt(0);
			String last_name = scanner.next();
			double participation_points = scanner.nextDouble();
			double[] student_assignment_scores = new double [num_of_assignments];
			for(int counttt = 0; counttt<num_of_assignments; counttt++) {
				student_assignment_scores[counttt] = scanner.nextDouble();
			}
			double midterm_grade = scanner.nextDouble();
			double final_grade = scanner.nextDouble();
			String letter_grade;
			double assignment_grade;
			double assignment_total_points = 0.0;
			double student_assignment_points = 0.0;
			for(int countttt=0; countttt<num_of_assignments; countttt++) {
				student_assignment_points += student_assignment_scores[countttt];
				assignment_total_points += assignment_scores[countttt];
			}
			assignment_grade = ((student_assignment_points/assignment_total_points)*100);
		
			double participation_grade = ((participation_points/(participation_total_points*.8))*100);
			if (participation_grade > 100) {
				participation_grade = 100;
			}
	
			double percent_grade = ((assignment_grade*.4)+(participation_grade*.15)+(midterm_grade*.2)+(final_grade*.25));			
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

