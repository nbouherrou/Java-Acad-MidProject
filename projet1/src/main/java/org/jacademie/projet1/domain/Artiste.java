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

/**
 * Classe qui gère un artiste.
 * @author jacademie-team
 *
 * ANNOTATION - MAPPING
 * On persiste la classe dans une table qu'on nomme :  ARTISTE
 */
@Entity
@Table(name = "ARTISTE")
public class Artiste implements java.io.Serializable {

	
	/**
	 * ANNOTATION - MAPPING
	 * Clé primaire de la table avec un nom de colonne "ARTISTE_ID"
	 */
	@Id
	@Column(name = "ARTISTE_ID")
	private Integer idArtiste;

	/**
	 * ANNOTATION - MAPPING
	 * Colonne "NOM" de la table (contiendra le nom de l'album) 
	 */
	@Column(name = "NOM")
	private String nom;

	
	/**
	 * ANNOTATION - MAPPING
	 * Lien (1 -> N)  entre un artiste et sa liste d'albums.
	 * Cette relation injecte les clés étrangères (FOREIGN KEYS) adéquates.
	 * 
	 * @see Album
	 */
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Album> albums;
	

	/**
	 * Constructeur avec parametres de la classe Artiste.
	 * 
	 * @param idArtiste		: identifiant d'un artiste (clée primaire de la table)
	 * @param nom			: nom de l'artiste
	 * @param albums		: liste des albums appartenant à cet artiste	
	 */
	public Artiste(Integer idArtiste, String nom, Set<Album> albums) {
		super();
		this.idArtiste = idArtiste;
		this.nom = nom;
		this.albums = albums;
	}

	/**
	 * Constructeur sans parametres de la classe Chanson.
	 */
	public Artiste() {

		super();

		this.albums = new HashSet<Album>();
	}

	/**
	 * Ajout d'un album pour cet artiste.
	 * @param album
	 */
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


}
