package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Musique;

public interface MusiqueDAO {
	public List<Musique> list();
	
	public List<Musique> listLatest();
	
	public void add(Musique musique);
	
	public void delete(Integer id);
	
	public void modif(Musique musique, Boolean newMusique);
	
	public Musique getMusique(Integer id) ;
	
	public List<Musique> listArtiste(int id);
	
	public List<Musique> listAlbum(int id);
	
	public List<Musique> listPopulaire(CommentMusiqueDAO commentMusiqueDao);
}
