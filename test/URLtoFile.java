package test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class URLtoFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://localhost:8011/mrcjava/license.html";
		try {
			URL ur = new URL(url);
			File urlf = FileUtils.toFile(ur);
			System.out.println(urlf);
		} catch (MalformedURLException e) {
			 
			e.printStackTrace();
		}
		

	}

}
