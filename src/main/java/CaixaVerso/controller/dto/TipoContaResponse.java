package CaixaVerso.controller.dto;
import CaixaVerso.entity.TipoConta;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record TipoContaResponse(
        @Schema(example = "Poupança") String nome
) {
    public TipoContaResponse de(TipoConta tipoConta){
        return new TipoContaResponse(tipoConta.getNome());

    }
}
