import java.sql.*;
import java.util.Scanner;

// export CLASSPATH=$CLASSPATH\:.\:/oracle/jdbc/lib/classes12.zip

public class database {
	static Connection conn = null;

	public static void main(String[] argv) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String database ="jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
		String username;
		String password;

		// Obtains database username and password
		if (argv.length != 2){
			System.out.println("Enter oracle username followed by password");
			System.exit(0);
		}
		username = argv[0];
		password = argv[1];
		
		// Attempts to connect to Oracle database
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle JDBC Driver not found");
			return;
		}
		try{
			conn = DriverManager.getConnection(database, username, password);
		} catch (SQLException e) {
			System.out.println("Connection to Oracle failed");
			return;
		}
		if (conn != null)
			requestAction();
	}

	// Requests selection from user & ensures valid request
	private static void requestAction() {
		Scanner keyboard;
		int selection;
		
		//Requests selection from user
		System.out.println("Welcome to the auto registration system");
		while(true){
			System.out.println("\nPlease select from the following:");
			System.out.println("\t1: New Vehicle Registration");
			System.out.println("\t2: Auto Transaction");
			System.out.println("\t3: Driver License Registration");
			System.out.println("\t4: Violation Record");
			System.out.println("\t5: Search Engine");
			System.out.println("\t6: Exit");
			keyboard = new Scanner(System.in);
			selection = keyboard.nextInt();
			if (selection == 1)
				vehicleRegistration();
			else if (selection == 2)
				autoTransaction();
			else if (selection == 3)
				licenseRegistration();
			else if (selection == 4)
				violationRecord();
			else if (selection == 5)
				searchEngine();
			else if (selection == 6){
				System.out.println("Connection to database will now close.");
				try {
					conn.close();
					System.exit(0);
				} catch (SQLException e) {
					System.out.println("Could not close database connection");
				}
			}
			else
				System.out.println("Selection not valid, please try again");
		}
		
	}

	// Registers new vehicle to database
	private static void vehicleRegistration(){
		Scanner keyboard;
		String input;
		System.out.println("You have selected new vehicle registration");
		while(true){
			System.out.print("Please enter the vehicle's serial number: ");
			keyboard = new Scanner(System.in);
			input = keyboard.nextLine();
			if (input.length() != 15)
				System.out.println("Serial number invalid");
			else{
				System.out.print("Please enter make of vehicle: ");
				input = keyboard.nextLine();
				if (input.length() > 20)
					System.out.println("Make of vehicle invalid");
			}
			
		}
	}
	
	// Complete an auto transaction in database
	private static void autoTransaction(){
		System.out.println("You have selected auto transaction");
	}
	
	// Registers new driving license to database
	private static void licenseRegistration(){
		System.out.println("You have selected driver license registration");
	}
	
	// Issue ticket & record violation in database
	private static void violationRecord(){
		System.out.println("You have selected violation record");
	}
	
	// Searches the database
	private static void searchEngine(){
		System.out.println("You have selected search engine");
	}
}
