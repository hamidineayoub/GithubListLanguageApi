package com.gemography.github.list.languages.api.app.services;

import com.gemography.github.list.languages.api.app.modules.GitResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public interface LanguagesService {

    public ResponseEntity getAllLanguages() ;

    public ResponseEntity<GitResponse> getResponse() ;
}
