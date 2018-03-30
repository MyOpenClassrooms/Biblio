package fr.khady.wsBiblio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "exemplaire")
public class Exemplaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_exemp")
	private long idExemp;
	@Column(name = "nbrexemp")
	private int nbrExemplaire;
	@Column(name = "num_rayon")
	private int numRayon;
	@JoinColumn(name = "id_ouvrage")
	private Ouvrage ouvrage;
	
	public Exemplaire() {}

	public Exemplaire(int nbrExemplaire, int numRayon, Ouvrage ouvrage) {
		super();
		this.nbrExemplaire = nbrExemplaire;
		this.numRayon = numRayon;
		this.ouvrage = ouvrage;
	}

	public long getIdExemp() {
		return idExemp;
	}

	public void setIdExemp(long idExemp) {
		this.idExemp = idExemp;
	}

	public int getNbrExemplaire() {
		return nbrExemplaire;
	}

	public void setNbrExemplaire(int nbrExemplaire) {
		this.nbrExemplaire = nbrExemplaire;
	}

	public int getNumRayon() {
		return numRayon;
	}

	public void setNumRayon(int numRayon) {
		this.numRayon = numRayon;
	}

	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
	}


	
	
	
	
}
