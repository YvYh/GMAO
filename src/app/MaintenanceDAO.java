package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAO {
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "bdd1"; //exemple BDD1
	final static String PASS = "bdd1"; //exemple BDD1

	public MaintenanceDAO() {
	try {
	Class.forName("oracle.jdbc.OracleDriver");
	} catch (ClassNotFoundException e) {
	System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
	}
	}
	
	public int ajouter(Maintenance maintenance) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO maintenance (maint_idEnt, maint_cont, maint_type, maint_duree, maint_etat, maint_idOp) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, maintenance.getIdEnt());
			ps.setString(2, maintenance.getcMaint());
			ps.setString(3, maintenance.getType());
			ps.setString(4, maintenance.getduree());
			ps.setInt(5, maintenance.getEtat());
			ps.setInt(6, maintenance.getidOp());
			retour = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
	}

	public Maintenance getMaintenance(int idMaint) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement pss = null;
		ResultSet rss = null;
		Maintenance retour = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM maintenance WHERE maint_id = ?");
			ps.setInt(1, idMaint);
			rs = ps.executeQuery();
			pss = con.prepareStatement("SELECT * FROM entreprise JOIN maintenance ON idEnt = maint_idEnt WHERE maint_id = ?");
			pss.setInt(1, idMaint);
			rss = pss.executeQuery();
			if (rs.next())
				retour = new Maintenance(rs.getInt("maint_ref"), rs.getString("maint_num"), rs.getString("maint_type"), rs.getString("maint_duree"), rs.getInt("maint_etat"), rs.getInt("maint_idOp"));
				if(rss.next()) {
					retour.setEntreprise(rss.getString("ent_nom"), rss.getInt("ent_nsiret"), rss.getString("ent_adresse"), rss.getString("ent_ape"));
				}
					
			} catch (Exception ee) {
			ee.printStackTrace();
			} 
		finally {
			try { if (rs != null) rs.close();} catch (Exception ignore) {}
			try { if (ps != null) ps.close();} catch (Exception ignore) {}
			try { if (con != null) con.close();} catch (Exception ignore) {}
			}
			return retour;
			}
	
	public List<Maintenance> getListeMaintenance() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Maintenance> retour = new ArrayList<Maintenance>();
		
		// connexion ÃƒÂ  la base de donnÃƒÂ©es
		try {
	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM maintenance");
	
			// on exÃƒÂ©cute la requÃƒÂªte
			rs = ps.executeQuery();
			// on parcourt les lignes du rÃƒÂ©sultat
			while (rs.next())
				retour.add(new Maintenance(rs.getInt("maint_id"), rs.getString("maint_num"), rs.getString("maint_type"), rs.getString("maint_duree")));
	
		}catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
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
		return retour;
	}

}


