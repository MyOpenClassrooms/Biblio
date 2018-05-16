package fr.khady.wsBiblio.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.khady.wsBiblio.entity.Exemplaire;
import fr.khady.wsBiblio.entity.Ouvrage;

@Stateless
public class ExemplaireDao {

	@PersistenceContext(unitName = "bdd_biblio_PU")
	private EntityManager entityManager;

	private static final String JPQL_SELECT_PAR_OUVRAGE = "SELECT e FROM Exemplaire e WHERE e.ouvrage.idOuvrage=:idOuvrage";
	private static final String PARAM_ID_OUVRAGE = "idOuvrage";
	
	public void creerExemplaire(int nbrExemp, int numRayon, long  idOuvrage) throws DaoException {
		Exemplaire exemplaire = new Exemplaire();
		Ouvrage ouvrage = new Ouvrage();
		
		exemplaire.setNbrExemplaire(nbrExemp);
		exemplaire.setNumRayon(numRayon);
		ouvrage.setIdOuvrage(idOuvrage);
		exemplaire.setOuvrage(ouvrage);

		try {
			entityManager.persist(exemplaire);
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Exemplaire> listerExemplaire() throws DaoException {
		Query req = entityManager.createQuery("select e from Exemplaire e");
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	public Exemplaire trouverExemplaireParId(long idExemp) {
		Exemplaire exemplaire = entityManager.find(Exemplaire.class, idExemp);
		return exemplaire;
	}
	
    public Exemplaire trouverExemplaireParOuvrage(long idOuvrage)  throws DaoException {
    	Exemplaire exemplaire = null;
        Query requete = entityManager.createQuery( JPQL_SELECT_PAR_OUVRAGE );
        requete.setParameter( PARAM_ID_OUVRAGE, idOuvrage );
        try {
        	exemplaire = (Exemplaire) requete.getSingleResult();
        } catch (Exception e) {
			throw new DaoException(e);
		}
        return exemplaire;
    }
	
//	
//	public Exemplaire trouverExemplaireParOuvrage(long idOuvrage) {
//		Exemplaire exemplaire = entityManager.find(Exemplaire.class, idOuvrage);
//		return exemplaire;
//	}

}
