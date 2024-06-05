package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="acoes")
public class Actions {
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
    @JoinColumn(name="plano_pla_id", nullable = false)
    private Plan plano;


    public Actions() {
        this(0L,"","",null);
    }

    public Actions(Long id, String dtacao, String descricao, Plan plano) {
        this.id = id;
        this.dtacao = dtacao;
        this.descricao = descricao;
        this.plano = plano;
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

    public Plan getPlano() {
        return plano;
    }

    public void setPlano(Plan plano) {
        this.plano = plano;
    }
}
