package CaixaVerso.exception.Erro404NaoEncontrato;

public class ClasseNotFound extends RuntimeException{
    public ClasseNotFound(String mensagem){
        super(mensagem);
    }
}
