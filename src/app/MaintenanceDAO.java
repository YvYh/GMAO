package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Maintenance DAO
 * @author CL
 *
 */
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
	
	/**
	 * permet d'ajouter une maintenance
	 * @param maintenance ¨¤ ajouter
	 * @return 1 s'il r¨¦ussit
	 */
	public int ajouter(Maintenance maintenance) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO maintenance (maint_id, maint_cont, maint_type, maint_duree, maint_etat, maint_idOp, maint_idEnt) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, maintenance.getId());
			ps.setString(2, maintenance.getcMaint());
			ps.setString(3, maintenance.getType());
			ps.setString(4, maintenance.getduree());
			ps.setInt(5, maintenance.getEtat());
			ps.setInt(6, maintenance.getidOp());
			ps.setInt(7, maintenance.getIdEnt());
			retour = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
	}

	/**
	 * permet de copier une maintenance depuis BDD d'apr¨¨s son id
	 * @param idMaint maint_id
	 * @return maintenance trouv¨¦e
	 */
	public Maintenance getMaintenance(int idMaint) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Maintenance retour = null;
		Entreprise e = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM maintenance LEFT JOIN ENTREPRISE ON MAINTENANCE.MAINT_IDENT=ENTREPRISE.ENT_NSIRET WHERE maint_id = ?");
			ps.setInt(1, idMaint);
			rs = ps.executeQuery();
			if (rs.next())
			{
				e = new Entreprise(rs.getString("ent_nom"), rs.getInt("ent_nsiret"), rs.getString("ent_adresse"),rs.getString("ent_ape"),null);
				retour = new Maintenance(rs.getInt("maint_id"), rs.getString("maint_cont"), rs.getString("maint_type"), rs.getString("maint_duree"), rs.getInt("maint_etat"), rs.getInt("maint_idOp"),e);
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
	
	/**
	 * permet de copier tous les maintenances
	 * @return ArrayList<Maintenance>
	 */
	public ArrayList<Maintenance> getListeMaintenance() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Maintenance> retour = new ArrayList<Maintenance>();
		
		// connexion ÃƒÂ  la base de donnÃƒÂ©es
		try {
	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM maintenance");
	
			// on exÃƒÂ©cute la requÃƒÂªte
			rs = ps.executeQuery();
			// on parcourt les lignes du rÃƒÂ©sultat
			while (rs.next())
				retour.add(new Maintenance(rs.getInt("maint_id"), rs.getInt("maint_idEnt"), rs.getString("maint_cont"), rs.getString("maint_type"), rs.getString("maint_duree"), rs.getInt("maint_etat"), rs.getInt("maint_idOp")));
	
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
	
	
	/**
	 * permet de valider une maintenance
	 * mettre son etat=1
	 * @param ref maint_id
	 * @return 1 s'il r¨¦ussit
	 */
	public int validerMaintenance(int ref) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		int etat = 1;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET maint_id = ? WHERE maint_etat = ?");
			ps.setInt(1, etat);
			ps.setInt(2, ref);
			retour = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
	}
	
	public int modifierOp(int ref, int idOp) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_idOp = ?");
			ps.setInt(1, idOp);
			ps.setInt(2, ref);
			retour = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
	}
	
	/**
	 * permet de renouvler une maintenance
	 * @param m maintenance ¨¤ renouvler
	 * @return 1 s'il r¨¦ussit
	 */
	public int updateMaintenance(Maintenance m) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET maint_cont = ?, maint_type = ?, maint_duree = ?, maint_etat = ?, maint_idOp = ?, maint_ident =? WHERE maint_id = ?");
			ps.setString(1, m.getcMaint());
			ps.setString(2, m.getType());
			ps.setString(3, m.getduree());
			ps.setInt(4, m.getEtat());
			ps.setInt(5, m.getidOp());
			ps.setInt(6, m.getIdEnt());
			ps.setInt(7, m.getId());
			retour = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		
		return retour;
	}

}

