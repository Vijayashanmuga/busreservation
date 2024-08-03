package busreserve;

// Import necessary classes
import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Class definition for Booking
public class Booking {
    // Instance variables for passenger name, bus number, and date
    String passengerName;
    int busNo;
    Date date;

    // Constructor for the Booking class, takes a Scanner object for input
    Booking(Scanner scanner) {
        // Prompt the user to enter the name of the passenger
        System.out.println("Enter name of passenger: ");
        // Read the full line of input for the passenger's name
        passengerName = scanner.nextLine(); // Changed to nextLine()

        // Validate bus number input with a loop to ensure it's an integer
        while (true) {
            System.out.println("Enter bus no: ");
            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                // Read the integer input for the bus number
                busNo = scanner.nextInt();
                // Consume the newline character left by nextInt()
                scanner.nextLine(); 
                // Exit the loop if input is valid
                break;
            } else {
                // Inform the user of invalid input and prompt again
                System.out.println("Invalid input. Please enter a valid bus number.");
                // Clear the invalid input
                scanner.nextLine();
            }
        }

        // Prompt the user to enter the date in dd-MM-yyyy format
        System.out.println("Enter date dd-MM-yyyy");
        String dateInput = scanner.nextLine();
        // Create a SimpleDateFormat object to parse the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            // Attempt to parse the date from the input string
            date = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            // Print stack trace if there's an error parsing the date
            e.printStackTrace();
        }
    }

    // Method to check if the booking is available
    public boolean isAvailable() throws SQLException {
        // Create instances of BusDAO and BookingDAO to interact with the database
        BusDAO busdao = new BusDAO();
        BookingDAO bookingdao = new BookingDAO();
        // Get the capacity of the bus
        int capacity = busdao.getCapacity(busNo);
        // Get the count of already booked seats for the given bus and date
        int booked = bookingdao.getBookedCount(busNo, date);
        // Return true if there are available seats, false otherwise
        return booked < capacity;
    }
}
