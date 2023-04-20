package it.gestionebigliettiweb.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.gestionebigliettiweb.service.MyServiceFactory;


@WebServlet("/PrepareDeleteBigliettoServlet")
public class PrepareDeleteBigliettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idBigliettoToDelete = request.getParameter("idBiglietto");

		if (!NumberUtils.isCreatable(idBigliettoToDelete)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {
			request.setAttribute("BigliettoToDelete", MyServiceFactory.getBigliettoServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idBigliettoToDelete)));
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}


		request.getRequestDispatcher("/biglietto/delete.jsp").forward(request, response);
	}

}
