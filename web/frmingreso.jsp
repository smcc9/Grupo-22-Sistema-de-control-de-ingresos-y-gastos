<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
                        <%
                        String msg = (String) session.getAttribute("reporte_i_g");
                        if (msg!=null) {                               
                        //session.invalidate();
                          session.removeAttribute("reporte_i_g");
                        }
                        %>
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
	<div class="centro"><h1>Ingresos</h1></div>
	<hr>
	<br>
	<form action="Controlador_ingresos" method="post">
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
            </div><br>           
		<div class="col-md-1"></div>
		<div class="col-md-6">
                    <input type="hidden" name="id" value="0"/>
		<label for="descripcion">Descripcion</label>
		<textarea name="descripcion" id="descripcion" cols="30" rows="10" class="form-control" placeholder="Ingresar descripcion"></textarea>	
		</div>
		<div class="col-md-3">
		<label for="tipo">Tipo o categoria:</label>
		<select name="categoria" id="categoria" class="form-control">
                    <c:forEach var="item_cat" items="${categoria}">
                        <c:if test="${item_cat.operacion == 'INGRESO'}">
                        <option value="${item_cat.id_categoria}">${item_cat.nombre_categoria}</option>            
                        </c:if>
                    
                    </c:forEach>
                    
		</select>
		<label for="tipo">Fecha:</label>
		<input type="date" name="fecha" class="form-control">
		<label for="tipo">Monto:</label>
		<input type="number" name="monto" class="form-control" placeholder="0">
		<label for="tipo">Vecino:</label>
		<select name="vecino" id="vecino" class="form-control">
                    <c:forEach var="item_vecino" items="${vecino}" >
                        <option value="${item_vecino.id_vecino}">${item_vecino.nombre}${item_vecino.apellidos}</option>
                    </c:forEach>			
		</select><br>
		<input type="submit" name="" class="btn btn-primary" value="Registrar" style="width: 100%;">	
		</div>	
	</div>		
	</form>
	<div class="row">
		<%--<div class="col-md-1"></div>--%>
                <div class="centro"><h3>Ingresos del dia</h3></div>
		<div class="col-md-11">
			<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Descripcion</th>
						<th>Categoria</th>
						<th>Fecha</th>
						<th>Monto</th>
						<th>Vecino</th>
						<th></th>
						<th></th>

					</tr>
				</thead>
				<tbody>
                                    <c:forEach var="item_ing" items="${ingreso}">
 					<tr>
					<td>${item_ing.descripcion}</td>
					<td>${item_ing.nombre_categoria}</td>
					<td>${item_ing.fecha}</td>
					<td>${item_ing.monto} /Bs</td>
					<td>${item_ing.nombre} ${item_ing.apellidos}</td>
        <td><a href="Controlador_ingresos?action=edit&id=${item_ing.id_ingreso}">Editar</a></td>
        <td><a href="Controlador_ingresos?action=delete&id=${item_ing.id_ingreso}">Eliminar</a></td>
					</tr>                                       
                                    </c:forEach>
				</tbody>
			</table>				
			</div>

		</div>
	</div>

</section>
</body>
</html>