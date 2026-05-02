package org.banco.mantenimiento;
import org.banco.modelo.*;

import org.banco.modelo.Banco;

import java.util.Scanner;
import org.banco.utils.Mantenimiento;

public class MantenimientoCliente implements Mantenimiento {
    private Scanner sc = new Scanner(System.in);
    private Banco banco;

    public MantenimientoCliente(Banco banco){
        this.banco = banco;
    }

    public void MenuMantenimientoCliente(){
        int bandera = 0;
        int opcion = 0;

        do {
            bandera = 0;
            do {
                System.out.println("-----------------------------");
                System.out.println("*****GESTOR DE CLIENTES******");
                System.out.println("    1. Agregar cliente");
                System.out.println("    2. Eliminar cliente");
                System.out.println("    3. Actualizar cliente");
                System.out.println("    4. Imprimir clientes");
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
        int opcionCuenta;

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese un nombre para el cliente: ");
        String nombre = sc.nextLine(); //Almacena el nombre
        System.out.print("Ingrese su dni: ");
        int dni = sc.nextInt(); //Almacena el dni

        Cliente nuevoCliente = banco.guardarListaClientes(nombre, dni);
        banco.extenderListaClientes();

        do {
            System.out.println("----------------------");
            System.out.println("Eliga e tipo de cuenta");
            System.out.println("    1. Cuenta ahorro");
            System.out.println("    2. Cuenta corriente");
            System.out.print("->");
            opcionCuenta = sc.nextInt();

            if (opcionCuenta > 2 || opcionCuenta < 1){
                System.out.println("------------------------------------");
                System.out.println("Por favor, ingrese una opción válida");
                continue;
            }

            break;
        } while (true);

        Cuenta nuevaCuenta = banco.guardarListaCuentas(opcionCuenta);
        banco.extenderListaCuentas();

        banco.guardarListaCliente_Cuenta(nuevoCliente, nuevaCuenta);
        banco.extenderListaClienteCuenta();

        System.out.println("--------------------------");
        System.out.println("Cliente añadido con éxito!");
    }

    @Override
    public void eliminar() {
        int opcion = 0;

        if (banco.getClientes()[0] == null){
            System.out.println("--------------------------------");
            System.out.println("Aún no hay clientes en la lista!");
            return;
        }

        do {
            System.out.println("Por favor, elija el cliente a eliminar");
            imprimir();
            System.out.print("-> ");
            opcion = sc.nextInt();

            if (!banco.existeCliente(opcion)){
                System.out.println("------------------------------------");
                System.out.println("Por favor, ingrese una opción válida");
                continue;
            }

            break;
        } while (true);

        banco.getClientes()[banco.buscarIndiceCliente(opcion)] = null;
        banco.disminuirListaClientes(opcion); //Disminuye la lista de clientes

        System.out.println("----------------------------");
        System.out.println("Cliente eliminado con éxito!");
    }

    @Override
    public void actualizar() {
        int opcionCliente = 0;
        int opcionParametro = 0;

        do {
            System.out.println("Por favor, elija el cliente a actualizar");
            imprimir();
            System.out.print("-> ");
            opcionCliente = sc.nextInt();

            if (!banco.existeCliente(opcionCliente)){
                System.out.println("------------------------------------");
                System.out.println("Por favor, ingrese una opción válida");
                continue;
            }

            break;
        } while (true);

        do {
            System.out.println("¿Que desea actualizar?");
            System.out.println("    1. Nombre");
            System.out.println("    2. DNI");
            System.out.print("-> ");
            opcionParametro = sc.nextInt();

            if (opcionParametro > 2|| opcionParametro < 1){
                System.out.println("------------------------------------");
                System.out.println("Por favor, ingrese una opción válida");
                continue;
            }

            break;
        } while (true);
        sc.nextLine();

        switch (opcionParametro){
            case 1 :
                System.out.println("Ingrese el nuevo nombre");
                System.out.print("-> ");
                String nuevoNombre = sc.nextLine();
                banco.buscarIdCliente(opcionCliente).setNombre(nuevoNombre);

                System.out.println("----------------------");
                System.out.println("Se a actualizado");
                break;
            case 2:
                System.out.println("Ingrese el nuevo DNI");
                System.out.print("-> ");
                int nuevoDni = sc.nextInt();
                banco.buscarIdCliente(opcionCliente).setDni(nuevoDni);

                System.out.println("----------------------");
                System.out.println("Se a actualizado");
                break;
        }
    }

    @Override
    public void imprimir() {
        StringBuilder sb = new StringBuilder("*****LISTA DE CLIENTES***** \n"); //Mero título

        for (int i = 0; i < banco.getClientes().length; i++) {
            if (banco.getClientes()[i] != null){ //Evalúa que el elemento del array no sea nulo (debido a que el array cliente siempre tiene un array nulo por delante)
                sb.append(banco.getClientes()[i].getIdCliente() + " | " );
                sb.append(banco.getClientes()[i].toString() + "\n");
            }
        }
        System.out.println(sb);
    }
}
