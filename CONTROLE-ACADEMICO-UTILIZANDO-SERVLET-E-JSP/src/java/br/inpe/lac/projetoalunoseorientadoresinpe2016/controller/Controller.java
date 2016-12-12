/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inpe.lac.projetoalunoseorientadoresinpe2016.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
/**
 *https://www.youtube.com/watch?v=L51rXE-78kM
 * https://www.youtube.com/watch?v=5J1LLfkkuvo
 * @author abraao
 */
@WebServlet(name = "Controller", urlPatterns = {"/pessoas"}) 
public class Controller extends HttpServlet {
          
          public  String getAllPersons() throws Exception{
                   MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build(); 
                   MongoClient mongoClient = new MongoClient(new ServerAddress(), options);
                   MongoDatabase my_db = mongoClient.getDatabase("alunos_e_orientadores_inpe");
                   MongoCollection<Document> pessoas = my_db.getCollection("pessoas");
                   List<Document>   list = pessoas.find().into(new ArrayList<Document>());
                   return new Gson().toJson(list);
          }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
          PrintWriter out = response.getWriter();
        try {
                   out.println(getAllPersons());
          }catch(Exception e){
                   out.println("Error: "+e.getMessage());
          }finally{
                   out.close();
         }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
