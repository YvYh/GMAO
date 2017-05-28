package app;


public class Maintenance {
	private int idEnt;
	private Entreprise entreprise;
	private int id;
	private String cMaint;
	private String type;
	private String duree;
	private boolean etat;
	private int idOp;
	
	public Maintenance(int id, int idEnt, String nMaint, String type, String duree, int etat, int idOp)
	{
		this.id=id;
		this.setIdEnt(idEnt);
		this.cMaint = nMaint;
		this.type = type;
		this.duree = duree;
		if (etat==1)
			this.etat = true;
		else
			this.etat = false;
		
		this.idOp=idOp;
	}
	
	public Maintenance(int id, String nMaint, String type, String duree, int etat, int idOp, Entreprise e)
	{
		this.id=id;
		this.idEnt=e.getEtat();
		this.entreprise=e;
		this.cMaint = nMaint;
		this.type = type;
		this.duree = duree;
		if (etat==1)
			this.etat = true;
		else
			this.etat = false;
		
		this.idOp=idOp;
	}
	
	public Maintenance(int id, String nMaint, String type, String duree)
	{
		this.id=id;
		this.cMaint = nMaint;
		this.type = type;
		this.duree = duree;
		setIdEnt(0);
		idOp=0;
		etat=false;
	}
	
	public Maintenance() {
		this.setIdEnt(0);
		this.id=0;
		this.cMaint=null;
		this.type=null;
		this.duree=null;
		this.etat=false;
		this.idOp=0;
	}
	
	public void setEntreprise(Entreprise e) {
		this.entreprise=e;
		this.idEnt=e.getID();
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}

	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	public String getcMaint()
	{
		return cMaint;
	}
	
	public void setcMaint(String nMaint)
	{
		this.cMaint = nMaint;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getduree()
	{
		return duree;
	}
	
	public void setduree(String duree)
	{
		this.duree = duree;
	}
	
	public int getEtat()
	{
		if (etat)
			return 1;
		else
			return 0;
	}
	
	public void validerMaint()
	{
		etat = true;
	}
	
	public int getidOp()
	{
		return idOp;
	}
	
	public void setidOp(int idOp)
	{
		this.idOp=idOp;
	}

	public int getIdEnt() {
		return idEnt;
	}

	public void setIdEnt(int idEnt) {
		this.idEnt = idEnt;
	}

	

}