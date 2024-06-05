package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="plano")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pla_id")
    private Long id;
    @Column(name="pla_dtinicio")
    private String dtinicio;
    @Column(name="pla_dtfim")
    private String dtfim;

    @Column(name="pla_observacao")
    private String observacao;


    @ManyToOne
    @JoinColumn(name="usuario_usu_id", nullable = false)
    private User usuario;
    @ManyToOne
    @JoinColumn(name="pessoa_fisica_pesf_id", nullable = false)
    private FisicalPerson pessoa;



    public FisicalPerson getPessoa() {
        return pessoa;
    }

    public void setPessoa(FisicalPerson pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDtinicio() {
        return dtinicio;
    }

    public void setDtinicio(String dtinicio) {
        this.dtinicio = dtinicio;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDtfim() {
        return dtfim;
    }

    public void setDtfim(String dtfim) {
        this.dtfim = dtfim;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
