package org.jacademie.projet1.controller;

import java.io.FileReader;
import java.nio.file.Path;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jacademie.projet1.constants.Constants;
import org.jacademie.projet1.dao.AlbumDao;
import org.jacademie.projet1.dao.ArtisteDao;
import org.jacademie.projet1.dao.ChansonDao;
import org.jacademie.projet1.domain.Album;
import org.jacademie.projet1.domain.AlbumId;
import org.jacademie.projet1.domain.Artiste;
import org.jacademie.projet1.domain.Chanson;
import org.jacademie.projet1.domain.ChansonId;
import org.jacademie.projet1.utils.HibernateUtils;

public class MainControl {

	private static Logger logger = LogManager.getLogger(MainControl.class);

	public void mainControl(Path path) {

		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withHeader(Constants.FILE_HEADER_MAPPING);

		FileReader fileReader = null;

		CSVParser csvFileParser = null;

		try {

			fileReader = new FileReader(path.toString());

			csvFileParser = new CSVParser(fileReader, csvFileFormat);

			HibernateUtils.setUp();

			for (CSVRecord record : csvFileParser.getRecords()) {

				logger.info("LineSize : " + record.size()
						+ " - isConsistent : " + record.isConsistent());

				logger.info(record);

				if (record.isConsistent()) {

					ArtisteDao artisteDao 		= new ArtisteDao();

					AlbumDao albumDao 			= new AlbumDao();

					ChansonDao chansonDao 		= new ChansonDao();

					Integer codeArtiste = Integer.parseInt(record
							.get(Constants.CODE_ARTISTE));

					String nomArtiste = record.get(Constants.NOM_ARTISTE);

					Integer codeAlbum = Integer.parseInt(record
							.get(Constants.CODE_ALBUM));

					String nomAlbum = record.get(Constants.NOM_ALBUM);

					Integer numeroChanson = Integer.parseInt(record
							.get(Constants.NUMERO_CHANSON));

					String titreChanson = record.get(Constants.TITRE_CHANSON);

					Integer dureeChanson = Integer.parseInt(record
							.get(Constants.DUREE_CHANSON));

					Artiste artiste = new Artiste();

					artiste = artisteDao.findArtisteById(codeArtiste);

					if (artiste != null) {

						Album album = albumDao.findAlbumByIdAndIdArtiste(codeAlbum,codeArtiste);
						
						if(album != null ){
							
							Chanson chanson = chansonDao.findChansonByIdAndIdAlbum(numeroChanson, codeAlbum);
							
							if(chanson != null){
								
								logger.info("Nothing has change, song already exists ! ");
								
							}else{
								
								Chanson chanson1 = new Chanson();
								
								ChansonId chansonID = new ChansonId(numeroChanson, codeAlbum);

								chanson1.setChansonID(chansonID);

								chanson1.setTitre(titreChanson);

								chanson1.setDureeChanson(dureeChanson);
								

								album.addChanson(chanson1);

								albumDao.updateAlbum(album);
								

								logger.info("New song added to the Album");
								
							}
							
						}else{
							
							Album album1 = new Album();
							
							album1.setAlbumID(new AlbumId(codeAlbum,codeArtiste));;

							album1.setNom(nomAlbum);

							artiste.addAlbum(album1);

							artisteDao.updateArtiste(artiste);
		
						}

					} else {
						
						Chanson chanson = new Chanson();

						ChansonId chansonID = new ChansonId(numeroChanson, codeAlbum);

						chanson.setChansonID(chansonID);

						chanson.setDureeChanson(dureeChanson);

		
						Album album = new Album();
						
						album.setAlbumID(new AlbumId(codeAlbum,codeArtiste));;

						album.setNom(nomAlbum);
						
						album.addChanson(chanson);
					
						
						Artiste art = new Artiste();

						art.setIdArtiste(codeArtiste);

						art.setNom(nomArtiste);	
						
						art.addAlbum(album);
						

						artisteDao.createArtiste(art);

					}

				}
				
			}

			HibernateUtils.tearDown();

		} catch (Exception e1) {
			
			logger.info("Encountered problem in file  : " + path);

			e1.printStackTrace();

		}

	}

}
