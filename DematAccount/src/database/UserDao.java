//IN THIS PACKAGE USERdAO I HAVE WRIITEN CODE TO CREATE A NEW ACCOUNT OR LOGIN TO A EXSISTING ACCOUNT
package database;

import java.io.ObjectInputStream.GetField;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import information.AccountCreate;
import information.AddMoney;
import information.DisplayDetails;
import information.LoginAccount;
import information.UserId;
import shareInfo.ShareDetails;

public class UserDao {
// This fuction can create new account 

	public static boolean createAccToDB(AccountCreate ac) {
		boolean f = false;
		try {
			// Creating a connection
			Connection con = CP.CreateC();
			String q = "insert into user_details(account_number, user_name,money )values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);

			// filling our My SQL data base with these details mention below
			pstmt.setInt(1, ac.getAccountNumber());
			pstmt.setString(2, ac.getUserName());
			pstmt.setDouble(3, ac.getMoney());

			// execute
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int generatedKey = rs.getInt(1);
				System.out.println("Account created successfully");
				System.out.println("Your user ID is: " + generatedKey);
			}
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	// This function is to Login to an existing account

	public static boolean loginToDB(LoginAccount la) {

		// this fuction is to deplay the user details like shares account money etc

		boolean f = false;
		try {

			// Creating a connection
			Connection con = CP.CreateC();
			String q = "SELECT * FROM user_details WHERE account_number = ?";
			PreparedStatement pstmt = con.prepareStatement(q);

			// filling our My SQL data base with these details mention below
			pstmt.setInt(1, la.getAccountNumber());

			ResultSet resultSet = null;

			// execute
			resultSet = pstmt.executeQuery();
			f = true;
			if (resultSet.next()) {
				System.out.println("User_id : " + resultSet.getString("id"));

				System.out.println("Login successful!");
			} else {
				System.out.println("Login failed. Incorrect account number.");
				System.out.println("Please press 0 ans try and Try again with correct Account Number2");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public static void displayDetails(DisplayDetails dd) {
		try {
			PreparedStatement pstmt = null;
			// Creating a connection
			Connection con = CP.CreateC();

			pstmt = con.prepareStatement("SELECT * FROM user_details WHERE account_number = ?");

			// filling our My SQL data base with these details mention below

			Scanner s = new Scanner(System.in);
			System.out.println("Enter ReEnter  your account number to confrim:");
			int accountNumber = s.nextInt();

			pstmt.setInt(1, accountNumber);
			ResultSet rs1 = null;
			rs1 = pstmt.executeQuery();

			if (rs1.next()) {
				
				System.out.println("User_id : " + rs1.getString("id"));
				System.out.println("UserName : " + rs1.getString("user_name"));
				System.out.println("Account Number : " + rs1.getInt("account_number"));
				System.out.println("Money : " + rs1.getDouble("money"));
				System.out.println();

				// Prepare the SELECT statement for the trasaction_details table

				String query = "SELECT transaction_details.user_id, transaction_details.share_id, transaction_details.number_of_shares, transaction_details.value_of_share, share_details.share_name "
						+ "FROM transaction_details " + "INNER JOIN share_details "
						+ "ON transaction_details.share_id = share_details.id "
						+ "WHERE transaction_details.user_id = ?";

				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, rs1.getInt("id"));
				ResultSet rs2 = pstmt.executeQuery();

				// Display the share details if the user holds any shares
				while (rs2.next()) {
					System.out.println("Share Name: " + rs2.getString("share_name"));
					System.out.println("Number of Shares: " + rs2.getInt("number_of_shares"));
					System.out.println("Value of Each Share: " + rs2.getDouble("value_of_share"));
					System.out.println("++++++++++++++++++++++++++");
				}
			} else {
				System.out.println("Account not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean addMoneytoDB(int accountNumber, double depositAmount) {
		boolean f = false;
		try {
			// Creating a connection
			Connection con = CP.CreateC();
			String q = "UPDATE user_details SET money = money + ? WHERE account_number = ?";
			PreparedStatement pstmt = con.prepareStatement(q);

			// filling our My SQL data base with these details mention below

			pstmt.setDouble(1, depositAmount);
			pstmt.setInt(2, accountNumber);

			pstmt.executeUpdate();
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public static boolean withdrawMoneyFromDB(int accountNumber, double withdrawalAmount) {
		boolean success = false;
		try {
			// Connect to the database
			Connection con = CP.CreateC();

			// Query to retrieve the current balance of the user
			String q1 = "SELECT money FROM user_details WHERE account_number = ?";
			PreparedStatement pstmt1 = con.prepareStatement(q1);
			pstmt1.setInt(1, accountNumber);

			ResultSet rs = pstmt1.executeQuery();
			if (rs.next()) {
				double currentBalance = rs.getDouble("money");

				// Check if the withdrawal amount is greater than the current balance
				if (withdrawalAmount > currentBalance) {
					System.out.println("Error: Insufficient balance");
					return success;
				}
				// Query to update the balance in the database
				String q2 = "UPDATE user_details SET money = money - ? WHERE account_number = ?";
				PreparedStatement pstmt2 = con.prepareStatement(q2);

				// Set the withdrawal amount and account number in the query
				pstmt2.setDouble(1, withdrawalAmount);
				pstmt2.setInt(2, accountNumber);

				// Execute the update query
				int result = pstmt2.executeUpdate();

				// If the query is executed successfully, return true
				if (result > 0) {
					success = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public static String BuyTransactionFromDB(int userId, int shareId, int numberOfShares) throws SQLException {
		Connection con = CP.CreateC();
		final double TRANSACTION_CHARGE_PERCENTAGE = 0.005;
		final double STT_PERCENTAGE = 0.001;
		final int MIN_TRANSACTION_CHARGE = 100;

		Statement stmt = con.createStatement();
		con.setAutoCommit(false);
		// boolean f = false;
		try {
			// All the Shares Listed in our application

			// Get the value of one share from the share_details table
			ResultSet rs = stmt.executeQuery("SELECT value_of_share FROM share_details WHERE id = " + shareId);
			if (!rs.next()) {
				throw new SQLException("Share with id " + shareId + " not found");
			}
			BigDecimal valueOfShare = rs.getBigDecimal("value_of_share");
			BigDecimal totalValue = valueOfShare.multiply(BigDecimal.valueOf(numberOfShares));

			// Check if the user has sufficient funds

			rs = stmt.executeQuery("SELECT money FROM user_details WHERE id = " + userId);
			if (!rs.next()) {
				throw new SQLException("User with id " + userId + " not found");
			}

			BigDecimal availableBalance = rs.getBigDecimal("money");
			
			if (totalValue.compareTo(availableBalance) > 0) {
				System.out.println("Not enough Funds");
				return "Not enough funds";
			}

			// Calculate the transaction charge
			BigDecimal transactionCharge = BigDecimal.valueOf(MIN_TRANSACTION_CHARGE)
					.multiply(BigDecimal.valueOf(numberOfShares));

			if (transactionCharge.intValue() < MIN_TRANSACTION_CHARGE) {
				availableBalance = availableBalance.subtract(BigDecimal.valueOf(MIN_TRANSACTION_CHARGE));
			} else {
				availableBalance = availableBalance.subtract(transactionCharge);
			}

			// Deduct the value of shares being bought
			availableBalance = availableBalance.subtract(totalValue);

			// Update the user's balance
			
			// Deduct the transaction charge and STT from the total value
			BigDecimal totalDeduction = transactionCharge.add(totalValue.multiply(BigDecimal.valueOf(STT_PERCENTAGE)));
			totalValue = totalValue.subtract(totalDeduction);
			
			int totalShares = 0;
			ResultSet result = stmt.executeQuery("SELECT SUM(number_of_shares) as total_shares FROM transaction_details WHERE user_id = " + userId);
			if (result.next()) {
			    totalShares = result.getInt("total_shares");
			}

			stmt.executeUpdate("UPDATE user_details SET money = " + availableBalance + " WHERE id = " + userId);
			stmt.executeUpdate("UPDATE user_details SET number_of_shares = " + (totalShares + numberOfShares) + ", share_id = " + shareId + " WHERE id = " + userId);




			// Add a new entry to the transaction_details table
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO transaction_details (user_id, share_id, date_of_transaction, number_of_shares, value_of_share, transaction_charge) VALUES (?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, userId);
			pstmt.setInt(2, shareId);
			pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(4, numberOfShares);
			pstmt.setBigDecimal(5, valueOfShare);
			pstmt.setBigDecimal(6, transactionCharge);
			pstmt.executeUpdate();

			// f = true;
			// Update the share_details table to reflect the new number of shares
			stmt.executeUpdate("UPDATE share_details SET total_shares = total_shares - " + numberOfShares
					+ " WHERE id = " + shareId);

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return "";
	}

	public static boolean SellTransactionFromDB(int userId, int shareId, int numberOfShares) throws SQLException {

		Connection con = CP.CreateC();
		final double TRANSACTION_CHARGE_PERCENTAGE = 0.005;
		final double STT_PERCENTAGE = 0.001;
		final int MIN_TRANSACTION_CHARGE = 100;

		Statement stmt = con.createStatement();
		con.setAutoCommit(false);
		boolean f = false;
		try {
			// Get the value of one share from the share_details table
			ResultSet rs = stmt.executeQuery("SELECT value_of_share FROM share_details WHERE id = " + shareId);
			if (!rs.next()) {
				throw new SQLException("Share with id " + shareId + " not found");
			}
			BigDecimal valueOfShare = rs.getBigDecimal("value_of_share");
			BigDecimal totalValue = valueOfShare.multiply(BigDecimal.valueOf(numberOfShares));

			// Calculate the transaction charge
			BigDecimal transactionCharge = totalValue.multiply(BigDecimal.valueOf(TRANSACTION_CHARGE_PERCENTAGE));
			if (transactionCharge.intValue() < MIN_TRANSACTION_CHARGE) {
				transactionCharge = BigDecimal.valueOf(MIN_TRANSACTION_CHARGE);
			}
			// Deduct the transaction charge and STT from the total value
			BigDecimal totalDeduction = transactionCharge.add(totalValue.multiply(BigDecimal.valueOf(STT_PERCENTAGE)));
			totalValue = totalValue.subtract(totalDeduction);
			
			// Check if the user has enough shares to sell
			ResultSet result = stmt.executeQuery("SELECT number_of_shares FROM user_details WHERE id = " + userId + " AND share_id = " + shareId);
			
			int currentShares = 0;
			if (result.next()) {
				currentShares = result.getInt("number_of_shares");
			}
			if (currentShares >= numberOfShares) {
				// Update the user_details table to reflect the new balance and number of shares
				stmt.executeUpdate("UPDATE user_details SET money = money + " + totalValue + ", number_of_shares = number_of_shares - " + numberOfShares
								+ " WHERE id = " + userId + " AND share_id = " + shareId);

				// Update the share_details table to reflect the new total shares
				stmt.executeUpdate("UPDATE share_details SET total_shares = total_shares - " + numberOfShares
								+ " WHERE id = " + shareId);
			// Update the user_details table to reflect the new balance
			//stmt.executeUpdate("UPDATE user_details SET money = money - " + totalValue + " WHERE id = " + userId);

			// Add a new entry to the transaction_details table
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO transaction_details (user_id, share_id, date_of_transaction, number_of_shares, value_of_share, transaction_charge) VALUES (?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, userId);
			pstmt.setInt(2, shareId);
			pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(4, numberOfShares);
			pstmt.setBigDecimal(5, valueOfShare);
			pstmt.setBigDecimal(6, transactionCharge);
			pstmt.executeUpdate();
			f = true;
			System.out.println("Sell Transaction completed successfully.");

			} else {
			    System.out.println("Error: User does not have enough shares to sell");
			}
			con.commit();

		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return f;

	}

	
}


