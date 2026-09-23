package CaixaVerso.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PessoaRequest(
        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        @Email
        String email
) {
}
