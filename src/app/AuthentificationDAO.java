package app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AuthentificationDAO {
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "bdd1";  //exemple BDD1
	final static String PASS = "bdd1";   //exemple BDD1

	/**
	 * Constructeur de la classe
	 * 
	 */
	public AuthentificationDAO() {
		// chargement du pilote de bases de données
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err
					.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
	
	public Utilisateur Connecter(String id, String motDePasse){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Utilisateur u = null;

		// connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur_uti WHERE util_id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			// passe à la première (et unique) ligne retournée
			if (rs.next())
				u = new Utilisateur(rs.getString("util_nom"),
						rs.getString("util_prenom"),
						rs.getString("util_mdp"),
						rs.getInt("util_etat"),
						rs.getInt("util_id"));
			
			if (motDePasse.equals(u.getMotDePasse())==false)
				u=null;
			

		}catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return u;
	}

}
	
