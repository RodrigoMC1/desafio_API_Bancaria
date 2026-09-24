package CaixaVerso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GerenciadorException {

    public GerenciadorException() {
    }

    //NOT FOUND
    @ExceptionHandler({
            PessoaNaoEncontradaException.class,
    })
    ResponseEntity<Map<String, Object>> PessoaNaoEncontrado(PessoaNaoEncontradaException erro) {
        return resposta(HttpStatus.NOT_FOUND, erro.getMessage());
    }
    @ExceptionHandler({
            ContaNaoEncontradaException.class,
    })
    ResponseEntity<Map<String, Object>> ContaNaoEncontrado(ContaNaoEncontradaException erro) {
        return resposta(HttpStatus.NOT_FOUND, erro.getMessage());
    }
    @ExceptionHandler({
            TipoContaNaoEncontradaException.class
    })
    ResponseEntity<Map<String, Object>> TipoContaNaoEncontrado(TipoContaNaoEncontradaException erro) {
        return resposta(HttpStatus.NOT_FOUND, erro.getMessage());
    }

    private ResponseEntity<Map<String, Object>> resposta(HttpStatus status, String detalhe) {
        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("timestamp", Instant.now());
        corpo.put("status", status.value());
        corpo.put("erro", status.getReasonPhrase());
        corpo.put("detalhe", detalhe);
        return ResponseEntity.status(status).body(corpo);
    }
}
