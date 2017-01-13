package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Util {
    
    /* *************************************************************************************
     **************************************************************************************
     ************************************************************************************* */

    /**Método para retornar o ano de uma determinada data
     * 
     * @params - Data no formato MM/DD/YY HH:MM:SS
     * @return String - Ano da data recebida como parâmetro
     * @throws ParseException 
     */
    public int pegarAno(String s) throws ParseException{        
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(3, 5)+"/");
        sb.append(s.substring(0, 2)+"/");
        sb.append(s.substring(6, 8));
        
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("dd/MM/yy");
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(sb.toString()));
        
        return calendar.get(Calendar.YEAR);
    }
    
}
