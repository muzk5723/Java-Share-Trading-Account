import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import database.ShareDao;
import database.TransactionDao;
import database.UserDao;
import information.AccountCreate;
import information.AddMoney;
import information.DisplayDetails;
import information.LoginAccount;
import information.UserId;

public class Start {

	public static void main(String[] args) {

		System.out.println("Welcome to Dmat App ");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Press 1 to Create New Account");
			System.out.println("Press 2 to LOGIN");
			System.out.println("Press 0 to EXIT");

			// Now asking user to select one option
			int c = sc.nextInt();
			if (c == 1) {
				System.out.println("Account number");
				int accno = sc.nextInt();

				System.out.println("Username");
				String uname = sc.next();

				System.out.println("Money you want to add in your account");
				double money = sc.nextDouble();
				AccountCreate ac = new AccountCreate(accno, uname, money);
				boolean ans = UserDao.createAccToDB(ac);
				
			} else if (c == 2) {
				// remaining options
				// 0 – Quit
				// 1 – Display Demat account details
				// 2 – Deposit Money
				// 3 – Withdraw Money
				// 4 – Buy transaction
				// 5 – Sell transaction
				// 6 – View transaction report

				System.out.println("Account number ");
				int acc = sc.nextInt();

				LoginAccount la = new LoginAccount(acc);
				boolean ans = UserDao.loginToDB(la);
				if (ans) {
					while (true) {
						System.out.println("Press 1 to Display Demat account details");
						System.out.println("Press 2 Deposit Money");
						System.out.println("Press 3 Withdraw Money");
						System.out.println("Press 4 Buy transaction");
						System.out.println("Press 5 Sell transaction");
						System.out.println("Press 6 View transaction report");		
						System.out.println("Press 0 to Go to the previos Menu");
						

						// Taking input from user to Select one of these options
						int s = sc.nextInt();
						if (s == 1) {
							System.out.println("Please enter accont number");
							int accId = sc.nextInt();
							// Display
							DisplayDetails dd = new DisplayDetails(accId);
							UserDao.displayDetails(dd);

						} else if (s == 2) {
							// deposit
							System.out.println("Enter the amount you want to deposit");
							double money = sc.nextDouble();
							System.out.println("Please confirm your account number ");
							int accno = sc.nextInt();
							UserDao ud = new UserDao();
							boolean a = UserDao.addMoneytoDB(accno, money);
							if (a) {
								System.out.println("Deposite Successfuly");
							}

						} else if (s == 3) {
							// Withdraw
							Scanner a = new Scanner(System.in);
							System.out.println("Please Enter the Amount you want to Withdraw");
							double withdrawalAmount = a.nextDouble();
							System.out.println("Please confirm your account number");
							int accno = a.nextInt();

							UserDao ud = new UserDao();
							boolean success = UserDao.withdrawMoneyFromDB(accno, withdrawalAmount);
							if (success) {
								System.out.println("Withdrawal successful");
							} else {
								System.out.println("Withdrawal failed");
							}
						} else if (s == 4) {
							ShareDao.showShareDetailsFromDB();
							// Buy
							// ShareDao.showShareDetailsFromDB(); // this method shows all the sharename and
							// its data
							System.out.println("Please enter userId");
							int userId = sc.nextInt();
							System.out.println("Please enter shareId");
							int shareId = sc.nextInt();
							System.out.println("Please enter how many shares you want to buy");
							int numberOfShares = sc.nextInt();
							try {
								String anss = UserDao.BuyTransactionFromDB(userId, shareId, numberOfShares);
								if (anss != null) {
									System.out.println("Transaction SUCCESSFUL");
								} else {
									System.out.println("Something Went Wrong");
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else if (s == 5) {
							// sell
							System.out.println("Please enter userId");
							int userId = sc.nextInt();
							System.out.println("Please enter shareId");
							int shareId = sc.nextInt();
							System.out.println("Please enter how many shares you want to sell");
							int numberOfShares = sc.nextInt();
							try {
								boolean anss = UserDao.SellTransactionFromDB(userId, shareId, numberOfShares);
								if (anss) {
									System.out.println("Transaction SUCCESSFUL");
								} else {
									System.out.println("Something Went Wrong");
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ShareDao.showShareDetailsAfterTrasactionFromDB();
						} else if (s == 6) {
							
							System.out.println("Press 1 to Print Report As per Date ");
							System.out.println("Press 2 to Print Report As per Share Id ");
							
							int w = sc.nextInt();
							if(w==1) {
							// View Tracaction as per dates 
							try {
								TransactionDao.viewTransactionsBetweenDates();
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}
							else if(w==2) {
								// View Tracaction as per Share Id  
								try {
									TransactionDao.viewUserIdTransFromDB();
								} catch (ClassNotFoundException | SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						} else if (s == 0) {
							break;
						} else {
							System.out.println("Please choose the correct option\n");
						}
					}
				} else {
					System.out.println("Please Enter correct account number");
				}
			} else if (c == 0) {
				break;
			} else {
				System.out.println("Please Enter correct account number");

			}

		}
		System.out.println("Thank you for using Dmat APP");
	}

}
