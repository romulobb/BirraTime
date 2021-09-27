package com.santanderBirraTime.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Wind {


    @JsonProperty("speed")
    private float speed;
    @JsonProperty("deg")
    private int deg;

}
