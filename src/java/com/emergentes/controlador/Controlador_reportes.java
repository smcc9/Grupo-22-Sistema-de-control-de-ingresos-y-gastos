
package com.emergentes.controlador;

import com.emergentes.dao.GastosDAO;
import com.emergentes.dao.GastosDAOimpl;
import com.emergentes.dao.IngresosDAO;
import com.emergentes.dao.IngresosDAOimpl;
import com.emergentes.dao.VecinosDAO;
import com.emergentes.dao.VecinosDAOimpl;
import com.emergentes.modelo.Gastos;
import com.emergentes.modelo.Ingresos;
import com.emergentes.modelo.Vecinos;
import com.emergentes.utiles.conexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/*import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;*/

@WebServlet(name = "Controlador_reportes", urlPatterns = {"/Controlador_reportes"})
public class Controlador_reportes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            conexionBD conn = new conexionBD();   
            IngresosDAO dao = new IngresosDAOimpl();
            GastosDAO dao3 = new GastosDAOimpl();
            VecinosDAO dao2 = new VecinosDAOimpl();
         try {            
         
            String action = (request.getParameter("action")!=null) ? request.getParameter("action") : "view";
            switch(action){
                
                case "vecino":
        int vecino = Integer.parseInt(request.getParameter("vecino"));
        System.out.println("ESTA ES EL ID DEL VECINO EN LA SESION "+vecino);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fe1 = request.getParameter("fecha_inicio2");
        String fe2 = request.getParameter("fecha_fin2");

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
        System.out.println("DE FECHA :"+fecha1+" A FECHA A :"+fecha2);        
        try {
            List<Ingresos> lista = dao.getAll_ingreso_vecino(fecha1, fecha2, vecino);

        HttpSession ses = request.getSession();               
        try {
            if (fecha1!=null && fecha2!=null){
                System.out.println("SE CREO LA SESION PARA LA CONSULTA INGRESOS DE VECINOS");
                ses.setAttribute("reporte_i_g", "OK");
                ses.setAttribute("fecha1", fe1);//fe1
                ses.setAttribute("fecha2", fe2);//fe2
                ses.setAttribute("vecino_id", vecino);
                System.out.println("---SESION----:"+fecha1);
                System.out.println("---SESION----:"+fecha2);
                System.out.println("---SESION----:"+vecino);
            }          
        } catch (Exception e) {
                ses.setAttribute("error_sesion", "no_identificado");
                System.out.println("ERROR EN LA CONSULTA para reporte con sesion");
        }                
            request.setAttribute("reporte_vecino", lista);
            request.getRequestDispatcher("informe3.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("error"+ ex.getMessage());
        }
            response.sendRedirect("informe3.jsp");
                    break;
                    
                case "edit":

                    break;
                case "reporte1":

                    break;
                default:
                    
                    List<Vecinos> lista2= dao2.getAll_vecino();
                    request.setAttribute("lista_vecinos", lista2);                                                           
                    
                    
                    double resultado_total = dao.total_ingresos();
                    double resultado_total_mes = dao.total_ingresos_mes();
                    System.out.println("EL MONTO DESDE EL CONTROLADOR"+ resultado_total);
                    request.setAttribute("ingreso_total", resultado_total);
                    request.setAttribute("ingreso_total_mes", resultado_total_mes);
                    
                    double resultado_total_gasto = dao3.total_gastos();
                    double resultado_total_mes_gasto = dao3.total_gastos_mes();
                    request.setAttribute("gasto_total", resultado_total_gasto);
                    request.setAttribute("gasto_total_mes", resultado_total_mes_gasto);
                                        
                    request.getRequestDispatcher("frmreportes.jsp").forward(request, response);
                    
                    
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
        IngresosDAO dao2 = new IngresosDAOimpl();
        String operacion = request.getParameter("operacion");  
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fe1 = request.getParameter("fecha_inicio");
        String fe2 = request.getParameter("fecha_fin");

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
        
        System.out.println("Reporte de :"+fecha1+" a fecha : "+fecha2);        
        
        Gastos gas = new Gastos();                      
        if (operacion.equals("GASTOS")) {
            try {
                List<Gastos> lista = dao.getAll_gasto_fechas(fecha1,fecha2,operacion);
                request.setAttribute("reporte1", lista); 
                
                HttpSession ses = request.getSession();
                
            try {
                if (fecha1 != null && fecha2 != null && operacion != null){
                    System.out.println("SE CREO LA SESION PARA LA CONSULTA REPORTE ");
                    ses.setAttribute("reporte_i_g", "OK");
                    ses.setAttribute("fecha1", fe1);
                    ses.setAttribute("fecha2", fe2);
                    ses.setAttribute("operacion", operacion);                
                    //response.sendRedirect("menu.jsp");
                }          
            } catch (Exception e) {
                    ses.setAttribute("error_sesion", "no_identificado");
                    //response.sendRedirect("login.jsp");
                    System.out.println("ERROR EN CREAR SESION PARA CONSULTA REPORTE(GASTOS)");            
             }                          
                request.getRequestDispatcher("informe1.jsp").forward(request, response);               
            } catch (Exception ex) {
                System.out.println("error"+ ex.getMessage());
            }
                response.sendRedirect("informe1.jsp");
        }else{
            if (operacion.equals("INGRESOS")) {
            try {
                List<Ingresos> lista2 = dao2.getAll_ingreso_fechas(fecha1,fecha2,operacion);
                request.setAttribute("reporte2", lista2);  
                                
                HttpSession ses = request.getSession();               
            try {
                if (fecha1!=null && fecha2!=null && operacion !=null){
                    System.out.println("SES CREO LA SESION PARA LA CONSULTA REPORTE (INGRESOS)");
                    ses.setAttribute("reporte_i_g", "OK");
                    ses.setAttribute("fecha1", fe1);
                    ses.setAttribute("fecha2", fe2);
                    ses.setAttribute("operacion", operacion);                                    
                }          
            } catch (Exception e) {
                    ses.setAttribute("error_sesion", "no_identificado");
                    System.out.println("ERROR AL CREAR LA SESION PARA LA CONMSULTA REPORTE");            
            }                
                   request.getRequestDispatcher("informe2.jsp").forward(request, response);               
            } catch (Exception ex) {
                System.out.println("error"+ ex.getMessage());
            }
            response.sendRedirect("informe2.jsp");
            }else{
                try {
                    response.sendRedirect("Controlador_reportes");
                    } catch (Exception e) {
                    System.out.println("error"+ e.getMessage());
                    }          
                }                     
        }
    }
}
