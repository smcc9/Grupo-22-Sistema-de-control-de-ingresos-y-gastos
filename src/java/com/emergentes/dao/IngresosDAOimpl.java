package com.emergentes.dao;

import com.emergentes.modelo.Ingresos;
import com.emergentes.utiles.conexionBD;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngresosDAOimpl extends conexionBD implements IngresosDAO{

    @Override
    public void insert_ingreso(Ingresos ingreso) throws Exception {
        try {
            this.conectar();
            String sql = "insert into ingresos (descripcion, categoria, fecha, monto, vecino) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, ingreso.getDescripcion());
            ps.setInt(2, ingreso.getCategoria());
            ps.setDate(3, ingreso.getFecha());
            ps.setDouble(4, ingreso.getMonto());
            ps.setInt(5, ingreso.getId_vecino());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }          
    }

    @Override
    public void update_ingreso(Ingresos ingreso) throws Exception {
    try {
            this.conectar();
            String sql = "update ingresos set descripcion = ?, categoria = ?, fecha = ?, monto = ?, vecino = ? where id_ingreso = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, ingreso.getDescripcion());
            ps.setInt(2, ingreso.getCategoria());
            ps.setDate(3, ingreso.getFecha());
            ps.setDouble(4, ingreso.getMonto());
            
            ps.setInt(5, ingreso.getVecino());            
            ps.setInt(6, ingreso.getId_ingreso());
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            throw e;
        } finally{
            //this.desconectar();
        }   
    }

    @Override
    public void delete_ingreso(int id) throws Exception {
        try {
            this.conectar();
            String sql = "delete from ingresos where id_ingreso =?";
            PreparedStatement ps= this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            //this.desconectar();
        }  
    }

    @Override
    public Ingresos getId_ingreso(int id) throws Exception {
           Ingresos ing = new Ingresos();
        try {
            this.conectar();
            //String sql = "select * from ingresos i, vecinos v where i.id_ingreso = ? and i.vecino = v.id_vecino";
            String sql = "select * from ingresos i, vecinos v, categorias c where i.id_ingreso = ? and i.vecino = v.id_vecino and c.id_categoria = i.categoria";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                ing.setNombre_categoria(rs.getString("nombre_categoria"));
                ing.setId_ingreso(rs.getInt("id_ingreso"));
                ing.setDescripcion(rs.getString("descripcion"));
                ing.setCategoria(rs.getInt("categoria"));
                ing.setFecha(rs.getDate("fecha"));
                ing.setMonto(rs.getDouble("monto"));
                ing.setNombre(rs.getString("nombre"));
                ing.setApellidos(rs.getString("apellidos"));
                ////////////////////////////////////////
                ing.setId_vecino(rs.getInt("vecino"));
            } 
            System.out.println("Ingreso obtenido exitosamente");
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return ing;  
    }

    @Override
    public List<Ingresos> getAll_ingreso() throws Exception {
                List<Ingresos> lista = null;
        try {
            this.conectar();
            String sql = "select * from categorias c, ingresos i, vecinos v where i.vecino = v.id_vecino and c.id_categoria=i.categoria and fecha = CURDATE() ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Ingresos>();
            while (rs.next()) {
                
                Ingresos ing = new Ingresos();
                
                ing.setId_ingreso(rs.getInt("id_ingreso"));
                ing.setDescripcion(rs.getString("descripcion"));
                ing.setCategoria(rs.getInt("categoria"));
                ing.setNombre_categoria(rs.getString("nombre_categoria"));
                ing.setFecha(rs.getDate("fecha"));
                ing.setMonto(rs.getDouble("monto"));
                ing.setNombre(rs.getString("nombre"));
                ing.setApellidos(rs.getString("apellidos"));
                
                lista.add(ing);
            }
            
        } catch (Exception e) {
            throw e;
        }finally{
            this.desconectar();
        }
        return lista; 
    }

    @Override
    public List<Ingresos> getAll_ingreso_fechas(Date fecha1, Date fecha2, String operacion) throws Exception {
        List<Ingresos> lista = null;
        try {
            this.conectar();
            if (operacion.equals("INGRESOS")) {
            String sql = "select c.*, i.*, v.nombre,v.apellidos from categorias c, ingresos i, vecinos v where c.id_categoria = i.categoria and v.id_vecino= i.vecino and i.fecha between ? and ? order by i.fecha";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setDate(1, fecha1);
            ps.setDate(2, fecha2);
            
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Ingresos>();
            while (rs.next()) {                
                Ingresos ing = new Ingresos();                
                ing.setId_ingreso(rs.getInt("id_ingreso"));
                ing.setDescripcion(rs.getString("descripcion"));
                ing.setCategoria(rs.getInt("id_categoria"));
                ing.setNombre_categoria(rs.getString("nombre_categoria"));
                ing.setFecha(rs.getDate("fecha"));
                ing.setMonto(rs.getDouble("monto"));
                ing.setNombre(rs.getString("nombre"));
                ing.setApellidos(rs.getString("apellidos"));
                lista.add(ing);
            }                
            }            
        } catch (Exception e) {
            throw e;
        }finally{
            this.desconectar();
        }
        return lista; 
    }

    @Override
    public List<Ingresos> getAll_ingreso_vecino(Date fecha1, Date fecha2, int vecino) throws Exception {
        List<Ingresos> lista = null;
        try {
            this.conectar();
            String sql = "select c.*, i.*, v.nombre,v.apellidos from categorias c, ingresos i, vecinos v where c.id_categoria = i.categoria and v.id_vecino= i.vecino and i.vecino = ? and i.fecha between ? and ? order by i.fecha DESC";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, vecino);
            ps.setDate(2, fecha1);
            ps.setDate(3, fecha2);
            
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Ingresos>();
            while (rs.next()) {                
                Ingresos ing = new Ingresos();                
                ing.setId_ingreso(rs.getInt("id_ingreso"));
                ing.setDescripcion(rs.getString("descripcion"));
                ing.setCategoria(rs.getInt("id_categoria"));
                ing.setNombre_categoria(rs.getString("nombre_categoria"));
                ing.setFecha(rs.getDate("fecha"));
                ing.setMonto(rs.getDouble("monto"));
                ing.setNombre(rs.getString("nombre"));
                ing.setApellidos(rs.getString("apellidos"));
                lista.add(ing);
            }                            
        } catch (Exception e) {
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;         
    }
    @Override
    public double total_ingresos() throws Exception {  
        double total=0.0;
        try {
            this.conectar();
            Statement st = conn.createStatement();
            String sql = "select SUM(monto)as monto from ingresos";
            //PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                total = rs.getDouble("monto");
                System.out.println("MONTO TOTAL"+ total);
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            //this.desconectar();
        }
        return total;

    }
    @Override
    public double total_ingresos_mes() throws Exception {
    
        double total=0.0;
        try {
            this.conectar();
            Statement st = conn.createStatement();
            String sql = "select SUM(monto)as monto from ingresos WHERE MONTH(fecha) = MONTH(CURRENT_DATE())";
            //PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                total = rs.getDouble("monto");
                System.out.println("MONTO TOTAL"+ total);
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            //this.desconectar();
        }
        return total;
        
    }
    
}
