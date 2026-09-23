package CaixaVerso.exception;

public class ContaJaCadastradaException extends RuntimeException {
    public ContaJaCadastradaException() {
        super("Conta já Cadastrada");
    }
}
