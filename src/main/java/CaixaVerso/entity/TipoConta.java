package CaixaVerso.entity;

import jakarta.persistence.*;

@Entity
@Table
public class TipoConta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    protected TipoConta(){

    }

    public TipoConta(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
