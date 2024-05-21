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
        this(null,"","","");
    }

    public LegalPerson(Gender genero, String cnpj, String ie, String site) {
        this.genero = genero;
        this.cnpj = cnpj;
        this.ie = ie;
        this.site = site;
    }
}