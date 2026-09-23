package CaixaVerso.service;

import CaixaVerso.entity.TipoConta;
import CaixaVerso.exception.ContaNaoEncontradaException;
import CaixaVerso.repository.TipoContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoContaService {
    private final TipoContaRepository tipoContaRepository;

    public TipoContaService(TipoContaRepository tipoContaRepository) {
        this.tipoContaRepository = tipoContaRepository;
    }

    public List<TipoConta> listar(){
        return tipoContaRepository.findAll();
    }

    public TipoConta buscarPorId(Long id){
        return tipoContaRepository.findById(id)
                .orElseThrow(()-> new ContaNaoEncontradaException("Tipo de Conta não Localizada!") );
    }

    @Transactional
    public void criar(String nome){
        tipoContaRepository.save(new TipoConta(nome));
    }




}
