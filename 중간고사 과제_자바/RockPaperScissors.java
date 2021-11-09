import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RockPaperScissors {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Rock-Paper-Scissors");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel primaryPanel = new JPanel();
		primaryPanel.setBackground(Color.white);
		primaryPanel.setPreferredSize(new Dimension(315, 190));
		primaryPanel.setLayout(null);

		Font fnt = new Font("Century", Font.BOLD, 15);

		JLabel User1, User2, Vs, Pic1, Pic2, Result;

		User1 = new JLabel("USER1");
		User1.setFont(fnt);
		User1.setBounds(50, -40, 100, 100);
		primaryPanel.add(User1);

		User2 = new JLabel("USER2");
		User2.setFont(fnt);
		User2.setBounds(210, -40, 100, 100);
		primaryPanel.add(User2);

		Vs = new JLabel("VS");
		Vs.setFont(fnt);
		Vs.setBounds(145, 90, 50, 50);
		primaryPanel.add(Vs);

		ImageIcon[] images = { // ImageIcon 클래스 객체 images를 배열로 선언
				new ImageIcon("images/rock.png"), // [0]
				new ImageIcon("images/paper.png"), // [1]
				new ImageIcon("images/scissor.png"), // [2]
		};

		Random generator = new Random();
		int ran1 = generator.nextInt(3);
		int ran2 = generator.nextInt(3);

		Pic1 = new JLabel(images[ran1], SwingConstants.CENTER);
		Pic1.setBounds(10, 30, 120, 120);
		primaryPanel.add(Pic1);

		Pic2 = new JLabel(images[ran2], SwingConstants.CENTER);
		Pic2.setBounds(180, 30, 120, 120);
		primaryPanel.add(Pic2);

		// 바위 : 0, 보 : 1, 가위 : 2
		String win = new String();
		if (ran1 == ran2)
			win = "End in a tie!!";
		else if (ran1 == 0 && ran2 == 2)
			win = "USER1 WIN!!";
		else if (ran1 == 2 && ran2 == 0)
			win = "USER2 WIN!!";
		else if (ran1 > ran2)
			win = "USER1 WIN!!";
		else
			win = "USER2 WIN!!";

		Result = new JLabel(win);
		Result.setFont(fnt);
		Result.setBounds(110, 120, 300, 100);
		primaryPanel.add(Result);

		frame.getContentPane().add(primaryPanel);
		frame.pack();
		frame.setVisible(true);
	} // main

} // RockPaperScissors
