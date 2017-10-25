package org.yoong.spring.counter.service;

import java.util.List;

import org.yoong.spring.counter.domain.SearchResponse;

/**
 * Interface for Counter service.
 */
public interface CounterService {

    public SearchResponse search(List <String> searchText);
    
    public String top(int limit);
    
}
