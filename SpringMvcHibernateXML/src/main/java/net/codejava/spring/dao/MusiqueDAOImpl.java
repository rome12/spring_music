package net.codejava.spring.dao;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import net.codejava.spring.model.Album;
import net.codejava.spring.model.CommentaireMusique;
import net.codejava.spring.model.Musique;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.ls.LSInput;

public class MusiqueDAOImpl implements MusiqueDAO {
	private SessionFactory sessionFactory;

	public MusiqueDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Musique> list() {
		
		@SuppressWarnings("unchecked")
		List<Musique> listMusique = (List<Musique>) sessionFactory.getCurrentSession()
				.createCriteria(Musique.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listMusique;
	}
	
	@Transactional
	public List<Musique> listLatest() {
		@SuppressWarnings("unchecked")
		List<Musique> listMusique = (List<Musique>) sessionFactory.getCurrentSession()
				.createCriteria(Musique.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).addOrder(Order.desc("id")).setMaxResults(5).list();
		return listMusique;
	}
	
	@Transactional
	public List<Musique> listPopulaire(CommentMusiqueDAO commentMusiqueDao){
		List<Musique> listMusique = this.list();
		HashMap<String, Musique> laHashmap = new HashMap<String,Musique>();
		for(int i = 0; i< listMusique.size();i++){
			laHashmap.put(commentMusiqueDao.avg(listMusique.get(i).getId()),listMusique.get(i));
		}
		TreeMap<String, Musique> laTreeMap = new TreeMap<String, Musique>(laHashmap);
		System.out.println(laTreeMap);
		listMusique.clear();
		listMusique.addAll(laTreeMap.values());
		Collections.reverse(listMusique);
		if(listMusique.size()<5) return listMusique;
		listMusique.subList(5,listMusique.size()).clear();
		return listMusique;
	}	
	
	@Transactional
	public List<Musique> listArtiste(int id) {
		String hql = "FROM Musique WHERE ar_id = "+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Musique> listMusique = query.list();
		return listMusique;
	}
	
	@Transactional
	public List<Musique> listAlbum(int id) {
		String hql = "FROM Musique WHERE al_id = "+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Musique> listMusique = query.list();
		return listMusique;
	}

	@Transactional
	public void add(Musique musique) {
		sessionFactory.getCurrentSession().save(musique);
	}

	@Transactional
	public void delete(Integer id) {
		Musique musique = (Musique) sessionFactory.getCurrentSession().load(
                Musique.class, id);
        if (null != musique) {
            this.sessionFactory.getCurrentSession().delete(musique);
        }
	}
	
	@Transactional
	public Musique getMusique(Integer id) {
		@SuppressWarnings("unchecked")
		List<Musique> listMusique = (List<Musique>) sessionFactory.getCurrentSession().createCriteria(Musique.class).add(Restrictions.eq("id", id)).list();
		return listMusique.get(0);
	}
	
	@Transactional
	public void modif(Musique musique, Boolean newMusique) {
		@SuppressWarnings("unchecked")
		List<Musique> ancienne = (List<Musique>) sessionFactory.getCurrentSession().createCriteria(Musique.class).add(Restrictions.eq("id", musique.getId())).list();
		if(!newMusique){
			musique.setFichier(ancienne.get(0).getFichier());
		}
		else {
			File ancienFile = new File("musiques/"+ancienne.get(0).getFichier());
			ancienFile.delete();
		}
		sessionFactory.getCurrentSession().merge(musique);
	}
}
