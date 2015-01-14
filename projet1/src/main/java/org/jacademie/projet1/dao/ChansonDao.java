package org.jacademie.projet1.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jacademie.projet1.domain.AlbumId;
import org.jacademie.projet1.domain.Chanson;
import org.jacademie.projet1.domain.ChansonId;
import org.jacademie.projet1.utils.HibernateUtils;


/**
 * Classe qui gère l'accès en BDD aux abjets Chanson.
 * @author jacademie-team
 *
 */
public class ChansonDao {

	/**
	 * Champs logger pour afficher les messages 
	 */
	private static Logger logger = LogManager.getLogger(ChansonDao.class);

	/**
	 * Persiste un Artiste en BDD.
	 * @param chanson		: objet Artiste
	 * @throws Exception
	 */
	public void createChanson(Chanson chanson) throws Exception {

		logger.info("Creating chanson : " + chanson + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.save(chanson);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Chanson created. \n");
	}
	
	/**
	 * Recupere un objet Artiste de la BDD a partir de son identifiant (objet chansonID).
	 * 
	 * @param chansonID		: identifiant d'une chanson
	 * @return
	 * @throws Exception
	 * 
	 * @see ChansonId
	 */
	public Chanson findChansonById(ChansonId chansonID) throws Exception {

		logger.info("findChansonById chansonID : "+ chansonID.toString());

		Session session = HibernateUtils.getSession();

		session.beginTransaction();
		
		Chanson chanson = (Chanson) session.get(Chanson.class, chansonID);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		if (chanson != null) {

			logger.info("Chanson found : " + chanson);
			
		} else {
			
			logger.info("Chanson not found");
		}

		return chanson;
	}
	
	
	/**
	 * Met a jour les données d'un objet Chanson en les persistant en BDD.
	 * @param chanson		: objet Chanson
	 * @throws Exception
	 */
	public void updateChanson(Chanson chanson) throws Exception {

		logger.info("Updating Chanson : " + chanson + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.merge(chanson);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Chanson updated. \n");
	}
	
	/**
	 * Recupere la liste de toutes les chansons en BDD.
	 * @return
	 * @throws Exception
	 */
	public List<Chanson> retrieveAllChansons() throws Exception {

		logger.info("Retrieving all Chansons...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(Chanson.class);
		
		List<Chanson> result = criteria.list();

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Chansons retrieved : " + result.size());

		return result;
	}
	
	/**
	 * Efface toutes les chansons de la BDD.
	 * @return
	 * @throws Exception
	 */
	public List<Chanson> deleteAllAlbums() throws Exception {

		logger.info("Deleting all Chansons...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(Chanson.class);
		
		List<Chanson> result = criteria.list();
		
		result.forEach( resultat -> session.delete(resultat) );

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Chansons deleted : " + result.size());

		return result;
	}
	
	
	
}
