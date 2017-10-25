package org.yoong.spring.counter.domain;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class SearchResponseBuilder {

    private List<SimpleEntry<String, Integer>> counts = new ArrayList<SimpleEntry<String, Integer>>();

    public SearchResponseBuilder addCount(String name, Integer count) {
        this.counts.add(new SimpleEntry<String, Integer>(name, count));
        return this;
    }

    public SearchResponse build() {
        return new SearchResponse(counts);
    }

}
