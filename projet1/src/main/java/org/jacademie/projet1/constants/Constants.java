package org.jacademie.projet1.constants;

public class Constants {

	/**
	 * Chemin vers le dossier music de l'application
	 */
	public static final String SOURCE_PATH 				= System.getProperty("user.dir")+"/src/main/resources/music/";

	/**
	 * Chemin vers le dossier processed de l'application
	 */
	public static final String DEST_PATH 				= System.getProperty("user.dir")+"/src/main/resources/processed/";
	
	/**
	 * Chemin vers le dossier racine de l'application
	 */
	public static final String RESSOURCE_DIR_PATH		= System.getProperty("user.dir");

	/**
	 * Extension acceptée dans l'application
	 */
	public static final String EXTENSION 				= "music";

	/**
	 * Mapping de l'entête des fichiers de l'application
	 */
	public static final String[] FILE_HEADER_MAPPING 	= { "CodeArtiste", "NomArtiste",
															"CodeAlbum", "NomAlbum", 
															"NumeroChanson", "TitreChanson",
															"DureeChanson"
															};

	public static final String CODE_ARTISTE 			= "CodeArtiste";

	public static final String NOM_ARTISTE 				= "NomArtiste";

	public static final String CODE_ALBUM 				= "CodeAlbum";

	public static final String NOM_ALBUM 				= "NomAlbum";

	public static final String NUMERO_CHANSON 			= "NumeroChanson";

	public static final String TITRE_CHANSON 			= "TitreChanson";

	public static final String DUREE_CHANSON 			= "DureeChanson";

}
