package org.jacademie.projet1.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ALBUM")
public class Album implements java.io.Serializable {

	@Id
	@Column(name = "ALBUM_ID")
	private AlbumId albumID;

	@Column(name = "NOM")
	private String nom;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Chanson> chansons;

	@ManyToOne
	private Artiste artiste;

	public Album() {

		super();

		this.chansons = new HashSet<Chanson>();

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Album [idAlbum=");
		builder.append(albumID.toString());
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", chansons=");
		builder.append(chansons);
		builder.append("]");
		return builder.toString();
	}

	public void addChanson(Chanson chanson) {

		chanson.setAlbum(this);

		this.chansons.add(chanson);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Chanson> getChansons() {
		return chansons;
	}

	public void setChansons(Set<Chanson> chansons) {
		this.chansons = chansons;
	}

	@Transient
	public Artiste getArtiste() {
		return artiste;
	}
	
	@Transient
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public AlbumId getAlbumID() {
		return albumID;
	}

	public void setAlbumID(AlbumId albumID) {
		this.albumID = albumID;
	}

}
