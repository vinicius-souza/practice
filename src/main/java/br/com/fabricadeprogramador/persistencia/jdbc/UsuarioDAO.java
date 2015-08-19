package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

/**
 * Objeto de acesso a dados
 * 
 * @author Vinicius Souza
 */

public class UsuarioDAO {

	private Connection con;

	public UsuarioDAO() {
		// Obtendo uma conexão com o banco
		con = ConexaoFactory.getConnection();
	}

	public void cadastrar(Usuario usuario) {
		String sql = "insert into usuario (nome, login, senha) values (?, ?, ?)";

		// Criando objeto Statment
		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			// Executando no banco
			preparador.execute();
			//preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterar(Usuario usuario) {
		String sql = "update usuario set nome=?, login=?, senha=? where id=?";

		// Criando objeto Statment
		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());

			// Executando no banco
			preparador.execute();
			//preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Salva com Insert ou Update Se o usuario tiver id então altera senao
	 * insere
	 * 
	 * @param usuario
	 */
	public void salvar(Usuario usuario) {

		if (usuario.getId() == null || usuario.getId() == 0) {
			cadastrar(usuario);
		} else {
			alterar(usuario);
		}

	}

	public void excluir(Usuario usuario) {
		String sql = "delete from usuario where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			// Criando objeto Statement
			preparador.setInt(1, usuario.getId());

			// Executando no banco
			preparador.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Usuario> buscarTodos() {

		List<Usuario> lista = new ArrayList<Usuario>();
		String sql = "select * from usuario";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			ResultSet resultado = preparador.executeQuery();
			Usuario usuario;
			while (resultado.next()) {

				usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));

				lista.add(usuario);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Usuario buscarPorId(Integer id) {

		Usuario usuario = null;
		String sql = "select * from usuario where id = ?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			//substitui o "?" no SQL pela variavel indicada
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			
			if (resultado.next()) {

				usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
			}
			
			System.out.println("Encontrado com Sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}

	public Usuario autenticar(Usuario usu) {
		Usuario usuario=null;
		String sql = "Select * from usuario where login=? and senha=?";
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usu.getLogin());
			preparador.setString(2, usu.getSenha());
			
			ResultSet resultado =preparador.executeQuery();
			if (resultado.next()){
				usuario =  new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}


}
