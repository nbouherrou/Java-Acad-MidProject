package org.jacademie.projet1.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jacademie.projet1.domain.Album;
import org.jacademie.projet1.domain.AlbumId;
import org.jacademie.projet1.utils.HibernateUtils;

/**
 * Classe qui gère l'accès en BDD aux abjets ALBUM.
 * @author jacademie-team
 *
 */
public class AlbumDao {

	/**
	 * Champs logger pour afficher les messages 
	 */
	private static Logger logger = LogManager.getLogger(AlbumDao.class);

	/**
	 * Persiste un Album en BDD.
	 * 
	 * @param album			: Objet Album
	 * @throws Exception
	 */
	public void createAlbum(Album album) throws Exception {

		logger.info("Creating album : " + album + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.save(album);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Album created. \n");
	}

	/**
	 * Recupere un objet Album de la BDD a partir de son identifiant (objet AlbumId)
	 * 
	 * @param albumID		: identifiant d'un album
	 * @return				: album
	 * @throws Exception
	 * 
	 * @see AlbumId
	 */
	public Album findAlbumById(AlbumId albumID) throws Exception {

		logger.info("findAlbumById albumID : " + albumID.toString());

		Session session = HibernateUtils.getSession();

		session.beginTransaction();
		
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

	/**
	 * Met a jour les données (enfants aussi, Chansons) d'un objet Album en les persistant en BDD.
	 * @param album			: objet album
	 * @throws Exception
	 */
	public void updateAlbum(Album album) throws Exception {

		logger.info("Updating Album : " + album + "...");

		Session session = HibernateUtils.getSession();

		session.beginTransaction();

		session.merge(album);

		session.getTransaction().commit();

		HibernateUtils.closeSession(session);

		logger.info("Album updated. \n");
	}

	/**
	 * Recupere la liste de tous les albums en BDD.
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * Efface tous les albums en BDD.
	 * @return
	 * @throws Exception
	 */
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


}
