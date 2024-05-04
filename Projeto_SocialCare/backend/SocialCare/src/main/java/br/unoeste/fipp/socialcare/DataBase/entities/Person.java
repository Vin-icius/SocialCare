package br.unoeste.fipp.socialcare.DataBase.entities;
import jakarta.persistence.*;

@Entity
@Table(name="pessoa")
public abstract class Person {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TERÁ QUE INSTANCIAR A CLASSE SERVICE AQUI DENTRO DAS ENTIDADES PARA UTILIZAR OS SERVIÇOS, TANTO COMO AS DAOS (REPOSITORIOS)//
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Atributos proprios da tabela de pessoa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pes_id")
    private Long id;
    @Column(name="pes_nome")
    private String nome;
    @Column(name="pes_endereco")
    private String endereco;
    @Column(name="pes_cep")
    private String cep;

    // Foreign Key
    @ManyToOne
    @JoinColumn(name="cidade_cid_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private City city;


    public Person() {
            this(0L,"","",null,"");
    }

    public Person(Long id, String nome, String endereco,City cidade, String cep) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.city=cidade;
        this.cep = cep;
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

    public City getCidade() {
        return city;
    }

    public void setCidade(City cidade) {
        this.city = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
