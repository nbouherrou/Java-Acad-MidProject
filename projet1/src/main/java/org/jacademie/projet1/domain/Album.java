package org.jacademie.projet1.domain;

import java.util.HashSet;
import java.util.Set;


public class Album {
	
	private Integer idAlbum;
	
	private String nom;
	
	private Set<Chanson> chansons;
	
	private Artiste artiste;

	
	public Album() {
		
		super();
		
		this.chansons = new HashSet<Chanson>();
		
	}
	
	
	
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Album [idAlbum=");
		builder.append(idAlbum);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", chansons=");
		builder.append(chansons);
		builder.append("]");
		return builder.toString();
	}






	public void addChanson(Chanson chanson){
		
		this.chansons.add(chanson);
		
		chanson.setAlbum(this);
	}




	public Integer getIdAlbum() {
		return idAlbum;
	}




	public void setIdAlbum(Integer idAlbum) {
		this.idAlbum = idAlbum;
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




	public Artiste getArtiste() {
		return artiste;
	}




	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	
	
	

	
	
	
	
}
