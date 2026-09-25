package CaixaVerso.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SaqueRequest(
        @NotNull
        BigDecimal valor
) {
}
