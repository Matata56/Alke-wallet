package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import WalletModel.Cuenta;
import WalletModel.TipoMoneda;
import WalletService.GestorCuentas;

public class GestorCuentasTest {
    
    private GestorCuentas gestor;
    
    @BeforeEach
    public void setUp() {
        gestor = new GestorCuentas();
    }
    
    @Test
    public void testRegistrarCuenta() {
        // CONSTRUCTOR CORREGIDO: solo 4 parámetros
        Cuenta cuenta = new Cuenta("001", "Usuario", "pass123", TipoMoneda.CLP);
        assertTrue(gestor.registrarCuenta(cuenta));
    }
    
    @Test
    public void testRegistrarCuentaDuplicada() {
        Cuenta cuenta1 = new Cuenta("001", "Usuario1", "pass1", TipoMoneda.CLP);
        Cuenta cuenta2 = new Cuenta("001", "Usuario2", "pass2", TipoMoneda.USD);
        
        gestor.registrarCuenta(cuenta1);
        assertFalse(gestor.registrarCuenta(cuenta2)); // No debe permitir duplicados
    }
    
    @Test
    public void testIniciarSesionExitoso() {
        Cuenta cuenta = new Cuenta("001", "Usuario", "pass123", TipoMoneda.CLP);
        gestor.registrarCuenta(cuenta);
        
        Cuenta resultado = gestor.iniciarSesion("001", "pass123");
        assertNotNull(resultado);
        assertEquals("Usuario", resultado.getTitular());
    }
    
    @Test
    public void testIniciarSesionPasswordIncorrecta() {
        Cuenta cuenta = new Cuenta("001", "Usuario", "pass123", TipoMoneda.CLP);
        gestor.registrarCuenta(cuenta);
        
        Cuenta resultado = gestor.iniciarSesion("001", "wrongpass");
        assertNull(resultado);
    }
    
    @Test
    public void testIniciarSesionCuentaNoExiste() {
        Cuenta resultado = gestor.iniciarSesion("999", "anypass");
        assertNull(resultado);
    }
    
    @Test
    public void testExisteCuenta() {
        Cuenta cuenta = new Cuenta("001", "Usuario", "pass123", TipoMoneda.CLP);
        gestor.registrarCuenta(cuenta);
        
        assertTrue(gestor.existeCuenta("001"));
        assertFalse(gestor.existeCuenta("999"));
    }
    
    @Test
    public void testGetCantidadCuentas() {
        assertEquals(0, gestor.getCantidadCuentas());
        
        Cuenta cuenta1 = new Cuenta("001", "Usuario1", "pass1", TipoMoneda.CLP);
        Cuenta cuenta2 = new Cuenta("002", "Usuario2", "pass2", TipoMoneda.USD);
        
        gestor.registrarCuenta(cuenta1);
        assertEquals(1, gestor.getCantidadCuentas());
        
        gestor.registrarCuenta(cuenta2);
        assertEquals(2, gestor.getCantidadCuentas());
    }
}