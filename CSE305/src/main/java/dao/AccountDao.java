package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Movie;
import model.Account;

public class AccountDao {
	
	public int getSalesReport(Account account) {
			
			/*
			 * The students code to fetch data from the database will be written here
			 * Query to get sales report for a particular month must be implemented
			 * account, which has details about the month and year for which the sales report is to be generated, is given as method parameter
			 * The month and year are in the format "month-year", e.g. "10-2018" and stored in the dateOpened attribute of account object
			 * The month and year can be accessed by getter method, i.e., account.getAcctCreateDate()
			 */
	
			int income = 0;
			
			String[] date = account.getAcctCreateDate().split("-");
					
			Connection conn = null;
			try {
				String sqlstr = "SELECT * FROM 7nVxZhInjB.Account WHERE DateOpened >= '"+ date[1]+"-"+date[0] + "'";
				System.out.println(sqlstr);
				ResultSet rs = null;
				// Connect to data base
				conn = DBAccessHelper.getDAO().getConnection();
				// executeQuery string
				rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
				if(rs == null){
					System.out.println("Failed. rs is null. Query is wrong");
					return 0;
				}
				
				else {
					while(rs.next()) {
						income += rs.getInt("BookingFee");
					}
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
			} finally {
				// close connection
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
	
			return income;
			
		}
	
	public String setAccount(String customerID, String accountType) {
		  Connection conn = null;
//		  Account account = new Account();
		  try {
		   String sqlstr = 
		     "UPDATE  7nVxZhInjB.Account "+
		     "SET AccountType = '" + accountType + "' "+
		     "WHERE 7nVxZhInjB.Account.CustomerId = "+ customerID;

		   ResultSet rs = null;
		   // Connect to data base
		   conn = DBAccessHelper.getDAO().getConnection();
		   // executeQuery string
		   DBAccessHelper.getDAO().execute(sqlstr, conn);

//		   if (rs.next()) {
//		    account.setAccountID(rs.getInt("Id"));
//		    account.setAcctCreateDate(rs.getString("DateOpened"));
//		    account.setAccountType(rs.getString("AccountType"));
//		    account.setCustomerID(rs.getString("CustomerId"));

//		   }


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
//		  System.out.println(account.getAccountID()+" "+account.getAccountType());
//		  account.setAccountType(accountType);
//		  System.out.println(account.getAccountID()+" "+account.getAccountType());
		  /*Sample data begins*/
		  return "success";
		  /*Sample data ends*/

		}

}
