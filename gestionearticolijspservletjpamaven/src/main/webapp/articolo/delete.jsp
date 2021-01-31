<!doctype html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="it.gestionearticolijspservletjpamaven.model.Articolo"%>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Inserisci nuovo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Delete Articolo
		    </div>
		    
		    <h1 style="color: red; text-align: center; padding-top: 50px">Attenzione!
		questa azione non può essere annullata</h1>
	<h3 style="text-align: center; padding-top: 50px">Sei sicuro di
		voler elminare questo articolo?</h3>
	
		    <% Articolo articoloInPagina = (Articolo)request.getAttribute("articoloDaEliminare"); %>
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Codice</dt>
				  <dd class="col-sm-9"><%=articoloInPagina.getCodice() %></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione:</dt>
				  <dd class="col-sm-9"><%=articoloInPagina.getDescrizione() %></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Prezzo:</dt>
				  <dd class="col-sm-9"><%=articoloInPagina.getPrezzo() %></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data di Arrivo:</dt>
				  <dd class="col-sm-9"><%=articoloInPagina.getDataArrivo()!=null? new SimpleDateFormat("dd/MM/yyyy").format(articoloInPagina.getDataArrivo()):"N.D."  %></dd>
		    	</dl>
		    	
		    </div>
		    <div>
			<form role="form" action="ExecuteDeleteArticoloServlet" method="post">
					<div class="form-group">
						<input class="form-control" name="idArticolo" id="idArticolo"
							placeholder="ID ARTICOLO" type="hidden"
							value="<%=articoloInPagina.getId()%>" />
				</div>
				<div class="text-center">
					<input type="submit" class="btn btn-danger" value='Conferma'>
			
					</div>
			</form>
			</div>
		    <div class='card-footer'>
		        <a href="ListArticoliServlet" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>