package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteArticoloServlet")
public class ExecuteDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idArticoloDaEliminare = request.getParameter("idArticolo");
		Long idArticoloDaModificareParsato = null;
		try {
			if (!idArticoloDaEliminare.isEmpty()) {
				idArticoloDaModificareParsato = Long.parseLong(idArticoloDaEliminare);
				Articolo articoloDaEliminare = MyServiceFactory.getArticoloServiceInstance()
						.caricaSingoloElemento(idArticoloDaModificareParsato);
				MyServiceFactory.getArticoloServiceInstance().rimuovi(articoloDaEliminare);
				request.setAttribute("successMessage", "Operazione effettuata con successo");
				request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/articolo/delete.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);
	}

}
