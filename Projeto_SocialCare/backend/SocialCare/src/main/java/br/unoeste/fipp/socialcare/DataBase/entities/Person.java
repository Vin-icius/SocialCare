package br.unoeste.fipp.socialcare.DataBase.entities;
import jakarta.persistence.*;

@Entity
@Table(name="pessoa")
public abstract class Person {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TERÁ QUE INSTANCIAR A CLASSE SERVICE AQUI DENTRO DAS ENTIDADES PARA UTILIZAR OS SERVIÇOS, TANTO COMO AS DAOS (REPOSITORIOS)//
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Atributos proprios da tabela de pessoa
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pes_id")
    private Long id;
    @Column(name="pes_nome")
    private String nome;
    @Column(name="pes_logradouro")
    private String logradouro;
    @Column(name="pes_bairro")
    private String bairro;
    @Column(name="pes_cep")
    private String cep;
    @Column(name="pes_email")
    private String email;
    @Column(name="pes_ativo")
    private int ativo;
    //@JoinColumn(name="cidade_cid_id", referencedColumnName="cid_id")
    // Foreign Key
    @ManyToOne
    @JoinColumn(name="cidade_cid_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private City city;


    public Person() {
    }

    public Person(Long id, String nome, String logradouro, String bairro, String cep, String email, int ativo, City city) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.email = email;
        this.ativo = ativo;
        this.city = city;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
