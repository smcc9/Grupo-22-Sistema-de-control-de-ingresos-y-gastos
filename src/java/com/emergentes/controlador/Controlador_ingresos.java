package com.emergentes.controlador;
import com.emergentes.dao.CategoriaDAO;
import com.emergentes.dao.CategoriaDAOimpl;
import com.emergentes.dao.IngresosDAO;
import com.emergentes.dao.IngresosDAOimpl;
import com.emergentes.dao.VecinosDAO;
import com.emergentes.dao.VecinosDAOimpl;
import com.emergentes.modelo.Categorias;
import com.emergentes.modelo.Ingresos;
import com.emergentes.modelo.Vecinos;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Controlador_ingresos", urlPatterns = {"/Controlador_ingresos"})
public class Controlador_ingresos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {            
            IngresosDAO dao = new IngresosDAOimpl();
            VecinosDAO dao2 = new VecinosDAOimpl();
            CategoriaDAO dao3 = new CategoriaDAOimpl();
            int id;
            Ingresos ing = new Ingresos();
            
            String action = (request.getParameter("action")!=null) ? request.getParameter("action") : "view";
            System.out.println("La action es :"+action);
            switch(action){
                case "add":
                    request.setAttribute("ingreso", ing);
                    request.getRequestDispatcher("info_ingreso.jsp").forward(request, response);
                    break;
                case "edit":
                    System.out.println("Controlador opcion editar");
                    id = Integer.parseInt(request.getParameter("id"));
                    ing = dao.getId_ingreso(id);
                    List<Vecinos> lista1_edit= dao2.getAll_vecino();
                    List<Categorias> lista2_edit= dao3.getAll_categoria();
                    request.setAttribute("ingreso", ing);
                    request.setAttribute("vecino", lista1_edit);                    
                    request.setAttribute("categoria", lista2_edit);  
                    request.getRequestDispatcher("info_ingreso.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete_ingreso(id);
                    response.sendRedirect("Controlador_ingresos");
                    break;
                case "edit_reporte_vecino":
                    System.out.println("EDITANDO REPORTE INGRESO VECINO");
                    id = Integer.parseInt(request.getParameter("id"));
                    ing = dao.getId_ingreso(id);
                    List<Vecinos> lista_vecinos_reporte_vec = dao2.getAll_vecino();
                    List<Categorias> lista_categorias_reporte_vec = dao3.getAll_categoria();
                    request.setAttribute("ingreso", ing);
                    request.setAttribute("vecino", lista_vecinos_reporte_vec);
                    request.setAttribute("categoria", lista_categorias_reporte_vec);  
                    request.getRequestDispatcher("modifica_ingreso_vecino.jsp").forward(request, response);
                    break;                    
                case "edit_reporte":
                    System.out.println("Editando desde reportes");
                    id = Integer.parseInt(request.getParameter("id"));
                    ing = dao.getId_ingreso(id);
                    List<Vecinos> lista_vecinos_reporte = dao2.getAll_vecino();
                    List<Categorias> lista_categorias_reporte = dao3.getAll_categoria();
                    request.setAttribute("ingreso", ing);
                    request.setAttribute("vecino", lista_vecinos_reporte);                    
                    request.setAttribute("categoria", lista_categorias_reporte);  
                    request.getRequestDispatcher("info_ingreso_reporte.jsp").forward(request, response);
                    break;
                    
                case "actualizar_ingreso_vecino":        
                    System.out.println("EDITANDO INGRESO DESDE REPORTES POR VECINO");
                    id = Integer.parseInt(request.getParameter("id"));  
                    String descripcion = request.getParameter("descripcion");
                    int categoria = Integer.parseInt(request.getParameter("categoria"));
                    int aux_id_vecino = Integer.parseInt(request.getParameter("vecino"));
        
                    SimpleDateFormat formato_vec = new SimpleDateFormat("yyyy-MM-dd");
                    String fe = request.getParameter("fecha");
                    Date fecha;
                    java.util.Date nfecha = null;
                    try {
                        nfecha = formato_vec.parse(fe);
                    } catch (ParseException ex) {
                        Logger.getLogger(Controlador_ingresos.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                    fecha = new java.sql.Date(nfecha.getTime());
                    System.out.println(fecha+"ESTA ES LA FECHA");
        
                    Double monto = Double.parseDouble(request.getParameter("monto"));
                    int vecino = Integer.parseInt(request.getParameter("vecino"));
                    System.out.println("-----------------------");
                    System.out.println("----="+id);
                    System.out.println("----="+descripcion);
                    System.out.println("----="+categoria);
                    System.out.println("----="+fecha);
                    System.out.println("----="+monto);
                    System.out.println("----="+vecino);
                    System.out.println("-----------------------");
                    //Ingresos ing = new Ingresos();
        
                    ing.setId_ingreso(id);
                    ing.setDescripcion(descripcion);
                    ing.setCategoria(categoria);
                    ing.setFecha(fecha);
                    ing.setMonto(monto);        
                    ing.setId_vecino(vecino);        
                    ing.setVecino(vecino);        

        if (id == 0) {
            try {
                dao.insert_ingreso(ing);
                response.sendRedirect("Controlador_ingresos");
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
            System.out.println("SESION EXISTENTE");
        }else{
            aux = "NOOK";
        }
        if (aux.equals("OK")) { 
            System.out.println("OK OK OK OK oK");
        String aux1 = (String) ses.getAttribute("fecha1");
        String aux2 = (String) ses.getAttribute("fecha2");
       // String aux4 = (String) ses.getAttribute("vecino_id");
        String aux4 =String.valueOf(ses.getAttribute("vecino_id"));  
        //////////////////////////////////////////
        int id_vecino = Integer.parseInt(aux4);
        ///////////////////////////////////////
        System.out.println("LA INFORMACION DE LA SESION :"+aux1);
        System.out.println("LA INFORMACION DE LA SESION :"+aux2);
        System.out.println("LA INFORMACION DE LA SESION :"+id_vecino);
        String fe1 = aux1;
        String fe2 = aux2;
        Date fecha1;
        Date fecha2;
        java.util.Date nfecha1 = null;
        java.util.Date nfecha2 = null;
        try {
            nfecha1 = formato_vec.parse(fe1);//fe1
            nfecha2 = formato_vec.parse(fe2);//fe2
        } catch (ParseException ex) {
            Logger.getLogger(Controlador_ingresos.class.getName()).log(Level.SEVERE, null, ex);
        }       
        fecha1 = new java.sql.Date(nfecha1.getTime());
        fecha2 = new java.sql.Date(nfecha2.getTime());
        System.out.println("FECHAS CONVERTIDAS:"+fecha1+"---"+fecha2);
        try {
            try {
                dao.update_ingreso(ing);
                System.out.println("ACTUALIZADO EXITOSAMENTE");
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
                System.out.println("ERROR EN LA ACTUALIZACION");
            }                            
            List<Ingresos> lista2 = dao.getAll_ingreso_vecino(fecha1, fecha2, id_vecino);
            System.out.println("INFORMACION DE INGRESOS DE VECINO OBTENIDO EXITOSAMENTE");
            request.setAttribute("reporte_vecino", lista2);                  
            request.getRequestDispatcher("informe3.jsp").forward(request, response);               
        } catch (Exception ex) {
            System.out.println("error"+ ex.getMessage());
        }
            response.sendRedirect("informe3.jsp");            
        }else{            
            dao.update_ingreso(ing);
            response.sendRedirect("Controlador_ingresos");
        }                                                                                                              
        } catch (Exception e) {
            System.out.println("error"+ e.getMessage());
        }            
        }                    
                    
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
                    dao.delete_ingreso(id);
                } catch (Exception e) {
                    System.out.println("error"+ e.getMessage());
                }                
                List<Ingresos> lista2 = dao.getAll_ingreso_fechas(fecha1, fecha2, aux3);
                request.setAttribute("reporte2", lista2);                  
                request.getRequestDispatcher("informe2.jsp").forward(request, response);               
                response.sendRedirect("informe2.jsp");            
                }else{                               
                    dao.delete_ingreso(id);
                    response.sendRedirect("Controlador_ingresos");
                }                                                                                                              
                } catch (Exception e) {
                    System.out.println("error"+ e.getMessage());
                }
                
