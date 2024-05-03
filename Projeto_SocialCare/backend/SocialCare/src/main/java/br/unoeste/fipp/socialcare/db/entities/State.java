package br.unoeste.fipp.socialcare.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name="estado")
public class State {

    //Atributos proprios da tabela estado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="est_id")
    private Long id;
    @Column(name="est_nome")
    private String nome;

    public State(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public State() {
        this(0L,"");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
