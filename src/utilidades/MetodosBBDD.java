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
	
	/**
	 * Metodo que crea conexion a la base de datos
	 * Devuelve dicha una conexion
	 * @return
	 */
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
	
	/**
	 * Metodo que busca en las diferentes tablas si existe la palabra en ingles o español
	 * Si existen devuelve mensaje: "Traduccion ya existente"
	 * Si no existen devuelve mensaje: "Traduccion creada con exito"
	 * @param con
	 * @param palabra
	 * @param word
	 * @return
	 */
	public static String insertaPalabra(Connection con, String palabra, String word) {
		int existeEs = 0, existeEn = 0, existeEsEn = 0;
		String mensaje = "";
		PreparedStatement psEs = null, psEsInsert = null, psEn = null, psEnInsert = null, psEsEn = null, psEsEnInsert = null;
		ArrayList<String> todasPalabras = new ArrayList<String>();
		ArrayList<String> allWords = new ArrayList<String>();
		LinkedHashMap<String, String> palabrasWords = new LinkedHashMap<String, String>();
		
		try {
			//verificamos que existe la palabra en ESPAÑOL en la tabla es
			psEs = con.prepareStatement("SELECT * FROM es WHERE palabra_es like " + palabra);
			ResultSet rsEs = psEs.executeQuery();
			
			while (rsEs.next()) {
				if (!rsEs.getString("palabra_es").equals(palabra)) {//es decir que no existe
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
				if (!rsEn.getString("word_en").equals(word)) {//es decir que no existe
					psEnInsert = con.prepareStatement("insert into es values (?)");
					psEnInsert.setString(1, word);
					psEnInsert.executeUpdate();
				} else {
					existeEn++;
				}
			}
			
			//verificamos que existe la palabra en ESPAÑOL e INGLES en la tabla es_en
			psEsEn = con.prepareStatement("SELECT * FROM es_en WHERE palabra_es = " + palabra + " AND word_en = " + word);
			ResultSet rsEsEn = psEn.executeQuery();
			
			while (rsEsEn.next()) {
				if (!rsEsEn.getString("palabra_es").equals(palabra) && !rsEsEn.getString("word_en").equals(word)) {//es decir que no existe
					psEsEnInsert = con.prepareStatement("insert into es values (?)");
					psEsEnInsert.setString(1, palabra);
					psEsEnInsert.setString(2, word);
					psEsEnInsert.executeUpdate();
				} else {
					existeEsEn++;
				}
			}
			
			if(existeEs != 0 || existeEn != 0 || existeEsEn != 0) {
				//es que alguna insercion ha hecho
				mensaje = "Traduccion creada con exito";
			} else {
				mensaje = "Traduccion ya existente";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensaje;
	}//fin metodo insertaPalabra
	
}
