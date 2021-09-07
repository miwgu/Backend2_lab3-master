package se.nackademin.java20.lab1.risk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Miwa Guhrés
 * Date: 06/09/2021
 * Time: 23:04
 * Project: lab3-master
 * Copyrigtht: MIT
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RikAssessmentDto {
    private final boolean pass;

    @JsonCreator
    public RikAssessmentDto(@JsonProperty("pass") boolean pass ){
    this.pass =pass;
    }

    public boolean isPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "RikAssessmentDto{" +
                "pass=" + pass +
                '}';
    }
}



