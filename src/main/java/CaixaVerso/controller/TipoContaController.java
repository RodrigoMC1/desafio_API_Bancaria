package CaixaVerso.controller;

import CaixaVerso.controller.dto.TipoContaResponse;
import CaixaVerso.entity.TipoConta;
import CaixaVerso.service.TipoContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api_bancaria/tipo")
@Tag(name="Tipo Conta")
public class TipoContaController {

    private final TipoContaService tipoContaService;

    public TipoContaController(TipoContaService tipoContaService) {
        this.tipoContaService = tipoContaService;
    }


}
