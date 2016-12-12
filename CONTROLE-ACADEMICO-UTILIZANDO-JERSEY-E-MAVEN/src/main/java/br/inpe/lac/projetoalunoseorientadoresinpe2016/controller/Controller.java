
package br.inpe.lac.projetoalunoseorientadoresinpe2016.controller;

import br.inpe.lac.projetoalunoseorientadoresinpe2016.mongo.Mongo;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;
import org.json.JSONArray;

/**
 *
 * @author abraao
 */
@Path("/")
public class Controller {
    
    
          @GET
          @Produces(MediaType.APPLICATION_JSON)
          @Path("alunos")
          public Object getAllAlunos(){
                    List<Document> alunos = (new Mongo()).getAllAlunos();
                    JSONArray jsonArray = new JSONArray();
                    for(Document document : alunos){  
                              //jsonArray.put(document);
                              jsonArray.put(document.toJson());
                    }
                    /*List<String> list = new ArrayList<>();
                    list.add("Um");
                    list.add("Dois");
                    return list;*/
                    return jsonArray;
          }
          
          @GET
          @Produces(MediaType.APPLICATION_JSON)
          @Path("orientadores")
          public Object getAllOrientadores(){
                    List<Document> orientadores = (new Mongo()).getAllOrientadores();
                    JSONArray jsonArray = new JSONArray();
                    for(Document document : orientadores){  
                              jsonArray.put(document.toJson());
                    }
                  
                    return jsonArray;
          }
          
          
}
