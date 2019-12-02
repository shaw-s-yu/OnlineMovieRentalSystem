package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	
	
	public Login login(String username, String password) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 */
		
		/*Sample data begins*/
		Login login = new Login();
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sqlstr = "SELECT * FROM 7nVxZhInjB.Login WHERE username = '"
					+ username +"' AND password = '" + password +"'";

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			try {
				
				// if failed to login
				if(rs == null ||!rs.next()){
					return null;
				}
				
				if (rs.next()) {
					int role = Integer.parseInt(rs.getString("role"));
					switch(role){
						case 0:
							login.setRole("customer");
							break;
						case 1:
							login.setRole("customerRepresentative");
							break;
						case 2:
							login.setRole("manager");
							break;	
						default:
							login=null;
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
//		login.setRole("customerRepresentative");
		
//		login.setRole("manager");
		return login;
		/*Sample data ends*/
		
	}
	
	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/
	}

}
