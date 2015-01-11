package org.jacademie.projet1;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;

public class TestMain {
	
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestMain.class);

	public static void main(String[] args) throws IOException {
		
		final String [] FILE_HEADER_MAPPING = {"CodeArtiste", "NomArtiste", "CodeAlbum", 
												"NomAlbum", "NumeroChanson", "TitreChanson", "DureeChanson"};
		
		final String CODE_ARTISTE 		= "CodeArtiste";
		
		final String NOM_ARTISTE 		= "NomArtiste";
	
	    final String CODE_ALBUM 		= "CodeAlbum";
	
	    final String NOM_ALBUM 			= "NomAlbum"; 
	
	    final String NUMERO_CHANSON 	= "NumeroChanson";
	    
	    final String TITRE_CHANSON 		= "TitreChanson";
	    
	    final String DUREE_CHANSON 		= "DureeChanson";
	    
		
		// Chemin au DIR du projet
		logger.info(System.getProperty("user.dir"));
		
		final String TO_RESSOURCE = System.getProperty("user.dir")+"/src/main/resources/music/";
		
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

		FileReader fileReader = null;

		CSVParser csvFileParser = null;
		
		fileReader = new FileReader(TO_RESSOURCE+"fichier1.music");
		
		csvFileParser = new CSVParser(fileReader, csvFileFormat);
		
//		List csvRecords = csvFileParser.getRecords();
		
		for(CSVRecord record : csvFileParser.getRecords()){
			
			logger.info("Size : "+ record.size()+" - isConsistent : "+ record.isConsistent());
			
			logger.info(record.get(NOM_ALBUM));
		}
		
		


	}

}
