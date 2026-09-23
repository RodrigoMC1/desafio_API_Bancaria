package CaixaVerso.repository;

import CaixaVerso.entity.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

    boolean existsByAgenciaAndNumero(String agencia, String numero);
}
