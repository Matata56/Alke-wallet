package WalletService;

import WalletModel.Cuenta;
import WalletModel.TipoMoneda;

/**
 * Servicio de conversión de monedas.
 * Proporciona métodos estáticos para convertir entre diferentes tipos de moneda
 * utilizando tasas de cambio predefinidas.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 */
public class ConversorMoneda {
    
    /**
     * Convierte un monto de una moneda a otra.
     * Utiliza el Peso Chileno (CLP) como moneda base para las conversiones.
     * 
     * @param monto Cantidad a convertir
     * @param monedaOrigen Moneda de origen
     * @param monedaDestino Moneda de destino
     * @return Monto convertido a la moneda destino
     */
    public static double convertir(double monto, TipoMoneda monedaOrigen, TipoMoneda monedaDestino) {
        // Convertir primero a la moneda base (CLP)
        double montoEnBase = monto / monedaOrigen.getTasaCambio();
        
        // Luego convertir de la moneda base a la moneda destino
        double montoConvertido = montoEnBase * monedaDestino.getTasaCambio();
        
        return montoConvertido;
    }
    
    /**
     * Convierte el saldo completo de una cuenta a una nueva moneda.
     * Actualiza tanto el saldo como el tipo de moneda de la cuenta.
     * 
     * @param cuenta Cuenta a convertir
     * @param nuevaMoneda Tipo de moneda destino
     */
    public static void convertirCuenta(Cuenta cuenta, TipoMoneda nuevaMoneda) {
        double saldoActual = cuenta.consultarSaldo();
        TipoMoneda monedaActual = cuenta.getMoneda();
        
        // Realizar la conversión
        double nuevoSaldo = convertir(saldoActual, monedaActual, nuevaMoneda);
        
        // Actualizar la cuenta
        cuenta.retirar(saldoActual); // Retirar todo el saldo actual
        cuenta.setMoneda(nuevaMoneda); // Cambiar la moneda
        cuenta.depositar(nuevoSaldo); // Depositar el saldo convertido
        
        System.out.println("Conversión exitosa de " + monedaActual + " a " + nuevaMoneda);
        System.out.println("Nuevo saldo: " + nuevoSaldo + " " + nuevaMoneda);
    }
}