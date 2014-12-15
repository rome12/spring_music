package net.codejava.spring.dao;


import java.util.List;

import net.codejava.spring.model.Album;
import net.codejava.spring.model.CommentaireAlbum;
import net.codejava.spring.model.CommentaireMusique;
import net.codejava.spring.model.Musique;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class CommentAlbumDAOImpl implements CommentAlbumDAO {
	
	private SessionFactory sessionFactory;

	public CommentAlbumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void add(Album album, String comm, int note) {
		CommentaireAlbum leComm = new CommentaireAlbum();
		leComm.setAlbum(album);
		leComm.setCommentaire(comm);
		leComm.setNote(note);
		sessionFactory.getCurrentSession().save(leComm);
	}
	
	@Transactional
	public List<CommentaireAlbum> list(int al_id){
		String hql = "FROM CommentaireAlbum WHERE al_id = "+al_id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CommentaireAlbum> listcomm = query.list();
		return listcomm;
	}

}
