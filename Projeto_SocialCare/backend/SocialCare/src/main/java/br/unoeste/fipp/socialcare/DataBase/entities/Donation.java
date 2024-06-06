package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="doacao")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doa_id")
    private Long id;
    @Column(name = "doa_data")
    private String data;

    @Column(name = "doa_observacao")
    private String observacao;

    // Foreign Key
    @ManyToOne
    @JoinColumn(name = "usuario_usu_id")
    private User usuario;
    @ManyToOne
    @JoinColumn(name = "pessoa_pes_id", nullable = false)
    private FisicalPerson pessoa;

    public Donation() {
        this(0L,"","",null,null);
    }

    public Donation(Long id, String data, String observacao, User usuario, FisicalPerson pessoa) {
        this.id = id;
        this.data = data;
        this.observacao = observacao;
        this.usuario = usuario;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public FisicalPerson getPessoa() {
        return pessoa;
    }

    public void setPessoa(FisicalPerson pessoa) {
        this.pessoa = pessoa;
    }
}


