package fr.afcepf.atod.ws.delivery.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.atod.ws.delivery.dao.api.IDeliveryDao;
import fr.afcepf.atod.ws.delivery.entity.Delivery;
import fr.afcepf.atod.ws.delivery.exception.DeliveriesWSError;
import fr.afcepf.atod.ws.delivery.exception.DeliveriesWSException;

/**
 * Concrete implementation of Delivery DAO.
 * @author Zouheir
 *
 */
@Stateless
public class DeliveryDao implements IDeliveryDao, Serializable {
    /*****************************************************
     * Requetes HQL.
     ****************************************************/
    /**
     * REQFINDDELIVERYBYNAME.
     */
    private static final String REQFINDDELIVERYBYNAME = "SELECT d FROM"
            + " Delivery d WHERE d.name = :name";
    /**
     * REQFINDALL.
     */
    private static final String REQFINDALL = "SELECT d FROM Delivery d";
    /**
     * REQDELETEALL.
     */
private static final String REQDELETEALL = "DELETE FROM Delivery";
    /**
     * setting injected entity manager..
     */
    @PersistenceContext(unitName = "OnWine-DeliveriesWS-Unit")
    private EntityManager em;
    /**
     * Serialization id.
     */
    private static final long serialVersionUID = 369353384438182690L;

    @Override
    public Delivery insert(Delivery paramD) throws DeliveriesWSException {
        if (paramD != null) {
            em.persist(paramD);
        } else {
            throw new DeliveriesWSException(
                    DeliveriesWSError.IMPOSSIBLE_AJOUT_DANS_BASE,
                    "object creation failed");
        }
        return paramD;
    }

    @Override
    public Boolean update(Delivery paramD) throws DeliveriesWSException {
        Boolean retour = false;
        if (paramD != null){
            if (em.merge(paramD) != null){
                retour = true;
            }
        }
        if (!retour) {
            throw new DeliveriesWSException(DeliveriesWSError.UPDATE_NON_EFFECTUE_EN_BASE,"object Delivery update failed");
        }
        return retour;
    }

    @Override
    public Boolean remove(Delivery paramD) throws DeliveriesWSException {
        Boolean retour = false;
        if (paramD != null) {
            em.remove(em.contains(paramD) ? paramD : em.merge(paramD));
            retour = true;
        }
        if (!retour) {
            throw new DeliveriesWSException(DeliveriesWSError.IMPOSSIBLE_SUPPRESSION_DANS_BASE, "object Delivery removal failed");
        }
        return retour;
    }

    @Override
    public Delivery find(Integer paramId) throws DeliveriesWSException {
        Delivery d = null;
        d = em.find(Delivery.class, paramId);
        if (d == null) {
            throw new DeliveriesWSException(DeliveriesWSError.RECHERCHE_NON_PRESENTE_EN_BASE, "object Delivery not found");
        }
        return d;
    }

    @Override
    public List<Delivery> findAll() throws DeliveriesWSException {
        List<Delivery> liste = new ArrayList<Delivery>();
        liste = em.createNamedQuery("delivery.findAll", Delivery.class)
                .getResultList();
        if (liste.isEmpty()) {
            throw new DeliveriesWSException(
                    DeliveriesWSError.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "object Delivery not found");
        }
        return liste;
    }

    @Override
    public Boolean deleteAllDeliveries() {
        em.createQuery("DELETE FROM Delivery").executeUpdate();
        return true;
    }

    @Override
    public Delivery findByName(String paramName) throws DeliveriesWSException {
        Delivery d = null;
        d = (Delivery) em.createQuery(REQFINDDELIVERYBYNAME).setParameter("name", paramName).getSingleResult();
        if (d == null) {
            throw new DeliveriesWSException(DeliveriesWSError.RECHERCHE_NON_PRESENTE_EN_BASE, "object Delivery not found");
        }
        return d;
    }

}
