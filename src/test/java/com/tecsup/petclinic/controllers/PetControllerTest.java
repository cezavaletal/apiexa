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




}