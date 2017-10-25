package org.yoong.spring.counter.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.yoong.spring.counter.util.CounterUtil;
import org.yoong.spring.data.impl.SampleParagraphDataImpl;

public class CounterUtilTest {

    @Test
    public void countFrequenciesTest_simple() {
        
        String text = "The quick brown fox jumps over the lazy dog.";
        
        Map<String, Integer> freq = CounterUtil.countFrequencies(text);
        
        // assert number of unique words and checks that it ignore lower-case/upper-case
        assertEquals("Check number of unique words counted", 8, freq.size());
        
        assertEquals("Check frequency of word 'the'", 2, (int) freq.get("the"));
        assertEquals("Check frequency of word 'fox'", 1, (int) freq.get("fox"));
    }
    
    @Test
    public void countFrequenciesTest_nullempty() {
        
        String text = "";
        
        Map<String, Integer> freq = CounterUtil.countFrequencies(text);
        assertEquals("Check number of unique words counted", 0, freq.size());
     
    }
    
    @Test
    public void countFrequenciesTest_sequentialPunctuation() {
        
        String text = "\"Hello?\", asked Jane, \"Is anybody there?\"";
        
        Map<String, Integer> freq = CounterUtil.countFrequencies(text);
        
        assertEquals("Check number of unique words counted", 6, freq.size());
    }
    
    @Test
    public void countFrequenciesTest_sampleParagraph() {
        
        SampleParagraphDataImpl sampleParagraph = new SampleParagraphDataImpl();
        
        Map<String, Integer> freq = CounterUtil.countFrequencies(sampleParagraph.getParagraph());
        
        assertEquals("Check number of unique words counted", 175, freq.size());
        assertEquals("Check frequency of word 'eget'", 17, (int) freq.get("eget"));
        
    }
   

}
