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


/**
 * Classe qui gère un album ainsi que ses musiques.
 * @author jacademie-team
 *
 * ANNOTATION - MAPPING
 * On persiste la class dans une table qu'on nomme : ALBUM
 */
@Entity
@Table(name = "ALBUM")
public class Album implements java.io.Serializable {

	/**
	 * ANNOTATION - MAPPING
	 * Clé primaire de la table avec un nom de colonne "ALBUM_ID"
	 * Remarque : 
	 * cet objet (contenant 2 champs) represente une clé primaire composée pour cette table.
	 * 
	 * @see AlbumId
	 */
	@Id
	@Column(name = "ALBUM_ID")
	private AlbumId albumID;

	
	/**
	 * ANNOTATION - MAPPING
	 * Colonne "NOM" de la table (contiendra le nom de l'album) 
	 */
	@Column(name = "NOM")
	private String nom;

	/**
	 * ANNOTATION - MAPPING
	 * Lien (1 -> N)  entre un album et sa liste de musique.
	 * Cette relation injecte les clés étrangères (FOREIGN KEYS) adéquates.
	 * 
	 * @see Chanson
	 */
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Chanson> chansons;

	/**
	 * ANNOTATION - MAPPING
	 * Ce champs ne sera pas persisté en BDD lors du mapping/sauvegarde de l'objet.
	 */
	@Transient
	private Artiste artiste;
	
	

	/**
	 * Constructeur avec paramètres de la classe Album.
	 * 
	 * @param AlbumId albumID 		: identifiant d'un album (obbjet AlbumId , modelisation d'une clée primaire composées)
	 * @param String nom			: nom de l'album
	 * @param Set<Chanson> chansons	: liste des chansons présentes dans l'album			: 
	 * @param artiste				: l'artiste qui a produit cet album (objet Artiste)
	 * 
	 * @see  AlbumId, Artiste
	 */
	public Album(AlbumId albumID, String nom, Set<Chanson> chansons,
			Artiste artiste) {
		
		super();
		
		this.albumID = albumID;
		
		this.nom = nom;
		
		this.chansons = chansons;
		
		this.artiste = artiste;
	}
	
	/**
	 * Constructeur sans paramètres de la classe Album
	 */
	public Album() {

		super();

		this.chansons = new HashSet<Chanson>();

	}

	/**
	 * Ajout d'une chanson dans à cet album.
	 * @param Chanson chanson : chanson
	 */
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
	
	public AlbumId getAlbumID() {
		return albumID;
	}

	public void setAlbumID(AlbumId albumID) {
		this.albumID = albumID;
	}

	@Transient
	public Artiste getArtiste() {
		return artiste;
	}
	
	@Transient
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
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


}
