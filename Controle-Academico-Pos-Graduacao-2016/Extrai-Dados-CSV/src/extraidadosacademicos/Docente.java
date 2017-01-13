package extraidadosacademicos;

import java.util.HashMap;

// REG_DOCENT,NOME_DOCENTE,TITULO,SIGLA_INST,ENDERECO, 
// TELEFONE,VISITANTE,E-MAIL,OBSERVAÇÃO

public class Docente{
    
    private String id = null;
    private String nome = null;
    private String titulo = null;
    private String sigla_inst = null;
    private String papel = "docente";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSigla_inst() {
        return sigla_inst;
    }

    public void setSigla_inst(String sigla_inst) {
        this.sigla_inst = sigla_inst;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
    
   

}
