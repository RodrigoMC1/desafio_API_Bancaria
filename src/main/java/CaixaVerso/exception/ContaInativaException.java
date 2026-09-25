package CaixaVerso.exception;

public class ContaInativaException extends RuntimeException {
    public ContaInativaException() {
        super("Conta inativa");
    }
}
