package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Artiste;

public interface ArtisteDAO {
	public List<Artiste> list();
	
	public void add(Artiste artiste);
	
	public void delete(Integer id);
	
	public void modif(Artiste artiste);
	
	public Artiste getArtiste(Integer id);
}
