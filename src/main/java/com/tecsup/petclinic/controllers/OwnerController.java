package com.tecsup.petclinic.controllers;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OwnerController {

    @Autowired
    private OwnerService service;

    @GetMapping("/owners")
    public Iterable<Owner> getOwners(){
        return service.findAll();
    }

    @RequestMapping(value="/owners/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Owner> findOne(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (OwnerNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/owners")
    @ResponseStatus(HttpStatus.CREATED)
    Owner create(@RequestBody Owner newOwner){
        return service.create(newOwner);
    }

    @DeleteMapping("/owners/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        try {
            service.delete(id);
            return  new ResponseEntity<>(""+id, HttpStatus.OK);
        } catch (OwnerNotFoundException e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
