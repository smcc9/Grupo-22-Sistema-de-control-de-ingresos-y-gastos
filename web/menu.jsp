                        <%
                        if (session.getAttribute("login")!= "OK") {
                                response.sendRedirect("login.jsp");
                            }
                        %>
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
    <div class="col-md-offset-3 col-md-6 centro">
        <h1>Bienvenido al Sistema</h1>
        <h3><%= session.getAttribute("nombre") %> <%= session.getAttribute("apellidos") %></h3>
    </div>
<hr>
<br>
<div class="clearfix"></div>
	<div class="centro">
            <div class="row">
                    <a href="Controlador_ingresos">
                        <div class="col-md-4">
			<div class="contenedor_menu enlace_menu">
			<span class="icon-credit"></span>
			<h3>Ingresos</h3>	
			</div>	
			</div>
                    </a>
                    <a href="Controlador_gastos">
			<div class="col-md-4">
				<div class="contenedor_menu enlace_menu">
				<span class="icon-calculator"></span>					
				<h3>Gastos</h3>	
				</div>				
			</div>                        
                    </a>
                    <a href="Controlador_reportes">
                        <div class="col-md-4">
				<div class="contenedor_menu enlace_menu">
					<span class="icon-pie-chart"></span>
				<h3>Reportes</h3>	
				</div>				
			</div>                        
                    </a>
		</div>
            <div class="clearfix"></div>
		<div class="row">
			<div class="col-md-2"></div>
                        <a href="Controlador_categorias">
			<div class="col-md-4">
				<div class="contenedor_menu enlace_menu">
					<span class="icon-bookmarks"></span>
				<h3>Informacion Categorias</h3>			
				</div>			
			</div>
                        </a>
                        <a href="Controlador_vecinos" class="">
                        <div class="col-md-4">
				<div class="contenedor_menu enlace_menu">
					<span class="icon-users"></span>
				<h3>Informacion Vecinos</h3>
				</div>
			</div>                           
                        </a>
		</div>
	</div>    
</section>    
</body>
</html>
