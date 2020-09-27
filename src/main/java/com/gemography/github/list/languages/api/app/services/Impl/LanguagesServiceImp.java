package com.gemography.github.list.languages.api.app.services.Impl;

import com.gemography.github.list.languages.api.app.modules.GitResponse;
import com.gemography.github.list.languages.api.app.services.LanguagesService;
import com.gemography.github.list.languages.api.app.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class LanguagesServiceImp implements LanguagesService {

    @Value("${url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DateTimeFormatter dateTimeFormatter;

    @Autowired
    MapperUtils mapperUtils;

    public ResponseEntity getAllLanguages() {
        try {
            ResponseEntity<GitResponse> responseEntity = getResponse();
            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                GitResponse response = responseEntity.getBody();
                return new ResponseEntity(
                        new ArrayList<>(mapperUtils.itemsToListLanguages(response.getItems()).values()),
                        responseEntity.getStatusCode()
                );
            }
            return responseEntity;
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }

    public ResponseEntity<GitResponse> getResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        StringBuilder urlWithParams = new StringBuilder(url);
        urlWithParams
                .append("?q=created:>")
                .append(LocalDate.now().minusDays(30).format(dateTimeFormatter))
                .append("&sort=stars&order=desc&per_page=100");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(urlWithParams.toString(), HttpMethod.GET, entity, GitResponse.class);
    }

}
