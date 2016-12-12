package utils;

import com.google.gson.Gson;
import java.util.ArrayList;
import org.json.JSONArray;

/**
 *
 * @author abraao
 */
public class MyClass {
          public static String getToken()  {
                    return "a token";
          }
          
          public static ArrayList<String> getList(){
                    ArrayList<String> a = new ArrayList<String>();
                    int r = (int)(Math.random()*10);
                    for(int x=0;x<r;x++)
                              a.add("X"+((int)(Math.random()*1000)));
                    return a;
          }
          
          public static Object getObject(){
                    JSONArray jsonArray = new  JSONArray();
                    ArrayList<String> lista1 = new ArrayList<String>();
                    ArrayList<String> lista2 = new ArrayList<String>();
                    lista1.add("Cachorro");
                    lista1.add("Gato");
                    lista1.add("Rato");
                    lista2.add("Tubarão");
                    lista2.add("Hipopotamo");
                    jsonArray.put(lista1);
                    jsonArray.put(lista2);
                    //return new Gson().toJson(lista);
                    //return new Gson().toJson(jsonArray);
                    return jsonArray;
          }
}
