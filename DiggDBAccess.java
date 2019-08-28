import java.sql.*;

/**
 * Description: access class for connecting
 * to the databases and running all SQL
 * queries for the user
 * 
 * (DSR Assignment 2) 
 * 
 * @author Bridget
 * @date May 2019
 */
public class DiggDBAccess {
	
	// declares the variables for the connections required
	// connection to dsr database
	private Connection conn1;
		
	/**
	 * constructor for the DiggDBAccess class
	 * conn1 - connects to the dsr database
	 */
	public DiggDBAccess() {
		try 
		{ 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	        conn1 = DriverManager.getConnection("jdbc:mysql://seitux2.adfa.unsw.edu.au/dsr", "z5215332", "mysqlpass"); 
		} 
		catch (Exception ex) 
		{ 
			System.err.println("Unable to load MySQL driver."); 
			ex.printStackTrace(); 
		} 		
	}

	/**
	 * prints the results from Task 3
	 * @return Parts A, B, C, D, E from Task 3 in a string
	 */
	public String printSimpleStatistics() {
		
		String result = printPartA() + "\n" + printPartB() + "\n" + printPartC()
							+ "\n" + printPartD() + "\n" + printPartE();		
		return result;
	}
	
	/**
	 * Lists the distinct category2 labels from the Stories table
	 * @return the SQL query as a string (result) (or an error is thrown)
	 */
	private String printPartA() {
		try 
	    {	        
	        Statement stmt = conn1.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT DISTINCT category2 FROM Stories"); 
	        
	        String result = "\n\nPart A - Task 3:\n";
	        result += "Display contents: category2\n";
	        while (rs.next()) 
	        { 
	         	 result += rs.getString("category2") + "\n"; 
	        } 
	        
	        rs.close();
	        stmt.close();
	        
	        return result;
	    }
	    catch (SQLException ex) 
	    {
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState:     " + ex.getSQLState());
	         System.out.println("VendorError:  " + ex.getErrorCode());
	         ex.printStackTrace();
	    }
		return null;
	}

	/**
	 * Displays the storyID, title, numDiggs and category2 from all stories with less than 200 diggs
	 * @return the SQL query as a string (or an error is thrown)
	 */
	private String printPartB() {
		try 
	    {	        
	        Statement stmt = conn1.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT storyID, title, numDiggs, category2 FROM Stories WHERE numDiggs < 200"); 
	        
	        String result = "\n\nPart B - Task 3:\n";
	        result += "Display contents: storyID, title, numDiggs, category2\n";
	        while (rs.next()) 
	        { 
	         	 result += (rs.getString("storyID")) + 
	         			 (",\t" + rs.getString("title") + 
	         					 ",\t" + rs.getInt("numDiggs") +
	         					 	",\t" + rs.getString("category2") + "\n"); 
	        } 
	        
	        rs.close();
	        stmt.close();
	        
	        return result;
	    }
	    catch (SQLException ex) 
	    {
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState:     " + ex.getSQLState());
	         System.out.println("VendorError:  " + ex.getErrorCode());
	         ex.printStackTrace();
	    }
		return null;
	}
	
	/**
	 * Displays the average number of diggs (numDiggs) per story 
	 * (as identified by a unique storyID) from the Stories table
	 * @return the SQL query as a string (or an error is thrown)
	 */
	private String printPartC() {
		try 
	    {	        
	        Statement stmt = conn1.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT AVG(numDiggs) FROM Stories"); 
	        
	        String result = "\n\nPart C -- Task 3:\n";
	        result += "Display contents: average numDiggs\n";
	        while (rs.next()) 
	        { 
	         	 result += rs.getString("AVG(numDiggs)") + "\n";  
	        } 
	        
	        rs.close();
	        stmt.close();
	        
	        return result;
	    }
	    catch (SQLException ex) 
	    {
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState:     " + ex.getSQLState());
	         System.out.println("VendorError:  " + ex.getErrorCode());
	         ex.printStackTrace();
	    }
		return null;
	}
	
