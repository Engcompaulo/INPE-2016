
package utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author abraao
 */
public class Teste {
          public static String obterDataInicial(){
                    String s = " new Date(2010, 0, 0)";
                    return s;
          }
          public static String obterDataFinal(){
                     String s = " new Date(2017, 0, 0)";
                    return s;
          }
          
          
          
          
          
          
          
          
          
          
          public static long obterDataInicialDeA(){
                    Calendar calendar  = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, 2010);
                    calendar.set(Calendar.MONTH, 6-1);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    long l = calendar.getTimeInMillis();
                    return l;
          }
          public static long obterDataFinalDeA(){
                    Calendar calendar  = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, 2010);
                    calendar.set(Calendar.MONTH, 8-1);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    long l = calendar.getTimeInMillis();
                    return l;
                    
          }
          public static String obterNomeDeA(){
                    return "JOANA";
          }
          public static String obterA(){
                    StringBuilder sb = new StringBuilder();
                    sb.append("{ ");
                    sb.append(" label: \"");
                    sb.append(obterNomeDeA()+"\", ");
                    sb.append(" times: [{\"color\":\"orange\", \"starting_time\":");
                    sb.append(obterDataInicialDeA());
                    sb.append(", \"ending_time\": ");
                    sb.append(obterDataFinalDeA());
                    sb.append("}]},");
                    return sb.toString();
          }
          
          
          
          
          
          
          
          public static long obterDataInicialDeB(){
                    Calendar calendar  = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, 2010);
                    calendar.set(Calendar.MONTH, 4-1);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    long l = calendar.getTimeInMillis();
                    return l;
          }
          public static long obterDataFinalDeB(){
                    Calendar calendar  = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, 2010);
                    calendar.set(Calendar.MONTH, 5-1);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    long l = calendar.getTimeInMillis();
                    return l;
                    
          }
          
          public static String obterNomeDeB(){
                    return "Celso";
          }
          
          public static String obterB(){
                    StringBuilder sb = new StringBuilder();
                    sb.append("{ ");
                    sb.append(" label: \"");
                    sb.append(obterNomeDeB()+"\", ");
                    sb.append(" times: [{\"color\":\"orange\", \"starting_time\":");
                    sb.append(obterDataInicialDeB());
                    sb.append(", \"ending_time\": ");
                    sb.append(obterDataFinalDeB());
                    sb.append("}]},");
                    return sb.toString();
          }
          
           public static void main(String[] args){
                    System.out.println(obterB());
           }
}
