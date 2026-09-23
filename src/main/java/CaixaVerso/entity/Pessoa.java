package CaixaVerso.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Pessoas")

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(length = 11, unique = true)
    private String cpf;

    private String email;

    protected Pessoa(){

    }

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        if(!email.contains("@"))
            throw new IllegalArgumentException("E-mail invalido");
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
