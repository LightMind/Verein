package plan.ui.lesson;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class ComponentProvider {
	public static JComponent getComponent(plan.Lesson lesson) {
		Border b = new BevelBorder(BevelBorder.RAISED);

		JPanel panel = new JPanel();
		panel.setBorder(b);
		JLabel start = new JLabel("start");
		JLabel id = new JLabel("" + lesson.id);
		JLabel end = new JLabel("end");
		panel.add(start);
		panel.add(id);
		panel.add(end);
		return panel;
	}
}
