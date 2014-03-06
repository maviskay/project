import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Calendar;

public class NewVehicle{
	// Registers new vehicle
	public static void vehicleRegistration(Connection dbConn) {
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		PreparedStatement checkSerial;
		ResultSet serialCount;
		Scanner keyboard;
		String serialNum, maker, model, color;
		String query = "SELECT COUNT(serial_no) FROM vehicle WHERE serial_no = ?";
		int year, typeID;

		System.out.println("You have selected new vehicle registration");
		// Requests for serial number
		while (true) {
		    System.out.print("Please enter the vehicle's serial number: ");
			keyboard = new Scanner(System.in);
			serialNum = keyboard.nextLine();
			if (serialNum.length() != 15)
				System.out.println("Serial number invalid");
			else{
				try{
					checkSerial = dbConn.prepareStatement(query);
					checkSerial.setString(1, serialNum);
					serialCount = checkSerial.executeQuery();
					serialCount.next();
					if (serialCount.getInt(1) != 0){					
						serialCount.close();
						System.out.println("Vehicle already exists");
					} else
						break;
				} catch (SQLException e){
					System.out.println(e.getMessage());
				}
			}
		}
		// Requests for vehicle make
		while (true) {
			System.out.print("Please enter make of vehicle: ");
		        keyboard = new Scanner(System.in);
			maker = keyboard.nextLine();
			if (maker.length() > 20)
				System.out.println("Make of vehicle invalid");
			else
			    break;
		}
		// Requests for vehicle model
		while (true) {
		    System.out.print("Please enter model of vehicle: ");
		    keyboard = new Scanner(System.in);
		    model = keyboard.nextLine();
		    if (model.length() > 20)
				System.out.println("Model of vehicle invalid");
		    else
				break;
		}
		// Requests for vehicle year
		while (true) {
		    System.out.print("Please enter year of vehicle: ");
		    keyboard = new Scanner(System.in);
			try{
			    year = keyboard.nextInt();
				if (Integer.toString(year).length() != 4 || year > currYear)
					System.out.println("Year of vehicle invalid");
				else
					break;
			} catch (InputMismatchException e) {
			    System.out.println("Invalid year");
			    continue;
			}
		}
		// Requests for vehicle color
		while (true) {
			System.out.print("Please enter color of vehicle: ");
			keyboard = new Scanner(System.in);
		    color = keyboard.nextLine();
		    if (color.length() > 10)
				System.out.println("Color of vehicle invalid");
		    else
				break;
		}
		// Requests for vehicle type
		while (true) {
			typeID = checkVehicleType(dbConn);
			if (typeID == -1)
				System.out.println("Type of vehicle invalid");
			else
				System.out.println(typeID);
		}

	}
	// TODO: see if casing of string matters
	// Checks for vehicle type and returns type_id
	public static int checkVehicleType(Connection dbConn){
		PreparedStatement findType;
		ResultSet typeResult;
		Scanner keyboard;
		String type;
		String query = "SELECT type_id FROM vehicle_type WHERE type = ?";

		System.out.print("Please enter type of vehicle: ");
		keyboard = new Scanner(System.in);
		type = keyboard.nextLine();
		if (type.length() > 10)
			return -1;
		else{
			try{
				// Assume all vehicle types are already in database
				findType = dbConn.prepareStatement(query);
				System.out.println("type: " + type);
				findType.setString(1, type);
				typeResult = findType.executeQuery();
				while(typeResult.next()){
					int test = typeResult.getInt("type_id");
					System.out.println(test);
				}
				typeResult.close();
			} catch (SQLException e){
				System.out.println(e.getMessage());
			}
		}
		// Should never reach here
		return -1;
	}
}
