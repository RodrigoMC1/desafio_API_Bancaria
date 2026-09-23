package CaixaVerso.exception;

public class PessoaNaoEncontradaException extends RuntimeException {
    public PessoaNaoEncontradaException() {
        super("Pessoa Não Encontrada");
    }
}
