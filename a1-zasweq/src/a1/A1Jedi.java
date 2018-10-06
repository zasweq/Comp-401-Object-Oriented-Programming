package a1;
import java.util.Scanner;

public class A1Jedi {

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
		//do what knp's hint was in assignment
		double[] participation_grades = new double [num_of_students];
		double[] assignment_grades = new double [num_of_students];
		int[] midterm_grades = new int[num_of_students];
		int[] final_grades = new int[num_of_students];
		for(int countt=0; countt<num_of_students; countt++) {
			String first_name = scanner.next();
			String last_name = scanner.next();
			double participation_points = scanner.nextDouble();
			double[] student_assignment_scores = new double [num_of_assignments];
			for(int counttt = 0; counttt<num_of_assignments; counttt++) {
				student_assignment_scores[counttt] = scanner.nextDouble();
			}
			midterm_grades[countt] = scanner.nextInt();
			final_grades[countt]  = scanner.nextInt();
			double assignment_grade;
			double assignment_total_points = 0.0;
			double student_assignment_points = 0.0;
			for(int countttt=0; countttt<num_of_assignments; countttt++) {
				student_assignment_points += student_assignment_scores[countttt];
				assignment_total_points += assignment_scores[countttt];
			}
			assignment_grades[countt] = ((student_assignment_points/assignment_total_points)*100);
			double participation_grade = ((participation_points/(participation_total_points*.8))*100);
			if (participation_grade > 100) {
				participation_grade = 100;
			}
			participation_grades[countt] = participation_grade;
		}
		//call a mean function, figure out mean
		//this is figuring out mean for midterm
		double total_midterm_scores = 0.0;
		for(int i=0; i<num_of_students; i++) {
			total_midterm_scores += midterm_grades[i];
		}
		double midterm_mean = total_midterm_scores/num_of_students;
		//calculate the standard deviation for midterm
		double sum_for_std_deviation = 0.0;
		for(int i=0; i<num_of_students; i++) {
			sum_for_std_deviation += ((midterm_grades[i]-midterm_mean)*(midterm_grades[i]-midterm_mean));
		}
		double midterm_std_dev = Math.sqrt(sum_for_std_deviation/num_of_students); 
		//calculate curved scores for midterm
		double [] midterm_curved_score = new double[num_of_students];
		for(int i=0; i<num_of_students; i++) {
			//calculate normalized score
			double low_normal;
			double high_normal;
			double low_curved;
			double high_curved;
			double normalized_score = ((midterm_grades[i]-midterm_mean)/midterm_std_dev);
			if(normalized_score>=2.0) {
				midterm_curved_score[i] = 100.0;
				continue;
			}
			else if(normalized_score>=1.0) {
				low_normal = 1.0;
				high_normal = 2.0;
				low_curved = 94.0;
				high_curved = 100.0;
			}
			else if(normalized_score>=0.0) {
				low_normal = 0.0;
				high_normal = 1.0;
				low_curved = 85.0;
				high_curved = 94.0;
			}
			else if(normalized_score>=-1.0) {
				low_normal = -1.0;
				high_normal = 0.0;
				low_curved = 75.0;
				high_curved = 85.0;
			}
			else if(normalized_score>=-1.5) {
				low_normal = -1.5;
				high_normal = -1.0;
				low_curved = 65.0;
				high_curved = 75.0;
			}
			else if(normalized_score>=-2.0) {
				low_normal = -2.0;
				high_normal = -1.5;
				low_curved = 55.0;
				high_curved = 65.0;
			}
			else if(normalized_score>=-3.0) {
				low_normal = -3.0;
				high_normal = -2.0;
				low_curved = 30.0;
				high_curved = 55.0;
			}
			else if(normalized_score>=-4.0) {
				low_normal = -4.0;
				high_normal = -3.0;
				low_curved = 0.0;
				high_curved = 30.0;
			}
			else{
				midterm_curved_score[i] = 0.0;
				continue;
			}
			midterm_curved_score[i] = (((normalized_score - low_normal)/(high_normal - low_normal))*(high_curved - low_curved)) + low_curved;
		}
		
		
		
