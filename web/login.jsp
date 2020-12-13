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
<body class="body_oscuro">
	<div class="">                
		<div>
                    <div class="centro" style="top: 10%; left: 39%; position: absolute">
                    <img src="img/logo1.png">                     
                    </div>
                    <div class="centro" style="top: 20%; left: 35%; position: absolute; color: #FFF; ">
                    <h4 class="centro">SISTEMA DE CONTROL DE INGRESOS Y GASTOS</h4>
                    </div>                    
			<form action="Controlador_sesion" method="post" class="login container">
			<h1>Login</h1>
			<div class="row login_contenedor">
			<input type="text" name="usuario" value="" class="form-control" placeholder="Usuario"><br>
			<input type="password" name="password" value="" class="form-control" placeholder="Contraseña">
			<br>

                        <%
                        String msg = (String) session.getAttribute("error");
                        if (msg != null) {                               
                        %>
                        <div class="alert alert-danger alert-dismissible">
                        <a href="" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong><%= msg %></strong>
                        </div>
                        <%
                        session.invalidate();
                        }
                        %>
			<input type="submit" class="btn btn-primary form-control" value="Ingresar" />				
			</div>
			</form>
		</div>
	</div>
</body>
</html>