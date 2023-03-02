package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 - Receber os parâmetros		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String lembrar = request.getParameter("lembrar");
		String usuario = "";
		
		System.out.println(email);
		
		//2 - Validar o preenchimento
		List<String> erros = new ArrayList<String>();
		
		if (email == null || email.isEmpty()) {
			erros.add("O email precisa ser preenchido");
		}
		
		if (senha == null || senha.isEmpty()) {
			erros.add("A senha precisa ser preenchida");
		}
		
		//3 - Consultar o banco de dados
		if (email.equals("teste@teste.com") && senha.equals("123")) {
			//Logado
			usuario = "Fulano";
		}
		else {
			erros.add("Usuário ou senha inválidos");
		}
		
		//4 - Redirecionar para a página interna ou de volta para o Login
		if (erros.isEmpty()) {
			// Se não há erros na lista, usuário ok
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect("areainterna.jsp");
		}
		else {
			// Invalida a sessão
			request.getSession().invalidate();
			request.setAttribute("erros", erros); // Erros é um atributo que será acessado no html
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			
			view.forward(request, response);
		}
	}

}
