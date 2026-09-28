package CaixaVerso.exception.Erro400ReuisicaoInvalida;

public class ValorDepositoIncorretoException extends ClasseRequisicaoInvalida{
    public ValorDepositoIncorretoException(String saldoInsulficiente) {
        super(saldoInsulficiente);
    }
}
