package br.inpe.lac;


import com.mongodb.Block;

import com.mongodb.MongoClient;

import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;

import java.util.TreeMap;
import org.bson.Document;

public class Mongo {
    private static final MongoClientURI URI = new MongoClientURI("mongodb://abraao:angra201294@ds157677.mlab.com:57677/controle_academico");
    private static final MongoDatabase MY_DB = (new MongoClient(URI)).getDatabase(URI.getDatabase());
    private static final Document ordemCrescente = new Document("nome", 1);
    
    private static int menorAno;
    private static int maiorAno;


    /**Este método retorna uma lista com os nomes e os registros dos docentes. 
     * 
     * @return String - Lista com os nomes e os registros dos docentes
     */
    public static String getDocentes(){
        StringBuilder sb = new StringBuilder();
        //sb.append("[");
        FindIterable<Document> iterable = MY_DB.getCollection("pessoas").find(Filters.eq("papel", "docente")).sort(ordemCrescente);
        iterable.forEach(new Block<Document>(){
            @Override
            public void apply(final Document document){
                if(document.getString("nome") != null){
                    sb.append("\""+document.getString("_id")+"\", ");
                    sb.append("\""+document.getString("nome")+"\", ");
                }
                
            }
        });
        //sb.append("]");
        return sb.toString();
    }
    
    
    
    
    /* **********************************************************************************************************************************
       ********************************************************************************************************************************** 
       ********************************************************************************************************************************** */
    
    
    /**Esté método vai armazenar na variável estática "linhaDoTempoDoDocente a linha do tempo do docente cujo id é passado
     * como parâmetro.
     * 
     * @param id - Número de identificação de um Docente no formato texto
     */
    
