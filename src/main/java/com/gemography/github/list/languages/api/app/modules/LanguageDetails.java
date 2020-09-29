package com.gemography.github.list.languages.api.app.modules;

import java.util.List;

public class LanguageDetails {
    private String name;
    private List<String> repos;
    private int count;

    public LanguageDetails(String name, List<String> repos) {
        this.name = name;
        this.repos = repos;
        this.count = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRepos() {
        return repos;
    }

    public void setRepos(List<String> repos) {
        this.repos = repos;
    }

    public int getCount(){
        return count;
    }

    public void addCount(){
        this.count += 1;
    }
}
