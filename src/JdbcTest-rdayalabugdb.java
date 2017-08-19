import java.sql.*;

public class JdbcTest {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		// Statement myStmt = null;
		PreparedStatement myStmt = null; // rdayala bugdb
		ResultSet myRs = null;
		
		try {
			// 1. Get a connection to database
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // rdayala bugdb
			
			// rdayala bugdb
			myConn=DriverManager.getConnection(					
					"jdbc:oracle:thin:@//amogridxp09-scan.us.oracle.com:1529/ldap_bugap.us.oracle.com","rdayala","P@$$word123");
			
			// myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student" , "student");
			
			System.out.println("Database connection successful!\n");
			
			// 2. Create a statement
			// myStmt = myConn.createStatement();
	
			// rdayala bugdb
			myStmt = myConn.prepareStatement("select h.rptno, h.subject from bug.rpthead h where h.programmer='RDAYALA' and h.status = 11");
			myStmt.setFetchSize(10);
			
			// 3. Execute SQL query
			// myRs = myStmt.executeQuery("select * from employees");
			
			// rdayala bugdb
			myRs = myStmt.executeQuery();
			
			// 4. Process the result set
			int i = 1;
			while (myRs.next()) {
				System.out.println(i + ": " + myRs.getInt("rptno") + ",  " + myRs.getString("subject"));
				// System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
				i++;				
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
	}

}
