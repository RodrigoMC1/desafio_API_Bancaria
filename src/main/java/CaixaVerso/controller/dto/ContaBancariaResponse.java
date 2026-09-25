package CaixaVerso.controller.dto;

import CaixaVerso.entity.ContaBancaria;
import io.swagger.v3.oas.annotations.media.Schema;


import java.math.BigDecimal;

public record ContaBancariaResponse(
        @Schema(example = "1") Long id,
        @Schema(example = "2343") String agencia,
        @Schema(example = "997973") String numero,
        @Schema(example = "1000.00") BigDecimal saldo,
        @Schema(example = "true") boolean ativa,
        @Schema(example = "1") Long pessoaId,
        @Schema(example = "Carlos") String pessoaNome,
        @Schema(example = "1") Long tipoContaId,
        @Schema(example = "Poupança") String tipoContaNome
) {
    public static ContaBancariaResponse de(ContaBancaria conta) {
        return new ContaBancariaResponse(
            conta.getId(),
            conta.getAgencia(),
            conta.getNumero(),
            conta.getSaldo(),
            conta.isAtiva(),
            conta.getTitular().getId(),
            conta.getTitular().getNome(),
            conta.getTipoConta().getId(),
            conta.getTipoConta().getNome()
        );
    }
}
