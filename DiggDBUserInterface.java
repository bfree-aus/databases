import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*************************************************
 * 
 * Description: user interface for a
 * data-mining application for Digg
 * social network data.
 * 
 * (DSR Assignment 2)
 * 
 * @author Kathryn Kasmarik
 * @date Feb 2019
 *
 *************************************************/
public class DiggDBUserInterface 
{
	// Main menu constants
	private final int CONTINUE = 0;
	private final int EXIT = 3;
	private final int TASK3 = 1;
	private final int TASK4 = 2;
	
	// The helper class that does the work
	// You must design this for Task 2
	private DiggDBAccess dbaccess;
	
	/**
	 * Constructor to create a new UI
	 */
	public DiggDBUserInterface()
	{
		dbaccess = new DiggDBAccess();
	}
	
	/**
	 * Method to run the UI until user
	 * chooses the exit option. You must
	 * test this for Task 5.
	 */
	public void runUI()
	{
		try
		{
			int option = CONTINUE;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			while (option != EXIT) 
			{
				System.out.println("WELCOME TO THE DIGG DATAMINING APPLICATION");
				System.out.println("Please select an option:");
				System.out.println("[1] Print simple statistics");
				System.out.println("[2] Create user table");
				System.out.println("[3] Exit");
				option = Integer.parseInt(in.readLine());
				
				switch(option)
				{
					case TASK3:
						// This branch will execute your answer for task 3
						System.out.println(dbaccess.printSimpleStatistics());
						break;
					case TASK4:
						// This branch will execute your answer for task 4
						dbaccess.createUserTable();
						break;
					case EXIT:
						break;
					default:
						System.out.println("Please enter a valid option.");
						break;
				}
				
			}
			
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	/** Main method to run the program */
	public static void main(String[] args)
	{
		DiggDBUserInterface ui = new DiggDBUserInterface();
		ui.runUI();
	}
}
