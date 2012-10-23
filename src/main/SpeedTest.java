package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SpeedTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 100000;
		List<Integer> list = new ArrayList<Integer>(size);
		long t1 = System.currentTimeMillis();
		for ( int i = 0 ; i < size ; i++){
			list.add(i);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("Adding elements:" + (t2-t1));
		
		t1 = System.currentTimeMillis();
		for ( int i = 0 ; i < 100 ; i++){
			list.remove(i*100);
			list.add(i*50,i*100);
		}
		
		t2 = System.currentTimeMillis();
		System.out.println("replacing elements:" + (t2-t1));
		
		t1 = System.currentTimeMillis();
		Collections.sort(list);		
		t2 = System.currentTimeMillis();
		System.out.println("Sorted List:" + (t2-t1));
		
		
		list = new ArrayList<Integer>(size);
		Random r = new Random(123);
		t1 = System.currentTimeMillis();
		for ( int i = 0 ; i < size ; i++){
			list.add(r.nextInt(size));
		}
		
		t2 = System.currentTimeMillis();
		System.out.println("Adding random elements:" + (t2-t1));
		
		t1 = System.currentTimeMillis();
		Collections.sort(list);		
		t2 = System.currentTimeMillis();
		System.out.println("Sorted Random List:" + (t2-t1));
		
	}

}
