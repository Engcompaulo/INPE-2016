package extraidadosacademicos;

//package extraidadosacademicos;
//
//import com.opencsv.CSVReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.StringReader;
//import java.nio.file.Files;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//public class ExtraiDadosAcademicos{
//
//          public static void main(String[] args) throws IOException, ParseException {
//                    // Lemos a tabela de docentes. Os campos são:
//                    //  0 REG_DOCENT,NOME_DOCENTE,TITULO,SIGLA_INST,ENDERECO,
//                    //  5 TELEFONE,VISITANTE,E-MAIL,OBSERVAÇÃO
//                    Docentes docentes = new Docentes();
//                    File fDoc = new File("Dados/DOCENTE.csv");
//                    List<String> linesDoc = Files.readAllLines(fDoc.toPath()); 
//                    // Pulamos cabeçalho
//                    linesDoc.remove(0);
//                    for (String line : linesDoc){
//                              // Recupero código e nome, reprocesso código.
//                              CSVReader reader = new CSVReader(new StringReader(line));
//                              String[] tokens = reader.readNext();
//                              docentes.add(formataRegistroDocente(tokens[0]),tokens[1]);
//                    }
//                    
//                    
//                    //***********************SEGUNDA PARTE********************************************
//                         
//                    // Mesma coisa para discentes. Os campos são:
//                    //  0 REG_ALUNO,NOME,NIVEL,NASCIMENTO,CIDADE,
//                    //  5 ESTADO,NACIONALIDADE,PAIS,SEXO,EST_CIVIL,
//                    // 10 DEPENDENTE,CIC,RG,ORGAO,EMISSAO,
//                    // 15 VINCEMP,SALARIO,RECUR_PROP,REGIME,CURSO,
//                    // 20 FUNCIONARIO
//                    
//                    Discente discentes = new Discente();
//                    File fDisc = new File("Dados/GDRPESS.csv");
//                    List<String> linesDisc= Files.readAllLines(fDisc.toPath()); 
//                    // Pulamos cabeçalho
//                    linesDisc.remove(0);
//                    for (String line : linesDisc){
//                              // Recupero campos, reprocesso código. 
//                              CSVReader reader = new CSVReader(new StringReader(line));
//                              String[] tokens = reader.readNext();
//                              String id = formataRegistroAluno(tokens[0]);
//                              String nome = tokens[1];
//                              String nível = tokens[2];
//                              String rg = tokens[12];
//                              // Só queremos os regulares!
//                              if (nível.equals("Mestrado") || nível.equals("Doutorado") ){
//                                        if(curso.equals("CAP"))discentes.put(id,nome,nível,curso);
//                              }
//                    }
//                    /*System.out.println(docentes.getSize()+" docentes e "+discentes.getSize()+" discentes");
//                    System.out.println(discentes.getDadosPeloID("21172/1996"));
//                    System.out.println(discentes.getDiscentes().toString());*/
//                    
//                    
//                    //***********************TERCEIRA PARTE********************************************
//                    
//                    // Agora começa a complicar. Os dados cadastrais dos alunos foram lidos, mas
//                    // os dados acadêmicos estão em outro arquivo.
//                    // Os campos são (alguns repetidos de GDRPESS!):
//                    //  0 REG_ALUNO,SIGLA_CURS,STATUS,D_ADIMISSA,D_FINAL,
//                    //  5 D_TRANCA,D_PRELIMI,D_RETORNO,D_PROPOSTA,D_INTEGRADO,
//                        // 10 D_QUALIFICAÇÃO,D_SEMINÁRIO,D_SITUÇÃO,TOTCREDI,CONCGLOB,
//                    // 15 NOTAGLOB,COD_BANCA,NIVEL,TITUTESE,APROVADO,
//                    // 20 O_ACADEM,O_PESQ1,O_PESQ2,O_PESQ3,O_PESQ4,
//                    // 25 Observação
//                    File CURSO_AL = new File("Dados/CURSO_AL.csv");
//                    List<String> linesCur= Files.readAllLines(CURSO_AL.toPath()); 
//                    // Pulamos cabeçalho
//                    linesCur.remove(0);
//                     for (String line : linesCur){
//                              // Recupero campos, reprocesso código.
//                              CSVReader reader = new CSVReader(new StringReader(line));
//                              String[] tokens = reader.readNext();
//                              String id = formataRegistroAluno(tokens[0]);
//                              String sigla_curso = tokens[1];
//                              String status = tokens[2];
//                              //Formatamos a data
//                              String admissão = tokens[3];
//                              StringBuilder dataAdmissao = new StringBuilder();
//                              dataAdmissao.append(admissão.substring(3, 5)+"/");
//                              dataAdmissao.append(admissão.substring(0, 2)+"/");
//                              dataAdmissao.append(admissão.substring(6, 8));
//                              SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("dd/MM/yy");
    //                              Calendar calendar  = Calendar.getInstance();
    //                              calendar.setTime(simpleDateFormat.parse(dataAdmissao.toString()));
