package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import information.AccountCreate;
import information.DisplayDetails;

public class ShareDao {
	 public static void showShareDetailsFromDB()
	 {
		 try {
		   		PreparedStatement stmt = null;
		   		ResultSet rs = null;
				// Creating a connection 
				Connection con = CP.CreateC();
			      String query = "SELECT share_name, total_shares, value_of_share , id FROM share_details";
			      stmt = con.prepareStatement(query);
			      rs = stmt.executeQuery();
			      
			      while (rs.next()) {
			          String shareName = rs.getString("share_name");
			          int totalShares = rs.getInt("total_shares");
			          double valueOfShare = rs.getDouble("value_of_share");
			          int shareId = rs.getInt("id");
			          
			          System.out.println("Share Name: " + shareName);
			          System.out.println("Total Shares: " + totalShares);
			          System.out.println("Value of Share: " + valueOfShare);
			          System.out.println("Share Id: " +shareId);
			          System.out.println("-------------------");
			      }
			      
	 }catch(Exception e) {
			e.printStackTrace();

	 }
}
	
	 
	 public static void showShareDetailsAfterTrasactionFromDB()
	 {
		 try {
		   		PreparedStatement stmt = null;
		   		ResultSet rs = null;
				// Creating a connection 
				Connection con = CP.CreateC();
			      String query = "SELECT share_name, total_shares, value_of_share , id FROM share_details";
			      stmt = con.prepareStatement(query);
			      rs = stmt.executeQuery();
			      
			      while (rs.next()) {
			          String shareName = rs.getString("share_name");
			          int totalshares = rs.getInt("total_shares");
			          double valueOfShare = rs.getDouble("value_of_share");
			          int shareId = rs.getInt("id");
			          
			          System.out.println("Share Name: " + shareName);
			          System.out.println("Total Shares You Can Purchase: " + totalshares);
			          System.out.println("Value of Share: " + valueOfShare);
			          System.out.println("Share Id: " +shareId);
			          System.out.println("-------------------");
			      }
			      
	 }catch(Exception e) {
			e.printStackTrace();

	 }
}
	 
}