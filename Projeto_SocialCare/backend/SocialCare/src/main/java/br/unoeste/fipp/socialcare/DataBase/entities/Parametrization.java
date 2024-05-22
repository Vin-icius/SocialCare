package br.unoeste.fipp.socialcare.DataBase.entities;


import jakarta.persistence.*;
@Entity
@Table(name="parametrizacao")
public class Parametrization {

    //Atributos proprios da tabela de parametrização
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "par_id")
    private Long id;
    @Column(name = "par_fantasia")
    private String fantasia;
    @Column(name = "par_razao")
    private String razao;
    @Column(name = "par_cnpj")
    private String cnpj;
    @Column(name = "par_logradouro")
    private String logradouro;
    @Column(name = "par_bairro")
    private String bairro;
    @Column(name = "par_cep")
    private String cep;
    @Column(name = "par_email")
    private String email;
    @Column(name = "par_site")
    private String site;
    @Column(name = "par_logogrande")
    private String logoG;
    @Column(name = "par_logopequena")
    private String logoP;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name = "cidade_cid_id", nullable = false)
    // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private City city;

    public Parametrization(Long id, String fantasia, String razao, String cnpj, String logradouro, String bairro, String cep, String email, String site, String logoG, String logoP, City city) {
        this.id = id;
        this.fantasia = fantasia;
        this.razao = razao;
        this.cnpj = cnpj;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.email = email;
        this.site = site;
        this.logoG = logoG;
        this.logoP = logoP;
        this.city = city;
    }

    public Parametrization() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLogoG() {
        return logoG;
    }

    public void setLogoG(String logoG) {
        this.logoG = logoG;
    }

    public String getLogoP() {
        return logoP;
    }

    public void setLogoP(String logoP) {
        this.logoP = logoP;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
