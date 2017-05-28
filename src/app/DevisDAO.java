package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DevisDAO {
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN = "bdd1"; //exemple BDD1
	final static String PASS = "bdd1"; //exemple BDD1

	public DevisDAO() {
	try {
	Class.forName("oracle.jdbc.OracleDriver");
	} catch (ClassNotFoundException e) {
	System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
	}
	}
	
	public int ajouter(Devis devis) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO devis (devis_id, maint_ref, devis_cout, devis_etat) VALUES (?, ?, ?, ?)");
			ps.setInt(1, devis.getId());
			ps.setInt(2, devis.getIdM());
			ps.setFloat(3, devis.getCout());
			ps.setInt(4, devis.getEtat());
			for(int i=1; i<devis.sizeSurcoutP() +1; i++) {
				ps = con.prepareStatement("INSERT INTO surcout (devis_id, surcout_p, surcout_rm, id) VALUES (?, ?, ?, ?)");
				ps.setInt(1, devis.getId());
				ps.setFloat(2, devis.getThisSurcoutP(i));
				ps.setString(3, devis.getThisSurcoutRM(i));
				ps.setInt(4, 1);
				retour = ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
	}
	

	public Devis getDevis(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement pss = null;
		ResultSet rss = null;
		Devis retour = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM devis WHERE devis_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				retour = new Devis(rs.getInt("devis_id"), rs.getInt("maint_ref"), rs.getInt("devis_cout"));
				pss = con.prepareStatement("SELECT * FROM surcout WHERE devis_id = ?");
				pss.setInt(1, retour.getId());
				rss = pss.executeQuery();
				while (rss.next()) {
					retour.addThisSurcoutP(rss.getFloat("surcout_p"));
					retour.addThisSurcoutRM(rss.getString("surcout_rm"));
				}
			}
		}

		catch (Exception ee) {
			ee.printStackTrace();
			} 
		finally {
			try { if (rs != null) rs.close();} catch (Exception ignore) {}
			try { if (ps != null) ps.close();} catch (Exception ignore) {}
			try { if (rss != null) rs.close();} catch (Exception ignore) {}
			try { if (pss != null) ps.close();} catch (Exception ignore) {}
			try { if (con != null) con.close();} catch (Exception ignore) {}
			}
			return retour;
			}
	
	

	public void updateDevis(Devis d) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args)
	{
		Devis d=new DevisDAO().getDevis(123);
		System.out.print(d.getId());
	}

	public ArrayList getListDevis() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement pss = null;
		ResultSet rss = null;
		ArrayList<Devis> retour = new ArrayList<Devis>();
		
		// connexion Ã  la base de donnÃ©es
		try {
	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM devis");
	
			// on exÃ©cute la requÃªte
			rs = ps.executeQuery();
			// on parcourt les lignes du rÃ©sultat
			while (rs.next())
				retour.add(new Devis(rs.getInt("devis_id"), rs.getInt("maint_ref"), rs.getInt("devis_cout")));
			for (int i=0; i<retour.size();i++)
			{
				pss = con.prepareStatement("SELECT * FROM surcout WHERE devis_id = ?");
				pss.setInt(1, retour.get(i).getId());
				rss = pss.executeQuery();
				while (rss.next()) {
					retour.get(i).addThisSurcoutP(rss.getFloat("surcout_p"));
					retour.get(i).addThisSurcoutRM(rss.getString("surcout_rm"));
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