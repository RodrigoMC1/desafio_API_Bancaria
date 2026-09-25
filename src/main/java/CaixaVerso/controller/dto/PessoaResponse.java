package CaixaVerso.controller.dto;

import CaixaVerso.entity.Pessoa;
import io.swagger.v3.oas.annotations.media.Schema;

public record PessoaResponse(
        @Schema(example = "1") Long id,
        @Schema(example = "Filipe Coordenador") String nome,
        @Schema(example = "13221739799") String cpf,
        @Schema(example = "filipecoordenador@caixa.gov.br") String email
) {
    public static PessoaResponse de(Pessoa pessoa){
        return new PessoaResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getEmail());
    }
}