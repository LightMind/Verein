package main;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import plan.Horse;
import plan.Lesson;
import plan.Student;
import plan.Teacher;
import plan.ui.horse.HorseTableModel;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		
		l1.assign(t1);
		l1.addStudent(st1);
		
		l2.addStudent(st2);
		l2.assignHorse(st2, h1);
		
		System.out.println(t1);
		System.out.println(st1);
		
		l1.assignHorse(st1, h1);
		System.out.println(st1);
		

		JFrame frame1 = new JFrame();
		TableModel t = new HorseTableModel(h1);
		JTable table = new JTable(t);
		frame1.add(table);
		frame1.pack();
		frame1.setVisible(true);
		
		
		

	}

}
