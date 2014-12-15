package net.codejava.spring.model;

public class CommentaireMusique {
	private int id;
	private String commentaire;
	private int note;
	private Musique musique;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public Musique getMusique() {
		return musique;
	}
	public void setMusique(Musique musique) {
		this.musique = musique;
	}

	public String toString() {
		return commentaire;
	}
	
	
	
	

}
