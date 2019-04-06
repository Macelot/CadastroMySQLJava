/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ControleUsuario;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import utilidades.Conexao;

/**
 *
 * @author marcelojtelles
 */
public class ModeloUsuario {
    Integer id;
    String nomeCompleto;
    String usuario;
    String senha;
    String versao;
    String ativo;
    String dataCadastro;
    byte[] imagem;  
    ImageIcon imagemTipo;
    Double salario;
    String celular;
    java.sql.Date nascimento;
    String sexo;
    
    /*
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
String data = dateFormat.format(dataDeNascimento);
java.sql.Date date = null;
date = new java.sql.Date( ((java.util.Date)dateFormat.parse(data)).getTime() );
this.dataDeNascimento = date;
    */

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public ImageIcon getImagemTipo() {
        return imagemTipo;
    }

    public void setImagemTipo(ImageIcon imagemTipo) {
        this.imagemTipo = imagemTipo;
    }

    public ImageIcon getFotoAsImageIcon(){
        
        this.imagemTipo = new ImageIcon();
        
        if(this.getImagem() != null){            
            this.imagemTipo = new ImageIcon(this.getImagem()); 
        }       
               
        return this.imagemTipo;
    }

    
    String nomeTabela = "usuarios";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
        

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
    
    public int inserirUsuarios(ModeloUsuario u){
        Conexao con = new Conexao();
        int status = 0;
        try{
            PreparedStatement stmt = con.conectaBanco().prepareStatement("insert into usuarios ("+
                    "nome," +
                    "usuario," +
                    "senha," +
                    "versao," +
                    "ativo," +
                    "dataCadastro," +
                    "imagem," +
                    "salario,"+
                    "celular,"+
                    "nascimento,"+
                    "sexo)" +
                    "values (" +
                    "?,"+
                    "?,"+
                    "?,"+
                    "?,"+
                    "?,"+
                    "?,"+
                    "?,"+
                    "?,"+
                    "?,"+
                    "?,"+
                    "?);");     
            stmt.setString(1, u.getNomeCompleto());  
            stmt.setString(2, u.getUsuario());  
            stmt.setString(3, u.getSenha());  
            stmt.setString(4, u.getVersao()); 
            stmt.setString(5, u.getAtivo()); 
            stmt.setString(6, u.getDataCadastro()); 
            stmt.setBytes(7, u.getImagem());
            stmt.setDouble(8, u.getSalario());
            stmt.setString(9, u.getCelular());
            stmt.setDate(10, u.getNascimento());
            stmt.setString(11, u.getSexo());
             
            System.out.println("Inserir do Usuário: "+stmt); 
            status = stmt.executeUpdate(); 
            stmt.close();  
            //con.desconectar();
           
        }catch(Exception e){
            System.out.println("Erro ao inserir Usuário \n");
            e.printStackTrace();
        }
        return status;
    }
    
