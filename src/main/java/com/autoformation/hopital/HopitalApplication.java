package com.autoformation.hopital;

import com.autoformation.hopital.entities.*;
import com.autoformation.hopital.repositories.ConsultationRepository;
import com.autoformation.hopital.repositories.MedecinRepository;
import com.autoformation.hopital.repositories.PatientRepository;
import com.autoformation.hopital.repositories.RendezVousRepository;
import com.autoformation.hopital.services.IConsultationService;
import com.autoformation.hopital.services.IMedecinService;
import com.autoformation.hopital.services.IPatientService;
import com.autoformation.hopital.services.IRendezVousService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/*
pour tester: deux manières: implemente l'interface CommandLineRunner et override la méthode run
or
n'implemente pas l'interface mais on defini une méthode avec l'annotation @Bean qui retourne un objet CommandLineRunner.
 (exp methode start)

en Spring: si une méthode utilise l'annotation Bean: s'execute au demarrage. donc si cette methode retourne un objet,
cet objet devient un composant spring: spring va le mettre dans la liste des objet (contexte), (par la suite on
 peut l'autilisé avec la nnotation autowired
* */
@SpringBootApplication
public class HopitalApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}

	//@Override
	//public void run(String... args) throws Exception {

	// CommandLineRunner contient une méthode run, donc on fournit le code qui sera executé
	// on peut rajouter des paramètres qu'on aura besoin. si un objet spring va faire l'injection de dépendance
	// sans utiliser autowired
	@Bean
	@Transactional
	CommandLineRunner start(IPatientService patientRepo,
							IMedecinService medecinRepo,
							IRendezVousService rendezVousRepo,
							IConsultationService consultationRepo){
		return args -> {
			Stream.of("ahmed","christophe","Ali","Damien")
					.forEach(name->{
						Patient p = new Patient();
						p.setNom(name);
						p.setPrenom(name+"Prenom");
						p.setDateNaissance(new Date(1212121212121L));
						p.setAdresse(name+"Adresse");
						p.setEmail(name+"Email");
						p.setMalade(false);
						patientRepo.savePatient(p);
					});

			Stream.of("Nicolas","Floriant","Justin","Farid")
					.forEach(name->{
						Medecin m = new Medecin();
						m.setNom(name);
						m.setPrenom(name+"Prenom");
						m.setDateNaissance(new Date( 1212121212121L));
						m.setAdresse(name+"Adresse");
						m.setEmail(name+"Email");
						m.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						medecinRepo.saveMedecin(m);
					});
			Patient patient = patientRepo.getPatientById(1L).orElse(null);
			Iterable<Patient> patientList = patientRepo.getPatientByName("ahmed");

			Optional<Medecin> med = medecinRepo.getMedecintById(1L);
			Medecin medecin = medecinRepo.getMedecintById(1L).orElse(null);
			med.ifPresent(x->System.out.println("Medecin not NUl ************"));

			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);

			//test
			//medecin.getRendezVous().add(rendezVous);
			//patient.getRendezVous().add(rendezVous);
			rendezVousRepo.saveRendezVous(rendezVous);

			Optional<RendezVous> red = rendezVousRepo.getRendezVousById(1L);
			RendezVous red1 = rendezVousRepo.getRendezVousById(1L).orElse(null);
			red.ifPresent(x->System.out.println("Rendez Vous not NUl ************"));

			Consultation cons1= new Consultation();
			cons1.setDateConsultation(new Date());
			cons1.setRendezVous(red1);
			cons1.setPrix_consultation(1428.0);
			cons1.setRapport("Rapport de la cosnultation 1");
			//red1.setConsultation(cons1);
			consultationRepo.saveConsultation(cons1);

			//cons1 = consultationRepo.getConsultationById(1L).orElse(null);
			//consultationRepo.deleteConsultationById(1L);

			//rendezVousRepo.deleteRDVById(1L);


		};




	/*CommandLineRunner start(PatientRepository patientRepo,
							MedecinRepository medecinRepo,
							RendezVousRepository rendezVousRepo,
							ConsultationRepository consultationRepo){
		return args -> {
			Stream.of("ahmed","christophe","Ali","Damien")
					.forEach(name->{
						Patient p = new Patient();
						p.setNom(name);
						p.setPrenom(name+"Prenom");
						p.setDateNaissance(new Date(1212121212121L));
						p.setAdresse(name+"Adresse");
						p.setEmail(name+"Email");
						p.setMalade(false);
						patientRepo.save(p);
					});

			Stream.of("Nicolas","Floriant","Justin","Farid")
					.forEach(name->{
						Medecin m = new Medecin();
						m.setNom(name);
						m.setPrenom(name+"Prenom");
						m.setDateNaissance(new Date( 1212121212121L));
						m.setAdresse(name+"Adresse");
						m.setEmail(name+"Email");
						m.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						medecinRepo.save(m);
					});
			Patient patient = patientRepo.findById(1L).orElse(null);
			Iterable<Patient> patientList = patientRepo.findByNom("ahmed");

			Optional<Medecin> med = medecinRepo.findById(1L);
			Medecin medecin = medecinRepo.findById(1L).orElse(null);
			med.ifPresent(x->System.out.println("Medecin not NUl ************"));

			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);

			//medecin.getRendezVous().add(rendezVous);
			//patient.getRendezVous().add(rendezVous);
			rendezVousRepo.save(rendezVous);


			RendezVous red1 = rendezVousRepo.findById(1L).orElse(null);
			Consultation cons1= new Consultation();
			cons1.setDateConsultation(new Date());
			cons1.setRendezVous(red1);
			cons1.setRapport("Rapport de la cosnultation 1");
			consultationRepo.save(cons1);


		};
		*/
	}
}
