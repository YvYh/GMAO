package app;

import java.util.ArrayList;

public class Operateur extends Utilisateur {
	private String adresse;
	private String tel;
	private ArrayList<Maintenance> listM;
	public Operateur(String nom, String prenom, String motDePasse,String adresse, String tel,String id)
	{
		super(nom, prenom, motDePasse, 2,id );
		this.setAdresse(adresse);
		this.setTel(tel);
		
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public void affectation(Maintenance m){
		listM.add(m);
	}
	
	public ArrayList<Maintenance> consulter(){
		return listM;
	}

}
