package br.unoeste.fipp.socialcare.DataBase.entities;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.time.LocalDate;

@Entity
@Table(name = "compra")
public class Compra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="com_id")
    private Long id;
    @Column(name="com_data")
    private LocalDate date;
    @Column(name = "com_vlrotal")
    private Double totalValue;

    @Column(name = "com_observacao")
    private String desc;
    @ManyToOne
    @JoinColumn(name = "juridica_pessoa_pes_id", nullable = false)
    private LegalPerson legalPerson;
    public Compra(){
        this(0L,null,null,null,null);
    }
    public Compra(Long id, LocalDate date, Double totalValue, String desc, LegalPerson legalPerson) {
        this.id = id;
        this.date = date;
        this.totalValue = totalValue;
        this.desc = desc;
        this.legalPerson = legalPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }




}
