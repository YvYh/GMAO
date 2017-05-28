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

	public Maintenance getMaintenance(int idMaint) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Maintenance retour = null;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM maintenance WHERE maint_id = ?");
			ps.setInt(1, idMaint);
			rs = ps.executeQuery();
			if (rs.next())
				retour = new Maintenance(rs.getInt("maint_id"), rs.getInt("maint_idEnt"), rs.getString("maint_cont"), rs.getString("maint_type"), rs.getString("maint_duree"), rs.getInt("maint_etat"), rs.getInt("maint_idOp"));
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
	
	public ArrayList<Maintenance> getListeMaintenance() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Maintenance> retour = new ArrayList<Maintenance>();
		
		// connexion Ã  la base de donnÃ©es
		try {
	
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM maintenance");
	
			// on exÃ©cute la requÃªte
			rs = ps.executeQuery();
			// on parcourt les lignes du rÃ©sultat
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
	
	public int modifierEnt(int ref, int ident) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE ident = ?");
			ps.setInt(1, ref);
			ps.setInt(2, ident);
			retour = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
	}
	
	
	public int modifierCont(int ref, String cont) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_cont = ?");
			ps.setString(1, cont);
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
	
	public int modifierType(int ref, String type) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_type = ?");
			ps.setString(1, type);
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
	
	public int modifierDuree(int ref, String duree) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_duree = ?");
			ps.setString(1, duree);
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
	
	public int modifierEtat(int ref, int etat) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_etat = ?");
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
	
	public int validerMaintenance(int ref) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		int etat = 1;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_etat = ?");
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
	
	public int modifierEnt(int ref, int ident) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE ident = ?");
			ps.setInt(1, ref);
			ps.setInt(2, ident);
			retour = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (ps != null) ps.close();} catch (Exception ignore) {}
			try {if (con != null) con.close();} catch (Exception ignore) {}
		}
		return retour;
	}
	
	
	public int modifierCont(int ref, String cont) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_cont = ?");
			ps.setString(1, cont);
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
	
	public int modifierType(int ref, String type) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_type = ?");
			ps.setString(1, type);
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
	
	public int modifierDuree(int ref, String duree) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_duree = ?");
			ps.setString(1, duree);
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
	
	public int modifierEtat(int ref, int etat) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_etat = ?");
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
	
	public int validerMaintenance(int ref) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;
		int etat = 1;
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE maintenance SET ref = ? WHERE maint_etat = ?");
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

}

