package utils;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import com.mongodb.DBObject;

import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import java.util.Map;
import java.util.TreeSet;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author abraao
 */
public class Mongo {
          private static final MongoDatabase MY_DB = (new MongoClient(new ServerAddress("localhost", 27017)) ).getDatabase("alunos_e_orientadores_inpe");
          
          public static TreeSet<String> getDiscentesCPFs(){
                    TreeSet<String> discentesCPFs = new TreeSet<String>();
                    FindIterable<Document> iterable = Mongo.MY_DB.getCollection("discentes").find();
                    iterable.forEach(new Block<Document>(){
                              @Override
                              public void apply(final Document document){
                                        String cpf = document.get("_id").toString();
                                        //int i = cpf.indexOf('E');
                                        //cpf = cpf.substring(0, 1)+cpf.substring(2, i);
                                        discentesCPFs.add(cpf);
                              }
                    });
                    //return new Gson().toJson(discentesCPFs);
                    return discentesCPFs;
          }
          
          public static Object getDiscentesDatas(){
                    JSONArray jsonArray = new  JSONArray();
                    TreeSet<String> discentesCPFs = getDiscentesCPFs();
                    for(String cpf : discentesCPFs){
                              BasicDBObject query = new BasicDBObject();
                              query.put("_id", cpf);
                              FindIterable<Document> iterable = Mongo.MY_DB.getCollection("discentes").find(query);
                              iterable.forEach(new Block<Document>(){
                                        @Override
                                        public void apply(final Document document){
                                                  Map<String, Integer> data_inicial = (Map<String, Integer>) document.get("data_inicial");
                                                  Map<String, Integer> data_final = (Map<String, Integer>) document.get("data_final");
                                                  JSONObject jsonObject = new JSONObject();
                                                  jsonObject.put("cpf", cpf);
                                                  jsonObject.put("data_inicial", data_inicial);
                                                  jsonObject.put("data_final", data_final);
                                                  jsonArray.put(jsonObject);
                                        }
                              });                              
                    }
                    return jsonArray;
          }
          
          
}
