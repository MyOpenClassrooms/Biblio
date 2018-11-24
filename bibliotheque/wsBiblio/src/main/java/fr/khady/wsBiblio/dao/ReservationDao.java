package fr.khady.wsBiblio.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.khady.wsBiblio.entity.Exemplaire;
import fr.khady.wsBiblio.entity.Ouvrage;
import fr.khady.wsBiblio.entity.Pret;
import fr.khady.wsBiblio.entity.Reservation;
import fr.khady.wsBiblio.entity.Utilisateur;

@Stateless
public class ReservationDao {
	@PersistenceContext(unitName = "bdd_biblio_PU")
	private EntityManager entityManager;

	private static final String JPQL_SELECT_PAR_DATE_RESA = "SELECT r FROM Reservation r WHERE r.dateReservation=:dateReservation";
	private static final String JPQL_SELECT_PAR_UTILISATEUR = "SELECT r FROM Reservation r WHERE r.user.idUser=:iduser";
	private static final String JPQL_SELECT_PAR_OUVRAGE = "SELECT r FROM Reservation r WHERE r.ouvrage.idOuvrage=:idOuvrage";
	private static final String JPQL_SELECT_PAR_UTILISATEUR_OUVRAGE = "SELECT r FROM Reservation r WHERE r.user.idUser=:iduser AND r.ouvrage.idOuvrage=:idOuvrage";
	private static final String JPQL_ANNULER_RESA_UTILISATEUR = "DELETE FROM reservation WHERE id_user=?1 and id_ouvrage = ?2 RETURNING position";
	private static final String JPQL_SELECT_DATE_RETOUR_PLUS_PROCHE = "SELECT MIN(date_sortie + (4 || 'week')::interval ) AS date_retour_plus_proche FROM pret, ouvrage, exemplaire "
			+ "WHERE ouvrage.id_ouvrage = exemplaire.id_ouvrage\r\n" + 
			"AND pret.id_exemp = exemplaire.id_exemp " + 
			"AND ouvrage.id_ouvrage = ?1 ";

	private static final String PARAM_DATE_RESA = "dateReservation";
	private static final String PARAM_UTILISATEUR = "iduser";
	private static final String PARAM_OUVRAGE = "idOuvrage";

	// permet de faire une réservation
	public void creerReservation(Ouvrage ouvrage, Utilisateur user) throws DaoException {
		Reservation reservation = new Reservation();
		int position;
		Date dateRetourPlusProche;
		List<Reservation> listReservationParUserOuvrage = this.trouverReservationParUserOuvrage(user, ouvrage);
		List<Reservation> listReservationParOuvrage = this.trouverReservationParOuvrage(ouvrage);
//		Ouvrage ouvrage = new Ouvrage();
//		ouvrage.setIdOuvrage(idOuvrage);
		reservation.setOuvrage(ouvrage);

//		Utilisateur user = new Utilisateur();
//		user.setIdUser(idUser);
		reservation.setUser(user);
		
		try {
			   Query req = entityManager.createNativeQuery(JPQL_SELECT_DATE_RETOUR_PLUS_PROCHE);
			   req.setParameter(1, reservation.getOuvrage().getIdOuvrage());
			   dateRetourPlusProche = (Date) req.getSingleResult();
			   
		} catch (Exception e) {
			throw new DaoException(e);
		}   

		reservation.setDateReservation(new Date());
		reservation.setDateRetourPlusProche(dateRetourPlusProche);
		if (listReservationParOuvrage.isEmpty()) {
			position = 1;
		} else {
			position = listReservationParOuvrage.size() + 1;
		}
		
		reservation.setPosition(position);
		
		try {
		   entityManager.persist(reservation);
		
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Reservation trouverReservationParId(long idResa) {
		Reservation reservation = entityManager.find(Reservation.class, idResa);
		return reservation;
	}
	
	// permet de lister toutes les reservations
	@SuppressWarnings("unchecked")
	public List<Reservation> listerReservation() throws DaoException {
		Query req = entityManager.createQuery("select r from Reservation r");
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	// permet de rechercher réservation par date
	@SuppressWarnings("unchecked")
	public List<Reservation> trouverReservationParDateResa(Date dateResa) throws DaoException {
		Query req = entityManager.createQuery(JPQL_SELECT_PAR_DATE_RESA);
		req.setParameter(PARAM_DATE_RESA, dateResa);
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	// permet de rechercher réservation par utilisateur
	@SuppressWarnings("unchecked")
	public List<Reservation> trouverReservationParUser( Utilisateur user) throws DaoException {
		Query req = entityManager.createQuery(JPQL_SELECT_PAR_UTILISATEUR);
		req.setParameter(PARAM_UTILISATEUR, user.getIdUser());
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	// permet d'annuler une reservation
	public int annulerReservation(Ouvrage ouvrage, Utilisateur user) throws DaoException {
		Query req = entityManager.createNativeQuery(JPQL_ANNULER_RESA_UTILISATEUR);
		req.setParameter(1, user.getIdUser());
		req.setParameter(2, ouvrage.getIdOuvrage());
		try {
		int result = (int) req.getSingleResult();
		int position;
		List<Reservation> listReservationOuvrage = this.trouverReservationParOuvrage(ouvrage);
		for (Reservation reservation : listReservationOuvrage) {
			position = reservation.getPosition();
			if (position != 1 && position > result) {
				position = position - 1;
				reservation.setPosition(position);
//            	entityManager.createNativeQuery(JPQL_UPDATE_RESA_POSITION).executeUpdate();
			}
//			if (listReservation.isEmpty()) {
//				position = 1;
//				reservation.setPosition(position);
//			} else {
//				position = reservation.getPosition() - 1;
//				reservation.setPosition(position);
//			}
		}
			return result;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
   public Date dateRetourPlusProche(long idOuvrage) {
	   Query req = entityManager.createNativeQuery(JPQL_SELECT_DATE_RETOUR_PLUS_PROCHE);
	   req.setParameter(1, idOuvrage);
	   Date dateRetourPlusProche;
	   dateRetourPlusProche = (Date) req.getSingleResult();
	   try {
			return dateRetourPlusProche;
		} catch (Exception e) {
			throw new DaoException(e);
		}
   }
   
   
	// permet de rechercher réservation par utilisateur et par ouvrage
	@SuppressWarnings("unchecked")
	public List<Reservation> trouverReservationParUserOuvrage( Utilisateur user, Ouvrage ouvrage) throws DaoException {
		Query req = entityManager.createQuery(JPQL_SELECT_PAR_UTILISATEUR_OUVRAGE);
		req.setParameter(PARAM_UTILISATEUR, user.getIdUser());
		req.setParameter(PARAM_OUVRAGE, ouvrage.getIdOuvrage());
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	// permet de rechercher réservation par ouvrage
	@SuppressWarnings("unchecked")
	public List<Reservation> trouverReservationParOuvrage(Ouvrage ouvrage) throws DaoException {
		Query req = entityManager.createQuery(JPQL_SELECT_PAR_OUVRAGE);
		req.setParameter(PARAM_OUVRAGE, ouvrage.getIdOuvrage());
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
