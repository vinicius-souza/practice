package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Estado;
import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class EstadoDAO {

	private Connection con;

	public EstadoDAO() {
		// Obtendo uma conexão com o banco
		con = ConexaoFactory.getConnection();
	}

	public void cadastrar(Estado estado) {
		String sql = "insert into estado (nome, UF) values (?, ?)";

		// Criando objeto Statment
		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			preparador.setString(1, estado.getNome());
			preparador.setString(2, estado.getUF());
			// Executando no banco
			preparador.execute();
			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterar(Estado estado) {
		String sql = "update estado set nome=?, uf=? where id=?";

		// Criando objeto Statment
		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			preparador.setString(1, estado.getNome());
			preparador.setString(2, estado.getUF());
			preparador.setInt(3, estado.getId());

			// Executando no banco
			preparador.execute();
			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Salva com Insert ou Update Se o estado tiver id então altera senao insere
	 * 
	 * @param estado
	 */
	public void salvar(Estado estado) {

		if (estado.getId() == null || estado.getId() == 0) {
			cadastrar(estado);
		} else {
			alterar(estado);
		}

	}

	public List<Estado> buscarTodos() {

		List<Estado> lista = new ArrayList<Estado>();
		String sql = "select * from estado";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			ResultSet resultado = preparador.executeQuery();
			Estado estado;
			while (resultado.next()) {

				estado = new Estado();
				estado.setId(resultado.getInt("id"));
				estado.setNome(resultado.getString("nome"));
				estado.setUF(resultado.getString("uf"));
				
				lista.add(estado);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Estado buscarPorId(Integer id) {
		
		Estado estado = null;
		String sql = "select * from estado where id = ?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			//substitui o "?" no SQL pela variavel indicada
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			
			if (resultado.next()) {

				estado = new Estado();
				estado.setId(resultado.getInt("id"));
				estado.setNome(resultado.getString("nome"));
				estado.setUF(resultado.getString("uf"));
				
			}
			
			System.out.println("Encontrado com Sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return estado;
	}

}
