package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import WalletModel.Cuenta;
import WalletModel.TipoMoneda;
import WalletService.ConversorMoneda;

public class ConversorMonedaTest {
    
    @Test
    public void testConvertirMismaMoneda() {
        // Convertir de CLP a CLP no debe cambiar el valor
        double resultado = ConversorMoneda.convertir(1000.0, TipoMoneda.CLP, TipoMoneda.CLP);
        assertEquals(1000.0, resultado, 0.01);
    }
    
    @Test
    public void testConvertirCLPaUSD() {
        // Cualquier conversión de CLP a otra moneda debe dar un resultado positivo
        double resultado = ConversorMoneda.convertir(950.0, TipoMoneda.CLP, TipoMoneda.USD);
        assertTrue(resultado > 0);
    }
    
    @Test
    public void testConvertirUSDaCLP() {
        // Conversión de USD a CLP debe ser positiva
        double resultado = ConversorMoneda.convertir(100.0, TipoMoneda.USD, TipoMoneda.CLP);
        assertTrue(resultado > 0);
    }
    
    @Test
    public void testConvertirRetornaValorPositivo() {
        // Toda conversión válida debe retornar un valor positivo
        double resultado = ConversorMoneda.convertir(1000.0, TipoMoneda.CLP, TipoMoneda.EUR);
        assertTrue(resultado > 0);
    }
    
    @Test
    public void testConvertirCuenta() {
        // CONSTRUCTOR 
        Cuenta cuenta = new Cuenta("002", "Test", "pass", TipoMoneda.CLP);
        cuenta.depositar(1000.0); // Agregamos saldo antes de convertir
        TipoMoneda monedaInicial = cuenta.getMoneda();
        
        ConversorMoneda.convertirCuenta(cuenta, TipoMoneda.USD);
        
        // Verifica que la moneda cambió
        assertEquals(TipoMoneda.USD, cuenta.getMoneda());
        assertNotEquals(monedaInicial, cuenta.getMoneda());
        // Verifica que tiene saldo positivo
        assertTrue(cuenta.consultarSaldo() > 0);
    }
    
    @Test
    public void testConvertirCuentaMantieneSaldo() {
        Cuenta cuenta = new Cuenta("003", "Test", "pass", TipoMoneda.CLP);
        cuenta.depositar(5000.0);
        double saldoInicial = cuenta.consultarSaldo();
        
        // Convertir a la misma moneda no debería alterar el saldo significativamente
        ConversorMoneda.convertirCuenta(cuenta, TipoMoneda.CLP);
        
        assertEquals(saldoInicial, cuenta.consultarSaldo(), 1.0);
    }
    
    @Test
    public void testConvertirCuentaMultiplesMonedas() {
        Cuenta cuenta = new Cuenta("004", "Test", "pass", TipoMoneda.CLP);
        cuenta.depositar(10000.0);
        
        // Convertir a USD
        ConversorMoneda.convertirCuenta(cuenta, TipoMoneda.USD);
        assertEquals(TipoMoneda.USD, cuenta.getMoneda());
        assertTrue(cuenta.consultarSaldo() > 0);
        
        // Convertir a EUR
        ConversorMoneda.convertirCuenta(cuenta, TipoMoneda.EUR);
        assertEquals(TipoMoneda.EUR, cuenta.getMoneda());
        assertTrue(cuenta.consultarSaldo() > 0);
    }
}