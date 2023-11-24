package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import com.tecsup.petclinic.exception.OwnerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.repositories.OwnerRepository;


@Service
public class OwnerServiceImpl implements OwnerService {

    private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

    @Autowired
    private OwnerRepository ownerRepository;

    /**
     * Crea un nuevo propietario.
     *
     * @param owner
     * @return
     */
    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     * Actualiza la información de un propietario.
     *
     * @param owner
     * @return
     */
    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     * Elimina un propietario por su ID.
     *
     * @param id
     * @throws OwnerNotFoundException
     */
    @Override
    public void delete(Long id) throws OwnerNotFoundException {

        Owner owner = findById(id);
        ownerRepository.delete(owner);

    }

    /**
     * Encuentra un propietario por su ID.
     *
     * @param id
     * @return
     * @throws OwnerNotFoundException
     */
    @Override
    public Owner findById(long id) throws OwnerNotFoundException {

        Optional<Owner> owner = ownerRepository.findById(id);

        if (!owner.isPresent())
            throw new OwnerNotFoundException("Record not found...!");

        return owner.get();
    }

    /**
     * Encuentra propietarios por su firstName.
     *
     * @param firstName
     * @return
     */
    @Override
    public List<Owner> findByFirstName(String firstName) {
        List<Owner> owners = ownerRepository.findByFirstName(firstName);
        owners.forEach(owner -> logger.info("" + owner));
        return owners;
    }

    /**
     * Encuentra propietarios por su lastName.
     *
     * @param lastName
     * @return
     */
    @Override
    public List<Owner> findByLastName(String lastName) {
        List<Owner> owners = ownerRepository.findByLastName(lastName);
        owners.forEach(owner -> logger.info("" + owner));
        return owners;
    }

    /**
     * Encuentra propietarios por su city.
     *
     * @param city
     * @return
     */
    @Override
    public List<Owner> findByCity(String city) {
        List<Owner> owners = ownerRepository.findByCity(city);
        owners.forEach(owner -> logger.info("" + owner));
        return owners;
    }

    /**
     * Encuentra propietarios por su telephone.
     *
     * @param telephone
     * @return
     */
    @Override
    public List<Owner> findByTelephone(String telephone) {
        List<Owner> owners = ownerRepository.findByTelephone(telephone);
        owners.forEach(owner -> logger.info("" + owner));
        return owners;
    }

    /**
     * Obtiene todos los propietarios.
     *
     * @return
     */
    @Override
    public Iterable<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
