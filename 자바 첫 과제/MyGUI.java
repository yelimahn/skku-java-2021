import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyGUI {

	public static void main(String[] args) {
		// heavyweight container
		JFrame frame = new JFrame("My GUI Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// lightweight container
		JPanel primary = new JPanel();
		primary.setPreferredSize(new Dimension(400, 300));
		primary.setBackground(Color.white);
		primary.setLayout(null); // layout manager disable

		// component
		JLabel lbl1, lbl2, lbl3, lbl4;
		Font fnt = new Font("Verdana", Font.BOLD, 20);

		lbl1 = new JLabel("SKKU University");
		lbl1.setFont(fnt);
		lbl1.setForeground(Color.red);
		lbl1.setBounds(100, 50, 200, 50);
		primary.add(lbl1);

		lbl2 = new JLabel("Applied Artificial Intelligence");
		lbl2.setFont(fnt);
		lbl2.setForeground(Color.green);
		lbl2.setBounds(30, 100, 400, 50);
		primary.add(lbl2);

		lbl3 = new JLabel("2019313464");
		lbl3.setFont(fnt);
		lbl3.setForeground(Color.blue);
		lbl3.setBounds(120, 150, 200, 50);
		primary.add(lbl3);

		lbl4 = new JLabel("Yelim Ahn");
		lbl4.setFont(fnt);
		lbl4.setForeground(Color.pink);
		lbl4.setBounds(130, 200, 200, 50);
		primary.add(lbl4);

		frame.getContentPane().add(primary);
		frame.pack();
		frame.setVisible(true);

	} // main

}// MyGUI class
