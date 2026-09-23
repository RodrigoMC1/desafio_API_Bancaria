package CaixaVerso.controller.dto;
import java.math.BigDecimal;

public record ContaBancariaRequest(String agencia,
                                   String numero,
                                   BigDecimal saldo,
                                   boolean ativa,
                                   Long pessoaId,
                                   Long tipoContaId) {
}
