package fabricaweb1;

import br.com.fabricadeprogramador.persistencia.entidade.Estado;
import br.com.fabricadeprogramador.persistencia.jdbc.EstadoDAO;

public class TestEstadoDAO {

	public static void main(String[] args) {
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado1 = new Estado();
		Estado estado2 = new Estado();
		
		estado1.setNome("Mato Grosso do Sul");
		estado1.setUF("MS");
		
		estado2.setNome("Sao Paulo");
		estado2.setUF("SP");
		
		estadoDAO.cadastrar(estado1);
		estadoDAO.cadastrar(estado2);
		System.out.println("Cadastrado com Sucesso\n");

	}

}
