/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import tela.TelaUsuarios;
import atualizaTabelas.AtualizaTabelaUsuario;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
/**
 *
 * @author marcelojtelles
 */
public class Funcoes {
    private ImageIcon imageIcon;
    public String buscaDataHora() throws Exception {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.okButtonText", "OK");
        
        String dataHora = "";
        try {
            Connection con;
            con = new Conexao().conectaBanco();
            Statement stmt = con.createStatement();
            String selecDataHora = "select now();";
            ResultSet rs = stmt.executeQuery(selecDataHora);
            while (rs.next()) {
                //System.out.println("campo: " + rs.getString("now()"));
                dataHora = rs.getString("now()");
            }
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel obter a data e hora do banco. [" + dataHora + "] . " + e.getMessage());
        } finally {
            return dataHora;
        }

    }
    public static String limpaApostrofo(String recebe) {
        String resposta = "";
        if (recebe != null) {
            resposta = recebe.replaceAll("'", "\\\\'");
        } else {
            resposta = "";
        }
        return (resposta);

    }
    
    public void reloadUsuarios(JInternalFrame f){
        //telaUsuarios.ini = 0;
        //telaUsuarios.fim = atualizaTabelas.atualizaTabelaUsuario.items;
        //telaUsuarios.iniInterno=0;
        TelaUsuarios.consulta="";
        TelaUsuarios.table = AtualizaTabelaUsuario.atualizaUsuarioPag(f,TelaUsuarios.ini,TelaUsuarios.fim,TelaUsuarios.consulta);     
        TelaUsuarios.jScrollPane1.setViewportView(TelaUsuarios.table);  
    }
    
    public byte[] imageToByte(String image) throws IOException {
        InputStream is = null;
        byte[] buffer = null;
        is = new FileInputStream(image);
        buffer = new byte[is.available()];
        is.read(buffer);
        is.close();
        return buffer;
    } 
    
    public byte[] imageToByte(Image image) {	
        BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(image, 0, 0, null);
        bg.dispose();

        ByteArrayOutputStream buff = new ByteArrayOutputStream();		
        try {  
            ImageIO.write(bi, "JPG", buff);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buff.toByteArray();		
    }
    
    public Image iconToImage(Icon icon) { 
          if (icon instanceof ImageIcon) { 
              return ((ImageIcon)icon).getImage(); 
          } else { 
              int w = icon.getIconWidth(); 
              int h = icon.getIconHeight(); 
              GraphicsEnvironment ge = 
              GraphicsEnvironment.getLocalGraphicsEnvironment(); 
              GraphicsDevice gd = ge.getDefaultScreenDevice(); 
              GraphicsConfiguration gc = gd.getDefaultConfiguration(); 
              BufferedImage image = gc.createCompatibleImage(w, h); 
              Graphics2D g = image.createGraphics(); 
              icon.paintIcon(null, g, 0, 0); 
              g.dispose(); 
              return image; 
          } 
      } 
    
    
     public boolean canReadFormat(String formatName) {
        Iterator iter = ImageIO.getImageReadersByFormatName(formatName);
        return iter.hasNext();
    } 

    public String getFormatName(Object o) {
        try {
          ImageInputStream iis = ImageIO.createImageInputStream(o);
          Iterator iter = ImageIO.getImageReaders(iis);
          if (!iter.hasNext()) {
            return null;
          }
          ImageReader reader = (ImageReader) iter.next();
          iis.close();

          return reader.getFormatName();
        } catch (IOException e) {
        }
        return null;
    }
    
    public String buscaData() throws Exception {
        String dataHora = "";
        try {
            Connection con;
            con = new Conexao().conectaBanco();
            Statement stmt = con.createStatement();
            String selecDataHora = "select date_format(now(),'%d/%m/%Y') as hora";
            ResultSet rs = stmt.executeQuery(selecDataHora);
            while (rs.next()) {
                //System.out.println("campo: " + rs.getString("now()"));
                dataHora = rs.getString("hora");
            }
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel obter a data e hora do banco. [" + dataHora + "] . " + e.getMessage());
        } finally {
            return dataHora;
        }

    }

}
