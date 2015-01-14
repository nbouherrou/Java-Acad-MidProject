package org.jacademie.projet1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Classe qui represente la clé en BDD d'un Album.
 * Elle implemente "Serializable", hash et equals afin d'etre utilisée comme une clé primaire composée
 * @author jacademie-team
 *
 * ANNOTATION - MAPPING
 * On declare la classe pour l'injecter dans comme composite-id
 */
@Embeddable
public class AlbumId implements Serializable{
	
	/**
	 * ANNOTATION - MAPPING
	 * Colonne "ALBUM_ID_PK_ALBUM"
	 */
	@Column(name = "ALBUM_ID_PK_ALBUM")
	private Integer idAlbum;
	
	/**
	 * ANNOTATION - MAPPING
	 * Colonne "ALBUM_ID_PK_ARTISTE"
	 */
	@Column(name = "ALBUM_ID_PK_ARTISTE")
	private Integer idArtiste;
	

	/**
	 * Constructeur avec parametres de la classe AlbumId.
	 * @param Integer idAlbum		: codeAlbum
	 * @param Integer idArtiste		: codeArtiste
	 */
	public AlbumId(Integer idAlbum, Integer idArtiste) {
		
		super();
		
		this.idAlbum = idAlbum;
		
		this.idArtiste = idArtiste;
	}
	
	/**
	 * Constructeur sans parametres de la classe AlbumId
	 */
	public AlbumId() {
		
		super();
		
	}
	

	public Integer getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Integer idAlbum) {
		this.idAlbum = idAlbum;
	}

	public Integer getIdArtiste() {
		return idArtiste;
	}

	public void setIdArtiste(Integer idArtiste) {
		this.idArtiste = idArtiste;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAlbum == null) ? 0 : idAlbum.hashCode());
		result = prime * result
				+ ((idArtiste == null) ? 0 : idArtiste.hashCode());
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
		
		
		AlbumId other = (AlbumId) obj;
		if (idAlbum == null) {
			if (other.idAlbum != null)
				return false;
			
		} else if (!idAlbum.equals(other.idAlbum))
			return false;
		
		if (idArtiste == null) {
			if (other.idArtiste != null)
				return false;
		} 
		
		return (this.idAlbum.longValue() == other.getIdAlbum().longValue()) && 
				(this.idArtiste.longValue() == other.getIdArtiste().longValue());
	}

	@Override
	public String toString() {
		return "AlbumId [idArtiste=" + idArtiste + ", idAlbum=" + idAlbum + "]";
	}
	
	
	

}
