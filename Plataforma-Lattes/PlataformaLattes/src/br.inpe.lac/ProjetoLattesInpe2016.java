package projetolattesinpe2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.DocumentException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author abraao
 */
public class ProjetoLattesInpe2016 {

    public static void main(String[] args) throws DocumentException{  
        String baseDir = "/home/abraao/NetBeansProjects/ProjetoLattes2016/Data/";
       
	File dir = new File(baseDir);
	File[] lista_de_arquivos = dir.listFiles();
		
	//paisDeNascimento(lista_de_arquivos);
	
       // ultimaAtualizacao(lista_de_arquivos);
        artigosPublicados(lista_de_arquivos);
      
    }
    
    
    public static void artigosPublicados(File[] lista_de_arquivos) throws DocumentException{       
        List<Integer> anos = new ArrayList<Integer>();
        anos.add(2010);anos.add(2011);anos.add(2012);anos.add(2013);anos.add(2014);anos.add(2015);anos.add(2016);
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
        treeMap.put(2010, 0);treeMap.put(2011, 0);treeMap.put(2012, 0);treeMap.put(2013, 0);treeMap.put(2014, 0);treeMap.put(2015, 0);treeMap.put(2016, 0);
        JSONArray jsonArray = new JSONArray();	
        Integer totalDeArtigosPublicados=0;
        
        for(File file : lista_de_arquivos){
            LattesInfo parser = new LattesInfo(file);
            
            TreeMap<Integer, Integer> resultMap = parser.pegaMapaDeArtigosCompletos();
            for(Integer ano : anos){
                if(resultMap.containsKey(ano)){
                    treeMap.put(ano, treeMap.get(ano)+resultMap.get(ano));
                }
            }
            
            totalDeArtigosPublicados += parser.pegaTotalDeArtigosCompletos();
        }
        
        Set<Integer> keys_set = treeMap.keySet();
        Collection<Integer> values_collection = treeMap.values();
        Object[] keys = keys_set.toArray();
        Object[] values = values_collection.toArray();
        
        for(int i = 0; i < keys.length; i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("year", keys[i]);
            jsonObject.put("number", values[i]);
            jsonArray.put(jsonObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", totalDeArtigosPublicados);
        jsonArray.put(jsonObject);
        System.out.println(jsonArray.toString());
       
        try{
            PrintWriter printWriter = new PrintWriter("/var/www/html/ProjetoLattesInpe2016/artigosPublicados.json");
            printWriter.write(jsonArray.toString());
            printWriter.flush();
            printWriter.close();
        }catch(IOException e){
            
        }
    }
    
    
    public static void ultimaAtualizacao(File[] lista_de_arquivos) throws DocumentException{       
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
        treeMap.put(2010, 0);treeMap.put(2011, 0);treeMap.put(2012, 0);treeMap.put(2013, 0);treeMap.put(2014, 0);treeMap.put(2015, 0);treeMap.put(2016, 0);
        JSONArray jsonArray = new JSONArray();	
        
        for(File file : lista_de_arquivos){
            LattesInfo parser = new LattesInfo(file);
            if(treeMap.containsKey(parser.getUltimaAtualizacao())){
                treeMap.put(parser.getUltimaAtualizacao(), treeMap.get(parser.getUltimaAtualizacao())+1);
            }
        }
        
        Set<Integer> keys_set = treeMap.keySet();
        Collection<Integer> values_collection = treeMap.values();
        Object[] keys = keys_set.toArray();
        Object[] values = values_collection.toArray();
        
        for(int i = 0; i < keys.length; i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("year", keys[i]);
            jsonObject.put("number", values[i]);
            jsonArray.put(jsonObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", lista_de_arquivos.length);
        jsonArray.put(jsonObject);
        System.out.println(jsonArray.toString());
        try{
            PrintWriter printWriter = new PrintWriter("/var/www/html/ProjetoLattesInpe2016/ultimaAtualizacao.json");
            printWriter.write(jsonArray.toString());
            printWriter.flush();
            printWriter.close();
        }catch(IOException e){
            
        }
    }
    

    public static void paisDeNascimento(File[] lista_de_arquivos) throws DocumentException{
        JSONArray jsonArray = new JSONArray();	
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
        
        
        for(File file : lista_de_arquivos){
            LattesInfo parser = new LattesInfo(file);
                if(treeMap.containsKey(parser.getPaisDeNascimento())){
                    treeMap.put(parser.getPaisDeNascimento(), treeMap.get(parser.getPaisDeNascimento())+1);
                }else{
                    treeMap.put(parser.getPaisDeNascimento(), 1);
                }
            
	}
    
        
        Set<String> keys_set = treeMap.keySet();
        Collection<Integer> values_collection = treeMap.values();
  
        Object[] keys= keys_set.toArray();
        Object[] values = values_collection.toArray();
    
        for(int i = 0; i < keys.length; i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("country", keys[i]);
            jsonObject.put("number", values[i]);
            jsonArray.put(jsonObject);
        }
        
        System.out.println(jsonArray.toString());
       
        try{
            PrintWriter printWriter = new PrintWriter("/var/www/html/ProjetoLattesInpe2016/paisDeNascimento.json");
            printWriter.write(jsonArray.toString());
            printWriter.flush();
            printWriter.close();
        }catch(IOException e){
            
        }
        
    }
    
}
