package org.jacademie.projet1.domain;

public class Chanson {
	
	private Integer idChanson;
	
	private String titre;
	
	private Integer dureeChanson;
	
	private Album album;
	

	public Chanson() {
		super();
	}

	
	


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chanson [idChanson=");
		builder.append(idChanson);
		builder.append(", titre=");
		builder.append(titre);
		builder.append(", dureeChanson=");
		builder.append(dureeChanson);
		builder.append("]");
		return builder.toString();
	}





	public Integer getIdChanson() {
		return idChanson;
	}


	public void setIdChanson(Integer idChanson) {
		this.idChanson = idChanson;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Integer getDureeChanson() {
		return dureeChanson;
	}


	public void setDureeChanson(Integer dureeChanson) {
		this.dureeChanson = dureeChanson;
	}


	public Album getAlbum() {
		return album;
	}


	public void setAlbum(Album album) {
		this.album = album;
	}

	
	


}
