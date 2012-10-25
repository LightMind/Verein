package plan.ui.lesson;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import plan.Lesson;
import util.Observeable;
import util.Observer;

public class LessonPanel extends JPanel implements Comparable<LessonPanel>,
		Observer<Lesson>, Observeable<LessonPanel> {
	private static final long serialVersionUID = -8809605582019351857L;
	private final Lesson l;
	private Observer<LessonPanel> myObserver = null;
	public float pixelPerMinute = 1;
	public float pixelPerDay = 1;
	private final SimpleDateFormat dayFormatter = new SimpleDateFormat("EEE dd");
	public boolean allowWeekDraggin = false;
	public boolean allowOutOfBoundsDragging = false;
	public boolean isControlPressed = false;

	public int dragBorder = 9;

	public LessonPanel(final Lesson l) {
		super();
		this.l = l;

		Border b = new BevelBorder(BevelBorder.RAISED);

		setBorder(b);
		final JLabel start = new JLabel("" + l.beginn.get(Calendar.HOUR_OF_DAY));
		JLabel id = new JLabel("" + l.id);
		final JLabel end = new JLabel("" + l.beginn.get(Calendar.DAY_OF_WEEK));
		start.setText("" + l.beginn.get(Calendar.HOUR_OF_DAY));
		end.setText(getDayFormatter().format(l.beginn.getTime()));

		add(start);
		add(id);
		add(end);
		LessonPanelMouse lpm = new LessonPanelMouse(this, l, start, end);
		addMouseListener(lpm);
		addMouseMotionListener(lpm);
	}

	public void setPPM(float pixelPerMinute) {
		this.pixelPerMinute = pixelPerMinute;
	}

	public void setPPD(float pixelPerDay) {
		this.pixelPerDay = pixelPerDay;
	}

	public Calendar getBeginn() {
		return l.getBeginn();
	}

	public Calendar getEnd() {
		return l.getEnd();
	}

	@Override
	public int compareTo(LessonPanel o) {
		return l.compareTo(o.l);
	}

	@Override
	public void notifyAfterChange(Lesson source) {
		myObserver.notifyAfterChange(this);
	}

	@Override
	public void notifyBeforeChange(Lesson source) {
		myObserver.notifyBeforeChange(this);
	}

	@Override
	public void addObserver(Observer<LessonPanel> obs) {
		myObserver = obs;

	}

	@Override
	public void removeObserver(Observer<LessonPanel> obs) {
		if (myObserver == obs) {
			myObserver = null;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((l == null) ? 0 : l.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return false;
	}

	public SimpleDateFormat getDayFormatter() {
		return dayFormatter;

	}

	private boolean dUp = false, dDown = false, dMiddle = false;

	public void setMouseOver(boolean b) {
		dMiddle = b;
	}

	public void setDrawDragBorder(boolean up, boolean down) {
		dUp = up;
		dDown = down;
	}

	private Color alphaGray = new Color(0.3f, 0.5f, 0.3f, 0.3f);

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(alphaGray);

		if (dUp) {
			g.fillRect(0, 0, getWidth(), dragBorder);
		}
		if (dDown) {
			g.fillRect(0, getHeight() - dragBorder, getWidth(), dragBorder);
		}
		if (dMiddle) {
			g.fillRect(0, dragBorder, getWidth(), getHeight() - 2 * dragBorder);
		}
	}
}
