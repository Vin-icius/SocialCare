package br.unoeste.fipp.socialcare.DataBase.entities;


import jakarta.persistence.*;

@Entity
@Table(name="pessoa_juridica")
public class LegalPerson{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pesj_id")
    private Long id;
    @Column(name= "pesj_nome")
    private String nome;
    @Column(name="pesj_logradouro")
    private String logradouro;
    @Column(name="pesj_bairro")
    private String bairro;
    @Column(name="pesj_cep")
    private String cep;
    @ManyToOne
    @JoinColumn(name="cidade_cid_id", nullable = false)
    private City cidade;
    @Column(name="pesj_email")
    private String email;
    @Column(name = "pesj_ativo")
    private Boolean ativo;
    @Column(name = "pesj_cnpj")
    private String cnpj;
    @Column(name = "pesj_ie")
    private String ie;
    @Column(name = "pesj_site")
    private String site;


    public LegalPerson(Long id, String nome, String logradouro, String bairro, String cep, City cidade, String email, Boolean ativo, String cnpj, String ie, String site) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.email = email;
        this.ativo = ativo;
        this.cnpj = cnpj;
        this.ie = ie;
        this.site = site;
    }

    public LegalPerson() {
         this(0L,null,null,null,null,null,null,true,null,null,null);
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

    public City getCidade() {
        return cidade;
    }

    public void setCidade(City cidade) {
        this.cidade = cidade;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}