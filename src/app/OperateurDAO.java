package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * Operateur DAO
 * @author CL
 *
 */
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
	/**
	 * permet d'ajouter un operateur dans la BDD
	 * @param op ¨¤ ajouter
	 * @return 1 s'il r¨¦ussit
	 */
	public int ajouter(Operateur op) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO utilisateur_uti (util_nom, util_prenom, util_mdp, util_etat, util_id) VALUES (?, ?, ?, ?, ?)");
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

	/**
	 * permet de copier un operateur d'apr¨¨s son id
	 * @param idOp op_id
	 * @return Operateur trouv¨¦
	 */
	public Operateur getOperateur(int idOp) {
		Connection con = null;
		PreparedStatement ps=null, ps1 = null;
		ResultSet rs=null, rs1 = null;
		Operateur retour = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			//ps = con.prepareStatement("SELECT * FROM utilisateur_uti WHERE util_id = ?");
			ps = con.prepareStatement("SELECT * FROM UTILISATEUR_UTI LEFT JOIN OPERATEUR ON UTILISATEUR_UTI.UTIL_ID=OPERATEUR.OP_ID WHERE UTILISATEUR_UTI.UTIL_ID=?");
			ps.setInt(1, idOp);
			rs = ps.executeQuery();
			if (rs.next())
			{
				retour = new Operateur(rs.getString("util_nom"), rs.getString("util_prenom"), rs.getString("util_mdp"), 
						rs.getString("op_adr"),rs.getString("op_tel"),rs.getInt("util_id"));
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


	/**
	 * permet de copier tous les operateur dans la BDD
	 * @return List<Operateur>
	 */
	public List<Operateur> getListeOperateur() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;

		List<Operateur> retour = new ArrayList<Operateur>();

		
		// connexion ÃƒÂ  la base de donnÃƒÂ©es
		try {
	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur_uti WHERE util_etat=2");
			rs = ps.executeQuery();
			while (rs.next())
			{
				retour.add(new Operateur(rs.getString("util_nom"), rs.getString("util_prenom"), rs.getString("util_mdp"), rs.getInt("util_etat"), rs.getInt("util_id")));
			}
			for(int i=0;i<retour.size();i++)
			{
				ps1 = con.prepareStatement("SELECT * FROM operateur WHERE op_id = ?");
				ps1.setInt(1, retour.get(i).getID());
				rs1 = ps1.executeQuery();
				if(rs1.next())
				{
					retour.get(i).setAdresse(rs1.getString("op_adr"));
					retour.get(i).setTel(rs1.getString("op_tel"));
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
	
	/**
	 * permet de renouvler un operateur
	 * @param op operateur ¨¤ renouvler
	 * @return 1 s'il r¨¦ussit
	 */
	public int updateOperateur(Operateur op) {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement pss = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE utilisateur SET util_nom = ?, util_prenom = ?, util_mdp = ? WHERE id = ?");
			ps.setString(1, op.getNom());
			ps.setString(2, op.getPrenom());
			ps.setString(3, op.getMotDePasse());
			ps.setInt(4, op.getID());
			retour = ps.executeUpdate();
			pss = con.prepareStatement("UPDATE operateur SET op_adresse = ?, op_tel = ?, op_etat = ? WHERE idOp = ?");
			pss.setString(1, op.getAdresse());
			pss.setString(2, op.getTel());
			pss.setInt(3, op.getEtat());
			pss.setInt(4, op.getID());
			retour = pss.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
	}


}
