package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="acoes")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="aco_id")
    private Long id;
    @Column(name="aco_dtacao")
    private String dtacao;

    @Column(name="aco_descricao")
    private String descricao;

    // Foreign Key
    @ManyToOne
    @JoinColumn(name="usuario_usu_id")
    private User usuario;
    @ManyToOne
    @JoinColumn(name="pesf_id", nullable = false)
    private FisicalPerson pessoa;


    public Action() {
        this(0L,"","",null,null);
    }

    public Action(Long id, String dtacao, String descricao, User usuario, FisicalPerson pessoa) {
        this.id = id;
        this.dtacao = dtacao;
        this.descricao = descricao;
        this.usuario=usuario;
        this.pessoa=pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDtacao() {
        return dtacao;
    }

    public void setDtacao(String dtacao) {
        this.dtacao = dtacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
