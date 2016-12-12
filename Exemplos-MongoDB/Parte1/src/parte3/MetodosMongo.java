package teste3;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import mongodb.*;

import java.util.TreeSet;
import org.bson.Document;

/**
 *
 * @author abraao
 */
public class MetodosMongo {
          private static final MongoDatabase MY_DB = (new MongoClient(new ServerAddress("localhost", 27017)) ).getDatabase("alunos_e_orientadores_inpe");
          
          public TreeSet<String> getCPFs(){
                    TreeSet<String> resultado = new TreeSet<String>();
                    FindIterable<Document> iterable = MetodosMongo.MY_DB.getCollection("pessoas").find();
                    iterable.forEach(new Block<Document>(){
                              @Override
                              public void apply(final Document document){
                                        resultado.add(document.get("_id").toString());
                              }
                    });
                    return resultado;
          }
          
          public TreeSet <String> getCPFsComLattesAssociado(){
                    TreeSet<String> resultado = new TreeSet<String>();
                    FindIterable<Document> iterable = MetodosMongo.MY_DB.getCollection("pessoas").find(Filters.exists("id_lattes"));
                    iterable.forEach(new Block<Document>(){
                              public void apply(final Document document){
                                        resultado.add(document.get("_id").toString());
                              }
                    });
                    return resultado;
          }
          
          
}
