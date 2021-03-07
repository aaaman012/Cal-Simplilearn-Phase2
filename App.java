package tools.aman.prj;

import java.sql.Statement;
import java.util.Scanner;

import javax.print.attribute.standard.MediaSize.ISO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App 
{
	String qry;
	Connection dbCon;
	Statement theStatement;
	
	App(){

    	try {
//    		Define the URL to connect
    		String urlToConnect = "jdbc:mysql://localhost:3306/simplilearn";
    		
//    		Define the username for db to connect
    		String dbUserName = "root";
    		
//    		Define the password
    		String dbUserPassword = "";
    		
//    		Define the driver for the database
    		String mySqlDriver = "com.mysql.cj.jdbc.Driver";
    		
    		
//        	Load the Driver
			Class.forName(mySqlDriver);
			
//			Try to establish the connection
			dbCon = DriverManager.getConnection(urlToConnect, dbUserName, dbUserPassword);
			
//        	Get a reference to the Statement
			theStatement = dbCon.createStatement();
			
//			System.out.println("Successfully connected to the database...");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load the Driver : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Can't connect to DB : " + e.getMessage());
		}
	}
	
    public static void main( String[] args )
    {
       

    	new App().getAllRecords();
    	/*System.out.println("Please enter the ID:");
    	
    	int id = new Scanner(System.in).nextInt();
    	
    	new App().getLearnerDetailsById(id);*/
    
    	System.out.println("Please enter the Name, Address of Learner to add :");
       	
       	Scanner theScanner = new Scanner(System.in);
       	
       	String learnerName = theScanner.nextLine();
       	
       	String learnerAddress = theScanner.nextLine();
       	//new App().addNewLearner();
       	
       	new App().addNewLearnerFromRuntime(learnerName, learnerAddress);
       	System.out.println("Please enter the ID to be updated");
       	int id = new Scanner(System.in).nextInt();
       	
       	new App().UpdateLearner(id);

		System.out.println("Please enter the ID to be deleted");
    	
    	int id1 = new Scanner(System.in).nextInt();
		
		new App().DeleteLearner(id1);
    	
    }
    
    
    
//    Get All Records from table:learners
    void getAllRecords() {
//    	Write the query to fetch all rows from table:learners
    	qry = "select * from downer";

    	try {

			
//			Execute the query
			ResultSet theResultSet = theStatement.executeQuery(qry);
			
//			Traverse through the results
			while(theResultSet.next()) {
				System.out.print("Name : " + theResultSet.getString("name"));
				System.out.print(", ID : " + theResultSet.getInt("id"));
				System.out.println(", Address : " + theResultSet.getString("roll"));
			}
			
		} catch (SQLException e) {
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}
    }
    
  
   

    void getLearnerDetailsById(int id) {
//    	Write the query to fetch details from the table:learners
    	qry = "select * from downer where id = " + id;
    	

    	try {
//        	Execute the query
			ResultSet theResultSet = theStatement.executeQuery(qry);
			
			System.out.println("Details of learner for id : " + id);
			
//			Traverse through the results
			while(theResultSet.next()) {
				System.out.println("Name : " + theResultSet.getString("name") + ", Address : " + theResultSet.getString("roll"));
			}
			
			
		} catch (SQLException e) {
			System.out.println("Can't execute the query : " + e.getMessage());
		}
    }
    void addNewLearner() {
//      Write the query to insert a new record
      	qry = "insert into downer(name, roll) values ('Alexander', 'Seattle, USA')";
      	

      	try {
//          	Execute the query
  			int result = theStatement.executeUpdate(qry);
  			
  			if(result > 0)
  				System.out.println("New Learner details addedd successfully...");
  		} catch (SQLException e) {
  			System.out.println("Can't add to the table : " + e.getMessage());
  		}
    }
      
      
      
//      Add a new Learner to the table:learners - learner details taken from runtime
      void addNewLearnerFromRuntime(String lName, String lAddress) {
//      	Write the query to insert a new record
      	qry = "insert into downer(name, roll) "
      			+ "values "
      			+ "('" 
      			+ lName + "','" 
      			+ lAddress + "')";
      	

      	try {
//          	Execute the query
  			if(theStatement.executeUpdate(qry) >  0)
  				System.out.println("Successfully added a new learner...");
  		} catch (SQLException e) {
  			System.out.println("Can't add to the table : " + e.getMessage());
  		}
      }
      void UpdateLearner(int id) {
//        Write the query to insert a new record
    	  qry = "UPDATE `downer` SET `name` = 'xyz' WHERE `downer`.`id` = "+id;;
//          	Execute the query
    	  try {
    		  theStatement.executeUpdate(qry);
    		  System.out.println("New Learner details updated successfully...");
  		} catch (SQLException e) {
  			System.out.println("Can't update to the table : " + e.getMessage());
  		}
      }
void DeleteLearner(int id) {
//  Write the query to insert a new record
	 qry = "DELETE FROM `downer` WHERE `downer`.`id` = "+id;
//    	Execute the query
		try {
			theStatement.executeUpdate(qry);
			System.out.println("New Learner details deleted successfully...");
		} catch (SQLException e) {
			System.out.println("Can't delete to the table : " + e.getMessage());
			
		}
		
			
}
      
}