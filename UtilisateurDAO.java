package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtilisateurDAO {
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "bdd1"; //exemple BDD1
	final static String PASS = "bdd1"; //exemple BDD1

	public UtilisateurDAO() {
	try {
	Class.forName("oracle.jdbc.OracleDriver");
	} catch (ClassNotFoundException e) {
	System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
	}
	}
	
	public int ajouter(Utilisateur utilisateur) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO utilisateur (util_nom, util_prenom, util_mdp, util_etat, util_id) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, utilisateur.getNom());
			ps.setString(2, utilisateur.getPrenom());
			ps.setString(3, utilisateur.getMotDePasse());
			ps.setInt(4, utilisateur.getEtat());
			ps.setInt(5, utilisateur.getID());
			retour = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (con != null) con.close();} catch (Exception ignore) {}
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
		}
		return retour;
	}

	public Utilisateur getUtilisateur(int idUtil) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Utilisateur retour = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM maintenance WHERE maint_id = ?");
			ps.setInt(1, idUtil);
			rs = ps.executeQuery();
			if (rs.next())
				retour = new Utilisateur(rs.getString("util_nom"), rs.getString("util_prenom"), rs.getString("util_mdp"), rs.getInt("util_etat"), rs.getInt("util_id"));
			} 
		catch (Exception ee) {
			ee.printStackTrace();
			} 
		finally {
			try { if (rs != null) rs.close();} catch (Exception ignore) {}
			try { if (ps != null) ps.close();} catch (Exception ignore) {}
			try { if (con != null) con.close();} catch (Exception ignore) {}
			}
			return retour;
			}

}