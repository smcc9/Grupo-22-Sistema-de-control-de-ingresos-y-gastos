package com.emergentes.dao;

import com.emergentes.modelo.Usuarios;

public interface UsuariosDAO {
    public Usuarios getId_usuario(String usuario, String password) throws Exception;
}
