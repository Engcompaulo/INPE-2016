package teste;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class TesteExtraiDadosAcademicosRafael {
          public static void main(String[] args) throws IOException{
                    
                    File fDisc = new File("Dados/GDRPESS.csv");
                    List<String> linesDisc = Files.readAllLines(fDisc.toPath());
                    /* TESTE 1
                    CSVReader reader = new CSVReader(new StringReader(linesDisc.get(4)));
                    String[] tokens = reader.readNext();
                    double d = Double.parseDouble(tokens[0]);
                    String s1 = String.format("%11.0f", d).trim();
                    StringBuilder sb = new StringBuilder(String.format("%11.0f", d).trim());
                    while(sb.length() < 5)sb.insert(0, '0');
                    sb.insert(sb.length()-4, "/");
                    System.out.println(tokens[0]);
                    System.out.println(d);
                    System.out.println(s1);
                    System.out.println("sb="+sb);
                    */
                    
                    /* TESTE 2
                    HashMap<String, HashMap<String,String>> discentes = new HashMap<String, HashMap<String,String>>();
                    HashMap<String,String> dadosJoao = new HashMap<String,String>();
                    dadosJoao.put("nome", "Joao");
                    dadosJoao.put("nivel", "Mestrado");
                    dadosJoao.put("curso", "redes");
                    System.out.println(dadosJoao.toString()); //{curso=redes, nome=Joao, nivel=Mestrado}
                    discentes.put("1111", dadosJoao); //{1111 = {curso=redes, nome=Joao, nivel=Mestrado}}
                    System.out.println(discentes.toString());
                    if(discentes.containsKey("1111")){
                              HashMap<String, String> dadosJoao2 = discentes.get("1111");
                              dadosJoao2.put("data_nascimento", "20/12/1994");
                              discentes.put("1111", dadosJoao2);
                    }
                    System.out.println(discentes.toString()); //{1111={data_nascimento=20/12/1994, curso=redes, nome=Joao, nivel=Mestrado}}
                    */
                    
//                    
//                    
//                    HashMap<String,String> dadosMaria = new HashMap<String,String>();
//                    dadosMaria.put("nome", "Maria");
//                    dadosMaria.put("nivel", "Doutorado");
//                    dadosMaria.put("curso", "banco de dados");
//                    discentes.put("2222", dadosMaria);
//                    
//                    HashMap<String, String> pessoaMaria = discentes.get("2222");
//                    pessoaMaria.put("nacionalidade", "cubana");
//                    discentes.put("2222", pessoaMaria);
//                    
                    
                    
                    
                    
                    
//                    TreeMap<String, String> treeMap = new TreeMap<String, String>();
//                    treeMap.put("juju", "fuba");
//                    treeMap.put("tutu", "tata");
//                    String pessoaDaArvore = treeMap.get("juju");
                    
//                    System.out.println(discentes.toString());
////                    System.out.println(treeMap.toString());
//                    System.out.println(pessoaMaria);



                    String data = "05/98";
                    int i = Integer.parseInt(data.substring(3, data.length()));
                    System.out.println(i);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH, 0);
                    calendar.set(Calendar.MONTH, 0);
                    calendar.set(Calendar.YEAR, i);
                    int ano = calendar.get(Calendar.YEAR);
                    System.out.println(ano);
          }
}