//                              // Quero adicionar estes campos ao registro do aluno.
//                              /*System.out.println("Procurando adicionar dados para id "+id);*/
//                              //Removemos alunos da CAP que começaram antes de 2000
//                              if(sigla_curso.equals("CAP")){
//                                        if(calendar.get(Calendar.YEAR) >= 2000){
//                                                  discentes.addKey(id,"Status",status);
//                                                  discentes.addKey(id,"Data_Admissão",simpleDateFormat.format(calendar.getTime()));
//                                        }else{
//                                                  discentes.removeKey(id);
//                                        }
//                              }
//                     }
//                     //Verificamos o resultado
//                     System.out.println(discentes.getDiscentes().toString());
////                    System.out.println(discentes.getDadosPeloID("21172/1996"));
//    
//                /* Parei aqui. O que falta fazer:
//                 * Simplificar e considerar somente alunos da CAP que entraram de 2000
//                   em diante? 
//                 * Verificar que outros campos temos em CURSO_AL que devemos armazenar 
//                   na coleção de alunos. O_PESQ{1,2,3,4} são necessários, mas pode ser 
//                   que estejam incompletos em relação à lista de docentes.
//                *  Como queremos marcar quem entrou e saiu do
//                   programa em qualquer situação acho que temos que ver para os outros 
//                   STATUS quais as datas relevantes. Por exemplo, se STATUS = Desligado,
//                   guardamos D_SITUÇÃO. Vi que D_SITUÇÃO parece ser a data do último
//                   registro da situação do aluno, pode ser que sirva para outras finalidades.
//                 * Temos registros incompletos, por exemplo, o aluno 20966/1996 foi desligado
//                   mas a data não aparece, e a D_SITUÇÃO dele é null. Muitos casos onde não
//                   temos data que diga qual é a situação final ou atual!!
//                 * Queremos usar estes dados para montar barrinhas que indiquem o período
//                   em que o aluno esteve no programa. Para alunos com Status = Ativo, a data 
//                   inicial é D_ADIMISSA e a final, como não existe, deve ser a de agora?
//                   Para Status Concluído: D_FINAL
//                   Para Status Trancado: não adicionar aos dados.
//                   Para Status Tranferido: não adicionar aos dados.
//                   Para Status Desligado: não adicionar aos dados.
//                   Para Status Desistiu: não adicionar aos dados.
//                   Que outros Status existem? Falecidos devem ser desconsiderados na análise. 
//                 * Depois de verificada a data de entrada e saída, repetir trecho de código
//                   e modificar classe Discente para registrar os trancamentos. 
//                   Estes estão no arquivo HISTORIC.csv, basta recuperar pelo código do aluno
//                   e ver quantas disciplinas ele fez com nomes TRC001, TRC002 e TRC003: cada um 
//                   corresponde a um período de trancamento.
//                 * A parte mais complicada é montar o gráfico!
//                 */
//    }
//
//  
//  // Converte um double em uma String para algo como XXX/YYYY onde YYYY é um ano
//  public static String formataRegistroAluno(String s){
//          double asDouble = Double.parseDouble(s);
//          StringBuilder asStr = new StringBuilder(String.format("%11.0f", asDouble).trim());
//          while(asStr.length() < 5) asStr.insert(0,'0');
//          asStr.insert(asStr.length()-4,"/");
//          return asStr.toString();
//}
//  
//          // Converte um double em uma String para algo como XXXXXX
//          public static String formataRegistroDocente(String s){
//                    if (s == null) return "";
//                    if (s.length() == 0) return "";
//                    double asDouble = Double.parseDouble(s);
//                    String asStr = String.format("%11.0f", asDouble).trim();
//                    return asStr;
//          }
//  
//  }
