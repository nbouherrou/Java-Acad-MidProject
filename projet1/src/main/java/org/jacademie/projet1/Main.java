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

		try {
			logger.info("Welcome to MUSIC Party !!!!!!!!!!!!!!");
			
			FileUtils fileUtils = new FileUtils();
			
			ArrayList<Path> pathList = new ArrayList<Path>();

			pathList = fileUtils.findFileInRepoWithGoodExtension(
					Constants.pathSource, Constants.extension);

			for (Path path : pathList) {

				try (Stream<String> lines = Files.lines(path,
						Charset.defaultCharset())) {
					lines.forEachOrdered(line -> {

						try {

							String[] s = line.split(",");

							if (s.length == 7) {
								
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
										
										if (chansonDao
												.findChansonById(numeroChanson) != null) {
											
										} else {
											
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
								
								
							}else {

								logger.info("Encountered problem in file  : " + path);
								
							}
							

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});
				}
				
				pathList.stream().forEach(pat ->{
					// TODO
				});
				

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("GoodBye !");

	}

}
