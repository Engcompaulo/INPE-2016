package utils;

import java.text.ParseException;


public class Formatador {
    
    private Util util;
    
    public Formatador(){
        this.util = new Util();        
    }
    
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */

    /**Método para formatar o registro do aluno no formato XXX/YYYY onde YYYY é um ano
     * 
     * @params - Registro do Aluno 
     * @return String - Registro do Aluno formatado
     */
    public String formataRegistroAluno(String s){
        
        double asDouble = Double.parseDouble(s);        
        StringBuilder asStr = new StringBuilder(String.format("%11.0f", asDouble).trim());
        
        while(asStr.length() < 5) 
            asStr.insert(0,'0');
        
        asStr.insert(asStr.length()-4,"/");
        
        return asStr.toString();
    }
    
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */

    /**Método para formatar o registro do Docente
     * 
     * @params - Registro do Docente
     * @return String - Registro do Docente formatado
     */
    public String formataRegistroDocente(String s){
        if (s == null) 
            return "";
        if (s.length() == 0) 
            return "";
        
        double asDouble = Double.parseDouble(s);
        String asStr = String.format("%11.0f", asDouble).trim();
        
        return asStr;
    }
    
    

    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */

    /**Método para formatar as datas de admissão e conclusão dos discentes
     * O resultado será YYYY-PP -> a letra P corresponde ao período
     * 
     * @params - Data no formato MM/DD/YY HH:MM:SS
     * @return String - Data no formato YYYY-PP
     */
    public String formataData(String s) throws ParseException{                  
        if(s.length() > 0){             
            int ano = util.pegarAno(s);
            String mes = s.trim().substring(0, 2);
            
            if(mes.equals("01") || mes.equals("02") || mes.equals("03") || mes.equals("04"))
                return ano+"-1";
            else if(mes.equals("05") || mes.equals("06") || mes.equals("07") || mes.equals("08"))
                return ano+"-2";
            else if(mes.equals("09") || mes.equals("10") || mes.equals("11") || mes.equals("12"))
                return ano+"-3";
            else
                return null; 
            
        }else{
            return null; 
        }
    }
    
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    
    /**Método para formatar as datas de trancamentos dos discentes
     * Este método receberá um string com o formato YYYYPP e colocará um hífen para separar o ano do período.
     * 
     * @params s - String com formato YYYYPP
     * @return String - String com formato YYYY-PP
    */
    public String formataDataDeTrancamento(String s){
        double d = Double.parseDouble(s);
        StringBuilder sb = new StringBuilder(String.format("%11.0f", d).trim());
        return sb.insert(4, "-").toString();
    }
    
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */
    
    /**Método para formatar o CPF dos discentes
     * 
     * @params s - String CPF não formatado
     * @return String - String CPF formatado
    */
    public String formataCPF(String s){
        if(s.length() > 0){
            double d = Double.parseDouble(s);
            StringBuilder sb = new StringBuilder(String.format("%11.0f", d).trim());
            for(int i = sb.length(); i < 11; i++)
                sb.insert(0, "0");
            sb.insert(3, ".");
            sb.insert(7, ".");
            sb.insert(11, "-");
            return sb.toString();
        }else{ 
            return null; 
        }
    }
    
    
    
    
}
