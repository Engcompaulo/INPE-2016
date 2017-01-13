package extraidadosacademicos;

import utils.Util;
import utils.Formatador;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;



public class ExtraiDadosAcademicos {
    
    private HashMap<String, Discente> discentes;
    private HashMap<String, Docente> docentes;
    private Formatador formatador;
    private Util util;
    
    public ExtraiDadosAcademicos(){   
        this.discentes = new HashMap<String, Discente>();
        this.docentes = new HashMap<String, Docente>();
        this.formatador = new Formatador();
        this.util = new Util();
        
        try {        
            this.extraiDadosDosDiscentesDaPlanilhaGDRPESS();
            this.extraiDadosDosDiscentesDaPlanilhaCURSO_AL(); 
            this.extraiDadosDosDiscentesDaPlanilhaHISTORIC();
            this.extraiDadosDaPlanilhaDocente();
        } catch (IOException ex) {
            System.out.println("Erro de I/O");
        } catch (ParseException ex) {
            System.out.println("Erro de parse");
        }    
        
    }

    public HashMap<String, Discente> getDiscentes() {
        return discentes;
    }

    public HashMap<String, Docente> getDocentes(){
        return docentes;
    }

    

    /* *************************************************************************************
    **************************************************************************************
    ************************************************************************************* */

    /**Método para extrair registro, nome, nível e rg dos discentes cujo nível é "Mestrado" ou "Doutorado"
    * 
    * @throws IOException 
    */
    private void extraiDadosDosDiscentesDaPlanilhaGDRPESS() throws IOException{
        //Começaremos extraindo os dados dos discentes
        File fDisc = new File("Dados/GDRPESS.csv");
        List<String> linesDisc= Files.readAllLines(fDisc.toPath()); 
        // Pulamos cabeçalho
        linesDisc.remove(0);
        
        for(String line : linesDisc){            
            CSVReader reader = new CSVReader(new StringReader(line));
            
            String[] tokens = reader.readNext();
            String id = formatador.formataRegistroAluno(tokens[0]);
            String nome = tokens[1];
            String nível = tokens[2];
            String cpf = formatador.formataCPF(tokens[11]);
            String sigla_curso = tokens[19];
            
            if (nível.equals("Mestrado") || nível.equals("Doutorado") ){
                Discente discente = new Discente();
                discente.setId(id);
                discente.setNome(nome);
                discente.setNivel(nível);
                discente.setCpf(cpf);
                discente.setSigla_curso(sigla_curso);
                
                discentes.put(id, discente);
            }
            
        }
    }
    
    /* *************************************************************************************
    **************************************************************************************
    ************************************************************************************* */

