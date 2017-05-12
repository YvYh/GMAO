package app;

public class Entreprise extends Utilisateur {
	private String nom;
	private String nSiret;
	private String adresse;
	private String ape;
	
	public Entreprise(String nom, String nSiret, String adresse, String ape,String motDePasse)
	{
		super(nom,"",motDePasse, 3, nSiret);
		this.adresse = adresse;
		this.ape = ape;
	}
	
	public Entreprise()
	{
		super();
		this.nom ="";
		this.nSiret = "";
		this.adresse = "";
		this.ape = "";
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public String getnSiret()
	{
		return nSiret;
	}
	
	public void setnSiret(String nSiret)
	{
		this.nSiret = nSiret;
	}
	
	public String getAdresse()
	{
		return adresse;
	}
	
	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}
	
	public String getApe()
	{
		return ape;
	}
	
	public void setApe(String ape)
	{
		this.ape = ape;
	}

}