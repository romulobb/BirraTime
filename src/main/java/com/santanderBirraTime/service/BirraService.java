package com.santanderBirraTime.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.santanderBirraTime.model.meetup.Persona;
import com.santanderBirraTime.model.weather.DTOWeather;
import com.santanderBirraTime.model.meetup.Meetup;
import com.santanderBirraTime.repository.MeetUpRepository;
import com.santanderBirraTime.repository.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BirraService implements IBirraService
{

    @Autowired
    private MeetUpRepository meetUpRepository;

    @Autowired
    private PersonaRepository personaRepository;

    private static final Logger logger = LoggerFactory.getLogger(BirraService.class);

    public long armarMeetup(String tema, Date dia, String lugar){
        Meetup meetup = new Meetup();
        meetup.setDia(dia);
        meetup.setTema(tema);
        meetup.setLugar(lugar);
        meetup.setOradores(new ArrayList());
        meetup.setAsistentes(new ArrayList());
        meetup.setInscriptos(new ArrayList());

        meetUpRepository.save(meetup);
        return meetup.getId();
    };
    public void agregarOrador(long idMeetup,long idOrador,String tema) {
        Optional<Meetup> meetup;
        meetup = meetUpRepository.findById(idMeetup);
        Optional<Persona> persona;
        persona = personaRepository.findById(idOrador);
        meetup.get().getOradores();
        meetup.get().getOradores().add(persona.get());
        meetup.get().setTema(meetup.get().getTema().concat("  ").concat(tema));

    };
    public void registrarInscripto(long idMeetup,long idInscripto){
        Optional<Meetup> meetup;
        meetup = meetUpRepository.findById(idMeetup);
        Optional<Persona> persona;
        persona = personaRepository.findById(idInscripto);
        meetup.get().getInscriptos().add(persona.get());

    };
    public Boolean registrarAsistente(long idMeetup, long idAsistente) {
        Boolean rta = false;
        Optional<Meetup> meetup;
        meetup = meetUpRepository.findById(idMeetup);
        Optional<Persona> persona;
        persona = personaRepository.findById(idAsistente);
        List<Persona> listInscriptos = meetup.get().getInscriptos();
        List<Persona> listAsistentes = meetup.get().getAsistentes();


        for (Persona per : listInscriptos) {
       // personaList.stream().forEach((per)-> {
            if (per.getId() == idAsistente) {
                rta = true;
            }
        }
        //Si esta la persona inscripta, la quito de la lista de inscriptos, y la guardo en la lista de asistentes.
        //y persisto el objeto meetup
        if (rta){ listInscriptos.remove(persona.get());
                   listAsistentes.add(persona.get());
                   meetup.get().setInscriptos(listInscriptos);
                   meetup.get().setAsistentes(listAsistentes);
                   meetUpRepository.save(meetup.get());
                }


        return rta;
    };

    public long conocerTemperatura() throws UnirestException {

        ObjectMapper mapper = new ObjectMapper();

        HttpResponse<String> response = Unirest.get("https://community-open-weather-map.p.rapidapi.com/weather?q=Buenos%20Aires&lat=0&lon=0&id=2172797&lang=sp&units=metric")
                .header("x-rapidapi-key", "a958cae565msh81d6cfdda83628bp1647f9jsne760a8d34217")
                .header("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .asString();

        String body =response.getBody();
              DTOWeather dto=null;
        try {
            dto = mapper.readValue(body, DTOWeather.class);
            logger.info("Rta del servicio weather {}", dto);
        } catch (IOException ex){
            logger.error("Error del servicio weather {}", ex.getMessage());
        }catch (Exception ex ) {
            logger.error("Error del servicio weather {}", ex.toString());
        }


        return (long) dto.getMain().getTemp();
    };


    public  long abastecerBirraMeetup(long idMeetup) throws UnirestException {
        long temperatura=this.conocerTemperatura();
        Optional<Meetup> meetup;
        meetup = meetUpRepository.findById(idMeetup);
        int inscriptos=meetup.get().getInscriptos().size();
        int cervezas=0;
        if (temperatura<20){
            Double rta=inscriptos*0.75;
            cervezas=rta.intValue();
            //la multiplicacion * 0.75 da justa cuando es modulo de 4, sino le sumo uno, porque queremos que sobre y no que falte
            if ((inscriptos%4)==0){cervezas=cervezas+1;}
        }else {if (temperatura>24){
                    cervezas=inscriptos*3;
                    }
                    else{ cervezas=inscriptos;}

              }
        int cajas = cervezas /6;
        //queremos que no falte cerveza   :))
        if ((cervezas%6)!=0){ cajas =cajas+1;}

        return cajas;
    };
}
