
package com.emergentes.dao;

import com.emergentes.modelo.Categorias;
import java.util.List;

public interface CategoriaDAO {
    public void insert_categoria(Categorias categoria) throws Exception;
    public void update_categoria(Categorias categoria) throws Exception;
    public void delete_categoria(int id) throws Exception;
    public Categorias getId_categoria(int id) throws Exception;
    public List<Categorias> getAll_categoria() throws Exception;     
}
