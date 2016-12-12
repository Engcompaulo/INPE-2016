
package br.inpe.lac.projetoalunoseorientadoresinpe2016;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;



/**
 *
 * @author abraao
 */

/**
 * Use the javax.ws.rs.ApplicationPath annotation to defined the base URI pattern that gets mapped to the servlet
 * Está classe faz o start das classes do Jersey
 */
@ApplicationPath("rest") //caiminho para o atendimento das requisições
public class MyApp extends ResourceConfig{
        /**
         * Abstract Class Application
        public class MyApplication extends Application {
                    @Override
                    public Set<Class<?>> getClasses() {
                              Set<Class<?>> s = new HashSet<Class<?>>();
                               s.add(HelloWorldResource.class);
                               return s;
                     }
        }
        Alternatively it is possible to reuse ResourceConfig - Jersey's own implementation of Application class. This class can either be directly instantiated and then configured or it can 
        be extended and the configuration into the constructor of the extending class. Compared to Application, the ResourceConfig provides advanced capabilities to simplify 
        registration of JAX-RS components, such as scanning for root resource and provider classes in a provided classpath 
        public class MyApplication extends ResourceConfig {
                public MyApplication() {
                        packages("org.foo.rest;org.bar.rest");
                }
           }
         
         No primeiro modelo colocamos as classes que são inciadas junto com o projeto dentro de um set então nao vai ser necessário escanear dentro de um pacote para 
         encontrar essas classes.
         */
        public MyApp(){
            packages("br.inpe.lac.projetoinpe2016.controller");
        }
}