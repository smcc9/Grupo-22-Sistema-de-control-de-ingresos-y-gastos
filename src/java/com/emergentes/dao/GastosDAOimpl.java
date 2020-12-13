package com.emergentes.dao;

import com.emergentes.modelo.Gastos;
import com.emergentes.utiles.conexionBD;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GastosDAOimpl extends conexionBD implements GastosDAO {

    @Override
    public void insert_gasto(Gastos gasto) throws Exception {
        try {
            this.conectar();
            String sql = "insert into gastos (descripcion, categoria, fecha, monto) values (?, ?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, gasto.getDescripcion());
            ps.setInt(2, gasto.getCategoria());
            ps.setDate(3, gasto.getFecha());
            ps.setDouble(4, gasto.getMonto());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }           
    }

    @Override
    public void update_gasto(Gastos gasto) throws Exception {
    try {
            this.conectar();
            String sql = "update gastos set descripcion = ?, categoria = ?, fecha = ?, monto = ? where id_gasto = ? ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, gasto.getDescripcion());
            ps.setInt(2, gasto.getCategoria());
            ps.setDate(3, gasto.getFecha());
            ps.setDouble(4, gasto.getMonto());
            ps.setInt(5, gasto.getId_gastos());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            //this.desconectar();
        }   
    }

    @Override
    public void delete_gasto(int id) throws Exception {
        try {
            this.conectar();
            String sql = "delete from gastos where id_gasto =?";
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
    public Gastos getId_gasto(int id) throws Exception {
           Gastos gas = new Gastos();
        try {
            this.conectar();
            String sql = "select * from gastos g, categorias c where g.id_gasto = ? and c.id_categoria = g.categoria";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                gas.setId_gastos(rs.getInt("id_gasto"));
                gas.setDescripcion(rs.getString("descripcion"));
                gas.setNombre_categoria(rs.getString("nombre_categoria"));
                gas.setFecha(rs.getDate("fecha"));
                gas.setMonto(rs.getDouble("monto"));
                gas.setId_categoria(rs.getInt("id_categoria"));
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return gas;  
    }

    @Override
    public List<Gastos> getAll_gasto() throws Exception {
                List<Gastos> lista = null;
        try {
            this.conectar();
            String sql = "select * from categorias c, gastos g where c.id_categoria = g.categoria and fecha = CURDATE() ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();                                          
            
            lista = new ArrayList<Gastos>();
            while (rs.next()) {                
                Gastos gas = new Gastos();                
                gas.setId_gastos(rs.getInt("id_gasto"));
                gas.setDescripcion(rs.getString("descripcion"));
                gas.setCategoria(rs.getInt("id_categoria"));
                gas.setNombre_categoria(rs.getString("nombre_categoria"));
                gas.setFecha(rs.getDate("fecha"));
                gas.setMonto(rs.getDouble("monto"));
                
                lista.add(gas);
            }
            
        } catch (Exception e) {
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;     
    }

    @Override
    public List<Gastos> getAll_gasto_fechas(Date fecha1, Date fecha2, String operacion) throws Exception {
        List<Gastos> lista = null;
        try {
            this.conectar();
            if (operacion.equals("GASTOS")) {
            String sql = "select * from categorias c, gastos g where c.id_categoria = g.categoria and g.fecha between ? and ? ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setDate(1, fecha1);
            ps.setDate(2, fecha2);
            
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Gastos>();
            while (rs.next()) {                
                Gastos gas = new Gastos();                
                gas.setId_gastos(rs.getInt("id_gasto"));
                gas.setDescripcion(rs.getString("descripcion"));
                gas.setCategoria(rs.getInt("id_categoria"));
                gas.setNombre_categoria(rs.getString("nombre_categoria"));
                gas.setFecha(rs.getDate("fecha"));
                gas.setMonto(rs.getDouble("monto"));                
                lista.add(gas);
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
    public Gastos getTotalgastos() throws Exception {
            System.out.println("TOTAL_GASTOS");
            Gastos gas = new Gastos();
        try {
            this.conectar();
            String sql = "SELECT SUM(monto) as monto FROM GASTOS";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();  

            if (rs != null) {
                System.out.println("SE EJECUTO LA CONSULTA");
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return gas;         
    }

    @Override
    public double total_gastos() throws Exception {
        double total=0.0;
        try {
            this.conectar();
            Statement st = conn.createStatement();
            String sql = "select SUM(monto)as monto from gastos";
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
    public double total_gastos_mes() throws Exception {
        double total=0.0;
        try {
            this.conectar();
            Statement st = conn.createStatement();
            String sql = "select SUM(monto)as monto from gastos WHERE MONTH(fecha) = MONTH(CURRENT_DATE())";            
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
