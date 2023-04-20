package it.gestionebigliettiweb.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.gestionebigliettiweb.service.MyServiceFactory;


@WebServlet("/PrepareEditBigliettoServlet")
public class PrepareEditBigliettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idBigliettoToUpdate = request.getParameter("idBiglietto");

		if (!NumberUtils.isCreatable(idBigliettoToUpdate)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {
			request.setAttribute("BigliettoToUpdate", MyServiceFactory.getBigliettoServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idBigliettoToUpdate)));
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/biglietto/edit.jsp").forward(request, response);
	}

}
