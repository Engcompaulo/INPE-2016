package br.inpe.lac;


import com.mongodb.Block;

import com.mongodb.MongoClient;

import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.HashSet;
import org.bson.Document;

public class Mongo {
    private static final MongoClientURI URI = new MongoClientURI("mongodb://abraao:angra201294@ds157677.mlab.com:57677/controle_academico");
    private static final MongoDatabase MY_DB = (new MongoClient(URI)).getDatabase(URI.getDatabase());
    private static final Document ordemCrescente = new Document("nome", 1);
    


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
        StringBuilder linhaDoTempoDoDocente = new StringBuilder();
        HashSet<Discente> discentes = new HashSet<>();
        ArrayList<Integer> anos = new ArrayList<>();
        ArrayList<Integer> listaAnos = new ArrayList<>();
        int menorAno = 9999;
        int maiorAno = 0;
        
        FindIterable<Document> iterable = MY_DB.getCollection("pessoas").find(Filters.eq("papel", "discente")).sort(ordemCrescente);
        iterable.forEach(new Block<Document>(){
            @Override
            public void apply(final Document document){
                if(document.get("orientador1") != null && document.getString("orientador1").equals(id) && document.getString("status").equalsIgnoreCase("Ativo") ){
                    String nome = document.getString("nome");
                    int anoAdmissao = Integer.parseInt(document.getString("data_admissao").substring(0, 4));
                    int anoConclusao = Integer.parseInt(document.getString("data_final").substring(0, 4));
                    int periodoConclusao = Integer.parseInt(document.getString("data_final").substring(5, 6));
                    
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
                    
                    if(anoTrc001 > 0 && periodoTrc001 > 0)
                         numeroTrancamentos++;
                    
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
//                    if(periodoConclusao == 1 && numeroTrancamentos == 3){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 2 && numeroTrancamentos == 2 ){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 2 && numeroTrancamentos == 3 ){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 3 && numeroTrancamentos == 1){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 3 && numeroTrancamentos == 2){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 3 && numeroTrancamentos == 3){
//                        anoConclusao++;
//                    }
                    

                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    anos.add(anoAdmissao);anos.add(anoConclusao);
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    Discente discente = new Discente();
                    discente.setNome(nome);
                    discente.setAnoAdmissao(anoAdmissao);
                    discente.setAnoConclusao(anoConclusao);
                    discente.setPeriodoConclusao(periodoConclusao);
                    discente.setAnoTrc001(anoTrc001);
                    discente.setPeriodoTrc001(periodoTrc001);
                    discente.setAnoTrc002(anoTrc002);
                    discente.setPeriodoTrc002(periodoTrc002);
                    discente.setAnoTrc003(anoTrc003);
                    discente.setPeriodoTrc003(periodoTrc003);
                    discentes.add(discente);
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                }else if (document.get("orientador2") != null && document.getString("orientador2").equals(id) && document.getString("status").equalsIgnoreCase("Ativo") ){
                    String nome = document.getString("nome");
                    int anoAdmissao = Integer.parseInt(document.getString("data_admissao").substring(0, 4));
                    int anoConclusao = Integer.parseInt(document.getString("data_final").substring(0, 4));
                    int periodoConclusao = Integer.parseInt(document.getString("data_final").substring(5, 6));
                    
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
                    
                    if(anoTrc001 > 0 && periodoTrc001 > 0)
                         numeroTrancamentos++;
                    
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
//                    if(periodoConclusao == 1 && numeroTrancamentos == 3){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 2 && numeroTrancamentos == 2 ){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 2 && numeroTrancamentos == 3 ){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 3 && numeroTrancamentos == 1){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 3 && numeroTrancamentos == 2){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 3 && numeroTrancamentos == 3){
//                        anoConclusao++;
//                    }
                    

                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    anos.add(anoAdmissao);anos.add(anoConclusao);
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    Discente discente = new Discente();
                    discente.setNome(nome);
                    discente.setAnoAdmissao(anoAdmissao);
                    discente.setAnoConclusao(anoConclusao);
                    discente.setPeriodoConclusao(periodoConclusao);
                    discente.setAnoTrc001(anoTrc001);
                    discente.setPeriodoTrc001(periodoTrc001);
                    discente.setAnoTrc002(anoTrc002);
                    discente.setPeriodoTrc002(periodoTrc002);
                    discente.setAnoTrc003(anoTrc003);
                    discente.setPeriodoTrc003(periodoTrc003);
                    discentes.add(discente);
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                }else if(document.get("orientador3") != null && document.getString("orientador3").equals(id) && document.getString("status").equalsIgnoreCase("Ativo") ){
                    String nome = document.getString("nome");
                    int anoAdmissao = Integer.parseInt(document.getString("data_admissao").substring(0, 4));
                    int anoConclusao = Integer.parseInt(document.getString("data_final").substring(0, 4));
                    int periodoConclusao = Integer.parseInt(document.getString("data_final").substring(5, 6));
                    
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
                    
                    if(anoTrc001 > 0 && periodoTrc001 > 0)
                         numeroTrancamentos++;
                    
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
//                    if(periodoConclusao == 1 && numeroTrancamentos == 3){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 2 && numeroTrancamentos == 2 ){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 2 && numeroTrancamentos == 3 ){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 3 && numeroTrancamentos == 1){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 3 && numeroTrancamentos == 2){
//                        anoConclusao++;
//                    }
//                    if(periodoConclusao == 3 && numeroTrancamentos == 3){
//                        anoConclusao++;
//                    }
                    

                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    anos.add(anoAdmissao);anos.add(anoConclusao);
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                    
                    Discente discente = new Discente();
                    discente.setNome(nome);
                    discente.setAnoAdmissao(anoAdmissao);
                    discente.setAnoConclusao(anoConclusao);
                    discente.setPeriodoConclusao(periodoConclusao);
                    discente.setAnoTrc001(anoTrc001);
                    discente.setPeriodoTrc001(periodoTrc001);
                    discente.setAnoTrc002(anoTrc002);
                    discente.setPeriodoTrc002(periodoTrc002);
                    discente.setAnoTrc003(anoTrc003);
                    discente.setPeriodoTrc003(periodoTrc003);
                    discentes.add(discente);
                    
                    /*
                     ****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                     *****************************************************************************************************************************************************
                    */
                }
            }
        });
        
