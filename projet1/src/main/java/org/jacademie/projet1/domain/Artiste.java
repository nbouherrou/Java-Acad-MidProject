package org.jacademie.projet1.domain;

import java.util.HashSet;
import java.util.Set;


public class Artiste {
	
	private Integer idArtiste;
	
	private String nom;
	
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




	public void addAlbum(Album album){
		
		this.albums.add(album);
		
		album.setArtiste(this);
	}



	public Integer getIdArtiste() {
		return idArtiste;
	}



	public void setIdArtiste(Integer idArtiste) {
		this.idArtiste = idArtiste;
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


	
	
	

	
}