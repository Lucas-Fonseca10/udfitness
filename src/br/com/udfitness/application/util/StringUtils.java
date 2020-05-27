
package br.com.udfitness.application.util;


public class StringUtils {
	
	public static boolean isEmpty(String s) {
		
		if (s==null) {
			return true;
		}
		
		return s.trim().length()==0;
		
	}

	public static String leftZeroes(int value, int finalSize ) {
		
		return String.format("%0" + finalSize + "d", value);
		
	}
	
	
}
