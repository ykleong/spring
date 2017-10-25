package org.yoong.spring.counter.domain;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * This object maps to json request body as required by the 'search' api.
 * 
 * eg. d'"{searchText":["Duis", "Sed", "Donec", "Augue", "Pellentesque", "123"]}'
 */

@Component
public class SearchText {

    private List<String> searchText;
    
    public SearchText() {
    }

    public List <String> getSearchText() {
        return searchText;
    }
    
}