	/**
	 * Displays the uploaderID of each distinct story uploader as listed in the Stories table, 
	 * and the number of stories uploaded by that user. 
	 * Only those users who have uploaded greater than 10 stories are listed.
	 * @return the SQL query as a string (or an error is thrown)
	 */
	private String printPartD() {
		try 
	    {	        
	        Statement stmt = conn1.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT DISTINCT uploaderID, COUNT(*) FROM Stories GROUP BY uploaderID HAVING COUNT(*) > 10"); 
	        
	        String result = "\n\nPart D -- Task 3:\n";
	        result += "Display contents: uploaderID, number of stories\n";
	        while (rs.next()) 
	        { 
	        	result += (rs.getString("uploaderID")) + 
    					 	("\t\t" + rs.getString("COUNT(*)") + "\n"); 
	        } 
	        
	        rs.close();
	        stmt.close();
	        
	        return result;
	    }
	    catch (SQLException ex) 
	    {
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState:     " + ex.getSQLState());
	         System.out.println("VendorError:  " + ex.getErrorCode());
	         ex.printStackTrace();
	    }
		return null;
	}
	
	/**
	 * Displays for all comments with more than 1000 followers, commentID, 
	 * numFollowers, storyID and originalURL of the story
	 * @return the SQL query as a string (or an error is thrown)
	 */
	private String printPartE() {
		try 
	    {	        
	        Statement stmt = conn1.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT commentID, numFollowers, Comments.storyID, originalURL FROM Comments "
	        		+ "LEFT JOIN Stories ON Comments.storyID = Stories.storyID WHERE numFollowers > 1000"); 
	        
	        String result = "\n\nPart E - Task 3:\n";
	        result += "Display contents: commentID, numFollowers, storyID, originalURL\n";
	        
	        while (rs.next()) 
	        { 
	         	 result += (rs.getString("commentID")) + 
	         			 ("\t" + rs.getString("numFollowers") + 
	         					 "\t" + rs.getInt("Comments.storyID") +
	         					 	"\t" + rs.getString("originalURL") + "\n"); 
	        } 
	        
	        rs.close();
	        stmt.close();
	        
	        return result;
	    }
	    catch (SQLException ex) 
	    {
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState:     " + ex.getSQLState());
	         System.out.println("VendorError:  " + ex.getErrorCode());
	         ex.printStackTrace();
	    }
		return null;
	}
	

	/**
	 * creates the user table using sql query
	 * prints to the user that the table has been created (or error thrown)
	 * runs the insertUploaderID method
	 */
	public void createUserTable() {
		try 
	    {	        
	        Statement stmt = conn1.createStatement();
	        /**drops the table if it is already created in order to create a new copy*/
	        stmt.execute("DROP TABLES IF EXISTS z5215332.knownUsers"); 
	        
	        /**creates the table in the z5215332 database*/
	        stmt.execute("CREATE TABLE z5215332.knownUsers (uploaderID varchar(15) "
	        		+ "UNIQUE, first_name VARCHAR(255), last_name varchar(255), age int, "
	        		+ "postcode int, numPosts int, gender CHAR(1), "
	        		+ "dateCreated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,"
	        		+ "email varchar(100) UNIQUE, PRIMARY KEY (uploaderID))"); 
	        
	        System.out.println("Created table in given database...");
	        
	        insertUploaderID();
	        
	        stmt.close();
	        
	    }
	    catch (SQLException ex) 
	    {
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState:     " + ex.getSQLState());
	         System.out.println("VendorError:  " + ex.getErrorCode());
	         ex.printStackTrace();
	    }
	}
	
	/**
	 * inserts the uploaderID's from Stories, Comments and Replies to the
	 * knownUsers table
	 * Print to the user that the uploaderID's have been inserted
	 */
	private void insertUploaderID(){
		try 
	    {	        
	        Statement stmt = conn1.createStatement();
	        
	        /**inserts all uploaderID's into the knownUsers table*/
	        stmt.executeUpdate("INSERT INTO z5215332.knownUsers(uploaderID) "
	        		+ "SELECT uploaderID AS uploaderID FROM dsr.Stories "
	        		+ "UNION SELECT Comments.userID AS uploaderID FROM dsr.Comments "
	        		+ "UNION SELECT Replies.userID AS uploaderID FROM dsr.Replies"); 
	        
	        System.out.println("Inserted uploaderID's..." + "\n");
	        
	        stmt.close();
	        
	    }
	    catch (SQLException ex) 
	    {
	         System.out.println("SQLException: " + ex.getMessage());
	         System.out.println("SQLState:     " + ex.getSQLState());
	         System.out.println("VendorError:  " + ex.getErrorCode());
	         ex.printStackTrace();
	    }
	}

	/**
	 * only used for testing the class without the user interface
	 * @param args
	 */
	public static void main(String[] args)
	{
		//For testing purposes
		DiggDBAccess db = new DiggDBAccess();
		db.printSimpleStatistics();
	}

}
