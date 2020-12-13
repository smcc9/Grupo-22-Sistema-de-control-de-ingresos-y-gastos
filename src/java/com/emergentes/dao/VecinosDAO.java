package com.emergentes.dao;

import com.emergentes.modelo.Vecinos;
import java.util.List;

public interface VecinosDAO {
    public void insert_vecino(Vecinos vecino) throws Exception;
    public void update_vecino(Vecinos vecino) throws Exception;
    public void delete_vecino(int id) throws Exception;
    public Vecinos getId_vecino(int id) throws Exception;
    public List<Vecinos> getAll_vecino() throws Exception;     
}
