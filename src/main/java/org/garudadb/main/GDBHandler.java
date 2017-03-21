/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.garudadb.main;

import com.google.gson.Gson;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import java.net.UnknownHostException;

/**
 *
 * @author mardian
 */
public class GDBHandler {
    
    public static void createObject(String jsonString) throws UnknownHostException {

        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("testDB");

        DBCollection col = db.getCollection("test");
        
        Gson gson = new Gson();
        Sequence seq = gson.fromJson(jsonString, Sequence.class);
        DBObject doc = createDBObject(seq);
        WriteResult result = col.insert(doc);
        
        System.out.println("***Object succesfully created!");

        //System.out.println(result.getUpsertedId());
        //System.out.println(result.getN());
        //System.out.println(result.isUpdateOfExisting());
        //System.out.println(result.getLastConcern());

        mongo.close();
    }
    
    public static void readObject(String jsonString) throws UnknownHostException {
        
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("testDB");

        DBCollection col = db.getCollection("test");
        
        Gson gson = new Gson();
        Sequence seq = gson.fromJson(jsonString, Sequence.class);
        String key = "";
        String val = "";
        
        if (seq.get_id()!=null) {key = "_id"; val = seq.get_id();}
        else if (seq.getName()!=null) {key = "name"; val = seq.getName();}
        
        if (key!="") {
        
            DBObject query = BasicDBObjectBuilder.start().add(key, val).get();
            DBCursor cursor = col.find(query);

            while (cursor.hasNext()) {
                System.out.println("***Query result: " + cursor.next());
            }
        }
        else
            System.out.println("***Query result: no object matches!");
        
        mongo.close();
    }
    
    public static void updateObject(String jsonString) throws UnknownHostException {

        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("testDB");

        DBCollection col = db.getCollection("test");
        
        Gson gson = new Gson();
        Sequence seq = gson.fromJson(jsonString, Sequence.class);
        String key = "";
        String val = "";
        
        if (seq.get_id()!=null) {key = "_id"; val = seq.get_id();}
        
        //seq.setName("updated_name");
        
        DBObject query = BasicDBObjectBuilder.start().add(key, val).get();
        DBObject doc = createDBObject(seq);
        
        WriteResult result = col.update(query, doc);
        
        System.out.println("***Object succesfully updated!");
        
        /*System.out.println(result.getUpsertedId());
        System.out.println(result.getN());
        System.out.println(result.isUpdateOfExisting());
        //System.out.println(result.getLastConcern());*/
        
        mongo.close();
        
    }
    
    public static void deleteObject(String jsonString) throws UnknownHostException {

        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("testDB");

        DBCollection col = db.getCollection("test");
        
        Gson gson = new Gson();
        Sequence seq = gson.fromJson(jsonString, Sequence.class);
        String key = "";
        String val = "";
        
        if (seq.get_id()!=null) {key = "_id"; val = seq.get_id();}
        else if (seq.getName()!=null) {key = "name"; val = seq.getName();}
        
        if (key!="") {
            DBObject query = BasicDBObjectBuilder.start().add(key, val).get();

            WriteResult result = col.remove(query);

            System.out.println("***Object succesfully deleted!");
        }
        else
            System.out.println("***No object matches!");
        
        /*System.out.println(result.getUpsertedId());
        System.out.println(result.getN());
        System.out.println(result.isUpdateOfExisting());
        //System.out.println(result.getLastConcern());*/

        mongo.close();
        
    }

    private static DBObject createDBObject(Sequence sequence) {
        
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", sequence.get_id());
        docBuilder.append("name", sequence.getName());
        docBuilder.append("sequence", sequence.getSequence());
        docBuilder.append("author", sequence.getAuthor());
        
        return docBuilder.get();
    }

    /*private static Sequence createSequence(String json) {
        Sequence sequence = new Sequence();
        sequence.set_id((String)json.get("id"));
        sequence.setName((String)json.get("name"));
        sequence.setSequence((String)json.get("sequence"));
        sequence.setAuthor((String)json.get("author"));
        return sequence;
    }*/
}
