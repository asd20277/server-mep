package framework.utility;

import java.io.*;
/**
 * My Utility class.
 * 
 * @author FengShuo Yu
 */
public class MyUtil {
	public static String big5ToUnicode(String s) {
		try {
			return new String(s.getBytes("ISO8859_1"), "big5");
		} catch (UnsupportedEncodingException uee) {
			return s;
		}
	}

}
