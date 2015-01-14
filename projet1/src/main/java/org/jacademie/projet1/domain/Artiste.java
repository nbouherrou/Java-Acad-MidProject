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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ARTISTE")
public class Artiste implements java.io.Serializable {

	@Id
	@Column(name = "ARTISTE_ID")
	private Integer idArtiste;

	@Column(name = "NOM")
	private String nom;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Album> albums;

	public Artiste() {

		super();

		this.albums = new HashSet<Album>();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Artiste [idArtiste=");
		builder.append(idArtiste);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", albums=");
		builder.append(albums);
		builder.append("]");
		return builder.toString();
	}

	public void addAlbum(Album album) {

		album.setArtiste(this);

		this.albums.add(album);

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public Integer getIdArtiste() {
		return idArtiste;
	}

	public void setIdArtiste(Integer idArtiste) {
		this.idArtiste = idArtiste;
	}

}
