package fr.khady.wsBiblio.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pret")
public class Pret implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pret")
	private long idPret;
	@Column(name = "date_sortie")
	private Date dateSortie;
	@Column(name = "date_retour")
	private Date dateRetourPrevu;
	@JoinColumn(name = "id_exemp")
	private Exemplaire exemplaire;
	@JoinColumn(name = "id_user")
	private Utilisateur user;
	private Boolean rendu;
	
	public Pret() {}


	public Pret(long idPret, Date dateSortie, Date dateRetourPrevu, Exemplaire exemplaire, Utilisateur user,
			Boolean rendu) {
		super();
		this.idPret = idPret;
		this.dateSortie = dateSortie;
		this.dateRetourPrevu = dateRetourPrevu;
		this.exemplaire = exemplaire;
		this.user = user;
		this.rendu = rendu;
	}

	public long getIdPret() {
		return idPret;
	}

	public void setIdPret(long idPret) {
		this.idPret = idPret;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Date getDateRetourPrevu() {
		return dateRetourPrevu;
	}

	public void setDateRetourPrevu(Date dateRetourPrevu) {
		this.dateRetourPrevu = dateRetourPrevu;
	}



	public Exemplaire getExemplaire() {
		return exemplaire;
	}



	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}



	public Utilisateur getUser() {
		return user;
	}



	public void setUser(Utilisateur user) {
		this.user = user;
	}


	public Boolean isRendu() {
		return rendu;
	}


	public void setRendu(Boolean rendu) {
		this.rendu = rendu;
	}


	
	
}
