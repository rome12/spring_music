package net.codejava.spring.model;

public class Artiste {
	private int id;
	private String nom;
	private String prenom;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String toString() {
		return prenom + " " + nom;
	}
}
