package CaixaVerso.exception;

public class ValorMovimentacaoInvalidoException extends RuntimeException {
    public ValorMovimentacaoInvalidoException() {
        super("Valor de movimentação inválido");
    }
}
