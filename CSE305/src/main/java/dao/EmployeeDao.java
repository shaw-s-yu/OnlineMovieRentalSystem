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
		
		Connection conn = null;
		
		String[] queries = new String[4];
		
		queries[0] = String.format("INSERT INTO  Location " +
				"values(\'%s\',\'%s\', \'%s\');",
			Integer.toString(employee.getZipCode()),
			employee.getCity(),
			employee.getState());
		
		queries[1] = String.format("INSERT INTO  Person " +
				"values(\'%s\',\'%s\', \'%s\', \'%s\', %s, \'%s\' ,\'%s\')",
				employee.getEmployeeID(),
				employee.getLastName(),
				employee.getFirstName(),
				employee.getAddress(),
				employee.getZipCode(),
				employee.getTelephone(),
				employee.getEmail());
		
		queries[2] = String.format("INSERT INTO  Employee " +
				"values(\'%s\', \'%s\',\'%s\', \'%s\',\'%s\');",
				employee.getEmployeeID(),
				employee.getEmployeeID(),
			employee.getStartDate(),
			employee.getHourlyRate(),
			employee.getLevel());
		
		queries[3] = String.format("INSERT INTO  Login " +
				"values(\'%s\',\'%s\', \'%s\', \'%s\');",
				employee.getEmployeeID(),
				employee.getEmail(),
				employee.getPassword(),
				Integer.toString(1) // representatives
			);
		
		try {
			
			conn = DBAccessHelper.getDAO().getConnection();
			for(int i=0; i<queries.length;i++) {
				System.out.println(queries[i]);
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
			String[] queries = update.updateByEmployee();
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
		Connection conn = null;
		try {
			String sqlstr = "DELETE FROM Employee WHERE SSN = '"
					+ employeeID +"'";

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			DBAccessHelper.getDAO().execute(sqlstr, conn);

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
		}finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			String sqlstr = "DELETE FROM Person WHERE SSN = '"
					+ employeeID +"'";

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			DBAccessHelper.getDAO().execute(sqlstr, conn);

		}catch (Exception e) {
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
		
		try {
			String sqlstr = "DELETE FROM Login WHERE SSN = '"
					+ employeeID +"'";

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			DBAccessHelper.getDAO().execute(sqlstr, conn);

		}catch (Exception e) {
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

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sqlstr = "SELECT * FROM Employee";

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			try {
				// if failed to login
				if(rs == null){
					System.out.println("Query is incorrect.");
					if (conn != null)
						conn.close();
					return null;
				}
				
				while (rs.next()) {
					Employee employee = this.getEmployee(rs.getString("SSN"));
					employees.add(employee);
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
			String sqlstr = "SELECT * FROM Person WHERE SSN = '"
					+ employeeID +"'";
			
			String sqlstr2 = "SELECT * FROM Employee WHERE SSN = '"
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
					if (conn != null)
						conn.close();
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
			String sqlstr = "SELECT * FROM Location WHERE ZipCode = '"
					+ ZipCode +"'";
			

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			try {
				// if failed to login
				if(rs == null){
					System.out.println("Failed to query.");
					if (conn != null)
						conn.close();
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
		
		Employee employee =  null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			String sqlstr = "SELECT CustRepId, COUNT(OrderId) FROM Rental GROUP BY CustRepId HAVING COUNT(OrderId) = (SELECT MAX(count) FROM (SELECT CustRepId, COUNT(OrderId) As count FROM Rental GROUP BY CustRepId)subquery)";

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			try {
				// if failed to login
				if(rs == null){
					System.out.println("Query is incorrect.");
					if (conn != null)
						conn.close();
					return null;
				}
				
				if (rs.next()) {
					try {
						sqlstr = "SELECT SSN FROM Employee WHERE Id = '" + rs.getString("CustRepId") +"'";
						// Connect to data base
						conn = DBAccessHelper.getDAO().getConnection();
						ResultSet rs2 = null;
						// executeQuery string
						rs2 = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
						if(rs2.next()){
							String SSN = rs2.getString("SSN");
							employee = this.getEmployee(SSN);
						}
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
		String employeeID = "";
		try {
			String sqlstr = "SELECT SSN FROM Person WHERE Email = '"
					+ username + "'";

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			
			try {
				// if failed to login
				if(rs == null){
					System.out.println("Failed. rs is null");
					if (conn != null)
						conn.close();
					return null;
				}
				
				if (rs.next()) {
					employeeID = rs.getString("SSN");
					
				}else{
					System.out.println("Failed. rs is empty");
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
		return employeeID;
	}


}
