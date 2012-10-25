package plan.ui.lesson;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import plan.Lesson;

public class LessonPanelMouse implements MouseListener, MouseMotionListener {

	private boolean draggedNormally = false;
	private boolean dragUp = false;
	private boolean dragDown = false;
	private int mouseY = 0;
	private int mouseX = 0;
	private final Lesson l;
	private LessonPanel panel;
	private JLabel start;
	private JLabel end;

	public int snapTo = 30;

	public LessonPanelMouse(LessonPanel panel, Lesson lesson, JLabel start,
			JLabel end) {
		this.panel = panel;
		this.l = lesson;
		this.start = start;
		this.end = end;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (!panel.allowOutOfBoundsDragging) {
			if (arg0.getPoint().x + panel.getBounds().getLocation().x > panel
					.getParent().getBounds().width) {
				return;
			}
			if (arg0.getPoint().x + panel.getBounds().getLocation().x < 0) {
				return;
			}
			if (arg0.getPoint().y + panel.getBounds().getLocation().y > panel
					.getParent().getBounds().height) {
				return;
			}
			if (arg0.getPoint().y + panel.getBounds().getLocation().y < 0) {
				return;
			}

		}

		if (draggedNormally) {
			draggedNormally = false;
			int difference = arg0.getPoint().y - mouseY;
			int differenceDay = arg0.getPoint().x - mouseX;

			int currentDuration = l.getDuration();

			int minutes = (int) (difference / panel.pixelPerMinute);
			Calendar lstart = l.getBeginn();
			Calendar lend = l.getEnd();
			lstart.add(Calendar.MINUTE, minutes);
			if (lstart.get(Calendar.DAY_OF_YEAR) != l.getBeginn().get(
					Calendar.DAY_OF_YEAR)) {
				lstart = l.getBeginn();
				lstart.set(Calendar.HOUR_OF_DAY, 0);
				lstart.set(Calendar.MINUTE, 0);
			}
			// lstart.set(Calendar.DAY_OF_YEAR, l.getBeginn().get(
			// Calendar.DAY_OF_YEAR));

			int days = (int) (differenceDay / panel.pixelPerDay);
			lstart.add(Calendar.DAY_OF_MONTH, days);

			if (!panel.allowWeekDraggin) {
				lstart.set(Calendar.WEEK_OF_YEAR, l.getBeginn().get(
						Calendar.WEEK_OF_YEAR));
			}

			l.setBeginn(lstart);
			lend = l.getEnd();

			if (lend.get(Calendar.DAY_OF_YEAR) != lstart
					.get(Calendar.DAY_OF_YEAR)) {
				Calendar midnight = new GregorianCalendar(2012, 01, 01, 23, 59,
						59);
				midnight.set(Calendar.YEAR, lstart.get(Calendar.YEAR));
				midnight.set(Calendar.DAY_OF_YEAR, lstart
						.get(Calendar.DAY_OF_YEAR));
				l.setEnd(midnight);
			}

		} else if (dragUp) {
			dragUp = false;
			int difference = arg0.getPoint().y - mouseY;
			int minutes = (int) (difference / panel.pixelPerMinute);
			int currentDuration = l.getDuration();
			Calendar lstart = l.getBeginn();
			Calendar lend = l.getEnd();
			lstart.add(Calendar.MINUTE, minutes);
			l.setBeginn(lstart);
			l.snapTo(snapTo);
			l.setEnd(lend);
		} else if (dragDown) {
			dragDown = false;
			int difference = arg0.getPoint().y - mouseY;
			int minutes = (int) (difference / panel.pixelPerMinute)
					+ l.getDuration();
			Calendar lstart = l.getBeginn();
			l.setDuration(minutes);
			Calendar lend = l.getEnd();

			if (lend.get(Calendar.DAY_OF_YEAR) != lstart
					.get(Calendar.DAY_OF_YEAR)) {
				Calendar midnight = new GregorianCalendar(2012, 01, 01, 23, 59,
						59);
				midnight.set(Calendar.YEAR, lstart.get(Calendar.YEAR));
				midnight.set(Calendar.DAY_OF_YEAR, lstart
						.get(Calendar.DAY_OF_YEAR));
				l.setEnd(midnight);
			}
		}

		l.snapTo(snapTo);

		start.setText("" + l.beginn.get(Calendar.HOUR_OF_DAY));
		end.setText(panel.getDayFormatter().format(l.beginn.getTime()));

		Component gpane = panel.getRootPane().getGlassPane();
		if (gpane instanceof RectGlassPane) {
			RectGlassPane rectpane = (RectGlassPane) gpane;
			rectpane.setRectangle(null);
			rectpane.repaint();
		}
		toDefault();
		invalid();

	}

