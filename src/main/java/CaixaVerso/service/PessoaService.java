package CaixaVerso.service;

import CaixaVerso.entity.Pessoa;
import CaixaVerso.entity.TipoConta;
import CaixaVerso.exception.ContaNaoEncontradaException;
import CaixaVerso.exception.CpfJaCadastradoException;
import CaixaVerso.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Long id){
        return pessoaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pessoa não Localizada!") );
    }

    @Transactional
    public void cadastrarPessoa(String nome, String cpf, String email){
        if(pessoaRepository.existsByCpf(cpf))
           throw new CpfJaCadastradoException();

        pessoaRepository.save(
                new Pessoa(nome, cpf, email));
    }
}
