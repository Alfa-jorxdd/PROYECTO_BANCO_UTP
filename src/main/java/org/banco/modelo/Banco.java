package org.banco.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Banco {
    private Cliente[] clientes;
    private Cuenta[] cuentas;
    private Cliente_Cuenta[] cliente_cuenta;

    private Scanner sc = new Scanner(System.in);

    public Banco(){
        clientes = new Cliente[1];
        cuentas = new Cuenta[1];
        cliente_cuenta = new Cliente_Cuenta[1];
    }

    public void extenderListaClientes(){
        if (clientes[clientes.length - 1] != null){
            Cliente[] extenderLista = new Cliente[clientes.length + 1]; //CREAMOS UNA LISTA CON 1 ESPACIO MÁS LARGO QUE LA ANTERIOR

            for (int i = 0; i < clientes.length; i++) {
                extenderLista[i] = clientes[i]; // GUARDAMOS TODO EN LA NUEVA LISTA
            }

            clientes = extenderLista; //APUNTAMOS A LA NUEVA LISTA
        }
    }

    public void extenderListaCuentas(){
        if(cuentas[cuentas.length - 1] != null){
            Cuenta[] extenderLista = new Cuenta[cuentas.length + 1];

            for (int i = 0; i < cuentas.length; i++) {
                extenderLista[i] = cuentas[i];
            }

            cuentas = extenderLista;
        }
    }

    public void extenderListaClienteCuenta(){
        if(cliente_cuenta[cliente_cuenta.length - 1] != null){
            Cliente_Cuenta[] extenderLista = new Cliente_Cuenta[cliente_cuenta.length + 1];

            for (int i = 0; i < cliente_cuenta.length; i++) {
                extenderLista[i] = cliente_cuenta[i];
            }

            cliente_cuenta = extenderLista;
        }
    }

    public void disminuirListaClientes(int opcion){
        Cliente[] disminuirLista = new Cliente[clientes.length - 1];

        for (int i = opcion - 1; i < disminuirLista.length; i++) {
            clientes[i] = clientes[i + 1];
        }

        for (int i = 0; i < disminuirLista.length; i++) {
            disminuirLista[i] = clientes[i];
        }

        setClientes(disminuirLista);
    }

    public void disminuirListaCuentas(int opcion){
        Cuenta[] disminuirLista = new Cuenta[cuentas.length - 1];

        for (int i = 0; i < disminuirLista.length; i++) {
            cuentas[i] = cuentas[i + 1];
        }

        for (int i = 0; i < disminuirLista.length; i++) {
            disminuirLista[i] = cuentas[i];
        }

        setCuentas(disminuirLista);

    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }

    public void setCuentas(Cuenta[] cuentas) {
        this.cuentas = cuentas;
    }


    public void cargarClientes() throws Exception{  //CARGA LOS CLIENTES DEL ARCHIVO "LISTA_CLIENTES" Y LOS PONE EN EL ARRAY "clientes"

       try {
           FileReader listaClientes = new FileReader("LISTA_CLIENTES.TXT");
           BufferedReader lector = new BufferedReader(listaClientes);

           if (listaClientes.ready()){
                String cadena = "";
               while ((cadena = lector.readLine()) != null){

                   String[] partesCadena = cadena.split(";");

                   Cliente nuevoCliente = guardarListaClientes(partesCadena[0], Integer.parseInt(partesCadena[1]));
                   extenderListaClientes();

                   Cuenta nuevaCuenta = guardarListaCuentas(Integer.parseInt(partesCadena[2]));
                   extenderListaCuentas();

                   guardarListaCliente_Cuenta(nuevoCliente
                           , nuevaCuenta);
                   extenderListaClienteCuenta();
               }

           } else {
               System.out.println("El archivo no está listo");
           }
       } catch (Exception e) {
           System.out.println(e);
       }
    }

    public Cliente guardarListaClientes(String nombreCliente, int dniCliente){
        Cliente nuevoCliente = new Cliente(nombreCliente, dniCliente);
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] == null){
                clientes[i] = nuevoCliente;
                return clientes[i];
            }
        }
        return null;
    }

    public Cuenta guardarListaCuentas(int opcionCuenta){
        Cuenta nuevaCuenta = null;

        for (int i = 0; i < cuentas.length; i++) { //<---------------------------------
            if (cuentas[i] == null){
                switch (opcionCuenta){
                    case 1:
                        cuentas[i] = new Cuenta_Ahorro();
                        nuevaCuenta = cuentas[i];
                        break;

                    case 2:
                        cuentas[i] = new Cuenta_Corriente();
                        nuevaCuenta = cuentas[i];
                        break;
                }
                return nuevaCuenta;
            }
        }

        return null;
    }

    public void guardarListaCliente_Cuenta(Cliente nuevoCliente, Cuenta nuevaCuenta){
        Cliente_Cuenta relacionClienteCuenta = new Cliente_Cuenta(nuevoCliente.getIdCliente(), nuevaCuenta.getIdCuenta());

        for (int i = 0; i < cliente_cuenta.length; i++) {
            if (cliente_cuenta[i] == null){
                cliente_cuenta[i] = relacionClienteCuenta;
                break;
            }
        }
    }

    public boolean existeCliente(int idCliente){ //VERIFICA QUE EL CLIENTE EXISTA POR EL ID
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getIdCliente() == idCliente){
                return true;
            }
        }
        return false;
    }

    public Cliente buscarIdCliente(int idCliente){ //BUSCA EL CLIENTE POR EL ID
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getIdCliente() == idCliente)
                return clientes[i];
        }
        return null;
    }

    public int buscarIndiceCliente(int idCliente){
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getIdCliente() == idCliente){
                return i;
            }
        }
        return -1;
    }

    public boolean existeCuenta(int idCuenta){
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null && cuentas[i].getIdCuenta() == idCuenta){
                return true;
            }
        }
        return false;
    }

    public Cuenta buscarIdCuenta(int idCuenta){ //BUSCA EL CLIENTE POR EL ID
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null && cuentas[i].getIdCuenta() == idCuenta)
                return cuentas[i];
        }
        return null;
    }

    public int buscarIndiceCuenta(int idCuenta){
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null && cuentas[i].getIdCuenta() == idCuenta){
                return i;
            }
        }
        return 0;
    }

    public Cuenta buscarCuentaporIdCLiente(int idCliente){

        for (int i = 0; i < cliente_cuenta.length; i++) {
            if (cliente_cuenta[i] != null && cliente_cuenta[i].getIdCliente() == idCliente){
                return buscarIdCuenta(cliente_cuenta[i].getIdCuenta());
            }
        }

        return null;
    }

    public Cuenta[] getCuentas() {
        return cuentas;
    }

    public Cliente_Cuenta[] getCliente_cuenta() {
        return cliente_cuenta;
    }
}
