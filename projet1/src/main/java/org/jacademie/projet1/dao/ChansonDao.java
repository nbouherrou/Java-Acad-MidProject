package org.jacademie.projet1.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.jacademie.projet1.domain.Chanson;
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

		logger.info("Chanson created.");
	}
	
	public Chanson findChansonById(int id) throws Exception {

		logger.info("Finding Client with id : " + id + "...");

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
	
	public void updateChanson(Chanson chanson) throws Exception {

		logger.info("Updating Chanson : " + chanson + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.merge(chanson);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Client updated.");
	}

}