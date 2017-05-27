package app;


public class Maintenance {
	private Entreprise entreprise;
	private int ref;
	private String cMaint;
	private String type;
	private String duree;
	private boolean etat;
	private int idOp;
	
	public Maintenance(int ref, Entreprise entreprise, String nMaint, String type, String duree, int etat, int idOp)
	{
		this.ref=ref;
		this.entreprise = entreprise;
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
		this.entreprise = new Entreprise();
		idOp=0;
		etat=false;
	}
	
	public Maintenance(int ref, String nMaint, String type, String duree, int etat, int idOp)
	{
		this.ref=ref;
		this.cMaint = nMaint;
		this.type = type;
		this.duree = duree;
		this.entreprise = new Entreprise();
		this.idOp = idOp;
		if (etat==1)
			this.etat = true;
		else
			this.etat = false;
	}
	
	
	public Maintenance() {
		this.entreprise = new Entreprise();
		this.ref=0;
		this.cMaint=null;
		this.type=null;
		this.duree=null;
		this.etat=false;
		this.idOp=0;
	}
	
	public void setEntreprise(String nom, int siret, String adresse, String ape) {
		this.entreprise.setNom(nom);
		this.entreprise.setnSiret(siret);
		this.entreprise.setAdresse(adresse);
		this.entreprise.setApe(ape);
	}

	public String getIdEnt()
	{
		return entreprise.getApe();
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
