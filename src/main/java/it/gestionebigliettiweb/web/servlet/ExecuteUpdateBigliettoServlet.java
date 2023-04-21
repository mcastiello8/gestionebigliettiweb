package it.gestionebigliettiweb.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.gestionebigliettiweb.model.Biglietto;
import it.gestionebigliettiweb.service.MyServiceFactory;
import it.gestionebigliettiweb.utility.UtilityBigliettoForm;


@WebServlet("/ExecuteUpdateBigliettoServlet")
public class ExecuteUpdateBigliettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idBigliettoDaAggiornare=request.getParameter("idUpdate");
		
		if (!NumberUtils.isCreatable(idBigliettoDaAggiornare)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		// estraggo input
		String provenienzaInputParam = request.getParameter("provenienza");
		String destinazioneInputParam = request.getParameter("destinazione");
		String dataStringParam = request.getParameter("data");
		String prezzoInputStringParam = request.getParameter("prezzo");
		Long idBigliettoToUpdate = Long.parseLong(request.getParameter("idUpdate"));
		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Biglietto bigliettoInstance = UtilityBigliettoForm.createBigliettoFromAll(idBigliettoToUpdate, provenienzaInputParam,
				destinazioneInputParam, dataStringParam, prezzoInputStringParam);
		
		bigliettoInstance.setId(idBigliettoToUpdate);
		
		// se la validazione non risulta ok
		
		if (!UtilityBigliettoForm.validateBigliettoBean(bigliettoInstance)) {
			
			request.setAttribute("BigliettoToUpdate", bigliettoInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/biglietto/edit.jsp").forward(request, response);
			return;
		}
		

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			MyServiceFactory.getBigliettoServiceInstance().aggiorna(bigliettoInstance);
			request.setAttribute("listaBigliettiAttribute", MyServiceFactory.getBigliettoServiceInstance().listAll());
			request.setAttribute("successMessage", "Biglietto modificato con succeso");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/results.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/biglietto/results.jsp").forward(request, response);

	}

}
