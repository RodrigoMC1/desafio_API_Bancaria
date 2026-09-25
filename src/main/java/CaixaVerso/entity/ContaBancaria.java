package CaixaVerso.entity;

import CaixaVerso.exception.SaldoInsuficienteException;
import CaixaVerso.exception.ValorDepositoIncorretoException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table (name = "Contas_Bancarias" , uniqueConstraints =
@UniqueConstraint(columnNames = {"agencia", "numero"}))
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String agencia;

    private String numero;

    private BigDecimal saldo;

    private boolean ativa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "titular_id", nullable = false)
    private Pessoa titular;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "tipo_conta_id", nullable = false)
    private TipoConta tipoConta;

    protected ContaBancaria(){

    }

    public ContaBancaria(String agencia, String numero, BigDecimal saldoInicial, boolean ativa, Pessoa titular, TipoConta tipoConta) {

        if (saldoInicial ==null ){
            throw new IllegalArgumentException("Informe o saldo inicial");
        }
        if(saldoInicial.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo");
        }
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldoInicial;
        this.ativa = ativa;
        this.titular = titular;
        this.tipoConta = tipoConta;
    }

    public Long getId() {
        return id;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }


    public void sacar(BigDecimal valor){
        if(saldo.compareTo(valor) > 0){
            throw new SaldoInsuficienteException("Saldo insulficiente");
        }
        this.saldo = saldo.subtract(valor);
    }

    public void depositar(@NotNull BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new ValorDepositoIncorretoException("Valor do deposito é invalido");
        }
        this.saldo = saldo.add(valor);
    }
}
