package teste3;

import java.util.TreeSet;

/**
 *
 * @author abraao
 */
public class Main {
          public static void main(String[] args){
                    MetodosMongo metodosMongo = new MetodosMongo();
//                    TreeSet<String> cpfs = metodosMongo.getCPFs();
//                    for(String cpf : cpfs){
//                              System.out.println(cpf.toString());
//                    }
                    
                    TreeSet<String> CPFsComLattes = metodosMongo.getCPFsComLattesAssociado();
                    for(String cpf : CPFsComLattes){
                              System.out.println(cpf);
                    }
          }
          
}
