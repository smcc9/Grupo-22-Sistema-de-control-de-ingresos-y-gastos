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
	<div class="centro"><h1>Gastos</h1></div>
	<hr>
	<br>
        <div class="col-md-offset-1 col-md-3" >
        <h3>
        <c:if test="${gasto.id_gastos == 0}" >Nuevo</c:if>
        <c:if test="${gasto.id_gastos != 0}" >Editar</c:if>
        registro        
        </h3>            
        </div>
        <div class="col-md-offset-5 col-md-2"><br>
<p><a href="Controlador_gastos">Atras..<span style="text-align: right; padding-top: 3px; font-size: 20px;" class="icon-arrow-with-circle-left" ></span></a></p>            
        </div>
        <div class="clearfix"></div>        
	<form action="Controlador_gastos" method="post">
	<div class="container">
		<div class="col-md-1"></div>
		<div class="col-md-6">
                <input type="hidden" name="id" value="${gasto.id_gastos}"/>
		<label for="descripcion">Descripcion</label>
                <textarea name="descripcion" id="descripcion" cols="30" rows="10" class="form-control" placeholder="Ingresar descripcion">${gasto.descripcion}</textarea>	
		</div>
		<div class="col-md-3">
		<label for="tipo">Tipo o categoria:</label>
		<select name="categoria" id="categoria" class="form-control">
                    <option value="${gasto.id_categoria}">${gasto.nombre_categoria}</option>
                    <c:forEach var="item_cat" items="${categoria}">
                        <c:if test="${item_cat.operacion == 'GASTO'}">
                        <option value="${item_cat.id_categoria}">${item_cat.nombre_categoria}</option>            
                        </c:if>                    
                    </c:forEach>
                    
		</select>
		<label for="tipo">Fecha:</label>
                <input type="date" name="fecha" class="form-control" value="${gasto.fecha}" >
		<label for="tipo">Monto:</label>
                <input type="number" name="monto" class="form-control" placeholder="0" value="${gasto.monto}" >
                <br>
		<input type="submit" name="" class="btn btn-primary" value="Registrar" style="width: 100%;">	
		</div>	
	</div>		
	</form>
</section>
</body>
</html>