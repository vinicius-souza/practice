package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Estado;
import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.EstadoDAO;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/estadocontroller.do")
public class EstadoController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Recebendo Parametros da Tela
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String uf = req.getParameter("uf");

		// Instanciando usuario e setando dados
		Estado est = new Estado();
		if (id != null || id != "0") {
			// Convertendo ID para Inteiro
			est.setId(Integer.parseInt(id));
		}
		est.setNome(nome);
		est.setUF(uf);

		// Persistindo no banco
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(est);

		// Resposta
		resp.getWriter().print("Salvo");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String acao = req.getParameter("acao");
		EstadoDAO estadoDAO = new EstadoDAO();

		if (acao == null || acao.equals("lis")) {
			List<Estado> lista = estadoDAO.buscarTodos();

			// Setar atributo do request
			req.setAttribute("listaest", lista);

			// Cria dispatcher pra encaminhar ao JSP
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("WEB-INF/listaest.jsp");

			// Encaminha ao JSP
			dispatcher.forward(req, resp);

			// resp.getWriter().print(lista);
		} else if (acao.equals("esc")) {
			// Pegando o id da tela
			String id = req.getParameter("id");
			Estado est = new Estado();
			est.setId(Integer.parseInt(id));
			estadoDAO.salvar(est);
			// Mensagem
			resp.getWriter().print("Salvo!");
		} else if (acao.equals("alt")) {

			String id = req.getParameter("id");
			Estado estado = estadoDAO.buscarPorId(Integer.parseInt(id));
			req.setAttribute("est", estado);
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("/WEB-INF/formestado.jsp");
			dispatcher.forward(req, resp);

		} else if (acao.equals("cad")) {
			String id = req.getParameter("id");

			Estado estado = new Estado();
			estado.setId(0);
			estado.setNome("");
			estado.setUF("");
			
			req.setAttribute("est", estado);
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("/WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		}

	}
}