	private void invalid() {
		panel.invalidate();
		start.invalidate();
		end.invalidate();
		panel.getParent().invalidate();
		panel.getParent().getParent().invalidate();
		panel.getParent().getParent().validate();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		draggedNormally = true;
		mouseY = arg0.getPoint().y;
		mouseX = arg0.getPoint().x;

		if (mouseY < panel.dragBorder) {
			draggedNormally = false;
			dragUp = true;
		}

		if (mouseY > panel.getHeight() - panel.dragBorder) {
			draggedNormally = false;
			dragDown = true;
		}

	}

	public void toDefault() {
		panel.setMouseOver(false);
		panel.setDrawDragBorder(false, false);
		panel.getRootPane().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (!draggedNormally && !dragDown && !dragUp) {
			toDefault();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	private int correctDayOfWeek(int i) {
		int weekday = i - 1;
		if (weekday == 0) {
			return 6;
		}
		return weekday - 1;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		Insets insets = panel.getInsets();
		Insets insets2 = panel.getParent().getInsets();
		if (draggedNormally) {
			int differenceDay = arg0.getPoint().x - mouseX;
			int days = (int) (differenceDay / panel.pixelPerDay);
			int currentDayOfWeek = correctDayOfWeek(panel.getBeginn().get(
					Calendar.DAY_OF_WEEK));
			int approxDayOfWeek = currentDayOfWeek + days;

			Component gpane = panel.getRootPane().getGlassPane();
			if (gpane instanceof RectGlassPane) {
				RectGlassPane rectpane = (RectGlassPane) gpane;
				Point mouse = arg0.getPoint();
				Point ppoint = panel.getLocation();
				Rectangle r = new Rectangle(
						// ppoint.x + mouse.x - mouseX +7,
						approxDayOfWeek * 100 + 7, ppoint.y + mouse.y - mouseY
								+ 7, panel.getWidth() - 5,
						panel.getHeight() - 5);
				rectpane.setRectangle(r);
				gpane.invalidate();
				rectpane.repaint();
			}
		}

		if (dragDown) {
			Component gpane = panel.getRootPane().getGlassPane();
			if (gpane instanceof RectGlassPane) {
				RectGlassPane rectpane = (RectGlassPane) gpane;
				Point mouse = arg0.getPoint();
				Point ppoint = panel.getLocation();
				Rectangle r = new Rectangle(ppoint.x + 7, ppoint.y + 7, panel
						.getWidth() - 5, panel.getHeight() + (mouse.y - mouseY)
						- 5);
				rectpane.setRectangle(r);
				gpane.invalidate();
				rectpane.repaint();
			}
		}

		if (dragUp) {
			Component gpane = panel.getRootPane().getGlassPane();
			if (gpane instanceof RectGlassPane) {
				RectGlassPane rectpane = (RectGlassPane) gpane;
				Point mouse = arg0.getPoint();
				Point ppoint = panel.getLocation();
				Rectangle r = new Rectangle(ppoint.x + 7, ppoint.y + 7
						+ (mouse.y - mouseY), panel.getWidth() - 5, panel
						.getHeight()
						- (mouse.y - mouseY) - 4);
				rectpane.setRectangle(r);
				gpane.invalidate();
				rectpane.repaint();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		panel.setMouseOver(false);
		panel.setDrawDragBorder(false, false);
		int mouseY = arg0.getPoint().y;
		int mouseX = arg0.getPoint().x;
		if (mouseX > 0 && mouseY > 0 && mouseX < panel.getWidth()
				&& mouseY < panel.getHeight()) {
			if (mouseY < panel.dragBorder) {
				panel.setDrawDragBorder(true, false);
				panel.getRootPane().setCursor(
						Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
			} else if (mouseY > panel.getHeight() - panel.dragBorder) {
				panel.setDrawDragBorder(false, true);
				panel.getRootPane().setCursor(
						Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
			} else {
				panel.setMouseOver(true);
				panel.getRootPane().setCursor(
						Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			}

		}
		panel.repaint();
	}

}