    public int alterarUsuarios(int idUsuario){
        Conexao con = new Conexao();
        int status = 0;
        String sql;
        try{
            PreparedStatement stmt = con.conectaBanco().prepareStatement("UPDATE usuarios SET " +
                    "nome = ?,"+
                    "usuario = ?,"+
                    "senha = ?,"+
                    "versao = ?,"+
                    "ativo = ?,"+
                    "dataCadastro = ?,"+
                    "imagem = ?,"+
                    "salario = ?,"+
                    "celular = ?,"+
                    "nascimento = ?,"+
                    "sexo = ? "+   
                    " WHERE id = ?;");
            
                    
            stmt.setString(1,this.getNomeCompleto());
            stmt.setString(2,this.getUsuario());
            stmt.setString(3,this.getSenha());
            stmt.setString(4,this.getVersao());
            stmt.setString(5,this.getAtivo());
            stmt.setString(6,this.getDataCadastro());
            stmt.setBytes(7,this.getImagem());
            stmt.setDouble(8,this.getSalario());
            stmt.setString(9,this.getCelular());
            stmt.setDate(10,this.getNascimento());
            stmt.setString(11,this.getSexo());
            stmt.setInt(12,idUsuario);
                  
            //super.alterarPessoa(this);
            System.out.println("Update "+stmt);
            status = stmt.executeUpdate();
            stmt.close(); 

        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    
    public int deletarUsuarios(int idUsuario){
        Conexao con = new Conexao();
        int status = 0;
        try{
            Statement stmt = con.conectaBanco().createStatement();
            String sql = "DELETE FROM usuarios WHERE id = "+idUsuario;
            status = stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }    
    
    public int quantosUsuarios(String o){
        Conexao con = new Conexao();
        int quantos = 0;
        String oqueBusca;
        try{
            Statement stmt = con.conectaBanco().createStatement();
            if (o == null ? "" != null : !o.equals("")){
                oqueBusca = "nome LIKE '%"+o+"%' AND ";
            }else{
                oqueBusca = "";
            }
            String sql = "select count(id) as total FROM usuarios WHERE " + oqueBusca + nomeTabela+".ativo = 1;";
            
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                quantos = resultSet.getInt("total");
            }
            
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return quantos;
    }
    
    //utilizado para alimentar o jTable inicial (telaUsuarios)
    public ArrayList buscarUsuario(String qualBuscar, int i, int f){
        ArrayList resultado = new ArrayList();
        String oqueBusca,consulta,nome,usuario,dataCadastro,id;
        int contUsuario;
        
        
        Conexao con = new Conexao();
        try{
            Statement stmt = con.conectaBanco().createStatement();

            if (qualBuscar == null ? "" != null : !qualBuscar.equals("")){
                oqueBusca = "nome LIKE '%"+qualBuscar+"%' AND ";
            }else{
                oqueBusca = "";
            }
            contUsuario=0;
            consulta = "SELECT * FROM "+nomeTabela+"  WHERE " + oqueBusca + nomeTabela+".ativo = 1 LIMIT "+i+", "+f+";";

            ResultSet resultSet = stmt.executeQuery(consulta);

            System.out.println("consulta "+consulta);

            while (resultSet.next()){
                id = resultSet.getString("id");
                nome = resultSet.getString("nome");
                usuario = resultSet.getString("usuario");
                dataCadastro = resultSet.getString("dataCadastro"); 
                resultado.add(new String[] { nome, usuario, dataCadastro,id });
                
            }
            stmt.close();
        }catch(Exception e){
            System.out.println("Erro ao consultar Usuário \n");
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    //utilizado para alimentar os jTextField (telaUsuariosDetalhe)
    public ModeloUsuario buscarUsuariosPorId(int idSelecionado){
        //ArrayList resultado = new ArrayList();
        ModeloUsuario u = new ModeloUsuario();
        String nome,ativo,usuario=null,senha=null,versao,dataCadastro,id,celular=null,sexo=null;
        java.sql.Date nascimento=null;
        
        Double salario;
        InputStream input;
        String consulta;
        SimpleDateFormat formatoSQL;
        formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
  
        Conexao con = new Conexao();
        try{
            Statement stmt = con.conectaBanco().createStatement();

           
            consulta = "SELECT * FROM "+nomeTabela+"  WHERE id=" +idSelecionado +" AND "+ nomeTabela+".ativo = 1;";

            ResultSet resultSet = stmt.executeQuery(consulta);

            System.out.println("consulta id Selecionado "+consulta);

            while (resultSet.next()){
                id = resultSet.getString("id");
                nome = resultSet.getString("nome");
                usuario = resultSet.getString("usuario");
                senha = resultSet.getString("senha"); 
                versao = resultSet.getString("versao");
                ativo = resultSet.getString("ativo");
                dataCadastro = resultSet.getString("dataCadastro"); 
                input = resultSet.getBinaryStream("imagem");
                salario = resultSet.getDouble("salario"); 
                celular = resultSet.getString("celular");
                
                if (resultSet.getString("nascimento")!=null){
                    nascimento = new java.sql.Date( ((java.util.Date)formatoSQL.parse(resultSet.getString("nascimento"))).getTime() );

                }
                sexo = resultSet.getString("sexo"); 
                
                
                u.setId(Integer.parseInt(id));
                u.setNomeCompleto(nome);
                u.setUsuario(usuario);
                u.setSenha(senha);
                u.setVersao(versao);
                u.setAtivo(ativo);
                u.setDataCadastro(dataCadastro);
                u.setSalario(salario);
                u.setCelular(celular);
                u.setNascimento(nascimento);
                u.setSexo(sexo);
   

                
                
                if(input != null){  
  
                   ByteArrayOutputStream output = new ByteArrayOutputStream();  
                   // set read buffer size  

                   byte[] rb = new byte[1024];  
                   int ch = 0;  

                   while ((ch = input.read(rb)) != -1){   
                       output.write(rb, 0, ch);  
                   }  

                   // transfer to byte buffer  
                   byte[] b = output.toByteArray();  
                   input.close();  
                   output.close();  

                   // onde o método setImagem espera um array de bytes  
                   u.setImagem(b);                       
                }            
                
                
                
                
                
                //resultado.add(new String[] { id, nome, usuario, senha, versao, ativo, dataCadastro });
            }
            stmt.close();
        }catch(Exception e){
            System.out.println("Erro ao consultar Usuário \n");
            e.printStackTrace();
        }
        
        return u;
    }

}

