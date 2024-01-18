import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextField;

public class CalculateGPA {
	private HashMap<String, Integer> grades = new HashMap<String, Integer>();
	
	CalculateGPA() { // constructor
		populateGrades();
	}
	
	private void populateGrades() {
		grades.put("H", 0);
		grades.put("G2", 1);
		grades.put("G1", 2);
		grades.put("F3", 3);
		grades.put("F2", 4);
		grades.put("F1", 5);
		grades.put("E3", 6);
		grades.put("E2", 7);
		grades.put("E1", 8);
		grades.put("D3", 9);
		grades.put("D2", 10);
		grades.put("D1", 11);
		grades.put("C3", 12);
		grades.put("C2", 13);
		grades.put("C1", 14);
		grades.put("B3", 15);
		grades.put("B2", 16);
		grades.put("B1", 17);
		grades.put("A5", 18);
		grades.put("A4", 19);
		grades.put("A3", 20);
		grades.put("A2", 21);
		grades.put("A1", 22);
	}
	
	public int calculateUsingCredits(ArrayList<JTextField> assessmentCredits, ArrayList<JTextField> grades, String totalCredits) {
		int maxCredits = Integer.parseInt(totalCredits);
		float gpa = 0;
		
		for (int i = 0; i < assessmentCredits.size(); i ++) {
			float assessmentCredit = Float.parseFloat(assessmentCredits.get(i).getText());
			float percentageOfCourse = ((float)100 / maxCredits) * assessmentCredit / 100;		    // percentage contribution of course
			int gradeAchieved = getNum(grades.get(i).getText());									// grade achieved
			gpa += percentageOfCourse * gradeAchieved;
		}
		int gpaInt = Math.round(gpa);																// rounds to nearest int

		return gpaInt;
	}
	
	public int calculateUsingPercent(ArrayList<JTextField> assessmentPercent, ArrayList<JTextField> grades) { // test
		float gpa = 0;
		
		for (int i = 0; i < assessmentPercent.size(); i ++) {
			float assessmentPercentage = Float.parseFloat(assessmentPercent.get(i).getText()) / 100;
			int gradeAchieved = getNum(grades.get(i).getText());									// grade achieved
			gpa += assessmentPercentage * gradeAchieved;
		}
		int gpaInt = Math.round(gpa);																// rounds to nearest int

		return gpaInt;
	}
	
	public int getNum(String letter) {
		String capitalizedLetter = letter.toUpperCase();
		if (grades.get(capitalizedLetter) != null) {
			return grades.get(capitalizedLetter);
		}
		return -1;
	}
	
	public String getLetter(int num) {
		String letter = "N/A to retrieve";
		for (String key: grades.keySet()) {
			if (grades.get(key) == num) {
				letter = key;
			}
		}
		return letter;
	}
}
	

