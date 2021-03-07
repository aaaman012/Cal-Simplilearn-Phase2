package tools.aman.prj;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class usingUpdatableResultsSet{
	String qry;
	Connection dbCon;
	Statement theStatement;
	
	public usingUpdatableResultsSet(){

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
			
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load the Driver : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Can't connect to DB : " + e.getMessage());
		}
	}
	public static void main(String[] args) {
		new usingUpdatableResultsSet().addNewLearner();
	}
	void addNewLearner() {
//		Write the query to insert
		qry = "select * from downer";
		

		try {
//			Get a reference to the PreparedStatement
			PreparedStatement pstmt = dbCon.prepareStatement(qry,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
//			Set the values for ?
			ResultSet theResultSet=pstmt.executeQuery();
			while(theResultSet.next()) {
				if(theResultSet.getInt("id")==8) {
					theResultSet.updateString("name",theResultSet.getString("name") + "leigh");
					theResultSet.updateRow();
					System.out.println("Added Simultaneously");
				}
			}
			
	} catch (SQLException e) {
			System.out.println("Can't get a reference to the PreparedStatement : " + e.getMessage());
		}
	}
}