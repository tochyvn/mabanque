package org.sid.fonctions;

public class Validator {

	public static boolean isDouble(String doubleString) {
		boolean flag = false;
		try {
			Double.parseDouble(doubleString);
			flag = true;
		} catch (NumberFormatException e) {
			
		}
		return flag;
	}
}
