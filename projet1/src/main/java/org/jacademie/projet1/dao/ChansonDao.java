package org.jacademie.projet1.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jacademie.projet1.domain.Album;
import org.jacademie.projet1.domain.Chanson;
import org.jacademie.projet1.domain.ChansonId;
import org.jacademie.projet1.utils.HibernateUtils;

public class ChansonDao {

	private static Logger logger = LogManager.getLogger(ChansonDao.class);

	public void createChanson(Chanson chanson) throws Exception {

		logger.info("Creating chanson : " + chanson + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.save(chanson);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Chanson created. \n");
	}

	public Chanson findChansonById(int id) throws Exception {

		logger.info("Finding Chanson with id : " + id + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		Chanson result = (Chanson) session.get(Chanson.class, id);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		if (result != null) {

			logger.info("Chanson found : " + result);
		} else {
			logger.info("Chanson not found");
		}

		return result;
	}
	
	public Chanson findChansonByIdAndIdAlbum(int id, int idAlbum) throws Exception {
		
		logger.info("findChansonByIdAndIdAlbum chanson_id : " + id + ", album_id : "+ idAlbum);
		
		ChansonId chansonID = new ChansonId(id, idAlbum);

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
	

	public void updateChanson(Chanson chanson) throws Exception {

		logger.info("Updating Chanson : " + chanson + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.merge(chanson);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Chanson updated. \n");
	}
	
	
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
