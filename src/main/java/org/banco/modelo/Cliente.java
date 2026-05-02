package org.banco.modelo;

public class Cliente {
     private static int idIncrementar = 0;
    private int idCliente;
    private String nombre;
    private int dni;

    public Cliente(String nombre, int dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.idCliente = ++idIncrementar;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s | DNI: %d", nombre, dni);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getIdCliente() {
        return idCliente;
    }
}
