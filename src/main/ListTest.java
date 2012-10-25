package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();

		list.add("Mon1");
		list.add("tue2");
		list.add("wed3");
		list.add("thu4");
		list.add("fri5");

		List<String> p1 = list.subList(0, 3);
		List<String> p2 = list.subList(3, 5);
		for (String str : p1) {
			System.out.println(str);
		}
		System.out.println("- - -");

		for (String str : p2) {
			System.out.println(str);
		}
		System.out.println("- - -");

		list.add(2, "diff");

		for (String str : p1) {
			System.out.println(str);
		}
		System.out.println("- - -");

		for (String str : p2) {
			System.out.println(str);
		}
		System.out.println("- - -");

	}

}
