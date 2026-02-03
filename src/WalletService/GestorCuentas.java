package WalletService;

import java.util.ArrayList;
import java.util.List;
import WalletModel.Cuenta;

public class GestorCuentas {
    private List<Cuenta> cuentas;
    
    public GestorCuentas() {
        this.cuentas = new ArrayList<>();
    }
    
    // Registrar nueva cuenta
    public boolean registrarCuenta(Cuenta cuenta) {
        // Verificar si ya existe el número de cuenta
        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta().equals(cuenta.getNumeroCuenta())) {
                return false; // Ya existe
            }
        }
        cuentas.add(cuenta);
        return true;
    }
    
    // Iniciar sesión
    public Cuenta iniciarSesion(String numeroCuenta, String password) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                if (cuenta.verificarPassword(password)) {
                    return cuenta; // Login exitoso
                } else {
                    return null; // Contraseña incorrecta
                }
            }
        }
        return null; // Cuenta no encontrada
    }
    
    // Verificar si existe una cuenta
    public boolean existeCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return true;
            }
        }
        return false;
    }
    
    // Obtener cantidad de cuentas
    public int getCantidadCuentas() {
        return cuentas.size();
    }
}