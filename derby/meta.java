/*
 * Created on April 1, 2004
 * Test connecting to local oracle db.
 */
package derby;
import java.sql.*;
 
public class meta {
	
	public static void main(String args[]) {
			Connection conn = null;

			try {
				Class.forName("org.apache.derby.jdbc.ClientDriver");
				System.out.println("Driver loaded.");
				String sys = "jdbc:derby://localhost:1527/mrcdb;create=false";		
				conn = DriverManager.getConnection(sys, "sa", "mrc");
				System.out.println("Connecting to: " + sys);
				
				DatabaseMetaData meta = conn.getMetaData();
				String[] types = {"TABLE"};
				ResultSet rs = meta.getSchemas();//(null, null, null, null);
				while (rs.next()) {
					String s1 = rs.getString("TABLE_SCHEM");
					//String s2 = rs.getString("COLUMN_NAME");
					//String s3 = rs.getString("DATA_TYPE");
				 
					System.out.println(s1  );
				}
				rs.close();
				conn.close();
				
			} catch (ClassNotFoundException exc) {
				System.out.println("Error: " + exc);
				exc.printStackTrace();
			} catch (SQLException exc) {
				System.out.println("connection failed with:'" + exc.getMessage() + "'");
				exc.printStackTrace();
			} finally {
				try {
				 	if (conn != null) {
				 		conn.close(); 
					    System.out.println("connection closed.");
				 	}
				}  catch (SQLException exc) {}
			}

		  
		} //main                


}
