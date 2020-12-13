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
	<div class="centro"><h1>Categorias</h1></div>
	<hr>
	<br>
        <div class="col-md-offset-2 col-md-3" >
        <h3>
        <c:if test="${categoria.id_categoria == 0}" >Nuevo</c:if>
        <c:if test="${categoria.id_categoria != 0}" >Editar</c:if>
        registro        
        </h3>            
        </div>
        <div class="col-md-offset-4 col-md-2"><br>
<p><a href="Controlador_categorias">Atras..<span style="text-align: right; padding-top: 3px; font-size: 20px;" class="icon-arrow-with-circle-left" ></span></a></p>            
        </div>
        <div class="clearfix"></div>
        <br>
	<div class="container">
            
		<form action="Controlador_categorias" method="post">
		<div class="col-md-2"></div>
		<div class="col-md-3">
                    <input type="hidden" name="id" value="${categoria.id_categoria}"/>
		<label for="descripcion">Nombre categoria:</label>
                <input type="text" name="nombre_categoria" placeholder="Nombre categoria" class="form-control" value="${categoria.nombre_categoria}">	
		</div>
		<div class="col-md-2">
		<label for="tipo">Operacion:</label>
		<select name="operacion" id="operacion" class="form-control">
                    <option value="${categoria.operacion}">${categoria.operacion}</option>
                    <option value="INGRESO">Ingreso</option>
                    <option value="GASTO">Gasto</option>
		</select>
		</div>
		<div class="col-md-3"><br>
		<input type="submit" name="" class="btn btn-primary" value="Registrar" style="width: 100%;">		
		</div>	
		<div class="clearfix"></div>			
		</form>		
	</div>
</section>
</body>
</html>