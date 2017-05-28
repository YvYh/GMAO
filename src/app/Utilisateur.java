package app;
/**
 * Classe Utilisateur
 * -nom
 * -prenom
 * -mot de passe
 * -etat: 1 responsable
 *        2 operateur
 *        3 client
 * -id
 * @author YH
 *
 */
public class Utilisateur {
  String nom;
  String prenom;
  String motDePasse;
  int etat;//Responsable=1, operateur=2; client=3
  int id;//numero d'identification
 
 public Utilisateur()
 {
	 nom="";
	 prenom="";
	 motDePasse="";
	 etat=0;
	 id=0;
 }
 
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
 
 public void setEtat(int i){
	 this.etat=i;
 }
 
 public void setMot(String mot)
 {
	 this.motDePasse=mot;
 }
 
 
}
