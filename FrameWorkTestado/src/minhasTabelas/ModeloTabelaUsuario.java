/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minhasTabelas;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marcelotelles
 */
    public class ModeloTabelaUsuario extends AbstractTableModel{ 
        private ArrayList linhas = null;  
        private String [] colunas = null;  
        public String[] getColunas() {return colunas;}  
        public ArrayList getLinhas() {return linhas;}  
        public final void setColunas(String[] strings) {colunas = strings;}  
        public final void setLinhas(ArrayList list) {linhas = list;}
        

    @Override
        public int getColumnCount() {
            return colunas.length;
        }

    @Override
        public int getRowCount() {
            return linhas.size();
        }

    @Override
        public String getColumnName(int col) {
            return colunas[col];
        }

    @Override
        public Object getValueAt(int rowIndex, int columnIndex) {  
            // Obtem a linha, que é uma String []  
            String [] linha = (String [])getLinhas().get(rowIndex);  
            // Retorna o objeto que esta na coluna  
            return linha[columnIndex];  
        } 

    @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

    @Override
        public boolean isCellEditable(int row, int col) {
            //if (col < 2) {
                return false;
            //} else {
            //    return true;
           // }
        }

    @Override
        public void setValueAt(Object value, int row, int col) {
            // Obtem a linha, que é uma String []  
            String [] linha = (String [])getLinhas().get(row);  
            // Altera o conteudo no indice da coluna passado  
            linha[col] = (String)value;  
            // dispara o evento de celula alterada  
            fireTableCellUpdated(row,col);
        }
        
        public ModeloTabelaUsuario(ArrayList dadosRecebidos, String[] colunasRecebidas){  
            setLinhas(dadosRecebidos);  
            setColunas(colunasRecebidas);  
        }  
    }