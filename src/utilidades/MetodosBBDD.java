package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;



public class MetodosBBDD {
	
	public static final String URL = "jdbc:mysql://localhost:3306/diccionario";
	public static final String USER = "root";
	public static final String PASS = "root";
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	} // fin del metodo que crea la conexion a BBDD
	
	
	public static void insertaPalabra(Connection con, String palabra, String word) {
		int existeEs = 0, existeEn = 0, existeEsEn = 0;
		
		PreparedStatement psEs = null, psEsInsert = null, psEn = null, psEnInsert = null;
		ArrayList<String> todasPalabras = new ArrayList<String>();
		ArrayList<String> allWords = new ArrayList<String>();
		LinkedHashMap<String, String> palabrasWords = new LinkedHashMap<String, String>();
		
		try {
			//verificamos que existe la palabra en ESPAÑOL en la tabla es
			psEs = con.prepareStatement("SELECT * FROM es WHERE palabra_es = " + palabra);
			ResultSet rsEs = psEs.executeQuery();
			
			while (rsEs.next()) {
				if (!rsEs.getString("palabra").equals(palabra)) {//es decir que no existe
					psEsInsert = con.prepareStatement("insert into es values (?)");
					psEsInsert.setString(1, palabra);
					psEsInsert.executeUpdate();
				} else {
					existeEs++;
				}
			}
			
			//verificamos que existe la palabra en INGLES en la tabla en
			psEn = con.prepareStatement("SELECT * FROM en WHERE word_en = " + word);
			ResultSet rsEn = psEn.executeQuery();
			
			while (rsEn.next()) {
				if (!rsEn.getString("palabra").equals(word)) {//es decir que no existe
					psEnInsert = con.prepareStatement("insert into es values (?)");
					psEnInsert.setString(1, word);
					psEnInsert.executeUpdate();
				} else {
					existeEs++;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}//fin metodo insertaPalabra
	
}
