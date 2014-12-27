package org.jacademie.projet1;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jacademie.projet1.constants.Constants;
import org.jacademie.projet1.dao.AlbumDao;
import org.jacademie.projet1.dao.ArtisteDao;
import org.jacademie.projet1.dao.ChansonDao;
import org.jacademie.projet1.domain.Album;
import org.jacademie.projet1.domain.Artiste;
import org.jacademie.projet1.domain.Chanson;
import org.jacademie.projet1.utils.FileUtils;
import org.jacademie.projet1.utils.HibernateUtils;

public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		/*
		 * try {
		 * 
		 * HibernateUtils.setUp();
		 * 
		 * ArtisteDao artisteDao = new ArtisteDao();
		 * 
		 * Artiste artiste = new Artiste(); artiste.setIdArtiste(2);
		 * artiste.setNom("2Pac");
		 * 
		 * Album album1 = new Album(); album1.setIdAlbum(44);
		 * album1.setNom("Greatest Hits");
		 * 
		 * Album album2 = new Album(); album2.setIdAlbum(55);
		 * album2.setNom("Album 2");
		 * 
		 * Chanson chanson1 = new Chanson(); chanson1.setIdChanson(777);
		 * chanson1.setTitre("Changes"); chanson1.setDureeChanson(5);
		 * 
		 * 
		 * Chanson chanson2 = new Chanson(); chanson2.setIdChanson(666);
		 * chanson2.setTitre("Life Goes On"); chanson2.setDureeChanson(3);
		 * 
		 * 
		 * Chanson chanson3 = new Chanson(); chanson3.setIdChanson(333);
		 * chanson3.setTitre("Me Against the World");
		 * chanson3.setDureeChanson(3);
		 * 
		 * Chanson chanson4 = new Chanson(); chanson4.setIdChanson(555);
		 * chanson4.setTitre("Chanson 4"); chanson4.setDureeChanson(6);
		 * 
		 * 
		 * 
		 * album1.addChanson(chanson1); album1.addChanson(chanson2);
		 * album1.addChanson(chanson3);
		 * 
		 * 
		 * 
		 * album2.addChanson(chanson4);
		 * 
		 * 
		 * 
		 * artiste.addAlbum(album1); artiste.addAlbum(album2);
		 * 
		 * 
		 * artisteDao.createArtiste(artiste);
		 * 
		 * 
		 * HibernateUtils.tearDown();
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		try {

			FileUtils fileUtils = new FileUtils();

			ArrayList<Path> pathList = new ArrayList<Path>();

			pathList = fileUtils.findFileInRepoWithGoodExtension(
					Constants.path, "music");

			for (Path path : pathList) {

				try (Stream<String> lines = Files.lines(path,
						Charset.defaultCharset())) {
					lines.forEachOrdered(line -> {

						try {
							// System.out.println(line);
							String[] s = line.split(",");

							HibernateUtils.setUp();

							ArtisteDao artisteDao = new ArtisteDao();
							AlbumDao albumDao = new AlbumDao();
							ChansonDao chansonDao = new ChansonDao();

							Integer codeArtiste = Integer.parseInt(s[0]);
							String nomArtiste = s[1];
							Integer codeAlbum = Integer.parseInt(s[2]);
							String nomAlbum = s[3];
							Integer numeroChanson = Integer.parseInt(s[4]);
							String titreChanson = s[5];
							Integer dureeChanson = Integer.parseInt(s[6]);

							if (artisteDao.findArtisteById(codeArtiste) != null) {

								Artiste artiste = new Artiste();
								artiste = artisteDao
										.findArtisteById(codeArtiste);

								if (albumDao.findAlbumById(codeAlbum) != null) {

									Album album = new Album();
									album = albumDao.findAlbumById(codeAlbum);
									
									if (chansonDao.findChansonById(numeroChanson) != null) {
										
									}else {
										
										Chanson chanson1 = new Chanson();
										chanson1.setIdChanson(numeroChanson);
										chanson1.setTitre(titreChanson);
										chanson1.setDureeChanson(dureeChanson);
										
										album.addChanson(chanson1);
										
										
										albumDao.updateAlbum(album);
										
										HibernateUtils.tearDown();
									}


								} else {

									Album album1 = new Album();
									album1.setIdAlbum(codeAlbum);
									album1.setNom(nomAlbum);

									Chanson chanson1 = new Chanson();
									chanson1.setIdChanson(numeroChanson);
									chanson1.setTitre(titreChanson);
									chanson1.setDureeChanson(dureeChanson);

									album1.addChanson(chanson1);

									artiste.addAlbum(album1);

									artisteDao.updateArtiste(artiste);

									HibernateUtils.tearDown();

								}

							} else {

								Artiste artiste = new Artiste();
								artiste.setIdArtiste(codeArtiste);
								artiste.setNom(nomArtiste);

								Album album1 = new Album();
								album1.setIdAlbum(codeAlbum);
								album1.setNom(nomAlbum);

								Chanson chanson1 = new Chanson();
								chanson1.setIdChanson(numeroChanson);
								chanson1.setTitre(titreChanson);
								chanson1.setDureeChanson(dureeChanson);

								album1.addChanson(chanson1);

								artiste.addAlbum(album1);

								artisteDao.createArtiste(artiste);

								HibernateUtils.tearDown();
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
				}

				// System.out.println(path);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
