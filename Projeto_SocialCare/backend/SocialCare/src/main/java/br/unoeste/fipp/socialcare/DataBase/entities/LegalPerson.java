package br.unoeste.fipp.socialcare.DataBase.entities;


import jakarta.persistence.*;

@Entity
@Table(name="juridica")
public class LegalPerson extends Person{


    @ManyToOne
    @JoinColumn(name="genero_gen_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Gender genero;

    @Column(name="jur_cnpj")
    private String cnpj;
    @Column(name="jur_ie")
    private String ie;
    @Column(name="jur_site")
    private String site;


    public LegalPerson() {
        this(0L,"","","","","",0,null,null,"","","");
    }

    public LegalPerson(Long id, String nome, String logradouro, String bairro, String cep, String email, int ativo,
                       City city, Gender genero, String cnpj, String ie, String site) {
        super(id, nome, logradouro, bairro, cep, email, ativo, city);
        this.genero = genero;
        this.cnpj = cnpj;
        this.ie = ie;
        this.site = site;
    }

    public Gender getGenero() {
        return genero;
    }

    public void setGenero(Gender genero) {
        this.genero = genero;
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

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String getLogradouro() {
        return super.getLogradouro();
    }

    @Override
    public void setLogradouro(String logradouro) {
        super.setLogradouro(logradouro);
    }

    @Override
    public String getBairro() {
        return super.getBairro();
    }

    @Override
    public void setBairro(String bairro) {
        super.setBairro(bairro);
    }

    @Override
    public String getCep() {
        return super.getCep();
    }

    @Override
    public void setCep(String cep) {
        super.setCep(cep);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public int getAtivo() {
        return super.getAtivo();
    }

    @Override
    public void setAtivo(int ativo) {
        super.setAtivo(ativo);
    }

    @Override
    public City getCity() {
        return super.getCity();
    }

    @Override
    public void setCity(City city) {
        super.setCity(city);
    }
}
