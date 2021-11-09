import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimplePainterView extends JPanel {

	private DrawController drawController;

	private JPanel menuPanel, optionPanel, messagePanel;
	private JButton[] btnMenuArray;
	private JTextField txtSize;
	private JButton btnColorChooser;
	private JCheckBox chkFill;
	//
	private JLabel _draw, _color, _size, _coord;
	private Color colorPrint = (Color.black);

	public SimplePainterView() {

		setBackground(Color.white);
		setPreferredSize(new Dimension(820, 830));
		setLayout(null);

		drawController = new DrawController(this);
		drawController.setBounds(10, 10, 800, 600);
		drawController.setBorder(BorderFactory.createTitledBorder("DRAWING")); // Å×µÎ¸®
		add(drawController);

		menuPanel = new JPanel();
		menuPanel.setBounds(10, 610, 300, 200);
		menuPanel.setBackground(Color.white);
		menuPanel.setBorder(BorderFactory.createTitledBorder("MENU"));
		menuPanel.setLayout(new GridLayout(2, 3));
		add(menuPanel);

		optionPanel = new JPanel();
		optionPanel.setBounds(310, 610, 200, 200);
		optionPanel.setBackground(Color.white);
		optionPanel.setBorder(BorderFactory.createTitledBorder("OPTION"));
		optionPanel.setLayout(new GridLayout(3, 1));
		add(optionPanel);

		messagePanel = new JPanel();
		messagePanel.setBounds(510, 610, 300, 200);
		messagePanel.setBackground(Color.white);
		messagePanel.setBorder(BorderFactory.createTitledBorder("MESSAGE"));
		add(messagePanel);

		btnMenuArray = new JButton[6];
		for (int i = 0; i < 6; i++) {
			btnMenuArray[i] = new JButton(Constants.MENU[i]);
			btnMenuArray[i].setBackground(Constants.HOVERING[0]);
			btnMenuArray[i].setForeground(Constants.HOVERING[1]);
			btnMenuArray[i].addMouseListener(new HoveringListener());
			btnMenuArray[i].addActionListener(new MenuListener());
			menuPanel.add(btnMenuArray[i]);
		} // for

		btnColorChooser = new JButton("COLOR CHOOSER");
		btnColorChooser.setBackground(Constants.HOVERING[0]);
		btnColorChooser.setForeground(Constants.HOVERING[1]);
		btnColorChooser.addMouseListener(new HoveringListener());
		btnColorChooser.addActionListener(new MenuListener());
		optionPanel.add(btnColorChooser);

		txtSize = new JTextField();
		txtSize.setFont(new Font("Verdana", Font.BOLD, 16));
		txtSize.setVisible(false);
		optionPanel.add(txtSize);

		chkFill = new JCheckBox("FILL");
		chkFill.setBackground(Color.white);
		chkFill.setFont(new Font("Verdana", Font.BOLD, 16));
		chkFill.setVisible(false);
		optionPanel.add(chkFill);

		_draw = new JLabel("Draw mode :" + "");
		_draw.setBounds(10, 20, 100, 60);
		_draw.setFont(new Font("Verdana", Font.BOLD, 15));
		_draw.setVisible(false);
		messagePanel.add(_draw);

		_color = new JLabel("Selected Color :" + "");
		_color.setBounds(10, 70, 100, 60);
		_color.setFont(new Font("Verdana", Font.BOLD, 15));
		_color.setVisible(false);
		messagePanel.add(_color);

		_size = new JLabel("txt Size :" + "");
		_size.setBounds(10, 120, 100, 60);
		_size.setFont(new Font("Verdana", Font.BOLD, 15));
		_size.setVisible(false);
		messagePanel.add(_size);

		_coord = new JLabel("Coordinate : ");
		_coord.setBounds(10, 170, 100, 60);
		_coord.setFont(new Font("Verdana", Font.BOLD, 15));
		_coord.setVisible(false);
		messagePanel.add(_coord);

	} // Simple PainterView()

	public void setTxtSize(int size) {
		txtSize.setText(Integer.toString(size));
	}

	public int getTxtSize() {
		return Integer.parseInt(txtSize.getText());
	}

	public boolean getChkFill() {
		return chkFill.isSelected();
	}

	//
	public void getText() {
		_size.setText("txt Size : " + Integer.parseInt(txtSize.getText()));
		_coord.setText("Coordinate : " + drawController.getPoint1() + ", " + drawController.getPoint2());
		_size.setVisible(true);
		_coord.setVisible(true);
	}

	private class HoveringListener implements MouseListener {

		@Override
		public void mouseEntered(MouseEvent arg0) {
			JButton btnMenu = (JButton) arg0.getSource();
			btnMenu.setBackground(colorPrint);
			if (colorPrint == Color.white)
				btnMenu.setForeground(Constants.HOVERING[1]);
			else
				btnMenu.setForeground(Constants.HOVERING[0]);
			// btnMenu.setBackground(Constants.HOVERING[2]);
			// btnMenu.setForeground(Constants.HOVERING[3]);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			JButton btnMenu = (JButton) arg0.getSource();
			btnMenu.setBackground(Constants.HOVERING[0]);
			btnMenu.setForeground(Constants.HOVERING[1]);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

	} // HoveringListener Class

	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Object obj = arg0.getSource();

			txtSize.setVisible(true);
			// chkFill.setVisible(false);

			if (obj == btnColorChooser) {
				Color c = JColorChooser.showDialog(btnColorChooser, "COLOR CHOOSER", colorPrint);
				colorPrint = c;
				drawController.setSelectedColor(c);
				_color.setText("Selected Color : " + c.toString().replace("java.awt.Color", ""));
				_color.setVisible(true);

				if (chkFill.isVisible()) {
					chkFill.setVisible(true);
				} else {
					chkFill.setVisible(false);
				}
			}

			for (int i = 0; i < 6; i++) {
				if (obj == btnMenuArray[i]) {
					drawController.setDrawMode(i);
					_draw.setVisible(true);
					if (i == Constants.DOT) {
						_draw.setText("Draw mode : DOT");
						chkFill.setVisible(false);
					} else if (i == Constants.LINE) {
						_draw.setText("Draw mode : LINE");
						chkFill.setVisible(false);
					} else if (i == Constants.RECT) {
						_draw.setText("Draw mode : RECT");
						chkFill.setVisible(true);
					} else if (i == Constants.OVAL) {
						_draw.setText("Draw mode : OVAL");
						chkFill.setVisible(true);
					} else if (i == Constants.UNDO) {
						_draw.setText("Draw mode : UNDO");
						_color.setVisible(false);
						_size.setVisible(false);
						_coord.setVisible(false);
						chkFill.setVisible(false);
						drawController.getUndo();
					} else if (i == Constants.CLEAR) {
						_draw.setText("Draw mode : CLEAR");
						_color.setVisible(false);
						_size.setVisible(false);
						_coord.setVisible(false);
						chkFill.setVisible(false);
						drawController.getClear();
					}
					break;
				}
			}

		} // actionPerformed()

	}// MenuListener class

}//
