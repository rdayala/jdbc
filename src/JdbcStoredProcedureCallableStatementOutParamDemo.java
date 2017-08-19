import java.sql.*;

public class JdbcStoredProcedureCallableStatementOutParamDemo {

	public static void main(String[] args) throws Exception {

		Connection myConn = null;
		CallableStatement myStmt = null;

		try {
			// Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");

			String theDepartment = "Engineering";
			
			// Prepare the stored procedure call
			myStmt = myConn.prepareCall("{call get_count_for_department(?, ?)}");

			// Set the parameters
			myStmt.setString(1, theDepartment);
			myStmt.registerOutParameter(2, Types.INTEGER);
			
			// Call stored procedure
			System.out.println("Calling stored procedure.  get_count_for_department('" + theDepartment + "', ?)");
			myStmt.execute();
			System.out.println("Finished calling stored procedure");			
			
			// Get the value of the OUT parameter
			int theCount = myStmt.getInt(2);
			
			System.out.println("\nThe count = " + theCount);

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt);
		}
	}

	private static void close(Connection myConn, Statement myStmt) throws SQLException {
		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}
}
