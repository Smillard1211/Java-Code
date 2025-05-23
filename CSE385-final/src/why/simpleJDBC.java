package why;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class simpleJDBC {
	
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

	public void readDataBase() throws Exception {
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//*******************************************************************
			//*******************************************************************
			String username = "root";//change this to your MySQL server user name
			String password = "1234";//change this to your MySQL server password
			//*******************************************************************
			//*******************************************************************
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?"+ "user="+username+"&password="+password);
			
			//this creates a statement object which is the thing that is actually sent to the MySQL database
			//MySQL does not process Strings, or arrays or anything else, it only processes Statement objects
			//So here we are preparing the statement that will eventually store the SQL query that we want to
			//send to the DB
			statement = connect.createStatement(); 
			
			
			resultSet = statement.executeQuery("Select * from classicModels.customers;");
			writeCustomerData(resultSet); //custom method call. We will write this.
			writeMetaData(resultSet);
			
			
		}//end try
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}//end catch
		finally {
			close(); //custom method that we will write;
		}
	}//end readDataBase
	
	private void writeMetaData(ResultSet resultSet) throws SQLException {
		System.out.println("*******************************************************************");
		System.out.println("the columns in the table are: ");
		
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }//end for
		
	}//end writeMetaData
	
	private void writeCustomerData(ResultSet resultSet) throws SQLException{
		while(resultSet.next()) {
		 int customerNumber = resultSet.getInt("customerNumber");
		 String customerName = resultSet.getString("customerName");
		 double creditLimit = resultSet.getDouble("creditLimit");
		 System.out.print("customer number: "+ customerNumber);
		 System.out.print(" cutomer name: "+ customerName);
		 System.out.println(" creditLimit: "+ creditLimit);
		}//end while
	}//end writeResultSet
	
	private void close() {
		try {
			if(resultSet != null) {
				resultSet.close();
			}//end if
			if(resultSet != null) {
				resultSet.close();
			}//end if
			if(resultSet != null) {
				resultSet.close();
			}//end if
			
		}//end try
		catch(Exception e) {
			e.printStackTrace();
		}//end catch
	}//end close
	
	public static void main(String[] args) throws Exception {
		System.out.println("starting program");
		simpleJDBC simpleConnaction = new simpleJDBC();
		System.out.println("initiating connection");
		simpleConnaction.readDataBase();
        
        System.out.println("program terminated");

	}

}
