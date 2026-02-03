package WalletModel;

/**
 * Clase que representa una cuenta bancaria en la billetera digital.
 * Implementa la interfaz Transaccionable para operaciones básicas.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 */
public class Cuenta implements Transaccionable {
    
    /** Número único de identificación de la cuenta */
    private String numeroCuenta;
    
    /** Nombre del titular de la cuenta */
    private String titular;
    
    /** Contraseña de acceso a la cuenta */
    private String password;
    
    /** Saldo actual disponible en la cuenta */
    private double saldo;
    
    /** Tipo de moneda en que está denominada la cuenta */
    private TipoMoneda moneda;
    
    /**
     * Constructor de la cuenta.
     * La cuenta se crea con saldo inicial de 0.
     * 
     * @param numeroCuenta Identificador único de la cuenta
     * @param titular Nombre del propietario de la cuenta
     * @param password Contraseña para autenticación
     * @param moneda Tipo de moneda de la cuenta
     */
    public Cuenta(String numeroCuenta, String titular, String password, TipoMoneda moneda) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.password = password;
        this.saldo = 0.0;  // ← AQUÍ ESTÁ EL SALDO INICIAL EN 0
        this.moneda = moneda;
    }
    
    /**
     * Verifica si la contraseña proporcionada es correcta.
     * 
     * @param password Contraseña a verificar
     * @return true si la contraseña es correcta, false en caso contrario
     */
    public boolean verificarPassword(String password) {
        return this.password.equals(password);
    }
    
    /**
     * Deposita dinero en la cuenta.
     * Solo permite depósitos de montos positivos.
     * 
     * @param monto Cantidad a depositar
     */
    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("Depósito exitoso: " + monto + " " + moneda);
        } else {
            System.out.println("El monto debe ser positivo");
        }
    }
    
    /**
     * Retira dinero de la cuenta.
     * Valida que haya fondos suficientes y que el monto sea positivo.
     * 
     * @param monto Cantidad a retirar
     */
    @Override
    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            this.saldo -= monto;
            System.out.println("Retiro exitoso: " + monto + " " + moneda);
        } else {
            System.out.println("Fondos insuficientes o monto inválido");
        }
    }
    
    /**
     * Consulta el saldo actual de la cuenta.
     * 
     * @return Saldo disponible
     */
    @Override
    public double consultarSaldo() {
        return this.saldo;
    }
    
    // Getters y Setters con comentarios breves
    
    /** @return Número de cuenta */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    /** @return Nombre del titular */
    public String getTitular() {
        return titular;
    }
    
    /** @return Tipo de moneda actual */
    public TipoMoneda getMoneda() {
        return moneda;
    }
    
    /** @param moneda Nuevo tipo de moneda para la cuenta */
    public void setMoneda(TipoMoneda moneda) {
        this.moneda = moneda;
    }
    
    @Override
    public String toString() {
        return "Cuenta: " + numeroCuenta + " | Titular: " + titular + 
               " | Saldo: " + saldo + " " + moneda;
    }
}