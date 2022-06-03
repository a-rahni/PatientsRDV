
/**
        package com.autoformation.hopital.web;


import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.services.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
//import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@ExtendWith(MockitoExtension.class) // for Junit5
@SpringBootTest
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
@WebMvcTest(controllers = PatientRestController.class)
public class PatientRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @MockBean
    private IPatientService patientService;

//    @InjectMocks
//    private PatientRestController patientRestController;


    Patient patient1 = new Patient(1L,"nomP1","PrenomP1", new Date(12541524515254L),
            "adresseP1","emailP1",true, new ArrayList<>());
    Patient patient2 = new Patient(2L,"nomP2","PrenomP2", new Date(12541524515254L),
            "adresseP2","emailP2",true, new ArrayList<>());
    Patient patient3 = new Patient(3L,"nomP3","PrenomP3", new Date(12541524515254L),
            "adresseP3","emailP3",true, new ArrayList<>());



    public void setUp(){
        //MockitoAnnotations.initMocks(this);
        //this.mockMvc = MockMvcBuilder.standaloneSetup(patientRestController).build();
    }

    @Test
    public void testpatientsListSuccess() throws Exception{
        List<Patient> patientList = new ArrayList<Patient>(Arrays.asList(patient1,patient2,patient3));

        Mockito.when(patientService.getAllPatient()).thenReturn(patientList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/patients/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nom", is("nomP3")));
    }

    @Test
    public void testGetPatientSucess() throws Exception{

        Mockito.when(patientService.getPatientById(patient1.getId())).thenReturn(Optional.ofNullable(patient1));
        mockMvc.perform(MockMvcRequestBuilders
                .get("patient/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.nom", is("nomP1")));
    }






}
*/