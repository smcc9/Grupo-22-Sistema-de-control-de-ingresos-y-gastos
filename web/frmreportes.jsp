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
	<div class="row">
            <div class="col-md-2"></div>
		<div class="col-md-2 espacio">
			<div class="contenedor_informe_verde">
			<div class="header_informe">
				<h3>Total Ingresos</h3>
			</div>
			<div class="cuerpo_informe">
                            <h1>${ingreso_total} <small style="color:#FFF">BOB.</small></h1>
			</div>				
			</div>
		</div>
		<div class="col-md-2 espacio">
			<div class="contenedor_informe_azul">
			<div class="header_informe">
				<h3>Total Gastos</h3>
			</div>
			<div class="cuerpo_informe">
                            <h1>${gasto_total} <small style="color:#FFF">BOB.</small></h1>
			</div>				
			</div>
		</div>
		<div class="col-md-2 espacio">
			<div class="contenedor_informe_naranja">
			<div class="header_informe">
				<h3>Ingresos Mes</h3>
			</div>
			<div class="cuerpo_informe">
                            <h1>${ingreso_total_mes} <small style="color:#FFF">BOB.</small></h1>
			</div>				
			</div>
		</div>
		<div class="col-md-2 espacio">
			<div class="contenedor_informe_amarilo">
			<div class="header_informe">
				<h3>Gastos Mes</h3>
			</div>
			<div class="cuerpo_informe">
                            <h1>${gasto_total_mes} <small style="color:#FFF">BOB.</small></h1>
			</div>				
			</div>
		</div>
		<div class="col-md-2 espacio">
			<div class="contenedor_informe_rojo">
			<div class="header_informe">
				<h3>Ahorro</h3>
			</div>
			<div class="cuerpo_informe">
				<h1>${ingreso_total - gasto_total } <small style="color:#FFF">BOB.</small></h1>
			</div>				
			</div>
		</div>
	</div>
	<div class="centro"><h1>Reportes</h1></div>
	<hr>        
	<form action="Controlador_reportes" method="post">
	<div class="container">
            <div class="row">
                <div class="col-md-offset-8 col-md-3">
                    <p>
                        <a href="menu.jsp">Volver a inicio..
                            <span style="text-align: right; padding-top: 3px; font-size: 20px;" class="icon-arrow-with-circle-left" >                        
                            </span>
                        </a>
                    </p>                            
                </div>
            </div>        
            <h4 class="centro">Informe de ingresos o gastos en rango de fechas</h4>
		<div class="col-md-2"></div>
		<div class="col-md-2">
		<label for="operacion">Operacion:</label>
                <select name="operacion" class="form-control">
			<option value="INGRESOS">Ingresos</option>
			<option value="GASTOS">Gastos</option>
		</select>	
		</div>
		<div class="col-md-2">
		<label for="tipo">De fecha inicio:</label>
		<input type="date" class="form-control" name="fecha_inicio">
		</div>	
		<div class="col-md-2">
			<label for="tipo"> a fecha fin:</label>
		<input type="date" class="form-control" name="fecha_fin">
		</div>
		<div class="col-md-2">	<br>
			<input type="submit" name="" class="btn btn-primary" value="Buscar" style="width: 100%;">	
		</div>
	</div>		
	</form>
        <hr>
        <h4 class="centro">Informe de ingresos de vecino</h4>
        
        <form action="Controlador_reportes" method="get">
            <div class="col-md-offset-2 col-md-2">
                <label>Vecino</label>
                <input id='action' name ='action' type="hidden" value="vecino"/>
                <select id="vecino" name="vecino" class="form-control">
                    <option>Seleccione</option>
                    <c:forEach var="item" items="${lista_vecinos}">
                        <option value="${item.id_vecino}">${item.nombre} ${item.apellidos}</option>
                    </c:forEach>                    
                </select>                
            </div>
		<div class="col-md-2">
		<label for="tipo">De fecha inicio:</label>
		<input type="date" class="form-control" name="fecha_inicio2">
		</div>	
		<div class="col-md-2">
			<label for="tipo"> a fecha fin:</label>
		<input type="date" class="form-control" name="fecha_fin2">
		</div>
		<div class="col-md-2"><br>                	
                    <input type="submit" class="btn btn-primary" value="Buscar" style="width: 100%"/>
		</div>
        </form>
</section>
</body>
</html>
