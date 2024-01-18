
public class Main {
	public static void main(String[] args ) {
//		CalculateGPA calculate = new CalculateGPA();
//		System.out.println(calculate.getNum("C1"));
//		System.out.println(calculate.getLetter(14));

		GUI UI = new GUI();
		javax.swing.SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				UI.setAndShowGUI();
			}
		});
		
	}

}
