package com.emergentes.controlador;

import com.emergentes.dao.CategoriaDAO;
import com.emergentes.dao.CategoriaDAOimpl;
import com.emergentes.dao.GastosDAO;
import com.emergentes.dao.GastosDAOimpl;
import com.emergentes.modelo.Categorias;
import com.emergentes.modelo.Gastos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controlador_gastos", urlPatterns = {"/Controlador_gastos"})
public class Controlador_gastos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {            
            GastosDAO dao = new GastosDAOimpl();
            CategoriaDAO dao3 = new CategoriaDAOimpl();
            int id;
            Gastos gas = new Gastos();
            
            String action = (request.getParameter("action")!=null) ? request.getParameter("action") : "view";
            switch(action){
                case "add":
                    request.setAttribute("gasto", gas);
                    request.getRequestDispatcher("info_gasto.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    gas = dao.getId_gasto(id);
                    List<Categorias> lista2_edit = dao3.getAll_categoria();
                    request.setAttribute("gasto", gas);                    
                    request.setAttribute("categoria", lista2_edit);  
                    request.getRequestDispatcher("info_gasto.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete_gasto(id);
                    response.sendRedirect("Controlador_gastos");
                    break;
                case "edit_reporte":
                    id = Integer.parseInt(request.getParameter("id"));
                    
                    gas = dao.getId_gasto(id);
                    List<Categorias> lista_categorias_reporte = dao3.getAll_categoria();
                    request.setAttribute("gasto", gas);                    
                    request.setAttribute("categoria", lista_categorias_reporte);  
                    request.getRequestDispatcher("info_gasto_reporte.jsp").forward(request, response);                    
                    break;
                case "delete_reporte":
                    id = Integer.parseInt(request.getParameter("id"));
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");                    
                try {           
                    HttpSession ses = request.getSession();
                    String aux = "";
                    aux = (String) ses.getAttribute("reporte_i_g");
                    
                    if (aux != null) {
                    aux = "OK";
                    }else{
                        aux = "NOOK";
                    }
                    if (aux.equals("OK")) {        
                        String aux1 =(String) ses.getAttribute("fecha1");
                        String aux2 =(String) ses.getAttribute("fecha2");
                        String aux3 =(String) ses.getAttribute("operacion");            
                        String fe1 = aux1;
                        String fe2 = aux2;
                        Date fecha1;
                        Date fecha2;
                        java.util.Date nfecha1 = null;
                        java.util.Date nfecha2 = null;
                try {
                    nfecha1 = formato.parse(fe1);
                    nfecha2 = formato.parse(fe2);
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador_ingresos.class.getName()).log(Level.SEVERE, null, ex);
                }       
                fecha1 = new java.sql.Date(nfecha1.getTime());
                fecha2 = new java.sql.Date(nfecha2.getTime());            

                try {
                    dao.delete_gasto(id);
                } catch (Exception e) {
                    System.out.println("error"+ e.getMessage());
                }                
                List<Gastos> lista2 = dao.getAll_gasto_fechas(fecha1, fecha2, aux3);
                request.setAttribute("reporte1", lista2);                  
                request.getRequestDispatcher("informe1.jsp").forward(request, response);               
                response.sendRedirect("informe1.jsp");            
                }else{                               
                    dao.delete_gasto(id);
                    response.sendRedirect("Controlador_gastos");
                }                                                                                                              
                } catch (Exception e) {
                    System.out.println("error"+ e.getMessage());
                }
                
                    break;
                default:
                    List<Gastos> lista = dao.getAll_gasto();
                    List<Categorias> lista3= dao3.getAll_categoria();
                    request.setAttribute("gasto", lista);                   
                    request.setAttribute("categoria", lista3);  
                                       
                    request.getRequestDispatcher("frmgasto.jsp").forward(request, response);
                    break;
                
            }
        } catch (Exception e) {
            System.out.println("Error"+ e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GastosDAO dao = new GastosDAOimpl();
        int id = Integer.parseInt(request.getParameter("id"));  
        String descripcion = request.getParameter("descripcion");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fe = request.getParameter("fecha");
        Date fecha;
        java.util.Date nfecha = null;
        try {
            nfecha = formato.parse(fe);
        } catch (ParseException ex) {
            Logger.getLogger(Controlador_ingresos.class.getName()).log(Level.SEVERE, null, ex);
        }       
        fecha = new java.sql.Date(nfecha.getTime());
        System.out.println(fecha+"ESTA ES LA FECHA");        
        Double monto = Double.parseDouble(request.getParameter("monto"));
        
        Gastos gas = new Gastos();        
        gas.setId_gastos(id);
        gas.setDescripcion(descripcion);
        gas.setCategoria(categoria);
        gas.setFecha(fecha);
        gas.setMonto(monto);                
        if (id == 0) {
            
            try {
                dao.insert_gasto(gas);
                response.sendRedirect("Controlador_gastos");
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
            }
            
        }else{
        try {
            
        HttpSession ses = request.getSession();
        String aux = "";
        aux = (String) ses.getAttribute("reporte_i_g");
        
        if (aux != null) {
            aux = "OK";
        }else{
            aux = "NOOK";
        }
        if (aux.equals("OK")) {        
        String aux1 =(String) ses.getAttribute("fecha1");
        String aux2 =(String) ses.getAttribute("fecha2");
        String aux3 =(String) ses.getAttribute("operacion");            
        String fe1 = aux1;
        String fe2 = aux2;
        Date fecha1;
        Date fecha2;
        java.util.Date nfecha1 = null;
        java.util.Date nfecha2 = null;
        try {
            nfecha1 = formato.parse(fe1);
            nfecha2 = formato.parse(fe2);
        } catch (ParseException ex) {
            Logger.getLogger(Controlador_ingresos.class.getName()).log(Level.SEVERE, null, ex);
        }       
        fecha1 = new java.sql.Date(nfecha1.getTime());
        fecha2 = new java.sql.Date(nfecha2.getTime());            
        try {
            try {
                dao.update_gasto(gas);
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
            }                
            List<Gastos> lista2 = dao.getAll_gasto_fechas(fecha1, fecha2, aux3);
            request.setAttribute("reporte1", lista2);                  
            request.getRequestDispatcher("informe1.jsp").forward(request, response);               
        } catch (Exception ex) {
            System.out.println("error"+ ex.getMessage());
        }
            response.sendRedirect("informe1.jsp");            
        }else{            
            dao.update_gasto(gas);
            response.sendRedirect("Controlador_gastos");
        }                                                                                                              
        } catch (Exception e) {
            System.out.println("error"+ e.getMessage());
        }            
        }
    }
}
