package extraidadosacademicos;

// Mesma coisa para discentes. Os campos são: 
// REG_ALUNO,NOME,NIVEL,NASCIMENTO,CIDADE, 
// ESTADO,NACIONALIDADE,PAIS,SEXO,EST_CIVIL, 
// DEPENDENTE,CIC,RG,ORGAO,EMISSAO, 
// VINCEMP,SALARIO,RECUR_PROP,REGIME,CURSO, 
// FUNCIONARIO

public class Discente{
    private String id = null;
    private String nome = null;
    private String cpf = null;
    private String nivel = null;
    private String status = null;
    private String sigla_curso = null;
    private String data_admissao = null;
    private String data_final = null;
    private String orientador1 = null;
    private String orientador2 = null;
    private String orientador3 = null;
    private String orientador4 = null;
    private String trc001 = null;
    private String trc002 = null;
    private String trc003 = null;
    private final String papel = "discente";

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSigla_curso() {
        return sigla_curso;
    }

    public void setSigla_curso(String sigla_curso) {
        this.sigla_curso = sigla_curso;
    }

    public String getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(String data_admissao) {
        this.data_admissao = data_admissao;
    }

    public String getData_final() {
        return data_final;
    }

    public void setData_final(String data_final) {
        this.data_final = data_final;
    }

        public String getOrientador1() {
        return orientador1;
    }

    public void setOrientador1(String orientador1) {
        this.orientador1 = orientador1;
    }

    public String getOrientador2() {
        return orientador2;
    }

    public void setOrientador2(String orientador2) {
        this.orientador2 = orientador2;
    }

    public String getOrientador3() {
        return orientador3;
    }

    public void setOrientador3(String orientador3) {
        this.orientador3 = orientador3;
    }

    public String getOrientador4() {
        return orientador4;
    }

    public void setOrientador4(String orientador4) {
        this.orientador4 = orientador4;
    }
    
    public String getTrc001(){
        return this.trc001;
    }
    
    public void setTrc001(String trc001){
        this.trc001 = trc001;
    }
    
    public String getTrc002(){
        return this.trc002;
    }
    
    public void setTrc002(String trc002){
        this.trc002 = trc002;
    }
    
    public String getTrc003(){
        return this.trc003;
    }
    
    public void setTrc003(String trc003){
        this.trc003 = trc003;
    }
}
