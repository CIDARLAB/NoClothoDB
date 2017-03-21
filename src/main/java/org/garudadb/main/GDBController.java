/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.garudadb.main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mardian
 */
@RestController
public class GDBController {
    
    @RequestMapping(value="/sequence")
    public ResponseEntity<Sequence> getSequence () {
        
        Sequence sequence = new Sequence ();
        sequence.set_id("1234");
        sequence.setName("rand_seq");
        sequence.setSequence("GCTA");
        sequence.setAuthor("mardian");
    
        return new ResponseEntity<Sequence> (sequence, HttpStatus.OK);
        
    }
    
    @RequestMapping(value="/sequence/create", method=RequestMethod.POST)
    public ResponseEntity<Sequence> createSequence (@RequestBody String jsonString) {
        
        GDBHandler handler = new GDBHandler();
        try {
            handler.createObject(jsonString);
        } catch (Exception e) {e.printStackTrace();}
            
        return new ResponseEntity<Sequence> (HttpStatus.OK);
        
    }
    
    @RequestMapping(value="/sequence/search", method=RequestMethod.POST)
    public ResponseEntity<Sequence> searchSequence (@RequestBody String jsonString) {
        
        GDBHandler handler = new GDBHandler();
        try {
            handler.readObject(jsonString);
        } catch (Exception e) {e.printStackTrace();}
            
        return new ResponseEntity<Sequence> (HttpStatus.OK);
        
    }
    
    @RequestMapping(value="/sequence/update", method=RequestMethod.POST)
    public ResponseEntity<Sequence> updateSequence (@RequestBody String jsonString) {
        
        GDBHandler handler = new GDBHandler();
        try {
            handler.updateObject(jsonString);
        } catch (Exception e) {e.printStackTrace();}
            
        return new ResponseEntity<Sequence> (HttpStatus.OK);
        
    }
    
    @RequestMapping(value="/sequence/delete", method=RequestMethod.POST)
    public ResponseEntity<Sequence> deleteSequence (@RequestBody String jsonString) {
        
        GDBHandler handler = new GDBHandler();
        try {
            handler.deleteObject(jsonString);
        } catch (Exception e) {e.printStackTrace();}
            
        return new ResponseEntity<Sequence> (HttpStatus.OK);
        
    }
}
  
