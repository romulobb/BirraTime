package com.santanderBirraTime.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class DTOWeather {

    @JsonProperty("id")
    private long id;
    @JsonProperty("coord")
    private Coordenate coord;
    @JsonProperty("weather")
    private List<Weather> weather;
    @JsonProperty("base")
    private String  base;
    @JsonProperty("main")
    private Main  main;
    @JsonProperty("visibility")
    private Long  visibility;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("dt")
    private Long dt;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cod")
    private String cod;


}
