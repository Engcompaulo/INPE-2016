/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inpe.lac.projetoalunoseorientadoresinpe2016.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author abraao
 */
public class Mongo {
          private MongoClientOptions options; 
          private MongoClient mongoClient;    
          private MongoDatabase my_db; 
          private MongoCollection<Document> alunos; 
          private MongoCollection<Document> orientadores;
          
          public Mongo(){
                    options = MongoClientOptions.builder().connectionsPerHost(100).build(); 
                    mongoClient = new MongoClient(new ServerAddress(), options);//MongoClient mongoClient = new MongoClient(new MongoClientURI("mongod://localhost:27017"));
                    my_db = mongoClient.getDatabase("alunos_orientadores_inpe");
                    alunos  = my_db.getCollection("alunos");
                    orientadores = my_db.getCollection("orientadores");
          }
          
          public List<Document> getAllAlunos(){
                    List<Document> list = alunos.find().into(new ArrayList<Document>());
                    return list;
          }
          public List<Document> getAllOrientadores(){
                    List<Document> list = orientadores.find().into(new ArrayList<Document>());
                    return list;
          }
}
