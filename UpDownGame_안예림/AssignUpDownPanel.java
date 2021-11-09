import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AssignUpDownPanel extends JPanel {

	private JPanel leftPanel, rightPanel;
	private JLabel lblTitle, lblMark, lblHint;
	private JButton btnRandom, btnInput;
	private JTextField txtInput;
	private int _random, _input;
	// 2. declaration of listener object
	private GameListener gamel;
	// showing range
	private JLabel _range, _count;
	private int _start, _end, _cnt;

	public AssignUpDownPanel() {

		setBackground(Color.white);
		setPreferredSize(new Dimension(630, 420));
		setLayout(null);

		// 2. creating of listener object
		gamel = new GameListener();

		leftPanel = new JPanel();
		leftPanel.setBounds(10, 10, 300, 400);
		leftPanel.setBackground(Color.cyan);
		leftPanel.setLayout(null);
		add(leftPanel);

		rightPanel = new JPanel();
		rightPanel.setBounds(320, 10, 300, 400);
		rightPanel.setBackground(Color.pink);
		rightPanel.setLayout(null);
		add(rightPanel);

		lblTitle = new JLabel("UP DOWN GAME");
		lblTitle.setBounds(10, 40, 280, 60);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 26));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		leftPanel.add(lblTitle);

		lblMark = new JLabel("?");
		lblMark.setBounds(10, 110, 280, 180);
		lblMark.setFont(new Font("Verdana", Font.BOLD, 80));
		lblMark.setHorizontalAlignment(SwingConstants.CENTER);
		lblMark.setVisible(false);
		leftPanel.add(lblMark);

		lblHint = new JLabel("RIGHT");
		lblHint.setBounds(10, 300, 280, 60);
		lblHint.setFont(new Font("Verdana", Font.BOLD, 20));
		lblHint.setHorizontalAlignment(SwingConstants.CENTER);
		lblHint.setVisible(false);
		leftPanel.add(lblHint);

		Font fnt = new Font("Verdana", Font.BOLD, 14);

		btnRandom = new JButton("Random Number");
		btnRandom.setBounds(40, 60, 220, 40);
		btnRandom.setFont(fnt);
		// 3. add the listener object to the component
		btnRandom.addActionListener(gamel);
		rightPanel.add(btnRandom);

		txtInput = new JTextField();
		txtInput.setBounds(40, 110, 115, 40);
		txtInput.setFont(fnt);
		txtInput.setEnabled(false);
		txtInput.addActionListener(gamel);
		rightPanel.add(txtInput);

		btnInput = new JButton("INPUT");
		btnInput.setBounds(160, 110, 100, 40);
		btnInput.setFont(fnt);
		btnInput.setEnabled(false);
		btnInput.addActionListener(gamel);
		rightPanel.add(btnInput);

		_random = _input = 0; // 초기화
		// (1) setting range
		_start = 1;
		_end = 100;
		// (2) setting count
		_cnt = 0;

		// (1) _range
		_range = new JLabel("range : " + _start + " ~ " + _end);
		_range.setBounds(50, 160, 200, 60);
		_range.setFont(new Font("Verdana", Font.BOLD, 20));
		_range.setHorizontalAlignment(SwingConstants.CENTER);
		_range.setVisible(false);
		rightPanel.add(_range);

		// (2) _count
		_count = new JLabel("count : " + _cnt);
		_count.setBounds(100, 310, 100, 60);
		_count.setFont(new Font("Verdana", Font.BOLD, 20));
		_count.setHorizontalAlignment(SwingConstants.CENTER);
		_count.setVisible(true);
		rightPanel.add(_count);

	} // constructor

	// 1. listener class
	private class GameListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// 4. event handling
			Object obj = event.getSource();

			if (obj == btnRandom) {
				_random = (int) (Math.random() * 100) + 1; // 1~100
				lblMark.setText("?");
				lblMark.setVisible(true);

				txtInput.setEnabled(true);
				btnInput.setEnabled(true);
				btnRandom.setEnabled(false);

				// (1) showing range print
				_range.setVisible(true);

				System.out.println("random >>" + _random);
			} else if (obj == txtInput || obj == btnInput) {
				_input = Integer.parseInt(txtInput.getText());
				txtInput.setText("");

				// (2) showing count print
				_cnt = _cnt + 1;
				_count.setText("count : " + _cnt);

				if (_random < _input) {
					lblHint.setText("DOWN");
					lblHint.setVisible(true);
					_end = _input - 1;
					_range.setText("range : " + _start + " ~ " + _end);
				} else if (_random > _input) {
					lblHint.setText("UP");
					lblHint.setVisible(true);
					_start = _input + 1;
					_range.setText("range : " + _start + " ~ " + _end);
				} else {
					lblHint.setText("RIGHT");
					lblMark.setText(Integer.toString(_input));

					int result = JOptionPane.showConfirmDialog(rightPanel, "Continue?");
					if (result == JOptionPane.YES_OPTION) {
						System.out.println("Yes");
						// (3) 초기화
						_range.setVisible(false);
						lblMark.setVisible(false);
						lblHint.setVisible(false);
						txtInput.setEnabled(false);
						btnInput.setEnabled(false);
						btnRandom.setEnabled(true);
						_start = 1;
						_end = 100;
						_cnt = 0;
						_range.setText("range : " + _start + " ~ " + _end);
						_count.setText("count : " + _cnt);

					} else if (result == JOptionPane.NO_OPTION) {
						System.exit(0);
					} else { // result == CANCEL_OPTION
						System.out.println("CANCEL");
					}
				}
			} // if .. else if

		} // actionPerformed
	} //
}
