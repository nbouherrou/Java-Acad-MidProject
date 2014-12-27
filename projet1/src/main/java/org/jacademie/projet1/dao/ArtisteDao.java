package org.jacademie.projet1.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.jacademie.projet1.domain.Artiste;
import org.jacademie.projet1.utils.HibernateUtils;

public class ArtisteDao {

	private static Logger logger = LogManager.getLogger(ArtisteDao.class);

	public void createArtiste(Artiste artiste) throws Exception {

		logger.info("Creating artiste : " + artiste + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.save(artiste);

		session.getTransaction().commit();

		logger.info("Artiste created.");
		HibernateUtils.closeSession(session);

	}

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

	public void updateArtiste(Artiste artiste) throws Exception {

		logger.info("Updating artiste : " + artiste + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.merge(artiste);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Artiste updated.");
	}

}
