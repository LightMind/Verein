package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import plan.Lesson;

public class LessonTime {
	public static List<Lesson> before(List<Lesson> list, Calendar c){
		Lesson l = new Lesson(-1);
		l.setBeginn(c);
		l.setEnd(c);
		
		
		int index = Collections.binarySearch(list, l);
		if ( index < 0 ){ 
			index = (index +1)*(-1);
		}
		List<Lesson> result = new ArrayList<Lesson>(index+1);
		for ( int i = 0 ; i < index ; i++){
			result.add(list.get(i));
		}
		return result;
	}
	
	public static List<Lesson> after(List<Lesson> list, Calendar c){
		Lesson l = new Lesson(-1);
		l.setBeginn(c);
		l.setEnd(c);
		
		int index = Collections.binarySearch(list, l);
		if ( index < 0 ){ 
			index = (index +1)*(-1);
		}
		List<Lesson> result = new ArrayList<Lesson>(list.size()-index +1);
		int size = list.size();
		for ( int i = index ; i < size ; i++){
			result.add(list.get(i));
		}
		return result;
	}
	
	public static List<Lesson> between(List<Lesson> list, Calendar start, Calendar end){
		Lesson l1 = new Lesson(-1);
		l1.setBeginn(start);
		l1.setEnd(start);
		
		Lesson l2 = new Lesson(-2);
		l2.setBeginn(end);
		l2.setEnd(end);
		
		int startIndex = Collections.binarySearch(list, l1);
		int endIndex = Collections.binarySearch(list, l2);
		if ( start.after(end)){
			return new ArrayList<Lesson>();
		}
		if ( startIndex < 0 ){ 
			startIndex = (startIndex +1)*(-1);
		}
		if ( endIndex < 0 ){ 
			endIndex = (endIndex +1)*(-1);
		}
		List<Lesson> result = new ArrayList<Lesson>(endIndex - startIndex + 10);
		for ( int i = startIndex ; i < endIndex ; i++){
			result.add(list.get(i));
		}
		return result;
	}
}
