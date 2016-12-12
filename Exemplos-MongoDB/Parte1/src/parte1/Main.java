
package teste1;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author abraao
 * Link: https://www.youtube.com/watch?v=GBInNEDmWX8
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
          MongoClient mongoClient = new MongoClient(new ServerAddress(), options);//MongoClient mongoClient = new MongoClient(new MongoClientURI("mongod://localhost:27017"));
    
          MongoDatabase my_db = mongoClient.getDatabase("testemongo");
          MongoCollection<Document> my_collection = my_db.getCollection("my_collection");
          
          /*Document doc1 = new Document().append("id", 1111)
          .append("nome", "Jose")
          .append("list", Arrays.asList(1, "um", 2, "dois"));
          Document doc2 = new Document().append("id", 2222)
          .append("nome", "Maria")
          .append("list", Arrays.asList(3, "tres", 4, "quatro"));
          my_collection.insertMany(Arrays.asList(doc1, doc2));*/
          
         /* Document firstDocument = my_collection.find().first();
          JSONArray jsonArray = new JSONArray();
          jsonArray.put(firstDocument);
          System.out.println(jsonArray.toString());*/
         
         /*List<Document> allDocuments = my_collection.find().into(new ArrayList<Document>());
         JSONArray jsonArray = new JSONArray();
         for(Document document : allDocuments){
                    //System.out.println(document.toJson());
                    //jsonArray.put(document);
                    jsonArray.put(document.toJson());
         }  
         System.out.println(jsonArray.toString());*/
         
    }
    
}
