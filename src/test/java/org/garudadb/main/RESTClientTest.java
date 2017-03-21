/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.garudadb.main;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author mardian
 */
public class RESTClientTest {
    
    @Test
    public void restClient() {
        
        //////////GET request
        /*System.out.println("** Sequence get starts here!");
        sequenceGet();
        System.out.println("** Sequence get finishes here!");*/
        
        long startTime = System.currentTimeMillis();
        
        //////////createObject
        int id = 0;
        for (int i=0; i<10; i++) {
            String input = "{\"_id\":\"" + (id+i) + "\",\"name\":\"new_seq\",\"sequence\":\"ATCG\",\"author\":\"mardian\"}";
            sequencePost(input, "create");
        }
        
        //////////readObject
        /*String input1 = "{\"_id\":\"2\"}";
        String input2 = "{\"name\":\"new_seq\"}";
        sequencePost(input1, "search");
        sequencePost(input2, "search");*/
        
        //////////updateObject
        /*String input = "{\"_id\":\"9\",\"name\":\"updated_name\",\"sequence\":\"ATCG\",\"author\":\"mardian\"}";
        sequencePost(input, "update");*/
        
        //////////deleteObject
        /*String input = "{\"name\":\"updated_name\"}";
        sequencePost(input, "delete");*/
        
        
        System.out.println("+++++ Rest client finished at: " + (System.currentTimeMillis() - startTime) + " miliseconds.");
    }
    
    public static void sequenceGet () {
        
        final String uri = "http://localhost:8080/sequence";

        RestTemplate restTemplate = new RestTemplate();
        /*String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);*/
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        System.out.println("***" + result);
        
    }
    
    public static void sequencePost (String input, String query) {
        
        final String uri = "http://localhost:8080/sequence/" + query;

        /*Sequence sequence = new Sequence ();
        sequence.set_id("1234");
        sequence.setName("rand_seq");
        sequence.setSequence("GCTA");
        sequence.setAuthor("Badu");
    
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);*/
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        
        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("Authorization", "Basic " + "xxxxxxxxxxxx");
        HttpEntity<String> entity = new HttpEntity<String>(input, headers);

        // send request and parse result
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        //System.out.println("***" + result);
    }
    
}
