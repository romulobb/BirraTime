package com.santanderBirraTime.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.santanderBirraTime.controller.BirraController;
import com.santanderBirraTime.model.meetup.Meetup;
import com.santanderBirraTime.model.meetup.Persona;
import com.santanderBirraTime.repository.MeetUpRepository;
import com.santanderBirraTime.repository.PersonaRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class BirraServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(BirraServiceTest.class);

    @Autowired
    private BirraService service;

    @Autowired
    private MeetUpRepository meetUpRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Before
    void populateDB(){

        Persona Asistente1 = new Persona();
        Asistente1.setNombre("Asistente1");
        Asistente1.setApellido("Asistente1");
        Asistente1.setCargo("Net Developer");
        Asistente1.setEmpresa("Microsoft");
        Asistente1.setDni(12344567);
        Asistente1.setMail("Asistente1@microsoft.com");
        personaRepository.save(Asistente1);

        Persona Asistente2 = new Persona();
        Asistente2.setNombre("Asistente2");
        Asistente2.setApellido("Asistente2");
        Asistente2.setCargo("IOS Developer");
        Asistente2.setEmpresa("Apple");
        Asistente2.setDni(23234124);
        Asistente2.setMail("Asistente2@apple.com");
        personaRepository.save(Asistente2);

        Persona Asistente3 = new Persona();

        Asistente3.setNombre("Asistente3");
        Asistente3.setApellido("Asistente3");
        Asistente3.setCargo("Android Developer");
        Asistente3.setEmpresa("YouTube");
        Asistente3.setDni(12344567);
        Asistente3.setMail("Asistente3@youtube.com");
        personaRepository.save(Asistente3);

        Persona Orador1 = new Persona();

        Orador1.setNombre("Orador1");
        Orador1.setApellido("Orador1");
        Orador1.setCargo("System Architect");
        Orador1.setEmpresa("Red Hat");
        Orador1.setDni(12344567);
        Orador1.setMail("orador1@redhat.com");
        personaRepository.save(Orador1);

        Persona Orador2 = new Persona();

        Orador2.setNombre("Orador2");
        Orador2.setApellido("Orador2");
        Orador2.setCargo("Bussiness Solution Architect");
        Orador2.setEmpresa("Enterprise Solutions Systemns");
        Orador2.setDni(12344567);
        Orador2.setMail("orador2@ladri.com");
        personaRepository.save(Orador2);


    }


    @Test
    void armarMeetup() {
        Long rta;
        try {
            rta=service.armarMeetup("MeetupTest",new Date(),"lugardeTest");
            logger.info("Meetup de test creada con el id {}",rta);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(true);

    }

    @Test
    void agregarOrador() {
        try{
            long meetupId=service.armarMeetup("MeetupTest",new Date(),"lugardeTest");
            Persona Orador = new Persona();
            Orador.setNombre("Orador");
            Orador.setApellido("Orador");
            Orador.setCargo("Bussiness Tecnical Advisor");
            Orador.setEmpresa("Electrical Company");
            Orador.setDni(12344567);
            Orador.setMail("orador@electrical.com");
            personaRepository.save(Orador);
            Orador.getId();
            service.agregarOrador(meetupId,Orador.getId(),"tema de ultimo momento");
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
        assertTrue(true);
    }

    @Test
    void registrarInscripto() {
        try{
            long meetupId=service.armarMeetup("MeetupTest",new Date(),"lugardeTest");

            Persona Asistente = new Persona();
            Asistente.setNombre("Asistente");
            Asistente.setApellido("Asistente");
            Asistente.setCargo("Net Developer");
            Asistente.setEmpresa("Microsoft");
            Asistente.setDni(12344567);
            Asistente.setMail("Asistente@microsoft.com");
            personaRepository.save(Asistente);

            service.registrarInscripto(meetupId,Asistente.getId());
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
        assertTrue(true);


    }


    @Test
    void registrarAsistente() {

        try{
            long meetupId=service.armarMeetup("MeetupTest",new Date(),"lugardeTest");

            Persona Asistente = new Persona();
            Asistente.setNombre("Asistente");
            Asistente.setApellido("Asistente");
            Asistente.setCargo("Net Developer");
            Asistente.setEmpresa("Microsoft");
            Asistente.setDni(12344567);
            Asistente.setMail("Asistente@microsoft.com");
            personaRepository.save(Asistente);

            service.registrarInscripto(meetupId,Asistente.getId());

            service.registrarAsistente(meetupId,Asistente.getId());
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
        assertTrue(true);

    }

    @Test
    void conocerTemperatura() {
        Long rta;
        try {
            rta=service.conocerTemperatura();
        } catch (UnirestException e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(true);
    }

    @Test
    void abastecerBirraMeetup() {



    }
}