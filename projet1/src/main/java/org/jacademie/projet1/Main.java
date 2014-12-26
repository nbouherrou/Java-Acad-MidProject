package org.jacademie.projet1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jacademie.projet1.dao.AlbumDao;
import org.jacademie.projet1.dao.ArtisteDao;
import org.jacademie.projet1.dao.ChansonDao;
import org.jacademie.projet1.domain.Album;
import org.jacademie.projet1.domain.Artiste;
import org.jacademie.projet1.domain.Chanson;
import org.jacademie.projet1.utils.HibernateUtils;

public class Main {
	
	private static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		try {
			
			HibernateUtils.setUp();
			
			ArtisteDao artisteDao = new ArtisteDao();
			
			Artiste artiste = new Artiste();
			artiste.setIdArtiste(2);
			artiste.setNom("2Pac");
			
			Album album1 = new Album();
			album1.setIdAlbum(44);
			album1.setNom("Greatest Hits");
			
			Album album2 = new Album();
			album2.setIdAlbum(55);
			album2.setNom("Album 2");
			
			Chanson chanson1 = new Chanson();
			chanson1.setIdChanson(777);
			chanson1.setTitre("Changes");
			chanson1.setDureeChanson(5);
			
			
			Chanson chanson2 = new Chanson();
			chanson2.setIdChanson(666);
			chanson2.setTitre("Life Goes On");
			chanson2.setDureeChanson(3);
			
			
			Chanson chanson3 = new Chanson();
			chanson3.setIdChanson(333);
			chanson3.setTitre("Me Against the World");
			chanson3.setDureeChanson(3);
			
			Chanson chanson4 = new Chanson();
			chanson4.setIdChanson(555);
			chanson4.setTitre("Chanson 4");
			chanson4.setDureeChanson(6);
			
			
			
			album1.addChanson(chanson1);
			album1.addChanson(chanson2);
			album1.addChanson(chanson3);
			
			
			
			album2.addChanson(chanson4);
			
			
			
			artiste.addAlbum(album1);
			artiste.addAlbum(album2);
			

			artisteDao.createArtiste(artiste);
			

			HibernateUtils.tearDown();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
