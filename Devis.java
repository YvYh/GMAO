package app;

import java.util.ArrayList;

public class Devis {
	private int id;
	private int idM;
	private float cout;
	private ArrayList<Float> surcoutP;
	private ArrayList<String> surcoutRM;

	
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

}
