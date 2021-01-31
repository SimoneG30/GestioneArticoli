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
		<%
	Articolo articoloInPagina = (Articolo) request.getAttribute("articoloDaModificare");
	%>
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica elemento</h5> 
		    </div>
		    <div class='card-body'>
					<form method="post" action="ExecuteUpdateArticoloServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Codice</label>
								<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" value="<%=articoloInPagina.getCodice()%>">
							</div>
							
							<div class="form-group col-md-6">
								<label>Descrizione</label>
								<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" value="<%=articoloInPagina.getDescrizione()%>">
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>Prezzo</label>
								<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo" value="<%=articoloInPagina.getPrezzo()%>">
							</div>
							<div class="form-group col-md-3">
								<label>Data di Arrivo</label>
                        		<input class="form-control" id="dataArrivo" type="date" placeholder="dd/MM/yy"
                            		title="<%=articoloInPagina.getDataArrivo()%>"  name="dataArrivo" value="<%=articoloInPagina.getDataArrivo()%>">
							</div>
							</div>
							<div class="form-group col-md-3">
								<input type="hidden" class="form-control" name="idArticolo" id="idArticolo" placeholder="ID ARTICOLO" value="<%=articoloInPagina.getId()%>"/>
							</div>
														
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>