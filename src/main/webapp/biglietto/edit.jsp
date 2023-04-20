<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="it.gestionebigliettiweb.model.Biglietto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Aggiorna</h5> 
				    </div>
				    <div class='card-body'>
		
		
		
							<form method="post" action="ExecuteUpdateBigliettoServlet" class="row g-3" novalidate="novalidate">
							
								<% Biglietto bigliettoInPagina = (Biglietto)request.getAttribute("BigliettoToUpdate"); %>
							
								<div class="col-md-5">
									<label for="provenienza" class="form-label">Provenienza</label>
									<input type="text" name="provenienza" id="provenienza" class="form-control" placeholder="Inserire la provenienza"  
										value="<%=bigliettoInPagina.getProvenienza()!=null?bigliettoInPagina.getProvenienza():"" %>" required>
								</div>
								
								<div class="col-md-5">
									<label for="destinazione" class="form-label">Destinazione</label>
									<input type="text" name="destinazione" id="destinazione" class="form-control" placeholder="Inserire la destinazione"  
										value="<%=bigliettoInPagina.getDestinazione()!=null?bigliettoInPagina.getDestinazione():"" %>" required>
								</div>
							
								<div class="col-md-3">
									<label for="prezzo" class="form-label">Prezzo</label>
									<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo" 
									value="<%=bigliettoInPagina.getPrezzo()!=null?bigliettoInPagina.getPrezzo():"" %>" required>
								</div>
							
								<div class="col-md-3">
									<label for="data" class="form-label">Data</label>
									<input class="form-control"  name="data" id="data" type="date" placeholder="dd/MM/yy" title="formato : gg/mm/aaaa" 
										value="<%=bigliettoInPagina.getData()!=null? bigliettoInPagina.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):""  %>" required/>
								</div>
							
								
								
								
								
								<input type="hidden" class="form-control" name="idUpdate" id="idDeleteIDd" 
									value="<%=bigliettoInPagina.getId()!=null?bigliettoInPagina.getId():"" %>" required>
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							</div>
		
						</form>
  
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>