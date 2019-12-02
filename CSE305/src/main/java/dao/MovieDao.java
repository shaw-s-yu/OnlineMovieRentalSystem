package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Customer;
import model.Employee;
import model.Movie;

public class MovieDao {

	
	public List<Movie> getMovies() {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of all the movies has to be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		
		Connection conn = null;
		try {
			String sqlstr = 
					"SELECT  * FROM  7nVxZhInjB.Movie";

			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			while (rs.next()) {
				Movie movie = new Movie();
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movie.setRating(rs.getInt("Rating"));
				movies.add(movie);
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
		
		return movies;

	}
	
	public Movie getMovie(String movieID) {

		/*
		 * The students code to fetch data from the database based on "movieID" will be written here
		 * movieID, which is the Movie's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Movie" class object
		 */

		Connection conn = null;
		Movie movie = new Movie();

		try {
			String sqlstr = 
					"SELECT  * FROM  7nVxZhInjB.Movie WHERE Id = " + movieID;

			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			
			if (rs.next()) {
				System.out.println(movieID+" "+ rs.getInt("Id"));
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movie.setRating(rs.getInt("Rating"));
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
		
		
		return movie;
	}
	
	public String addMovie(Movie movie) {

		/*
		 * All the values of the add movie form are encapsulated in the movie object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. movieName can be accessed by movie.getMovieName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the movie details and return "success" or "failure" based on result of the database insertion.
		 */
		Connection conn = null;
		
		String[] queries = new String[1];
		
		queries[0] = String.format("INSERT INTO  7nVxZhInjB.Movie " +
				"values(null, \'%s\',\'%s\', \'%s\', \'%s\', \'%s\');",
				movie.getMovieName(),
				movie.getMovieType(),
				Integer.toString(movie.getRating()),
				Integer.toString(movie.getDistFee()),
				Integer.toString(movie.getNumCopies()));
		
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
	
	public String editMovie(Movie movie) {
		/*
		 * All the values of the edit movie form are encapsulated in the movie object.
		 * These can be accessed by getter methods (see Movie class in model package).
		 * e.g. movieName can be accessed by movie.getMovieName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		Update update= new Update(movie);
		String sqlstr = update.updateByMovie();
		Connection conn = null;

		try {
			
			conn = DBAccessHelper.getDAO().getConnection();
			System.out.println(movie.getMovieID());
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

	public String deleteMovie(String movieID) {
		/*
		 * movieID, which is the Movie's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		Connection conn = null;
		try {
			String sqlstr = "DELETE FROM 7nVxZhInjB.Movie WHERE Id = '"
					+ movieID + "'";

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
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}
	
	
	public List<Movie> getBestsellerMovies() {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of the bestseller movies has to be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		
		Connection conn = null;
		try {
			String sqlstr = "SELECT 7nVxZhInjB.Movie.*, DistrFee*COUNT(MovieId) FROM 7nVxZhInjB.Movie, 7nVxZhInjB.MovieOrder "+
					"WHERE MovieId = 7nVxZhInjB.Movie.Id "+
					"GROUP BY MovieId "+
					"ORDER BY DistrFee*COUNT(MovieId) DESC";

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
				Movie movie = new Movie();
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movie.setDistFee(rs.getInt("DistrFee"));
				movie.setNumCopies(rs.getInt("NumCopies"));
				movie.setRating(rs.getInt("Rating"));
				movies.add(movie);
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
		
		return movies;

	}

	public List<Movie> getSummaryListing(String searchKeyword) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of summary listing of revenue generated by a particular movie or movie type must be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 * Store the revenue generated by an movie in the soldPrice attribute, using setSoldPrice method of each "movie" object
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 6; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movie.setDistFee(10000);
			movie.setNumCopies(3);
			movie.setRating(5);
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}
	
	
	

	public List<Movie> getMovieSuggestions(String customerID) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch movie suggestions for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom the movie suggestions are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}
	
	public List<Movie> getCurrentMovies(String customerID){
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch currently hold movie for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom currently hold movie are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;
		
		
		
	}
	
public List<Movie> getQueueOfMovies(String customerID){
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch movie queue for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom movie queue are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;
		
		
		
	}
	
	

//	public List getMoviesBySeller(String sellerID) {
//		
//		/*
//		 * The students code to fetch data from the database will be written here
//		 * Query to fetch movies sold by a given seller, indicated by sellerID, must be implemented
//		 * sellerID, which is the Sellers's ID who's movies are fetched, is given as method parameter
//		 * The bid and order details of each of the movies should also be fetched
//		 * The bid details must have the highest current bid for the movie
//		 * The order details must have the details about the order in which the movie is sold
//		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
//		 * Each bid record is required to be encapsulated as a "Bid" class object and added to the "bids" List
//		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
//		 * The movies, bids and orders Lists have to be added to the "output" List and returned
//		 */
//
//		List output = new ArrayList();
//		List<Movie> movies = new ArrayList<Movie>();
//		List<Bid> bids = new ArrayList<Bid>();
//		List<Order> orders = new ArrayList<Order>();
//		
//		/*Sample data begins*/
//		for (int i = 0; i < 4; i++) {
//			Movie movie = new Movie();
//			movie.setMovieID(123);
//			movie.setDescription("sample description");
//			movie.setType("BOOK");
//			movie.setName("Sample Book");
//			movies.add(movie);
//			
//			Bid bid = new Bid();
//			bid.setCustomerID("123-12-1234");
//			bid.setBidPrice(120);
//			bids.add(bid);
//			
//			Order order = new Order();
//			order.setMinimumBid(100);
//			order.setBidIncrement(10);
//			order.setOrderID(123);
//			orders.add(order);
//		}
//		/*Sample data ends*/
//		
//		output.add(movies);
//		output.add(bids);
//		output.add(orders);
//		
//		return output;
//	}

	public List<Movie> getMovieTypes() {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 * A query to fetch the unique movie types has to be implemented
		 * Each movie type is to be added to the "movie" object using setType method
		 */
		
		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		movies.add(new Movie());
		movies.get(movies.size()-1).setMovieType("Comedy");
		movies.add(new Movie());
		movies.get(movies.size()-1).setMovieType("Drama");
		movies.add(new Movie());
		movies.get(movies.size()-1).setMovieType("Action");
		movies.add(new Movie());
		movies.get(movies.size()-1).setMovieType("Foreign");
		
		/*Sample data ends*/
		
		return movies;
	}

	public List getMoviesByName(String movieName) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieName, which is the movie's name on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieName in their name has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
				
		Connection conn = null;
		try {
			String sqlstr = "SELECT * FROM 7nVxZhInjB.Movie WHERE NAME LIKE '%"+movieName+"'";

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
				Movie movie = new Movie();
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movies.add(movie);
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

		
		return movies;
	}
	
	public List getMoviesByActor(String actorName) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieName, which is the movie's name on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieName in their name has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	

	public List getMoviesByType(String movieType) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieType, which is the movie's type on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieType as their type has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		
		Connection conn = null;
		try {
			String sqlstr = "SELECT * FROM 7nVxZhInjB.Movie WHERE Type = '" + movieType +"'";

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
				Movie movie = new Movie();
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movies.add(movie);
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
		

		
		return movies;
	}
	
	public List getMovieRentalsByName(String movieName) {
		
		

		List<Movie> movies = new ArrayList<Movie>();
		
		
		Connection conn = null;
		try {
			String sqlstr = "SELECT * FROM 7nVxZhInjB.Movie WHERE Name LIKE '%"+movieName+"%'";

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
				Movie movie = new Movie();
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movies.add(movie);
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
		

		
		return movies;
	}
	
	public List getMovieRentalsByCustomer(String customerName) {
		
		

		List<Movie> movies = new ArrayList<Movie>();
		
		
		Connection conn = null;
		try {
			String sqlstr = "SELECT * FROM 7nVxZhInjB.Movie WHERE Id IN ("+
					"SELECT Q.MovieId FROM 7nVxZhInjB.MovieQ Q, 7nVxZhInjB.Person P, 7nVxZhInjB.Account A "+
					"WHERE A.Id = Q.AccountId AND A.CustomerId = P.SSN AND "+
					"(P.LastName LIKE '%"+customerName+"%' OR P.FirstName LIKE '%"+customerName+"%'))";

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
				Movie movie = new Movie();
				movie.setMovieID(rs.getInt("Id"));
				movie.setMovieName(rs.getString("Name"));
				movie.setMovieType(rs.getString("Type"));
				movies.add(movie);
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
		

		
		return movies;
	}
	

	public List getMovieRentalsByType(String movieType) {
		
	

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	

	public List<Movie> getBestsellersForCustomer(String customerID) {

		/*
		 * The students code to fetch data from the database will be written here.
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList.
		 * Query to get the Best-seller list of movies for a particular customer, indicated by the customerID, has to be implemented
		 * The customerID, which is the customer's ID for whom the Best-seller movies have to be fetched, is given as method parameter
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 6; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfatfwefher");
			movie.setMovieType("Drama");
			movie.setDistFee(10000);
			movie.setNumCopies(3);
			movie.setRating(5);
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}

}
