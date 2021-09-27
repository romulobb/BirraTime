package com.santanderBirraTime.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class Main {

    @JsonProperty("temp")
    private float temp;
    @JsonProperty("feels_like")
    private float feelslike;
    @JsonProperty("temp_min")
    private float tempmin;
    @JsonProperty("temp_max")
    private float tempmax;
    @JsonProperty("pressure")
    private int pressure;
    @JsonProperty("humidity")
    private int humidity;

}
