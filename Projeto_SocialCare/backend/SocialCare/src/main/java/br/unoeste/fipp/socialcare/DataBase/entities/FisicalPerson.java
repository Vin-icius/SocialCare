package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="fisica")
public class FisicalPerson extends Person{



    //Foreign Key
    @ManyToOne
    @JoinColumn(name="pessoa_pes_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Person pessoa;

    //Atributos proprios da tabela de pessoa fisica
    @Column(name="fis_cpf")
    private String cpf;
    @Column(name="fis_rg")
    private String rg;
    @Column(name="fis_dtnasc")
    private String dtnasc;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name="genero_gen_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Gender genero;

    public FisicalPerson() {
        this(0L,"","","","","",0,null,"","","",null);
    }

    public FisicalPerson(Long id, String nome, String logradouro, String bairro, String cep,
                         String email, int ativo, City city, String cpf, String rg, String dtnasc,
                         Gender genero) {
        super(id, nome, logradouro, bairro, cep, email, ativo, city);
        this.cpf = cpf;
        this.rg = rg;
        this.dtnasc = dtnasc;
        this.genero = genero;
    }

    public Person getPessoa() {
        return pessoa;
    }

    public void setPessoa(Person pessoa) {
        this.pessoa = pessoa;
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

    public String getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(String dtnasc) {
        this.dtnasc = dtnasc;
    }

    public Gender getGenero() {
        return genero;
    }

    public void setGenero(Gender genero) {
        this.genero = genero;
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
