
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI implements ActionListener{
	int y = 0;										// tracks length of panel
	JFrame frame = new JFrame();
	Container pane = frame.getContentPane();		// returns instance of JPanel
	JButton removeRow, addRow, calculate;			// to add assessment row and calculate GPA
	JTextField totalCredits;					
	JLabel GPALetter, GPANum;						// to display GPA 
	ArrayList<JTextField> credits = new ArrayList<JTextField>();	// stores number of credits for assessments
	ArrayList<JTextField> grades = new ArrayList<JTextField>();		// stores grades acquired from assessments
	ArrayList<JTextField> assessment = new ArrayList<JTextField>(); // stores assessment text 
	
	public void initilaisePane(Container pane) {
		addCourseLabels(pane);						// label for course & total credits
		addCourseTextFields(pane);					// text for course & total credit
		addAssessmentLabels(pane);					// label for assessment, credits & grade
		addTextFieldRow(pane);						// text for assessment, credit & grade
		addRemoveTextFieldRowButton(pane);				// button to remove assessment text row
		addTextFieldRowButton(pane);				// button to add new assessment text row
		addCalculateButton(pane);					// button to calculate GPA
		addGPALabels(pane);							// GPA labels 
	}
	
	public void addCourseLabels(Container pane) {
		JLabel label;
		
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				label = new JLabel("Course:");
			} else {
				label = new JLabel("Total credits (if using credits) :");
			}
			
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 3;
			c.gridx = 0;
			c.gridy = y;
			c.insets = new Insets(5,10,5,10);
			c.fill = GridBagConstraints.BOTH;
		
			label.setHorizontalAlignment(SwingConstants.CENTER);
			pane.add(label, c);
			y ++;
		}
	}
	
	public void addCourseTextFields(Container pane) {
		JTextField field;
		
		for (int i = 0; i < 2; i++) {
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 3;
			c.gridx = 1;
			c.gridy = i;
			c.insets = new Insets(5,10,5,10);
			c.fill = GridBagConstraints.BOTH;
			
			if (i == 0) {
				field = new JTextField();
				field.setHorizontalAlignment(SwingConstants.CENTER);
				pane.add(field, c);
				
			} else {
				totalCredits = new JTextField();
				totalCredits.setHorizontalAlignment(SwingConstants.CENTER);
				totalCredits.addActionListener(this);							// enables access to total credits for course
				pane.add(totalCredits, c);
			}
			y ++;
		}
	}
	
	public void addAssessmentLabels(Container pane) {
		JLabel label;
	
		for (int i = 0; i < 3; i++) {  
			if (i == 0) {
				label = new JLabel("Assessment");
			} else if (i == 1) {
				label = new JLabel("Credits / Percentage");
			} else {
				label = new JLabel("Grade");
			}
			
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 3;
			c.gridx = i;
			c.gridy = y;
			c.insets = new Insets(100,10,5,10);
			c.fill = GridBagConstraints.BOTH;
		
			label.setHorizontalAlignment(SwingConstants.CENTER);
			pane.add(label, c);
		}
		y ++;
	}
	
	public void addTextFieldRow(Container pane) { 
		JTextField field;
		
		for (int i = 0; i < 3; i++) {
			field = new JTextField();
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 2;
			c.gridx = i;
			c.gridy = y;
			c.insets = new Insets(5,10,5,10);
			c.fill = GridBagConstraints.BOTH;
			field.setHorizontalAlignment(SwingConstants.CENTER);
			
			if (i % 2 == 1) {
				credits.add(field);
			} else if (i % 2 == 0 && i != 0) {
				grades.add(field);
			} else if (i == 0) {
				assessment.add(field);
			}
			
			pane.add(field, c);
		}
		y ++;
	}
	
	public void addTextFieldRowButton(Container pane) { 
		if (addRow != null) {
			addRow.setVisible(false);			// if addRow button exists, hides that instance of button
			removeRow.setVisible(false);        // if removeRow button exists, hides that instance of button
			addRemoveTextFieldRowButton(pane);     // adds another instance of the remove text field row button
		}
		
		addRow  = new JButton("+");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = y;
		c.insets = new Insets(5,10,5,10);
		addRow.setHorizontalAlignment(SwingConstants.CENTER);
		pane.add(addRow , c);
		
		addRow.addActionListener(this);
		y ++;
		
		if (calculate != null) {
			calculate.setVisible(false);		// if calculate button exists, hides that instance of button 
			GPANum.setVisible(false);
			GPALetter.setVisible(false);
			addCalculateButton(pane);			// creates new instance of calculate button
			addGPALabels(pane);
		}
	}
	
	public void addRemoveTextFieldRowButton(Container pane) { // test 
		
		removeRow  = new JButton("-");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = y;
		c.insets = new Insets(5,10,5,10);
		removeRow.setHorizontalAlignment(SwingConstants.CENTER);
		pane.add(removeRow , c);
		
		removeRow.addActionListener(this);
		// does not increment y so it is level with add row button
	}
	
	private JTextField getLastAssessmentField() {
		return assessment.get(assessment.size()- 1); // returns the last assessment text field
	}
	
	private JTextField getLastCreditsField() {
		return credits.get(credits.size()- 1); // returns the last assessment text field
	}
	
	private JTextField getLastGradesField() {
		return grades.get(grades.size()- 1); // returns the last assessment text field
	}
	
	public void addCalculateButton(Container pane) {
		calculate = new JButton("Calculate GPA:");
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 3;
		c.gridx = 0;
		c.gridy = y;
		c.insets = new Insets(5,10,5,10);
		c.fill = GridBagConstraints.BOTH;
		calculate.setHorizontalAlignment(SwingConstants.CENTER);
		calculate.addActionListener(this);
		pane.add(calculate, c);
		
		y ++;
	}
	
	public void addGPALabels(Container pane) {
		for (int i = 0; i < 2; i++) {
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 3;
			c.insets = new Insets(5,10,5,10);
			c.fill = GridBagConstraints.BOTH;
			if (i == 0) {
				c.gridx = 1;
				c.gridy = y - 1;
				GPANum = new JLabel("GPA number");
				GPANum.setHorizontalAlignment(SwingConstants.CENTER);
				pane.add(GPANum,c);
			} else {
				c.gridx = 2;
				c.gridy = y - 2;
				GPALetter = new JLabel("GPA letter");
				GPALetter.setHorizontalAlignment(SwingConstants.CENTER);
				pane.add(GPALetter,c);
			}
			
			y ++;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addRow) {
			addTextFieldRow(pane);
			addTextFieldRowButton(pane);
		} else if (e.getSource()== calculate) {
			if (creditDataInput()) {
				CalculateGPA calculateGPA = new CalculateGPA();
				int GPAINNum = calculateGPA.calculateUsingCredits(credits, grades, totalCredits.getText()); // using credits
				String GPAInString = calculateGPA.getLetter(GPAINNum);
				GPANum.setText(Integer.toString(GPAINNum));
				GPALetter.setText(GPAInString);
			} else if (perctentageDataInput()) {
				CalculateGPA calculateGPA = new CalculateGPA();
				int GPAINNUm = calculateGPA.calculateUsingPercent(credits, grades);
				String GPAInString = calculateGPA.getLetter(GPAINNUm);
				GPANum.setText(Integer.toString(GPAINNUm));
				GPALetter.setText(GPAInString);
			}
		} else if (e.getSource() == removeRow) {
			JTextField assessmentText = getLastAssessmentField();
			JTextField creditsText = getLastCreditsField();
			JTextField gradesText = getLastGradesField();
			assessmentText.setVisible(false);
			creditsText.setVisible(false);
			gradesText.setVisible(false);
			assessment.remove(getLastAssessmentField());
			credits.remove(getLastCreditsField());
			grades.remove(getLastGradesField());
			y --; 
			
			removeRow.setVisible(false);
			addRow.setVisible(false);
			calculate.setVisible(false);
			GPALetter.setVisible(false);
			GPANum.setVisible(false);
			
			addRemoveTextFieldRowButton(pane);				// button to remove assessment text row
			addTextFieldRowButton(pane);				    // button to add new assessment text row
		}
	}
	
	private boolean creditDataInput() {
		boolean hasCorrectData = true;
		if (totalCredits.getText().isEmpty()) {
			return false;
		}
		
		double numberOfCredits = 0;
		for (int i = 0; i < credits.size(); i ++) {
			if (credits.get(i).getText().length() == 0  || grades.get(i).getText().length() == 0 || totalCredits.getText().length() == 0) {
				hasCorrectData = false;
			}
			numberOfCredits += Double.parseDouble(credits.get(i).getText());
		}
		
		if (numberOfCredits != Double.parseDouble(totalCredits.getText())) {
			hasCorrectData = false;
		}
		
		creditDataError(hasCorrectData);
		
		return hasCorrectData;
	}
	
	private void creditDataError(boolean inputData) {
		if (inputData == false) {
			GPANum.setText("Incorrect data input");
			GPALetter.setText("for credit calculation");
		}
		
	}
	
	private boolean perctentageDataInput() {
		boolean hasCorrectData = true;
		double percentage = 0;
		for (int i = 0; i < credits.size(); i ++) {
			percentage += Double.parseDouble(credits.get(i).getText());
		}
		
		if (percentage != 100) {
			hasCorrectData = false;
		}
		
		percentageDataError(hasCorrectData);
		
		return hasCorrectData;
	}
	
	private void percentageDataError(boolean inputData) {
		if (inputData == false) {
			GPANum.setText("Incorrect data input");
			GPALetter.setText("for percentage calculation");
		}
	}
	
	protected void setAndShowGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setLayout(new GridBagLayout());
		initilaisePane(pane);
		
		frame.pack();
		frame.setSize(500, 700);
		frame.setVisible(true);
	}
}