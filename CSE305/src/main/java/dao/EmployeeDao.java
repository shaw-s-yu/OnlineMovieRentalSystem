package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		Connection conn = null;
		try {
			
			Update update = new Update(employee);
			String[] queries = update.updateByCustomer();
			conn = DBAccessHelper.getDAO().getConnection();
			for(int i=0; i<queries.length;i++) {
				DBAccessHelper.getDAO().execute(queries[i], conn);
			}					
		} catch (Exception e) {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "failure";
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		
		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Employee employee = new Employee();
			employee.setEmail("shiyong@cs.sunysb.edu");
			employee.setFirstName("Shiyong");
			employee.setLastName("Lu");
			employee.setAddress("123 Success Street");
			employee.setCity("Stony Brook");
			employee.setStartDate("2006-10-17");
			employee.setState("NY");
			employee.setZipCode(11790);
			employee.setTelephone("5166328959");
			employee.setEmployeeID("631-413-5555");
			employee.setHourlyRate(100);
			employees.add(employee);
		}
		/*Sample data ends*/
		
		return employees;
	}

	public Employee getEmployee(String employeeID) {

		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */

		Employee employee = new Employee();
		Connection conn = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int ZipCode = 123;
		try {
			String sqlstr = "SELECT * FROM 7nVxZhInjB.Person WHERE SSN = '"
					+ employeeID +"'";
			
			String sqlstr2 = "SELECT * FROM 7nVxZhInjB.Employee WHERE SSN = '"
					+ employeeID +"'";

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			rs2 = DBAccessHelper.getDAO().executeQuery(sqlstr2, conn);
			try {
				// if failed to login
				if(rs == null || rs2 == null){
					System.out.println("Failed to query.");
					return null;
				}
				
				if (rs.next() && rs2.next()) {
					employee.setEmail(rs.getString("Email"));
					employee.setFirstName(rs.getString("FirstName"));
					employee.setLastName(rs.getString("LastName"));
					employee.setAddress(rs.getString("Address"));
					employee.setTelephone(rs.getString("Telephone"));
					employee.setEmployeeID(rs.getString("SSN"));
					
					ZipCode = Integer.parseInt(rs.getString("ZipCode"));
					employee.setZipCode(ZipCode);
					
					employee.setStartDate(rs2.getString("StartDate"));
					employee.setHourlyRate(Integer.parseInt(rs2.getString("HourlyRate")));
					
				}else{
					System.out.println("Failed to get a result from this employee id.");
					return null;
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
		
		try {
			String sqlstr = "SELECT * FROM 7nVxZhInjB.Location WHERE ZipCode = '"
					+ ZipCode +"'";
			

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			try {
				// if failed to login
				if(rs == null){
					System.out.println("Failed to query.");
					return null;
				}
				
				if (rs.next()) {
					employee.setCity(rs.getString("City"));
					employee.setState(rs.getString("State"));
				}else{
					System.out.println("Failed to get a result from this zipcode.");
					return null;
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		
		/*Sample data begins*/
		employee.setEmail("shiyong@cs.sunysb.edu");
		employee.setFirstName("Shiyong");
		employee.setLastName("Lu");
		employee.setEmployeeID("631-413-5555");
		/*Sample data ends*/
		
		return employee;
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sqlstr = "SELECT SSN FROM 7nVxZhInjB.Person WHERE Email = '"
					+ username + "'";

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			
			try {
				// if failed to login
				if(rs == null){
					System.out.println("Failed. rs is null");
					return null;
				}
				
				if (rs.next()) {
					String SSN = rs.getString("SSN");
					return SSN;
					
				}else{
					System.out.println("Failed. rs is empty");
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return "111111111";
	}


}
