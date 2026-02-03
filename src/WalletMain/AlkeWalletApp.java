package WalletMain;

import java.util.Scanner;
import WalletModel.Cuenta;
import WalletModel.TipoMoneda;
import WalletService.ConversorMoneda;
import WalletService.GestorCuentas;

/**
 * Aplicación principal de Alke Wallet.
 * Punto de entrada del sistema de billetera digital.
 * Proporciona una interfaz de consola interactiva para gestionar cuentas y operaciones.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 */
public class AlkeWalletApp {

    /**
     * Método principal que inicia la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorCuentas gestor = new GestorCuentas();
        
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║   BIENVENIDO A ALKE WALLET    ║");
        System.out.println("╚════════════════════════════════╝\n");
        
        boolean salirApp = false;
        
        while (!salirApp) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Crear nueva cuenta");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch(opcion) {
                case 1:
                    crearCuenta(scanner, gestor);
                    break;
                    
                case 2:
                    iniciarSesion(scanner, gestor);
                    break;
                    
                case 3:
                    System.out.println("\n¡Gracias por usar Alke Wallet!");
                    salirApp = true;
                    break;
                    
                default:
                    System.out.println("Opción inválida");
            }
        }
        
        scanner.close();
    }
    
    // Método para crear cuenta
    private static void crearCuenta(Scanner scanner, GestorCuentas gestor) {
        System.out.println("\n=== CREAR NUEVA CUENTA ===");
        
        System.out.print("Ingrese número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        // Verificar si ya existe
        if (gestor.existeCuenta(numeroCuenta)) {
            System.out.println("❌ El número de cuenta ya existe");
            return;
        }
        
        System.out.print("Ingrese nombre del titular: ");
        String titular = scanner.nextLine();
        
        System.out.print("Cree una contraseña: ");
        String password = scanner.nextLine();
        
        System.out.println("\nSeleccione tipo de moneda:");
        System.out.println("1. CLP (Peso Chileno)");
        System.out.println("2. USD (Dólar)");
        System.out.println("3. EUR (Euro)");
        System.out.println("4. ARS (Peso Argentino)");
        System.out.print("Opción: ");
        int opcionMoneda = scanner.nextInt();
        
        TipoMoneda moneda = TipoMoneda.CLP;
        switch(opcionMoneda) {
            case 1: moneda = TipoMoneda.CLP; break;
            case 2: moneda = TipoMoneda.USD; break;
            case 3: moneda = TipoMoneda.EUR; break;
            case 4: moneda = TipoMoneda.ARS; break;
        }
        
        // CONSTRUCTOR CORREGIDO: solo 4 parámetros
        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, titular, password, moneda);
        
        if (gestor.registrarCuenta(nuevaCuenta)) {
            System.out.println("\n✓ Cuenta creada exitosamente con saldo inicial de 0 " + moneda);
            System.out.println(nuevaCuenta);
            System.out.println("\n💡 Inicia sesión y realiza tu primer depósito para comenzar a usar tu cuenta.");
        } else {
            System.out.println("❌ Error al crear la cuenta");
        }
    }
    
    // Método para iniciar sesión
    private static void iniciarSesion(Scanner scanner, GestorCuentas gestor) {
        System.out.println("\n=== INICIAR SESIÓN ===");
        
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        
        Cuenta cuenta = gestor.iniciarSesion(numeroCuenta, password);
        
        if (cuenta == null) {
            System.out.println("❌ Cuenta o contraseña incorrecta");
            return;
        }
        
        System.out.println("\n✓ Sesión iniciada exitosamente");
        System.out.println("Bienvenido/a " + cuenta.getTitular());
        
        // Menú de la cuenta
        menuCuenta(scanner, cuenta);
    }
    
    // Menú de operaciones de la cuenta
    private static void menuCuenta(Scanner scanner, Cuenta cuenta) {
        boolean cerrarSesion = false;
        
        while(!cerrarSesion) {
            System.out.println("\n=== MI CUENTA ===");
            System.out.println("Titular: " + cuenta.getTitular());
            System.out.println("1. Ver saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Convertir moneda");
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione opción: ");
            int opcion = scanner.nextInt();
            
            switch(opcion) {
                case 1:
                    System.out.println("\n--- CONSULTA DE SALDO ---");
                    System.out.println("Saldo actual: " + cuenta.consultarSaldo() + " " + cuenta.getMoneda());
                    break;
                    
                case 2:
                    System.out.print("\nIngrese monto a depositar: ");
                    double montoDeposito = scanner.nextDouble();
                    cuenta.depositar(montoDeposito);
                    System.out.println("Saldo actual: " + cuenta.consultarSaldo() + " " + cuenta.getMoneda());
                    break;
                    
                case 3:
                    System.out.print("\nIngrese monto a retirar: ");
                    double montoRetiro = scanner.nextDouble();
                    cuenta.retirar(montoRetiro);
                    System.out.println("Saldo actual: " + cuenta.consultarSaldo() + " " + cuenta.getMoneda());
                    break;
                    
                case 4:
                    System.out.println("\n--- CONVERSIÓN DE MONEDA ---");
                    System.out.println("Moneda actual: " + cuenta.getMoneda());
                    System.out.println("Saldo actual: " + cuenta.consultarSaldo() + " " + cuenta.getMoneda());
                    System.out.println("\nSeleccione moneda de destino:");
                    System.out.println("1. CLP (Peso Chileno)");
                    System.out.println("2. USD (Dólar)");
                    System.out.println("3. EUR (Euro)");
                    System.out.println("4. ARS (Peso Argentino)");
                    System.out.print("Opción: ");
                    int opcionConversion = scanner.nextInt();
                    
                    TipoMoneda nuevaMoneda = TipoMoneda.CLP;
                    switch(opcionConversion) {
                        case 1: nuevaMoneda = TipoMoneda.CLP; break;
                        case 2: nuevaMoneda = TipoMoneda.USD; break;
                        case 3: nuevaMoneda = TipoMoneda.EUR; break;
                        case 4: nuevaMoneda = TipoMoneda.ARS; break;
                    }
                    
                    ConversorMoneda.convertirCuenta(cuenta, nuevaMoneda);
                    break;
                    
                case 5:
                    System.out.println("\n✓ Sesión cerrada");
                    System.out.println("Estado final de su cuenta:");
                    System.out.println(cuenta);
                    cerrarSesion = true;
                    break;
                    
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}