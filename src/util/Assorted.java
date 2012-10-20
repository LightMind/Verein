package util;

import java.util.Collection;

public class Assorted {
	public static String renderList(Collection c){
		String result = "(";
		for (Object element : c){
			result += element + "\r\n";
		}
		return result+")\r\n";
	}
}
