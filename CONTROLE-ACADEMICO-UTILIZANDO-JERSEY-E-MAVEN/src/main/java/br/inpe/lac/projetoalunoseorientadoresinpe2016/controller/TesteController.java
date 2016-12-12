
package br.inpe.lac.projetoalunoseorientadoresinpe2016.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author abraao
 */
@Path("teste")
public class TesteController {
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String getMessage(@QueryParam("user") String user){
            /**
             * Configuramos em @QueryParam para o Jersey entender que o parâmetro está vido através de uma "QueryParam"
             */
            return "Deu Certo" + user;
        }
        
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("usuarios/{id}")
        public String getUser(@PathParam("id") long id){
            /**
             * PathParam recupera uma variável da url e converte
             */
            return "Recuperando usario com ID: "+id;
        }
}