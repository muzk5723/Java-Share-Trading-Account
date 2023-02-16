package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionDao {

	public static void viewTransactionsBetweenDates() throws SQLException, ClassNotFoundException {
		Connection con = CP.CreateC();
		Scanner sc = new Scanner(System.in);
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// View report of all transactions between a given date range
			System.out.println("Enter start date (yyyy-mm-dd):");
			String startDate = sc.nextLine();
			System.out.println("Enter end date (yyyy-mm-dd):");
			String endDate = sc.nextLine();

			String query = "SELECT * FROM transaction_details WHERE date_of_transaction BETWEEN '" + startDate
					+ "' AND '" + endDate + "'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			System.out.println("Transaction Report between " + startDate + " and " + endDate + ":");
			System.out.println(
					"User ID | Share ID | Date of Transaction | Number of Shares | Value of Share | Transaction Charge");

			while (rs.next()) {
				int userId = rs.getInt("user_id");
				int shareId = rs.getInt("share_id");
				String date = rs.getString("date_of_transaction");
				int numberOfShares = rs.getInt("number_of_shares");
				double valueOfShare = rs.getDouble("value_of_share");
				double transactionCharge = rs.getDouble("transaction_charge");

				System.out.println(userId + " | " + shareId + " | " + date + " | " + numberOfShares + " | "
						+ valueOfShare + " | " + transactionCharge);
			}
		} catch (SQLException e) {
			System.out.println("Error: unable to connect to the database!");
			e.printStackTrace();
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	private static void closeResources(ResultSet rs, Statement stmt, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Error: unable to close resources!");

		}
	}

	public static void viewUserIdTransFromDB() throws SQLException, ClassNotFoundException {
		Connection con = CP.CreateC();
		Scanner sc = new Scanner(System.in);
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// View report of transactions for a given share ID
			System.out.println("Enter share ID:");
			int shareId = sc.nextInt();

			String query = "SELECT * FROM transaction_details WHERE share_id = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, shareId);
			rs = pstmt.executeQuery();

			System.out.println("Transaction Report for Share ID " + shareId + ":");
			System.out
					.println("User ID | Date of Transaction | Number of Shares | Value of Share | Transaction Charge");

			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String date = rs.getString("date_of_transaction");
				int numberOfShares = rs.getInt("number_of_shares");
				double valueOfShare = rs.getDouble("value_of_share");
				double transactionCharge = rs.getDouble("transaction_charge");

				System.out.println(userId + " | " + date + " | " + numberOfShares + " | " + valueOfShare + " | "
						+ transactionCharge);
			}
		} catch (SQLException e) {
			System.out.println("Error: unable to connect to the database or execute the query!");
			e.printStackTrace();
		}
	}

}
