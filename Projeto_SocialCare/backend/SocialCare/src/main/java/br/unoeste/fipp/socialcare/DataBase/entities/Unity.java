package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="unidade")
public class Unity {

    //Atributos proprios da tabela unidade
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uni_id")
    private Long id;
    @Column(name="uni_nome")
    private String nome;
    @Column(name="uni_endereco")
    private String endereco;
    @Column(name="uni_cep")
    private String cep;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name="cidade_cid_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private City city;

    public Unity(Long id, String nome, String endereco, String cep, City city) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.city = city;
    }

    public Unity() {
        this(0L, "", "", "", null);
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}