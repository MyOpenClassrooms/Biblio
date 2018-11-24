package fr.khady.wsBiblio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_resa")
	private long idResa;
	@Column(name = "date_resa")
	private Date dateReservation;
	
	@JoinColumn(name = "id_user")
	private Utilisateur user;
	
	@JoinColumn(name = "id_Ouvrage")
	private Ouvrage ouvrage;
	private int position;
	@Column(name = "date_rpp")
	private Date dateRetourPlusProche;
	
	public Reservation() {
	}

	public Reservation(long idResa, Date dateReservation, Utilisateur user, Ouvrage ouvrage,int position) {
		super();
		this.idResa = idResa;
		this.dateReservation = dateReservation;
		this.user = user;
		this.ouvrage = ouvrage;
		this.position = position;
	}

	public long getIdResa() {
		return idResa;
	}

	public void setIdResa(long idResa) {
		this.idResa = idResa;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Date getDateRetourPlusProche() {
		return dateRetourPlusProche;
	}

	public void setDateRetourPlusProche(Date dateRetourPlusProche) {
		this.dateRetourPlusProche = dateRetourPlusProche;
	}
	
	
}
