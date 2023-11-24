package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Owner;


@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    // Buscar propietarios por firstName
    List<Owner> findByFirstName(String firstName);

    // Buscar propietarios por lastName
    List<Owner> findByLastName(String lastName);

    // Buscar propietarios por city
    List<Owner> findByCity(String city);

    // Buscar propietarios por telephone
    List<Owner> findByTelephone(String telephone);

}
