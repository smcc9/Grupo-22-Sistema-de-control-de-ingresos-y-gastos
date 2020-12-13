package com.emergentes.dao;

import com.emergentes.modelo.Gastos;
import java.sql.Date;
import java.util.List;

public interface GastosDAO {
    public void insert_gasto(Gastos gasto) throws Exception;
    public void update_gasto(Gastos gasto) throws Exception;
    public void delete_gasto(int id) throws Exception;
    public Gastos getId_gasto(int id) throws Exception;
    public List<Gastos> getAll_gasto() throws Exception;  
    public List<Gastos> getAll_gasto_fechas(Date fecha1, Date fecha2, String operacion) throws Exception;  
    public Gastos getTotalgastos() throws Exception;
    public double total_gastos()throws Exception;
    public double total_gastos_mes()throws Exception;
}
