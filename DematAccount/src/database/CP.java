package database;
	import java.sql.Connection;
	import java.sql.DriverManager;

	public class CP {
			// create connection with those  steps
			
		static Connection con;
		public static Connection CreateC() {
			 //1. Load the driver 
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				
				String user = "root";
				String password = "Bhopal@786";
				String url = "jdbc:mysql://localhost:3306/dmat_manage";
				con = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}
}
