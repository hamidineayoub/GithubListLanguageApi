package com.gemography.github.list.languages.api.app.services;

import com.gemography.github.list.languages.api.app.modules.GitResponse;
import org.springframework.http.ResponseEntity;

public interface LanguagesService {

    ResponseEntity getAllLanguages() ;

    ResponseEntity<GitResponse> getRepos() ;
}
