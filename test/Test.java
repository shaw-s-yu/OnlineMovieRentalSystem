//STEP 1. Import required packages
import java.sql.*;

public class Test {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://remotemysql.com:3306/?user=7nVxZhInjB";

   //  Database credentials
   static final String USER = "7nVxZhInjB";
   static final String PASS = "J2q22YGyY6";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   Object a = new Test();
   System.out.println(a.getClass().getName());

   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      String sqlstr = "UPDATE `7nVxZhInjB`.`Person` SET LastName = 'Smith' WHERE SSN = 123456789";
      stmt.execute(sqlstr);

      //STEP 5: Extract data from result set
      
      //STEP 6: Clean-up environment
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample