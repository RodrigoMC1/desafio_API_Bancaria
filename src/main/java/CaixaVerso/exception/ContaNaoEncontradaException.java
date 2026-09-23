package CaixaVerso.exception;

public class ContaNaoEncontradaException extends RuntimeException {
    public ContaNaoEncontradaException() {
        super("Conta Não Encontrada");
    }
}
