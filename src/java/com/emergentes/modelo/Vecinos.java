
package com.emergentes.modelo;

public class Vecinos {
    private int id_vecino;
    private String nombre;
    private String apellidos;
    private String direccion;
    private int celular;
    private int lote;
    private int manzana;

    public int getId_vecino() {
        return id_vecino;
    }

    public void setId_vecino(int id_vecino) {
        this.id_vecino = id_vecino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public int getManzana() {
        return manzana;
    }

    public void setManzana(int manzana) {
        this.manzana = manzana;
    }
}
