package fr.khady.wsBiblio.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ouvrage")
public class Ouvrage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ouvrage")
	private long idOuvrage;
	private String isbn;
	private String titre;
	private String resume;
	private int nbrpage;
	@Column(name = "date_parution")
	private Date dateParution;
	private Boolean disponibilite;
	@Column(name = "photocouverture")
	private String photoCouverture;
	@JoinColumn(name = "id_cat")
	private Categorie categorie;
	@JoinColumn(name = "id_aut")
	private Auteur auteur;
	@Column(name = "nbr_exep_libre")
	private Integer nombreExplLibre;
//	@OneToMany(mappedBy = "ouvrage", cascade = CascadeType.PERSIST) 
//	private List<Reservation> reservations; 

	public Ouvrage() {}


	
	public Ouvrage(String isbn, String titre, String resume, int nbrpage, Date dateParution,
			Boolean disponibilite, String photoCouverture, Categorie categorie, Auteur auteur) {
		super();
		this.isbn = isbn;
		this.titre = titre;
		this.resume = resume;
		this.nbrpage = nbrpage;
		this.dateParution = dateParution;
		this.disponibilite = disponibilite;
		this.photoCouverture = photoCouverture;
		this.categorie = categorie;
		this.auteur = auteur;
	}



	public long getIdOuvrage() {
		return idOuvrage;
	}

	public void setIdOuvrage(long idOuvrage) {
		this.idOuvrage = idOuvrage;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public int getNbrpage() {
		return nbrpage;
	}

	public void setNbrpage(int nbrpage) {
		this.nbrpage = nbrpage;
	}

	public Date getDateParution() {
		return dateParution;
	}

	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}



	public Boolean getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	public String getPhotoCouverture() {
		return photoCouverture;
	}

	public void setPhotoCouverture(String photoCouverture) {
		this.photoCouverture = photoCouverture;
	}



	public Categorie getCategorie() {
		return categorie;
	}



	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Auteur getAuteur() {
		return auteur;
	}


	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}


//	public List<Reservation> getReservations() {
//		return reservations;
//	}
//
//
//	public void setReservations(List<Reservation> reservations) {
//		this.reservations = reservations;
//	}

	
}
