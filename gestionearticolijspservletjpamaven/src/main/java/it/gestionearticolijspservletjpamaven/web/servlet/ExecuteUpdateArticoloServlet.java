package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/ExecuteUpdateArticoloServlet")
public class ExecuteUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idArticoloDaModificare = request.getParameter("idArticolo");
		Long idArticoloDaModificareParsato = Long.parseLong(idArticoloDaModificare);
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		Integer prezzoParsato = Integer.parseInt(prezzoInputStringParam);
		String dataArrivoStringParam = request.getParameter("dataArrivo");
		Date dataArrivoParsed = parseDateArrivoFromString(dataArrivoStringParam);

		if (!this.validateInput(codiceInputParam, descrizioneInputParam, prezzoInputStringParam, dataArrivoStringParam)
				&& dataArrivoParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/articolo/edit.jsp").forward(request, response);
			return;
		}

		try {
			Articolo articoloInstance = MyServiceFactory.getArticoloServiceInstance().caricaSingoloElemento(idArticoloDaModificareParsato);
			articoloInstance.setCodice(codiceInputParam);
			articoloInstance.setDescrizione(descrizioneInputParam);
			articoloInstance.setPrezzo(prezzoParsato);
			if(!dataArrivoStringParam.isEmpty()) {
				articoloInstance.setDataArrivo(dataArrivoParsed);
			}
			MyServiceFactory.getArticoloServiceInstance().aggiorna(articoloInstance);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/articolo/edit.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);

	}

	private boolean validateInput(String codiceInputParam, String descrizioneInputParam, String prezzoInputStringParam,
			String dataArrivoStringParam) {
		// prima controlliamo che non siano vuoti
		if (StringUtils.isBlank(codiceInputParam) && StringUtils.isBlank(descrizioneInputParam)
				&& !NumberUtils.isCreatable(prezzoInputStringParam) && StringUtils.isBlank(dataArrivoStringParam)) {
			return false;
		}
		return true;
	}

	private Date parseDateArrivoFromString(String dataArrivoStringParam) {
		if (StringUtils.isBlank(dataArrivoStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataArrivoStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
