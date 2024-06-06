package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="pessoa_fisica")
public class FisicalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pesf_id")
    private Long id;

    @Column(name="pesf_nome")
    private String nome;

    @Column(name="pesf_logradouro")
    private String logradouro;

    @Column(name="pesf_bairro")
    private String bairro;

    @Column(name="pesf_cep")
    private String cep;

    @Column(name="pesf_email")
    private String email;

    @Column(name="pesf_ativo")
    private Boolean ativo;

    @Column(name="pesf_cpf")
    private String cpf;

    @Column(name="pesf_rg")
    private String rg;

    @Column(name="pesf_dtnascimento")
    private String dtnascimento;

    // Foreign Key
    @ManyToOne
    @JoinColumn(name="cidade_cid_id", nullable = false)
    private City cidade;

    @ManyToOne
    @JoinColumn(name="genero_gen_id", nullable = false)
    private Gender genero;

    // Construtor completo
    public FisicalPerson(Gender genero, City cidade, String dtnascimento, String rg, String cpf, String email, Boolean ativo, String bairro, String cep, String logradouro, String nome, Long id) {
        this.genero = genero;
        this.cidade = cidade;
        this.dtnascimento = dtnascimento;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.ativo = ativo;
        this.bairro = bairro;
        this.cep = cep;
        this.logradouro = logradouro;
        this.nome = nome;
        this.id = id;
    }

    // Construtor padrão
    public FisicalPerson() {
        this(null, null, "", "", "", "", false, "", "", "", "", 0L);
    }

    // Getters e Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(String dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public City getCidade() {
        return cidade;
    }

    public void setCidade(City cidade) {
        this.cidade = cidade;
    }

    public Gender getGenero() {
        return genero;
    }

    public void setGenero(Gender genero) {
        this.genero = genero;
    }
}
