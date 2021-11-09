import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandomNumber {

	public static void main(String[] args) {

		JFrame frame = new JFrame("MIDTERM EXAMINATION");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel primaryPanel = new JPanel();
		primaryPanel.setBackground(Color.white);
		primaryPanel.setPreferredSize(new Dimension(440, 440));
		primaryPanel.setLayout(null);

		Random generator = new Random();

		int r, g, b;
		r = generator.nextInt(256);
		g = generator.nextInt(256);
		b = generator.nextInt(256);

		JPanel colorPanel = new JPanel();
		colorPanel.setBackground(new Color(r, g, b));
		colorPanel.setBounds(10, 10, 420, 420);
		colorPanel.setLayout(null);
		primaryPanel.add(colorPanel);

		int num1 = generator.nextInt(6) + 1;
		int num2 = generator.nextInt(6) + 1;

		JLabel n1, n2;
		n1 = new JLabel(Integer.toString(num1));
		n2 = new JLabel(Integer.toString(num2));

		if (num1 > num2) {
			n1.setFont(new Font("Verdana", Font.BOLD, 100));
			n2.setFont(new Font("Verdana", Font.BOLD, 40));

		} else if (num1 < num2) {
			n1.setFont(new Font("Verdana", Font.BOLD, 40));
			n2.setFont(new Font("Verdana", Font.BOLD, 100));
		} else {
			n1.setFont(new Font("Verdana", Font.BOLD, 70));
			n2.setFont(new Font("Verdana", Font.BOLD, 70));
		}

		int r1, g1, b1, w1, h1;
		r1 = generator.nextInt(256);
		g1 = generator.nextInt(256);
		b1 = generator.nextInt(256);
		w1 = generator.nextInt(420 - 100);
		h1 = generator.nextInt(420 - 100);

		int r2, g2, b2, w2, h2;
		r2 = generator.nextInt(256);
		g2 = generator.nextInt(256);
		b2 = generator.nextInt(256);
		w2 = generator.nextInt(420 - 100);
		h2 = generator.nextInt(420 - 100);

		n1.setForeground(new Color(r1, g1, b1));
		n1.setBounds(w1, h1, 100, 100);
		colorPanel.add(n1);

		n2.setForeground(new Color(r2, g2, b2));
		n2.setBounds(w2, h2, 100, 100);
		colorPanel.add(n2);

		frame.getContentPane().add(primaryPanel);
		frame.pack();
		frame.setVisible(true);
	} // main

} // RandomNumber
