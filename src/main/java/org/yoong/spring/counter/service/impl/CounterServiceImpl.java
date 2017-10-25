package org.yoong.spring.counter.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yoong.spring.counter.domain.SearchResponse;
import org.yoong.spring.counter.domain.SearchResponseBuilder;
import org.yoong.spring.counter.service.CounterService;
import org.yoong.spring.counter.util.CounterUtil;
import org.yoong.spring.data.ParagraphData;

@Component
public class CounterServiceImpl implements CounterService {

    private ParagraphData data;
    
    @Autowired
    public CounterServiceImpl(ParagraphData data) {
        this.data = data;
    }

    public SearchResponse search(List<String> searchText) {

        SearchResponseBuilder responseBuilder = new SearchResponseBuilder();

        Map<String, Integer> frequencies = countFrequencies(data.getParagraph());

        // loop through input and set the frequencies into the output.
        for (String text : searchText) {
            Integer count = frequencies.get(text.toLowerCase());
            responseBuilder.addCount(text, count != null ? count : 0);
        }

        return responseBuilder.build();
    }

    public String top(int limit) {

        StringBuilder sb = new StringBuilder();
        
        Map<String, Integer> frequencies = countFrequencies(data.getParagraph());

        // sort the frequencies and format the top <limit> to csv.
        frequencies.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) 
        .limit(limit) 
        .forEach(a -> sb.append(a.getKey()).append("|").append(a.getValue()).append("\n"));

        return sb.toString();
    }

    /**
     * Returns a map of frequencies of all unique words in the paragraph.
     * 
     * Note: this could be cached, to save processing time for future top/search requests.
     * 
     * @param paragraph
     * @return
     */
    private Map<String, Integer> countFrequencies(String paragraph) {

        return CounterUtil.countFrequencies(paragraph);

    }

}
