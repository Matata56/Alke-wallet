package WalletModel;

public enum TipoMoneda {CLP(1),      // Peso chileno (base)
    USD(0.0012),    // Dólar
    EUR(0.00098),   // Euro
    ARS(1.67);     // Peso argentino
    
    private double tasaCambio;
    
    TipoMoneda(double tasaCambio) {
        this.tasaCambio = tasaCambio;
    }
    
    public double getTasaCambio() {
        return tasaCambio;
    }

}
