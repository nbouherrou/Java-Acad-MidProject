package org.jacademie.projet1.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.jacademie.projet1.domain.Album;
import org.jacademie.projet1.domain.Artiste;
import org.jacademie.projet1.utils.HibernateUtils;

public class AlbumDao {
	
	private static Logger logger = LogManager.getLogger(AlbumDao.class);

	public void createAlbum(Album album) throws Exception {

		logger.info("Creating album : " + album + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.save(album);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Album created.");
	}
	
	public Album findAlbumById(int id) throws Exception {

		logger.info("Finding album with id : " + id + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		Album result = (Album) session.get(Album.class, id);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		if (result != null) {
			
			logger.info("Album found : " + result);
		} else {
			logger.info("Album not found");
		}

		return result;
	}
	
	public void updateAlbum(Album album) throws Exception {

		logger.info("Updating Album : " + album + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.merge(album);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Album updated.");
	}

}
