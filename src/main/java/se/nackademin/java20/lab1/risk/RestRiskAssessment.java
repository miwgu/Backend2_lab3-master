package se.nackademin.java20.lab1.risk;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Miwa Guhrés
 * Date: 06/09/2021
 * Time: 23:04
 * Project: lab3-master
 * Copyrigtht: MIT
 */

public class RestRiskAssessment implements RiskAssessmentAdapter {

    private final RestTemplate restTemplate;
    private final String beseUrl;

    public RestRiskAssessment(RestTemplate restTemplate, String beseUrl) {
        this.restTemplate = restTemplate;
        this.beseUrl = beseUrl;
    }

    @Override
    public boolean isPassingRiskAssessment(String userId) {
        final ResponseEntity<RikAssessmentDto> entity = restTemplate.getForEntity(beseUrl + "/risk/" + userId, RikAssessmentDto.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody().isPass();
        }

        throw new RuntimeException("risk assessment coud not be fetched " + entity.getStatusCodeValue());
    }
}
