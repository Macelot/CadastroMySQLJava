/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;
import java.util.ArrayList;
import modelo.ModeloUsuario;

/**
 *
 * @author marcelojtelles
 */
public class ControleUsuario {
     /**
     * Atributos: usuario.
     */
    private ModeloUsuario usuario;
    // Construtor
    public ControleUsuario(){
        usuario = new ModeloUsuario();
    }

    /**
	* Retorna uma referência para a entidade Usuario
	* @return referência para a entidade Usuario
	*/
    public ModeloUsuario getUsuario() {
        return usuario;
    }
    /**
	* Configura a entidade usuario
	* @param usuario entidade usuario
	*/
    public void setUsuario(ModeloUsuario usuario) {
        this.usuario = usuario;
    }
    /**
	* Cadastra um usuario no banco de dados
	* @return situação do cadastro: 1 usuario cadastrado e 0 usuario não cadastrado
	*/
    public int inserir(){
        return usuario.inserirUsuarios(usuario);
    }
    /**
	* Altera os dados de um usuario no banco de dados
	* @return situação da alteração: 1 usuario alterado e 0 usuario não alterado
	*/
    public int alterar(int idUsuario){
        return usuario.alterarUsuarios(idUsuario);
    }
    /**
	* Exclui um usuario no banco de dados
	* @return situação da exclusão: 1 usuario excluido e 0 usuario não excluido
	*/
    public int deletar(int idUsuario){
        return usuario.deletarUsuarios(idUsuario);
    }
    
    public int quantosU(String oqueBuscar){
        return usuario.quantosUsuarios(oqueBuscar);
    }
    
    public ArrayList buscarU(String qualBuscar, int inicio, int fim){
        return usuario.buscarUsuario(qualBuscar,inicio,fim);
    }
    
    public ModeloUsuario buscarUpId(int idSelecionado){
        return usuario.buscarUsuariosPorId(idSelecionado);
    }
    

}
