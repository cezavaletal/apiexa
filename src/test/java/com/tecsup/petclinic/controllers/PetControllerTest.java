package com.tecsup.petclinic.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.entities.PetDTO;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.annotation.PostMapping;

import static net.bytebuddy.matcher.ElementMatchers.is;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PetControllerTest {

  //  private  static final  Logger logger = (Logger) LoggerFactory.getLogger(PetControllerTest.class);
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPets() throws  Exception{
        int ID_FIRST_RECORD = 1;
         this.mockMvc.perform(get("/pets"))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$[0].id").value(ID_FIRST_RECORD));
    }

    @Test
    public void testFindPetOK() throws Exception{
        int ID = 1;
        String NAME_PET= "Leo";
        int TYPE_ID= 1;
        int OWNER_ID = 1;
        Date DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-01");
        String DATE_STRING = new SimpleDateFormat("yyyy-MM-dd").format(DATE);


        this.mockMvc.perform(get("/pets/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.id").value(ID))
                .andExpect( jsonPath("$.name").value(NAME_PET))
                .andExpect( jsonPath("$.typeId").value(TYPE_ID))
                .andExpect( jsonPath("$.ownerId").value(OWNER_ID))
                .andExpect( jsonPath("$.birth_date").value(DATE_STRING));

    }

    @Test
    public void testFindPetKO() throws Exception {
        this.mockMvc.perform(get("/pets/666"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePet() throws Exception {
        String NAME_PET = "BeethovenY";
        int TYPE_ID = 1;
        int OWNER_ID = 1;
        String DATE_REF = "2021-11-16";
        java.sql.Date DATE =  java.sql.Date.valueOf(DATE_REF);
        PetDTO newPet = new PetDTO(NAME_PET,TYPE_ID,OWNER_ID, DATE);
        //logger.info(newPet.toString());
        //logger.info(om.writeValueAsString(newPet));

        this.mockMvc.perform(post("/pets")
                        .content(om.writeValueAsString(newPet))
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect( jsonPath("$.name").value(NAME_PET))
                .andExpect( jsonPath("$.typeId").value(TYPE_ID))
                .andExpect( jsonPath("$.ownerId").value(OWNER_ID))
                .andExpect( jsonPath("$.birth_date").value(DATE_REF));
    }


    @Test
    public void testDeletePet() throws Exception{
        String NAME_PET = "BeethovenY";
        int TYPE_ID = 1;
        int OWNER_ID = 1;
        String DATE_REF = "2021-11-16";
        java.sql.Date DATE =  java.sql.Date.valueOf(DATE_REF);
        PetDTO newPet = new PetDTO(NAME_PET,TYPE_ID,OWNER_ID, DATE);

        ResultActions mvcActions = mockMvc.perform(post("/pets")
                .content(om.writeValueAsString(newPet))
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");
        mockMvc.perform(delete("/pets/"+id))
             //   .andDo(print())
                .andExpect(status().isOk());
    }


}