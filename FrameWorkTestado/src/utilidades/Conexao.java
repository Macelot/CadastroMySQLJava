/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author marcelojtelles
 */
public class Conexao {
    public static String sistemaIp, sistemaPorta, sistemaUser, sistemaSenha, sistemaRegistro;
    public static Connection con;
    
    public Connection conectaBanco()throws Exception{
        sistemaIp = "localhost";
        sistemaPorta = "3307";
        sistemaUser = "root";
        sistemaSenha = "usbw";
        try{
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:mysql://"+sistemaIp+":"+sistemaPorta+"/frameWork", sistemaUser, sistemaSenha);

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Erro na comunicação "+sistemaPorta +" "+sistemaUser+" "+ e.getMessage() );
                JOptionPane.showMessageDialog(null, "Saindo do sistema.");
                System.exit(0);
            }
        return con;
    }
    
    public synchronized void desconectar() {  
        if (con != null) {  
            try {  
                con.close();  
            } catch (SQLException ex) {  
                ex.printStackTrace();  
            }  
            con = null;  
        }  
    }  

}
