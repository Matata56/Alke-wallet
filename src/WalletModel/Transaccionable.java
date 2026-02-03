package WalletModel;

public interface Transaccionable {
	void depositar(double monto);
	void retirar(double monto);
	double consultarSaldo();
}
