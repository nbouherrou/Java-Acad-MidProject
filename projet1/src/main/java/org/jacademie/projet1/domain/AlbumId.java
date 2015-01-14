package org.jacademie.projet1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AlbumId implements Serializable{
	
	@Column(name = "ALBUM_ID_PK_ALBUM")
	private Integer idAlbum;
	
	@Column(name = "ALBUM_ID_PK_ARTISTE")
	private Integer idArtiste;
	

	public AlbumId() {
		super();
	}

	public AlbumId(Integer idAlbum, Integer idArtiste) {
		super();
		this.idAlbum = idAlbum;
		this.idArtiste = idArtiste;
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
