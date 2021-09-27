package com.santanderBirraTime.controller;


import com.mashape.unirest.http.exceptions.UnirestException;
import com.santanderBirraTime.model.meetup.Meetup;
import com.santanderBirraTime.model.meetup.Persona;
import com.santanderBirraTime.model.response;
import com.santanderBirraTime.service.BirraService;
import com.santanderBirraTime.service.IBirraService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/santanderMeetups")
public class BirraController {
    @Autowired
    private BirraService service;
    private Object response;

    private static final Logger logger = LoggerFactory.getLogger(BirraController.class);


    @ApiOperation(value = "Generar la reunion segun un dia, tema y lugar", response = Long.class, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success Operation"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    @PostMapping(value = "/generar")
    public long generar(@ApiParam(name =  "Tema la reunion",type = "String", required = true)
                        @RequestParam() String tema,
                        @ApiParam(name =  "Dia",type = "String", required = true)
                        @RequestParam() Date dia,
                        @ApiParam(name =  "Lugar",type = "String", required = true)
                        @RequestParam() String lugar) {

        long idMeetup = service.armarMeetup(tema,dia,lugar);
        return idMeetup;
    }


    @ApiOperation(value = "Agregar Orador a reunion")
    @PutMapping(value = "/agregarOrador")
    public HttpStatus agregarOrador(@ApiParam(name =  "Id reunion",type = "long", required = true)
                                   @RequestParam(value = "Id reunion") Long idMeetup,
                                   @ApiParam(name =  "IdOrador",type = "Long", required = true)
                                   @RequestParam(value = "Id Orador") Long idOrador,
                                    @ApiParam(name =  "Tema del cual hablara el orador",type = "String", required = true)
                                        @RequestParam(value = "Tema") String tema){
        HttpStatus resultStatus;
        resultStatus = HttpStatus.OK;
        try {
            service.agregarOrador(idMeetup,idOrador,tema);
        }
        catch(Exception e){
            logger.error("Ocurrio un error en la insripcion del orador {} a la reunion id {}",idOrador,idMeetup);
            resultStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return resultStatus;
    }


    @ApiOperation(value = "Registrar Inscripto en reunion", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success Operation"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    @PutMapping(value = "/registrarInscripto")
    public  HttpStatus registrarInscripto(@ApiParam(name =  "Id reunion",type = "long", required = true)
                        @RequestParam(value = "Id reunion") Long idMeetup, @ApiParam(name =  "IdInscripto",type = "Long", required = true)
                        @RequestParam(value = "Id Inscripto") Long idInscripto) {
        HttpStatus resultStatus;
        resultStatus = HttpStatus.OK;
        try {
            service.registrarInscripto(idMeetup,idInscripto);

        }
        catch(Exception e){
            logger.error("Ocurrio un error en la insripcion del usuarioid {} a la reunion id {}",idInscripto,idMeetup);
            resultStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return resultStatus;
    }



    @ApiOperation(value = "Registrar Asistente a reunion", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success Operation"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    @PutMapping(value = "/registrarAsistencia")
    public HttpStatus registrarAsistencia(@ApiParam(name =  "Id reunion",type = "long", required = true)
                                   @RequestParam(value = "Id reunion") Long idMeetup,
                                   @ApiParam(name =  "IdAsistente",type = "Long", required = true)
                                   @RequestParam(value = "Id Asistente") Long idAsistente) {
        HttpStatus resultStatus;
        try {

            resultStatus = HttpStatus.OK;
            Boolean rta = service.registrarAsistente(idMeetup, idAsistente);


            if (!rta) {
                logger.error("El usuario no estaba registrado para la meetup");
                resultStatus = HttpStatus.UNAUTHORIZED;
            }
        }
        catch(Exception e){
            logger.error("Ocurrio un error en la registracion del usuarioid {} a la reunion id {}",idAsistente,idMeetup);
            resultStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return resultStatus;
    }

    @ApiOperation(value = "Conocer la temperatura del dia de la reunion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success Operation"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    @GetMapping(value = "/temperatura")
    public long temperatura() throws UnirestException {

            long l = service.conocerTemperatura();
            return l;

    }

    @ApiOperation(value = "Devuelve la cantidad de cajas de cerveza que se debera comprar para una reunion", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success Operation"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    @PostMapping(value = "/cantidadCajasReunion")
    public long temperatura(@ApiParam(name =  "Id reunion",type = "long", required = true)
                            @RequestParam(value = "Id reunion") Long idMeetup) throws UnirestException {

        long l = service.abastecerBirraMeetup(idMeetup);
        return l;

    }



}