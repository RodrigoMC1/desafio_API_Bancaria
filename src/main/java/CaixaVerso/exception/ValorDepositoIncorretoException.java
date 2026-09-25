package CaixaVerso.exception;

public class ValorDepositoIncorretoException extends RuntimeException{
    public ValorDepositoIncorretoException(String saldoInsulficiente) {
        super(saldoInsulficiente);
    }
}
