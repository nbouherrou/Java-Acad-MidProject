package org.jacademie.projet1.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jacademie.projet1.domain.Album;
import org.jacademie.projet1.domain.AlbumId;
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

		logger.info("Album created. \n");
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

	public Album findAlbumByIdAndIdArtiste(int id, int idArtiste)
			throws Exception {

		logger.info("findAlbumByIdAndIdArtiste album_id : " + id
				+ ", artiste_id : " + idArtiste);

		Session session = HibernateUtils.getSession();

		session.beginTransaction();
		
		AlbumId albumID  = new AlbumId(id,idArtiste);
		
		Album album = (Album) session.get(Album.class, albumID);
		
		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		if (album != null) {

			logger.info("Album found : " + album);
			
		} else {
			
			logger.info("Album not found");
		}

		return album;
	}

	public void updateAlbum(Album album) throws Exception {

		logger.info("Updating Album : " + album + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.merge(album);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Album updated. \n");
	}

	public List<Album> retrieveAllAlbums() throws Exception {

		logger.info("Retrieving all Albums...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(Album.class);

		List<Album> result = criteria.list();

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Albums retrieved : " + result.size());

		return result;
	}

	public List<Album> deleteAllAlbums() throws Exception {

		logger.info("Deleting all Artistes...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(Album.class);

		List<Album> result = criteria.list();

		result.forEach(resultat -> session.delete(resultat));

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Albums deleted : " + result.size());

		return result;
	}

	public static void main(String[] args) {

		try {

			HibernateUtils.setUp();

			AlbumDao albumDao = new AlbumDao();

			albumDao.findAlbumByIdAndIdArtiste(1, 1);

			HibernateUtils.tearDown();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
