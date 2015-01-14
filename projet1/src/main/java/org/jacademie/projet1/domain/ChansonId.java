package org.jacademie.projet1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChansonId implements Serializable {

	@Column(name = "CHANSON_ID_PK_CHANSON")
	private Integer idChanson;

	@Column(name = "CHANSON_ID_PK_ALBUM_ID")
	private AlbumId albumID;

	public ChansonId(Integer idChanson, AlbumId albumID) {
		super();
		this.idChanson = idChanson;
		this.albumID = albumID;
	}

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

	public Integer getIdChanson() {
		return idChanson;
	}

	public void setIdChanson(Integer idChanson) {
		this.idChanson = idChanson;
	}

	public AlbumId getAlbumID() {
		return albumID;
	}

}
