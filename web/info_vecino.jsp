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
        <div class="col-md-offset-4 col-md-3" >
        <h3>
        <c:if test="${vecino.id_vecino == 0}" >Nuevo</c:if>
        <c:if test="${vecino.id_vecino != 0}" >Editar</c:if>
        registro        
        </h3>            
        </div>
        <div class="col-md-offset-0 col-md-2"><br>
<p><a href="Controlador_vecinos">Atras..<span style="text-align: right; padding-top: 3px; font-size: 20px;" class="icon-arrow-with-circle-left" ></span></a></p>            
        </div>
        <div class="clearfix"></div>
	<form action="Controlador_vecinos" method="post">            
            <div class="container">
		<div class="col-md-4"></div>
		<div class="col-md-4">
                <input type="hidden" name="id" value="${vecino.id_vecino}"/>
		<label for="nombre">Nombre:</label>
		<input type="text" name="nombre" class="form-control" placeholder="Ingresar nombre" value="${vecino.nombre}">
		<label for="apellidos">Apellidos:</label>
		<input type="text" name="apellidos" class="form-control" placeholder="Ingresar apellidos" value="${vecino.apellidos}">
		<label for="celular">Celular:</label>
		<input type="text" name="celular" class="form-control" placeholder="Ingresar celular" value ="${vecino.celular}">
		<label for="direccion">Direccion:</label>
                <input type="text" name="direccion" class="form-control" placeholder="Ingresar direccion" value="${vecino.direccion}">
		<label for="manzana">Manzano:</label>
                <input type="text" name="manzana" class="form-control" placeholder="Ingresar manzano" value="${vecino.manzana}">
		<label for="lote">Lote:</label>
		<input type="text" name="lote" class="form-control" placeholder="Ingresar lote" value="${vecino.lote}"><br>
                <center>
                    <input type="submit" name="" class="btn btn-primary" value="Registrar" style="width: 100%;">
                </center>                
		</div>
                <br><br>               
            </div>                
                <br><br><br>			
	</form>
</section>
</body>
</html>
