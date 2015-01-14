package org.jacademie.projet1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Classe qui represente la clé en BDD d'une Chanson.
 * Elle implemente "Serializable", hash et equals afin d'être utilisées comme une clé primaire composée
 * @author jacademie-team
 *
 * ANNOTATION - MAPPING
 * On déclare la classe pour l'injecter dans comme composite-id
 */
@Embeddable
public class ChansonId implements Serializable {

	/**
	 * ANNOTATION - MAPPING
	 * Colonne "CHANSON_ID_PK_CHANSON"
	 */
	@Column(name = "CHANSON_ID_PK_CHANSON")
	private Integer idChanson;

	/**
	 * ANNOTATION - MAPPING
	 * Colonne "CHANSON_ID_PK_ALBUM_ID"
	 * 
	 * @see AlbumId
	 */
	@Column(name = "CHANSON_ID_PK_ALBUM_ID")
	private AlbumId albumID;

	/**
	 * Constructeur avec parametres de la classe AlbumId.
	 * @param Integer idChanson		: numeroChanson
	 * @param AlbumId albumID		: Objet AlbumId composé du codeAlbum et du codeArtiste
	 */
	public ChansonId(Integer idChanson, AlbumId albumID) {
		super();
		this.idChanson = idChanson;
		this.albumID = albumID;
	}

	/**
	 * Constructeur sans parametres de la classe Album
	 */
	public ChansonId() {
		
		super();
		
	}

	public void setAlbumID(AlbumId albumID) {
		this.albumID = albumID;
	}

	@Override
	public String toString() {
		return "ChansonId [idChanson=" + idChanson + ", albumID=" + albumID.toString()
				+ "]";
	}
	
	public Integer getIdChanson() {
		return idChanson;
	}

	public void setIdChanson(Integer idChanson) {
		this.idChanson = idChanson;
	}

	public AlbumId getAlbumID() {
		return albumID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumID == null) ? 0 : albumID.hashCode());
		result = prime * result
				+ ((idChanson == null) ? 0 : idChanson.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		ChansonId other = (ChansonId) obj;

		if (albumID == null) {
			if (other.albumID != null)
				return false;
		} else if (!albumID.equals(other.albumID))
			return false;
		if (idChanson == null) {
			if (other.idChanson != null)
				return false;
		} else if (!idChanson.equals(other.idChanson))
			return false;

		return (this.albumID.equals(other))
				&& (this.idChanson.longValue() == other.getIdChanson()
						.longValue());
	}

}
