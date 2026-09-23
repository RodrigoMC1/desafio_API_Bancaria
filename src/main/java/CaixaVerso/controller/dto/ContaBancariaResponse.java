package CaixaVerso.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ContaBancariaResponse(

        @Schema(example = "2343") String agencia,
        @Schema(example = "997973") String numero,
        @Schema(example = "1000.00") BigDecimal saldo,
        @Schema(example = "true") boolean ativa,
        @Schema(example = "1") Long pessoaId,
        @Schema(example = "1") Long tipoContaId
) {
}
