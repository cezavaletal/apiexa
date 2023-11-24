package com.tecsup.petclinic.controllers;

import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.PetDTO;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetController {

    @Autowired
    private PetService service;

    @GetMapping("/pets")
    public Iterable<Pet> getPets(){
        return service.findAll();
    }

    @RequestMapping(value="/pets/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Pet> findOne(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (PetNotFoundException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pets")
    @ResponseStatus(HttpStatus.CREATED)
    Pet create(@RequestBody PetDTO newPet){
        Pet pet = new Pet();
        pet.setName(newPet.getName());
        pet.setOwnerId(newPet.getOwnerId());
        pet.setTypeId(newPet.getTypeId());
        pet.setBirth_date(newPet.getBirth_date());
        return service.create(pet);
    }

    @DeleteMapping("/pets/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        try {
            service.delete(id);
            return  new ResponseEntity<>(""+id,HttpStatus.OK);
        }catch (PetNotFoundException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
