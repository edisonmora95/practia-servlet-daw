<%@page import="java.util.List"%>
<%@page import="modelos.Conferencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Espol conferencias</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    </head>
    <body>
    	<!--Barra de navegacion-->
        <nav class="navbar navbar-inverse navbar-static-top">
	      <div class="container">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="#">CONFERENCIAS</a>
	        </div>
	        <div id="navbar" class="collapse navbar-collapse">
	          <ul class="nav navbar-nav">
	            <li class="active"><a href="index.html">Inicio</a></li>
	            <li><a href="conferencias.jsp">Conferencia</a></li>
	            <li><a href="#">Asistentes</a></li>
	          </ul>
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="#about"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a></li>
	            <li><a href="#">Logout</a></li>
	          </ul>
	        </div><!--/.nav-collapse -->
	      </div>
	    </nav>

	    <div class="container conferencias">
	    	<h2>Conferencias a realizarse</h2>
	    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalConferencias">Registrar nueva</button>	 
	    	<table id="tablaConferencias" class="table table-bordered table-striped">
	    		<thead>
	    			<tr>
			            <th>Nombre</th>
			            <th>Fecha</th>
			            <th>Descripcion</th>
			            <th></th>
			            <th></th>
		          	</tr>
	    		</thead>
	    		<tbody>
	    			<%
		                List<Conferencia> conferencias = (List<Conferencia>) request.getAttribute("conferencia");
		                int cont = 0;
		                for (Conferencia c : conferencias) {
		            %>
		            <tr>
			            <th scope="row"><%= cont %></th>
			            <td><%= c.getNombre() %></td>
			            <td><%= c.getFecha() %></td>
			            <td><%= c.getDescripcion() %></td>
			            <td><a href="#"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
			            <td><a href="#"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
			        </tr>
		            <%
			            	cont+=1;
			            }
			        %>

	    		</tbody>
	    	</table>
	    </div>


	    <!-- Modal -->
	    <div class="modal fade" id="modalConferencias" tabindex="-1" role="dialog">
	      <div class="modal-dialog" role="document">
	        <div class="modal-content">
	          <form action="CreateUser" method="post">
	            <div class="modal-header">
	              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	              <h4 class="modal-title">Ingresar Conferencia</h4>
	            </div>
	            <div class="modal-body">
	                <div class="form-group">
	                  <input type="text" class="form-control" id="inputNombreConferencia" name="inputNombreConferencia" placeholder="Nombre">
	                </div>
	                <div class="form-group">
	                  <input type="date" name="inputDateConferencia">
	                </div>
	                <div class="form-group">
	                  <input type="text" class="form-control" id="inputDescConferencia" name="inputDescConferencia" placeholder="Algo que agregar?">
	                </div>
	            </div>
	            <div class="modal-footer">
	              <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	              <button type="submit" class="btn btn-primary">Registrar</button>
	            </div>
	          </form>
	        </div>
	      </div>
	    </div>

	    <!--SCRIPTS-->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	    <script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js"></script>



    </body>
</html>
