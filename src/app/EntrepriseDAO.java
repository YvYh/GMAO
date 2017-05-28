package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Entreprise DAO
 * @author CL
 *
 */
public class EntrepriseDAO {
	
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "bdd1"; //exemple BDD1
	final static String PASS = "bdd1"; //exemple BDD1

	public EntrepriseDAO() {
	try {
	Class.forName("oracle.jdbc.OracleDriver");
	} catch (ClassNotFoundException e) {
	System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
	}
	}
	
	/**
	 * permet d'ajouter une entreprise
	 * @param entreprise ¨¤ ajouter
	 * @return 1 s'il r¨¦ussit
	 */
	public int ajouter(Entreprise entreprise) {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO utilisateur_uil (util_nom, util_prenom, util_mdp, util_etat, util_id) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, entreprise.getNom());
			ps.setString(2, null);
			ps.setString(3, entreprise.getMotDePasse());
			ps.setInt(4, 3);
			ps.setInt(5, entreprise.getnSiret());
			
			ps = con.prepareStatement("INSERT INTO entreprise (ent_nom, ent_ape, ent_adresse, ent_nSiret) VALUES (?, ?, ?, ?)");
			ps.setString(1, entreprise.getNom());
			ps.setString(2, entreprise.getApe());
			ps.setString(3, entreprise.getAdresse());
			ps.setInt(4, entreprise.getnSiret());

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
	 * permet de copier une entreprise depuis BDD d'apr¨¨s son id
	 * @param idEnt ent_id
	 * @return Entreprise trouv¨¦e
	 */
	public Entreprise getEntreprise(int idEnt) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement pss = null;
		ResultSet rss = null;
		Entreprise retour = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM entreprise WHERE ent_nSiret = ?");
			ps.setInt(1, idEnt);
			rs = ps.executeQuery();
			if (rs.next())
			{
				retour = new Entreprise(rs.getString("ent_nom"), rs.getInt("ent_nSiret"), rs.getString("ent_adresse"),rs.getString("ent_ape"),null);
			}
			pss = con.prepareStatement("SELECT * FROM utilisateur_uti WHERE util_id = ?");
			pss.setInt(1, idEnt);
			rss = pss.executeQuery();
			if (rss.next())
				retour.setMot(rss.getString("util_mdp"));
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
	
<<<<<<< HEAD
	/**
	 * permet de renouvler une entreprise
	 * @param e entreprise ¨¤ renouvler
	 * @return 1 s'il r¨¦ussit
	 */
=======
		public int getIdEnt(String nom) {
		int retour = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM entreprise WHERE ent_nom = ?");
			ps.setString(1, nom);
			rs = ps.executeQuery();
			retour = (rs.getInt("ent_id"));
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
	
>>>>>>> origin/master
	public int updateEntreprise(Entreprise e) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE entreprise SET ent_nom = ?, ent_nSiret = ?, ent_adresse = ?, ent_ape = ?  WHERE idEnt = ?");
			ps.setString(1, e.getNom());
			ps.setInt(2, e.getnSiret());
			ps.setString(3, e.getAdresse());
			ps.setString(4, e.getApe());
			ps.setInt(5, e.getID());
			retour = ps.executeUpdate();
		} catch (Exception ae) {
			ae.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
	}
<<<<<<< HEAD

	/**
	 * permet de copier tous les entreprises
	 * @return List<Entreprise>
	 */
	public List<Entreprise> getListeEntreprise() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;

		List<Entreprise> retour = new ArrayList<Entreprise>();

		
		// connexion ÃƒÂ  la base de donnÃƒÂ©es
		try {
	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur_uti WHERE util_etat=3");
			rs = ps.executeQuery();
			while (rs.next())
			{
				retour.add(new Entreprise(rs.getString("util_nom"),rs.getInt("util_id"),null,null,rs.getString("util_mdp")));
			}
			for(int i=0;i<retour.size();i++)
			{
				ps1 = con.prepareStatement("SELECT * FROM entreprise WHERE ent_nsiret = ?");
				ps1.setInt(1, retour.get(i).getID());
				rs1 = ps1.executeQuery();
				if(rs1.next())
				{
					retour.get(i).setAdresse(rs1.getString("ent_adresse"));
					retour.get(i).setApe(rs1.getString("ent_ape"));
				}
			}

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
				if (rs1 != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps1 != null)
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
=======
}
>>>>>>> origin/master
