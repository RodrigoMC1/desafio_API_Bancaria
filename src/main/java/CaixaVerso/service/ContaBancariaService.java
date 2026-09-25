package CaixaVerso.service;

import CaixaVerso.entity.ContaBancaria;
import CaixaVerso.entity.Pessoa;
import CaixaVerso.entity.TipoConta;
import CaixaVerso.exception.*;
import CaixaVerso.repository.ContaBancariaRepository;
import CaixaVerso.repository.PessoaRepository;
import CaixaVerso.repository.TipoContaRepository;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    public ContaBancaria buscarPorId(Long id){
        return contaBancariaRepository.findById(id)
                .orElseThrow(()-> new ContaNaoEncontradaException());
    }

    public List<ContaBancaria> buscarContasPorPessoa(Long id){
        pessoaRepository.findById(id).orElseThrow(
                ()-> new PessoaNaoEncontradaException()
        );

        return contaBancariaRepository.findByTitularId(id);
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
}
