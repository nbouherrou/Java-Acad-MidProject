package org.jacademie.projet1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CHANSON")
public class Chanson implements java.io.Serializable {
	
	@Id
	@Column(name = "CHANSON_ID")
	private ChansonId chansonID;
	
	@Column(name = "TITRE") 
	private String titre;
	
	public Chanson(ChansonId chansonID, String titre, Integer dureeChanson,
			Album album) {
		super();
		this.chansonID = chansonID;
		this.titre = titre;
		this.dureeChanson = dureeChanson;
		this.album = album;
	}


	@Column(name = "DUREE") 
	private Integer dureeChanson;
	
	@ManyToOne
	private Album album;
	

	public Chanson() {
		super();
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chanson [idChanson=");
		builder.append(chansonID.toString());
		builder.append(", titre=");
		builder.append(titre);
		builder.append(", dureeChanson=");
		builder.append(dureeChanson);
		builder.append("]");
		return builder.toString();
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

	@Transient
	public Album getAlbum() {
		return album;
	}

	@Transient
	public void setAlbum(Album album) {
		this.album = album;
	}


	public ChansonId getChansonID() {
		return chansonID;
	}


	public void setChansonID(ChansonId chansonID) {
		this.chansonID = chansonID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dureeChanson == null) ? 0 : dureeChanson.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		Chanson other = (Chanson) obj;
		if (dureeChanson == null) {
			if (other.dureeChanson != null)
				return false;
		} else if (!dureeChanson.equals(other.dureeChanson))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return (this.getTitre().equals(other) && this.getDureeChanson() == other.getDureeChanson());
	}

	


}
