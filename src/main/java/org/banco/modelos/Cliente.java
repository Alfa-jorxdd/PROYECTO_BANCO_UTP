package org.banco.modelos;

public class Cliente {
    private static int idIncrementar = 0;
    private final int idCliente;
    private String nombres;
    private String apellidos;
    private int dni;
    private int telefono;
    private String correo;

    public Cliente(String nombres, String apellidos, int dni, int telefono, String correo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.idCliente = ++idIncrementar;
        this.telefono = telefono;
        this.correo = correo;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s | DNI: %d", nombres, dni);
    }

    public void setNombres(String nombre) {
        this.nombres = nombre;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombres() {
        return nombres;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getApellidos() {
        return apellidos;
    }

    public int getDni() {
        return dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
