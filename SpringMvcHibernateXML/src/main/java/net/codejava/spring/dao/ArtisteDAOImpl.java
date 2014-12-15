package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Artiste;
import net.codejava.spring.model.Musique;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

public class ArtisteDAOImpl implements ArtisteDAO {
	
	private SessionFactory sessionFactory;

	public ArtisteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Artiste> list() {
		
		@SuppressWarnings("unchecked")
		List<Artiste> listArtiste = (List<Artiste>) sessionFactory.getCurrentSession()
				.createCriteria(Artiste.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listArtiste;
	}

	@Transactional
	public void add(Artiste artiste) {
		sessionFactory.getCurrentSession().save(artiste);
	}

	@Transactional
	public void delete(Integer id) {
		Artiste artiste = (Artiste) sessionFactory.getCurrentSession().load(
                Artiste.class, id);
        if (null != artiste) {
            this.sessionFactory.getCurrentSession().delete(artiste);
        }
	}
	
	@Transactional
	public void modif(Artiste artiste) {
		sessionFactory.getCurrentSession().update(artiste);
	}
	
	@Transactional
	public Artiste getArtiste(Integer id) {
		@SuppressWarnings("unchecked")
		List<Artiste> listArtiste = (List<Artiste>) sessionFactory.getCurrentSession().createCriteria(Artiste.class).add(Restrictions.eq("id", id)).list();
		return listArtiste.get(0);
	}
}
