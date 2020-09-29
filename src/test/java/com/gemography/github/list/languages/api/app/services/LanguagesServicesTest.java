package com.gemography.github.list.languages.api.app.services;


import com.gemography.github.list.languages.api.app.modules.GitResponse;
import com.gemography.github.list.languages.api.app.modules.Item;
import com.gemography.github.list.languages.api.app.services.Impl.LanguagesServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
public class LanguagesServicesTest {

    @InjectMocks
    private LanguagesService languagesService = new LanguagesServiceImp();

    @Mock
    private RestTemplate restTemplate;


    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String url = "url";

    @BeforeEach
    private void setUp() {
        ReflectionTestUtils.setField(languagesService, "url", url);
        ReflectionTestUtils.setField(languagesService, "dateTimeFormatter", formatter);
    }

    @Test
    public void getAllLanguagesFailed() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        StringBuilder urlWithParams = new StringBuilder(url);
        urlWithParams
                .append("?q=created:>")
                .append(LocalDate.now().minusDays(30).format(formatter))
                .append("&sort=stars&order=desc&per_page=100");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        Mockito.when(
                restTemplate.exchange(urlWithParams.toString(), HttpMethod.GET, entity, GitResponse.class)
        )
                .thenReturn(new ResponseEntity(new GitResponse(), HttpStatus.BAD_REQUEST));

        ResponseEntity response = languagesService.getAllLanguages();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getAllLanguagesThrowsException() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        StringBuilder urlWithParams = new StringBuilder(url);
        urlWithParams
                .append("?q=created:>")
                .append(LocalDate.now().minusDays(30).format(formatter))
                .append("&sort=stars&order=desc&per_page=100");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        Mockito.when(
                restTemplate.exchange(urlWithParams.toString(), HttpMethod.GET, entity, GitResponse.class)
        )
                .thenThrow(new RestClientException(""));

        ResponseEntity response = languagesService.getAllLanguages();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.EXPECTATION_FAILED);
    }

    @Test
    public void getAllLanguagesOk() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        StringBuilder urlWithParams = new StringBuilder(url);
        urlWithParams
                .append("?q=created:>")
                .append(LocalDate.now().minusDays(30).format(formatter))
                .append("&sort=stars&order=desc&per_page=100");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        GitResponse gitResponse = new GitResponse();
        gitResponse.setItems(new Item[0]);
        Mockito.when(
                restTemplate.exchange(urlWithParams.toString(), HttpMethod.GET, entity, GitResponse.class)
        )
                .thenReturn(new ResponseEntity(gitResponse, HttpStatus.OK));

        ResponseEntity response = languagesService.getAllLanguages();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }


}
