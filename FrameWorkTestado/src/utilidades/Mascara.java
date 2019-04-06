/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author marcelojosuetelles
 */
public class Mascara {

     public static MaskFormatter defineMascara(String Mascara){

        MaskFormatter F_Mascara = new MaskFormatter();
        try{
            F_Mascara.setMask(Mascara); //Atribui a mascara
            F_Mascara.setPlaceholderCharacter('_'); //Caracter para preencimento
        }
        catch (Exception excecao) {
            excecao.printStackTrace();
        }
        return F_Mascara;
    }

     public static String ValidaData(String recebe){

        String F_data = new String();
        String dia,mes,ano;
        if (recebe.equals("__/__/____")){
           F_data = "0000-00-00 00:00:00";
        }else{
            try{
               ano = recebe.substring(6,10);
               mes = recebe.substring(3,5);
               dia = recebe.substring(0,2);

               if ((Integer.parseInt(ano)<0) || (Integer.parseInt(dia)<0) || (Integer.parseInt(mes)<0) || (Integer.parseInt(mes)>13) || (Integer.parseInt(dia)>31)){
                   
                   return("Erro");
               }

               
               F_data = ano+"-"+mes+"-"+dia;
            }
            catch (Exception excecao) {
                excecao.printStackTrace();
            }
        }
        return F_data;
    }

}
