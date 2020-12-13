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
	<div class="centro"><h1>Informacion vecinos</h1></div>
	<hr>
	<br>
	<form action="" method="post">
		<div class="col-md-1"></div>
		<div class="col-md-10">
                    <div class="row">
                        <div class="col-md-3">
<p><a href="Controlador_vecinos?action=add"><span style="padding-top: 3px; font-size: 20px;" class="icon-circle-with-plus" ></span>Nuevo registro</a></p>                            
                        </div>
                        <div class="col-md-offset-6 col-md-3">
<p><a href="menu.jsp">Volver a inicio..<span style="text-align: right; padding-top: 3px; font-size: 20px;" class="icon-arrow-with-circle-left" ></span></a></p>                            
                        </div>
                    </div>
                    
                    <br>
		<div class="table-responsive">
			<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">Nombre</th>
			      <th scope="col">Apellidos</th>
			      <th scope="col">Celular</th>
			      <th scope="col">Direccion</th>
			      <th scope="col">Manzano</th>
			      <th scope="col">Lote</th>
			      <th></th>
			      <th></th>
			    </tr>
			  </thead>
			  <tbody>
                             <c:forEach var="item" items="${vecino}" >
                             <tr>
                             <td>${item.nombre}</td>
                             <td>${item.apellidos}</td>
                             <td>${item.celular}</td>
                             <td>${item.direccion}</td>
                             <td>${item.manzana}</td>
                             <td>${item.lote}</td>
                             <td><a href="Controlador_vecinos?action=edit&id=${item.id_vecino}">Editar</a></td>
                             <td><a href="Controlador_vecinos?action=delete&id=${item.id_vecino}">Eliminar</a></td>
                             </tr>                
                             </c:forEach>
			  </tbody>
			</table>
		</div>		
		</div>			
	</form>
</section>
</body>
</html>
