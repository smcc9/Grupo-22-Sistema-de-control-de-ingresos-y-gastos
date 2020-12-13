
package com.emergentes.modelo;

import com.emergentes.dao.CategoriaDAO;
import com.emergentes.dao.VecinosDAO;
import java.sql.Date;
import java.util.List;
public class Ingresos extends Vecinos {
    private int id_ingreso;
    private String descripcion;
    private int categoria;
    private Date fecha;
    private Double monto;
    private int vecino;

    public int getVecino() {
        return vecino;
    }

    public void setVecino(int vecino) {
        this.vecino = vecino;
    }
    public int getId_ingreso() {
        return id_ingreso;
    }

    public void setId_ingreso(int id_ingreso) {
        this.id_ingreso = id_ingreso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }    
    private String nombre_categoria;

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
}
