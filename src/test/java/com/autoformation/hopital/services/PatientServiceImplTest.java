package com.autoformation.hopital.services;

import com.autoformation.hopital.dtos.Medecin;
import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.*;
import com.autoformation.hopital.repositories.PatientRepository;
import com.autoformation.hopital.web.PatientRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static com.autoformation.hopital.entities.StatusRDV.PENDING;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceImplTest {
    @Mock
    private PatientRepository patientRepositoryMock;
    @InjectMocks
    private PatientServiceImpl patientService;


    @Test()
    void testSavePatientSuccess() {
        Patient patient1 = new Patient(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        //Mockito.when(patient1.toPatientEntity()).thenReturn(patientEntity1);
        Mockito.when(patientRepositoryMock.save(any(PatientEntity.class/*patient1.toPatientEntity()*/))).thenReturn(patientEntity1);
        //Mockito.when(patientRepository.save(any(PatientEntity.class))).thenReturn(patientEntity1);

        Patient savedPatient = patientService.savePatient(patient1);
        System.out.println(savedPatient.getPrenom());

        Assertions.assertNotNull(savedPatient);
        Assertions.assertEquals(savedPatient.getNom(),"nomP1");
        Assertions.assertEquals(savedPatient.getPrenom(),"prenomP1");
        //verify(patientRepository,times(1)).save(patientEntity1);
    }

    //@Test(expected = java.lang.Exception.class)
    @Test
    void testSavePatientUnsuccess() {
        Patient patient1 = null;
        try {
            //Mockito.when(patientRepositoryMock.save(any(PatientEntity.class))).thenReturn(null);
            Patient savedPatient = patientService.savePatient(patient1);
            fail("we expected an exception to be thrown");
        }catch(Exception e)
        {
            Assertions.assertTrue(true);
            Assertions.assertEquals("a new Patient must be not null", e.getMessage());
        }
    }

    @Test
    void testUpdatePatientSuccess() {
        Long patient1Id = 1L;
        Patient patient1 = new Patient(null,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());

        Mockito.when(patientRepositoryMock.save(any(PatientEntity.class/*patient1.toPatientEntity()*/))).thenReturn(patientEntity1);
        Mockito.when(patientRepositoryMock.findById(eq(patient1Id))).thenReturn(Optional.ofNullable(patientEntity1));

        Patient updatedPatient = patientService.updatePatient(patient1, patient1Id);

        Assertions.assertNotNull(updatedPatient);
        Assertions.assertEquals(patient1.getId(),patient1Id);
        Assertions.assertEquals(updatedPatient.getNom(),"nomP1");
        Assertions.assertEquals(updatedPatient.getPrenom(),"prenomP1");
    }

    @Test
    void testUpdatePatientUnsuccess() {

        Long patient1Id = 1L;
        Patient patient1 = new Patient(null,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        try {
            Mockito.when(patientRepositoryMock.findById(eq(patient1Id))).thenReturn(Optional.ofNullable(null));

            Patient updatedPatient = patientService.updatePatient(patient1, patient1Id);
            fail("we expected an exception to be thrown ");

        }catch (Exception e){
            Assertions.assertTrue(true);
            Assertions.assertEquals("Patient not found", e.getMessage());
        }
    }

    @Test
    void testDeletePatientById() {

        //Mockito.when(patientRepositoryMock.deleteById(eq(1L))).thenCallRealMethod();
        //patientService.deletePatientById(1L);
        patientService.deletePatientById(null);

    }

    @Test
    void testGetAllPatient() {
        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        PatientEntity patientEntity2 = new PatientEntity(2L,"nomP2","prenomP2", new Date(12541524515254L),
                "adresseP2","emailP2",true, new ArrayList<>());
        PatientEntity patientEntity3 = new PatientEntity(3L,"nomP3","prenomP3", new Date(12541524515254L),
                "adresseP3","emailP3",true, new ArrayList<>());

        List<PatientEntity> listPatientEntity = new ArrayList<>(Arrays.asList(patientEntity1, patientEntity2, patientEntity3));

        when(patientRepositoryMock.findAll()).thenReturn(listPatientEntity);

        List<Patient> listPatient= patientService.getAllPatient();

        Assertions.assertEquals(3, listPatient.size());
        Assertions.assertEquals("nomP1", listPatient.get(0).getNom());
        Assertions.assertEquals("nomP3", listPatient.get(2).getNom());
    }

    @Test
    void testGetAllPatientPerPage() {

        int page = 0; int size = 2;
        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        PatientEntity patientEntity2 = new PatientEntity(2L,"nomP2","prenomP2", new Date(12541524515254L),
                "adresseP2","emailP2",true, new ArrayList<>());
        PatientEntity patientEntity3 = new PatientEntity(3L,"nomP3","prenomP3", new Date(12541524515254L),
                "adresseP3","emailP3",true, new ArrayList<>());

        List<PatientEntity> listPatientEntity = new ArrayList<>(Arrays.asList(patientEntity1, patientEntity2));
        Page<PatientEntity> pagePatientEntity = new PageImpl<>(listPatientEntity,PageRequest.of(page,size),3);

        when(patientRepositoryMock.findAll(PageRequest.of(page, size))).thenReturn(pagePatientEntity);

        Page<Patient> pagePatient= patientService.getAllPatient(page,size);

        Assertions.assertEquals(size, pagePatient.getSize());
        Assertions.assertEquals("nomP1", pagePatient.toList().get(0).getNom());
        Assertions.assertEquals("nomP2", pagePatient.toList().get(1).getNom());
    }

    @Test
    void testGetPatientByNameSuccess() {

        String nom = "nomP";

        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        PatientEntity patientEntity2 = new PatientEntity(2L,"nomP2","prenomP2", new Date(12541524515254L),
                "adresseP2","emailP2",true, new ArrayList<>());
        PatientEntity patientEntity3 = new PatientEntity(3L,"nomP3","prenomP3", new Date(12541524515254L),
                "adresseP3","emailP3",true, new ArrayList<>());

        List<PatientEntity> listPatientEntity = new ArrayList<>(Arrays.asList(patientEntity1, patientEntity2, patientEntity3));

        when(patientRepositoryMock.findByNom(eq(nom))).thenReturn(listPatientEntity);

        List<Patient> listPatient= patientService.getPatientByName(nom);

        Assertions.assertEquals(3, listPatient.size());
        Assertions.assertEquals("nomP1", listPatient.get(0).getNom());
        Assertions.assertEquals("nomP3", listPatient.get(2).getNom());
    }

    @Test
    void testGetPatientByNameUnsuccess() {
        String nom= null;
        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        PatientEntity patientEntity2 = new PatientEntity(2L,"nomP2","prenomP2", new Date(12541524515254L),
                "adresseP2","emailP2",true, new ArrayList<>());
        PatientEntity patientEntity3 = new PatientEntity(3L,"nomP3","prenomP3", new Date(12541524515254L),
                "adresseP3","emailP3",true, new ArrayList<>());

        try {
            List<PatientEntity> listPatientEntity = new ArrayList<>(Arrays.asList(patientEntity1, patientEntity2, patientEntity3));
            when(patientRepositoryMock.findByNom(eq(nom))).thenReturn(listPatientEntity);
            List<Patient> listPatient = patientService.getPatientByName(nom);
            fail(" an runtime exception must be thrown");
        }catch (RuntimeException e){
            Assertions.assertTrue(true);
            Assertions.assertEquals("the searched name must be not null", e.getMessage());
        }
    }

    @Test
    void testGetPatientByNamePerPage() {
        int page = 0; int size = 2; String name ="nomP";
        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        PatientEntity patientEntity2 = new PatientEntity(2L,"nomP2","prenomP2", new Date(12541524515254L),
                "adresseP2","emailP2",true, new ArrayList<>());
        PatientEntity patientEntity3 = new PatientEntity(3L,"nomP3","prenomP3", new Date(12541524515254L),
                "adresseP3","emailP3",true, new ArrayList<>());

        List<PatientEntity> listPatientEntity = new ArrayList<>(Arrays.asList(patientEntity1, patientEntity2));
        Page<PatientEntity> pagePatientEntity = new PageImpl<>(listPatientEntity,PageRequest.of(page,size),3);

        when(patientRepositoryMock.findByNomContains(name, PageRequest.of(page, size))).thenReturn(pagePatientEntity);

        Page<Patient> pagePatient= patientService.getPatientByName(name,PageRequest.of(page, size));

        Assertions.assertEquals(size, pagePatient.getSize());
        Assertions.assertEquals("nomP1", pagePatient.toList().get(0).getNom());
        Assertions.assertEquals("nomP2", pagePatient.toList().get(1).getNom());
    }

    @Test
    void testGetPatientByNamePerPageUnsuccess() {
        int page = 0; int size = 2; String name =null;
        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        PatientEntity patientEntity2 = new PatientEntity(2L,"nomP2","prenomP2", new Date(12541524515254L),
                "adresseP2","emailP2",true, new ArrayList<>());
        PatientEntity patientEntity3 = new PatientEntity(3L,"nomP3","prenomP3", new Date(12541524515254L),
                "adresseP3","emailP3",true, new ArrayList<>());
        try {
            List<PatientEntity> listPatientEntity = new ArrayList<>(Arrays.asList(patientEntity1, patientEntity2));
            Page<PatientEntity> pagePatientEntity = new PageImpl<>(listPatientEntity, PageRequest.of(page, size), 3);
            when(patientRepositoryMock.findByNomContains(name, PageRequest.of(page, size))).thenReturn(pagePatientEntity);
            Page<Patient> pagePatient = patientService.getPatientByName(name, PageRequest.of(page, size));
            fail(" an runtime exception must be thrown");
        }catch (RuntimeException e){
            Assertions.assertTrue(true);
            Assertions.assertEquals("the searched name must be not null", e.getMessage());
        }
    }

    @Test
    void testGetPatientByIdSuccess() {
        Long id = 1L;
        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        Optional<PatientEntity> opPatientEntity1 = Optional.ofNullable(patientEntity1);

        when(patientRepositoryMock.findById(eq(id))).thenReturn(opPatientEntity1);

        Optional<Patient> patient= patientService.getPatientById(id);

        Assertions.assertTrue(patient.isPresent());
        Assertions.assertEquals(id, patient.get().getId());
        Assertions.assertEquals("nomP1", patient.get().getNom());
    }

    @Test
    void testGetPatientByIdUnsuccess() {
        Long id = 1L;
        Optional<PatientEntity> opPatientEntity1 = Optional.ofNullable(null);
        when(patientRepositoryMock.findById(eq(id))).thenReturn(opPatientEntity1);
        Optional<Patient> patient= patientService.getPatientById(id);

        Assertions.assertFalse(patient.isPresent());
    }

    @Test
    private void assertGetRdvPatientSuccess(StatusRDV statusRdv) {

        Long id = 1L;
        PatientEntity patientEntity1 = new PatientEntity(1L,"nomP1","prenomP1", new Date(12541524515254L),
                "adresseP1","emailP1",true, new ArrayList<>());
        RendezVousEntity rdv1 = new RendezVousEntity(1L,new Date(12541544524584L),StatusRDV.PENDING,
                                    patientEntity1,new MedecinEntity(),new ConsultationEntity());
        RendezVousEntity rdv2 = new RendezVousEntity(2L,new Date(12541544524584L),StatusRDV.CANCELED,
                patientEntity1,new MedecinEntity(),new ConsultationEntity());
        RendezVousEntity rdv3 = new RendezVousEntity(3L,new Date(12541544524584L),StatusRDV.DONE,
                patientEntity1,new MedecinEntity(),new ConsultationEntity());

        patientEntity1.getRendezVous().add(rdv1);
        patientEntity1.getRendezVous().add(rdv2);
        patientEntity1.getRendezVous().add(rdv3);
        Optional<PatientEntity> opPatientEntity1 = Optional.ofNullable(patientEntity1);

        when(patientRepositoryMock.findById(eq(id))).thenReturn(opPatientEntity1);

        List<RendezVous> lisRendezVous= patientService.getRdvPatient(id,statusRdv);

        Assertions.assertEquals(1, lisRendezVous.size());
        Assertions.assertEquals(1L, lisRendezVous.get(0).getPatient().getId());
        if(statusRdv == StatusRDV.PENDING)
            Assertions.assertEquals(1L, lisRendezVous.get(0).getId());
        if(statusRdv == StatusRDV.CANCELED)
            Assertions.assertEquals(2L, lisRendezVous.get(0).getId());
        if(statusRdv == StatusRDV.DONE)
            Assertions.assertEquals(3L, lisRendezVous.get(0).getId());
    }

    @Test
    void TestGetRdvPatientSuccess() {
        assertGetRdvPatientSuccess(StatusRDV.PENDING);
        assertGetRdvPatientSuccess(StatusRDV.CANCELED);
        assertGetRdvPatientSuccess(StatusRDV.DONE);
    }
    @Test
    private void assertGetRdvPatientUnsuccess(StatusRDV statusRdv) {

        Long id = 1L;
        Optional<PatientEntity> opPatientEntity1 = Optional.ofNullable(null);
        when(patientRepositoryMock.findById(eq(id))).thenReturn(opPatientEntity1);
        List<RendezVous> lisRendezVous= patientService.getRdvPatient(id,statusRdv);
        Assertions.assertNull(lisRendezVous);
    }

    @Test
    void TestGetRdvPatientUnsuccess() {
        assertGetRdvPatientUnsuccess(StatusRDV.PENDING);
        assertGetRdvPatientUnsuccess(StatusRDV.CANCELED);
        assertGetRdvPatientUnsuccess(StatusRDV.DONE);
    }

}