package br.unoeste.fipp.socialcare.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name="cidade")
public class City {

    //Atributos proprios da tabela cidade
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cid_id")
    private Long id;
    @Column(name="cid_nome")
    private String nome;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name="estado_est_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private State state;

    public City(Long id, String nome, State state) {
        this.id = id;
        this.nome = nome;
        this.state = state;
    }

    public City() {
        this(0L,"",null);
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
