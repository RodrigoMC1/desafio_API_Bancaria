package CaixaVerso.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PessoaResponse(
        @Schema(example = "1") Long id,
        @Schema(example = "Filipe Coordenador") String nome,
        @Schema(example = "123456790") String cpf,
        @Schema(example = "filipecoordenador@caixa.gov.br") String email
) {
}