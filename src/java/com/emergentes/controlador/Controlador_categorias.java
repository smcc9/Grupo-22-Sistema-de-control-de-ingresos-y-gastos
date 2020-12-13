package com.emergentes.controlador;

import com.emergentes.dao.CategoriaDAO;
import com.emergentes.dao.CategoriaDAOimpl;
import com.emergentes.modelo.Categorias;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Controlador_categorias", urlPatterns = {"/Controlador_categorias"})
public class Controlador_categorias extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {            
            CategoriaDAO dao = new CategoriaDAOimpl();                       
            int id;
            Categorias cat = new Categorias();
            
            String action = (request.getParameter("action")!=null) ? request.getParameter("action") : "view";
            switch(action){
                case "add":
                    request.setAttribute("categoria", cat);
                    request.getRequestDispatcher("info_categoria.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    cat = dao.getId_categoria(id);
                    request.setAttribute("categoria", cat);
                    request.getRequestDispatcher("info_categoria.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete_categoria(id);              
                    response.sendRedirect("Controlador_categorias");
                    break;
                default:
                    List<Categorias> lista = dao.getAll_categoria();
                    request.setAttribute("categoria", lista);
                    request.getRequestDispatcher("frmcategoria.jsp").forward(request, response);
                    break;
                
            }
        } catch (Exception e) {
            System.out.println("Error"+ e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaDAO dao = new CategoriaDAOimpl();
        int id = Integer.parseInt(request.getParameter("id"));  
        String nombre_categoria = request.getParameter("nombre_categoria");
        String operacion = request.getParameter("operacion");
       
        Categorias cat = new Categorias();
        
        cat.setId_categoria(id);
        cat.setNombre_categoria(nombre_categoria);
        cat.setOperacion(operacion);        
      
        if (id == 0) {
            try {
                dao.insert_categoria(cat);
                response.sendRedirect("Controlador_categorias");
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
            }
        }else{
            try {
                dao.update_categoria(cat);
                response.sendRedirect("Controlador_categorias");
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
            }            
        }  
    }
}
