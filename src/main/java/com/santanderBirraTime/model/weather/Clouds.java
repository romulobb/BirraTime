package com.santanderBirraTime.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Clouds {

    @JsonProperty("all")
    private long all;

}
