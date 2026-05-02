package org.banco.mantenimiento;

import org.banco.modelo.*;
import org.banco.utils.Mantenimiento;

import java.util.Scanner;

public class MantenimientoCuenta implements Mantenimiento{
    
    private Scanner sc = new Scanner(System.in);
    private Banco banco;

    public MantenimientoCuenta(Banco banco){
        this.banco = banco;

    }

    public void MenuMantenimientoCuenta(){
        int bandera;
        int opcion;

        do {
            bandera = 0;
            do {
                System.out.println("-----------------------------");
                System.out.println("*****GESTOR DE CUENTA******");
                System.out.println("    1. Agregar cuenta");
                System.out.println("    2. Eliminar cuenta");
                System.out.println("    3. Actualizar cuenta");
                System.out.println("    4. Imprimir cuenta");
                System.out.println("    5. Salir");
                System.out.print("-> ");
                opcion = sc.nextInt();
                if (opcion > 5 || opcion < 1){
                    System.out.println("Por favor, ingrese una opción válida");
                    continue;
                }

                bandera = 1;
            } while(bandera == 0);
            sc.nextLine();

            switch (opcion){
                case 1:
                    agregar();
                    break;

                case 2:
                    eliminar();
                    break;

                case 3:
                    actualizar();
                    break;

                case 4:
                    imprimir();
                    break;

                case 5:
                    bandera = 2;
                    break;
            }

        } while(bandera == 1);
    }

    @Override
    public void agregar() {
        int idCliente = 0;
        int opcionCuenta = 0;
        MantenimientoCliente listaClientes = new MantenimientoCliente(banco);

        do {
            System.out.println("---------------------------");
            System.out.println("Por favor, elige el usuario");
            listaClientes.imprimir();
            System.out.print("->");
            idCliente = sc.nextInt();

            if (!banco.existeCliente(idCliente)){
                System.out.println("Por favor, elija una opción válida");
                continue;
            }

            break;
        } while(true);
        sc.nextLine();

        Cliente nuevoCLiente = banco.buscarIdCliente(idCliente);

        do {
            System.out.println("--------------------------");
            System.out.println("Eliga una cuenta a agregar");
            System.out.println("    1. Cuenta Ahorro");
            System.out.println("    2. Cuenta Corriente");
            System.out.print("->");
            opcionCuenta = sc.nextInt();

            if (opcionCuenta > 2 || opcionCuenta < 1){
                System.out.println("Por favor, elija una opción válida");
                continue;
            }

            break;
        } while (true);

        Cuenta nuevaCuenta = banco.guardarListaCuentas(opcionCuenta);
        banco.extenderListaCuentas();

        banco.guardarListaCliente_Cuenta(nuevoCLiente, nuevaCuenta);
        banco.extenderListaClienteCuenta();

        System.out.println("-------------------------");
        System.out.println("Cuenta añadida con éxito!");
    }

    @Override
    public void eliminar() {

        int idCuenta = 0;

        do {
            System.out.println("---------------------------");
            System.out.println("Eliga una cuenta a eliminar");
            imprimir();
            System.out.print("->");
            idCuenta = sc.nextInt();

            if (!banco.existeCuenta(idCuenta)){
                System.out.println("Por favor, elija una opción válida");
                continue;
            }
            break;
        } while (true);

        banco.getCuentas()[banco.buscarIndiceCuenta(idCuenta)] = null;
        banco.disminuirListaCuentas(idCuenta);

        System.out.println("----------------------------");
        System.out.println("Cliente eliminado con éxito!");

    }

    @Override
    public void actualizar() {

    }

    @Override
    public void imprimir() {
        StringBuilder sb = new StringBuilder("*****LISTA DE CUENTAS***** \n");

        for (int i = 0; i < banco.getCuentas().length; i++) {
            if (banco.getCuentas()[i] != null){
                sb.append(banco.getCuentas()[i].getIdCuenta() + ". ");
                sb.append(banco.getCuentas()[i].toString() + "\n");
            }
        }

        System.out.println(sb);
    }
    
}
