package com.gemography.github.list.languages.api.app.utils;

import com.gemography.github.list.languages.api.app.modules.Item;
import com.gemography.github.list.languages.api.app.modules.LanguageDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MapperUtilsTest {

    @Mock
    Item item;

    @Test
    void itemsToListLanguages(){
        Mockito.when(
                item.getLanguage()
        ).thenReturn("lang");
        Mockito.when(
                item.getFullName()
        ).thenReturn("repo");
        Item[] items = new Item[1];
        items[0]=item;

        Map<String, LanguageDetails> map = MapperUtils.itemsToListLanguages(items);

        Assertions.assertEquals(map.size(),1);
        Assertions.assertEquals(map.containsKey("lang"),true);
        Assertions.assertEquals(map.get("lang").getRepos().size(),1);
        Assertions.assertEquals(map.get("lang").getRepos().get(0),"repo");
    }
}
