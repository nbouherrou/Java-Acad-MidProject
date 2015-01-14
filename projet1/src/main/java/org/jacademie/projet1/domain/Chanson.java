package org.jacademie.projet1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Classe qui gère une chanson.
 * @author jacademie-team
 *
 * ANNOTATION - MAPPING
 * On persiste la class dans une table qu'on nomme :  CHANSON
 */
@Entity
@Table(name = "CHANSON")
public class Chanson implements java.io.Serializable {
	
	/**
	 * ANNOTATION - MAPPING
	 * Clé primaire de la table avec un nom de colonne "ALBUM_ID"
	 * Remarque : 
	 * cet objet contenant 2 champs (numeroChanson, codeAlbum) represente une clé primaire composée pour cette table.
	 * @see ChansonId
	 */
	@Id
	@Column(name = "CHANSON_ID")
	private ChansonId chansonID;
	
	/**
	 * ANNOTATION - MAPPING
	 * Colonne "TITRE" de la table (contiendra le tire de la chanson) 
	 */
	@Column(name = "TITRE") 
	private String titre;

	/**
	 * ANNOTATION - MAPPING
	 * Colonne "DUREE" de la table (contiendra la duree d'une chanson) 
	 */
	@Column(name = "DUREE") 
	private Integer dureeChanson;
	
	/**
	 * ANNOTATION - MAPPING
	 * Ce champs ne sera pas persister en BDD lors du mapping/sauvegarde de l'objet.
	 */
	@Transient
	private Album album;
	
	
	/**
	 * Constructeur avec parametres de la classe Chanson.
	 * 
	 * @param chansonID			: identifiant d'une chanson (objet chansonID , modelisation d'une clée primaire composées)
	 * @param titre				: titre de la chanson
	 * @param dureeChanson		: durée de la chanson
	 * @param album				: l'album auquel cette chanson appartient
	 * 
	 * @see chansonID
	 */
	public Chanson(ChansonId chansonID, String titre, Integer dureeChanson,
			Album album) {
		
		super();
		
		this.chansonID = chansonID;
		
		this.titre = titre;
		
		this.dureeChanson = dureeChanson;
		
		this.album = album;
	}

	/**
	 * Constructeur sans parametres de la classe Chanson.
	 */
	public Chanson() {
		super();
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

	@Override
	/**
	 * Redefinition de la methode equals pour comparer deux objets Chanson sur le titre et la durée seulement.
	 */
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
