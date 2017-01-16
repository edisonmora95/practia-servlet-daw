<%-- 
    Document   : asistentes
    Created on : Jan 15, 2017, 8:27:40 AM
    Author     : edison
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <title>Espol asistentes</title>
    </head>
    <body>
        <!--NAVBAR-->
        <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">ASISTENTES</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
              <ul class="nav navbar-nav">
                <li class="active"><a href="index.html">Inicio</a></li>
                <li><a href="conferencias.jsp">Conferencia</a></li>
                <li><a href="asistentes.jsp">Asistentes</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li><a href="#about"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a></li>
                <li><a href="#">Logout</a></li>
              </ul>
            </div><!--/.nav-collapse -->
          </div>
        </nav>
        <!--FIN DE NAVBAR-->
        <div class="container asistentes">
          <h1>Asistentes</h1>
          <p>Seleccione la conferencia que desee buscar</p>
          <div class="dropdown">

            <select name="conferencias" id="conferencias">
              <option value="" disabled selected hidden>Select your option</option>
            </select>
          </div>

          <div class="asistentes">
            <h2>Personas que asistieron</h2>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalAsistentes">Añadir nuevo</button>  
                    <!--<form action="MainServlet" method="post">-->
            <table id="tabla-asistentes" class="table table-bordered table-striped" action="#" method="POST">
              <thead>
                <tr>
                  <th>Cedula</th>
                  <th>Nombre</th>
                  <th>Apellido</th>
                  <th>Correo</th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                
              </tbody>
            </table>
          </div>
          
          <!-- Modal -->
	    <div class="modal fade" id="modalAsistentes" tabindex="-1" role="dialog">
	      <div class="modal-dialog" role="document">
	        <div class="modal-content">
	          <form action="CreateAsistant" method="post">
	            <div class="modal-header">
	              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	              <h4 class="modal-title">Ingresar Asistente</h4>
	            </div>
	            <div class="modal-body">
	                <div class="form-group">
	                  <input type="text" class="form-control" id="inputAsistantId" name="inputAsistantId" placeholder="Nº de Cédula">
	                </div>
                        <div class="form-group">
	                  <input type="text" class="form-control" id="inputAsistantName" name="inputAsistantName" placeholder="Nombre">
	                </div>
                        <div class="form-group">
	                  <input type="text" class="form-control" id="inputAsistantLast" name="inputAsistantLast" placeholder="Apellido">
	                </div>
                        <div class="form-group">
	                  <input type="email" class="form-control" id="inputAsistantEmail" name="inputAsistantEmail" placeholder="Correo Electrónico">
	                </div>
                        <div class="form-group">
                            <select name="inputConferenciaId" id="inputConferenciaId"></select>
	                </div>
                        <input type="hidden" id="inputAction" name="inputAction">
	            </div>
	            <div class="modal-footer">
	              <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	              <button type="submit" class="btn btn-primary">Registrar</button>
	            </div>
	          </form>
	        </div>
	      </div>
	    </div>

        </div>

        <!--SCRIPTS-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js"></script>
        <script type="text/javascript" src="js/asistentes.js"></script>
    </body>
</html>
