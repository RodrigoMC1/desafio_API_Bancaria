package CaixaVerso.controller.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ContaBancariaRequest(
        @Size(max = 4)
        String agencia,
        String numero,
        BigDecimal saldo,
        boolean ativa,
        @NotNull
        Long titularId,
        @NotNull
        Long tipoContaId) {
}
