import java.sql.*;

public class PostgresDatabaseTest {
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/inventory", 
					"postgres", 
					"p4ssw0rd");
			
			if (connection != null) {
				System.out.println("Db connection succeeded");
				
				PreparedStatement preparedStatement = connection.prepareStatement("select * from inv_products");
				ResultSet result = preparedStatement.executeQuery();
				
				while(result.next())
				{
					System.out.println(result.getString("name"));
				}
			} else {
				System.out.println("Db connection failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
