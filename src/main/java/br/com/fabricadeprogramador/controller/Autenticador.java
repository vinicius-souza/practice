package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class Autenticador extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//Dados da tela de login
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		//Criando um objeto usuario de busca
		Usuario usu = new Usuario();
		
		usu.setLogin(login);
		usu.setSenha(senha);
		
		//Busca no banco
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuarioDAO.autenticar(usu);
		
		//Se existe
		if(usuAutenticado != null){
			
			//Criando uma sessao
			HttpSession sessao = req.getSession();
			sessao.setMaxInactiveInterval(3000);
			
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			
			req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
			
		} else{
			
			//Usuario não encontrado
			resp.sendRedirect("login.html");
		}
	}

}
