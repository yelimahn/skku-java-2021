import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawController extends JPanel {

	private SimplePainterModel nowData;
	private ArrayList<SimplePainterModel> savedList;
	private DrawListener drawL;
	private SimplePainterView view;
	private boolean bDrag;
	//
	private int coo1, coo2;

	public DrawController(SimplePainterView v) {

		view = v;

		setBackground(Color.white);

		drawL = new DrawListener();
		addMouseListener(drawL);
		addMouseMotionListener(drawL);

		nowData = new SimplePainterModel();
		savedList = new ArrayList<SimplePainterModel>();

		nowData.nDrawMode = Constants.NONE;
		bDrag = false;

	} // DrawController()

	public void setDrawMode(int mode) {
		nowData.nDrawMode = mode;
		if (nowData.nDrawMode == Constants.DOT)
			view.setTxtSize(10);
		else
			view.setTxtSize(1);
	}

	public void setSelectedColor(Color color) {
		nowData.selectedColor = color;
	}

	public void getUndo() {
		if (savedList.isEmpty())
			repaint();
		else {
			savedList.remove(savedList.size() - 1);
			repaint();
		}
	}

	public void getClear() {
		if (savedList.isEmpty())
			repaint();
		else {
			savedList.clear();
			repaint();
		}
	}

	public int getPoint1() {
		return coo1;
	}

	public int getPoint2() {
		return coo2;
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		if (bDrag) {
			Graphics2D page2 = (Graphics2D) page;
			switch (nowData.nDrawMode) {
			case Constants.LINE:
				page.setColor(nowData.selectedColor);
				page2.setStroke(new BasicStroke(nowData.nSize));
				page.drawLine(nowData.ptOne.x, nowData.ptOne.y, nowData.ptTwo.x, nowData.ptTwo.y);
				coo1 = nowData.ptOne.x;
				coo2 = nowData.ptOne.y;
				break;
			case Constants.RECT:
				page.setColor(nowData.selectedColor);
				page2.setStroke(new BasicStroke(nowData.nSize));
				draw4Rect(page, nowData.ptOne, nowData.ptTwo, nowData.bFill);
				break;
			case Constants.OVAL:
				page.setColor(nowData.selectedColor);
				page2.setStroke(new BasicStroke(nowData.nSize));
				draw4Oval(page, nowData.ptOne, nowData.ptTwo, nowData.bFill);
				break;
			} // switch
		} // if

		for (SimplePainterModel data : savedList) {
			Graphics2D page2 = (Graphics2D) page;
			switch (data.nDrawMode) {
			case Constants.DOT:
				page.setColor(data.selectedColor);
				page.fillOval(data.ptOne.x - data.nSize / 2, data.ptOne.y - data.nSize / 2, data.nSize, data.nSize);
				coo1 = data.ptOne.x - data.nSize / 2;
				coo2 = data.ptOne.y - data.nSize / 2;
				break;
			case Constants.LINE:
				page.setColor(data.selectedColor);
				// Graphics2D page2 = (Graphics2D) page;
				page2.setStroke(new BasicStroke(data.nSize));
				page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
				coo1 = data.ptOne.x;
				coo2 = data.ptOne.y;
				break;
			case Constants.RECT:
				page.setColor(data.selectedColor);
				page2.setStroke(new BasicStroke(data.nSize));
				draw4Rect(page, data.ptOne, data.ptTwo, data.bFill);
				break;
			case Constants.OVAL:
				page.setColor(data.selectedColor);
				page2.setStroke(new BasicStroke(data.nSize));
				draw4Oval(page, data.ptOne, data.ptTwo, data.bFill);
				break;
			}
		}
	} // paintComponent()

	private void draw4Rect(Graphics page, Point pt1, Point pt2, boolean fill) {
		int x, y, width, height;
		x = y = width = height = 0;
		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			x = pt1.x;
			y = pt1.y;
			width = pt2.x - pt1.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			x = pt1.x;
			y = pt2.y;
			width = pt2.x - pt1.x;
			height = pt1.y - pt2.y;
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			x = pt2.x;
			y = pt1.y;
			width = pt1.x - pt2.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			x = pt2.x;
			y = pt2.y;
			width = pt1.x - pt2.x;
			height = pt1.y - pt2.y;
		} // if..else if

		coo1 = x;
		coo2 = y;
		if (fill)
			page.fillRect(x, y, width, height);
		else
			page.drawRect(x, y, width, height);
	}

	private void draw4Oval(Graphics page, Point pt1, Point pt2, boolean fill) {
		int x, y, width, height;
		x = y = width = height = 0;
		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			x = pt1.x;
			y = pt1.y;
			width = pt2.x - pt1.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			x = pt1.x;
			y = pt2.y;
			width = pt2.x - pt1.x;
			height = pt1.y - pt2.y;
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			x = pt2.x;
			y = pt1.y;
			width = pt1.x - pt2.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			x = pt2.x;
			y = pt2.y;
			width = pt1.x - pt2.x;
			height = pt1.y - pt2.y;
		} // if..else if

		coo1 = x;
		coo2 = y;
		if (fill)
			page.fillOval(x, y, width, height);
		else
			page.drawOval(x, y, width, height);
	}

	private class DrawListener implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (nowData.nDrawMode == Constants.DOT) {
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTxtSize();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
				view.getText();//
			} // if
		} // mouseClicked()

		@Override
		public void mousePressed(MouseEvent e) {
			if (nowData.nDrawMode == Constants.LINE) {
				bDrag = true;
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTxtSize();
				view.getText(); //
			} else if (nowData.nDrawMode == Constants.RECT) {
				bDrag = true;
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTxtSize();
				nowData.bFill = view.getChkFill();
				view.getText(); //
			} else if (nowData.nDrawMode == Constants.OVAL) {
				bDrag = true;
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTxtSize();
				nowData.bFill = view.getChkFill();
				view.getText(); //
			}
		} // mousePressed()

		@Override
		public void mouseReleased(MouseEvent e) {
			if (nowData.nDrawMode == Constants.LINE) {
				bDrag = false;
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
				view.getText();//
			} else if (nowData.nDrawMode == Constants.RECT) {
				bDrag = false;
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
				view.getText();//
			} else if (nowData.nDrawMode == Constants.OVAL) {
				bDrag = false;
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
				view.getText();//
			}
		} // mouseReleased()

		@Override
		public void mouseDragged(MouseEvent arg0) {
			if (nowData.nDrawMode == Constants.LINE) {
				nowData.ptTwo = arg0.getPoint();
				repaint();
			} else if (nowData.nDrawMode == Constants.RECT) {
				nowData.ptTwo = arg0.getPoint();
				repaint();
			} else if (nowData.nDrawMode == Constants.OVAL) {
				nowData.ptTwo = arg0.getPoint();
				repaint();
			}
		} // mouseDragged()

		@Override
		public void mouseMoved(MouseEvent arg0) {
			if (nowData.nDrawMode == Constants.RECT) {
				nowData.ptTwo = arg0.getPoint();
				repaint();
			} else if (nowData.nDrawMode == Constants.OVAL) {
				nowData.ptTwo = arg0.getPoint();
				repaint();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}
} // DrawController class
