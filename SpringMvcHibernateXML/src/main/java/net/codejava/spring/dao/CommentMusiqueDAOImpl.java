package net.codejava.spring.dao;


import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import net.codejava.spring.model.CommentaireMusique;
import net.codejava.spring.model.Musique;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class CommentMusiqueDAOImpl implements CommentMusiqueDAO {
	
	private SessionFactory sessionFactory;

	public CommentMusiqueDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void add(Musique musique, String comm, int note) {
		CommentaireMusique leComm = new CommentaireMusique();
		leComm.setMusique(musique);
		leComm.setCommentaire(comm);
		leComm.setNote(note);
		sessionFactory.getCurrentSession().save(leComm);
	}
	
	@Transactional
	public List<CommentaireMusique> list(int mu_id){
		String hql = "FROM CommentaireMusique WHERE mu_id = "+mu_id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CommentaireMusique> listcomm = query.list();
		return listcomm;
	}
	
	@Transactional
	public List<CommentaireMusique> listLastest(){
		String hql = "FROM CommentaireMusique ORDER BY cm_id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CommentaireMusique> listcomm = query.list();
		Collections.reverse(listcomm);
		if(listcomm.size()<3) return listcomm;
		listcomm.subList(3,listcomm.size()).clear();
		return listcomm;
	}
	
	@Transactional
	public String avg(int mu_id){
		String hql = "SELECT AVG(note) FROM CommentaireMusique WHERE mu_id = "+mu_id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Double> avgList = (List<Double>) query.list();
		System.out.println(avgList);
		if(avgList.get(0)==null)return "0";
		return new DecimalFormat("#.#").format(avgList.get(0));
	}

}
