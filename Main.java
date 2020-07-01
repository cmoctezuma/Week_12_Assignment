package dominoesToGo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	private static Connection conn;

	public static void main(String[] args) { // within this method, connection to the database is made
		System.out.println("Hello World");

		final String connectionStr = "jdbc:mysql://127.0.0.1:3306/groceryList?serverTimezone=UTC";

		try {
			conn = DriverManager.getConnection(connectionStr, "root", "Mango1279!");
			System.out.println("Succesfully connected!");

		} catch (SQLException e) {
			System.out.println("Unable to connect to database");
			e.printStackTrace();
		}

		System.out.println("\n");
		System.out.println("Welcome!");

		// method to show Menu;
	}

	public static void createProfile(String customer_name, String address, String phone_number) {
		// this method creates a profile for the customer. A customer id is
		// auto-generated. The customer will need to
		// enter their data(name address, phone number)

		final String createProfileQuery = "INSERT INTO customer (customer_name, address, phone_number) VALUES (?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(createProfileQuery);
			ps.setString(1, customer_name);
			ps.setString(2, address);
			ps.setString(3, phone_number);

			ps.executeUpdate();

			System.out.println("Profile created successfully! Your ID #: ");
			getCustomerId(customer_name);

		} catch (SQLException e) {
			System.out.println("Error in createProfile query");
			e.printStackTrace();
		}
	}

	public static void getCustomerId(String customer_name) { // this method will get a customer's id #.
		// The customer can input their name and the method will return the customer Id
		// so that they can
		// place their order.
		final String getCustomerIdQuery = "Select id FROM customer WHERE customer_name = ?";

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(getCustomerIdQuery);

			System.out.println(rs.getInt("id"));

		} catch (SQLException e) {
			System.out.println("Error in getCustomerId query");
			e.printStackTrace();
		}
	}

	public static void addItem(String item, int quantity) {
		// This method will add items to the order. This should be on a continuous loop
		// until customer
		// states order is complete.
		final String addItemQuery = "INSERT INTO customer_order(item, quantity) VALUE (?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(addItemQuery);
			ps.setString(1, item);
			ps.setInt(2, quantity);

			ps.executeUpdate();

			System.out.println(quantity + " " + item + "('s) added successfully!");

		} catch (SQLException e) {
			System.out.println("Error in add item query");
			e.printStackTrace();
		}
	}

}
