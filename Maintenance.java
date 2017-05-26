package app;


public class Maintenance {
	private int idEnt;
	private int ref;
	private String cMaint;
	private String type;
	private String duree;
	private boolean etat;
	private int idOp;
	
	public Maintenance(int ref, int idEnt, String nMaint, String type, String duree, int etat, int idOp)
	{
		this.ref=ref;
		this.idEnt = idEnt;
		this.cMaint = nMaint;
		this.type = type;
		this.duree = duree;
		if (etat==1)
			this.etat = true;
		else
			this.etat = false;
		
		this.idOp=idOp;
	}
	
	public Maintenance(int ref, String nMaint, String type, String duree)
	{
		this.ref=ref;
		this.cMaint = nMaint;
		this.type = type;
		this.duree = duree;
		idEnt=0;
		idOp=0;
		etat=false;
	}
	
	public Maintenance() {
		this.idEnt=0;
		this.ref=0;
		this.cMaint=null;
		this.type=null;
		this.duree=null;
		this.etat=false;
		this.idOp=0;
	}

	public int getId()
	{
		return idEnt;
	}
	
	public void setId(int idEnt)
	{
		this.idEnt = idEnt;
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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

}