package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Genre;

public interface GenreDAO {
	public List<Genre> list();
	
	public void add(Genre genre);
	
	public void delete(Integer id);
	
	public void modif(Genre genre);
}
