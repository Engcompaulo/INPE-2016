package teste2;

import com.mongodb.BasicDBObject;
import java.util.Map;
import java.util.TreeMap;
import mongodb.Connection;


/**
 *Link Importante: http://mongodb.github.io/mongo-java-driver/2.13/getting-started/quick-tour/
 * @author abraao
 */
public class Main {
    
          public static void main(String[] args) {
                    Connection mongo = new Connection();
                    String word = "Teste";
                    Map<String, Integer> map1 = new TreeMap<String, Integer>();
                    map1.put("2003", 50);
                    map1.put("2004", 60);
                    
                    Map<String, Integer> map2 = new TreeMap<String, Integer>();
                    map2.put("total_2003", 240);
                    map2.put("total_2004", 350);
                    
                    //boolean resultado = mongo.add(word, map1, map2);
                    //System.out.println(resultado);
                    mongo.search(word);
          }
          
          
}
