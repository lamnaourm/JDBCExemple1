import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.PreparedStatement;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		// Chargement du driver
		Properties props = new Properties();
		props.load(new FileInputStream("database.properties"));
		
		Class.forName(props.getProperty("driver"));
		
		// Les informations de la connexion
		String url = props.getProperty("url");
		String user = props.getProperty("user");
		String pwd = props.getProperty("pwd");
				
		// Creation de connection 
		Connection conn = DriverManager.getConnection(url,user,pwd);
		
		Statement st = conn.createStatement();
		
		ResultSet rst = st.executeQuery("SELECT * FROM EMPLOYE");
				
		while(rst.next()) {
			System.out.println("---------------------------------------------");
			System.out.println(rst.getInt(1));
			System.out.println(rst.getString(2));
			System.out.println(rst.getString(3));
			System.out.println(rst.getDouble(4));
			System.out.println("---------------------------------------------");
		}
		
		int res = st.executeUpdate("INSERT INTO EMPLOYE VALUES (null,'ISMO11','OFFSHORE',1234.67)");
		
		
		// Exemple PREPARED STATEMENT
		PreparedStatement r = conn.prepareStatement("SELECT * FROM EMPLOYE WHERE ID = ?");
		r.setInt(1, 3);
		
		ResultSet rst1 = r.executeQuery();
		
		while(rst1.next()) {
			System.out.println("---------------------------------------------");
			System.out.println(rst1.getInt(1));
			System.out.println(rst1.getString(2));
			System.out.println(rst1.getString(3));
			System.out.println(rst1.getDouble(4));
			System.out.println("---------------------------------------------");
		}
		// fermeture de connexion
		conn.close();
		st.close();
	}

}
