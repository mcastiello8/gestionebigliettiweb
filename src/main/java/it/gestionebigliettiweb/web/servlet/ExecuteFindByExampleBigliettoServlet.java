package it.gestionebigliettiweb.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionebigliettiweb.model.Biglietto;
import it.gestionebigliettiweb.service.MyServiceFactory;
import it.gestionebigliettiweb.utility.UtilityBigliettoForm;



@WebServlet("/ExecuteFindByExampleBigliettoServlet")
public class ExecuteFindByExampleBigliettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// binding

		String provenienzaInputParam = request.getParameter("provenienza");
		String destinazioneInputParam = request.getParameter("destinazione");
		String dataStringParam = request.getParameter("data");
		String prezzoInputStringParam = request.getParameter("prezzo");

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Biglietto bigliettoInstance = UtilityBigliettoForm.createBigliettoFromParams(provenienzaInputParam,
				destinazioneInputParam, dataStringParam, prezzoInputStringParam);
		
		// validazione vuota

		// business

		try {
			request.setAttribute("listaBigliettiAttribute", MyServiceFactory.getBigliettoServiceInstance().findByExample(bigliettoInstance));
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore");
			request.getRequestDispatcher("/biglietto/search.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/biglietto/results.jsp").forward(request, response);

	}
}
