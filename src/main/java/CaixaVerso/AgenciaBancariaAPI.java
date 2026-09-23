package CaixaVerso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da API didática de fundamentos.
 */
@SpringBootApplication
public class AgenciaBancariaAPI {
    /** Classe utilitária de inicialização; não deve ser instanciada. */
    private AgenciaBancariaAPI() {
    }

    /**
     * Inicializa o Spring Boot e o servidor HTTP incorporado.
     *
     * @param args argumentos opcionais de inicialização
     */
    public static void main(String[] args) {
        SpringApplication.run(AgenciaBancariaAPI.class, args);
    }
}
