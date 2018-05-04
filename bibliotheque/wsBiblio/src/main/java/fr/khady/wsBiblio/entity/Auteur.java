package fr.khady.wsBiblio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auteur")
public class Auteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aut")
	private long idAuteur;
	private String nom;
	private String prenom;
	@Column(name = "datnaiss")
	private Date dateNaiss;
	
	public Auteur() {}

	public Auteur(long idAuteur, String nom, String prenom, Date dateNaiss) {
		super();
		this.idAuteur = idAuteur;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = dateNaiss;
	}

	public long getIdAuteur() {
		return idAuteur;
	}

	public void setIdAuteur(long idAuteur) {
		this.idAuteur = idAuteur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	
	
	
}
