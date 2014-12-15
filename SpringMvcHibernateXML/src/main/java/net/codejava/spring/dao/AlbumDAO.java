package net.codejava.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;

import net.codejava.spring.model.Album;

public interface AlbumDAO {
public List<Album> list();
	
	public void add(Album album);
	
	public void delete(Integer id) ;
	
	public void modif(Album album, Boolean newAlbum);
	
	public Album getAlbum(Integer id);
}
