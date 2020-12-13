package com.emergentes.controlador;

import com.emergentes.dao.UsuariosDAO;
import com.emergentes.dao.UsuariosDAOimpl;
import com.emergentes.modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controlador_sesion", urlPatterns = {"/Controlador_sesion"})
public class Controlador_sesion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        String login = (String) ses.getAttribute("login");
        if (login.equals("OK")) {
            ses.invalidate();
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("VALIDANDO A USUARIO");
        HttpSession ses = request.getSession();
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        UsuariosDAO dao = new UsuariosDAOimpl();
        Usuarios usu = new Usuarios();
        Usuarios usuresp = new Usuarios();
        usu.setUsuario(usuario);
        usu.setPassword(password);        
        try {
            usuresp = dao.getId_usuario(usuario, password);
            if (usuresp.getUsuario().equals(usuario) && usuresp.getPassword().equals(password)){
                System.out.println("Usuario Validado Exitosamente");
                ses.setAttribute("login", "OK");
                ses.setAttribute("usuario", usuario);
                ses.setAttribute("nombre", usuresp.getNombre());
                ses.setAttribute("apellidos", usuresp.getApellidos());
                response.sendRedirect("menu.jsp");
            }
        } catch (Exception e) {
                ses.setAttribute("error", "Usuario no identificado");
                response.sendRedirect("login.jsp");
                System.out.println("ERROR EN LA CONSULTA");            
        }
        
    }
}
