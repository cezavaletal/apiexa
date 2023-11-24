package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

/**
 * Servicio para la entidad Owner.
 *
 * @author jgomezm
 */
public interface OwnerService {

    /**
     * Crea un nuevo propietario.
     *
     * @param owner
     * @return
     */
    Owner create(Owner owner);

    /**
     * Actualiza la información de un propietario.
     *
     * @param owner
     * @return
     */
    Owner update(Owner owner);

    /**
     * Elimina un propietario por su ID.
     *
     * @param id
     * @throws OwnerNotFoundException
     */
    void delete(Long id) throws OwnerNotFoundException;

    /**
     * Encuentra un propietario por su ID.
     *
     * @param id
     * @return
     * @throws OwnerNotFoundException
     */
    Owner findById(long id) throws OwnerNotFoundException;

    /**
     * Encuentra propietarios por su firstName.
     *
     * @param firstName
     * @return
     */
    List<Owner> findByFirstName(String firstName);

    /**
     * Encuentra propietarios por su lastName.
     *
     * @param lastName
     * @return
     */
    List<Owner> findByLastName(String lastName);

    /**
     * Encuentra propietarios por su city.
     *
     * @param city
     * @return
     */
    List<Owner> findByCity(String city);

    /**
     * Encuentra propietarios por su telephone.
     *
     * @param telephone
     * @return
     */
    List<Owner> findByTelephone(String telephone);

    /**
     * Obtiene todos los propietarios.
     *
     * @return
     */
    Iterable<Owner> findAll();
}
