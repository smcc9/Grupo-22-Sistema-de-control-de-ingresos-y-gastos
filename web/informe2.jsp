<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" href="img/logo.png"><title>SCIG</title>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/Entypo/fonts/style.css">	
	<link rel="stylesheet" href="css/estilo.css">
</head>
<body>
<header id="header" class="container-fluid">
    <img src="img/logo1.png" style="width: 20%;">
<!--<br><br><br><br>-->
<div style="float: right; padding-right: 40px; padding-top: 20px; color: #FFF">    
<div class="btn-group">
    <button type="button" class="btn btn-danger"><%= session.getAttribute("nombre") %>
        <span class="icon-user" style="color: white; font-size: 16px; border: solid 1px #FFF; border-radius: 50%;"></span>
    </button>

  <button type="button" class="btn btn-danger">
    <a class="" href="Controlador_sesion">
        <span class="icon-squared-cross" style="color: white; font-size: 16px;"></span>
    </a>     
  </button>
</div>
    
</div>
</header>
<section class="container">
    <div>
    <%@ include file="opciones_menu.html" %>
    </div>    
	<div class="centro">
            <h1>Reporte de Ingresos</h1>
            <h4>De fecha :<%= session.getAttribute("fecha1") %> A: <%= session.getAttribute("fecha2") %> </h4>
        </div>
	<hr>
	<br>
	<form action="" method="post">
		<div class="col-md-1"></div>
		<div class="col-md-10">
                    <div class="row">
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-offset-6 col-md-3">
<p><a href="Controlador_reportes">Volver atras..<span style="text-align: right; padding-top: 3px; font-size: 20px;" class="icon-arrow-with-circle-left" ></span></a></p>                            
                        </div>
                    </div>
                    
                    <br>
		<div class="table-responsive">
			<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">Descripcion</th>
			      <th scope="col">Categoria</th>
			      <th scope="col">Fecha</th>
			      <th scope="col">Vecino</th>                              
			      <th scope="col">Monto</th>
			      <th></th>
			      <th></th>
			    </tr>
			  </thead>
			  <tbody>
                            <c:set var="montototal" value="${0}"></c:set> 
                             <c:forEach var="item" items="${reporte2}" >
                                 <c:set var="montototal" value="${montototal + item.monto}"></c:set>
                             <tr>
                             <td>${item.descripcion}</td>
                             <td>${item.nombre_categoria}</td>
                             <td>${item.fecha}</td>
                             <td>${item.nombre} ${ item.apellidos}</td>
                             <td>${item.monto}</td>                             
                             <td><a href="Controlador_ingresos?action=edit_reporte&id=${item.id_ingreso}">Editar</a></td>
                             <td><a href="Controlador_ingresos?action=delete_reporte&id=${item.id_ingreso}">Eliminar</a></td>
                             </tr>                
                             </c:forEach>    
			  </tbody>
			</table>
                             <div style="text-align: right;">
                                 <h3>Monto total:</h3>
                                 <h2 class="alert alert-success" style="padding-top: 0; margin-top: 0;">${montototal}</h2>
                             </div>                                
		</div>		
		</div>			
	</form>
                             <!--<a href="Controlador_reportes?action=reporte1">IMPRIMIR</a>-->
</section>
</body>
</html>
