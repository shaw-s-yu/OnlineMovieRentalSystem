package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Rental;

public class RentalDao {
	
	public List<Rental> getOrderHisroty(String customerID) {
		  
		  List<Rental> rentals = new ArrayList<Rental>();
		  
		  Connection conn = null;
		  //System.out.println("here");
		  try {
		   String sqlstr = "SELECT Rental.* FROM  Rental,Account,Customer "+
		     "WHERE Rental.AccountId = Account.Id AND Account.CustomerId =Customer.CustomerId AND Customer.CustomerId = "+customerID
		     ;

		   ResultSet rs = null;
		   // Connect to data base
		   conn = DBAccessHelper.getDAO().getConnection();
		   // executeQuery string
		   rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
		   if(rs == null){
		    System.out.println("Failed. rs is null. Query is wrong");
		    return null;
		   }
		   
		   while (rs.next()) {
		    Rental rental = new Rental();
		    rental.setAccountID(rs.getInt("AccountId"));
		    rental.setCustomerRepID(rs.getInt("CustRepId"));
		    rental.setMovieID(rs.getInt("MovieId"));
		    rental.setOrderID(rs.getInt("OrderId"));
		    rentals.add(rental);
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
		  
		  return rentals;
		  
		 }
}
