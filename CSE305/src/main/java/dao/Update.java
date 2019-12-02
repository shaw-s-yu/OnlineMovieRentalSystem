package dao;

import model.Customer;
import model.Employee;
import model.Movie;
import java.lang.Object;

public class Update {
	
	private Customer customer;
	private Employee employee;
	private Movie movie;
	
	public Update(Object obj) {
		if(obj.getClass().getSimpleName().equals("Customer")) {
			this.customer = (Customer)obj;
		}
		else if(obj.getClass().getSimpleName().equals("Movie")) {
			this.movie = (Movie)obj;
		}
		else if(obj.getClass().getSimpleName().equals("Employee")) {
			this.employee = (Employee)obj;
		}
	}
	
	
	public String[] updateByCustomer(){
		String[] queries = new String[]{"", "", "", ""};
		
		queries[0]= this.updatePerson("Customer");
		queries[1]= this.updateCustomer("Customer");
		queries[2]= this.updateLocation("Customer");
		queries[3]= this.updateLogin("Customer");
		
		return queries;
	}
	
	public String[] updateByEmployee(){
		String[] queries = new String[]{"", "", ""};
		
		queries[0]= this.updatePerson("Employee");
		queries[1]= this.updateLogin("Employee");
		queries[2]= this.updateEmployee("Employee");
		
		return queries;
	}
	
	public String updateByMovie() {
		String query = "UPDATE 7nVxZhInjB.Movie SET" + " Name ='" + this.movie.getMovieName()+
				"' , Type = '"+this.movie.getMovieType()+"' , Rating = '"+this.movie.getRating()+
				"' , DistrFee = '"+this.movie.getDistFee()+ "' , NumCopies = '"+this.movie.getNumCopies()+
				"' WHERE Id = " + this.movie.getMovieID();
		return query;
	}
	
	public String updatePerson(String type) {

		String sql ="";
		if(type.equals("Customer")) {
			sql = "UPDATE 7nVxZhInjB.Person SET" + " Email = '"+ this.customer.getEmail() +
					"' , LastName = '"+ this.customer.getLastName() + "', FirstName = '"+this.customer.getFirstName()+
					"' , Address = '"+ this.customer.getAddress() + "', ZipCode = '"+this.customer.getZipCode()+ 
					"' , Telephone = '"+ this.customer.getTelephone() + "' WHERE SSN = "+this.customer.getCustomerID();
		}else if(type.equals("Employee")) {
			sql = "UPDATE 7nVxZhInjB.Person SET" + " Email = '"+ this.employee.getEmail() +
					"' , LastName = '"+ this.employee.getLastName() + "', FirstName = '"+this.employee.getFirstName()+
					"' , Address = '"+ this.employee.getAddress() + "', ZipCode = '"+this.employee.getZipCode()+ 
					"' , Telephone = '"+ this.employee.getTelephone() + "' WHERE SSN = "+this.employee.getEmployeeID();
		}
		return sql;
	}
	
	public String updateCustomer(String type) {
		String sql ="";
		if(type.equals("Customer")) {
			sql = "UPDATE 7nVxZhInjB.Customer SET" +
				 "Rating = '"+this.customer.getRating()+
				"' , CreditCardNumber = '"+ this.customer.getCreditCard() + "' WHERE SSN = "+this.customer.getCustomerID();
		}
		return sql;
	}
	
	public String updateLocation(String type) {
		String sql ="";
		if(type.equals("Customer")) {
			 sql = "UPDATE 7nVxZhInjB.Location SET" +
					" City = '"+ this.customer.getCity() + "', State = '"+this.customer.getState()+
					"' WHERE ZipCode = "+this.customer.getZipCode();
		}else if(type.equals("Employee")) {
			sql = "UPDATE 7nVxZhInjB.Location SET" +
					" City = '"+ this.employee.getCity() + "', State = '"+this.employee.getState()+
					"' WHERE ZipCode = "+this.employee.getZipCode();
		}

		
		return sql;
	}
	
	public String updateLogin(String type) {
		String sql ="";
		if(type.equals("Customer")) {
		sql = "UPDATE 7nVxZhInjB.Login SET" +
				" Email = '"+ this.customer.getEmail() +
				"' WHERE SSN = "+this.customer.getCustomerID();
		}else if(type.equals("Employee")) {
			sql = "UPDATE 7nVxZhInjB.Login SET" +
					" Email = '"+ this.employee.getEmail() +
					"' WHERE SSN = "+this.employee.getEmployeeID();
		}

		return sql;
	}
	
	public String updateEmployee(String type) {
		String sql = "UPDATE 7nVxZhInjB.Employee SET" + " StartDate = '"+ this.employee.getStartDate() +
				"' , HourlyRate = '"+ this.employee.getHourlyRate()  + "' WHERE SSN = "+this.employee.getEmployeeID();
		return sql;
	}
}
