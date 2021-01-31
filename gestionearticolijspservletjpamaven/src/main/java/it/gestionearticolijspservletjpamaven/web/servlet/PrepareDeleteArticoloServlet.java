package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;



@WebServlet("/PrepareDeleteArticoloServlet")
public class PrepareDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	Long idDaRimuovere = Long.parseLong(request.getParameter("idArticolo"));
    	
		try {
			request.setAttribute("articoloDaEliminare", MyServiceFactory.getArticoloServiceInstance().caricaSingoloElemento(idDaRimuovere));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/articolo/result.jsp").forward(request, response);
			return;		
			}

		request.getRequestDispatcher("/articolo/delete.jsp").forward(request, response);

	}
}