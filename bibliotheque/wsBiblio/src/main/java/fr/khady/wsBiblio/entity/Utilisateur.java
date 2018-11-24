package fr.khady.wsBiblio.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	private String nom;
	private String prenom;
	private String adress;
	private String email;
	private String login;
	private String password;
	private String photo;
//	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST) 
//	private List<Reservation> reservations; 

	public Utilisateur() {
	}

	public Utilisateur(String nom, String prenom, String adress, String email, String login,
			String password, String photo) {
		this.nom = nom;
		this.prenom = prenom;
		this.adress = adress;
		this.email = email;
		this.login = login;
		this.password = password;
		this.photo = photo;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return String.format("Utilisateur[id=%d, nom='%s', prenom='%s']", idUser, nom, prenom);
	}
//
//	public List<Reservation> getReservations() {
//		return reservations;
//	}
//
//	public void setReservations(List<Reservation> reservations) {
//		this.reservations = reservations;
//	}
//	
//	public void addRservation(Reservation reservation) {
//		this.reservations.add(reservation);
//	}
//
//	public void removeReservation(Reservation reservation) {
//		this.reservations.remove(reservation);
//	}
}
