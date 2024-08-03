package busreserve;

// Import necessary classes
import java.util.Scanner;

// Main class for the bus reservation system
public class BusMain {
    public static void main(String[] args) {
        // Create an instance of BusDAO to interact with bus data
        BusDAO busdao = new BusDAO();

        try {
            // Display the bus information to the user
            busdao.displayBusInfo();

            // Initialize the user option variable to control the loop
            int userOpt = 1;

            // Create a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);

            // Loop to repeatedly ask the user for input until they choose to exit
            while (userOpt == 1) {
                // Prompt the user to enter 1 to book a bus or 2 to exit
                System.out.println("Enter 1 to Book and 2 to exit");

                // Check if the input is an integer
                if (scanner.hasNextInt()) {
                    // Read the integer input
                    userOpt = scanner.nextInt();
                    // Consume the newline character left by nextInt
                    scanner.nextLine();
                } else {
                    // Handle invalid input (not an integer)
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    // Clear the invalid input
                    scanner.nextLine();
                    // Continue to the next iteration of the loop
                    continue;
                }

                // If the user chooses to book (option 1)
                if (userOpt == 1) {
                    // Create a Booking object, passing the scanner to it
                    Booking booking = new Booking(scanner);
                    
                    // Check if the booking is available
                    if (booking.isAvailable()) {
                        // Create a BookingDAO instance to handle booking operations
                        BookingDAO bookingdao = new BookingDAO();
                        // Add the booking to the database or data store
                        bookingdao.addBooking(booking);
                        // Confirm the booking to the user
                        System.out.println("Your booking is confirmed");
                    } else {
                        // Notify the user that the bus is full
                        System.out.println("Sorry. Bus is full. Try another bus or date.");
                    }
                }
            }

            // Display a thank you message when the user exits
            System.out.println("Thank you for using the booking system!");
            // Close the scanner object to free up resources
            scanner.close();
        } catch (Exception e) {
            // Handle any exceptions that may occur
            System.out.println(e);
        }
    }
}
