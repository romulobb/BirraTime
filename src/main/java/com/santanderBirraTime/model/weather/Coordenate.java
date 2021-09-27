package com.santanderBirraTime.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Coordenate {


    @JsonProperty("lon")
    private long lon;
    @JsonProperty("lat")
    private long lat;

}
