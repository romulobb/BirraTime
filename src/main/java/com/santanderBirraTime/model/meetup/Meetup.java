package com.santanderBirraTime.model.meetup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@Entity
public class Meetup {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private long id;

    @JsonProperty("tema")
    private String tema;

    @ElementCollection
    @JsonProperty("oradores")
    private List<Persona> oradores;

    //puede ser una direccion o un nombre de salon con piso ej piso 3 sala 14
    @JsonProperty("lugar")
    private String lugar;

    @JsonProperty("dia")
    private Date dia;

    //Cuando se inscriben, se llena esta lista
    @ElementCollection
    @JsonProperty("inscriptos")
    private List<Persona> inscriptos;

    //antes de entrar en la meetup, pasan de la lista de inscriptos a la lista de asistentes
    @ElementCollection
    @JsonProperty("asistentes")
    private List<Persona> asistentes;


}
