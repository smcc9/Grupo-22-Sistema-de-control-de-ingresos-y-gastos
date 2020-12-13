
package com.emergentes.modelo;
import java.sql.Date;
public class Gastos extends Categorias{
    private int id_gastos;
    private String descripcion;
    private int categoria;

    private Date fecha;
    private Double monto;

    public int getId_gastos() {
        return id_gastos;
    }

    public void setId_gastos(int id_gastos) {
        this.id_gastos = id_gastos;
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
    
}
