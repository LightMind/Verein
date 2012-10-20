package main;

import plan.Horse;
import plan.Lesson;
import plan.Student;
import plan.Teacher;

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
		
		l1.assign(t1);
		l1.addStudent(st1);
		
		System.out.println(t1);
		System.out.println(st1);
		
		l1.assignHorse(st1, h1);
		System.out.println(st1);
		
		l1.removeStudent(st1);
		System.out.println(st1);
		

	}

}
