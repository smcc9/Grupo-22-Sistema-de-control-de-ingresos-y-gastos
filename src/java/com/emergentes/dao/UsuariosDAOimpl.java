package com.emergentes.dao;

import com.emergentes.modelo.Usuarios;
import com.emergentes.utiles.conexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuariosDAOimpl extends conexionBD implements UsuariosDAO{

    @Override
    public Usuarios getId_usuario(String usuario, String password) throws Exception {
           Usuarios usu = new Usuarios();
        try {
            this.conectar();
            String sql = "select * from usuarios where usuario = ? and password = ? ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                usu.setId_usuario(rs.getInt("id_usuario"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApellidos(rs.getString("apellidos"));
                usu.setCelular(rs.getInt("celular"));                
                usu.setUsuario(rs.getString("usuario"));                
                usu.setPassword(rs.getString("password"));
            }            
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return usu;            
    }  
}
