package net_ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFtp {

	public static void main(String[] args) {
		String server = "192.168.0.170";
		List<String> files = new ArrayList<String>();
		files.add("aa.jar");
		files.add("example.log");
		
		String localDir = "C:/temp";
		String serverDir = "/home/jin";
		ftpList(server,localDir,  serverDir, "jintesot", "mrcuser001", files);

	}

	/*******************************************************************
	 * FTP a list of files to server
	 ******************************************************************/
	public static void ftpList(String ftpHost, String localDir, String serverDir,
			String user, String pswd, 	List<String> files) {

		FTPClient client = new FTPClient();
		FileInputStream fis = null;
		String sep = File.separator;
        int reply;

		try {
			client.connect(ftpHost);
			 reply = client.getReplyCode();
			 System.out.println("FTP replyCode:" + reply);
			 //220 Service ready for new user.

	            if (!FTPReply.isPositiveCompletion(reply))
	            {
	            	client.disconnect();
	                System.exit(1);
	            }
	            
	            
			client.login(user, pswd);
			//230 User logged in, proceed. Logged out if appropriate.
			 System.out.println("replyCode:" + client.getReplyCode());
			 boolean cd = client.changeWorkingDirectory(serverDir);
             System.out.print(client.getReplyString());
             
             if (!cd) {
                     boolean mkdir = client.makeDirectory(serverDir);
                     System.out.print(client.getReplyString());
                     if (!mkdir) {
                             System.err.println("Cannot create upload directory: " + serverDir + " at server: " + ftpHost);
                             System.exit(1);
                     }
                     cd = client.changeWorkingDirectory(serverDir);
             }

	 
			 
			 //426 Connection closed; transfer aborted. 
			for (String file : files) {
				String filename = localDir + sep + file;
				fis = new FileInputStream(filename);
				//client.changeWorkingDirectory("/home/jin");
				client.storeFile(file, fis);
				 System.out.println("reply: " + client.getReplyCode() + ":");
				 for (String str : client.getReplyStrings()) {
				 System.out.println(str);
				 }
				System.out.println("sent: " + file);
				fis.close();
			}

			client.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {			 
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}