package CaixaVerso.exception;

import CaixaVerso.exception.Erro400ReuisicaoInvalida.ClasseRequisicaoInvalida;
import CaixaVerso.exception.Erro404NaoEncontrato.ClasseNotFound;
import CaixaVerso.exception.Erro409Conflito.ClasseConflito;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.OnClose;
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

    //404 NOT FOUND
    @ExceptionHandler({
            ClasseNotFound.class,
    })
    ResponseEntity<Map<String, Object>> RetornoNaoEncontrado(ClasseNotFound erro) {
        return resposta(HttpStatus.NOT_FOUND, erro.getMessage());
    }

    //409 CONFLITO
    @ExceptionHandler({ClasseConflito.class})
    ResponseEntity<Map<String, Object>> RetornoErroConflito(ClasseConflito erro){
        return resposta(HttpStatus.CONFLICT,erro.getMessage());
    }

    //400 Requisicao Invalida
    @ExceptionHandler({ClasseRequisicaoInvalida.class})
    ResponseEntity<Map<String, Object>> RetornoErroConflito(ClasseRequisicaoInvalida erro){
        return resposta(HttpStatus.BAD_REQUEST,erro.getMessage());
    }

    /** Oculta detalhes internos quando ocorrer uma falha não prevista. */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> tratarInesperado(
            Exception erro,
            HttpServletRequest request) {
        return resposta(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno não esperado."
        );
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
