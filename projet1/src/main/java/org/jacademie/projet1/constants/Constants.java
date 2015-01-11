package org.jacademie.projet1.constants;

public class Constants {

	public static final String SOURCE_PATH 				= System.getProperty("user.dir")+"/src/main/resources/music/";

	public static final String DEST_PATH 				= System.getProperty("user.dir")+"/src/main/resources/processed/";

	public static final String EXTENSION 				= "music";

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