        /*
         ****************************************************************************************************************************************************
         *****************************************************************************************************************************************************
         *****************************************************************************************************************************************************
        */

        for(int ano : anos){
            if(ano < menorAno)
                menorAno = ano;
            if(ano > maiorAno)
                maiorAno = ano;
        }        
        
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
        
        linhaDoTempoDoDocente.append("<table  border=\"0\" cellspacing=\"1\" cellpadding=\"0\" style=\"margin: 0 auto;\">\n");
        linhaDoTempoDoDocente.append("<tr>\n  <td></td>\n");
        for(int ano : listaAnos){
            linhaDoTempoDoDocente.append("  <td colspan=\"3\" align=middle>"+ano+"</td>\n");
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
        for(Discente discente : discentes){ 
            linhaDoTempoDoDocente.append("<tr>\n");
            linhaDoTempoDoDocente.append("  <td>"+discente.getNome()+"</td>\n");
            for(int ano : listaAnos){
                if(discente.getAnoAdmissao() <= ano && discente.getAnoConclusao() >= ano){
                    System.out.println(discente.getAnoAdmissao() + "  "+ano+"  "+discente.getAnoConclusao());
                    if(discente.getAnoTrc001() == ano && discente.getAnoTrc002() == ano && discente.getAnoTrc003() == ano){
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                        discente.setAnoTrc001(0); discente.setAnoTrc002(0); discente.setAnoTrc003(0);
                        if(ano < maiorAno)linhaDoTempoDoDocente.append("  <td width=20></td>\n");
                    }
                    if(discente.getAnoTrc001() == ano && discente.getAnoTrc002() == ano && discente.getAnoTrc003() != ano){
                        if(discente.getPeriodoTrc001() != 1 && discente.getPeriodoTrc002() != 1){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            discente.setAnoTrc001(0); discente.setAnoTrc002(0);
                        }else if(discente.getPeriodoTrc001() != 2 && discente.getPeriodoTrc002() != 2){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            discente.setAnoTrc001(0); discente.setAnoTrc002(0);
                        }else if(discente.getPeriodoTrc001() != 3 && discente.getPeriodoTrc002() != 3){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            discente.setAnoTrc001(0); discente.setAnoTrc002(0);
                        }
                        if(ano < maiorAno)linhaDoTempoDoDocente.append("  <td width=20></td>\n");
                    }
                    if(discente.getAnoTrc002() == ano && discente.getAnoTrc003() == ano && discente.getAnoTrc001() != ano){
                        if(discente.getPeriodoTrc002() != 1 && discente.getPeriodoTrc003() != 1){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            discente.setAnoTrc002(0); discente.setAnoTrc003(0);
                        }else if(discente.getPeriodoTrc002() != 2 && discente.getPeriodoTrc003() != 2){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            discente.setAnoTrc002(0); discente.setAnoTrc003(0);
                        }else if(discente.getPeriodoTrc002() != 3 && discente.getPeriodoTrc003() != 3){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            discente.setAnoTrc002(0); discente.setAnoTrc003(0);
                        }
                        if(ano < maiorAno)linhaDoTempoDoDocente.append("  <td width=20></td>\n");
                    }
                    if(discente.getAnoTrc001() == ano && discente.getAnoTrc002() != ano && discente.getAnoTrc003() != ano){
                        if(discente.getPeriodoTrc001() == 1){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            discente.setAnoTrc001(0);
                        }else if(discente.getPeriodoTrc001() == 2){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            discente.setAnoTrc001(0);
                        }else if(discente.getPeriodoTrc001() == 3){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            discente.setAnoTrc001(0);
                        }
                        if(ano < maiorAno)linhaDoTempoDoDocente.append("  <td width=20></td>\n");
                    }else if(discente.getAnoTrc002() == ano && discente.getAnoTrc001() != ano && discente.getAnoTrc003() != ano){
                        if(discente.getPeriodoTrc002() == 1){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            discente.setAnoTrc002(0);
                        }else if(discente.getPeriodoTrc002() == 2){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            discente.setAnoTrc002(0);
                        }else if(discente.getPeriodoTrc002() == 3){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            discente.setAnoTrc002(0);
                        }
                        if(ano < maiorAno)linhaDoTempoDoDocente.append("  <td width=20></td>\n");
                    }else if(discente.getAnoTrc003() == ano && discente.getAnoTrc001() != ano && discente.getAnoTrc002() != ano){
                        if(discente.getPeriodoTrc003() == 1){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            discente.setAnoTrc003(0);
                        }else if(discente.getPeriodoTrc003() == 2){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            discente.setAnoTrc003(0);
                        }else if(discente.getPeriodoTrc003() == 3){
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                            linhaDoTempoDoDocente.append("  <td width=20 bgcolor=#ff6565></td>\n");
                            discente.setAnoTrc003(0);
                        }
                        if(ano < maiorAno)linhaDoTempoDoDocente.append("  <td width=20></td>\n");
                    }else{
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                        linhaDoTempoDoDocente.append("  <td width=20 bgcolor=lightgreen></td>\n");
                        if(ano < maiorAno)linhaDoTempoDoDocente.append("  <td width=20></td>\n");
                    }
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
        /*
        ***************************************************************************************************************************
        ***************************************************************************************************************************
        ***************************************************************************************************************************
        */
         for(Discente d : discentes){
             System.out.println(d.getNome()+"  "+d.getAnoConclusao());
         }
        
       
        return linhaDoTempoDoDocente.toString();
    }    
    
   
    
    public static void main(String[] args){
  
        System.out.println(construaLinhaDoTempoDoDocente("69051"));
//        construaLinhaDoTempoDoDocente("48437");
//        System.out.println(getLinhaDoTempoDoDocente());
      
        
    }
}
