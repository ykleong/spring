package org.yoong.spring.counter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yoong.spring.counter.domain.SearchResponse;
import org.yoong.spring.counter.domain.SearchText;
import org.yoong.spring.counter.service.CounterService;

@RestController
@RequestMapping(value = "/countapi")
public class CounterController {
    
    private static final Logger logger = LoggerFactory.getLogger(CounterController.class);
    
    private CounterService counterService;
    
    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(path = "/count", method = RequestMethod.POST, consumes = "application/json")
    public SearchResponse search(@RequestBody SearchText searchText) {
        
        logger.debug("received COUNT: " + searchText.getSearchText());
        
        return counterService.search(searchText.getSearchText());
    }

    @RequestMapping(path = "/top/{topCount}", method = RequestMethod.GET, produces = "text/csv")
    public String top(@PathVariable int topCount) {

        logger.debug("received TOP: " + topCount);

        return counterService.top(topCount);
    }

}
