package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.CommentaireMusique;
import net.codejava.spring.model.Musique;

public interface CommentMusiqueDAO {
	
	public void add(Musique musique, String comm, int note);
	
	public List<CommentaireMusique> list(int mu_id);
	
	public String avg(int mu_id);
	
	public List<CommentaireMusique> listLastest();

}
