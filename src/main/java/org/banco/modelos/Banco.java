package org.banco.modelos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;

public class Banco {

    private Cliente[] clientes;
    private Cuenta[] cuentas;
    private Cliente_Cuenta[] cliente_cuenta;
    private RegistroOperacion[] operaciones;

    private final Scanner sc = new Scanner(System.in);

    public Banco() {
        clientes = new Cliente[1];
        cuentas = new Cuenta[1];
        cliente_cuenta = new Cliente_Cuenta[1];
        operaciones = new RegistroOperacion[1];
    }

    public void extenderListaClientes() {
        if (clientes[clientes.length - 1] != null) {
            Cliente[] extenderLista = new Cliente[clientes.length + 1]; //CREAMOS UNA LISTA CON 1 ESPACIO MÁS LARGO QUE LA ANTERIOR
            System.arraycopy(clientes, 0, extenderLista, 0, clientes.length); // GUARDAMOS TODO EN LA NUEVA LISTA

            clientes = extenderLista; //APUNTAMOS A LA NUEVA LISTA
        }
    }

    public void extenderListaCuentas() {
        if (cuentas[cuentas.length - 1] != null) {
            Cuenta[] extenderLista = new Cuenta[cuentas.length + 1];

            System.arraycopy(cuentas, 0, extenderLista, 0, cuentas.length);

            cuentas = extenderLista;
        }
    }

    public void extenderListaClienteCuenta() {
        if (cliente_cuenta[cliente_cuenta.length - 1] != null) {
            Cliente_Cuenta[] extenderLista = new Cliente_Cuenta[cliente_cuenta.length + 1];

            System.arraycopy(cliente_cuenta, 0, extenderLista, 0, cliente_cuenta.length);

            cliente_cuenta = extenderLista;
        }
    }

    public void disminuirListaClientes(int indice) {
        Cliente[] disminuirLista = new Cliente[clientes.length - 1];

        for (int i = indice; i < disminuirLista.length; i++) {
            clientes[i] = clientes[i + 1];
        }

        System.arraycopy(clientes, 0, disminuirLista, 0, disminuirLista.length);

        setClientes(disminuirLista);
    }

    public void disminuirListaCuentas(int indice) {
        Cuenta[] disminuirLista = new Cuenta[cuentas.length - 1];

        for (int i = indice; i < disminuirLista.length; i++) {
            cuentas[i] = cuentas[i + 1];
        }

        System.arraycopy(cuentas, 0, disminuirLista, 0, disminuirLista.length);

        setCuentas(disminuirLista);
    }
    
    public void disminuirListaCliente_CuentaPorIdCliente(int idCliente){
        int acumulador = 0;
        for (Cliente_Cuenta cc : cliente_cuenta) {
            if (cc != null && cc.getIdCliente() != idCliente) { //Descarta hasta el null
                acumulador++;
            }
        }
        
        Cliente_Cuenta[] disminuirLista = new Cliente_Cuenta[acumulador + 1]; //Se le agrega +1 para agregar ese null al final
        int i = 0;
        for(Cliente_Cuenta cc : cliente_cuenta){
            if (cc != null && cc.getIdCliente() != idCliente) {
                disminuirLista[i++] = cc;
            }
        }
        
        setCliente_cuenta(disminuirLista);
    }
    
    public void disminuirListaCliente_CuentaPorIdCuenta(int idCuenta){
        int acumulador = 0;
        for (Cliente_Cuenta cc : cliente_cuenta) {
            if (cc != null && cc.getIdCuenta()!= idCuenta) {
                acumulador++;
            }
        }
        
        Cliente_Cuenta[] disminuirLista = new Cliente_Cuenta[acumulador + 1];
        int i = 0;
        for(Cliente_Cuenta cc : cliente_cuenta){
            if (cc != null && cc.getIdCuenta() != idCuenta) {
                disminuirLista[i++] = cc;
            }
        }
        
        setCliente_cuenta(disminuirLista);
    }

