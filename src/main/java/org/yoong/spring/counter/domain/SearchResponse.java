package org.yoong.spring.counter.domain;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * This object maps to json response as required by the 'search' api.
 * 
 * eg. {"counts": [{"Duis": 11}, {"Sed": 16}, {"Donec": 8}, {"Augue": 7}, {"Pellentesque": 6}, {"123": 0}]}
 */

@Component
public class SearchResponse {

    private List<SimpleEntry<String, Integer>> counts;

    public SearchResponse() {
    }

    public SearchResponse(List<SimpleEntry<String, Integer>> counts) {
        this.counts = counts;
    }

    public List<SimpleEntry<String, Integer>> getCounts() {
        return counts;
    }

}
