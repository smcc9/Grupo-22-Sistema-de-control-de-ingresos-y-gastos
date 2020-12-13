package com.emergentes.dao;

import com.emergentes.modelo.Ingresos;
import java.sql.Date;
import java.util.List;

public interface IngresosDAO {
    public void insert_ingreso(Ingresos ingreso) throws Exception;
    public void update_ingreso(Ingresos ingreso) throws Exception;
    public void delete_ingreso(int id) throws Exception;
    public Ingresos getId_ingreso(int id) throws Exception;
    public List<Ingresos> getAll_ingreso() throws Exception;      
    public List<Ingresos> getAll_ingreso_fechas(Date fecha1, Date fecha2, String operacion) throws Exception;       
    public List<Ingresos> getAll_ingreso_vecino(Date fecha1, Date fecha2, int vecino) throws Exception;
    public double total_ingresos()throws Exception;
    public double total_ingresos_mes()throws Exception;
}
