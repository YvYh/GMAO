package app;

public class Utilisateur {
 private String nom;
 private String prenom;
 private String motDePasse;
 private int etat;//Responsable=1, operateur=2; client=3
 private int id;//numero d'identification
 
 
 public Utilisateur(String nom, String prenom, String motDePasse, int etat, int id)
 {
	 this.nom=nom;
	 this.prenom=prenom;
	 this.motDePasse=motDePasse;
	 this.etat=etat;
	 this.id=id;
 }
 
 public int getID()
 {
	 return id;
 }
 
 public int getEtat()
 {
	 return etat;
 }
 
 public String getNom()
 {
	 return nom;
 }
 
 public String getPrenom()
 {
	 return prenom;
 }
 
 public String getMotDePasse()
 {
	 return motDePasse;
 }
 
 
}
