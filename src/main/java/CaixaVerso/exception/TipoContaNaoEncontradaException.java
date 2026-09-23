package CaixaVerso.exception;

public class TipoContaNaoEncontradaException extends RuntimeException {
    public TipoContaNaoEncontradaException() {
        super("Tipo Conta Não Encontrada");
    }
}
