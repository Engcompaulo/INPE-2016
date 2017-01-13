package main;

import extraidadosacademicos.ExtraiDadosAcademicos;
import mongo.Mongo;



public class Main {
    
    public static void main(String[] args){           
        ExtraiDadosAcademicos extrator = new ExtraiDadosAcademicos();
        Mongo mongo = new Mongo();
        // HashMap<String, Discente> discentes = extrator.getDiscentes();
        

        //extrator.mostraDiscentes();
        //extrator.mostraDocentes();
        
        mongo.eliminaDocentesSemOrientação();
        mongo.armazenaTodosDiscentes2();
        mongo.armazenaTodosDocentes();
        
       
                
    }
    
}
