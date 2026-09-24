package CaixaVerso.controller;


import CaixaVerso.controller.dto.PessoaRequest;
import CaixaVerso.controller.dto.PessoaResponse;
import CaixaVerso.entity.Pessoa;
import CaixaVerso.exception.CpfJaCadastradoException;
import CaixaVerso.repository.PessoaRepository;
import CaixaVerso.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api_bancaria/pessoa")
@Tag(name="Pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<PessoaResponse> cadastrar(@Valid @RequestBody PessoaRequest dto){
        Pessoa pessoa = pessoaService.cadastrarPessoa(dto.nome(),dto.cpf(), dto.email());

        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoa.getId())
                .toUri();

        return ResponseEntity.created(localizacao).body(PessoaResponse.de(pessoa));
    }

}
