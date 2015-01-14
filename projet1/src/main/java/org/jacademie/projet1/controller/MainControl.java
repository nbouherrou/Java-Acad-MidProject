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

/**
 * Classe qui gère la logique de l'application
 * @author jacademie-team
 *
 */
public class MainControl {

	/**
	 * Champs logger pour afficher les messages 
	 */
	private static Logger logger = LogManager.getLogger(MainControl.class);

	
	/**
	 * Fonction coeur de l'application.
	 * 1/ parse les fichiers 
	 * 2/ mets à jour la BDD
	 * 
	 * @param path		: Chemin du fichier
	 */
	public void mainControl(Path path) {
		
		// Initialisation du header CSV, parser et lecteur de fichier
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(Constants.FILE_HEADER_MAPPING);

		FileReader fileReader 	= null;

		CSVParser csvFileParser = null;

		try {

			fileReader = new FileReader(path.toString());

			csvFileParser = new CSVParser(fileReader, csvFileFormat);

			HibernateUtils.setUp();
			
			// On construit nos DAO
			ArtisteDao artisteDao 		= new ArtisteDao();

			AlbumDao albumDao 			= new AlbumDao();

			ChansonDao chansonDao 		= new ChansonDao();
			
			// Pour chaque ligne dans le fichier CSV
			for (CSVRecord record : csvFileParser.getRecords()) {

				logger.info("LineSize : " + record.size() + " - isConsistent : " + record.isConsistent());

				logger.info(record);

				if (record.isConsistent()) {				
					
					// initialisation des variables avec les données de la ligne

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
					
					// Création des ID (Album , Chanson) 
					
					AlbumId 	albumID 	= new AlbumId(codeAlbum,codeArtiste);
					
					ChansonId 	chansonID 	= new ChansonId(numeroChanson, albumID);
					
					// recherche de l'artiste en BDD

					Artiste artiste = new Artiste();

					artiste = artisteDao.findArtisteById(codeArtiste);

					if (artiste != null) {
						
						// Si son nom a changé, on fait un UPDATE
						if(!artiste.getNom().equals(nomArtiste)){
							
							artisteDao.updateArtiste(artiste);
							
							logger.info("Artiste  details (Nom) Updated ! ");
						}

						// recherche de l'album en BDD
						
						Album album = albumDao.findAlbumById(albumID);
						
						if(album != null ){
							
							// Si le nom de l'abum a changé, on fait un UPDATE
							
							if(!album.getNom().equals(nomAlbum)){
								
								albumDao.updateAlbum(album);
								
								logger.info("Album details (Nom) Updated ! ");
							}
							
							// recherche de la chanson en BDD
							Chanson chanson = chansonDao.findChansonById(chansonID);
							
							if(chanson != null){
								
								Chanson current_chanson = new Chanson(chansonID, titreChanson, dureeChanson, album );
								
								// Si les nom/durée de la chanson ont changé, on fait un UPDATE
								if(!chanson.equals(current_chanson)){
									
									logger.info("Song  details (Titre,Duree) Updated ! ");
									
								}else{
									
									// Dans le cas ou la chanson existe en BDD
									
									logger.info("Nothing has change, song already exists ! ");
								
								}
								
							}else{
								
								// Dans le cas ou la chanson n'existe pas en BDD
								
								Chanson chanson1 = new Chanson();
						
								chanson1.setChansonID(chansonID);

								chanson1.setTitre(titreChanson);

								chanson1.setDureeChanson(dureeChanson);
								

								album.addChanson(chanson1);

								albumDao.updateAlbum(album);
								

								logger.info("New song added to the Album");
								
							}
							
						}else{
							
							// Dans le cas ou l'abum n'existe pas en BDD

							Chanson chanson = new Chanson();

							chanson.setChansonID(chansonID);
							
							chanson.setTitre(titreChanson);

							chanson.setDureeChanson(dureeChanson);
							
							
							Album album1 = new Album();
							
							album1.setAlbumID(albumID);;

							album1.setNom(nomAlbum);
							
							
							album1.addChanson(chanson);

							artiste.addAlbum(album1);

							artisteDao.updateArtiste(artiste);
		
						}

					} else {
						
						// Dans le cas ou la ligne (Artiste/Album/Chanson) n'exsite pas en BDD

						Chanson chanson = new Chanson();

						chanson.setChansonID(chansonID);
						
						chanson.setTitre(titreChanson);

						chanson.setDureeChanson(dureeChanson);

		
						Album album = new Album();
						
						album.setAlbumID(albumID);;

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
			
			// Erreurs lors de la lecture du fichier
			
			logger.info("Encountered problem in file  : " + path);

			e1.printStackTrace();

		}

	}

}
