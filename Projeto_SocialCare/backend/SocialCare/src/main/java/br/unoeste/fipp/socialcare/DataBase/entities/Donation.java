package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="doacao")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doa_id")
    private Long id;
    @Column(name = "doa_data")
    private LocalDate data;

    @Column(name = "doa_observacao")
    private String observacao;

    // Foreign Key
    @ManyToOne
    @JoinColumn(name = "pesf_id", nullable = false)
    private FisicalPerson pessoa;

    public Donation() {
        this(0L,null,"",null);
    }

    public Donation(Long id, LocalDate data, String observacao,  FisicalPerson pessoa) {
        this.id = id;
        this.data = data;
        this.observacao = observacao;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


    public FisicalPerson getPessoa() {
        return pessoa;
    }

    public void setPessoa(FisicalPerson pessoa) {
        this.pessoa = pessoa;
    }
}


