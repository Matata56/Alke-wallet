package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import WalletModel.Cuenta;
import WalletModel.TipoMoneda;

public class CuentaTest {
    
    private Cuenta cuenta;
    
    @BeforeEach
    public void setUp() {
        // Crear cuenta con saldo inicial de 0 (solo 4 parámetros)
        cuenta = new Cuenta("001", "Test User", "password123", TipoMoneda.CLP);
        // Depositar 1000 para tener saldo en los tests
        cuenta.depositar(1000.0);
    }
    
    @Test
    public void testCrearCuenta() {
        Cuenta cuentaNueva = new Cuenta("002", "Test User 2", "pass", TipoMoneda.CLP);
        assertNotNull(cuentaNueva);
        assertEquals("002", cuentaNueva.getNumeroCuenta());
        assertEquals("Test User 2", cuentaNueva.getTitular());
        assertEquals(0.0, cuentaNueva.consultarSaldo()); // Saldo inicial es 0
        assertEquals(TipoMoneda.CLP, cuentaNueva.getMoneda());
    }
    
    @Test
    public void testVerificarPasswordCorrecta() {
        assertTrue(cuenta.verificarPassword("password123"));
    }
    
    @Test
    public void testVerificarPasswordIncorrecta() {
        assertFalse(cuenta.verificarPassword("wrongpassword"));
    }
    
    @Test
    public void testDepositar() {
        cuenta.depositar(500.0);
        assertEquals(1500.0, cuenta.consultarSaldo());
    }
    
    @Test
    public void testDepositarMontoNegativo() {
        cuenta.depositar(-100.0);
        assertEquals(1000.0, cuenta.consultarSaldo()); // No debe cambiar
    }
    
    @Test
    public void testDepositarCero() {
        cuenta.depositar(0);
        assertEquals(1000.0, cuenta.consultarSaldo()); // No debe cambiar
    }
    
    @Test
    public void testRetirar() {
        cuenta.retirar(300.0);
        assertEquals(700.0, cuenta.consultarSaldo());
    }
    
    @Test
    public void testRetirarSinFondos() {
        cuenta.retirar(2000.0);
        assertEquals(1000.0, cuenta.consultarSaldo()); // No debe cambiar
    }
    
    @Test
    public void testRetirarMontoNegativo() {
        cuenta.retirar(-50.0);
        assertEquals(1000.0, cuenta.consultarSaldo()); // No debe cambiar
    }
    
    @Test
    public void testConsultarSaldo() {
        double saldo = cuenta.consultarSaldo();
        assertEquals(1000.0, saldo);
    }
    
    @Test
    public void testCambiarMoneda() {
        cuenta.setMoneda(TipoMoneda.USD);
        assertEquals(TipoMoneda.USD, cuenta.getMoneda());
    }
}