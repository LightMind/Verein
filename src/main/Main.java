package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.NavigableSet;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

import plan.Horse;
import plan.Lesson;
import plan.LessonPlan;
import plan.Student;
import plan.Teacher;
import plan.ui.horse.HorseTableModel;
import plan.ui.lesson.ComponentProvider;
import plan.ui.lesson.LessonMaker;
import plan.ui.lesson.LessonPanel;
import plan.ui.lesson.RectGlassPane;
import plan.ui.lesson.TimeLayoutManager;
import util.Observeable;
import util.Observer;
import util.SortedObservingSet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LessonPlan lp = new LessonPlan();

		Horse h1 = new Horse("Horse1", 1);
		Horse h2 = new Horse("Horse2", 2);
		Horse h3 = new Horse("Horse3", 3);

		Teacher t1 = new Teacher("Teach1", 1);
		Teacher t2 = new Teacher("Teach2", 2);

		Student st2 = new Student("Stud2", 2);
		Student st1 = new Student("Stud1", 1);
		Student st3 = new Student("Stud3", 3);

		Lesson l1 = new Lesson(1);
		Lesson l2 = new Lesson(2);

		l2.setBeginn(new GregorianCalendar(2012, 1, 15, 6, 0));
		l2.setEnd(new GregorianCalendar(2012, 1, 15, 7, 0));
		l1.setBeginn(new GregorianCalendar(2012, 1, 16, 8, 0));
		l1.setEnd(new GregorianCalendar(2012, 1, 16, 9, 0));

		l1.assign(t1);
		l1.addStudent(st1);

		l2.addStudent(st2);
		l2.assignHorse(st2, h1);

		l1.assignHorse(st1, h1);

		final JFrame frame1 = new JFrame();
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setLayout(new FlowLayout());

		final RectGlassPane glassPane = new RectGlassPane();
		frame1.setGlassPane(glassPane);
		glassPane.setVisible(true);

		Border b = new BevelBorder(BevelBorder.LOWERED);
		final JLabel label2 = new JLabel();

		label2.setLayout(new TimeLayoutManager(0, 24));
		label2.setBorder(b);
		label2.add(new LessonPanel(l1));
		label2.add(new LessonPanel(l2));
		label2.setVisible(true);
		label2.setPreferredSize(new Dimension(700, 600));
		frame1.add(label2);
		/*
		 * TableModel t = new HorseTableModel(h1); JTable table = new JTable(t);
		 * frame1.add(table);
		 */

		/*
		 * JComponent c1 = ComponentProvider.getComponent(l1); JComponent c2 =
		 * ComponentProvider.getComponent(l2); frame1.add(c1); frame1.add(c2);
		 */

		// frame1.add(new LessonMaker(lp));
		frame1.pack();
		frame1.setVisible(true);
		/*
		 * NavigableSet<Lesson> ls = new SortedObservingSet<Lesson>();
		 * ls.add(l1); ls.add(l2);
		 * for ( Lesson l : ls){ System.out.println(l); }
		 * for ( Lesson l : ls){ System.out.println(l); }
		 */
	}

}
