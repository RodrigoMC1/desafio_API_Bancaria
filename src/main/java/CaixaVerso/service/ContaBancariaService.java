package CaixaVerso.service;

import CaixaVerso.controller.dto.ContaBancariaResponse;
import CaixaVerso.entity.ContaBancaria;
import CaixaVerso.entity.Pessoa;
import CaixaVerso.entity.TipoConta;
import CaixaVerso.exception.*;
import CaixaVerso.repository.ContaBancariaRepository;
import CaixaVerso.repository.PessoaRepository;
import CaixaVerso.repository.TipoContaRepository;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;
    private final TipoContaRepository tipoContaRepository;
    private final PessoaRepository pessoaRepository;

    public ContaBancariaService(ContaBancariaRepository contaBancariaRepository, TipoContaRepository tipoContaRepository, PessoaRepository pessoaRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
        this.tipoContaRepository = tipoContaRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<ContaBancaria> listar(){
        return contaBancariaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ContaBancariaResponse buscarPorId(Long id){
        return ContaBancariaResponse.de(
                contaBancariaRepository.findById(id)
                .orElseThrow(()-> new ContaNaoEncontradaException())
        );
    }

    @Transactional(readOnly = true)
    public List<ContaBancariaResponse> buscarContasPorPessoa(Long id){
        pessoaRepository.findById(id).orElseThrow(
                ()-> new PessoaNaoEncontradaException()
        );

        List<ContaBancariaResponse> contas = contaBancariaRepository.findByTitularId(id)
                .stream().map(ContaBancariaResponse::de).toList();
        return contas;
    }

    @Transactional
    public ContaBancaria cadastrarContaBancaria(String agencia, String numero, BigDecimal saldo, boolean ativa, Long idPessoa, Long idTipoConta ){
        //TipocontaExiste
        TipoConta tipoConta = tipoContaRepository.findById(idTipoConta).orElseThrow(() ->new TipoContaNaoEncontradaException());

        //PessoaExiste
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(()->new PessoaNaoEncontradaException());

        //ContaExiste
            if(contaBancariaRepository.existsByAgenciaAndNumero(agencia,numero))
                throw new ContaJaCadastradaException();

            ContaBancaria novaConta = new ContaBancaria(agencia,numero,saldo,ativa,pessoa,tipoConta);

        System.out.println(novaConta);

        return contaBancariaRepository.save(novaConta);
    }

    @Transactional
    public ContaBancariaResponse sacar(Long id, @NotNull BigDecimal valor) {

        ContaBancaria conta = contaBancariaRepository.findById(id)
                .orElseThrow(()-> new ContaNaoEncontradaException());

        conta.sacar(valor);

        return ContaBancariaResponse.de(conta);
    }

    @Transactional
    public ContaBancariaResponse depositar(Long id, @NotNull BigDecimal valor) {

        ContaBancaria conta = contaBancariaRepository.findById(id).orElseThrow(()-> new ContaNaoEncontradaException());

        conta.depositar(valor);
        return ContaBancariaResponse.de(conta);

    }
}
