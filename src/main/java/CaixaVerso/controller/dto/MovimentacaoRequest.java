package CaixaVerso.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record MovimentacaoRequest(
        @NotNull
        @Positive
        BigDecimal valor
) {


}
