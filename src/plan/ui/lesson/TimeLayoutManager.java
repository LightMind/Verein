package plan.ui.lesson;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Calendar;

import util.SortedObservingSet;

public class TimeLayoutManager implements LayoutManager2, LayoutManager {

	private int hourStart, hourEnd;
	private int width, height;

	public TimeLayoutManager(int hourStart, int hourEnd) {
		this.hourStart = hourStart;
		this.hourEnd = hourEnd;
		width = 700;
		height = 600;
	}

	@Override
	public void addLayoutComponent(Component com, Object arg1) {

	}

	@Override
	public float getLayoutAlignmentX(Container arg0) {
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container arg0) {
		return 0;
	}

	@Override
	public void invalidateLayout(Container arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Dimension maximumLayoutSize(Container parent) {
		return layoutSize(parent);
	}

	private Dimension layoutSize(Container parent) {
		Insets ins = parent.getInsets();
		return new Dimension(width + ins.left + ins.right, height + ins.top
				+ ins.bottom);
	}

	@Override
	public void addLayoutComponent(String arg0, Component com) {

	}

	/**
	 * duration in minutes
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private int duration(Calendar start, Calendar end) {
		int hour1 = start.get(Calendar.HOUR_OF_DAY);
		int hour2 = end.get(Calendar.HOUR_OF_DAY);
		int min1 = start.get(Calendar.MINUTE);
		int min2 = end.get(Calendar.MINUTE);

		int a = 60 * hour1 + min1;
		int b = 60 * hour2 + min2;

		return b - a;
	}

	private int duration(int hourStart, Calendar start) {
		int hour1 = start.get(Calendar.HOUR_OF_DAY);
		int min1 = start.get(Calendar.MINUTE);
		int a = 60 * hour1 + min1;
		return a - hourStart * 60;
	}

	@Override
	public void layoutContainer(Container parent) {
		float wholeDuration = (hourEnd - hourStart) * 60;
		float pixelPerMinute = (float) height / wholeDuration;
		float pixelPerDay = (float) width / 7.0f;

		int count = parent.getComponentCount();
		for (int i = 0; i < count; i++) {
			Component component = parent.getComponent(i);
			if (component instanceof LessonPanel) {
				LessonPanel panel = (LessonPanel) component;

				panel.setPPM(pixelPerMinute);
				panel.setPPD(pixelPerDay);
				float duration = duration(panel.getBeginn(), panel.getEnd());
				float durationFromBeginn = duration(hourStart, panel
						.getBeginn());

				int top = (int) (durationFromBeginn * pixelPerMinute);
				int height = (int) (duration * pixelPerMinute);
				int left = (int) (correctDayOfWeek(panel.getBeginn().get(
						Calendar.DAY_OF_WEEK)) * pixelPerDay);

				panel.setBounds(left, top, 100, height);
			}
		}
	}

	private int correctDayOfWeek(int i) {
		int weekday = i - 1;
		if (weekday == 0) {
			return 6;
		}
		return weekday - 1;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return layoutSize(parent);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return layoutSize(parent);
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub
	}

}
