/*
 * Test01.java   Created on May 1, 2007, 4:03:01 PM
 *
 */
package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;

public class Test01 {
   
    public static void main(String[] args) {

    	String file = "C:/temp/aaa.txt";
		try {
			InputStream is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
    	String pound = "£";
    	//URLEncoder encode = new URL
    	try {
			String en = URLEncoder.encode(pound, "UTF-8");
			System.out.println(en);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
    	
       String xxx0 = "2011-11-12 11:22:12";
       boolean bb = xxx0.matches(".*\\.\\d{3,6}$");
       bb = xxx0.matches(".*\\d{2}\\W\\d{2}\\W\\d{2}$");
       System.err.println(bb);

       String xx = "This is a cat in the room";
       System.err.println(xx.matches(".*\\bcat\\b"));

    }
    
    public static String rep1 ( ) {
        String s = "AA_ANOTHER$/1";
        String r = "ANOTHER$";
        String r1 = r.replaceAll("\\$", "\\\\\\$");
        s = s.replaceAll(r1, "oooooo");
        System.out.println(s);
        return s;
     }


}
