package CaixaVerso.controller;

import CaixaVerso.controller.dto.ContaBancariaRequest;
import CaixaVerso.controller.dto.ContaBancariaResponse;
import CaixaVerso.entity.ContaBancaria;
import CaixaVerso.repository.ContaBancariaRepository;
import CaixaVerso.repository.PessoaRepository;
import CaixaVerso.service.ContaBancariaService;
import CaixaVerso.service.TipoContaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api_bancaria/conta")
@Tag(name="Conta Bancaria")
public class ContaBancariaController {

    private final ContaBancariaService contaBancariaService;

    public ContaBancariaController(ContaBancariaService contaBancariaService) {
        this.contaBancariaService = contaBancariaService;
    }

    @PostMapping("/abrir")
    public ResponseEntity<ContaBancariaResponse> abrir(@Valid @RequestBody ContaBancariaRequest dto){
        ContaBancaria conta = contaBancariaService.cadastrarContaBancaria(
                dto.agencia(),
                dto.numero(),
                dto.saldo(),
                dto.ativa(),
                dto.titularId(),
                dto.tipoContaId()
        );

        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(conta.getId())
                .toUri();

        return ResponseEntity.created(localizacao).body(ContaBancariaResponse.de(conta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancariaResponse> buscar(@PathVariable Long id){

        ContaBancaria conta = contaBancariaService.buscarPorId(id);

        return ResponseEntity.ok(ContaBancariaResponse.de(conta));
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<List<ContaBancariaResponse>> buscarPorPessoa(@PathVariable Long id){

        List<ContaBancaria> contas = contaBancariaService.buscarContasPorPessoa(id);

        return ResponseEntity.ok(contas.stream().map(ContaBancariaResponse::de).toList());
    }


}
