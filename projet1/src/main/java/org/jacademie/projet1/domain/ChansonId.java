package org.jacademie.projet1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChansonId implements Serializable{
	
	@Column(name = "CHANSON_ID_PK_CHANSON")
	private Integer idChanson;
	
	@Column(name = "CHANSON_ID_PK_ALBUM")
	private Integer idAlbum;


	public ChansonId() {
		super();
	}

	public ChansonId(Integer idChanson, Integer idAlbum) {
		super();
		this.idAlbum 	= idAlbum;
		this.idChanson 	= idChanson;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAlbum == null) ? 0 : idAlbum.hashCode());
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
		if (idAlbum == null) {
			if (other.idAlbum != null)
				return false;
			
		} else if (!idAlbum.equals(other.idAlbum))
			return false;
		
		if (idChanson == null) {
			if (other.idChanson != null)
				return false;
		} 
		
		return (this.idAlbum.longValue() == other.getIdAlbum().longValue()) && 
				(this.idChanson.longValue() == other.getIdChanson().longValue());
	}

	public Integer getIdChanson() {
		return idChanson;
	}

	public void setIdChanson(Integer idChanson) {
		this.idChanson = idChanson;
	}

	public Integer getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Integer idAlbum) {
		this.idAlbum = idAlbum;
	}

	@Override
	public String toString() {
		return "ChansonId [idChanson=" + idChanson + ", idAlbum=" + idAlbum
				+ "]";
	}
	
	
	

}
