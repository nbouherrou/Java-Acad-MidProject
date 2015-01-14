package org.jacademie.projet1.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jacademie.projet1.domain.Artiste;
import org.jacademie.projet1.utils.HibernateUtils;

/**
 * Classe qui gère l'accès en BDD aux abjets Artiste.
 * @author jacademie-team
 *
 */
public class ArtisteDao {

	/**
	 * Champs logger pour afficher les messages 
	 */
	private static Logger logger = LogManager.getLogger(ArtisteDao.class);

	/**
	 * Persiste un Artiste en BDD.
	 * @param artiste		: objet Artiste
	 * @throws Exception
	 */
	public void createArtiste(Artiste artiste) throws Exception {

		logger.info("Creating artiste : " + artiste + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.save(artiste);

		session.getTransaction().commit();

		logger.info("Artiste created. \n");

		HibernateUtils.closeSession(session);

	}
	
	/**
	 * Recupere un objet Artiste de la BDD a partir de son identifiant
	 * @param id		: identifiant de l'artiste
	 * @return
	 * @throws Exception
	 */
	public Artiste findArtisteById(int id) throws Exception {

		logger.info("Finding Artiste with id : " + id + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		Artiste result = (Artiste) session.get(Artiste.class, id);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		if (result != null) {

			logger.info("Artiste found : " + result);
		} else {
			logger.info("Artiste not found");
		}

		return result;
	}
	
	
	/**
	 * Met a jour les données (enfants aussi, Albums, Chansons) d'un objet Artiste en les persistant en BDD.
	 * @param artiste		: objet Artiste	
	 * @throws Exception
	 */
	public void updateArtiste(Artiste artiste) throws Exception {

		logger.info("Updating artiste : " + artiste + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.merge(artiste);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Artiste updated. \n");
	}

	/**
	 * Recupere la liste de tous les Artiste en BDD.
	 * @return
	 * @throws Exception
	 */
	public Artiste retrieveAllArtistes() throws Exception {

		logger.info("Retrieving all Artistes...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(Artiste.class);
		
		List<Artiste> result = criteria.list();

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Artistes retrieved : " + result.size());

		return result.get(0);
	}
	
	/**
	 * Efface tous les artiste en BDD.
	 * @return
	 * @throws Exception
	 */
	public List<Artiste> deleteAllArtistes() throws Exception {

		logger.info("Deleting all Artistes...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(Artiste.class);
		
		List<Artiste> result = criteria.list();
		
		result.forEach( resultat -> session.delete(resultat) );

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Artistes deleted : " + result.size() + "\n");

		return result;
	}

}