    public void cargarClientes() throws Exception {  //CARGA LOS CLIENTES DEL ARCHIVO "LISTA_CLIENTES" Y LOS PONE EN EL ARRAY clientes, cuentas y cliente_cuenta

        try {
            FileReader listaClientes = new FileReader("LISTA_CLIENTES.TXT");
            BufferedReader lector = new BufferedReader(listaClientes);

            if (listaClientes.ready()) {
                String cadena = "";
                while ((cadena = lector.readLine()) != null) {

                    String[] partesCadena = cadena.split(";");

                    Cliente nuevoCliente = agregarListaClientes(
                            partesCadena[0], 
                            partesCadena[1],
                            Integer.parseInt(partesCadena[2]), 
                            Integer.parseInt(partesCadena[3]), 
                            partesCadena[4]
                    );

                    Cuenta nuevaCuenta = agregarListaCuentas(
                            TipoCuenta.valueOf(partesCadena[5]),
                            Moneda.valueOf(partesCadena[6]), 
                            EstadoCuenta.valueOf(partesCadena[7])
                    );
                    

                    agregarListaCliente_Cuenta(nuevoCliente,
                            nuevaCuenta);
                }

            } else {
                System.out.println("El archivo no está listo");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    public Cliente agregarListaClientes(String nombreCliente, String apellidoCliente, int dniCliente, int telefonoCLiente, String correoCliente) {
        Cliente nuevoCliente = new Cliente(nombreCliente, apellidoCliente, dniCliente, telefonoCLiente, correoCliente);
        clientes[clientes.length - 1] = nuevoCliente;
        
        extenderListaClientes();
        return nuevoCliente;
    }

    public Cuenta agregarListaCuentas(TipoCuenta tipoCuenta, Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        Cuenta nuevaCuenta = null;

        switch (tipoCuenta) {
            case AHORRO:
                nuevaCuenta = new Cuenta_Ahorro(tipoMoneda, estadoCuenta);
                break;
            case CORRIENTE:
                nuevaCuenta = new Cuenta_Corriente(tipoMoneda, estadoCuenta);
                break;
            case MANCOMUNADA:
                nuevaCuenta = new Cuenta_Mancomunada(tipoMoneda, estadoCuenta);
                break;
        }

        cuentas[cuentas.length - 1] = nuevaCuenta;
        extenderListaCuentas();

        return nuevaCuenta;
    }

    public void agregarListaCliente_Cuenta(Cliente nuevoCliente, Cuenta nuevaCuenta) {
        Cliente_Cuenta relacionClienteCuenta = new Cliente_Cuenta(nuevoCliente.getIdCliente(), nuevaCuenta.getIdCuenta());
        cliente_cuenta[cliente_cuenta.length - 1] = relacionClienteCuenta;
        
        extenderListaClienteCuenta();
    }

    public boolean existeCliente(int idCliente) { //VERIFICA QUE EL CLIENTE EXISTA POR EL ID
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getIdCliente() == idCliente) {
                return true;
            }
        }
        return false;
    }

    public Cliente buscarIdCliente(int idCliente) { //BUSCA EL CLIENTE POR EL ID
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public int buscarIndiceCliente(int idCliente) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getIdCliente() == idCliente) {
                return i;
            }
        }
        return -1;
    }

    public boolean existeCuenta(int idCuenta) { //ESTE MÉTODO SE ELIMINARÁ, RECUERDA ESTO ANGHELOOO
        for (Cuenta cuenta : cuentas) {
            if (cuenta != null && cuenta.getIdCuenta() == idCuenta) {
                return true;
            }
        }
        return false;
    }

    public Cuenta buscarCuentaPorId(int idCuenta) { //BUSCA EL CLIENTE POR EL ID
        for (Cuenta cuenta : cuentas) {
            if (cuenta != null && cuenta.getIdCuenta() == idCuenta) {
                return cuenta;
            }
        }
        return null;
    }

    public int buscarIndiceCuenta(int idCuenta) {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null && cuentas[i].getIdCuenta() == idCuenta) {
                return i;
            }
        }
        return 0;
    }

    public Cuenta[] buscarCuentasporIdCLiente(int idCliente) {

        int cantidad = 0;
        for (Cliente_Cuenta cc : cliente_cuenta) {
            if (cc != null && cc.getIdCliente() == idCliente) {
                cantidad++;
            }
        }

        Cuenta[] cuentas = new Cuenta[cantidad];
        int i = 0;
        for (Cliente_Cuenta cc : cliente_cuenta) {
            if (cc != null && cc.getIdCliente() == idCliente) {
                cuentas[i++] = buscarCuentaPorId(cc.getIdCuenta());
            }
        }
        return cuentas;
    }

    public Cliente[] buscarClientesPorIdCuenta(int idCuenta) {

        int cantidad = 0;
        for (Cliente_Cuenta cc : cliente_cuenta) {
            if (cc != null && cc.getIdCuenta() == idCuenta) {
                cantidad++;
            }
        }

        Cliente[] clientes = new Cliente[cantidad];
        int i = 0;
        for (Cliente_Cuenta cc : cliente_cuenta) {
            if (cc != null && cc.getIdCuenta() == idCuenta) {
                clientes[i++] = buscarIdCliente(cc.getIdCliente());
            }
        }
        return clientes;
    }
    
    public int buscarIdClientePorNombre(String nombreCliente) {
        for (int i = 0; i < clientes.length - 1; i++) {
            Cliente c = clientes[i];
            String nombreCompleto = c.getNombres() + " " + c.getApellidos();
            if (nombreCompleto.equals(nombreCliente)) {
                return c.getIdCliente();
            }
        }
        return -1;
    }
    
    public Cuenta buscarCuentaPorNumeroCuenta(long numeroCuenta){
        for (int i = 0; i < cuentas.length - 1; i++) {
            Cuenta c = cuentas[i];
            long posibleCuenta = c.getNumeroCuenta();
            if (posibleCuenta == numeroCuenta) {
                return c;
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
    
    public Cliente[] getClientes() {
        return clientes;
    }

    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }

    public void setCuentas(Cuenta[] cuentas) {
        this.cuentas = cuentas;
    }
    
    public void setCliente_cuenta(Cliente_Cuenta[] cliente_cuenta) {
        this.cliente_cuenta = cliente_cuenta;
    }
}
