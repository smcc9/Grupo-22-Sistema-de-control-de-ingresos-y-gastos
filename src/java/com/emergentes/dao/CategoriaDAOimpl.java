
package com.emergentes.dao;

import com.emergentes.modelo.Categorias;
import com.emergentes.utiles.conexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOimpl extends conexionBD implements CategoriaDAO{

    @Override
    public void insert_categoria(Categorias categoria) throws Exception {
        try {
            this.conectar();
            String sql = "insert into categorias (nombre_categoria, operacion) values (?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre_categoria());
            ps.setString(2, categoria.getOperacion());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }         
    }
    @Override
    public void update_categoria(Categorias categoria) throws Exception {
    try {
            this.conectar();
            String sql = "update categorias set nombre_categoria = ?, operacion = ? where id_categoria = ? ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre_categoria());
            ps.setString(2, categoria.getOperacion());            
            ps.setInt(3, categoria.getId_categoria());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }   
    }

    @Override
    public void delete_categoria(int id) throws Exception {
        try {
            this.conectar();
            String sql = "delete from categorias where id_categoria =?";
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
    public Categorias getId_categoria(int id) throws Exception {
           Categorias cat = new Categorias();
        try {
            this.conectar();
            String sql = "select * from categorias where id_categoria = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                cat.setId_categoria(rs.getInt("id_categoria"));
                cat.setNombre_categoria(rs.getString("nombre_categoria"));
                cat.setOperacion(rs.getString("operacion"));                
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return cat;   
    }

    @Override
    public List<Categorias> getAll_categoria() throws Exception {
                List<Categorias> lista = null;
        try {
            this.conectar();
            String sql = "select * from categorias";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Categorias>();
            while (rs.next()) {
                
                Categorias cat = new Categorias();
                
                cat.setId_categoria(rs.getInt("id_categoria"));
                cat.setNombre_categoria(rs.getString("nombre_categoria"));
                cat.setOperacion(rs.getString("operacion"));
                lista.add(cat);
            }
            System.out.println("Lista de categorias obtenida exitosamente");
        } catch (Exception e) {
            System.out.println("No se pudo obtener la lista de categorias");
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;   
    }    
}
