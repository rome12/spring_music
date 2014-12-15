package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Album;
import net.codejava.spring.model.CommentaireAlbum;

public interface CommentAlbumDAO {
	
	public void add(Album album, String comm, int note);
	
	public List<CommentaireAlbum> list(int mu_id);

}
