package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import extraidadosacademicos.Discente;
import extraidadosacademicos.*;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;
import java.util.List;

public class Mongo {
    //Conexão com uma base de dados online 
    private final MongoClientURI URI = new MongoClientURI("mongodb://abraao:angra201294@ds157677.mlab.com:57677/controle_academico");
    private final DB MY_DB = (new MongoClient(URI)).getDB(URI.getDatabase());
    private final DBCollection PESSOAS = MY_DB.getCollection("pessoas");
    
    //Conexão com uma base de dados Local
    //private final DB BANCO_LOCAL = (new MongoClient("localhost", 27017)).getDB("controle_academico");
    //private final DBCollection PESSOAS_LOCAL = BANCO_LOCAL.getCollection("pessoas");
    
    private  Collection<Discente> discentes = (new ExtraiDadosAcademicos()).getDiscentes().values();
    private  Collection<Docente> docentes = (new ExtraiDadosAcademicos()).getDocentes().values();
    
    /*****************************************************************************************************************/
    /*****************************************************************************************************************/
    /*****************************************************************************************************************/
    
    /** 
    * Este método elimina todos os docentes que não estão orientando nenhum discente.
    * @
    */
    public void eliminaDocentesSemOrientação(){
        List<String> lista = new ArrayList<String>();
        
        for(Discente discente : discentes){
            if(discente.getStatus().equalsIgnoreCase("Ativo")){
                lista.add(discente.getOrientador1());
                lista.add(discente.getOrientador2());
                lista.add(discente.getOrientador3());
                lista.add(discente.getOrientador4());
            }
        }
        
        for(Iterator<Docente> iterator = docentes.iterator(); iterator.hasNext();){
            Docente docente = iterator.next();
            if(!lista.contains(docente.getId())){
                iterator.remove();
            }
        }    
    }
    

    
    /*****************************************************************************************************************/
    /*****************************************************************************************************************/
    /*****************************************************************************************************************/
    
    /** 
    * Este método vai armazenar cada discente na coleção chamada PESSOAS
    * Aqui guardamos os orientadores e as datas de trancamentos como uma lista de arrays na base de dados
    * @
    */
    public void armazenaTodosDiscentes(){            
        for(Discente discente : discentes){
            if(discente.getData_admissao() != null){
                BasicDBObject basicDBObject = new BasicDBObject();
                List<BasicDBObject> orientadores = new ArrayList<>();
                List<BasicDBObject> trancamentos = new ArrayList<>();
                basicDBObject.put("_id", discente.getId());
                basicDBObject.put("cpf", discente.getCpf());
                basicDBObject.put("nome", discente.getNome());
                basicDBObject.put("papel", "discente");
                basicDBObject.put("nivel", discente.getNivel());
                basicDBObject.put("status", discente.getStatus());
                basicDBObject.put("sigla_curso", discente.getSigla_curso());
                basicDBObject.put("data_admissao", discente.getData_admissao());
                if(discente.getData_final() == null){
                    if(discente.getNivel().equalsIgnoreCase("Mestrado")){
                        int ano = Integer.parseInt(discente.getData_admissao().substring(0, 4));
                        ano += 3;
                        String data_final = ano+"-"+discente.getData_admissao().substring(5, discente.getData_admissao().length());
                        discente.setData_final(data_final);
                    }
                    if(discente.getNivel().equalsIgnoreCase("Doutorado")){
                        int ano = Integer.parseInt(discente.getData_admissao().substring(0, 4));
                        ano += 5;
                        String data_final = ano+"-"+discente.getData_admissao().substring(5, discente.getData_admissao().length());
                        discente.setData_final(data_final);
                    } 
                }
                basicDBObject.put("data_final", discente.getData_final());
                orientadores.add(new BasicDBObject("orientador1", discente.getOrientador1()));
                orientadores.add(new BasicDBObject("orientador2", discente.getOrientador2()));
                orientadores.add(new BasicDBObject("orientador3", discente.getOrientador3()));
                basicDBObject.put("orientadores", orientadores);
                trancamentos.add(new BasicDBObject("trc001", discente.getTrc001()));
                trancamentos.add(new BasicDBObject("trc002", discente.getTrc002()));
                trancamentos.add(new BasicDBObject("trc003", discente.getTrc003()));
                basicDBObject.put("trancamentos", trancamentos);
                //PESSOAS.insert(basicDBObject);
                //PESSOAS_LOCAL.insert(basicDBObject);
            }
        }
    }
    
    /*****************************************************************************************************************/
    /*****************************************************************************************************************/
    /*****************************************************************************************************************/
    
    
    
    /**
    * Este método vai armazenar cada discente na coleção chamada PESSOAS
    * Aqui não usamos arrays nem listas para armazenar os orientadores e as datas de trancamentos na base de dados
    * @
    */
    
    public void armazenaTodosDiscentes2(){            
        for(Discente discente : discentes){
            if(discente.getData_admissao() != null){
                BasicDBObject basicDBObject = new BasicDBObject();
                basicDBObject.put("_id", discente.getId());
                basicDBObject.put("cpf", discente.getCpf());
                basicDBObject.put("nome", discente.getNome());
                basicDBObject.put("papel", "discente");
                basicDBObject.put("nivel", discente.getNivel());
                basicDBObject.put("status", discente.getStatus());
                basicDBObject.put("sigla_curso", discente.getSigla_curso());
                basicDBObject.put("data_admissao", discente.getData_admissao());
                if(discente.getData_final() == null){
                    if(discente.getNivel().equalsIgnoreCase("Mestrado")){
                        int ano = Integer.parseInt(discente.getData_admissao().substring(0, 4));
                        ano += 3;
                        String data_final = ano+"-"+discente.getData_admissao().substring(5, discente.getData_admissao().length());
                        discente.setData_final(data_final);
                    }
                    if(discente.getNivel().equalsIgnoreCase("Doutorado")){
                        int ano = Integer.parseInt(discente.getData_admissao().substring(0, 4));
                        ano += 5;
                        String data_final = ano+"-"+discente.getData_admissao().substring(5, discente.getData_admissao().length());
                        discente.setData_final(data_final);
                    } 
                }
                basicDBObject.put("data_final", discente.getData_final());
                basicDBObject.put("orientador1", discente.getOrientador1());
                basicDBObject.put("orientador2", discente.getOrientador2());
                basicDBObject.put("orientador3", discente.getOrientador3());
                basicDBObject.put("orientador4", discente.getOrientador4());
                basicDBObject.put("trc001", discente.getTrc001());
                basicDBObject.put("trc002", discente.getTrc002());
                basicDBObject.put("trc003", discente.getTrc003());
                PESSOAS.insert(basicDBObject);
                //PESSOAS_LOCAL.insert(basicDBObject);
            }
        }
    }
    
    /*****************************************************************************************************************/
    /*****************************************************************************************************************/
    /*****************************************************************************************************************/
    
    
    /**
    * Este método vai armazenar cada docente na coleção chamada PESSOAS
    * @
    */
    public void armazenaTodosDocentes(){
        for(Docente docente : docentes){
            if(!docente.getId().isEmpty()){
                BasicDBObject basicDBObject = new BasicDBObject();
                basicDBObject.put("_id", docente.getId());
                basicDBObject.put("nome", docente.getNome());
                basicDBObject.put("papel", docente.getPapel());
                basicDBObject.put("titulo", docente.getTitulo());
                basicDBObject.put("sigla_inst", docente.getSigla_inst());
                PESSOAS.insert(basicDBObject);
                //PESSOAS_LOCAL.insert(basicDBObject);
            }
        }
    }

}
