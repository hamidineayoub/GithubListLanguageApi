package com.gemography.github.list.languages.api.app.utils;

import com.gemography.github.list.languages.api.app.modules.Item;
import com.gemography.github.list.languages.api.app.modules.LanguageDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class MapperUtils {

    public static Map<String, LanguageDetails> itemsToListLanguages(Item[] items) {
        Map<String, LanguageDetails> map = new HashMap<String, LanguageDetails>();
        for (Item item : items) {
            String language = item.getLanguage() == null ? "Not defined" : item.getLanguage();
            if (map.containsKey(language)) {
                map.get(language).addCount();
                map.get(language).getRepos().add(item.getFullName());
            } else {
                map.put(language, new LanguageDetails(language, new ArrayList<>(Arrays.asList(item.getFullName()))));
            }
        }
        return map;
    }
}
