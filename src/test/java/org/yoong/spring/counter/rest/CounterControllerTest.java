package org.yoong.spring.counter.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.yoong.spring.config.ApplicationContextConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextConfiguration.class})
@WebMvcTest(CounterController.class)
public class CounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String SEARCH_API = "/countapi/count";
    private static final String TOP_API = "/countapi/top";
    
    private static final String AUTH_HEADER = "Authorization";
    private static final String BASIC_AUTH = "Basic dXNlcjpwYXNzMTIz";
    
    private static final String SEARCHTEXT_JSON_1 = "{\"searchText\":[\"Duis\", \"Sed\", \"Donec\", \"Augue\", \"Pellentesque\", \"123\"]}";
    private static final String SEARCHTEXT_JSON_2 = "{\"searchText\":[\"Duis\", \"Duis\", \"duis\", \"dius\"]}";
    
    private static final String TOPRESULT_1 = "vel|17\n"; 
    private static final String TOPRESULT_2 = "vel|17\neget|17\nsed|16\nin|15\net|14\n";
    
    @Test
    public void test_Authorization() throws Exception {
        
        mockMvc.perform(get(TOP_API + "/1")).andExpect(status().isUnauthorized());
        mockMvc.perform(get(TOP_API + "/1").header(AUTH_HEADER, BASIC_AUTH)).andExpect(status().isOk());
    }

    @Test
    public void testSearch_simple()  throws Exception{
        
        mockMvc.perform(post(SEARCH_API).contentType(MediaType.APPLICATION_JSON).header(AUTH_HEADER, BASIC_AUTH)
                .content(SEARCHTEXT_JSON_1))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.counts", hasSize(6)))
                .andExpect(jsonPath("$.counts[0].Duis", is(11)))
                .andExpect(jsonPath("$.counts[1].Sed", is(16)))
                .andExpect(jsonPath("$.counts[2].Donec", is(8)))
                .andExpect(jsonPath("$.counts[3].Augue", is(7)))
                .andExpect(jsonPath("$.counts[4].Pellentesque", is(6)))
                .andExpect(jsonPath("$.counts[5].123", is(0)));
    }
    
    @Test
    public void testSearch_repeated()  throws Exception {
        
        mockMvc.perform(post(SEARCH_API).contentType(MediaType.APPLICATION_JSON).header(AUTH_HEADER, BASIC_AUTH)
                .content(SEARCHTEXT_JSON_2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.counts", hasSize(4)))
                .andExpect(jsonPath("$.counts[0].Duis", is(11)))
                .andExpect(jsonPath("$.counts[1].Duis", is(11)))
                .andExpect(jsonPath("$.counts[2].duis", is(11)))
                .andExpect(jsonPath("$.counts[3].dius", is(0)));
    }

    @Test
    public void testSearch_invalidContentType()  throws Exception{
    
        mockMvc.perform(post(SEARCH_API).contentType(MediaType.APPLICATION_XML).header(AUTH_HEADER, BASIC_AUTH)
                .content("invalid"))
                .andExpect(status().isUnsupportedMediaType());
    }
    
    @Test
    public void testSearch_invalidContent()  throws Exception{
        
        mockMvc.perform(post(SEARCH_API).contentType(MediaType.APPLICATION_JSON).header(AUTH_HEADER, BASIC_AUTH)
                .content("invalid"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testSearch_invalidMethod()  throws Exception{
    
        mockMvc.perform(get(SEARCH_API).contentType(MediaType.APPLICATION_JSON).header(AUTH_HEADER, BASIC_AUTH)
                .content(SEARCHTEXT_JSON_1))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testTop_limit1() throws Exception{
        
        mockMvc.perform(get(TOP_API + "/1").header(AUTH_HEADER, BASIC_AUTH))
                .andExpect(status().isOk())
                .andExpect(content().string(TOPRESULT_1));
    }
    
    @Test
    public void testTop_limit5() throws Exception{
    
        mockMvc.perform(get(TOP_API + "/5").header(AUTH_HEADER, BASIC_AUTH).accept("text/csv"))
                .andExpect(status().isOk())
                .andExpect(content().string(TOPRESULT_2));
    }

    @Test
    public void testTop_invalidUrl() throws Exception{
        
        mockMvc.perform(get(TOP_API + "/abc").header(AUTH_HEADER, BASIC_AUTH))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testTop_invalidAccept() throws Exception{
        
        mockMvc.perform(get(TOP_API + "/5").header(AUTH_HEADER, BASIC_AUTH).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }

}
