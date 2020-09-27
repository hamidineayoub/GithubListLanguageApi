package com.gemography.github.list.languages.api.app.controllers;

import com.gemography.github.list.languages.api.app.services.LanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("languages")
public class LanguagesController {

    @Autowired
    LanguagesService languagesService;

    @GetMapping("/list")
    private ResponseEntity getLanguagesList(){
        return languagesService.getAllLanguages();
    }

}
