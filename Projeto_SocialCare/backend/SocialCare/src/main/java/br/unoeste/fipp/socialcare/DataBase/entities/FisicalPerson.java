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
    private String nome;
    @Column(name="fis_dtnasc")
    private String dtnasc;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name="genero_gen_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Gender genero;

    public FisicalPerson() {
        this(null,"","","",null);
    }

    public FisicalPerson(Person person, String cpf, String rg, String dtnasc, Gender gender) {
        this.pessoa=person;
        this.cpf=cpf;
        this.nome=rg;
        this.dtnasc=dtnasc;
        this.genero=gender;
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

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
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
}