                    break;
                case "delete_reporte_vecino":
                    id = Integer.parseInt(request.getParameter("id"));
                try {           
                    HttpSession ses = request.getSession();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");                    
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
                        String aux4 =String.valueOf(ses.getAttribute("vecino_id"));  
                        int id_vecino = Integer.parseInt(aux4);                                        
                        String fe1 = aux1;
                        String fe2 = aux2;
                        Date fecha1;
                        Date fecha2;
                        java.util.Date nfecha1 = null;
                        java.util.Date nfecha2 = null;
                try {
                    nfecha1 = format.parse(fe1);
                    nfecha2 = format.parse(fe2);
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador_ingresos.class.getName()).log(Level.SEVERE, null, ex);
                }       
                fecha1 = new java.sql.Date(nfecha1.getTime());
                fecha2 = new java.sql.Date(nfecha2.getTime());            

                try {
                    dao.delete_ingreso(id);
                } catch (Exception e) {
                    System.out.println("error"+ e.getMessage());
                }                
                List<Ingresos> lista2 = dao.getAll_ingreso_vecino(fecha1, fecha2, id_vecino);
                
                request.setAttribute("reporte_vecino", lista2);
                request.getRequestDispatcher("informe3.jsp").forward(request, response);               
                response.sendRedirect("informe3.jsp");            
                }else{                               
                    dao.delete_ingreso(id);
                    response.sendRedirect("Controlador_ingresos");
                }                                                                                                              
                } catch (Exception e) {
                    System.out.println("error"+ e.getMessage());
                }
                
                    break;
                    
                default:
                    List<Ingresos> lista = dao.getAll_ingreso();
                    List<Vecinos> lista2= dao2.getAll_vecino();
                    List<Categorias> lista3= dao3.getAll_categoria();
                    request.setAttribute("ingreso", lista);
                    request.setAttribute("vecino", lista2);                    
                    request.setAttribute("categoria", lista3);                    
                    request.getRequestDispatcher("frmingreso.jsp").forward(request, response);
                    break;
                
            }
        } catch (Exception e) {
            System.out.println("Error"+ e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        IngresosDAO dao = new IngresosDAOimpl();
        int id = Integer.parseInt(request.getParameter("id"));  
        String descripcion = request.getParameter("descripcion");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int aux_id_vecino = Integer.parseInt(request.getParameter("vecino"));
        
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
        int vecino = Integer.parseInt(request.getParameter("vecino"));
        
        Ingresos ing = new Ingresos();
        
        ing.setId_ingreso(id);
        ing.setDescripcion(descripcion);
        ing.setCategoria(categoria);
        ing.setFecha(fecha);
        ing.setMonto(monto);        
        ing.setId_vecino(vecino);        
        ing.setVecino(vecino);        

        if (id == 0) {
            try {
                dao.insert_ingreso(ing);
                response.sendRedirect("Controlador_ingresos");
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
                dao.update_ingreso(ing);
            } catch (Exception e) {
                System.out.println("error"+ e.getMessage());
            }                
            List<Ingresos> lista2 = dao.getAll_ingreso_fechas(fecha1, fecha2, aux3);
            request.setAttribute("reporte2", lista2);                  
            request.getRequestDispatcher("informe2.jsp").forward(request, response);               
        } catch (Exception ex) {
            System.out.println("error"+ ex.getMessage());
        }
            response.sendRedirect("informe2.jsp");            
        }else{            
            dao.update_ingreso(ing);
            response.sendRedirect("Controlador_ingresos");
        }                                                                                                              
        } catch (Exception e) {
            System.out.println("error"+ e.getMessage());
        }            
        }
    }
}