    /**Método para extrair sigla do curso, status do curso, data de admissão, data final e 
     * orientadores dos discentes que começaram o curso a partir de 2000, estão ativos ou com status
     * concluído e que são da CAP
     * 
     * @throws IOException
     * @throws ParseException 
     */
    private void extraiDadosDosDiscentesDaPlanilhaCURSO_AL() throws IOException, ParseException{
        //Agora vamos extrair outras informações dos discentes que estão na planilha CURSO_AL.csv
        File CURSO_AL = new File("Dados/CURSO_AL.csv");
        List<String> linesCur= Files.readAllLines(CURSO_AL.toPath()); 
        //Pulamos cabeçalho
        linesCur.remove(0);
        
        for (String line : linesCur){
            CSVReader reader = new CSVReader(new StringReader(line));
            String[] tokens = reader.readNext();
            String id = formatador.formataRegistroAluno(tokens[0]);
            String sigla_curso = tokens[1];
            String status = tokens[2];
            String dataAdmissao = tokens[3];
            String dataFinal = formatador.formataData(tokens[4]);
            String orientador1 = formatador.formataRegistroDocente(tokens[21]);
            String orientador2 = formatador.formataRegistroDocente(tokens[22]);
            String orientador3 = formatador.formataRegistroDocente(tokens[23]);
            String orientador4 = formatador.formataRegistroDocente(tokens[24]);
            
            if(discentes.containsKey(id)){
                int ano = util.pegarAno(dataAdmissao); 
                
                if(sigla_curso.equals("CAP") && ano >= 2000){
                    if(status.equalsIgnoreCase("Ativo") || status.equalsIgnoreCase("Concluído")){
                        Discente discente = discentes.get(id);
                        
                        discente.setSigla_curso(sigla_curso);
                        discente.setStatus(status);
                        discente.setData_admissao(formatador.formataData(dataAdmissao));
                        discente.setData_final(dataFinal);
                        if(!orientador1.equals("0"))
                            discente.setOrientador1(orientador1);
                        if(!orientador2.equals("0"))
                            discente.setOrientador2(orientador2);
                        if(!orientador3.equals("0"))
                            discente.setOrientador3(orientador3);
                        if(!orientador4.equals("0"))
                            discente.setOrientador4(orientador4);
                    }else{ 
                        discentes.remove(id); 
                    }
                }else{
                    discentes.remove(id); 
                }
                
            }
        }

        //Vamos fazer outro tratamento no hashset discentes para retirar aqueles que não são da CAP
        Collection<Discente> colecaoDiscentes = discentes.values();
        
        for(Iterator<Discente> iterator = colecaoDiscentes.iterator(); iterator.hasNext();){
            Discente discente = iterator.next();
            if(!discente.getSigla_curso().equalsIgnoreCase("CAP")) 
                iterator.remove();
        }
        
    }
    
    
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    
    /**Método para extrair todos os períodos em que o discente trancou o curso
     * Este método encontrará estas informações na planilha HISTORIC.csv
     * 
     * @throws IOException 
     */
    private void extraiDadosDosDiscentesDaPlanilhaHISTORIC() throws IOException{
        File HISTORIC_CSV = new File("Dados/HISTORIC.csv");
        List<String> linesHistoric = Files.readAllLines(HISTORIC_CSV.toPath());
        // Pulamos cabeçalho
        linesHistoric.remove(0);
        
        for(String line : linesHistoric){            
            CSVReader reader = new CSVReader(new StringReader(line));
            
            String[] tokens = reader.readNext();
            String id  = formatador.formataRegistroAluno(tokens[0]);
            String sigla_mat = tokens[2];
            String periodo = formatador.formataDataDeTrancamento(tokens[3]);
            if(discentes.containsKey(id)){
                Discente discente = discentes.get(id);
                if(sigla_mat.equalsIgnoreCase("TRC001")){  
                    discente.setTrc001(periodo);
                }else if(sigla_mat.equalsIgnoreCase("TRC002")){
                    discente.setTrc002(periodo);    
                }else if(sigla_mat.equalsIgnoreCase("TRC003")){
                    discente.setTrc003(periodo);    
                }
           
            }
            
        }
        
    }
    
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    
    /**Método para extrair registro, nome, título e sigla da instituição dos docentes
     * a partir da planilha DOCENTE.csv
     * 
     * @throws IOException 
     */
    private void extraiDadosDaPlanilhaDocente() throws IOException{
        File DOCENTE_CSV = new File("Dados/DOCENTE.csv");
        List<String> linesDocente = Files.readAllLines(DOCENTE_CSV.toPath());
        //pulamos cabeçalho
        linesDocente.remove(0);
        for(String line : linesDocente){
            CSVReader reader = new CSVReader(new StringReader(line));
            String[] tokens = reader.readNext();
            String id = formatador.formataRegistroDocente(tokens[0]);
            String nome = tokens[1];
            String titulo = tokens[2];
            String sigla_inst = tokens[3];
            
            Docente docente = new Docente();
            if(!id.isEmpty())
                docente.setId(id);
            if(!nome.isEmpty())
                docente.setNome(nome);
            if(!titulo.isEmpty())
                docente.setTitulo(titulo);
            if(!sigla_inst.isEmpty())
                docente.setSigla_inst(sigla_inst);
            
            docentes.put(id, docente);
        }
    }
   
    
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    
    public void mostraDiscentes(){
        Collection<Discente> todosDiscentes = discentes.values();
        
        for(Discente discente : todosDiscentes){
            System.out.println(discente.getId());
            System.out.println(discente.getNome());
            System.out.println("CPF: "+discente.getCpf());
            System.out.println(discente.getSigla_curso());
            System.out.println(discente.getNivel());
            System.out.println(discente.getStatus());
            System.out.println("Data Admissao: "+discente.getData_admissao());
            System.out.println("Data Final: "+discente.getData_final());
            System.out.println("Orientador1: "+discente.getOrientador1());
            System.out.println("Orientador2: "+discente.getOrientador2());
            System.out.println("Orientador3: "+discente.getOrientador3());
            System.out.println("Orientador4: "+discente.getOrientador4());
            System.out.println("TRC001: "+discente.getTrc001());
            System.out.println("TRC002: "+discente.getTrc002());
            System.out.println("TRC003: "+discente.getTrc003()+"\n\n");
        }
        
        System.out.println(todosDiscentes.size());
    }
    
  
    public void mostraDocentes(){
        Collection<Docente> todosDocentes = docentes.values();
        
        for(Docente docente : todosDocentes){
            System.out.println(docente.getId());
            System.out.println(docente.getNome());
            System.out.println(docente.getTitulo());
            System.out.println(docente.getSigla_inst());
            System.out.println(docente.getPapel()+"\n\n");
        }
        System.out.println(todosDocentes.size());
    }
}
