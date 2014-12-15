package net.codejava.spring.dao;

import java.io.File;
import java.util.List;

import net.codejava.spring.model.Album;
import net.codejava.spring.model.Musique;

import org.hibernate.Criteria;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

public class AlbumDAOImpl implements AlbumDAO {
	private SessionFactory sessionFactory;

	public AlbumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Album> list() {
		
		@SuppressWarnings("unchecked")
		List<Album> listAlbum = (List<Album>) sessionFactory.getCurrentSession()
				.createCriteria(Album.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listAlbum;
	}

	@Transactional
	public void add(Album album) {
		sessionFactory.getCurrentSession().save(album);
	}

	@Transactional
	public void delete(Integer id) {
		Album album = (Album) sessionFactory.getCurrentSession().load(
                Album.class, id);
        if (null != album) {
            this.sessionFactory.getCurrentSession().delete(album);
        }
	}
	
	@Transactional
	public void modif(Album album) {
		sessionFactory.getCurrentSession().update(album);
	}
	
	@Transactional
	public void modif(Album album, Boolean newAlbum) {
		@SuppressWarnings("unchecked")
		List<Album> ancienne = (List<Album>) sessionFactory.getCurrentSession().createCriteria(Album.class).add(Restrictions.eq("id", album.getId())).list();
		if(!newAlbum){
			album.setFichier(ancienne.get(0).getFichier());
		}
		else {
			File ancienFile = new File("images/"+ancienne.get(0).getFichier());
			ancienFile.delete();
		}
		sessionFactory.getCurrentSession().merge(album);
	}
	
	@Transactional
	public Album getAlbum(Integer id) {
		@SuppressWarnings("unchecked")
		List<Album> listAlbum = (List<Album>) sessionFactory.getCurrentSession().createCriteria(Album.class).add(Restrictions.eq("id", id)).list();
		return listAlbum.get(0);
	}
}
