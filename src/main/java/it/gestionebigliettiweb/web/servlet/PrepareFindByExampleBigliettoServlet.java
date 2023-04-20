package it.gestionebigliettiweb.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionebigliettiweb.model.Biglietto;

@WebServlet("/PrepareFindByExampleBigliettoServlet")
public class PrepareFindByExampleBigliettoServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("findByExample_attr", new Biglietto());
		request.getRequestDispatcher("/biglietto/search.jsp").forward(request, response);
	}


}
