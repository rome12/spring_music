package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Genre;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class GenreDAOImpl implements GenreDAO {
	private SessionFactory sessionFactory;

	public GenreDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Genre> list() {
		
		@SuppressWarnings("unchecked")
		List<Genre> listGenre = (List<Genre>) sessionFactory.getCurrentSession()
				.createCriteria(Genre.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listGenre;
	}

	@Transactional
	public void add(Genre genre) {
		sessionFactory.getCurrentSession().save(genre);
	}

	@Transactional
	public void delete(Integer id) {
		Genre genre = (Genre) sessionFactory.getCurrentSession().load(
                Genre.class, id);
        if (null != genre) {
            this.sessionFactory.getCurrentSession().delete(genre);
        }
	}
	
	@Transactional
	public void modif(Genre genre) {
		sessionFactory.getCurrentSession().update(genre);
	}
}
