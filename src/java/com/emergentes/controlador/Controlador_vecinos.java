package com.emergentes.controlador;

import com.emergentes.dao.VecinosDAO;
import com.emergentes.dao.VecinosDAOimpl;
import com.emergentes.modelo.Vecinos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controlador_vecinos", urlPatterns = {"/Controlador_vecinos"})
public class Controlador_vecinos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {            
            VecinosDAO dao = new VecinosDAOimpl();                       
            int id;
            Vecinos vec = new Vecinos();
            
            String action = (request.getParameter("action")!=null) ? request.getParameter("action") : "view";
            switch(action){
                case "add":
                    request.setAttribute("vecino", vec);
                    request.getRequestDispatcher("info_vecino.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    vec = dao.getId_vecino(id);
                    request.setAttribute("vecino", vec);
                    request.getRequestDispatcher("info_vecino.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete_vecino(id);
                    //request.getRequestDispatcher("Inicio").forward(request, response);
                    response.sendRedirect("Controlador_vecinos");
                    break;
                default:
                    List<Vecinos> lista = dao.getAll_vecino();
                    request.setAttribute("vecino", lista);
                    request.getRequestDispatcher("frmvecino.jsp").forward(request, response);
                    break;
                
            }
        } catch (Exception e) {
            System.out.println("Error"+ e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VecinosDAO dao = new VecinosDAOimpl();
        int id = Integer.parseInt(request.getParameter("id"));  
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String direccion = request.getParameter("direccion");
        int celular = Integer.parseInt(request.getParameter("celular"));
        int lote = Integer.parseInt(request.getParameter("lote"));
        int manzana = Integer.parseInt(request.getParameter("manzana"));
        
        Vecinos vec = new Vecinos();
        
        vec.setId_vecino(id);
        vec.setNombre(nombre);
        vec.setApellidos(apellidos);
        vec.setDireccion(direccion);
        vec.setCelular(celular);
        vec.setLote(lote);
        vec.setManzana(manzana);
        
        if (id == 0) {
            try {
                dao.insert_vecino(vec);
                response.sendRedirect("Controlador_vecinos");
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
            }
        }else{
            try {
                dao.update_vecino(vec);
                response.sendRedirect("Controlador_vecinos");
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
            }            
        }          
    }
}
