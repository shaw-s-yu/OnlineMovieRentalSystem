package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

import java.util.stream.IntStream;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers() {
		/*
		 * This method fetches one or more customers and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		
				
		Connection conn = null;
		try {
			String sqlstr = 
					"SELECT  P.SSN, P.Address, P.LastName, P.FirstName, L.City, L.State, C.Email, P.ZipCode, P.Telephone, C.CreditCardNumber, C.Rating "+
					"FROM 7nVxZhInjB.Location L, 7nVxZhInjB.Person P, 7nVxZhInjB.Customer C "+
					"where L.ZipCode = P.ZipCode and C.CustomerId = P.SSN ";

			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("SSN"));
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setTelephone(rs.getString("Telephone"));
				customer.setCreditCard(rs.getString("CreditCardNumber"));
				customer.setRating(rs.getInt("Rating"));
				customers.add(customer);
			}

		} catch (SQLException e) {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */


		/*Sample data begins*/
		Customer customer = new Customer();
		customer.setCustomerID("111-11-1111");
		customer.setLastName("Lu");
		customer.setFirstName("Shiyong");
		customer.setEmail("shiyong@cs.sunysb.edu");
		/*Sample data ends*/
	
		return customer;
		
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		
		List<Customer> customers = new ArrayList<Customer>();

		Connection conn = null;
		try {
			String sqlstr = 
					"SELECT P.SSN, P.FirstName, P.LastName, P.Address, L.State, L.City, L.ZipCode, C.Email "+
					"FROM 7nVxZhInjB.Person P, 7nVxZhInjB.Customer C, 7nVxZhInjB.Location L "+
					"WHERE P.SSN = C.CustomerId AND P.ZipCode = L.ZipCode ";

			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("SSN"));
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customers.add(customer);
			}

		} catch (SQLException e) {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		
		/*Sample data begins*/
		Customer customer = new Customer();
		customer.setCustomerID("111-11-1111");
		customer.setAddress("123 Success Street");
		customer.setLastName("Lu");
		customer.setFirstName("Shiyong");
		customer.setCity("Stony Brook");
		customer.setState("NY");
		customer.setEmail("shiyong@cs.sunysb.edu");
		customer.setZipCode(11790);
		customer.setTelephone("5166328959");
		customer.setCreditCard("1234567812345678");
		customer.setRating(1);
		/*Sample data ends*/
		
		return customer;
	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */

		/*Sample data begins*/
		return "success";
		/*Sample data ends*/
		
	}


	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */

		return "111-11-1111";
	}


	public List<Customer> getSellers() {
		
		/*
		 * This method fetches the all seller details and returns it
		 * The students code to fetch data from the database will be written here
		 * The seller (which is a customer) record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();
		
		/*Sample data begins*/
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setCustomerID("111-11-1111");
			customer.setAddress("123 Success Street");
			customer.setLastName("Lu");
			customer.setFirstName("Shiyong");
			customer.setCity("Stony Brook");
			customer.setState("NY");
			customer.setEmail("shiyong@cs.sunysb.edu");
			customer.setZipCode(11790);
			customers.add(customer);			
		}
		/*Sample data ends*/
		
		return customers;

	}


	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

}
