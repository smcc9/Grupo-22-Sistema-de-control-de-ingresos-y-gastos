
package com.emergentes.dao;

import com.emergentes.modelo.Vecinos;
import com.emergentes.utiles.conexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VecinosDAOimpl extends conexionBD implements VecinosDAO {

    @Override
    public void insert_vecino(Vecinos vecino) throws Exception {
        try {
            this.conectar();
            String sql = "insert into vecinos (nombre, apellidos, direccion, celular, lote, manzana) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, vecino.getNombre());
            ps.setString(2, vecino.getApellidos());
            ps.setString(3, vecino.getDireccion());
            ps.setInt(4, vecino.getCelular());
            ps.setInt(5, vecino.getLote());
            ps.setInt(6, vecino.getManzana());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }         
    }

    @Override
    public void update_vecino(Vecinos vecino) throws Exception {
    try {
            this.conectar();
            String sql = "update vecinos set nombre = ?, apellidos = ?, direccion = ?, celular = ?, lote = ?, manzana = ? where id_vecino = ? ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, vecino.getNombre());
            ps.setString(2, vecino.getApellidos());
            ps.setString(3, vecino.getDireccion());
            ps.setInt(4, vecino.getCelular());
            ps.setInt(5, vecino.getLote());
            ps.setInt(6, vecino.getManzana());
            ps.setInt(7, vecino.getId_vecino());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }       
    }

    @Override
    public void delete_vecino(int id) throws Exception {
        try {
            this.conectar();
            String sql = "delete from vecinos where id_vecino =?";
            PreparedStatement ps= this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }       
    }

    @Override
    public Vecinos getId_vecino(int id) throws Exception {
           Vecinos vec = new Vecinos();
        try {
            this.conectar();
            String sql = "select * from vecinos where id_vecino = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                vec.setId_vecino(rs.getInt("id_vecino"));
                vec.setNombre(rs.getString("nombre"));
                vec.setApellidos(rs.getString("apellidos"));
                vec.setDireccion(rs.getString("direccion"));
                vec.setCelular(rs.getInt("celular"));
                vec.setLote(rs.getInt("lote"));
                vec.setManzana(rs.getInt("manzana"));
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return vec;     
    }

    @Override
    public List<Vecinos> getAll_vecino() throws Exception {
                List<Vecinos> lista = null;
        try {
            this.conectar();
            String sql = "select * from vecinos";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Vecinos>();
            while (rs.next()) {
                
                Vecinos vec = new Vecinos();
                
                vec.setId_vecino(rs.getInt("id_vecino"));
                vec.setNombre(rs.getString("nombre"));
                vec.setApellidos(rs.getString("apellidos"));
                vec.setDireccion(rs.getString("direccion"));
                vec.setCelular(rs.getInt("celular"));
                vec.setLote(rs.getInt("lote"));
                vec.setManzana(rs.getInt("manzana"));
                lista.add(vec);
            }
            System.out.println("Listado de vecinos obtenido exitosamente");
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de vecinos");
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;          
    }
    
}
