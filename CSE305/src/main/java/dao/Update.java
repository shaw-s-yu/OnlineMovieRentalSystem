package dao;

import model.Customer;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Update {
	
	private Customer customer;
	
	public Update(Object obj) {
		if(obj.getClass().getName().equals("model.Customer")) {
			this.customer = (Customer)obj;
		}
		else if(obj.getClass().getName().equals("model.Movie")) {
			//to be continued
		}
	}
	
	
	public String[] updateByCustomer(){
		String[] queries = new String[]{"", "", ""};
		
		queries[0]= this.updatePerson();
		queries[1]= this.updateCustomer();
		queries[2]= this.updateLocation();
		
		return queries;
	}
	
	public String updatePerson() {

		String sql = "UPDATE 7nVxZhInjB.Person SET" +
				" LastName = '"+ this.customer.getLastName() + "', FirstName = '"+this.customer.getFirstName()+
				"' , Address = '"+ this.customer.getAddress() + "', ZipCode = '"+this.customer.getZipCode()+
				"' , Telephone = '"+ this.customer.getTelephone() + "' WHERE SSN = "+this.customer.getCustomerID();
		return sql;
	}
	
	public String updateCustomer() {
		String sql = "UPDATE 7nVxZhInjB.Customer SET" +
				" Email = '"+ this.customer.getEmail() + "', Rating = '"+this.customer.getRating()+
				"' , CreditCardNumber = '"+ this.customer.getCreditCard() + "' WHERE SSN = "+this.customer.getCustomerID();
		return sql;
	}
	
	public String updateLocation() {
		
		String sql = "UPDATE 7nVxZhInjB.Location SET" +
				" City = '"+ this.customer.getCity() + "', State = '"+this.customer.getState()+
				"' WHERE ZipCode = "+this.customer.getZipCode();
		return sql;
	}
	
}
