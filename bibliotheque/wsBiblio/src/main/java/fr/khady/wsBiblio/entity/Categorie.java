package fr.khady.wsBiblio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cat")
	private long idCat;
	private String libelle;
	
	public Categorie() {}

	public Categorie(long idCat, String libelle) {
		super();
		this.idCat = idCat;
		this.libelle = libelle;
	}

	public long getIdCat() {
		return idCat;
	}

	public void setIdCat(long idCat) {
		this.idCat = idCat;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
	
}
