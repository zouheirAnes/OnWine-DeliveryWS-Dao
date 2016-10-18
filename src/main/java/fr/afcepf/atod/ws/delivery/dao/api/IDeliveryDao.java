package fr.afcepf.atod.ws.delivery.dao.api;

import java.util.List;

import javax.ejb.Local;

import fr.afcepf.atod.ws.delivery.entity.Delivery;
import fr.afcepf.atod.ws.delivery.exception.DeliveriesWSException;


/**
 * API of the delivery DAO.
 * @author Zouheir
 *
 */
@Local
public interface IDeliveryDao {
    /**
     * Create method for {@link Delivery} objects.
     * @param c the currency object to persist
     * @return Delivery the delivery object persisted
     * @throws DeliveriesWSException custom exception
     */
    Delivery insert(Delivery d)  throws DeliveriesWSException;
    /**
    * Update method for {@link Delivery} objects.
    * @param d the delivery object to update
    * @return Boolean return the result of the delivery object update
    * @throws DeliveriesWSException custom exception
    */
    Boolean update(Delivery d)   throws DeliveriesWSException;
    /**
     * delete method for {@link Delivery} objects.
     * @param d the delivery object to delete
     * @return Boolean return the result of the delivery object deletion
     * @throws DeliveriesWSException custom exception
     */
    Boolean remove(Delivery d)   throws DeliveriesWSException;
    /**
     * Retrieve method for a {@link Delivery} object.
     * @param id The Integer identifier of the {@link Delivery}
     * @return a {@link Delivery} object
     * @throws DeliveriesWSException custom exception
     */
    Delivery find(Integer id)    throws DeliveriesWSException;
    /**
     * Retrieve method for all {@link Delivery} objects.
     * @return a List of {@link Delivery} objects
     * @throws DeliveriesWSException custom exception
     */
    List<Delivery> findAll()     throws DeliveriesWSException;
    /**
     * Delete method for all {@link Delivery} objects. Test Purpose.
     * @return Boolean did it work?
     * @throws DeliveriesWSException custom exception
     */
    Boolean deleteAllDeliveries();
    /**
     * Retrieve method for a {@link Delivery} object.
     * @param paramName The name of the {@link Delivery}
     * @return a {@link Delivery} object
     * @throws DeliveriesWSException custom exception
     */
    Delivery findByName(String paramName)    throws DeliveriesWSException;
}
