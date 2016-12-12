
package teste1;

/**
 *
 * @author abraao
 */
public class User {
        private int id;
        private String nome;
        private boolean isEmployee;
        
        public int getId(){
            return id;
        }
        public void setId(int id){
            this.id = id;
        }
        public String getNome(){
            return nome;
        }
        public void setNome(String nome){
            this.nome = nome;
        }
        public boolean getIsEmployee(){
            return isEmployee;
        }
        public void setIsEmployee(boolean  isEmployee){
            this.isEmployee = isEmployee;
        }
}