    public static String construaLinhaDoTempoDoDocente(String id){   
        menorAno = 9999;
        maiorAno = 0;
        TreeMap<String, TreeMap<String, String>> discentes = new TreeMap<>();
        ArrayList<String> chaves = new ArrayList<>();
        StringBuilder linhaDoTempoDoDocente = new StringBuilder();
        ArrayList<Integer> listaAnos = new ArrayList<>();
        
        
        FindIterable<Document> iterable = MY_DB.getCollection("pessoas").find(Filters.eq("papel", "discente")).sort(ordemCrescente);
        iterable.forEach(new Block<Document>(){
            @Override
            public void apply(final Document document){
                if(document.get("orientador1") != null && document.getString("orientador1").equals(id) ||  document.get("orientador2") != null && document.getString("orientador2").equals(id) || document.get("orientador3") != null && document.getString("orientador3").equals(id) ){
                    String nome = document.getString("nome");
                    int anoAdmissao = Integer.parseInt(document.getString("data_admissao").substring(0, 4));
                    int anoConclusao = Integer.parseInt(document.getString("data_final").substring(0, 4));
                    int periodoConclusao = Integer.parseInt(document.getString("data_final").substring(5, 6));
                    System.out.println(periodoConclusao);
                    /*
                     ****************************************************************************************************************************************************
                    *****************************************************************************************************************************************************
                    *****************************************************************************************************************************************************
                    */
                    
                    int anoTrc001 = 0; int periodoTrc001 = 0;
                    int anoTrc002 = 0; int periodoTrc002 = 0;
                    int anoTrc003 = 0; int periodoTrc003 = 0;
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    if(document.get("trc001") != null){
                       anoTrc001 = Integer.parseInt(document.getString("trc001").substring(0, 4));
                       periodoTrc001 = Integer.parseInt(document.getString("trc001").substring(6, 7));
                    }
                    if(document.get("trc002") != null){
                        anoTrc002 = Integer.parseInt(document.getString("trc002").substring(0, 4));
                        periodoTrc002 = Integer.parseInt(document.getString("trc002").substring(6, 7));
                    }
                    if(document.get("trc003") != null){
                        anoTrc003 = Integer.parseInt(document.getString("trc003").substring(0, 4));
                        periodoTrc003 = Integer.parseInt(document.getString("trc003").substring(6, 7));
                    }
                    
                    
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    int numeroTrancamentos=0;
                    if(anoTrc001 > 0 && periodoTrc001 > 0)
                        numeroTrancamentos++;
                    
                    if(anoTrc002 > 0 && periodoTrc002 > 0)
                         numeroTrancamentos++;
                    
                    if(anoTrc003 > 0 && periodoTrc003 > 0)
                         numeroTrancamentos++;
                    
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    if(periodoConclusao == 1 && numeroTrancamentos == 3){
                        anoConclusao++;
                    }
                    if(periodoConclusao == 2 && numeroTrancamentos == 2 ){
                        anoConclusao++;
                    }
                    if(periodoConclusao == 2 && numeroTrancamentos == 3 ){
                        anoConclusao++;
                    }
                    if(periodoConclusao == 3 && numeroTrancamentos == 1){
                        anoConclusao++;
                    }
                    if(periodoConclusao == 3 && numeroTrancamentos == 2){
                        anoConclusao++;
                    }
                    if(periodoConclusao == 3 && numeroTrancamentos == 3){
                        anoConclusao++;
                    }
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    TreeMap<String, String> discente = new TreeMap<>();
                    discente.put("nome", nome);
                    discente.put("anoAdmissao", String.valueOf(anoAdmissao));
                    discente.put("anoConclusao", String.valueOf(anoConclusao));
                    discente.put("anoConclusao", String.valueOf(anoConclusao));
                    discente.put("anoTrc001", String.valueOf(anoTrc001));
                    discente.put("periodoTrc001", String.valueOf(periodoTrc001));
                    discente.put("anoTrc002", String.valueOf(anoTrc002));
                    discente.put("periodoTrc002", String.valueOf(periodoTrc002));
                    discente.put("anoTrc003", String.valueOf(anoTrc003));
                    discente.put("periodoTrc003", String.valueOf(periodoTrc003));
                    discentes.put(document.getString("_id"), discente);
                    chaves.add(document.getString("_id"));

                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    if(anoAdmissao < menorAno)menorAno = anoAdmissao;
                    if(anoConclusao > maiorAno)maiorAno = anoConclusao;

                }
            }
        });
        

        /*
         ****************************************************************************************************************************************************
         *****************************************************************************************************************************************************
         *****************************************************************************************************************************************************
        */
        
        for(int ano = menorAno; ano <= maiorAno; ano++){
            listaAnos.add(ano);
        }  
        
        /*
         ****************************************************************************************************************************************************
         *****************************************************************************************************************************************************
         *****************************************************************************************************************************************************
        */
        
        linhaDoTempoDoDocente.append("<table id=\"timeline\" border=\"0\"  cellspacing=\"1\" cellpadding=\"0\" style=\"margin: 0 auto;\">\n");
        
        linhaDoTempoDoDocente.append("<tr>\n  <td></td>\n");
        
        for(int ano : listaAnos){
            linhaDoTempoDoDocente.append("  <td colspan=\"3\" align=middle>").append(ano).append("</td>\n");
            if(ano < maiorAno)
                linhaDoTempoDoDocente.append("  <td></td>\n");
        }
        
        linhaDoTempoDoDocente.append("</tr>\n");
        
        linhaDoTempoDoDocente.append("<tr>\n");
        
        linhaDoTempoDoDocente.append("  <td></td>\n");
        
        for(int ano : listaAnos){
            linhaDoTempoDoDocente.append("  <td align=middle>1</td>\n");
            linhaDoTempoDoDocente.append("  <td align=middle>2</td>\n");
            linhaDoTempoDoDocente.append("  <td align=middle>3</td>\n");
            if(ano < maiorAno)
                linhaDoTempoDoDocente.append("  <td></td>\n");
        }
        linhaDoTempoDoDocente.append("</tr>\n");
        
        
        
        for(String chave : chaves){
            TreeMap<String, String> discente = discentes.get(chave);
            linhaDoTempoDoDocente.append("<tr>\n");
            linhaDoTempoDoDocente.append("  <td>").append(discente.get("nome")).append("</td>\n");
            for(int ano : listaAnos){
                if(Integer.parseInt(discente.get("anoAdmissao")) <= ano && Integer.parseInt(discente.get("anoConclusao")) >= ano){
                    if(Integer.parseInt(discente.get("anoTrc001")) == ano && Integer.parseInt(discente.get("anoTrc002")) == ano && Integer.parseInt(discente.get("anoTrc003")) == ano){
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                    }
                    else if(Integer.parseInt(discente.get("anoTrc001")) == ano && Integer.parseInt(discente.get("anoTrc002")) == ano && Integer.parseInt(discente.get("anoTrc003")) != ano){
                        if(Integer.parseInt(discente.get("periodoTrc001")) != 1 && Integer.parseInt(discente.get("periodoTrc002")) != 1){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        }else if(Integer.parseInt(discente.get("periodoTrc001")) != 2 && Integer.parseInt(discente.get("periodoTrc002")) != 2){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        }else if(Integer.parseInt(discente.get("periodoTrc001")) != 3 && Integer.parseInt(discente.get("periodoTrc002")) != 3){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                        }else{
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                        }
                    } else if(Integer.parseInt(discente.get("anoTrc002")) == ano && Integer.parseInt(discente.get("anoTrc003")) == ano && Integer.parseInt(discente.get("anoTrc001")) != ano){
                        if(Integer.parseInt(discente.get("periodoTrc002")) != 1 && Integer.parseInt(discente.get("periodoTrc003")) != 1){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        }else if(Integer.parseInt(discente.get("periodoTrc002")) != 2 && Integer.parseInt(discente.get("periodoTrc003")) != 2){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        }else if(Integer.parseInt(discente.get("periodoTrc002")) != 3 && Integer.parseInt(discente.get("periodoTrc003")) != 3){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                        }else{
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                        }  
                    } else if(Integer.parseInt(discente.get("anoTrc001")) == ano && Integer.parseInt(discente.get("anoTrc003")) == ano && Integer.parseInt(discente.get("anoTrc002")) != ano){
                        if(Integer.parseInt(discente.get("periodoTrc001")) != 1 && Integer.parseInt(discente.get("periodoTrc003")) != 1){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        }else if(Integer.parseInt(discente.get("periodoTrc001")) != 2 && Integer.parseInt(discente.get("periodoTrc003")) != 2){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        }else if(Integer.parseInt(discente.get("periodoTrc001")) != 3 && Integer.parseInt(discente.get("periodoTrc003")) != 3){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                        }else{
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                        }
                    }else if(Integer.parseInt(discente.get("anoTrc001")) == ano && Integer.parseInt(discente.get("anoTrc002")) != ano && Integer.parseInt(discente.get("anoTrc003")) != ano){
                        switch (Integer.parseInt(discente.get("periodoTrc001"))) {
                            case 1:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                break;
                            case 2:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                break;
                            case 3:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                                break;
                            default:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                                break;
                        }
                    }else if(Integer.parseInt(discente.get("anoTrc001")) != ano && Integer.parseInt(discente.get("anoTrc002")) == ano && Integer.parseInt(discente.get("anoTrc003")) != ano){
                        switch (Integer.parseInt(discente.get("periodoTrc002"))) {
                            case 1:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                break;
                            case 2:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                break;
                            case 3:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                                break;
                            default:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                                break;
                        }
                    }else if(Integer.parseInt(discente.get("anoTrc001")) != ano && Integer.parseInt(discente.get("anoTrc002")) != ano && Integer.parseInt(discente.get("anoTrc003")) == ano){
                        switch (Integer.parseInt(discente.get("periodoTrc003"))) {
                            case 1:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                break;
                            case 2:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                break;
                            case 3:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                                break;
                            default:
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                                linhaDoTempoDoDocente.append("  <td width=20 bgcolor=blue></td>\n");
                                break;
                        }
                    }else{
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                    }
                    if(ano < maiorAno)linhaDoTempoDoDocente.append("  <td width=20></td>\n");
                }else{
                    linhaDoTempoDoDocente.append("  <td width=20 bgcolor=white></td>\n");
                    linhaDoTempoDoDocente.append("  <td width=20 bgcolor=white></td>\n");
                    linhaDoTempoDoDocente.append("  <td width=20 bgcolor=white></td>\n");
                    if(ano < maiorAno)linhaDoTempoDoDocente.append("  <td width=20></td>\n");
                }    
            }
            linhaDoTempoDoDocente.append("\n</tr>\n");
        }
        
        linhaDoTempoDoDocente.append("\n</table>");
        
        return linhaDoTempoDoDocente.toString();
    }    
    
   
    
    public static void main(String[] args){
         System.out.println(construaLinhaDoTempoDoDocente("125896"));
        //System.out.println(construaLinhaDoTempoDoDocente("69051"));
//        construaLinhaDoTempoDoDocente("48437");
//        System.out.println(getLinhaDoTempoDoDocente());
      
        
    }
}
