import javax.swing.JFrame;

public class AssignUpDown {

	public static void main(String[] args) {

		JFrame frame = new JFrame("UP DOWN GAME");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // 사이즈 변경 안됌

		AssignUpDownPanel primary = new AssignUpDownPanel();
		frame.getContentPane().add(primary);

		frame.pack();
		frame.setVisible(true);

	}// main

} // AssignUpDown