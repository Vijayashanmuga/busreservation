package busreserve;

import java.util.Date;
import java.sql.*;

// This class handles database operations related to bus bookings.
public class BookingDAO {

    // Method to get the count of booked passengers for a specific bus on a specific date
    public int getBookedCount(int busNo, Date date) throws SQLException {
        
        // SQL query to count the number of passengers for a given bus number and travel date
        String query = "select count(passenger_name) from booking where bus_no=? and travel_date=?";
        
        // Establishing a connection to the database using DbConnection class
        Connection con = DbConnection.getConnection();
        
        // Creating a PreparedStatement to execute the SQL query
        PreparedStatement pst = con.prepareStatement(query);
        
        // Convert the java.util.Date to java.sql.Date for database compatibility
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        
        // Setting the parameters in the query
        pst.setInt(1, busNo);    // Set the bus number parameter
        pst.setDate(2, sqldate); // Set the travel date parameter
        
        // Execute the query and retrieve the result set
        ResultSet rs = pst.executeQuery();
        
        // Move the cursor to the first row of the result set
        rs.next();
        
        // Return the count of booked passengers (the value of the first column in the result set)
        return rs.getInt(1);
    }

    // Method to add a new booking to the database
    public void addBooking(Booking booking) throws SQLException {
        
        // SQL query to insert a new booking into the booking table
        String query = "Insert into booking values(?,?,?)";
        
        // Establishing a connection to the database using DbConnection class
        Connection con = DbConnection.getConnection();
        
        // Convert the java.util.Date to java.sql.Date for database compatibility
        java.sql.Date sqldate = new java.sql.Date(booking.date.getTime());
        
        // Creating a PreparedStatement to execute the SQL query
        PreparedStatement pst = con.prepareStatement(query);
        
        // Setting the parameters in the query
        pst.setString(1, booking.passengerName); // Set the passenger name parameter
        pst.setInt(2, booking.busNo);            // Set the bus number parameter
        pst.setDate(3, sqldate);                 // Set the travel date parameter
        
        // Execute the update query to insert the booking
        pst.executeUpdate();
    }
}
