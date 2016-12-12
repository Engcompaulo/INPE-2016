package projetolattesinpe2016;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
/**
 *
 * @author henrique
 */
public class LattesInfo {
    private Document doc;
    
    public LattesInfo(File file) throws DocumentException{
	SAXReader reader = new SAXReader();
	doc = reader.read(file);
    }
    
    public String getPaisDeNascimento(){
	Node node = doc.selectSingleNode("//CURRICULO-VITAE/DADOS-GERAIS");
	String pais_de_nascimento = node.valueOf("@PAIS-DE-NASCIMENTO");
	return pais_de_nascimento.toUpperCase();
    }
    
    
    public Integer getUltimaAtualizacao(){
        Node node = doc.selectSingleNode("//CURRICULO-VITAE");
        String ultima_atualizacao = node.valueOf("@DATA-ATUALIZACAO");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = null;
        try{
            date = simpleDateFormat.parse(ultima_atualizacao);
        }catch(ParseException e){
            throw new IllegalArgumentException("O formato de data: "+ultima_atualizacao+" é inválido");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        
        return year;
    }

    public TreeMap<Integer, Integer> pegaMapaDeArtigosCompletos(){
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        List<Node> artigos = doc.selectNodes("//PRODUCAO-BIBLIOGRAFICA/ARTIGOS-PUBLICADOS/ARTIGO-PUBLICADO");
        for (Node artigo : artigos){
            Node dados_basicos_do_artigo = artigo.selectSingleNode("./DADOS-BASICOS-DO-ARTIGO");
            int ano = Integer.parseInt(dados_basicos_do_artigo.valueOf("@ANO-DO-ARTIGO"));
            String natureza = dados_basicos_do_artigo.valueOf("@NATUREZA");
            if (natureza.equalsIgnoreCase("COMPLETO")){
                int i = 0;
                if(map.containsKey(ano)){
                     i = map.get(ano);
                }
                map.put(ano, i + 1);
            }
        }
        return map; 
    }
    
    public Integer pegaTotalDeArtigosCompletos(){
        List<Node> artigos = doc.selectNodes("//PRODUCAO-BIBLIOGRAFICA/ARTIGOS-PUBLICADOS/ARTIGO-PUBLICADO");
        Integer total=0;
        for (Node artigo : artigos){
            Node dados_basicos_do_artigo = artigo.selectSingleNode("./DADOS-BASICOS-DO-ARTIGO");
            String natureza = dados_basicos_do_artigo.valueOf("@NATUREZA");
            if (natureza.equalsIgnoreCase("COMPLETO")){
                total++;
            }
        }
        return total; 
    }
    

   
    
}