/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atualizaTabelas;

import controle.ControleUsuario;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import minhasTabelas.ModeloTabelaUsuario;
import tela.TelaUsuarios;
import tela.TelaUsuariosDetalhe;
import tela.TelaPrincipal;
import utilidades.JNumberFormatField;

/**
 *
 * @author marcelojtelles
 */
public class AtualizaTabelaUsuario {
    
    public static ArrayList<Object> dados = new ArrayList<Object>();
    public static ArrayList<Object> dadosSoDaPagina = new ArrayList<Object>();
    public static int totalReg;
    public static int fim,ini,calculaPaginasTotal,comoCalcula=1,calculaPaginas;
    public static int totalRegistros,contadorRegistrosEncontrados=0;
    public static final int items=10;
    public static int totalPaginas,paginaAtual;
    private static JTable table = null;
       
    public static int proximoInterno;
    public static int anteriorInterno;
    public static int quantidadeTotalInterno;
    public static int iniInterno;
    public static int fimInterno;
    
    public static JTable montador (ArrayList dados, String[] colunas, final int quantidadeDeRegistros){
        if (quantidadeDeRegistros==0){
            dados.add(new String[] { "", "", "", "" });
        }
        dadosSoDaPagina=dados;
        if (dadosSoDaPagina.size()>AtualizaTabelaUsuario.items){
            for (int k=AtualizaTabelaUsuario.items;k<dadosSoDaPagina.size();k++){
                dadosSoDaPagina.remove(k);
            }
        }
             
        table = new JTable(new ModeloTabelaUsuario(dadosSoDaPagina,colunas)); 
         
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
            private void tableMouseClicked(MouseEvent evt) {
                Object valor;  
                int rowIndex = table.getSelectionModel().getAnchorSelectionIndex();
                TableModel tm = table.getModel();
                valor = tm.getValueAt(rowIndex, 3);
                System.out.println("rr"+rowIndex);
                DecimalFormat df;
                df = new DecimalFormat("#0.00"); 
                
                
                int idConsultado;
                idConsultado = Integer.valueOf(String.valueOf(valor));
                 
                String id="";
                String nome="";
                String usuario="";
                String senha="";
                String dataCadastro="";
                String celular="";
                String sexo="";
                Double salario=0.0;
                java.sql.Date nascimento;
                
                
                try{
                    
                    //this.setVisible(false);
                    TelaUsuariosDetalhe t;
                    t = new TelaUsuariosDetalhe();
                    TelaPrincipal.telaTrabalho.add(t);
                    t.setVisible(true);
                    
                    ControleUsuario u = new ControleUsuario();
                   
                    u.setUsuario(u.buscarUpId(idConsultado));
                    
                    
                    TelaUsuariosDetalhe.id=(u.getUsuario().getId());
                    TelaUsuariosDetalhe.txtNome.setText(u.getUsuario().getNomeCompleto());
                    TelaUsuariosDetalhe.txtUsuario.setText(u.getUsuario().getUsuario());                          
                    TelaUsuariosDetalhe.pwdSenha.setText(u.getUsuario().getSenha());
                    TelaUsuariosDetalhe.pwdSenha2.setText(u.getUsuario().getSenha());
                    TelaUsuariosDetalhe.txtDataCadastro.setText(u.getUsuario().getDataCadastro());
                    TelaUsuariosDetalhe.dtcNascimento.setDate(u.getUsuario().getNascimento());
                    TelaUsuariosDetalhe.txtCelular.setText(u.getUsuario().getCelular());
                    TelaUsuariosDetalhe.txtSalario.setText(df.format(u.getUsuario().getSalario()));
                    
                    if (u.getUsuario().getSexo()!=null){
                        if (u.getUsuario().getSexo().equals("M")){
                            TelaUsuariosDetalhe.radMasculino.setSelected(true);
                        }
                        if (u.getUsuario().getSexo().equals("F")){
                            TelaUsuariosDetalhe.radFeminino.setSelected(true);
                        }

                        TelaUsuariosDetalhe.txtDataCadastro.setText(u.getUsuario().getDataCadastro());
                    }
                    

                    TelaUsuariosDetalhe.btnAtualizar.setEnabled(true);
                    TelaUsuariosDetalhe.btnDeletar.setEnabled(true);
                    TelaUsuariosDetalhe.btnInserir.setEnabled(false);
                    TelaUsuariosDetalhe.txtNome.setEditable(true);
                    TelaUsuariosDetalhe.txtUsuario.setEditable(true);
                    TelaUsuariosDetalhe.pwdSenha.setEditable(true);
                    TelaUsuariosDetalhe.pwdSenha2.setEditable(true);
                    //telaUsuariosDetalhe.txtDataCadastro.setEditable(true);
                    
                    ImageIcon imageIcon = new ImageIcon();
        
                    if(u.getUsuario().getImagem() != null){            

                        // só lembrando... getImagem() retorna um array de bytes
                        imageIcon  = new ImageIcon(u.getUsuario().getImagem());
                        TelaUsuariosDetalhe.lblImagem.setIcon(imageIcon);
                        TelaUsuariosDetalhe.imagemRecebidaDobanco = imageIcon;
                    }       

                    TelaUsuariosDetalhe.idUsuario = id;
        
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"erro " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            
        });     
    return table;       
    }

    
    public static JTable atualizaUsuarioPag(JInternalFrame Frame,int iniRecebeu, int fimRecebeu, String consulta) {
    String[] colunas = new String[] { "Nome","Usuário","Data Cadastro" }; 
    String id;
    String nome;
    String email;
    String idade;
    double a;
    int resto;
    int s;
    try{
        ControleUsuario u = new ControleUsuario();
        totalRegistros = u.quantosU(consulta);
        TelaUsuarios.lblTotal.setText(String.valueOf(totalRegistros));
       //estou consultando
        dados = u.buscarU(consulta,iniRecebeu,items);
        contadorRegistrosEncontrados = dados.size();        

        System.out.println("fimRecebeu"+fimRecebeu);
        System.out.println("totalRegistros"+totalRegistros);
        if (fimRecebeu<totalRegistros){
            TelaUsuarios.btnProximo.setEnabled(true);
            TelaUsuarios.btnUltimo.setEnabled(true);
        }else{
            TelaUsuarios.btnProximo.setEnabled(false);
            TelaUsuarios.btnUltimo.setEnabled(false);
            TelaUsuarios.btnAnterior.requestFocus();
        }

        if (iniRecebeu>items-1){
            TelaUsuarios.btnAnterior.setEnabled(true);
            TelaUsuarios.btnPrimeiro.setEnabled(true);
        }else{
            TelaUsuarios.btnAnterior.setEnabled(false);
            TelaUsuarios.btnPrimeiro.setEnabled(false);
            TelaUsuarios.btnProximo.requestFocus();
        }
        TelaUsuarios.iniInterno = iniRecebeu;
        //telaUsuarios.fimInterno = fimRecebeu;

        if (fimRecebeu>totalRegistros){
            fim = totalRegistros;
        }else{
            fim = fimRecebeu;
        }
        if (iniRecebeu<0){
            ini=0;
        }else{
            ini=iniRecebeu;
        }

        a = totalRegistros/items;
        //System.out.println("totalReg"+totalRegistros);
        resto = totalRegistros%items;
        //System.out.println("resto"+resto);

        if (resto>=1){
            a=a+1;
        }
        totalPaginas = (int) a;
        paginaAtual = fim/items;
        resto = fim%items;
        if (resto>=1){
            paginaAtual++;
        }



        //alimneta comboBox das paginas exixtentes
        TelaUsuarios.cmbIr.removeAllItems();
        for (int i=1;i<=totalPaginas;i++){
            TelaUsuarios.cmbIr.addItem(i);
        }
        TelaUsuarios.cmbIr.setSelectedItem(paginaAtual);
        //telaAgendaPag.cmbIr.setSelectedItem("1");
        
        TelaUsuarios.lblPagina.setText(paginaAtual+" de "+String.valueOf(totalPaginas));


        table = montador(dados,colunas,contadorRegistrosEncontrados);

        }catch (Exception e){
            JOptionPane.showMessageDialog(Frame,"erro criar jTable" + e.getMessage());
        }
        return table;       
    }

    
    
}
