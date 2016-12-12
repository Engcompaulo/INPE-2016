
package mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.util.Map;


/**
 *
 * @author abraao
 */
public class Connection {
          private Mongo mongo;
          private DB my_db;
          private DBCollection my_collection;
          
          public Connection(){
                    mongo = new MongoClient("localhost",27017);
                    my_db = mongo.getDB("testemongo");
                    my_collection = my_db.getCollection("my_collection");
          }
          
          public boolean add(String word, Map<String, Integer> docOccurrence, Map<String, Integer> totalOccurrence){
                    BasicDBObject document = new BasicDBObject();
                    document.put("word", word);
                    document.put("docOccurrence", new BasicDBObject(docOccurrence));
                    document.put("totalOccurrence", new BasicDBObject(totalOccurrence));
                    try{
                              my_collection.insert(document);
                    }catch(MongoException e){
                              System.out.println(e.getMessage());
                              return false;
                    }
                    return true;
          }
          
          public void search(String word){
                    BasicDBObject searchQuery = new BasicDBObject();
                    searchQuery.put("word", word);
                    try{
                              DBCursor cursor = my_collection.find(searchQuery);
                              while(cursor.hasNext()){
                                        DBObject document = cursor.next();
                                        Map<String, Integer> map1 = (Map<String, Integer>) document.get("docOccurrence");
                                        System.out.println("docOccurrence ->  "+map1.toString());
                                        Map<String, Integer> map2 = (Map<String, Integer>) document.get("totalOccurrence");
                                        System.out.println("totalOccurrence -> "+map2.toString());
                              }
                    }catch(MongoException e){
                              System.out.println(e.getMessage());
                    }
          }
}
