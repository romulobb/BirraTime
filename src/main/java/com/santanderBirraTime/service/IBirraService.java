package com.santanderBirraTime.service;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Date;

public interface IBirraService
{
    long armarMeetup(String tema, Date dia,String lugar);
    void agregarOrador(long idMeetup,long idOrador,String tema);
    void registrarInscripto(long idMeetup,long idInscripto);
    Boolean registrarAsistente(long idMeetup, long idAsistente);
    long conocerTemperatura() throws UnirestException;
    long abastecerBirraMeetup(long idMeetuo) throws UnirestException;

}