		//figuring out mean for final
		double total_final_scores = 0.0;
		for(int j=0; j<num_of_students; j++) {
			total_final_scores += final_grades[j];
		}
		double final_mean = total_final_scores/num_of_students;
		
		//calculate standard deviation for final
		sum_for_std_deviation = 0.0;
		for(int i=0; i<num_of_students; i++) {
			sum_for_std_deviation += ((final_grades[i]-final_mean)*(final_grades[i]-final_mean));
		}
		double final_std_dev = Math.sqrt(sum_for_std_deviation/num_of_students);
		/*
		System.out.println(midterm_std_dev);
		System.out.println(final_std_dev);
		*/
		//calculate curved scores for final
				double [] final_curved_score = new double[num_of_students];
				for(int i=0; i<num_of_students; i++) {
					//calculate normalized score
					double low_normal;
					double high_normal;
					double low_curved;
					double high_curved;
					double normalized_score = ((final_grades[i]-final_mean)/final_std_dev);
					if(normalized_score>=2.0) {
						final_curved_score[i] = 100.0;
						continue;
					}
					else if(normalized_score>=1.0) {
						low_normal = 1.0;
						high_normal = 2.0;
						low_curved = 94.0;
						high_curved = 100.0;
					}
					else if(normalized_score>=0.0) {
						low_normal = 0.0;
						high_normal = 1.0;
						low_curved = 85.0;
						high_curved = 94.0;
					}
					else if(normalized_score>=-1.0) {
						low_normal = -1.0;
						high_normal = 0.0;
						low_curved = 75.0;
						high_curved = 85.0;
					}
					else if(normalized_score>=-1.5) {
						low_normal = -1.5;
						high_normal = -1.0;
						low_curved = 65.0;
						high_curved = 75.0;
					}
					else if(normalized_score>=-2.0) {
						low_normal = -2.0;
						high_normal = -1.5;
						low_curved = 55.0;
						high_curved = 65.0;
					}
					else if(normalized_score>=-3.0) {
						low_normal = -3.0;
						high_normal = -2.0;
						low_curved = 30.0;
						high_curved = 55.0;
					}
					else if(normalized_score>=-4.0) {
						low_normal = -4.0;
						high_normal = -3.0;
						low_curved = 0.0;
						high_curved = 30.0;
					}
					else{
						final_curved_score[i] = 0.0;
						continue;
					}
					final_curved_score[i] = (((normalized_score - low_normal)/(high_normal - low_normal))*(high_curved - low_curved)) + low_curved;
				}
		//declare all the grade values
		int a = 0;
		int a_minus = 0;
		int b_plus = 0;
		int b = 0;
		int b_minus = 0;
		int c_plus = 0;
		int c = 0;
		int c_minus = 0;
		int d_plus = 0;
		int d = 0;
		int f = 0;
		for(int i=0; i<num_of_students; i++) {
			double percentage_grade;
			percentage_grade = (assignment_grades[i]*.4)+(participation_grades[i]*.15)+(midterm_curved_score[i]*.2)+(final_curved_score[i]*.25);
			if(percentage_grade>=94) {
				a++;
			}
			else if(percentage_grade>=90) {
				a_minus++;
			}
			else if(percentage_grade>=86) {
				b_plus++;
			}
			else if(percentage_grade>=83) {
				b++;
			}
			else if(percentage_grade>=80) {
				b_minus++;
			}
			else if(percentage_grade>=76) {
				c_plus++;
			}
			else if(percentage_grade>=73) {
				c++;
			}
			else if(percentage_grade>=70) {
				c_minus++;
			}
			else if(percentage_grade>=65) {
				d_plus++;
			}
			else if(percentage_grade>=60) {
				d++;
			}
			else {
				f++;
			}
		}
		System.out.println("A:  " + a);
		System.out.println("A-: " + a_minus);
		System.out.println("B+: " + b_plus);
		System.out.println("B:  " + b);
		System.out.println("B-: " + b_minus);
		System.out.println("C+: " + c_plus);
		System.out.println("C:  " + c);
		System.out.println("C-: " + c_minus);
		System.out.println("D+: " + d_plus);
		System.out.println("D:  " + d);
		System.out.println("F:  " + f);
	}
}
