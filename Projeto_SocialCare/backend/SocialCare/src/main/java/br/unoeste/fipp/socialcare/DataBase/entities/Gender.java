package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="genero")
public class Gender {

    //Atributos proprios da tabela genero
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gen_id")
    private Long id;
    @Column(name="gen_nome")
    private String nome;

    public Gender(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Gender() {
        this(0L, "");
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
