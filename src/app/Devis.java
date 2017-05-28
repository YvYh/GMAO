package app;

import java.util.ArrayList;

public class Devis {
	private int id;
	private int idM;
	private float cout;
	public ArrayList<Float> surcoutP;
	public ArrayList<String> surcoutRM;
	private int etat;

	public Devis(int id, int idM, float cout) {
		this.id = id;
		this.idM = idM;
		this.cout = cout;
		this.surcoutP = new ArrayList<Float>();
		this.surcoutRM = new ArrayList<String>();
		this.etat = 0;
	}
	public Devis() {
		id = 0;
		idM = 0;
		cout = 0;
		etat = 0;
		this.surcoutP = new ArrayList<Float>();
		this.surcoutRM = new ArrayList<String>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdM() {
		return idM;
	}
	public void setIdM(int idM) {
		this.idM = idM;
	}
	public float getCout() {
		return cout;
	}
	public void setCout(float cout) {
		this.cout = cout;
	}
	public ArrayList<String> getSurcoutRM() {
		return surcoutRM;
	}
	public void setSurcoutRM(ArrayList<String> surcoutRM) {
		this.surcoutRM = surcoutRM;
	}
	public ArrayList<Float> getSurcoutP() {
		return surcoutP;
	}
	public void setSurcoutP(ArrayList<Float> surcoutP) {
		this.surcoutP = surcoutP;
	}
	
	public int sizeSurcoutP() {
		return surcoutP.size();
	}
	
	public int sizeSurcoutRM() {
		return surcoutRM.size();
	}
	
	public float getThisSurcoutP(int i) {
		return surcoutP.get(i);
	}
	
	public void addThisSurcoutP(float p) {
		surcoutP.add(p);
	}
	
	public void setThisSurcoutP(int i, float p) {
		surcoutP.set(i, p);
	}
	
	public String getThisSurcoutRM(int i) {
		return surcoutRM.get(i);
	}
	
	public void addThisSurcoutRM(String rm) {
		surcoutRM.add(rm);
	}
	
	public void setThisSurcoutRM(int i, String rm) {
		surcoutRM.set(i, rm);
	}
	
	public void validerDevis() {
		this.etat = 1;
	}
	
	public int getEtat() {
		return etat;
	}

}