package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OperateurDAO {
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "bdd1"; //exemple BDD1
	final static String PASS = "bdd1"; //exemple BDD1

	public OperateurDAO() {
	try {
	Class.forName("oracle.jdbc.OracleDriver");
	} catch (ClassNotFoundException e) {
	System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
	}
	}
	
	@SuppressWarnings("resource")
	public int ajouter(Operateur op) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO utilisateur (util_nom, util_prenom, util_mdp, util_etat, util_id) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, op.getNom());
			ps.setString(2, op.getPrenom());
			ps.setString(3, op.getMotDePasse());
			ps.setInt(4, op.getEtat());
			ps.setInt(5, op.getID());
			retour = ps.executeUpdate();
			ps = con.prepareStatement("INSERT INTO operateur (op_id, op_adr, op_tel) VALUES (?, ?, ?)");
			ps.setInt(1, op.getID());
			ps.setString(2, op.getAdresse());
			ps.setString(3, op.getTel());
			retour = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (con != null) con.close();} catch (Exception ignore) {}
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
		}
		return retour;
	}

	public Operateur getOperateur(int idOp) {
		Connection con = null;
		PreparedStatement ps=null, ps1 = null;
		ResultSet rs=null, rs1 = null;
		Operateur retour = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur WHERE util_id = ?");
			ps.setInt(1, idOp);
			rs = ps.executeQuery();
			if (rs.next())
			{
				retour = new Operateur(rs.getString("util_nom"), rs.getString("util_prenom"), rs.getString("util_mdp"), rs.getInt("util_etat"), rs.getInt("util_id"));
				ps1 = con.prepareStatement("SELECT * FROM operateur WHERE op_id = ?");
				ps1.setInt(1, idOp);
				rs1 = ps1.executeQuery();
				retour.setAdresse(rs1.getString("op_adr"));
				retour.setTel(rs1.getString("op_tel"));
				
				
			}
		}
		catch (Exception ee) {
			ee.printStackTrace();
			} 
		finally {
			try { if (rs != null) rs.close();} catch (Exception ignore) {}
			try { if (ps != null) ps.close();} catch (Exception ignore) {}
			try { if (rs1 != null) rs1.close();} catch (Exception ignore) {}
			try { if (ps1 != null) ps1.close();} catch (Exception ignore) {}
			try { if (con != null) con.close();} catch (Exception ignore) {}
			}
			return retour;
			}

}
