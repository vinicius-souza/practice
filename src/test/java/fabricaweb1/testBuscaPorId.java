package fabricaweb1;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class testBuscaPorId {

	public static void main(String[] args){
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usuRetorno = usuDAO.buscarPorId(3);
		
		if(usuRetorno != null)
			System.out.println(usuRetorno.getId() + "   " + usuRetorno.getNome() + "   "  + usuRetorno.getLogin() + "   " + usuRetorno.getSenha());
	}
}
